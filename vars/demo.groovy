#!/usr/bin/env groovy
import org.cli.linuxcli

def call() {
    echo "this is demo"
    linuxcli sam = new linuxcli()
    sam.echo("work")

}