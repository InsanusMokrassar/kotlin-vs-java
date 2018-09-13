#!/bin/bash

function extractFolders() {
    folders=()

    while [ -n "$1" ]
    do
        case "$1" in
            -n) shift; CALCULATION_TIMES=$1 ;;
            *) folders[${#folders[*]}]=$1 ;;
        esac
        shift
    done

    echo ${folders[*]}
}

function extractCalculationTimes() {

    while [ -n "$1" ]
    do
        case "$1" in
            -n) shift; CALCULATION_TIMES=$1 ;;
        esac
        shift
    done

    setIfNull CALCULATION_TIMES 10

    echo $CALCULATION_TIMES
}

function calculateBuildTimeResults() {
    folders=$@
    results=()

    currentFolder=`pwd`

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

function printApproximateResultsTable() {
    printTableTitle ${folders[*]}
    foldersCount=${#folders[*]}
    approxs=()

    for ((i=0; i<foldersCount; i++))
    do
        start=`round "$i*$CALCULATION_TIMES" 0`
        end=`round "($i+1)*$CALCULATION_TIMES" 0`

        subarray=${results[@]:start:end}

        value=0
        for result in ${subarray[*]}
        do
            value=$(round "($value + $result)" 15)
        done

        approxs[i]=$(round "$value / $CALCULATION_TIMES")
    done

    printAsPartOfTable ${approxs[*]}
}


