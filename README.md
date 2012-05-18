hello-spring
============

A Spring MVC application using the MarkLogic Java API.

Set-Up
======

The Java API JAR is not yet available from a Maven repository. (This will change for GA.) Until then, you’ll need to add it to your local repository.

```
mvn install:install-file -Dfile=~/Downloads/client-api-java-1.0-SNAPSHOT/lib/client-api-java-1.0-SNAPSHOT.jar -DgroupId=com.marklogic -DartifactId=client-api-java -Dversion=1.0-20120516 -Dpackaging=jar
```

You’ll have to adjust your paths accordingly. Don’t change the group, artifact, and version, though. These are referenced in the project POM.