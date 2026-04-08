@echo off
setlocal enabledelayedexpansion

REM Force UTF-8 code page on Windows console (CMD)
chcp 65001 >nul

REM Taskr launcher script (Windows)
set "SCRIPT_DIR=%~dp0"
set "JAR_PATH=%SCRIPT_DIR%target\taskr.jar"

if not exist "%JAR_PATH%" (
  echo Error: JAR not found at: "%JAR_PATH%"
  echo Build it with: mvn clean package
  exit /b 1
)

REM Force Java stdout/stderr encoding to UTF-8
java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -jar "%JAR_PATH%" %*