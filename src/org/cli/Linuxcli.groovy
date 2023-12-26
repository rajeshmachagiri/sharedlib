package org.cli

class Linuxcli implements cli {

  @Override
  def sample (String arg) {
      echo arg
  }

  @Override
  def sh(String arg) {
      sh "$arg"
  }
  def errorcall(String arg) {
      error "$arg"
  }

  def warn(String arg){
      warnError("$arg") {
          // some block
      }
  }

}

