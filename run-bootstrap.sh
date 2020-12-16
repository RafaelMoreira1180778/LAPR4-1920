#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.app.bootstrap/target/base.app.bootstrap-1.3.0-SNAPSHOT.jar:base.app.bootstrap/target/dependency/*;

#REM call the java VM, e.g,

rm ~/lapr4.mv.db
rm ~/lapr4.trace.db

java -cp $BASE_CP eapli.base.app.bootstrap.BaseBootstrap