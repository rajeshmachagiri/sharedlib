#!/usr/bin/env groovy

def call() {
    echo "this is demo"
    linuxcli sam = new linuxcli()
    sam.echo("work")

}