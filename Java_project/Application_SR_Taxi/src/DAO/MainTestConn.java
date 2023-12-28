package DAO;

import metier.*;

import java.sql.*;

public class MainTestConn {
    public static void main (String [] args){

        IClientDAO i =new IClientDAOImplement();
        Client client=new Client("client1","clt1","03222666","lvvf@vfv4.com","c01M$5");
        //i.register(client);

        Date dateActuelle = new java.sql.Date(System.currentTimeMillis());
        Time heureActuelle = new java.sql.Time(System.currentTimeMillis());
        GestionReservation reserver=new GestionReservation();
        reserver.effectuerReservation(client,"Dakhla","Jamaa el fna, Marrakech 40000", "Carte Bancaire");
        //Reservation res=new Reservation(client,  ,  "bine 9chali", "gueliz", 100.5, "carte bancaire", dateActuelle, heureActuelle);


        /*

        IConducteurDAO e=new IConducteurDAOImplement();

        Conducteur c = new Conducteur();

        //i.InsererClient(client);
        c=e.SelectConducteurAleatoire();


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
        //Client client =new Client("Guer","Maria","123456789","maria.guer@gmail.com","M123@");
        //GestionReservation reserver=new GestionReservation();
        //reserver.effectuerReservation(client,"Dakhla","Jamaa el fna, Marrakech 40000", "Carte Bancaire");
         //reserver.CalculerTarifEnFctDistance("Carré Eden, Av. Mohammed V, Marrakech 40000");
         //System.out.println(reserver.calculerDistance("DAKHLA","MARRAKESH")+"km");
      // IConducteurDAO cond=new IConducteurDAOImplement();
     // Personne p = new Conducteur("ko","ko","123456789","Neha.t@example.com","Po@123");
     //  cond.register(p);
        /*
        PreparedStatement stmt=null;
        Connection conn= null;
        try
        {
            conn=ConnectionDB.getConnexion();
            ITaxiDAO t=new ITaxiDAOImplement();
            String  mat=t.selectRandomMatricule();
            stmt = conn.prepareStatement("INSERT INTO conducteur(nom, prenom, telephone, email, password, matricule)"
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, p.getNom());
            stmt.setString(2, p.getPrenom());
            stmt.setString(3, p.getTelephone());
            stmt.setString(4, p.getEmail().toLowerCase());
            stmt.setString(5, p.getPassword());
            stmt.setString(6, mat);
            stmt.executeUpdate();
            System.out.println("Inserted!");

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
*/
    }


}
