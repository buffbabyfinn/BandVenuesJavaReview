# _Band Tracker_

#### _Epicodus Java Database Exercise, 03.04.16_

#### By _**Megan Fayer**_

## Description

_With this page, you can add bands, venues, assign venues to bands, and see all venues bands have played, update, and delete bands._

## Setup/Installation Requirements

* _Clone this repository_
* _Install the [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Java SRE](http://www.java.com/en/)._
* _[Install gradle](http://codetutr.com/2013/03/23/how-to-install-gradle/)_
* _Open Postgres 9.3_
* _Connect to database 9.3_

* _Open psql shell as an administrator and import the `band_venues` sequel file to your specified folder path. Mine was:_
```
$ CREATE database band_venues;
$\c band_venues;
$ \i 'C:/Users/Megan/Documents/Programming/java/band-venues/band_venues.sql';
```
* _Make sure that the user and password in the DB.java file and the DatabaseRule.java file match the username and password settings for your postgres, for example in DB.java:_

```
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/band_venues", null, null);
```

* _Go back to your command prompt and run gradle:_
```
$ gradle run
```
* _Open localhost:4567 in a browser._

## Known Bugs

_No current known bugs._

## Support and contact details

_To contact, leave a comment on Github._

## Technologies Used

* _Java_
* _JUnit_
* _FluentLenium_
* _Gradle_
* _Spark_
* _SQL_

### License

*MIT License*

Copyright (c) 2016 **_Megan Fayer_**
