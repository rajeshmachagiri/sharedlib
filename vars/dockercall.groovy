#!/usr/bin/env groovy
import org.cli.Steps
import org.cli.docker.DockerDevil
import org.cli.registry.Registry

import javax.swing.event.DocumentEvent

def call() {

    DockerDevil Docker
    Registry reg
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

                                dir('users-api'){

                                    script {
                                        Steps link = new Steps()
                                        String store1 = link.withenvstd(["AWS_ACCESS_KEY_ID=xyz","AWS_SECRET_ACCESS_KEY=xyz","AWS_DEFAULT_REGION=xyz"],"demo","ls")
                                        echo store1
                                    }
                                }
                            }

                        }
                    }
                    stage('docker-build2') {
                        steps {
                            container('docker') {
                                dir('todos-api'){
                                   sh "ls"
                                }
                            }
                        }
                    }

                }
            }
            stage('kubectl'){
                steps {
                    sh "curl -LO https://dl.k8s.io/release/v1.28.4/bin/linux/amd64/kubectl"
                    sh "chmod +x kubectl"
                    sh "./kubectl get pods"
                    sh "./kubectl apply -f ./k8s/users-api"
                    sh "./kubectl apply -f ./k8s/todos-api"
                }
            }
        }

    }
}