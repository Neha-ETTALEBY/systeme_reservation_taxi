package DAO;
import metier.*;
public interface IPersonneDAO {
    public void register(Personne p);
    public boolean isValidEmail(String email);
    public boolean isValidPassword(String password);
    public boolean isExistEmail(String email);
    public void login(String email, String password);
   // public void consulterHistoriqueReservation();

    public int getIdFromDB(Personne p);
    public void logout();
}
