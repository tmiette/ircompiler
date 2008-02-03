#/bin/bash

killall -STOP java && ant clean all ; killall -CONT java
