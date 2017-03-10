# Build Scala Akka-Http with Bazel

Example how to build akka-http Scala example with [Bazel](https://bazel.build)

## rules_scala

You can use Scala Rules for Bazel ([rules_scala](https://github.com/bazelbuild/rules_scala)) to build Scala projects

## How to run

- Install `Bazel`
- run `bazel run :akka`

## How to convert Sbt project to Bazel

- Copy `WORKSPACE`, `3rdparty`, `tools` to root directory
- Remove `3rdparty/jvm/*`
- Follow steps from `https://github.com/johnynek/bazel-deps` to generate `3rdparty/jvm/*`

```
./gen_maven_deps.sh <PWD> 3rdparty/workspace.bzl dependencies.yaml
```
- run `bazel run :akka`

## Fun -> watch

Install [watchman](https://facebook.github.io/watchman/)

In first terminal window run `./ping.sh`
In second window run `./watch.sh`

When you change something Main.scala project will be re-build and re-run.
