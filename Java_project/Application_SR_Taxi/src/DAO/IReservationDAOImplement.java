package DAO;
import  metier.*;
import java.sql.*;
import java.util.*;

public class IReservationDAOImplement implements IReservationDAO{
    Connection conn = ConnectionDB.getConnexion();
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    @Override
    public void getAllReservations() {
        conn=null;
        stmt=null;
        rs=null;
        try
        {
            conn = ConnectionDB.getConnexion();
            String sql = "SELECT * FROM reservation";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation();

                // pour faire le traitement de la recherche des infos des clients et  conducteurs et  taxis pour les stocker dans les attributs de type  Client .... etc
                // appel a la methode qui fait le traitement de recherche client
                //resulat souhaité c'est : afficher les reservations avec les infos complet du client et
                //les infos complet du conducteur ainsi du taxi
                  int id =rs.getInt("idClient");
                  IClientDAO cl =new IClientDAOImplement();
                  Client client=new Client();
                  client= cl.SelectClientParId(id);
                // appel a la methode qui fait le traitement de  recherche conducteur
                id=rs.getInt("idConduteur");
                IConducteurDAO c=new IConducteurDAOImplement();
                Conducteur conducteur=new Conducteur();
                conducteur =c.SelectConductParId(id);

                reservation.setClient(client);
                reservation.setConducteur(conducteur);
                reservation.setLieuSource(rs.getString("lieuSource"));
                reservation.setLieuDestination(rs.getString("lieuDestination"));
                reservation.setTypePaiement(rs.getString("typePaiement"));
                reservation.setTarif(rs.getDouble("tarif"));
                reservation.toString();


            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }

    }

    @Override
    public void getReservationOfSpecificClient(Client client ) {
        Reservation R=new Reservation();
         conn=null;
         stmt=null;
         rs=null;
        int idClient;//stocke id du client a partir de ses infos entrees dans argument
        try{
             conn=ConnectionDB.getConnexion();
             String sql="SELECT * FROM reservation WHERE idClient=?";
             IClientDAO c=new IClientDAOImplement();
             idClient=c.getIdFromDB(client);// la methode retourne l ID du client entrée dans argument en comparant ses infos avec celles dans la BD
             stmt=conn.prepareStatement(sql);
             stmt.setInt(1,idClient);
             rs=stmt.executeQuery();
             while(rs.next()){
                 R.setClient(client);
                 //***** obtenir le conducteur a partir de son id ///
                 int id=rs.getInt("idConducteur");
                 IConducteurDAO idcond=new IConducteurDAOImplement();
                 Conducteur conducteur=new Conducteur();
                 conducteur =idcond.SelectConductParId(id);
                 ///**** stocker les infos dans une var  reservation pour  l'afficher par la suite ///
                 R.setConducteur(conducteur);
                 R.setLieuSource(rs.getString("lieuSource"));
                 R.setLieuDestination(rs.getString("lieuDestination"));
                 R.setTypePaiement(rs.getString("typePaiement"));
                 R.setTarif(rs.getDouble("tarif"));

             }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(R.toString());
    }

    @Override
    public void InsertReservation(Reservation r) {
         conn=null;
         stmt=null;
         rs=null;
        try{
            conn = ConnectionDB.getConnexion();
            String sql = "INSERT INTO reservation (lieuSource,lieuDestination,typePaiement,tarif,date,heure,idClient,idConducteur,matricule ) VALUES (?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,r.getLieuSource());
            stmt.setString(2,r.getLieuDestination());
            stmt.setString(3,r.getTypePaiement());
            stmt.setDouble(4,r.getTarif());
            stmt.setDate(5,r.getDate());
            stmt.setTime(6,r.getHeure());
            IClientDAO e=new IClientDAOImplement();
            stmt.setInt(7,e.getIdFromDB(r.getClient()));//ici on cherche le client  dans BD s il existe  et on retourne son id
            IConducteurDAO cond=new IConducteurDAOImplement();
            stmt.setInt(8,cond.getIdFromDB(r.getConducteur()));//ici on cherche le conducteur  dans BD s il existe  et on retourne son id
            stmt.setString(9,r.getConducteur().getTaxi().getMatricule());//la meme chose ici
            int l=stmt.executeUpdate();
           if( l>0)
           {
               System.out.println("Insertion du reservation réussie.");
           }else {
               System.out.println("Insertion du reservation echoué.");
           }


        }catch ( SQLException e){
            e.printStackTrace();
        }
    }
}
