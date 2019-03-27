#!/bin/bash

./scripts_bridge_build.sh -f :KotlinComparisonModule:QuickSortKotlin -f :JavaComparisonModule:QuickSortJava $@
echo
./execution_time_test.sh -f :KotlinComparisonModule:QuickSortKotlin -f :JavaComparisonModule:QuickSortJava $@
