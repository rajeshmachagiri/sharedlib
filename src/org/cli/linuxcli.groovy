package org.cli

class linuxcli implements cli {

  def linuxcli(){

    }

  def name(String arg) {
   echo "$arg"
  }

  @Override
  def sh(String arg) {
      sh "$arg"
  }
  def error(String arg) {
      error "$arg"
  }

  def warn(String arg){
      warnError("$arg") {
          // some block
      }
  }


}