#!/bin/bash

openssl genrsa -des3 -out nginx/ssl/server.key -passout pass:carbon 1024 

openssl req -new -key nginx/ssl/server.key -out nginx/ssl/server.csr -passin pass:carbon -subj '//C=BR//ST=Distrito Federal//L=Brasilia//CN=bomdestino.gov.br//O=bomdestino//OU=bomdestino'

openssl x509 -req -days 365 -in nginx/ssl/server.csr -signkey nginx/ssl/server.key -out nginx/ssl/server.crt -passin pass:carbon

