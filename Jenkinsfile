pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir("/var/jenkins_home/workspace/healthcache-discussion-board/discussion-board"){
                    sh "ls"
                    sh "mvn clean install"
                    sh "mvn spring-boot:run"
                }
            }
        }
    }
}
