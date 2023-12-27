package org.cli


// echo the string on the screen/output
def echo_j(String arg) {
    echo arg
}

def error(String arg){
    error arg
}

def sh(String command){
    sh command
}

def withenv(List<String> arg){
    withEnv(arg) {
        echo "$SAM"
    }
}

