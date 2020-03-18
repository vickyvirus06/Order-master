FROM docker.baymax.jp/flyway
ENV APPJAR=oa-order-management-1.0.jar

COPY build/libs/${APPJAR} /
COPY src/main/resources/db/migration/* /flyway/sql/