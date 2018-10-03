#!/bin/bash

source ./BaseScripts/base.sh
source ./BaseScripts/common_test.sh

folders=(`extractFolders $@`)
CALCULATION_TIMES=`extractCalculationTimes $@`

echo "Sum of calculations for each test: $CALCULATION_TIMES"
echo "Chosen folders for tests:"

for i in ${folders[*]}
do
    printf "    %s\n" $i
done

export CALCULATION_TIMES

mvn clean package

pwdir=`pwd`

points_results=()

for i in ${!folders[*]}
do
    folder=${folders[i]}

    assert_success cd $pwdir/$folder/target/

    current_folder_results=()

    for ((n=0; n < $CALCULATION_TIMES; n++))
    do
        current_folder_results[n]="`java -jar ./*jar-with-dependencies.jar | grep "[^[:space:]]*:[0-9]*:[^[:space:]]*"`"
    done

    points_results+=(${current_folder_results[*]})
done

test_names=()
times=()
pointnames=()

for i in ${!points_results[*]}
do
    point=${points_results[i]}
    test_names[i]="`echo $point | grep -o "^[^:]*"`"
    times[i]="`echo $(echo $point | grep -o "[:][^:]*[:]") | grep -o "[0-9]*"`"
    point_names[i]="`echo $point | grep -o "[^:]*$"`"
    echo $point
done



cd $pwdir

