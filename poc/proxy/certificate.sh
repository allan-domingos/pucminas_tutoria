#!/bin/bash

openssl genrsa -des3 -out nginx/ssl/server.key -passout pass:carbon 1024 

openssl req -new -key nginx/ssl/server.key -out nginx/ssl/server.csr -passin pass:carbon 

#-subj '//C=BR//ST=Distrito Federal//L=Brasilia//CN=allandomingos.ddns.net//O=allan de lemos domingos//OU=allan de lemos domingos'

openssl x509 -req -days 365 -in nginx/ssl/server.csr -signkey nginx/ssl/server.key -out nginx/ssl/server.crt -passin pass:carbon

