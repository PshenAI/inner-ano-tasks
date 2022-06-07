#!/bin/bash

#jpdaOpts="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=localhost:8101"

#release_path=<absolute_path_to_release>
#dist=<dist_jar>
#lib=<lib_folder>
#etc=<etc_folder>
#initialMemory=<initial_memory_size>
#maxMemory=<max_memory_size>
#configuremeEnvironment=<configureme_environment>
#user=<restricted_user_name>
#outlog=<nohup_stdout_log_file_name>
#errlog=<nohup_stdout_log_file_name>
#logdir=<log_directory>
#tmpdir=<temporary_files_directory>
#pidfile=<log_files_directory>

if [ -z "$release_path" ]; then
	echo 'release_path environment is not set!'
	exit 1
fi

if [ -z "$dist" ]; then
	echo 'dist environment is not set!'
	exit 1
fi

dist=$release_path/$dist
lib=$release_path/${lib:=lib}
etc=$release_path/${etc:=etc}
initialMemory=${initialMemory:=512M}
maxMemory=${maxMemory:=512M}
configuremeEnvironment=${configuremeEnvironment:=dev}
user=${user:=$USER}
logdir=$release_path/${logdir:=.}
outlog=${outlog:=$logdir/nohup-EchoServer-out.log}
errlog=${errlog:=$logdir/nohup-EchoServer-err.log}
tmpdir=$release_path/${tmpdir:=/tmp}
pidfile=${pidfile:=$tmpdir/EchoServer.pid}

server='org.distributeme.generated.EchoServer'

isServerUp() {
	if [ -f "$pidfile" ]; then
		pid=`cat "$pidfile"`
		alive=`ps --no-heading $pid 2>/dev/null | wc -l`
		if [ $alive == 0 ]; then
			rm -f "$pidfile"
			return 0
		else
			return 1
		fi
	else
		return 0
	fi
}

start() {
	isServerUp
	running=$?
	if [ $running == 0 ]; then
		echo -n 'Starting EchoServer... '
		for file in $(ls $lib); do
			CLASSPATH=$CLASSPATH:$lib/$file
			rmicodebase="$rmicodebase file:$lib/$file"
		done
		CLASSPATH=$etc:$dist:$CLASSPATH
		rmicodebase="file:$etc file:$dist $rmicodebase"
		work_dir=`pwd`
		cd "$release_path"
		sudo -u "$user" /bin/bash -c "nohup java $jpdaOpts -Xmx$maxMemory -Xms$initialMemory -classpath $CLASSPATH -Djava.rmi.server.codebase=\"$rmicodebase\" -Dpidfile=\"$pidfile\" -Dconfigureme.defaultEnvironment=$configuremeEnvironment $server 1>>\"$outlog\" 2>>\"$errlog\" &"
		cd "$work_dir"
		#Wait for possible starting error
		sleep 2
		isServerUp
		running=$?
		if [ $running == 1 ]; then
			echo Done
		else
			echo Failed
		fi
	else
		echo EchoServer is already running
	fi
}

stop() {
	isServerUp
	running=$?
	if [ $running == 0 ]; then
		echo 'EchoServer is not running'
	else
		echo -n 'Stopping EchoServer... '
		pid=`cat "$pidfile"`
		kill $pid
		sleep 2
		isServerUp
		running=$?
		if [ $running == 0 ]; then
			echo Done
			rm -f "$pidfile"
		else
			echo Failed
		fi
	fi
}

status() {
	isServerUp
	running=$?
	if [ $running == 1 ]; then
		echo 'EchoServer is running'
	else
		echo 'EchoServer is not running'
	fi
}

case $1 in
	start)
		start
		;;
	stop)
		stop
		;;
	restart)
		stop
		sleep 1
		start
		;;
	status)
		status
		;;
	*)
	echo $"Usage: $0 {start|stop|restart|status}"
	exit 1
esac

exit 0
