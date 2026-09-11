#!/bin/bash

ENV_FILE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/.env"

if [ ! -f "$ENV_FILE" ]; then
  echo "Missing .env file. Copy .env.example to .env and add your local values."
  return 1 2>/dev/null || exit 1
fi

set -a
source "$ENV_FILE"
set +a

echo "Jolt environment loaded."
