#!/usr/bin/env bash

java -Dserver.port=8082 -cp P2DRegressionSuite-1.0-SNAPSHOT-shaded.jar:P2DR egressionSuite-1.0-SNAPSHOT-tests.jar:log4j-api-2.11.1.jar:log4j-core-2.11.1.jar org.testng.TestNG  -testjar P2DR egressionSuite-1.0-SNAPSHOT-tests.jar   -xmlpathinjar P2DRegressionSuite.xml