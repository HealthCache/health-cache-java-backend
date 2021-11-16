pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "ls"
                sh "cd discussion-board"
                sh "ls"
                sh "mvn clean install"
                sh "mvn spring-boot:run"
            }
        }
    }
}
