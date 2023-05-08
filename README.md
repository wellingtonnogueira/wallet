# Crypto Wallet Performance

## Technology decision
As I created the project using the SpringBoot Initializr, I got the latest stable versions of SpringBoot and SpringBatch.
This means also that I need to use Java 17.

I decided to use SpringBoot and Spring-Batch because it will process one CSV file at a execution time.

I decided to add HSQLDB support to transform the information required and add it to a table from where I'll get the final result.

All the code is validated using SonarLint to avoid some code-smells or possible bugs.

## Usages
There's a file `application.properties` on src/main/resources with the following properties:
- filename: The name of the file (CSV format) put on the same folder/directory.
- concurrencyLimit: It is possible to change the number of simultaneous threads
- coincapHost: The host for coincap API

Once the information on properties are set up, just run:
```shell
mvn clean package
java -jar target\wallet-0.0.1-SNAPSHOT.jar
```

it also works:
```shell
mvn clean install spring-boot:run
```

## How the Application works
It is all managed by `BatchConfig` class.
The importCryptoJob creates a Job and add the listener and how it will work (on steps)
The reader will read the file and chunk it in parts represented by Crypto object.
The step will execute a job following the rules on processor (CryptoItemProcessor).

The JobCompletionNotificationListener listens when the jobs will start and end and it is possible to add some behavior to the application.
On the begining (start) it will log the time it starts
In the end, it will get all the information saved on HSQLDB and calculates the performance.

The CryptoItemProcessor will, for each chunk, get the history in coincap (based on the filter given) and enhance the information
