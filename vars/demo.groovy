#!/usr/bin/env groovy
import org.cli.*
linuxcli sam = new linuxcli()
def call() {
    echo "this is demo"
    sam.name("work")
}