:: mkdir src\main\java
:: thrift.exe -r -out ../../java -gen java Exception.thrift
:: thrift.exe -r -out ../../java -gen java Test.thrift
:: thrift.exe -r -out ../../java -gen java service.thrift
:: thrift.exe -r -out ../../java -gen java tutorial.thrift
thrift.exe -r -out ../../java -gen java identity.thrift
thrift.exe -r -out ../../java -gen java log.thrift