package DAO;

import metier.Taxi;
import  java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ITaxiDAOImplement implements ITaxiDAO {
    Connection conn = ConnectionDB.getConnexion();
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    @Override
    public Taxi SelectTaxiParMatricule(String matricule) {
        Taxi t=new Taxi();
         conn=null;
         stmt=null;
         rs=null;
        try
        {
            conn = ConnectionDB.getConnexion();
            String sql = "SELECT * FROM taxi WHERE matricule=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,matricule);
            rs = stmt.executeQuery();

            while (rs.next()) {
                t.setMatricule(matricule);
                t.setModele(rs.getString("modele"));
                t.setStatus(rs.getString("status"));
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
         conn=null;
         stmt=null;
         rs =null;
        Taxi t=new Taxi();
        try{
            conn=ConnectionDB.getConnexion();
            String sql= "SELECT TOP 1 * FROM taxi ORDER BY NEWID()";//pour selectionner un taxi aleatoirement
             stmt=conn.prepareStatement(sql);
             rs=stmt.executeQuery();
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
        conn=null;
        stmt=null;
        rs =null;
        Taxi t=new Taxi();
        List <Taxi> taxis=new ArrayList<>();
        try{
            conn=ConnectionDB.getConnexion();
            String sql= "SELECT * FROM taxi WHERE status = ?";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,"Disponible");
            rs=stmt.executeQuery();
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
         conn=null;
         stmt=null;
         try{
            conn=ConnectionDB.getConnexion();
            String sql= "UPDATE taxi SET status=? WHERE matricule=?";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,status);
            stmt.setString(2,t.getMatricule());
            int ligne=stmt.executeUpdate();
            if(ligne>0)
            {
                System.out.println("modified status ");
            }
            else {
                System.out.println("failed status modif");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
