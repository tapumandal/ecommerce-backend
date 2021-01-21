FROM openjdk:8
ADD target/ecommerce-backend.jar ecommerce-backend.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "ecommerce-backend.jar"]

## MySql pull then run with credentials
# docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=tapudb -e MYSQL_USER=user-t4pu -e MYSQL_PASSWORD=password -d mysql:5.6
#
# docker run --net=my-network --name mysql-server -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=ims-db -e MYSQL_USER=ims-user -e MYSQL_PASSWORD
#=password -d mysql:5.6

## phpmyadmin pull then run with mysql server linked    
# docker run --name myphpmyadmin -d --link mysql-server:db -p 8001:80 phpmyadmin/phpmyadmin

## Spring app Build
# docker build . -t ecommerce-backend
## App Run and link with mysql server
# docker run -p 8080:8080 --name ecommerce-backend  --link mysql-server:mysql -d ecommerce-backend

#RUN AND BUILD TOGETHER WITHN SPECIFIC NETWORKS
#docker build -t ecommerce-backend . && docker run --net=my-network -p 8080:8080 --name ecommerce-backend  --link mysql-server:mysql -d
#ecommerce-backend

#docker build -t ecommerce-backend . && docker run -p 80:8081 --name ecommerce-backend --link mysql-server:mysql tapumadnal/ecommerce-backend