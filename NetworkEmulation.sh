#!/bin/bash

./scripts_bridge_build.sh -f :KotlinComparisonModule:NetworkEmulationKotlin -f :JavaComparisonModule:NetworkEmulationJava $@
echo
./execution_time_test.sh -f :KotlinComparisonModule:NetworkEmulationKotlin -f :JavaComparisonModule:NetworkEmulationJava $@
