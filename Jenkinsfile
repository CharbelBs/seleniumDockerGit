pipeline {
    // Master executor should be set to 0
    agent any

    stages {
        stage('Build Jar') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image') {
            steps {
                bat 'docker build -t charbelbsaibess/selenium-docker .'
            }
        }

        stage('Push Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat "docker login --username=charbelbsaibess --password=#Qawserdf1234"
                    bat 'docker push charbelbsaibess/selenium-docker:latest'
                }
            }
        }
    }
}
