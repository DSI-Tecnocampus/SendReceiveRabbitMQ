# Publisher/Subscriber example with Spring Cloud Stream
This example is composed of three services, two publishers and one subscriber. One publisher just sends messages with
the current date and time (the frequency can be changed with a paramter) and the othe publisher is a REST controller
that sends a message when the method /greetings/{name} is called. Both publishers send their messages through different
channels. The subscriber is subscribed to both channels and logs the received messages.

[RabbitMQ](https://www.rabbitmq.com) is used as the message broker. It handles a queue for each channel
that are created when the *subscribers* are first created. Note that if the *publisher* is created before any *subscriber*
 the messages will be lost until the first *subscriber* is created. However, once a *subscriber* is created
 the queue is persistent. That is, even if there aren't any *subscribers* messages will be stored
 by the broker until they are delivered.
 
## Project Structure
There are three modules two for the publishers called *TimeSender* and *greetingsSender* and another for the subscriber
called *TimeGreetingsReceiver*. These modules are independent application that can be build and run independently.

They can be run from the IDE as we usually do. Note that you can edit the configuration for 
running them and add VM options.

It is also possible and probably advisable to compile them from outside using maven. Go to 
a console window. Navigate to <project_directory>/greetingssender, <project_directory>/timesender or 
<project_directory>/timegreetingsreceiver and 
execute `./mvnw clear package `
. It will create a jar file in a directory called *target*

## Running the example
**Warning:** You'll need to download and install the RabbitMQ message broker in 
your local machine. https://www.rabbitmq.com

Run the services in the following order:

1. Start the RabbitMQ broker
    * `./rabbitmq-server -detached ` to run the broker
    * `./rabbitmqctl status` to see its status
    * `./rabbitmqctl stop` to stop it
    * You can monitor the broker in the following address: `http://localhost:15672/`
2. Start one or more *timegreetingsreceiver* services
    * `java -jar timegreetingsreceiver/target/timegreetingsreceiver-0.0.1-SNAPSHOT.jar` to run the first one with default parameters
    * `java -jar timegreetingsreceiver/target/timegreetingsreceiver-0.0.1-SNAPSHOT.jar` to run a second one with default parameters
    * `java -Dspring.cloud.stream.bindings.receiverGreetingsChannel.group=pepA -Dspring.cloud.stream.bindings.receiverTimeChannel.group=pepA -jar timegreetingsreceiver/target/timegreetingsreceiver-0.0.1-SNAPSHOT.jar
` to run a third one in a different consumer group for both channels
3. Start the *publishers*
    * `java -jar timesender/target/timesender-0.0.1-SNAPSHOT.jar`
    * `java -jar greetingssender/target/greetingssender-0.0.1-SNAPSHOT.jar`
    
## Observe the behaviour
Assuming that the *broker* and the *publishers* are started:

* When the first *subscriber* is started  (as described above) it begins to consume all the messages
* When the second *subscriber* is started (as described above) it consumes approximately half the messages.
The other half is consumed by the first *subscriber*. This is because both of them are in the same *consumer
group*
* When the third *subscriber* is started (as described above) the other *subscribers* don't change their
behaviour while the third one gets all the messages
* When at least a *subscriber* has been created and then all are stopped the messages are enqueued by the 
broker. If a *subscriber* is run again it consumes all the past non consumed messages.
* If the broker is stopped while it had messages stored in the queue they aren't lost. When it
is run again the *receivers* receive those messages. Obviously the messages sent by the *publisher*
while the *broker* was down are lost






