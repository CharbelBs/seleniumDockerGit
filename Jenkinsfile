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

        /*stage('Push Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat "docker login --username=charbelbsaibess --password=#Qawserdf1234"
                    bat 'docker push charbelbsaibess/selenium-docker:latest'
                }
            }
        }

        stage('Pull Latest Image') {
                    steps {
                        retry(3) {  // Retry up to 3 times in case of failure
                            bat "docker pull charbelbsaibess/selenium-docker"
                        }
                    }
                }
        */
        stage('Start Selenium Grid and run Tests') {
                   steps {
                        bat 'docker-compose up -d --scale chrome=10'
                    }
         }
    }

    post {
            always {
                script {
                    echo "Archiving test results..."
                    archiveArtifacts artifacts: "docker-output/**", onlyIfSuccessful: false
                }

                echo "Shutting down Selenium Grid..."
                bat "docker-compose down --remove-orphans"
                bat 'docker volume prune -f'
            }

            failure {
                echo "Build failed! Check logs for details."
            }
        }
}
