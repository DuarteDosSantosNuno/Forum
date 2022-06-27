FROM tomcat:8.0

WORKDIR /opt
RUN curl -O https://download.java.net/java/GA/jdk14/076bab302c7b4508975440c56f6cc26a/36/GPL/openjdk-14_linux-x64_bin.tar.gz
RUN tar xvf openjdk-14_linux-x64_bin.tar.gz
ENV JAVA_HOME /opt/jdk-14
RUN export JAVA_HOME
ENV PATH="/opt/jdk-14/bin:${PATH}"
RUN export PATH

COPY Forum.war /usr/local/tomcat/webapps

EXPOSE 8080
CMD ["catalina.sh", "run"]

