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


mvn archetype:generate -DgroupId=com.example -DartifactId=evenodd-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
