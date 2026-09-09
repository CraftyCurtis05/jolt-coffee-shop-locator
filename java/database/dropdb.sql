-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'best_buds_dispensary_locator';

DROP DATABASE best_buds_dispensary_locator;

DROP USER best_buds_owner;
DROP USER best_buds_appuser;
