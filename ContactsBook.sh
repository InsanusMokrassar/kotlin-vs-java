#!/bin/bash

./scripts_bridge_build.sh -f kotlin/ContactBookKotlin/ -f java/ContactBookJava/ $@
echo
./execution_time_test.sh -f kotlin/ContactBookKotlin/ -f java/ContactBookJava/ $@
