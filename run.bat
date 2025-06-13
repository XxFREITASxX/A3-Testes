@echo off
echo ============================
echo     Compilando o projeto
echo ============================

REM Ajuste os caminhos para os jars conforme seu projeto
set JFLEX_JAR=lib\jflex-full-1.9.1.jar
set CUP_JAR=lib\java-cup-11b.jar

echo Gerando lexer com JFlex...
java -jar %JFLEX_JAR% chess.flex
if errorlevel 1 (
    echo Erro ao gerar lexer com JFlex.
    pause
    exit /b 1
)

echo Gerando parser com Java CUP...
java -jar %CUP_JAR% -parser parserName chess.cup
if errorlevel 1 (
    echo Erro ao gerar parser com Java CUP.
    pause
    exit /b 1
)

echo Compilando arquivos Java...
javac *.java
if errorlevel 1 (
    echo Erro ao compilar arquivos Java.
    pause
    exit /b 1
)

echo Executando programa principal com argumento chess.txt...
java Main chess.txt
if errorlevel 1 (
    echo Erro ao executar Main.
    pause
    exit /b 1
)

echo ============================
echo       Processo concluido
echo ============================
pause
