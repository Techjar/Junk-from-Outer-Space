#!/bin/sh
EXEDIR=${0%/*}
java -Xms256M -Xmx1024M -Djava.library.path=${EXEDIR}/dist/lib/native/linux -jar ${EXEDIR}/dist/Junk_from_Outer_Space.jar
