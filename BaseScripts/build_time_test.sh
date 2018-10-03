#!/bin/bash

function runTests() {
    CALCULATION_TIMES=$1
    setIfNull CALCULATION_TIMES 10
    TEST_FOLDER=$2

    cd $TEST_FOLDER

    result=()

    for ((i=0; i<CALCULATION_TIMES ; i++)); do
        # result[i]="$i.1"
        result[i]=`(echo $(mvn clean package | grep -o "Total time: [0-9.]* s") | grep -o [0-9.]*)`
    done

    echo ${result[*]}
}