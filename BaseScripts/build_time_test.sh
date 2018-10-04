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

function printApproximateResultsTable() {
    printTableTitle ${folders[*]}
    foldersCount=${#folders[*]}
    approxs=()

    for ((i=0; i<foldersCount; i++))
    do
        start=`round "$i*$CALCULATION_TIMES" 0`
        end=`round "($i+1)*$CALCULATION_TIMES" 0`

        subarray=(${results[@]:start:end})

        approxs[i]=$(round `average ${subarray[*]}` 3)
    done

    printAsPartOfTable ${approxs[*]}
}

function calculateBuildTimeResults() {
    folders=$@
    results=()

    for i in ${!folders[*]}
    do
        results[i]="`runTests $CALCULATION_TIMES ${folders[i]}`"
    done

    echo ${results[*]}
}

function printTableOfResults() {
    printTableTitle "n" ${folders[*]}

    for ((i=0; i<CALCULATION_TIMES;i++))
    do
        vals=()
        vals[0]=$i
        for j in ${!folders[*]}
        do
            offset=`round "$j*$CALCULATION_TIMES+$i" 0`
            vals[j+1]=${results[offset]}
        done
        printAsPartOfTable ${vals[*]}
    done
}
