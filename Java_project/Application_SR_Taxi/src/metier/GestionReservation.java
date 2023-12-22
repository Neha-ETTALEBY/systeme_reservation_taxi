package metier;

import DAO.*;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class GestionReservation {
    public void effectuerReservation (Client client,String lieuSource ,String lieuDestination,double tarifMax,String typePaiement){
        //ici avant d'effectuer la reservation du client il faut d'abord chercher les taxi dispo qui vont prendre cette reservation
        //ainsi il faut calculer  tarif en considerant la distance entre lieuSrc et dest
        ITaxiDAO interTaxi=new ITaxiDAOImplement();
        List<Taxi> taxis=new ArrayList<>();
        taxis=interTaxi.SelectTaxisDispo();
        //affichage des taxis dipso ;
        for (Taxi  t: taxis)
        {
            System.out.println(t);
        }
        //temporaire ( a supprimer apres) -----------
        System.out.println("ecrivez le matricule du taxi de vous voulez reserver : ");
        Scanner scanner = new Scanner(System.in);
        String matriculeTaxi = scanner.nextLine();
        scanner.close();
        //---------------
        Taxi taxi=new Taxi();
        taxi=interTaxi.SelectTaxiParMatricule(matriculeTaxi);
        IConducteurDAO interCond=new IConducteurDAOImplement();
        Conducteur conducteur=new Conducteur();
        conducteur=interCond.SelectConducteurOfTaxi(taxi);
        Date dateActuelle = new java.sql.Date(System.currentTimeMillis());
        Time heureActuelle = new java.sql.Time(System.currentTimeMillis());
        Reservation res=new Reservation(client,conducteur, lieuSource, lieuDestination, tarifMax, typePaiement, dateActuelle, heureActuelle);
        IReservationDAO interRes=new IReservationDAOImplement();
        interRes.InsertReservation(res);


    }
    public void CalculerTarifEnFctDistance()
    {

    }

}
