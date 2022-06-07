#This is service definition file, it is sourced from the start script.
#This service definition is created from services.json with entry: ServiceEntry{name='helloworldservice', startClass='org.distributeme.helloworld.generated.HelloWorldServer', rmiPort=9228, jvmOptions=null, profiles=[dev]}.
export SERVICE_NAME=helloworldservice
export TARGET_PID=helloworldservice.pid
export TARGET_CLASS=org.distributeme.helloworld.generated.HelloWorldServer
export RMI_PORT=9228
export JVM_OPTIONS="none"
## profiles for this service are: [dev]
