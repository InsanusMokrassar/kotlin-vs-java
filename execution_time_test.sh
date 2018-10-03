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

for i in ${!folders[*]}
do
    folder=${folders[i]}

    assert_success cd $pwdir/$folder/target/

    current_folder_results=()

    for ((n=0; n < $CALCULATION_TIMES; n++))
    do
        current_folder_results[n]="`java -jar ./*jar-with-dependencies.jar | grep "[^[:space:]]*:[0-9]*:[^[:space:]]*"`"
    done

    echo ${current_folder_results[*]}
done

cd $pwdir

