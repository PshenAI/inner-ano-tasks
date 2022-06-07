#!/usr/bin/env bash
source environment.sh
echo current profile: $DISTRIBUTEME_PROFILE
profile_found="false"
SERVICES_dev="echoservice "

if [ "dev" = "$DISTRIBUTEME_PROFILE" ]; then
  profile_found="true"
  SERVICES=$SERVICES_dev
fi

if [ $profile_found = "false" ]; then
  echo profile $DISTRIBUTEME_PROFILE not found!
else
  echo stoping services $SERVICES
fi
for i in $SERVICES; do
	echo stoping service $i
	cd $i
	bin/stop_service.sh
	cd ..
done
