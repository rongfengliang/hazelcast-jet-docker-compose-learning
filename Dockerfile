FROM dalongrong/alpine-oraclejdk8:8.131.11-full-arthas-tz
WORKDIR /app
COPY app/hazelcast-jet-app.jar /app
CMD [ "java","-jar","/app/hazelcast-jet-app.jar" ]