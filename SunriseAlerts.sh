source SunriseAlerts.config
echo "Welcome to Sunrise Alerts!"

cd src/main/java
javac SunriseAlert.java

if [[ -n "$user" ]]; then
    echo "Good morning $user"
fi

if [[ $results -lt 101 ]]; then
    if [[ -n "$location" ]]; then
        java SunriseAlert $results $location
    else java SunriseAlert $results
    fi
else java SunriseAlert 20
fi
