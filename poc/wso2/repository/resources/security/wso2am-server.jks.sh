#!/bin/bash

keytool -genkey -alias wso2am-server -keyalg RSA -keysize 2048 -keystore wso2am-server-keystore.jks -validity 365 -storetype JKS -ext "SAN=DNS:wso2am-server,IP:10.1.0.3"  -dname "CN=wso2am-server, OU=allan de lemos domingos, O=allan de lemos domingos, L=Brasília, S=Distrito Federal, C=BR" -storepass carbon -keypass carbon

keytool -export -alias wso2am-server -keystore wso2am-server-keystore.jks -storetype JKS -file wso2am-server.pem -storepass carbon 

keytool -import -alias wso2am-server -file wso2am-server.pem -keystore wso2am-server-truststore.jks -storetype JKS -storepass carbon -keypass carbon -trustcacerts -noprompt

keytool -import -alias ibge -file ibge.cer -keystore wso2am-server-truststore.jks -storetype JKS -storepass carbon -keypass carbon -trustcacerts -noprompt
