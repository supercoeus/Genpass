#!/bin/bash


cd "$(dirname "$0")"
sed -i "s/strongpassword/`uuidgen`/g" Genpass.sh Genpass_Android/Genpass/src/com/feix/genpass/MainActivity.java Genpass_iOS/Genpass_iOS/ViewController.swift
