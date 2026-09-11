#!/bin/bash

BASEDIR="$(dirname "$0")"

DATABASE="${DB_NAME:-jolt}"
DB_USERNAME="${DB_USERNAME:-postgres}"

export PGPASSWORD="${DB_PASSWORD:-}"

psql -U "$DB_USERNAME" -f "$BASEDIR/dropdb.sql" &&

createdb -U "$DB_USERNAME" "$DATABASE" &&

psql -U "$DB_USERNAME" -d "$DATABASE" -f "$BASEDIR/schema.sql" &&

psql -U "$DB_USERNAME" -d "$DATABASE" -f "$BASEDIR/data.sql" &&

psql -U "$DB_USERNAME" -d "$DATABASE" -f "$BASEDIR/user.sql"