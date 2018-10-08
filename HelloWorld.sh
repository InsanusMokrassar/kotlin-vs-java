#!/bin/bash

./scripts_bridge_build.sh -f kotlin/HelloWorldKotlin/ -f java/HelloWorldJava/ $@
echo
./execution_time_test.sh -f kotlin/HelloWorldKotlin/ -f java/HelloWorldJava/ $@
