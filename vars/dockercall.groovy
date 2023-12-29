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
              - name: aws
                image: dwolla/jenkins-agent-awscli:latest
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
                            container('aws') {

                                dir('users-api'){

                                    script {
                                        sh '''curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install'''
                                        Docker = new DockerDevil()
                                        Docker.login()

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