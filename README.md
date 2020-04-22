# Asda Delivery Slot Finder
After getting frustrated trying to find free delivery slots, I thought I'll automate the process with Selenium WebDriver.
Currently, the application just prints out available slots to the console but can be extended to send notifications.

The application needs a remote web driver server with chrome capabilities. You can launch one using docker as follows:

```
docker run -d -p 4444:4444 selenium/standalone-chrome
```

Build the jar:

```
./mvnw clean package
```

Run the jar file as follows (make sure to set the host and port of the `remoteDriverAddress` url):

```
java -DremoteDriverAddress="http://<host>:<port>/wd/hub" -DasdaUsername="<username>" -DasdaPassword="<password>" -jar target/asda-delivery-slot-finder.jar
```





