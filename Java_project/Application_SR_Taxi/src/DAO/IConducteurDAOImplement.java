package DAO;

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
}
