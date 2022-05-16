FROM eclipse-temurin:11

RUN apt-get update

RUN apt-get install -y inotify-tools wait-for-it

ENV HOME=/app

RUN mkdir -p $HOME

WORKDIR $HOME