#!/bin/bash

./HelloWorld.sh -n 10
./QuickSort.sh -n 10 -a 100000
./ContactsBook.sh -n 10 --jvm-args -Xmx2048m --jvm-args -Xint -a 50 -a 10000
./ImageHandling.sh -n 3 -a "`pwd`/test_sprite_images/"
./NetworkEmulation.sh -n 2 -a 10000

