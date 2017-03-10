scala_binary(
    name = "akka",
    srcs = glob(["src/main/scala/Main.scala"]),
    resources = ["//src/main/resources:application"],
    deps = [
        "//3rdparty/jvm/com/typesafe/akka:akka_http",
        "//3rdparty/jvm/com/typesafe/akka:akka_http_spray_json",
    ],
    main_class = "Main",
    visibility = ["//visibility:public"]
)
