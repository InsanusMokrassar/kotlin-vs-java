#!/bin/bash

function extractFolders() {
    folders=()

    while [ -n "$1" ]
    do
        case "$1" in
            -f) shift; folders+=("$1") ;;
        esac
        shift
    done

    echo ${folders[*]}
}

function extractTestArgs() {
    freeArgs=()

    while [ -n "$1" ]
    do
        case "$1" in
            -a) shift; freeArgs+=("$1") ;;
        esac
        shift
    done

    echo ${freeArgs[*]}
}

function extractJVMArgs() {
    jvmArgs=()

    while [ -n "$1" ]
    do
        case "$1" in
            --jvm-args) shift; jvmArgs+=("$1") ;;
        esac
        shift
    done

    echo ${jvmArgs[*]}
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


