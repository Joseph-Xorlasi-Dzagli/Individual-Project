package  com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;



public class Main {

    static String Directory = "C:\\Users\\joseph.dzagli\\OneDrive - Ashesi University\\Canvas\\Intermediate Computer Programming\\";

    static String Request() {
        Scanner SourceCity = new Scanner(System.in);
        System.out.println("Enter Source City: ");
        String S_City  = SourceCity.nextLine();
        Scanner SourceCountry = new Scanner(System.in);
        System.out.println("Enter Source Country: ");
        String S_Country = SourceCountry.nextLine();

        Scanner DestinationCity = new Scanner(System.in);
        System.out.println("Enter Destination City: ");
        String D_City = DestinationCity.nextLine();
        Scanner DestinationCountry = new Scanner(System.in);
        System.out.println("Enter Destination Country: ");
        String D_Country = DestinationCountry.nextLine();


        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Directory + SourceCity+ "_"+DestinationCity  +  ".txt"));
            bw.write(S_City + "," + S_Country);
            bw.write("\n" + D_City + "," + D_Country);
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return Directory + SourceCity +"-" + DestinationCity + ".txt";

    }

    static String[] ProcessRequest(String filepath) {
        String[] Data = new String[4];
        String[] temp;
        int i = 0;
        String s;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(filepath));
            while ((s = br.readLine()) != null && i<3) {
                temp = s.split(",");
                Data[i] = temp[0];
                i++;
                Data[i] = temp[1];
                i++;
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Data; //     Data = [Source City, Source Country, Destination City, Destination Country]
    }


