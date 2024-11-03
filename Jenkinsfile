pipeline {
    agent any

    stages {
        stage('Git') {
            steps {
                echo 'Recup Code de Git : ';
                git branch : 'NidhalTekaya-G5-5SIM3',
                url :'https://github.com/nidhal66/5SIM-3G5-Gestion-station-ski.git';
            }
        }

        stage('Maven Clean') {
            steps {
                echo 'Nettoyage du Projet : ';
                sh 'mvn clean';
            }
        }

        stage('Maven Compile') {
            steps {
                echo 'Construction du Projet : ';
                sh 'mvn compile';
            }
        }

        stage('SonarQue') {
            steps {
                echo 'Analyse de la Qualité du Code : ';
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Vagrant@2024';
            }
        }

        stage('Maven Package') {
            steps {
                echo 'Création du livrable : ';
                sh 'mvn package -DskipTests';
            }
        }/*
         stage('Maven Deploy') {
            steps {
                echo 'Deploiement du livrable : ';
                sh 'mvn deploy -DskipTests';
            }
        }*/

        stage('Image') {
            steps {
                echo 'Création Image : ';
                sh 'docker build -t nidhal777/gestion-station-ski-image:1.0.0 .';
            }
        }

        stage('Dockerhub') {
            steps {
                echo 'Push Image to dockerhub : ';
                sh 'docker login -u nidhal777 -p Vagrant@2024';
                sh 'docker push nidhal777/gestion-station-ski-image:1.0.0';
            }
        }

        stage('Docker-Compose') {
            steps {
                echo 'Staet Backend + DB : ';
                sh 'docker compose up -d';
            }
        }

    }
}
