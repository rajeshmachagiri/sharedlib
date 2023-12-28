#!/usr/bin/env groovy
import org.cli.docker.DockerDevil

call() {
    pipeline {
        agent {
            kubernetes {
                yaml '''
            apiVersion: v1
            kind: Pod
            spec:
              containers:
              - name: maven
                image: maven:alpine
                command:
                - cat
                tty: true
              - name: docker
                image: docker:19.03.1-dind
                securityContext:
                  privileged: true
                env:
                  - name: DOCKER_TLS_CERTDIR
                    value: ""
            '''
            }
        }
        stages {
            stage('init') {
                steps {
                    script {
                        demo("let it go")

                    }
                }
            }
            stage('Build-Jar-file') {
                parallel {
                    stage('docker build') {
                        steps {
                            container('docker') {

                                script {

                                    DockerDevil dante = new DockerDevil()
                                    dante.login()


                                }
                            }
                        }
                    }
                }
            }
        }

    }
}