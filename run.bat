@echo off
setlocal

echo ================================
echo == Analisador de Xadrez A3 ==
echo ================================

REM Caminhos dos JARs
set JFLEX_JAR=lib\jflex-full-1.9.1.jar
set JCUP_JAR=lib\java-cup-11b.jar

echo ------------------------------
echo == Gerando Lexer com JFlex ==
echo ------------------------------
java -jar %JFLEX_JAR% chess.flex
if errorlevel 1 (
    echo Erro ao gerar o Lexer!
    pause
    exit /b
)

echo -------------------------------
echo == Gerando Parser com JCup ==
echo -------------------------------
java -jar %JCUP_JAR% -parser Parser chess.cup
if errorlevel 1 (
    echo Erro ao gerar o Parser!
    pause
    exit /b
)

echo ----------------------------
echo == Compilando Arquivos .java ==
echo ----------------------------
javac -cp "lib\java-cup-11b-runtime.jar" *.java
if errorlevel 1 (
    echo Erro na compilação Java!
    pause
    exit /b
)

echo -------------------------
echo == Executando Main.java ==
echo -------------------------
java -cp ".;lib\java-cup-11b-runtime.jar" Main chess.txt
echo -------------------------
pause
