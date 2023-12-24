package DAO;

import metier.Client;
import metier.Conducteur;
import metier.GestionReservation;
import metier.Reservation;

import java.sql.Date;
import java.sql.Time;

public class MainTestConn {
    public static void main (String [] args){
        /*
        IReservationDAO R=new IReservationDAOImplement();
        IConducteurDAO e=new IConducteurDAOImplement();
        IClientDAO i =new IClientDAOImplement();
        Conducteur c = new Conducteur();

        //i.InsererClient(client);
        c=e.SelectConducteurAleatoire();
        Date dateActuelle = new java.sql.Date(System.currentTimeMillis());
        Time heureActuelle = new java.sql.Time(System.currentTimeMillis());
        Reservation res=new Reservation(client,  c,  "bine 9chali", "gueliz", 100.5, "carte bancaire", dateActuelle, heureActuelle);

         R.InsertReservation(res);

         // R.getAllReservations();
      // Conducteur c = new Conducteur("neha","ettal","123456789","neha@example.com");
      // Conducteur c = new Conducteur("neha","ettal","123456789","neha@example.com");

        //  R.getReservationOfSpecificClient(c);
         //System.out.println(c.toString());
        // IClientDAO e=new IClientDAOImplement();
       //  e.InsererClient(c);
        //IConducteurDAO e =new IConducteurDAOImplement();
      //  e.InsererConducteur(c);
       // Reservation res=new Reservation(c,);
        // R.InsertReservation(res);
        // à faire
        // avant d'inserer une reservation il faut que le client soit dans database  ainsi que le conducteur
        //donc je dois ajouter une methode d'insertion du client puis conducteur puis je dois recuperer leur  id
        //pour  que je puisse les inserer dans reservation
*/
        Client client =new Client("Guer","Maria","123456789","maria.guer@gmail.com","M123@");
        GestionReservation reserver=new GestionReservation();
        reserver.effectuerReservation(client,"Dakhla","Jamaa el fna, Marrakech 40000", "Carte Bancaire");
         //reserver.CalculerTarifEnFctDistance("Carré Eden, Av. Mohammed V, Marrakech 40000");
         //System.out.println(reserver.calculerDistance("DAKHLA","MARRAKESH")+"km");
    }
}
