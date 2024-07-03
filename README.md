## Introduction:

The `TirPerf` application allows us to keep track of and store the results of performance tests. Until now, the state of the art has been using different types of applications to perform tests on our local applications. However, the problem is that each application produces a report that differs from the others. These reports aren't stored anywhere that we can consult, compare, or extract statistics from them.

This application is created to change this state of affairs. In its first release, it focuses on saving the generated reports to a database (Postgres in our case, though this can be changed).

## How test my code :

### Prerequisites:

- Java 22
- Postgres 16.2 or higher
-  Create a database in your Postgres named "tirperf". Make sure your username and password are the default Postgres credentials, or you can use the Bash script `create_db.sh`.

#### how to run `create_db.sh` :

- Make sure the script is executable by running :
```bash
chmod +x create_db.sh
```
- Run the Script :
```bash
./create_db.sh
```

After this you can run the `DbConnectorTest`  to test the connection to Postgres.
you can find the test on this path `src/test/java/DbTests/DbConnectorTest.java`, if every thing is green you can proceed to the rest of the tests .
