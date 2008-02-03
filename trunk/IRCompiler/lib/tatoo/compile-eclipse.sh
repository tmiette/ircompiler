#/bin/bash

(killall -q -STOP eclipse || killall -q -STOP java || echo non to suspend) && ant "$@" clean all ; (killall -q -CONT eclipse || killall -q -CONT java || echo non to resume)
