#!/bin/bash
source /home/my-genesis-app/.bashrc
systemctl start postgresql-14
su -c "source /home/my-genesis-app/.bashrc ; genesisInstall" - "my-genesis-app"
echo "genesisInstall done"