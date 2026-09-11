-- ********************************************************************************
-- This script creates the Jolt database roles if they do not already exist
-- ********************************************************************************

-- Database owner
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT FROM pg_catalog.pg_roles
        WHERE rolname = 'jolt_owner'
    ) THEN
        CREATE ROLE jolt_owner NOLOGIN;
    END IF;
END
$$;

-- Application user
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT FROM pg_catalog.pg_roles
        WHERE rolname = 'jolt_appuser'
    ) THEN
        CREATE ROLE jolt_appuser LOGIN;
    END IF;
END
$$;
