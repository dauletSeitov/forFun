#!/bin/bash
rm -rf build/
rm -rf build.lib/
set -x

mkdir build.lib
./gradlew clean

./gradlew build