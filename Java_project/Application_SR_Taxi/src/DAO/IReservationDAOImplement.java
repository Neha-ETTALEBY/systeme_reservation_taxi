package DAO;
import  metier.*;
import java.sql.*;
import java.util.*;

public class IReservationDAOImplement implements IReservationDAO{
    @Override
    public void getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try
        {
            connection = ConnectionDB.getConnexion();
            String sql = "SELECT * FROM reservation";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Reservation reservation = new Reservation();

                // pour faire le traitement de la recherche des infos des clients et  conducteurs et  taxis pour les stocker dans les attributs de type  Client .... etc
                // appel a la methode qui fait le traitement de recherche client
                  int id =resultSet.getInt("idClient");
                  IClientDAO cl =new IClientDAOImplement();
                  Client client=new Client();
                  client= cl.SelectClientParId(id);
                // appel a la methode qui fait le traitement de  recherche conducteur
                id=resultSet.getInt("idConduteur");
                IConducteurDAO c=new IConducteurDAOImplement();
                Conducteur conducteur=new Conducteur();
                conducteur =c.SelectConductParId(id);

                // remplissage du liste de resevations
                reservation.setId(resultSet.getInt("idReservation"));
                reservation.setClient(client);
                reservation.setConducteur(conducteur);
                reservation.setLieuSource(resultSet.getString("lieuSource"));
                reservation.setLieuDestination(resultSet.getString("lieuDestination"));
                reservation.setTypePaiement(resultSet.getString("typePaiement"));
                reservation.setTarif(resultSet.getDouble("tarif"));

                reservations.add(reservation);

            }
            for (Reservation e : reservations)
            {
               System.out.println(e.toString());
            }
            //statement.close();
           // connection.close();


        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }



    }

    @Override
    public void getReservationOfSpecificClient(Client client ) {
        Reservation R=new Reservation();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int idClient;//stocke id du client a partir de ses infos entrees dans argument
        try{
             conn=ConnectionDB.getConnexion();
             String sql="SELECT * FROM reservation WHERE idClient=?";
             IClientDAO c=new IClientDAOImplement();
             idClient=c.SelectIdOfClient(client);// la methode retourne l ID du client entrée dans argument en comparant ses infos avec celles dans la BD
             ps=conn.prepareStatement(sql);
             ps.setInt(1,idClient);
             rs=ps.executeQuery();
             while(rs.next()){
                 R.setId(rs.getInt("idReservation"));
                 R.setClient(client);
                 //***** obtenir le conducteur a partir de son id ///
                 int id=rs.getInt("idConduteur");
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
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            conn = ConnectionDB.getConnexion();
            String sql = "INSERT INTO reservation (lieuSource,lieuDestination,typePaiement,tarif,date,heure,idClient,idConducteur,matricule  VALUES (?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,r.getLieuSource());
            ps.setString(2,r.getLieuDestination());
            ps.setString(3,r.getTypePaiement());
            ps.setDouble(4,r.getTarif());
            ps.setString(5,r.getDate());
            ps.setString(6,r.getHeure());
            ps.setInt(7,r.getClient().getId());
            ps.setInt(8,r.getConducteur().getId());
            ps.setString(9,r.getConducteur().getTaxi().getMatricule());
           if(ps.executeUpdate() >0)
           {
               System.out.println("Insertion réussie.");
           }else {
               System.out.println("Insertion echoué.");
           }



        }catch ( SQLException e){
            e.printStackTrace();
        }
    }
}
