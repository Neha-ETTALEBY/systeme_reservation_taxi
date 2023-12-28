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
    //-------maria-------------
    public String selectRandomMatricule() {
        //utilisé lors de la inscription d'un nouveau conducteur il faut afffecter à lui un taxi qui n'a pas un conducteur avant
        conn=null;
        stmt=null;
        rs=null;
        String matricule="";
        try {
            conn=ConnectionDB.getConnexion();
            stmt = conn.prepareStatement("SELECT TOP 1 matricule FROM taxi WHERE affectationConducteur = ? ORDER BY NEWID()");
            stmt.setString(1, "Non");
            rs = stmt.executeQuery();
            while(rs.next()) {
               matricule=rs.getString("matricule");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return matricule;
    }
    //Lorsqu'un conducteur est inscrit , on lui affecte un taxi, dans ce cas nous devons change la colonne affecationConducteuur
    public void updateTaxiAffectationConducteur(String matricule) {

        //  Lorsque le conducteur est inscrit , le taxi est affecté donc on doit changer la col affecationConducteur
        // pour indiquer que le taxi est déjà pris
        try {

            stmt = conn.prepareStatement("UPDATE taxi SET affectationConducteur = ? WHERE matricule = ?");
            String oui="Oui";
            stmt.setString(1, oui);
            stmt.setString(2, matricule );
            stmt.executeUpdate();
            conn.commit();
            System.out.println("affectationConducteur updated");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
