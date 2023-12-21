package DAO;

import metier.Client;

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
                cl.setIdClient(id);
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
                String sql = "SELECT idClient FROM client WHERE  nom=? AND prenom=? AND telephone=? AND email=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1,c.getNom());
                statement.setString(2,c.getPrenom());
                statement.setString(3,c.getTelephone());
                statement.setString(4,c.getEmail());
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
}
