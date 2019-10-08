FROM java:8-jdk
WORKDIR /srv

ENV TZ=Asia/Almaty

ADD build/libs/forfun*.jar /srv/
ADD build/libs/application.yml /srv/application.yml
ADD build/libs/forfun.sh /srv/

CMD ["sh", "forfun.sh"]
