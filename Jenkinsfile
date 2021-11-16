pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "ls"
                sh "cd /var/jenkins_home/workspace/healthcache-discussion-board/"
                sh "ls"
                sh "cd discussion-board"
                sh "ls"
                sh "mvn clean install"
                sh "mvn spring-boot:run"
            }
        }
    }
}
