#!/usr/bin/env bash

echo "ping...."

trap 'exit' INT

while :
do
    curl http://127.0.0.1:8080
    sleep 0.5
done
