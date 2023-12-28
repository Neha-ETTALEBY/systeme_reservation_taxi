package DAO;
import metier.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IConducteurDAOImplement implements IConducteurDAO{
    Connection conn = ConnectionDB.getConnexion();
    PreparedStatement stmt = null;
    PreparedStatement stmt2 = null;
    PreparedStatement stmt3 = null;
    ResultSet rs = null;
    //------------------------maria ------------------------------
    public void register(Personne p) {

        try {

            //isValidEmail(p.getEmail());  On fait appel à la fonction isValidEmail pour verifier la validité de l'email
            if (isValidEmail(p.getEmail()) && isValidPassword(p.getPassword()) && !isExistEmail(p.getEmail())) {
                ITaxiDAO t=new ITaxiDAOImplement();
                String  mat=t.selectRandomMatricule();
                stmt = conn.prepareStatement("INSERT INTO conducteur(nom, prenom, telephone, email, password, matricule)"
                        + "VALUES (?, ?, ?, ?, ?, ?)");
                stmt.setString(1, p.getNom());
                stmt.setString(2, p.getPrenom());
                stmt.setString(3, p.getTelephone());
                stmt.setString(4, p.getEmail().toLowerCase());
                stmt.setString(5, p.getPassword());
                stmt.setString(6, mat);
                stmt.executeUpdate();
                System.out.println("Inserted!");
               t.updateTaxiAffectationConducteur(mat); //on met a jour de la colonne taxiAffectation = Oui

                // On doit mettre a jour le status du taxi car il n'est plus dispo


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public boolean isValidEmail(String email) {
        String regexExpression = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(email);
        boolean m = matcher.find();
        if(m==false)
        {System.out.println("email invalid (i.e:exemple.exemple@mail.xx)");}
        return m;
    }
    public boolean isExistEmail(String email) {
        boolean flag = false;
        try {
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM conducteur WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                flag = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (flag) {
            System.out.println("Email already exists!");
        }

        return flag;
    }
    public void login(String email, String password) {
        if (conn != null) {
            try {
                stmt = conn.prepareStatement("SELECT idConducteur, nom, password FROM conducteur WHERE email = ?");
                stmt.setString(1, email);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Welcome " + rs.getString("nom")
                            + ", ur password : " + rs.getString("password"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("you are not connected");
        }
    }
    public boolean isValidPassword(String password) {
        String regexExpression = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{1,15})";
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(password);
        boolean ma = matcher.find();
        if(ma==false)
        {System.out.println("password invalid (i.e:Mm$123");}
        return ma;

    }


    public int getIdFromDB(Personne p) {
        int id = -1;
        try {
            stmt = conn.prepareStatement("SELECT idConducteur FROM conducteur WHERE email = ?");
            stmt.setString(1, p.getEmail());
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idConducteur");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;

    }

    // La methode getRandomConducteur affecte un conducteur  pour une reservation d'un client donne
    public int getRandomConducteur() {
        int idConducteur = -1;
        try {
            stmt2 = conn.prepareStatement("SELECT TOP 1 c.idConducteur FROM conducteur c JOIN taxi t ON c.matricule = t.matricule WHERE t.status = ? ORDER BY NEWID()");
            stmt2.setString(1, "Disponible");
            rs = stmt2.executeQuery();

            if (rs.next()) {
                // Au moins un conducteur disponible a été trouvé
                idConducteur = rs.getInt("idConducteur");
                // Vous pouvez également ajouter d'autres traitements si nécessaire
            } else {
                System.out.println("Aucun conducteur disponible trouvé.");
                // Gérer le cas où aucun conducteur disponible n'est trouvé, si nécessaire
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la récupération d'un conducteur disponible. Réessayez plus tard.");
            e.printStackTrace();
        } finally {
            // Assurez-vous de fermer le ResultSet et le PreparedStatement dans la section "finally"
            // pour éviter les fuites de ressources
            try {
                if (rs != null) rs.close();
                if (stmt2 != null) stmt2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idConducteur;
    }


    public void logout() {
        System.exit(0);
        conn = null;
    }

    //------------------------------------mine -----------------
    public Conducteur SelectConductParId(int id)
    {
        Conducteur c=new Conducteur();
        conn=null;
        stmt=null;
        rs=null;
        try
        {
            conn = ConnectionDB.getConnexion();
            String sql = "SELECT * FROM conducteur WHERE idConducteur=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                c.setId(id);
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

        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }

        return c;
    }

    @Override
    public Conducteur SelectConducteurOfTaxi(Taxi taxi) {

         conn=null;
         stmt=null;
         rs=null;
        Conducteur c=new Conducteur();
        try
        {
            conn = ConnectionDB.getConnexion();
            String sql = "SELECT * FROM conducteur WHERE matricule=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,taxi.getMatricule());
            rs = stmt.executeQuery();
            while(rs.next())
            {
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setTelephone(rs.getString("telephone"));
                c.setEmail(rs.getString("email"));
                c.setTaxi(taxi);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }

        return  c;
    }
}
