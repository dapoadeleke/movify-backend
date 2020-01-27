node {
    stage('Pull Repository') {
        git 'https://github.com/adelekeoladapo/movify-backend.git'
    }
    stage('Compile and Package') {
        sh 'mvn package'
    }
}
