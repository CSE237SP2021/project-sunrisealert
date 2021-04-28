# For Student Graders!!
Please pull code from the development branch, not master.

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
  - ~As a user, I want to blacklist articles from certain urls~ Not supported through the API
  - As a user, I want the option to automatically open links instead of running an additional script 

Everything we've implemented currently works

### Iteration 3
Completed user stories:
 - As a user, I want to customize the amount of articles that appear
 - As a user, I want the option to automatically open links instead of running an additional script
 - As a user, I want Sunshine Alerts to have configurable settings so it opens and runs quickly

Everything we have implemented works (within reason). Windows Subsystem for Linux (WSL) does not have native support for opening programs found on the main windows system. But if run through an IDE, even Windows users can have articles opened in their favorite browser. 


### Running Instructions: 
1. type 'bash SunriseAlerts.sh' into your terminal
1. to automatically open the urls in your default web browser type the number of the article when prompted (not for WSL)
1. to view the urls, open src/main/java/links.txt
1. to change your name, location, or the number of articles displayed change the corresponding fields in SunriseAlerts.config

