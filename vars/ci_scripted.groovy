def call() {
    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts = ""
    }
    node('workstation') {
try {

    stage('Check out code') {
       cleanWs()
        git branch: 'main', url: 'https://github.com/nikhil-thokala/cart'
    }

    sh 'env'

    if(env.BRANCH_NAME != "main"){
    stage('Compile/Build') {
        common.compile()
}

    }

if(env.TAG_NAME ==~ ".*") {
    stage('Test Cases') {
        common.testcases()
}

    }


    stage('Code Quality') {
        common.codequality()
    }
} catch (e) {
    mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'nikhil.rockz12@gmail.com',  subject: "${component} - Pipeline Failed", to: 'nikhil.rockz12@gmail.com', mimeType: 'text/html'
}

    }
}