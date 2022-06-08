# back
# устанавливаем самую лёгкую версию JVM
FROM openjdk:8-jdk-alpine

# указываем ярлык. Например, разработчика образа и проч. Необязательный пункт.
LABEL maintainer="apshenichniy@anotheria.net"

# указываем точку монтирования для внешних данных внутри контейнера (как мы помним, это Линукс)
VOLUME /tmp

# внешний порт, по которому наше приложение будет доступно извне
EXPOSE 8099

# указываем, где в нашем приложении лежит джарник
ARG JAR_FILE=target/EchoService-1.0-SNAPSHOT.jar

# добавляем джарник в образ под именем rebounder-chain-backend.jar
ADD ${JAR_FILE} echo-backend.jar

# команда запуска джарника
ENTRYPOINT ["java","-jar","/echo-backend.jar"]

#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]