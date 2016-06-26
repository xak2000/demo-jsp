# Demo of using Spring MVC for search reports (using JSP as view engine)

## Requirements

Java 8+

## Download

Clone this repository using:
`git clone https://github.com/xak2000/demo-jsp.git`

## Application profiles

By default application runs in `DEV` (development) profile. In this mode it will use
embedded `H2` database filled from default `data.sql` file in project `resources` folder.

This profile is useful for development and test purposes.

You can run application in `PROD` (production) profile in several ways.

- Pass command line argument `--spring.profiles.active=prod`
- Set environment variable `SPRING_PROFILES_ACTIVE=prod`
- Place `application.yml` or `application.properties` file inside WAR file's directory with
profile selection setting.

## Run in DEV mode

Enter into `demo-jsp` folder, then run:

```
./gradlew bootRun
```

*This way application will run in embedded tomcat server using embedded `H2` database.*

Open URL `http://localhost:8080` in the browser.

## Build and run in DEV mode

Enter into `demo-jsp` folder, then run:

```
./gradlew build
java -jar ./build/libs/demo-jsp-0.0.1-SNAPSHOT.war
```

Open URL `http://localhost:8080` in the browser.

*This way application will run in embedded tomcat server using embedded `H2` database.*

## Build and run in PROD mode

Enter into `demo-jsp` folder, then run:

```
./gradlew build
```

Create database structure on MySQL server using `mysql/schema.sql` and `mysql/data.sql` scripts.

Copy `build/libs/demo-jsp-0.0.1-SNAPSHOT.war` to new folder.

Copy `conf/application.yml` to the same folder. This is sample of production config file.

Modify new copy of `application.yml` file to reflect your database settings. Especially
`url`, `username` and `password` properties.

The desired directory sctructure should be:
```
|-your_new_folder
  |-demo-jsp-0.0.1-SNAPSHOT.war
  |-application.yml
```

Run `java -jar ./demo-jsp-0.0.1-SNAPSHOT.war` from this folder.

Open URL `http://localhost` in the browser.

You can also change http server's `port` in `application.yml` file if port 80 is
already used on your computer.

*This way application will run in embedded tomcat server using external `MySQL` database.
This is a primary way to run this application in production mode, which is more
convinient, than run it on external tomcat instance.*

## Deploy on external Tomcat instance

You can deploy the resulting WAR file to the Tomcat server. By default it will run
in DEV mode when deployed in external container. To run it in PROD mode you need to
set config properties as environment variables. You can do this using Tomcat's context
configuration file for deployed instance.

Example:
`$CATALINA_BASE/conf/[enginename]/[hostname]/demo-jsp-0.0.1-SNAPSHOT.xml`
```
<Context docBase="/path/to/your/war/your.war">
    <Parameter name="spring.profiles.active" value="prod" />
    <Parameter name="spring.datasource.url" value="jdbc:mysql://mysql_host:3306/db_name?useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&serverTimezone=Europe/Kiev" />
    <Parameter name="spring.datasource.username" value="mysql_user" />
    <Parameter name="spring.datasource.password" value="mysql_password" />
    <Parameter name="server.port" value="9090" />
</Context>
```
