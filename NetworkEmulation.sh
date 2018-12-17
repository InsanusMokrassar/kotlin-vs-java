#!/bin/bash

./scripts_bridge_build.sh -f kotlin/NetworkEmulationKotlin/ -f java/NetworkEmulationJava/ $@
echo
./execution_time_test.sh -f kotlin/NetworkEmulationKotlin/ -f java/NetworkEmulationJava/ $@
