# Ubuntu 20.04.2 LTS with OpenJDK version 11.0.12
FROM adoptopenjdk/openjdk11

# copy Jar into image
COPY target/bookmyhike.jar /app/bookmyhike.jar

EXPOSE 8080

# Start the Exchange Rate Service
CMD java -jar /app/bookmyhike.jar