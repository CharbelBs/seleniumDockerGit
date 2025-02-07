pipeline {
    // Master executor should be set to 0
    agent any

    stages {
        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image') {
            steps {
                sh 'docker build -t charbelbsaibess/selenium-docker .'
            }
        }

        stage('Push Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: '#Qawserdf1234', usernameVariable: 'charbelbsaibess')]) {
                    sh 'docker login --username=${user} --password=${pass}'
                    sh 'docker push charbelbsaibess/selenium-docker:latest'
                }
            }
        }
    }
}
