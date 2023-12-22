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

    @Override
    public Taxi SelectTaxiAleatoire() {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs =null;
        Taxi t=new Taxi();
        try{
            conn=ConnectionDB.getConnexion();
            String sql= "SELECT TOP 1 * FROM taxi ORDER BY NEWID()";//pour selectionner un taxi aleatoirement
             ps=conn.prepareStatement(sql);
             rs=ps.executeQuery();
             while(rs.next())
             {
                 t.setMatricule(rs.getString("matricule"));
                 t.setModele(rs.getString("modele"));
                 t.setStatus(rs.getString("status"));
             }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
}
