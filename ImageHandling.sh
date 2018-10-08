#!/bin/bash

./scripts_bridge_build.sh -f kotlin/ImageHandlingKotlin/ -f java/ImageHandlingJava/ $@
echo
./execution_time_test.sh -f kotlin/ImageHandlingKotlin/ -f java/ImageHandlingJava/ $@
