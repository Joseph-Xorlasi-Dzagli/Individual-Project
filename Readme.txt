Read Me
Airline.java: A class for all Airlines, creates airline objects using rows from the airlines.csv file.
Airport.java: A class for all Airports, creates airport objects using rows from the airports.csv file.
Route.java: A class for all routes creates route objects using rows from the routes.csv file.
# I created classes for the airports, routes and Airplanes to enable me access their attributes without reading through the database every time.
The constructors for each of those classes take a row in the spreadsheet and convert it into a class, identifying and initialising each of its required attributes.
I created a method in my main class to receive path search request from the user and store it into a text file. Request()
I created another class to read the input text file and feed the parameters into the search algorithm. ProcessRequest()
I created a function to implement my heuristic for the search algorithm. Heurstic()
I also created functions to create the class of the source airport and the destination airport within my main method. findAirportClass() and findDAirportClass()
I created a class to find all airports reachable from a current state aiport. findAllDestinationAirports().
A class to print results and provide final output text file. printResults().
The findRoute() function returns the route from a given source airport to a destination airport.
