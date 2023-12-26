#!/usr/bin/env groovy
import org.cli.*

def call() {
    echo "this is demo"
    linuxcli sam = new linuxcli()
    sam.name("work")

}