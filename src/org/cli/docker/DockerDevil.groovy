package org.cli.docker
import org.cli.Steps
import org.cli.Tool
import org.cli.registry.Registry


class DockerDevil implements DockerInterface {
    Steps link = new Steps()
    String tag = "ownproject"
    Registry reg
    DockerDevil(Registry reg = new Registry()){
        this.reg = reg
    }
    @Override
    def build() {
        String command= "docker build -t ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com/${tag}:devil ."
        link.shell(command)
    }

    def login() {
        String commandstd = """docker login --username AWS --password ${reg.store} ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com""".stripIndent().toString()
        String command = """set +x
echo ${reg.store} | docker login --username AWS --password-stdin ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com""".toString()
        link.script("docker login",command)
    }

    def push() {
        String command = "docker push ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com/${tag}:devil"
        link.shell(command)
    }


}