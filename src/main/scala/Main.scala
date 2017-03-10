import akka.actor.{Actor, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink

import scala.concurrent.{Future, Promise}

object Main extends App  {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val serverSource = Http().bind(interface = "localhost", port = 8080)

  object RequestHandler {
    case class Handle(promise: Promise[HttpResponse])
  }

  class RequestHandler extends Actor {
    import RequestHandler._
    def receive = {
      case Handle(promise) =>
        promise.success(HttpResponse(entity = HttpEntity(
          ContentTypes.`text/html(UTF-8)`,
          "Hello world!\n")))
        context.stop(self)
    }
  }

  val requestHandler: HttpRequest => Future[HttpResponse] = { request =>
      // println("Accepted new request from " + request.method)
      val promise = Promise[HttpResponse]
      system.actorOf(Props[RequestHandler]) ! RequestHandler.Handle(promise)
      promise.future
  }

  val bindingFuture: Future[Http.ServerBinding] =
    serverSource.to(Sink.foreach { connection =>
      //println("Accepted new connection from " + connection.remoteAddress)

      connection handleWithAsyncHandler requestHandler
      // this is equivalent to
      // connection handleWith { Flow[HttpRequest] map requestHandler }
    }).run()
}
