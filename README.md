aad-webservice
==============

##################################################################################################
##################################################################################################
###############  								   ###############
###############			Schoolware Web Services API			   ###############
###############									   ###############
##################################################################################################
##################################################################################################


################################# Steps to setup WS ##############################################

#1 Get the latest code from: https://github.com/sambodhi/aad-webservice

#2 Install eclipse plugin egit: http://download.eclipse.org/egit/updates

#4 Install eclipse maven plugin: http://m2eclipse.sonatype.org/sites/m2e/
	or you can search on Eclipse Marketplace: maven (Install: Maven Integration for Eclipse WTP (Incubation)

#5 Once you have project imported in eclipse with maven plugin, right click on project maven -> Enable dependency management

#6 Install command line maven tool from http://maven.apache.org/download.cgi#Installation

Run WS code (command line)
	# mvn compile (Compiles the code)
	# mvn tomcat6:run (Run tomcat, deploys the application to tomcat running on localhost:9999)
	# mvn clean verify (Runs all unit and integration tests)
	# mvn package (packages the project as aad-ws.war in target folder)
