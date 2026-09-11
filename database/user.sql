-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER jolt_owner;

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO jolt_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO jolt_owner;

CREATE USER jolt_appuser;

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO jolt_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO jolt_appuser;