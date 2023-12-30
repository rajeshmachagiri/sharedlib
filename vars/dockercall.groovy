#!/usr/bin/env groovy
import org.cli.Steps
import org.cli.docker.DockerDevil
import org.cli.registry.Registry

import javax.swing.event.DocumentEvent

def call() {
DockerDevil Docker
Registry registry


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
              - name: ubuntu
                image: ubuntu
                securityContext:
                  privileged: true
                command:
                - cat
                tty: true
            '''
            }
        }
        stages {
            stage('init') {
                steps {
                    container('ubuntu') {
                        script {
                            demo("let it go")
//                            sh '''apt update -y
//apt install curl -y
//apt install unzip -y
//curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
//unzip awscliv2.zip
//./aws/install'''
//                            registry = new Registry()
//                            registry.token()
//                            Docker = new DockerDevil(registry)
                            String demo = libraryResource(sample.yaml)
                            echo demo

                        }
                    }

                }
            }
//            stage('Build-Jar-file') {
//                parallel {
//                    stage('docker build') {
//                        steps {
//                            container('docker') {
//
//                                dir('users-api'){
//
//                                    script {
//                                        Docker.login()
//                                        Docker.build()
//                                        Docker.push()
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                    stage('docker-build2') {
//                        steps {
//                            container('docker') {
//                                dir('todos-api'){
//                                    sh "ls"
//                                    script {
//
//                                        Docker.login()
//                                        Docker.build()
//                                        Docker.push()
//
//                                    }
//                                }
//                            }
//                        }
//                    }

//                }
//            }
//            stage('kubectl'){
//                steps {
//                    sh "curl -LO https://dl.k8s.io/release/v1.28.4/bin/linux/amd64/kubectl"
//                    sh "chmod +x kubectl"
//                    sh "./kubectl get pods"
//                    sh "./kubectl apply -f ./k8s/users-api"
//                    sh "./kubectl apply -f ./k8s/todos-api"
//                }
//            }
        }

    }
}