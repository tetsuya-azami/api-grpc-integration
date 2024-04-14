FROM arm64v8/mysql:8.3.0

COPY ./conf.d/my.cnf /etc/mysql/conf.d/my.cnf

USER mysql

CMD ["mysqld"]