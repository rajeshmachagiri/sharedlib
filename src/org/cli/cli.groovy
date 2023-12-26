package org.cli

interface cli{
    String sample(String arg)

    def warn(String arg)

    def error(String arg)

    def sh(String arg)

}