//    Code assistance from https://www.w3resource.com/java-exercises/basic/java-basic-exercise-36.php
    static double Heuristic(Airport SourceAirport, Airport DestinationAirport) {
        double lat1 = Math.toRadians(SourceAirport.getLatitude());
        double lon1 = Math.toRadians(SourceAirport.getLongitude());
        double lat2 = Math.toRadians(DestinationAirport.getLatitude());
        double lon2 = Math.toRadians(DestinationAirport.getLongitude());

        double earthRadius = 6371.01; //Kilometers
        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
    }


    static Airport findAirportClass(String City, String Country) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "Airports.csv"));
            BufferedReader rbr = new BufferedReader(
                    new FileReader(Directory + "routes.csv"));

            String s;
            while ((s = br.readLine()) != null) {
                String[] attributes = s.split(",");

                if (attributes[2].contains(City) && attributes[3].contains(Country)) {

                    return new Airport(s);

                }
            }
        } catch (NullPointerException | IOException ne) {
            ne.printStackTrace();
            return null;
        }

        return null;
    }


    static Airport findDAirportClass(String City, String Country) {
         Airport Destination = null;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "Airports.csv"));

            String s;
            String t;
            String[] rattributes;
            String[] attributes;
            String[] Airport;
            while ((s = br.readLine()) != null) {
                attributes = s.split(",");
                if (attributes[2].contains(City) && attributes[3].contains(Country)) {

                    Destination = new Airport(s);

                    BufferedReader rbr = new BufferedReader(
                        new FileReader(Directory + "routes.csv"));

                    while ((t = rbr.readLine()) != null) {
                        rattributes = t.split(",");

                        if ( rattributes[4].equals(Destination.getIATA()) || rattributes[4].equals(Destination.getICAO())) {
                            return new Airport(s);
                        }
                    }
                }
            }
        } catch (NullPointerException | IOException ne) {
            ne.printStackTrace();
        }
        System.out.println("We Couldn't find travel Destination")
        return null;
    }


    static Airport findAirportClass(int AirportID) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "airports.csv"));

            String s;
            while ((s = br.readLine()) != null) {
                String[] attributes = s.split(",");
                if (Integer.parseInt(attributes[0]) == AirportID) {

                    return new Airport(s);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }

        return null;
    }


    static Airport findAirportClass(String AirportName) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "airports.csv"));

            String s;
            while ((s = br.readLine()) != null) {
                if (s.contains(AirportName)) {
                    return new Airport(s);
                }
            }
        }  catch (NullPointerException | IOException ne) {
            ne.printStackTrace();
        }
        return null;
    }


    static List<Airport> findAllDestinationAirports(Airport SourceAirportID) {
        int counter = 0;
        List<Airport> Destination = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "routes.csv"));
            String s;
            String[] attributes;
            while ((s = br.readLine()) != null) {
                attributes = s.split(",");
                try {
                    if ( !attributes[5].contains("N") && attributes[2].equals(SourceAirportID.getIATA()) || attributes[2].equals(SourceAirportID.getICAO())) {
                        Destination.add(findAirportClass(Integer.parseInt(attributes[5])));
                    }
                }catch (NumberFormatException nFe){
                    nFe.printStackTrace();
                }
            }
            br.close();
            return Destination;
        }  catch (NullPointerException | IOException ne) {
            ne.printStackTrace();
        }
        return Destination;
    }


    static String printResults(String[] route, String Scity, String Dcity){
        List<Airport> Airports = new ArrayList<>();
        List<Route> Routes = new ArrayList<>();
        for(int i = 0; route[i]!= null; i++) {
            Airports.add(findAirportClass(route[i]));
            if (i > 0 && route[i] != null) {

                try {
                    BufferedReader br = new BufferedReader(
                            new FileReader(Directory + "routes.csv"));
                    String s;
                    String[] attributes;

                    while ((s = br.readLine()) != null) {
                        attributes = s.split(",");
                        if ((attributes[2].contains(Airports.get(i-1).getIATA()) || attributes[2].contains(Airports.get(i-1).getICAO())) && (attributes[4].contains(Airports.get(i).getIATA()) || attributes[4].contains(Airports.get(i).getICAO()))) {

                            Routes.add(new Route(s));
                        }
                    }
                }  catch (NullPointerException | IOException ne) {
                    ne.printStackTrace();
                }

            }

        }
        int totalflights = 0;
        int totalstops = 0;
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(Directory +Scity+ "_" + Dcity+  "_output.txt"));


            for(int i = 1; route[i]!= null; i++) {
                bw.write(Integer.toString(i) + ". " + Routes.get(i).Airline + " from " + Airports.get(i-1).getIATA() + " to " + Airports.get(i).getIATA() +" "+ Routes.get(i).stops + " stops\n");
                totalflights = i;
                totalstops = totalstops + Routes.get(i).stops;

            }
            bw.write("Total flights: " + Integer.toString(totalflights)+ "\n");
            bw.write("Total Additional Stops: " + Integer.toString(totalstops));

            bw.close();
        }  catch (NullPointerException | IOException ne) {
            ne.printStackTrace();
        }
        return Directory + route[0] + route[totalflights] + "output.txt";
    }


    static String[] findroute() {
        String[] Data = ProcessRequest( "Accra_Winnipeg.txt");

        String[] route = new String[100];

        Set<Airport> Explored = new HashSet<>();


        int j = 0;
        Airport SourceAirport = null;
        Airport DestinationAirport = null;
        try {
            SourceAirport = findAirportClass(Data[0], Data[1]);

            DestinationAirport = findDAirportClass(Data[2], Data[3]); // @param DestinationCity, DestinationCountry
            assert DestinationAirport != null;
        } catch (NullPointerException ne) {
            ne.printStackTrace();

        }

        Airport min = SourceAirport;
        assert min != null;
        route[0] = min.getAirportName();
        Explored.add(min);

        while (min != DestinationAirport) {
            List<Airport> Destinations = findAllDestinationAirports(min);

            min = Destinations.get(0);

            for (int i = 0; i < Destinations.size() && Destinations.get(i) != null; i++) {


                if (Destinations.get(i) != null) {

                    if (Destinations.get(i).getAirportName().equals(DestinationAirport.getAirportName())) {
                        System.out.println("Found Solution!");
                        j = j + 1;
                        route[j] = min.getAirportName();
                        route[j + 1] = Destinations.get(i).getAirportName();
                        System.out.println(Arrays.toString(route));
                        printResults(route);
                        return route;
                    } else if (!Explored.contains(Destinations.get(i)) && Heuristic(Destinations.get(i), DestinationAirport) < Heuristic(min, DestinationAirport)) {
                        min = Destinations.get(i);
                        Explored.add(min);


                    }
                }
            }
            j = j + 1;
            route[j] = min.getAirportName();
        }

        return route;


    }



    public static void main(String[] args) {
        String Directory = "C:\\Users\\joseph.dzagli\\OneDrive - Ashesi University\\Canvas\\Intermediate Computer Programming\\";
        findroute();



    }
}


