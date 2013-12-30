set LAST_PATH=..
set classpath=%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;%LAST_PATH%\lib;
java -jar %LAST_PATH%\${project.build.finalName}.${project.packaging}