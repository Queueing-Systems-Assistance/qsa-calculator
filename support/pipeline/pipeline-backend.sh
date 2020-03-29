#!/bin/bash

#### Init
cd "${TRAVIS_BUILD_DIR}" || exit

#### Build
printf "Build QSA Calculator"
./gradlew clean build || exit
echo "[OK]"