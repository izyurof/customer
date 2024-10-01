#!/bin/bash

CERT_DIR="./certs"

if [ ! -d "$CERT_DIR" ]; then
  mkdir -p "$CERT_DIR"
fi

openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout "$CERT_DIR/nginx-selfsigned.key" -out "$CERT_DIR/nginx-selfsigned.crt" -subj "/CN=localhost"

openssl dhparam -out "$CERT_DIR/dhparam.pem" 2048

echo "The SSL certificate and key were successfully generated in $CERT_DIR"
