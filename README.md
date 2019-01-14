To launch demo you need:
1. maven 3, jdk 8, mysql server 8, nodejs 10+, npm, angular cli, properly setting env variables.
2. change datasource connection url to mysql, credentials: name and password in file \demo\src\main\resources\application.properties
3. compile and run, in root of java project execute: mvn clean package -U 
4. launch java -jar ./target/demo.jar
5. go to root of frontend app download all dependencies launch npm install
6. launch frontend app ng server --ssl true