@Library('mobigen-jenkins-library') _

CONFIG = readYaml(file: 'jenkins/deploy.yaml')
DOCKER_IMAGE = [
    'name': '',
    'tag': ''
]

pipeline {
    agent any

    options {
        ansiColor('xterm')
        copyArtifactPermission('*')
    }

    stages {
        stage('Build and push') {
            environment {
                registryConfig = CONFIG.registry
                deployConfig = CONFIG.deploy
            }

            steps {
                script {
                    DOCKER_IMAGE.tag = sh(
                        script: "echo `date '+%Y%m%d'`-`git rev-parse --short=7 HEAD`",
                        returnStdout: true
                    )

                    println("-tag name: ${tag}")
                    DOCKER_IMAGE.name = "${registryConfig.url}/${registryConfig.project}/${config.service}"
                    def app = docker.build(
                        DOCKER_IMAGE.name,
                        "-f ${deployConfig.dockerfile} ${deployConfig.path}"
                    )

                    docker.withRegistry("https://${registryConfig.url}", 'harbor-jenkins') {
                        app.push(DOCKER_IMAGE.tag)
                        app.push('latest')
                    }
                }
            }
        }

        stage('Deploy') {
            environment {
                deployConfig = CONFIG.deploy
            }

            steps {
                println("-deploy namespace: ${deployConfig.k8s.namespace}")
                println("-container image: ${CONTAINER_IMAGE.name}:${CONTAINER_IMAGE.tag}")

                /*
                    현재 플러그인에 각종 보안 이슈가 존재함
                    https://plugins.jenkins.io/kubernetes-cd
                    https://jenkins.iris.tools 도메인에는 아직 해당 플러그인 미설치 상태임
                */
                // kubernetesDeploy(configs: deployConfig.k8s.yaml, kubeconfigId: 'k8s')
            }
        }
    }

    post {}
}
