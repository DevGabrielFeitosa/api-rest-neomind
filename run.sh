#!/bin/bash

echo "========================================"
echo " Executando API REST Neomind"
echo " JavaEE com Payara Micro Embutido"
echo "========================================"
echo

echo "[1/3] Compilando o projeto..."
./mvnw clean package -DskipTests

if [ $? -ne 0 ]; then
    echo
    echo "ERRO: Falha na compilacao!"
    exit 1
fi

echo
echo "[2/3] Iniciando servidor Payara Micro..."
echo "Servidor sera iniciado em: http://localhost:8080"
echo "API disponivel em: http://localhost:8080/api/hello-world"
echo
echo "Pressione Ctrl+C para parar o servidor"
echo

echo "[3/3] Executando aplicacao..."
./mvnw payara-micro:start
