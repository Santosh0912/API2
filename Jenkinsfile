node{
  stage('SCM Checkout'){
  git 'https://github.com/Santosh0912/API'
  }

  stage('Compile-Package'){
  def mvnHome = tool name: 'Maven', type: 'maven' 
  sh "${mvnHome}/bin/mvn package"
  }
}
