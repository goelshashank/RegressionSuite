#!/usr/bin/env bash

java -Dserver.port=8082 -Dtest.out.path=/apps/test/regression/out -cp P2DRegressionSuite-1.0-SNAPSHOT-shaded.jar:log4j-api-2.11.1.jar:log4j-core-2.11.1.jar com.dhisco.regression.services.ManageConfigurations
