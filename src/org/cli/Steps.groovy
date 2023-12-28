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

def script(String label , String command ){
    sh label: label, script: command
}

def withenv(List<String> arg, String label, String command){
    withEnv(arg) {
        echo $SAM
        script(label , command)
    }
}



