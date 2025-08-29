@echo off
setlocal

set "SRC=..\src\main\java"
set "BIN=..\bin"

REM
if exist "%BIN%" rmdir /S /Q "%BIN%"
mkdir "%BIN%"

REM
if exist ACTUAL.TXT del ACTUAL.TXT

REM
if exist data\duke.txt del data\duke.txt

REM
dir /s /b "%SRC%\*.java" > sources.txt
if %errorlevel% neq 0 (
  echo No Java sources found under %SRC%
  exit /b 1
)

REM
javac -Xlint:none -d "%BIN%" @sources.txt
if errorlevel 1 (
  echo ********** BUILD FAILURE **********
  del sources.txt
  exit /b 1
)
del sources.txt

REM
set "MAIN="
if exist "%BIN%\Arvee.class" set "MAIN=Arvee"
if exist "%BIN%\duke\Arvee.class" set "MAIN=duke.Arvee"

if not defined MAIN (
  echo Could not find Arvee.class after compile. Do you have a different main class?
  exit /b 1
)

java -cp "%BIN%" %MAIN% < input.txt > ACTUAL.TXT

REM
fc ACTUAL.TXT EXPECTED.TXT

endlocal