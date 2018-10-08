#!/bin/bash

./scripts_bridge_build.sh -f kotlin/QuickSortKotlin/ -f java/QuickSortJava/ $@
echo
./execution_time_test.sh -f kotlin/QuickSortKotlin/ -f java/QuickSortJava/ -a 100000 $@
