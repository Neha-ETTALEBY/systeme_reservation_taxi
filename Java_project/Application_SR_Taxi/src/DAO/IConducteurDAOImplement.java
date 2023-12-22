package DAO;

import metier.Client;
import metier.Conducteur;
import metier.Taxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IConducteurDAOImplement implements IConducteurDAO{

    public Conducteur SelectConductParId(int id)
    {
        Conducteur c=new Conducteur();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try
        {
            connection = ConnectionDB.getConnexion();
            String sql = "SELECT * FROM conducteur WHERE idConducteur=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                c.setId(id);
                c.setNom(resultSet.getString("nom"));
                c.setPrenom(resultSet.getString("prenom"));
                c.setTelephone(resultSet.getString("telephone"));
                c.setEmail(resultSet.getString("email"));
                String mat=resultSet.getString("matricule");
                ITaxiDAO i=new ITaxiDAOImplement();
                Taxi taxi=new Taxi();
                taxi=i.SelectTaxiParMatricule(mat);
                c.setTaxi(taxi);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }

        return c;
    }

    @Override
    public void InsererConducteur(Conducteur c) {
        Connection conn=null;
        PreparedStatement ps =null;
        try{
            conn=ConnectionDB.getConnexion();
            conn = ConnectionDB.getConnexion();
            String sql = "INSERT INTO conducteur (nom,prenom,email,telephone,matricule)  VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //pour affecter un taxi aleatoirement à ce conducteur
            ITaxiDAO inter=new ITaxiDAOImplement();
            Taxi t=new Taxi();
            t=inter.SelectTaxiAleatoire();
            c.setTaxi(t);
            //insertion
            ps.setString(1,c.getNom());
            ps.setString(2,c.getPrenom());
            ps.setString(3,c.getEmail());
            ps.setString(4,c.getTelephone());
            ps.setString(5,c.getTaxi().getMatricule());
            int insertion_reussie=ps.executeUpdate();
            if(insertion_reussie>0)
            {
                System.out.println("insertion conducteur reussie");
            }
            else
            {
                System.out.println("insertion  conducteur echoue");

            }


        }catch(SQLException e){
           e.printStackTrace();
        }

    }
    public int SelectIdOfConducteur(Conducteur c) {

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        int x=0;
        try
        {
            connection = ConnectionDB.getConnexion();
            String sql = "SELECT idConducteur FROM conducteur WHERE  nom=? AND prenom=? AND telephone=? AND email=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,c.getNom());
            statement.setString(2,c.getPrenom());
            statement.setString(3,c.getTelephone());
            statement.setString(4,c.getEmail());
            resultSet = statement.executeQuery();
            while(resultSet.next())
                x=resultSet.getInt("idConducteur");

        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }

        return  x;
    }


    public Conducteur SelectConducteurAleatoire() {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs =null;
        Conducteur c=new Conducteur();
        try{
            conn=ConnectionDB.getConnexion();
            String sql= "SELECT TOP 1 * FROM conducteur ORDER BY NEWID()";//pour selectionner un conducteur aleatoirement
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
            {
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setTelephone(rs.getString("telephone"));
                c.setEmail(rs.getString("email"));
                String mat=rs.getString("matricule");
                ITaxiDAO i=new ITaxiDAOImplement();
                Taxi taxi=new Taxi();
                taxi=i.SelectTaxiParMatricule(mat);
                c.setTaxi(taxi);

            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
