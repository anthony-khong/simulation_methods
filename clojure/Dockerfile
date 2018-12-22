FROM ubuntu:18.04

MAINTAINER Anthony Khong <anthony.kusumo.khong@gmail.com>

RUN apt-get update && apt-get install -y curl rlwrap build-essential

RUN apt-get install -y default-jre default-jdk

RUN curl https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein \
	-o /usr/local/bin/lein

RUN chmod a+x /usr/local/bin/lein && lein

ADD project.clj project.clj

RUN lein install

RUN lein repl

