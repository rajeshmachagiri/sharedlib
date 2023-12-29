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

def scriptstd(String label , String command ){
    sh label: label, script: command , returnStdout=true
}

def withenv(List<String> arg, String label, String command){
    withEnv(arg) {
        script(label , command)
    }
}

def withenvstd(List<String> arg, String label, String command){
    withEnv(arg) {
        scriptstd(label , command)
    }
}






