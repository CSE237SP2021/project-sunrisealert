# Sunrise Alerts
A weather news app that will fill you in on all of the greatest headlines that have occurred recently. Will help you stay in shape and have something interesting to talk about in forced Zoom meetings. Also configurable to show your local weather so you know if it's even worth going outside that day.

### Iteration 1
Completed user stories:
  - As a user, I want to receive top news stories for the day
  - As a user, I want to view the weather at my current location

User stories for next iteration:
  - As a user, I want Sunshine Alerts to tell me good morning PERSONALLY
  - As a user, I want Subshine Alerts to compile the links for the top news stories for me
 
Everything we've implemented currently works.

### Iteration 2
Completed user stories:
  - As a user, I want Sunshine Alerts to compile the links for the top news stories for me
  - As a user, I want Sunshine Alerts to open up an interesting article for me
  - As a user, I want to conveniently run and compile Sunrise Alerts in one command

User stories for next iteration:
  - As a user, I want to customize the amount of articles that appear
  - As a user, I want to blacklist articles from certain urls
  - As a user, I want the option to automatically open links instead of running an additional script 

Everything we've implemented currently works

Running Instructions: 
1. type 'bash SunriseAlerts.sh' into your terminal
1. change directories into src/main/java
1. to get top headlines and the weather (based on IP) run 'java SunriseAlert' 
    1. to get weather for other locations type 'java SunriseAlert [zip code/city]'
1. to checkout the urls (and paste them in your browser) of articles, open 'links.txt'


