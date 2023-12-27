package org.cli

class Linuxcli implements cli {

  Steps link = new Steps()
  @Override
  def echo (String arg) {
      link.echo_j(arg)
  }

  @Override
  def sh(String arg) {
      link.sh(arg)
  }
  def error(String arg) {
      link.error(arg)
  }

  def withenv(int arg){
      link.withenv(arg)
  }

}

