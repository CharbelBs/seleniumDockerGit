FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl jq && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/selenium-docker.jar selenium-docker.jar
COPY target/selenium-docker-tests.jar selenium-docker-tests.jar
COPY target/libs libs
COPY testng.xml testng.xml

#ENTRYPOINT ["java", "-cp", "selenium-docker.jar:selenium-docker-tests.jar:libs/*", "-DBROWSER=${BROWSER}", "-DHUB_HOST=${HUB_HOST}", "org.testng.TestNG", "testng.xml"]
#ADD healthcheck.sh healthcheck.sh
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh
ENTRYPOINT sh healthcheck.sh