#!/bin/bash

./scripts_bridge_build.sh -f kotlin/QuickSortKotlin/ -f kotlin/QuickSortNullableKotlin/ -f java/QuickSortJava/
./execution_time_test.sh -f kotlin/QuickSortKotlin/ -f kotlin/QuickSortNullableKotlin/ -f java/QuickSortJava/ -a 100000
