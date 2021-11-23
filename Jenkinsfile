pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir("/root/.jenkins/workspace/healthcache-discussion-board/discussion-board"){
                    sh "ls"
                    sh "mvn clean install"
                    sh "mvn spring-boot:run"
                }
            }
        }
    }
}
