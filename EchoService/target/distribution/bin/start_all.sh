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
  echo starting services $SERVICES
fi

for i in $SERVICES; do
	echo starting service $i
	cd $i
	bin/start_service.sh
	cd ..
done
