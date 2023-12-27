package org.cli


// echo the string on the screen/output
def echo(String arg) {
    echo arg
}

def error(String arg){
    error arg
}

def sh(String command){
    sh command
}

def withenv(List arg){
    withEnv(arg[0] = arg[1]) {
        echo ${arg[0]}
    }
}

