#!/bin/bash
while true
do
	ps x -o rss,vsz,command | awk 'NR>1 {$1=int($1/1024)"M"; $2=int($2/1024)"M";}{ print ;}' | grep $1 | grep -v "watch_mem" | grep -v "grep" >> mem.log;
	sleep 1;
done