#!/usr/bin/env groovy
import org.cli.*
linuxcli sam = new linuxcli()
def call() {
    echo "this is demo"
    linuxcli sam = new linuxcli()
    sam.name("work")

}