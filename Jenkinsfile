pipeline {
    agent any

    tools {
        jdk "$JDK_VERSION"
        maven "$MAVEN_VERSION"
    }

    environment {
        MAVEN_VERSION = 'mvn3.5.2'
        JDK_VERSION = 'jdk1.8'
        PROJECT_ROOT = "."
        SNAPSHOT_DESIGNATOR = "-SNAPSHOT"
        REPO_URL = "aleperaltabazas/tp-dds-2018"
    }

    stages {
        stage("checkout") {
            steps {
                checkout scm
            }
        }

        stage("compile") {
            steps {
                dir(PROJECT_ROOT) {
                    withMaven(maven: MAVEN_VERSION) {
                        sh "mvn --batch-mode clean compile"
                    }
                }
            }
        }


        stage("test") {
            steps {
                withMaven(maven: MAVEN_VERSION) {
                    sh "mvn --batch-mode clean test"
                }
            }
        }

        stage("Pull Request Analysis") {
            when {
                branch "PR-*"
            }

            steps {
                dir(PROJECT_ROOT) {
                    withCredentials([[$class  : 'StringBinding', credentialsId: 'd6b611d7-6115-4e55-8aa6-ca557717777b',
                                      variable: 'GITHUB_ACCESS_TOKEN']]) {
                        withSonarQubeEnv('Sonar AS-Comm') {
                            sh "mvn sonar:sonar -Dsonar.analysis.mode=preview -Dsonar.github.repository=${REPO_URL} -Dsonar.github.pullRequest=${env.CHANGE_ID} -Dsonar.github.oauth=${GITHUB_ACCESS_TOKEN}"
                        }
                    }
                }
            }
        }

        stage("SonarQube Release Analysis") {
            when {
                branch "PR-*"
            }

            steps {
                dir(PROJECT_ROOT) {
                    withSonarQubeEnv('Sonar AS-Comm') {
                        sh "mvn sonar:sonar"
                    }
                }
            }
        }
    }
}
