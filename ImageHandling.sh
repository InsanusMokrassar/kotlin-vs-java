#!/bin/bash

./scripts_bridge_build.sh -f :KotlinComparisonModule:ImageHandlingKotlin -f :JavaComparisonModule:ImageHandlingJava $@
echo
./execution_time_test.sh -f :KotlinComparisonModule:ImageHandlingKotlin -f :JavaComparisonModule:ImageHandlingJava $@
