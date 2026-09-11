-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- Terminate active database connections so the database can be dropped
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'final_capstone';

DROP DATABASE IF EXISTS final_capstone;

DROP USER IF EXISTS final_capstone_owner;

DROP USER IF EXISTS final_capstone_appuser;