#/bin/bash

killall -STOP eclipse && ant clean all ; killall -CONT eclipse
