#!/usr/bin/env bash
mvn install:install-file \
    -Dfile=postmsg-ump-2.4.jar \
    -DgroupId=com.ump \
    -DartifactId=postmsg-ump \
    -Dversion=2.4 \
    -Dpackaging=jar
