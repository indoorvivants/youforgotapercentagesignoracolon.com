#!/bin/sh

curl -fLo cs "https://github.com/coursier/coursier/releases/download/v2.1.0-M5/coursier" && chmod +x cs 
./cs launch ammonite:2.5.1 -- site.sc --destination $(pwd)/_site
