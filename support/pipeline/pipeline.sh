#!/bin/bash

#### Init
cd "${TRAVIS_BUILD_DIR}" || exit
git config --global user.email "builds@travis-ci.com" || exit
git config --global user.name "Travis CI" || exit

# Update feature branch name
BRANCH_NAME=${TRAVIS_BRANCH}
if [[ "${BRANCH_NAME}" != "master" ]]; then
  BRANCH_NAME=$(echo "${BRANCH_NAME}" | sed s#/#-#g | sed s/[.]/_/g | sed s#-#_#g | awk '{print $1""}') || exit
fi

GITHUB_ENDPOINT="https://${GITHUB_OWN_TOKEN}@github.com/${TRAVIS_REPO_SLUG}"
RELEASE_VERSION=${PROJECT_MAJOR_VERSION}.${PROJECT_MINOR_VERSION}.${TRAVIS_BUILD_NUMBER}

if [[ "${BRANCH_NAME}" != "master" ]]; then
  RELEASE_VERSION="${RELEASE_VERSION}_${BRANCH_NAME}"
fi

# Print variables
echo "BRANCH_NAME=${BRANCH_NAME}"
echo "GITHUB_ENDPOINT=${GITHUB_ENDPOINT}"
echo "RELEASE_VERSION=${RELEASE_VERSION}"

echo "RELEASE_VERSION=${RELEASE_VERSION}" >> gradle.properties

# Build
printf "Build QSA Calculator"
./gradlew clean build -Prelease.version="${RELEASE_VERSION}" || exit

# Create git tag & push to GitHub
# git tag -a "${RELEASE_VERSION}" -m "${RELEASE_VERSION}" || exit
# git push "${GITHUB_ENDPOINT}" refs/tags/"${RELEASE_VERSION}" || exit

## Docker Login
#echo "${DOCKER_PASSWORD}" | docker login docker.pkg.github.com -u "${DOCKER_USERNAME}" --password-stdin || exit
#
## Docker Build Image
#sudo docker build \
#-f ./support/docker/Dockerfile \
#-t docker.pkg.github.com/queueing-systems-assistance/qsa-calculator/qsa-calculator:"${SELECTED_VERSION}" . || exit
#
#sudo docker push docker.pkg.github.com/queueing-systems-assistance/qsa-calculator/qsa-calculator:"${SELECTED_VERSION}" || exit
