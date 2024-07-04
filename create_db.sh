#!/bin/bash

# Database credentials "by default, you can update it if you want, just keep the DB name
DB_USER="postgres"
DB_PASS="postgres"
DB_HOST="localhost"
DB_PORT="5432"
DB_NAME="tirperf"


export PGPASSWORD=$DB_PASS

psql -U $DB_USER -h $DB_HOST -p $DB_PORT -c "CREATE DATABASE $DB_NAME;"

# Create the applicatif table
psql -U $DB_USER -h $DB_HOST -p $DB_PORT -d $DB_NAME -c "
CREATE TABLE IF NOT EXISTS applicatif (
    id SERIAL PRIMARY KEY,
    intitule VARCHAR(100),
    version VARCHAR(50),
    fonctions VARCHAR(200)
);"

# Create the scenario table
psql -U $DB_USER -h $DB_HOST -p $DB_PORT -d $DB_NAME -c "
CREATE TABLE IF NOT EXISTS scenario (
    id SERIAL PRIMARY KEY,
    description VARCHAR(500),
    applicatif_id SMALLINT,
    scenario_fonctions VARCHAR(200)
    );"
# Create the tirPerf table

psql -U $DB_USER -h $DB_HOST -p $DB_PORT -d $DB_NAME -c "
CREATE TABLE IF NOT EXISTS tirperf (
    id SERIAL PRIMARY KEY,
    tir_perf_date DATE,
    scenario_id SMALLINT
    );"

unset PGPASSWORD

echo "Database '$DB_NAME' created successfully."
