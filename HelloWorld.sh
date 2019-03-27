#!/bin/bash

./scripts_bridge_build.sh -f :KotlinComparisonModule:HelloWorldKotlin -f :JavaComparisonModule:HelloWorldJava $@
echo
./execution_time_test.sh -f :KotlinComparisonModule:HelloWorldKotlin -f :JavaComparisonModule:HelloWorldJava $@
