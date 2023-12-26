package org.cli

class Linuxcli implements cli {

  @Override
  def sample(String arg) {
      echo "sample24"
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
