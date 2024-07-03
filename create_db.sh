#!/bin/bash

# Database credentials "by default, you can update it if you want, just keep the DB name
DB_USER="postgres"
DB_PASS="postgres"
DB_HOST="localhost"
DB_PORT="5432"
DB_NAME="tirperf"


export PGPASSWORD=$DB_PASS

psql -U $DB_USER -h $DB_HOST -p $DB_PORT -c "CREATE DATABASE $DB_NAME;"

unset PGPASSWORD

echo "Database '$DB_NAME' created successfully."
