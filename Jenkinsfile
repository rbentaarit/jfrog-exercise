// Scripted Pipeline with stages
 pipeline {
 environment {
         //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
         IMAGE = 'Jfrog_exercise'
         VERSION = semver().getVersion()
     }
  stage('Source') { // Get code
    // get code from our Git repository
    git 'https://github.com/rbentaarit/jfrog-exercise'
  }
  stage('build & unit test') { // Compile and do unit testing
    // get Gradle HOME value
    def gradleHome = tool 'gradle5'
    // run Gradle to execute compile and unit testing
    sh "'${gradleHome}/bin/gradle' clean build test"
  }
  stage ('git tag'){
  sh "'echo git tag '"
  }
  stage ('Xray'){
    sh "'echo Xray '"
    }

  stage ('docker build publish'){
        when {
                  branch 'master'
              }
                sh """
                 docker build -t ${IMAGE} .
                 docker tag ${IMAGE} ${IMAGE}:${VERSION}
                """

        // Step 1: Obtain an Artifactiry instance, configured in Manage Jenkins --> Configure System:
        def server = Artifactory.server '<ArtifactoryServerID>'

        // Step 2: Create an Artifactory Docker instance:
        def rtDocker = Artifactory.docker server: server

        // Step 3: Push the image to Artifactory.
        // Make sure that <artifactoryDockerRegistry> is configured to reference <targetRepo> Artifactory repository. In case it references a different repository, your build will fail with "Could not find manifest.json in Artifactory..." following the push.
        def buildInfo = rtDocker.push '<artifactoryDockerRegistry>/${IMAGE}:${VERSION}', '<targetRepo>'

        // Step 4: Publish the build-info to Artifactory:
        server.publishBuildInfo buildInfo
    }
}

