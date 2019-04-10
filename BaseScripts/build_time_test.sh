#!/bin/bash

function now() {
    echo $(($(date +%s%N)/1000000))
}

function runTests() {
    CALCULATION_TIMES=$1
    setIfNull CALCULATION_TIMES 10
    TEST_FOLDER=$2

    result=()

    assert_success ./gradlew clean build > /dev/null 2> /dev/null

    for ((i=0; i<CALCULATION_TIMES ; i++)); do
        start=`now`
        assert_success ./gradlew $TEST_FOLDER:clean $TEST_FOLDER:build > /dev/null 2> /dev/null
        stop=`now`
        result[i]=$(( $stop - $start ))
    done

    echo ${result[*]}
}

function printApproximateResultsTable() {
    printTableTitle ${folders[*]} -c
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
    printTableTitle "n" ${folders[*]} -c

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
