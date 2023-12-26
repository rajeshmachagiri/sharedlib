#!/usr/bin/env groovy
import org.cli.linuxcli


def call() {
    echo "this is demo"
    def devil = new org.cli.linuxcli()
    devil.name()

}