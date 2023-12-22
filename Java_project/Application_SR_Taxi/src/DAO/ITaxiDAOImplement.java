package DAO;

import metier.Taxi;
import  java.util.*;

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

    @Override
    public List <Taxi> SelectTaxisDispo() {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs =null;
        Taxi t=new Taxi();
        List <Taxi> taxis=new ArrayList<>();
        try{
            conn=ConnectionDB.getConnexion();
            String sql= "SELECT * FROM taxi WHERE status = ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"Disponible");
            rs=ps.executeQuery();
            while(rs.next())
            {
                t.setMatricule(rs.getString("matricule"));
                t.setModele(rs.getString("modele"));
                t.setStatus(rs.getString("status"));
                taxis.add(t);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return taxis;
    }
    public void UpdateTaxiStatus(Taxi t,String status)//modifie le status du taxi après la fin du reservation
    {
        Connection conn=null;
        PreparedStatement ps=null;
        try{
            conn=ConnectionDB.getConnexion();
            String sql= "UPDATE taxi SET status=? WHERE matricule=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,status);
            ps.setString(2,t.getMatricule());
            int ligne=ps.executeUpdate();
            if(ligne>0)
            {
                System.out.println("modified");
            }
            else {
                System.out.println("modif failed");
            }






        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
