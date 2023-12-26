#!/usr/bin/env groovy
import org.cli.*

def call() {
    echo "this is demo"
    script {
        linuxcli sam = new linuxcli()
        sam.name("work")
    }
}