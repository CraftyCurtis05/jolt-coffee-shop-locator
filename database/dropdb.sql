-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- Terminate active database connections so the database can be dropped
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'jolt';

DROP DATABASE IF EXISTS jolt;

DROP USER IF EXISTS jolt_owner;

DROP USER IF EXISTS jolt_appuser;