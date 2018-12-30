FROM fsharp:netcore

MAINTAINER Anthony Khong <anthony.kusumo.khong@gmail.com>

ENV PAKET_URL https://github.com/fsprojects/Paket/releases/download/5.194.0/paket.bootstrapper.exe

RUN mkdir .paket && \
    curl -L $PAKET_URL -o .paket/paket.bootstrapper.exe && \
    mono .paket/paket.bootstrapper.exe

ADD paket.dependencies paket.dependencies

RUN mono .paket/paket.exe install
