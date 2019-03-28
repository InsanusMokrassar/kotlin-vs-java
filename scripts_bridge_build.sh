#!/bin/bash

source BaseScripts/base.sh
source BaseScripts/build_time_test.sh
source BaseScripts/common_test.sh

./gradlew clean

folders=(`extractFolders $@`)
CALCULATION_TIMES=`extractCalculationTimes $@`

echo "Sum of calculations for each test: $CALCULATION_TIMES"
echo "Chosen folders for tests:"

for i in ${folders[*]}
do
    printf "    %s\n" $i
done

export CALCULATION_TIMES

results=(`calculateBuildTimeResults ${folders[*]}`)

export TEST_FOLDERS=${folders}
export RESULTS=${results}

printTableOfResults
echo
echo Arithmetic average
echo
printApproximateResultsTable
