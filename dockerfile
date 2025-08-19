#syntax=docker/dockerfile:1

FROM clojure:openjdk-17-tools-deps

WORKDIR /src/app/

COPY ./src /src/app/src/

RUN clojure -P

CMD clojure -M -m app.core
