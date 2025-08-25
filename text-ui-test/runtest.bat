@echo off
setlocal

set "SRC=..\src\main\java"
set "BIN=..\bin"

REM --- clean bin to avoid stale classes ---
if exist "%BIN%" rmdir /S /Q "%BIN%"
mkdir "%BIN%"

REM --- clean old output ---
if exist ACTUAL.TXT del ACTUAL.TXT

REM --- collect all sources recursively (handles subpackages) ---
dir /s /b "%SRC%\*.java" > sources.txt
if %errorlevel% neq 0 (
  echo No Java sources found under %SRC%
  exit /b 1
)

REM --- compile everything into bin ---
javac -Xlint:none -d "%BIN%" @sources.txt
if errorlevel 1 (
  echo ********** BUILD FAILURE **********
  del sources.txt
  exit /b 1
)
del sources.txt

REM --- detect where Arvee.class ended up and run it ---
set "MAIN="
if exist "%BIN%\Arvee.class" set "MAIN=Arvee"
if exist "%BIN%\duke\Arvee.class" set "MAIN=duke.Arvee"

if not defined MAIN (
  echo Could not find Arvee.class after compile. Do you have a different main class?
  exit /b 1
)

java -cp "%BIN%" %MAIN% < input.txt > ACTUAL.TXT

REM --- compare output ---
fc ACTUAL.TXT EXPECTED.TXT

endlocal