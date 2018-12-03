#/bin/bash

mvn heroku:deploy -Dmaven.test.skip=true
