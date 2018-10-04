#!/bin/bash

source ./BaseScripts/base.sh
source ./BaseScripts/common_test.sh

folders=(`extractFolders $@`)
testArgs=(`extractTestArgs $@`)
CALCULATION_TIMES=`extractCalculationTimes $@`

echo "Sum of calculations for each test: $CALCULATION_TIMES"
echo "Chosen folders for tests:"

for i in ${folders[*]}
do
    printf "    %s\n" $i
done

export CALCULATION_TIMES

#mvn clean package

pwdir=`pwd`

points_results=()

for i in ${!folders[*]}
do
    folder=${folders[i]}

    assert_success cd "$pwdir/$folder/target/"

    current_folder_results=()

    for ((n=0; n < $CALCULATION_TIMES; n++))
    do
        output="`java -jar ./*jar-with-dependencies.jar ${testArgs[*]}`"
        current_folder_results[n]="`echo $output | grep "[^[:space:]]*:[0-9]*:[^[:space:]]*"`"
    done

    points_results+=(${current_folder_results[*]})
done

test_names=()
times=()
point_names=()

for i in ${!points_results[*]}
do
    point="${points_results[i]}"
    test_names[i]="`extractTestName "$point"`"
    times[i]="`extractTime $point`"
    point_names[i]="`extractPointName $point`"
done

unique_point_names=(`unique ${point_names[*]}`)
unique_test_names=(`unique ${test_names[*]}`)

printTableTitle "Point" "n" "${unique_test_names[*]}"

average_executions=()

pointsAverages=()

for point_index in ${!unique_point_names[*]}
do
    point_name=${unique_point_names[point_index]}
    content=()
    for i in ${!point_names[*]}
    do
        if [ "${point_names[i]}" == "$point_name" ]
        then
            content+=("${points_results[i]}")
        fi
    done

    averages=()
    for ((i=0; i<$CALCULATION_TIMES; i++))
    do
        if [ "$i" -eq "0" ]
        then
            point_row=("$point_name" "$i")
        else
            point_row=("$MARKDOWN_EMPTY_TABLE_CELL" "$i")
        fi
        for testNameIndex in ${!unique_test_names[*]}
        do
            testName="${unique_test_names[testNameIndex]}"
            index=`indexOfFirst "$testName" $(extractTestName ${content[*]})`
            pointTime="$MARKDOWN_EMPTY_TABLE_CELL"
            if [ "$index" -ne "-1" ]
            then
                let index=$index+$i
                content_item=${content[index]}
                if [ "`extractTestName $content_item`" == "$testName" ]
                then
                    pointTime="`extractTime $content_item`"
                fi
            fi
            point_row+=("$pointTime")
            if [ "${averages[$testNameIndex]}" == "" ]
            then
                averages[$testNameIndex]="$pointTime"
            else
                averages[$testNameIndex]="`average ${averages["$testNameIndex"]} $pointTime`"
            fi
        done

        printAsPartOfTable ${point_row[*]}
    done

    pointAverages+=(${averages[*]})
done

printf "\nAverage results:\n\n"

printTableTitle "Point" "${unique_test_names[*]}"

for point_index in ${!unique_point_names[*]}
do
    point_name=${unique_point_names[point_index]}
    point_row=("$point_name")
    let offset=$point_index*${#unique_test_names[*]}

    for test_index in ${!unique_test_names[*]}
    do
        index=0
        let index=$offset+$test_index

        point_row+=("${pointAverages[$index]}")
    done

    printAsPartOfTable ${point_row[*]}
done


cd $pwdir

