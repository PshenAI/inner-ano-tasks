#This is service definition file, it is sourced from the start script.
#This service definition is created from services.json with entry: ServiceEntry{name='echoservice', startClass='org.distributeme.generated.EchoServer', rmiPort=9228, jvmOptions=null, profiles=[dev]}.
export SERVICE_NAME=echoservice
export TARGET_PID=echoservice.pid
export TARGET_CLASS=org.distributeme.generated.EchoServer
export RMI_PORT=9228
export JVM_OPTIONS="none"
## profiles for this service are: [dev]
