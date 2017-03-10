#!/usr/bin/env bash

echo "watch...."

trap 'exit' INT

while :
do
    bazel run --strategy=Scalac=worker :akka &
    PID=$!

    watchman-wait . -p '**/*.scala'
    kill $PID
done
