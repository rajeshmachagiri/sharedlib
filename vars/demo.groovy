import org.cli.*
import org.cli.docker.DockerDevil

def call(String arg) {
    Linuxcli sample = new Linuxcli()
    sample.echo(arg)
    sample.withenv(['SAM=245'],"evil",'''ls -la
ls -la''')

}