#!/usr/bin/env bash

function assert_success() {
    "${@}"
    local status=${?}
    if [ ${status} -ne 0 ]; then
        echo "### Error ${status} at: ${BASH_LINENO[*]} ###"
        exit ${status}
    fi
}

function setIfNull() {
    if [ -z "$(eval echo \$$1)" ]
    then
        export $1="$2"
    fi
}

function printAsPartOfTable() {
    printf "|"
    while [ -n "$1" ]
    do
        printf " $1 |"
        shift
    done
    printf "\n"
}

function printTableTitle() {
    printAsPartOfTable $@

    printf "|"
    for title in $@
    do
        printf " "
        for ((i=0; i<${#title}; i++))
        do
            printf "-"
        done
        printf " |"
    done
    printf "\n"
}

function round() {
    # $1 is expression to round (should be a valid bc expression)
    # $2 is number of decimal figures (optional). Defaults to three if none given
    local df=${2:-3}
    echo $(echo "a=$1; if(a>0) a+=5/10^($df+1) else if (a<0) a-=5/10^($df+1); scale=$df; a/1" | bc -l)
}