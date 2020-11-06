#!/bin/bash

# Get Argument PROFILE
PROFILE=$1
echo "PROFILE=${PROFILE}"

cd ../
#mvn clean package
cp target/api-demo* devops/
cd devops/



###############################################################################
# If was set argument($1) PROFILE=production
# example: ./up.sh production
# then execute docker compose setting file(-f) docker-compose-production.yml
# --build = always build image
###############################################################################
if [ "$PROFILE" == "production" ] || [ "$PROFILE" == "PRODUCTION" ]; then
    echo "Executing docker-compose production"
    docker-compose -f docker-compose-production.yml up --build
    exit 0
fi

###############################################################################
# IF was not set argument($1) or argument($!) was not equals "production"
# then execute docker compose default without compose file
###############################################################################
echo "Executing docker-compose default"
docker-compose -f docker-compose.yml up --build
