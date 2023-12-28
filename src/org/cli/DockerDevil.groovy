package org.cli



class DockerDevil implements Doc {
Steps link = new Steps()
    @Override
    def login() {
        link.withenv(['DOCKER_REGISTRY_USER=rajeshmachagiri','DOCKER_REGISTRY_PASS=dckr_pat_EhSNYMctetlqFc-p2uuY_VrSQHI'],"login",'echo "$DOCKER_REGISTRY_PASS" | docker login $DOCKER_REGISTRY --username $DOCKER_REGISTRY_USER --password-stdin')
    }

    @Override
    def auth() {
        return null
    }

    @Override
    def push() {
        return null
    }
}