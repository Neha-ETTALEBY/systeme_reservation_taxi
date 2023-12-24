package DAO;

import metier.Client;
import metier.Taxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IClientDAOImplement implements  IClientDAO{

    public Client SelectClientParId(int id)
    {
        Client cl=new Client();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try
        {
            connection = ConnectionDB.getConnexion();
            String sql = "SELECT * FROM client WHERE idClient=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cl.setId(id);
                cl.setNom(resultSet.getString("nom"));
                cl.setPrenom(resultSet.getString("prenom"));
                cl.setTelephone(resultSet.getString("telephone"));
                cl.setEmail(resultSet.getString("email"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }

        return cl;
    }

    @Override
    public int SelectIdOfClient(Client c) {

            Connection connection;
            PreparedStatement statement;
            ResultSet resultSet;

            int x=0;
            try
            {
                connection = ConnectionDB.getConnexion();
                String sql = "SELECT idClient FROM client WHERE  nom=? AND prenom=? AND telephone=? AND email=? AND password=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1,c.getNom());
                statement.setString(2,c.getPrenom());
                statement.setString(3,c.getTelephone());
                statement.setString(4,c.getEmail());
                statement.setString(5,c.getPassword());
                resultSet = statement.executeQuery();
                while(resultSet.next())
                  x=resultSet.getInt("idClient");

            }
            catch (SQLException e)
            {
                e.printStackTrace();

            }

         return  x;
    }

    @Override
    public void InsererClient(Client c) {
        Connection conn=null;
        PreparedStatement ps =null;
        try{
            conn=ConnectionDB.getConnexion();
            conn = ConnectionDB.getConnexion();
            String sql = "INSERT INTO client (nom,prenom,telephone,email,password)  VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //insertion
            ps.setString(1,c.getNom());
            ps.setString(2,c.getPrenom());
            ps.setString(3,c.getTelephone());
            ps.setString(4,c.getEmail());
            ps.setString(5,c.getPassword());
            int insertion_reussie=ps.executeUpdate();
            if(insertion_reussie>0)
            {
                System.out.println("insertion du client reussie");
            }
            else
            {
                System.out.println("insertion du client echoue");

            }


        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
