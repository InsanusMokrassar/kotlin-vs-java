#!/bin/bash

./HelloWorld.sh -n 100
./QuickSort.sh -n 100 -a 100000
./ContactsBook.sh -n 100 -jvm-args -Xmx4096m -a "50 1000"
./ImageHandling.sh -n 10 -a "`pwd`/test_sprite_images/"

