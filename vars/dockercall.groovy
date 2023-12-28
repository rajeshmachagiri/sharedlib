#!/usr/bin/env groovy
import org.cli.docker.DockerDevil

def call() {
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
                                dir('users-api'){

                                    sh 'docker ps'
                                    sh 'docker build -t users-api .'
                                    sh 'docker tag users-api rajeshmachagiri/users-api:latest'
                                    sh 'docker push rajeshmachagiri/users-api:latest'
                                }
                            }

                        }
                    }
                    stage('docker-build2') {
                        steps {
                            container('docker') {
                                dir('todos-api'){
                                    sh 'docker ps'
                                    sh 'docker login -u=rajeshmachagiri -p=8686548640rrR!'
                                    sh 'docker build -t auth-api .'
                                    sh 'docker tag auth-api rajeshmachagiri/auth-api:latest'
                                    sh 'docker push rajeshmachagiri/auth-api:latest'
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