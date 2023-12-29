package org.cli.docker
import org.cli.Steps
import org.cli.Tool
import org.cli.registry.Registry


class DockerDevil implements DockerInterface {
    Steps link = new Steps()
    String tag = "demo"
    Registry reg
    DockerDevil(Registry reg = new Registry()){
        this.reg = reg
    }
    @Override
    def build() {
        String command= "docker build -t ${tag}:devil ."
        link.sh(command)
    }

    def login() {
        println(reg.store)
        String command = """
set +x
docker login --username AWS --password "${reg.store}" ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com"""
        link.script("docker login",command)
    }

    def push() {
        String command = "docker push ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com/${tag}:devil"
        link.sh{command}
    }


}