#!/bin/bash

#### Init
cd "${TRAVIS_BUILD_DIR}" || exit

#### Docker Login
echo "$DOCKER_PASSWORD" | docker login docker.pkg.github.com -u "$DOCKER_USERNAME" --password-stdin || exit

#### Docker Build Image
sudo docker build \
  -f ./support/docker/Dockerfile \
  -t docker.pkg.github.com/queueing-systems-assistance/qsa-calculator/qsa-calculator:latest . || exit
sudo docker push docker.pkg.github.com/queueing-systems-assistance/qsa-calculator/qsa-calculator:latest || exit
