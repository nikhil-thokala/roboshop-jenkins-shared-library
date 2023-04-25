def compile() {
    if(app_lang == "nodejs") {
        sh 'npm install'
    }
    if(app_lang == "maven") {
        sh 'mvn package'
    }
}

def testcases() {
    // npm test
    // mvn test
    // python -m unittests
    // go test
    sh 'echo ok'
}

def codequality() {
    sh 'sonar-scanner -Dsonar.host.url=http://172.31.2.104:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component} ${sonar_extra_opts}'
}