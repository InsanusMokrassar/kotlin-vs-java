#!/bin/bash

./scripts_bridge_build.sh -f :KotlinComparisonModule:ContactBookKotlin -f :JavaComparisonModule:ContactBookJava $@
echo
./execution_time_test.sh -f :KotlinComparisonModule:ContactBookKotlin -f :JavaComparisonModule:ContactBookJava $@
