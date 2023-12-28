import org.cli.*

def call(String arg) {
    Linuxcli sample = new Linuxcli()
    sample.echo(arg)
    sample.withenv(['SAM=245'],"evil",'''ls -la
ls -la''')
    DockerDevil doc = new DockerDevil()
    doc.login()
}