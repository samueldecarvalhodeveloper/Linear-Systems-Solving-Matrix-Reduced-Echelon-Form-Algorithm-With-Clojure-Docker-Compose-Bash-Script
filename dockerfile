#syntax=docker/dockerfile:1

FROM clojure:latest

WORKDIR /src/app/

COPY ./deps.edn /src/app/
COPY ./src /src/app/src/

RUN clojure -P

CMD clojure -M -m app.core
