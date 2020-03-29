#!/bin/bash

#### Init
echo "Project path [${TRAVIS_BUILD_DIR}]"
echo "Build version [${TRAVIS_BUILD_NUMBER}]"
cd "${TRAVIS_BUILD_DIR}" || exit
