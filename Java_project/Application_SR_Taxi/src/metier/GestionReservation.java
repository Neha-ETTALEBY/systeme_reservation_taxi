package metier;
import DAO.*;
import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.*;
import java.net.*;
import org.json.*;
import com.google.gson.*;

public class GestionReservation {
    public int effectuerReservation (Client client,String lieuSource ,String lieuDestination,String typePaiement){


        IConducteurDAO interCond=new IConducteurDAOImplement();
        int idConducteur=interCond.getRandomConducteur();
        if(idConducteur!=-1)
        {
            Conducteur conducteur=new Conducteur();
            conducteur=interCond.SelectConductParId(idConducteur);
            Date dateActuelle = new java.sql.Date(System.currentTimeMillis());
            Time heureActuelle = new java.sql.Time(System.currentTimeMillis());
            double tarifMax=CalculerTarifEnFctDistance(lieuSource,lieuDestination);
            Reservation res=new Reservation(client,conducteur, lieuSource, lieuDestination, tarifMax, typePaiement, dateActuelle, heureActuelle);
            IReservationDAO interRes=new IReservationDAOImplement();

            //insertion --------
                interRes.InsertReservation(res);
                ITaxiDAO interTaxi=new ITaxiDAOImplement();
                interTaxi.UpdateTaxiStatus(conducteur.getTaxi(),"Occupe");



        }
     else
        {
            System.out.println("Ressayez plus tard tout les taxis sont reservés");
        }

      return idConducteur;
    }
    public double CalculerTarifEnFctDistance(String lieuSource,String lieuDestination)
    {
        double tarifBase = 5.0;

        // Tarif par kilomètre
        double tarifParKilometre = 2.0;

        // Calcul du tarif total
        double distance=calculerDistance(lieuSource,lieuDestination);
        System.out.println("la distance pour  y arriver en KM :"+distance);
        double tarifTotal = tarifBase + (tarifParKilometre * distance);
        System.out.println("tarif total en DHs :"+tarifTotal);
        return tarifTotal;

    }
    public double calculerDistance (String src,String dest)
    {
        Coordonnees coordonnees_Source=new Coordonnees();
        Coordonnees coordonnees_Destination=new Coordonnees();
        double RAYON_TERRE = 6371.0;
        System.out.println("source ");
        coordonnees_Source=getCoordinates(src);
        System.out.println("Destination ");
        coordonnees_Destination=getCoordinates(dest);
        // Convertir les latitudes et longitudes en radians
        coordonnees_Source.setLatitude(Math.toRadians(coordonnees_Source.getLatitude()));
        coordonnees_Source.setLongitude(Math.toRadians(coordonnees_Source.getLongitude()));
        coordonnees_Destination.setLatitude(Math.toRadians(coordonnees_Destination.getLatitude()));
        coordonnees_Destination.setLongitude(Math.toRadians(coordonnees_Destination.getLongitude()));
        // Calcul des écarts de latitudes et longitudes
        double dLat = coordonnees_Destination.getLatitude() - coordonnees_Source.getLatitude();
        double dLon = coordonnees_Destination.getLongitude() - coordonnees_Destination.getLongitude();

        // Formule haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(coordonnees_Source.getLatitude()) * Math.cos(coordonnees_Destination.getLatitude()) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance en kilomètres
        DecimalFormat decimalFormat = new DecimalFormat("#.##");//pour afficher la distance avec deux chiffres après virgule
        String distanceAsString=decimalFormat.format(RAYON_TERRE * c);
        distanceAsString = distanceAsString.replace(',', '.');//virgule en type double doit etre  remplacer par .
        double distance =Double.parseDouble(distanceAsString);
        return distance ;

    }
    public Coordonnees getCoordinates (String location)
    {
        Coordonnees coordonnees=new Coordonnees();
        try {
            // Replace "YOUR_API_KEY" with your actual OpenCage API key
            String apiKey = "d294b1cbc21f4db1a051a092d0dc57bc";

            // Encode the location for use in the URL
            String encodedLocation = URLEncoder.encode(location, "UTF-8");

            // Create the URL with the OpenCage Geocoding API endpoint and parameters
            URL url = new URL("https://api.opencagedata.com/geocode/v1/json?q=" + encodedLocation + "&key=" + apiKey);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // crée un objet BufferedReader qui permet de lire les caractères de manière plus efficace en les stockant dans un tampon.
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));// crée un lecteur qui lit les caractères à partir du flux d'entrée.
            String line;
            StringBuilder response = new StringBuilder();// Crée un objet StringBuilder qui est utilisé pour construire une chaîne mutable. Il sera utilisé pour stocker la réponse complète.
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Assurez-vous que la clé "results" est présente dans la réponse JSON
            if (jsonResponse.has("results")) {
                // Obtenez le tableau "results"
                JSONArray resultsArray = jsonResponse.getJSONArray("results");

                // Assurez-vous que le tableau "results" n'est pas vide
                if (resultsArray.length() > 0) {
                    // Obtenez le premier élément du tableau
                    JSONObject firstResult = resultsArray.getJSONObject(0);

                    // Assurez-vous que la clé "geometry" est présente dans le premier résultat
                    if (firstResult.has("geometry")) {
                        // Obtenez l'objet "geometry" à l'intérieur du premier résultat
                        JSONObject geometryObject = firstResult.getJSONObject("geometry");

                        // Extrait la latitude et la longitude
                        double latitude = geometryObject.getDouble("lat");
                        double longitude = geometryObject.getDouble("lng");
                        coordonnees.setLatitude(latitude);
                        coordonnees.setLongitude(longitude);

                        // Imprimez les valeurs extraites
                        System.out.println("Latitude : " + latitude);
                        System.out.println("Longitude : " + longitude);
                    } else {
                        System.out.println("La clé 'geometry' n'est pas présente dans le premier résultat.");
                    }
                } else {
                    System.out.println("Le tableau 'results' est vide.");
                }
            } else {
                System.out.println("La clé 'results' n'est pas présente dans la réponse JSON.");
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
     return coordonnees;
    }
 ///il faut ajouter une methode pour notifier le conducteur qu'il a une reservation affecté
}
