package org.cli.docker
import org.cli.Steps
import org.cli.Tool
import org.cli.registry.Registry


class DockerDevil implements DockerInterface {
    Steps link = new Steps()
    Tool tool = new Tool()
    String tag = tool.determineRepoName()
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
        reg.token()
        String command = "echo ${reg.store} | docker login --username AWS --password-stdin ${reg.accountid}.dkr.ecr.region.amazonaws.com"
        link.sh("echo \"login into ECR\"")
        link.sh(command)
    }

    def push() {
        String command = "docker push ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com/${tag}:devil"
        link.sh{command}
    }


}