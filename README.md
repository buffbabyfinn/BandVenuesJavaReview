# _Band Tracker_

#### _Epicodus Java Database Exercise, 03.04.16_

#### By _**Megan Fayer**_

## Description

_With this page, you can add bands, venues, assign venues to bands, and see all venues bands have played, update, and delete bands._

## Setup/Installation Requirements

* _Clone this repository_
* _Install the [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Java SRE](http://www.java.com/en/)._
* _[Install gradle](http://codetutr.com/2013/03/23/how-to-install-gradle/)_
* _Open a terminal and run Postgres_
```
$ postgres
```
* _Open a new tab in terminal and create the `band_venues` database:_
```
$ psql
$ CREATE DATABASE band_venues;
$ psql band_venues < band_venues.sql
```
* _Navigate back to the directory where this repository has been cloned and run gradle:_
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
