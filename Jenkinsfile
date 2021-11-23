pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir("discussion-board"){
                    sh "ls"
                    sh "mvn clean install"
                    sh "mvn spring-boot:run"
                }
            }
        }
    }
}
