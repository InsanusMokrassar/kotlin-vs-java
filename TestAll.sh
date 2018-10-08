#!/bin/bash

./HelloWorld.sh -n 10
./QuickSort.sh -n 10 -a 100000
./ContactsBook.sh -n 10 --jvm-args -Xmx2048m -a 50 -a 1000
./ImageHandling.sh -n 3 -a "`pwd`/test_sprite_images/"

