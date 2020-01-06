# movify-backend

Movify-backend is a simple Web API that allows users fetch a list of movies, sort, filter and also view additional information of each movie.

It's a Maven project and it was developed using Jooby (jooby.org), a scalable, fast and modular micro web framework for Java.

<b>Installation</b>

<i>Requirements:</i><br>
Install JDK 8+ <br>
Install Maven 3+ <br>
Install MySQL database

<i>Database setup:</i> <br>
The database dump (movify.sql) can be found in the root diirectory of the project. Create a database locally and import movify.sql thereafter.


Kindly clone the project, navigate to the root directory on your terminal and run <b><i>mvn install</i></b> to download all the projet's dependencies. 

Navigate to the <b><i>/conf/application.conf</i></b> file and point the application to your database by editing the <b><i>db.url, db.user and db.password</i></b> properties.

Start the server by runing <b><i>mvn jooby:run</i></b> command from the terminal.

The API documentation can be found <i>http://134.209.80.23:8090/swagger</i> or <i>http://134.209.80.23:8090/redoc</i>

A sample of the application has also been hosted here: <i>http://134.209.80.23:8080</i>
