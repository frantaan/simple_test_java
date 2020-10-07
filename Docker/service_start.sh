#!/usr/bin/env bash
echo " ==============================> pulling Chrome and Firefox "
docker pull selenoid/vnc:chrome_85.0
docker pull selenoid/vnc:firefox_47.0
docker pull selenoid/video-recorder:latest-release
echo " starting Selenoid Service ....... "
docker-compose up --force-recreate -d