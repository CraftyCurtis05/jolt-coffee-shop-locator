-- **************************************************************
-- This script destroys the Jolt database
-- **************************************************************

-- Terminate active database connections so the database can be dropped
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'jolt';

DROP DATABASE IF EXISTS jolt;