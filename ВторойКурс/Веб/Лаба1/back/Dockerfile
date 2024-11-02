
FROM openjdk:17-jdk AS java_build

RUN mkdir -p /opt/user/myapp

COPY ./back/lib/fastCGI.jar /opt/user/myapp/lib/fastCGI.jar

COPY ./back/src/ /opt/user/myapp/src/

WORKDIR /opt/user/myapp


RUN javac -cp "/opt/user/myapp/src/:/opt/user/myapp/lib/fastCGI.jar" -d /opt/user/myapp/classes  /opt/user/myapp/src/Main.java

RUN echo "Main-Class: Main" > manifest.txt
RUN echo "Class-Path: lib/fastCGI.jar" >> manifest.txt

RUN jar -cvfm myapp.jar manifest.txt -C classes/ . -C lib/ fastCGI.jar


FROM node:14 AS frontend_build

WORKDIR /opt/user/myapp/frontend/

COPY ./back/static/ /opt/user/myapp/frontend/

RUN npm install --only=dev


RUN npm run build


FROM openjdk:17-jdk-slim

COPY --from=java_build /opt/user/myapp/myapp.jar /opt/user/myapp/myapp.jar
COPY --from=frontend_build /opt/user/myapp/frontend/dist /opt/user/myapp/frontend/dist
COPY --from=frontend_build /opt/user/myapp/frontend/ /opt/user/myapp/frontend/

COPY --from=java_build /opt/user/myapp/lib/fastCGI.jar /opt/user/myapp/lib/fastCGI.jar


EXPOSE 9000


CMD ["java", "-DFCGI_PORT=9000", "-jar", "/opt/user/myapp/myapp.jar"]
