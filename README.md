PIPELINE SCRIPT
pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git branch:'main',url:'https://github.com/ManojMIS/EvenOdd-midterm-.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t evenodd-app .'
    }
}
    }
}

mvn archetype:generate -DgroupId=com.example -DartifactId=evenodd-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
git config --global user.name "NewUsername"
git config --global user.email "newemail@gmail.com"
git init git add . git commit -m git add remote origin git branch -M main git push -u origin main

pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "itsmekalai/conference-app:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/itsmekalai15/kalai.git'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    echo "Building Conference Webpage Image..."
                    bat "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                        bat "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }

        stage('K8s Deployment') {
            steps {
                script {
                    echo "Deploying Conference App to Kubernetes..."
                    // Loads the secret file with ID 'itsmekalai' into the KUBE_CFG variable
                    withCredentials([file(credentialsId: 'itsmekalai', variable: 'KUBE_CFG')]) {
                        // Passes the securely loaded file to the kubectl command
                        bat 'kubectl apply -f deployment.yaml --kubeconfig="%KUBE_CFG%"'
                    }
                }
            }
        }
    }
    
    post {
        success {
            echo "CI/CD Pipeline Complete. Access via NodePort 30007"
        }
    }
}

mvn archetype:generate -DgroupId=com.example -DartifactId=evenodd-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
