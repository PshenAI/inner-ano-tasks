#This is service definition file, it is sourced from the start script.
#This service definition is created from services.json with entry: ServiceEntry{name='echoservice1', startClass='org.distributeme.generated.EchoServer', rmiPort=9227, jvmOptions=-DdimeRegistrationNameProvider=org.distributeme.core.failing.Failover -XX:+IgnoreUnrecognizedVMOptions, profiles=[dev]}.
export SERVICE_NAME=echoservice1
export TARGET_PID=echoservice1.pid
export TARGET_CLASS=org.distributeme.generated.EchoServer
export RMI_PORT=9227
export JVM_OPTIONS="-DdimeRegistrationNameProvider=org.distributeme.core.failing.Failover -XX:+IgnoreUnrecognizedVMOptions"
## profiles for this service are: [dev]
