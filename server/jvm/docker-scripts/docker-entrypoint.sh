#!/bin/bash
systemctl start postgresql-14
systemctl enable sshd.service
systemctl start sshd.service
su -c "startServer" - "my-genesis-app"
echo "Logged as my-genesis-app, starting server"
tail -f /dev/null