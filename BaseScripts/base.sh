#!/usr/bin/env bash

MARKDOWN_EMPTY_TABLE_CELL="%s" #<div></div>

TRUE="true"
FALSE="false"

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

function checkFlag() {
    flag="$1"

    shift

    isFlag="$FALSE"

    while [ -n "$1" ]
    do
        case "$1" in
            "$flag") isFlag="$TRUE"; break ;;
        esac
        shift
    done

    echo "$flag"
}

function printTableTitle() {
    isLeft="$FALSE"
    isRight="$FALSE"

    titles=($@)

    alignSymbol=0

    while [ -n "$1" ]
    do
        case "$1" in
            -l) isLeft="$TRUE"; break ;;
            -r) isRight="$TRUE"; break ;;
            -c) isLeft="$TRUE"; isRight="$TRUE"; break ;;
        esac
        let alignSymbol++
        shift
    done

    titles=(${titles[*]:0:$alignSymbol} ${titles[*]:`expr $alignSymbol + 1`:${#titles[*]}})

    printAsPartOfTable ${titles[*]}

    printf "|"
    for title in ${titles[*]}
    do
        let titleLength--
        if [ "$isLeft" == "$TRUE" ]
        then
            printf ":"
        else
            printf " "
        fi
        for ((i=0; i<${#title}; i++))
        do
            printf "-"
        done
        if [ "$isRight" == "$TRUE" ]
        then
            printf ":"
        else
            printf " "
        fi
        printf "|"
    done
    printf "\n"
}

function round() {
    # $1 is expression to round (should be a valid bc expression)
    # $2 is number of decimal figures (optional). Defaults to three if none given
    local df=${2:-3}
    echo $(echo "a=$1; if(a>0) a+=5/10^($df+1) else if (a<0) a-=5/10^($df+1); scale=$df; a/1" | bc -l)
}

function average() {
    count=1
    value=$(round "$1" 15)
    shift
    while [ -n "$1" ]
    do
        value=$(round "($value + $1)" 15)
        let count++
        shift
    done

    echo `round "$value / $count" 15`
}

function unique() {
    printf '%s\n' "$@" | awk -v RS='[[:space:]]+' '!a[$0]++{printf "%s%s", $0, RT}'
}

function extractTestName() {
    while [ -n "$1" ]
    do
        echo $1 | grep -o "^[^:]*"
        shift
    done
}

function extractTime() {
    while [ -n "$1" ]
    do
        echo $(echo $1 | grep -o "[:][^:]*[:]") | grep -o "[0-9]*"
        shift
    done
}

function extractPointName() {
    while [ -n "$1" ]
    do
        echo $1 | grep -o "[^:]*$"
        shift
    done
}

# Return index of first element in array or -1 if is absent
# usage: indexOfFirst $control_element ${array[*]}
function indexOfFirst() {
    control=$1
    shift
    i=0
    result=-1
    while [ -n "$1" ]
    do
        if [ "$control" == "$1" ]
        then
            result=${i}
            break
        fi
        let i++
        shift
    done
    echo $result
}