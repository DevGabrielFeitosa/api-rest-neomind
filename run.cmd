@echo off
echo ========================================
echo  Executando API REST Neomind
echo  JavaEE com Payara Micro Embutido
echo ========================================
echo.

REM Configurar JAVA_HOME automaticamente
if "%JAVA_HOME%"=="" (
    echo [INFO] Configurando JAVA_HOME automaticamente...
    set "JAVA_HOME=C:\Program Files\Java\jdk-17"
    echo [INFO] JAVA_HOME definido como: %JAVA_HOME%
)

echo [1/3] Compilando o projeto...
call mvnw.cmd clean package -DskipTests

if %ERRORLEVEL% neq 0 (
    echo.
    echo ERRO: Falha na compilacao!
    pause
    exit /b 1
)

echo.
echo [2/3] Iniciando servidor Payara Micro...
echo Servidor sera iniciado em: http://localhost:8080
echo API disponivel em: http://localhost:8080/api/hello-world
echo.
echo Pressione Ctrl+C para parar o servidor
echo.

echo [3/3] Executando aplicacao...
set JAVA_HOME=C:\Program Files\Java\jdk-17
java -jar %USERPROFILE%\.m2\repository\fish\payara\extras\payara-micro\6.2024.2\payara-micro-6.2024.2.jar --deploy target\api.war --contextroot /api --port 8080

pause
