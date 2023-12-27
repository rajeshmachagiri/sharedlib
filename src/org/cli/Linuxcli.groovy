package org.cli

class Linuxcli implements cli {

  Man link = new Man()
  @Override
  def sample (String arg) {
      link.echo_j(arg)
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

