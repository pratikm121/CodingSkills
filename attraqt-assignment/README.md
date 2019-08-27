# Attraqt Cloud Services Assignment

Attraqt provides different services in a managed environment to customers.
To achive this Attraqt has developed an application called "Controller" which
interfaces with the hosting provider, and configures servers that are afterwards
associated with the customer service.

Currently a customer can use three different types of services:

* FAS: the search/marketing services 
* SUGGEST: an add-on service that improves the customers search functionality further
* DATA: ETL services for loading customer data into the other Attraqt services.

The hosting provider provides an API that Attraqt uses to work with the
servers. The controller application provides a REST interface (based on JAX-RS)
so that it can be programmatically monitored and scripted.


## Tasks

### Task 1: Overview

Attraqt's Operations team needs to monitor the existing environment, so that
it can ensure that the Attraqt service is always available to the customer.
Create a simple overview of the existing environment that the Controller
application is currently managing. The entry point in this task is Overview
class.


### Task 2: How's the health?

The hosting provider does not guarantee a reliable operation of the servers at
all times, so the controller application must have a notion of the service
health for each of the services. A service can either be "operational",
"degraded", or "down".  For the different services the following rules are
defined to calculate the health:
   
* DATA: always operational
* SUGGEST: operational if more than two servers are available, degraded if only
one server is available, and down if no servers are available.
* FAS: operational if there are at least 2 servers with a ping below 600 ms and
a load of less than 0.7. Down if no servers are available at all. Degraded if
servers are available.

Complete the service health calculation in the services.

### Task 3: Enter the monkeys

The notion of service health is an indicator for the environment of Controller. Based on this Controller or other applications might build all sorts of strategies.

We introduce the concept of monkeys. A monkey is a process with a dedicated
task. We start by two examples of monkeys. A crash monkey is one that is able to
kill a server at random in the environment. A crash monkey is useful to simulate
realistic unpredictable failures in the environment. A monitor monkey is another
one that is able to monitor the service health in the environment and react to
it if necessary. One fundamental requirement is that in no case should a monkey
process affect the availability and/or functionality of the service. They live
only to help improve the environment and should not turn into bottlenecks or
points of failure.

There are interesting items to achieve in this task:

* Complete the crash monkey.
* Complete the monitor monkey.
* How would you test a monkey?
* How would you run a monkey on a schedule?
* How would you distribute monkeys?

### Task 4: Now, it's your time!

This is not a coding task. It is appreciated if you can prepare a short write-up
on the following items:

* How did you find the tasks? Any feedback is most welcome.
* Is there something that should be considered further regarding the description
of the tasks?
* What parts would you do differently than described? Pretty please, why?
* What part(s) did you like the most? Any specific reason?

It might be a good idea to keep a list of notes open as you are working on the
codeing tasks.

## Tools

* Make sure you have a JDK 1.8+. You may need to define `JAVA_HOME` environment and update `PATH`.
* Make sure you have Apache Maven 3.5+ on your environment and it's included in the path.
* The assignment is provided a standard Maven project that can be set up in your IDE.
* Use any library or tool that you think you need. Just remember to include it in your Task 4 and mention why.

## Hints

* Just note that Controller is a simplified version of a complex reality. It is aimed to be highly concurrent and distributed and failure-tolerant.
* Before starting, it may be worth to take a look around. "World" simulates the provider API. The Controller application main entry point is "ControllerConfig" class. The Controller REST API is available at "Controller" class. And, "ControllerClient" is a REST client to communicate with Controller through HTTP protocol. Feel free to add anything that is missing.
* The Controller starts at http://localhost:7000
* The Controller REST API details is available at http://localhost:7000/application.wadl
* Google should be around!
* Do not forget to enjoy the challenge on the way!

