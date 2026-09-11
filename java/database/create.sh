#!/bin/bash

set -euo pipefail

BASEDIR="$(dirname "$0")"

DB_HOST="${DB_HOST:-localhost}"
DATABASE="${DB_NAME:-jolt}"
ADMIN_USERNAME="${DB_ADMIN_USERNAME:-postgres}"
APP_USERNAME="${DB_APP_USERNAME:-jolt_appuser}"
OWNER_USERNAME="${DB_OWNER_USERNAME:-jolt_owner}"

export PGPASSWORD="${DB_ADMIN_PASSWORD:-}"

psql -h "$DB_HOST" -U "$ADMIN_USERNAME" \
  -v ON_ERROR_STOP=1 \
  -f "$BASEDIR/dropdb.sql" &&

psql -h "$DB_HOST" -U "$ADMIN_USERNAME" \
  -v ON_ERROR_STOP=1 \
  -f "$BASEDIR/user.sql" &&

createdb -h "$DB_HOST" -U "$ADMIN_USERNAME" -O "$OWNER_USERNAME" "$DATABASE" &&

psql -h "$DB_HOST" -U "$ADMIN_USERNAME" -d "$DATABASE" \
  -v ON_ERROR_STOP=1 \
  -c "SET ROLE $OWNER_USERNAME;" \
  -f "$BASEDIR/schema.sql" &&

psql -h "$DB_HOST" -U "$ADMIN_USERNAME" -d "$DATABASE" \
  -v ON_ERROR_STOP=1 \
  -c "SET ROLE $OWNER_USERNAME;" \
  -f "$BASEDIR/data.sql" &&

psql -h "$DB_HOST" -U "$ADMIN_USERNAME" -d "$DATABASE" -c \
"GRANT CONNECT ON DATABASE $DATABASE TO $APP_USERNAME;" &&

psql -h "$DB_HOST" -U "$ADMIN_USERNAME" -d "$DATABASE" -c \
"GRANT USAGE ON SCHEMA public TO $APP_USERNAME;" &&

psql -h "$DB_HOST" -U "$ADMIN_USERNAME" -d "$DATABASE" -c \
"GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO $APP_USERNAME;" &&

psql -h "$DB_HOST" -U "$ADMIN_USERNAME" -d "$DATABASE" -c \
"GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO $APP_USERNAME;"
