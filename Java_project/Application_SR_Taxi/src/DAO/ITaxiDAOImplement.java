package DAO;

import metier.Taxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ITaxiDAOImplement implements ITaxiDAO {
    @Override
    public Taxi SelectTaxiParMatricule(String matricule) {
        Taxi t=new Taxi();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try
        {
            connection = ConnectionDB.getConnexion();
            String sql = "SELECT * FROM taxi WHERE matricule=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,matricule);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                t.setMatricule(matricule);
                t.setModele(resultSet.getString("modele"));
                t.setStatus(resultSet.getString("status"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }



        return t;
    }
}
