import org.cli.*


def call(String arg) {
    Linuxcli sample = new Linuxcli()
    sample.echo(arg)
    sample.withenv(['SAM=245'])

}