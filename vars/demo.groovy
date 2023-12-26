#!/usr/bin/env groovy
import org.cli.linuxcli


def call() {
    echo "this is demo"
    linuxcli devil = new linuxcli()
    echo devil
    devil.name("work")

}