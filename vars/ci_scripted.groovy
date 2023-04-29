def call() {
    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts = ""
    }
    node('workstation') {
try {

    stage('Check out code') {
        sh 'ls -l'
       cleanWs()
        sh 'ls -l'
        git branch: 'main', url: 'https://github.com/nikhil-thokala/cart'
        sh 'ls -l'
    }


    stage('Compile/Build') {
        sh 'env'
        common.compile()
    }

    stage('Test Cases') {
        common.testcases()
    }


    stage('Code Quality') {
        common.codequality()
    }
} catch (e) {
    mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'nikhil.rockz12@gmail.com',  subject: "${component} - Pipeline Failed", to: 'nikhil.rockz12@gmail.com', mimeType: 'text/html'
}

    }
}