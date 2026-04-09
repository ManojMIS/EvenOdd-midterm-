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

mvn archetype:generate -DgroupId=com.example -DartifactId=evenodd-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
