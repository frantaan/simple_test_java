#!/usr/bin/env bash
echo " ==============================> pulling Chrome and Firefox "
docker pull selenoid/vnc:chrome_85.0
docker pull selenoid/vnc:firefox_47.0
docker pull selenoid/video-recorder:latest-release
echo " starting Selenoid Service ....... "
docker-compose up --force-recreate -d
cd ..
mvn test -Pweb_execution -Dsuite=dataprovider -Dtestng.dtd.http=true -Dio.netty.tryReflectionSetAccessible=true -Dcom.couchbase.client.core.deps.io.netty.noUnsafe=true
allure serve