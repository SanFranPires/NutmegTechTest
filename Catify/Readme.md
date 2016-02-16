Introduction
============

This project was developed within the scope of a technical test.

Used Technologies
-----------------

The following technologies and libraries were used in the project:
* JDK 1.8
* Eclipse: IDE
* Maven: project build and dependency manager
* Apache HTTP Client: to connect to the HTTP API
* JSON-Simple: to parse JSON responses
* Commons-IO: to copy streams
* TestNG: as the testing library

Packaging
---------

You can use the maven project configuration to build a self-contained jar that can be distributed as a single file (the default packaging will create a fat JAR with all dependencies within).

Running the packaging command with create the file **Catify-1.0.0-jar-with-dependencies.jar**. Feel free to rename this filename if you wish.

Usage 
-----

**java -jar Catify-1.0.0-jar-with-dependencies.jar [ file | categories | fact ]

Author
======

This code was developed by Francisco Pires.