echo "Welcome to Sunrise Alerts!"

echo "Please type your name [leave blank if undesired]"
read name

echo "Please type a city or area code for weather [IP by default]"
read location

cd src/main/java
javac SunriseAlert.java

if [[ -n "$name" ]]; then
	echo "Good morning $name"
fi

if [[ -n "$location" ]]; then
	java SunriseAlert $location
else java SunriseAlert
fi