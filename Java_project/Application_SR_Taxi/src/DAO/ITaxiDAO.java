package DAO;

import metier.Taxi;
import  java.util.*;

public interface ITaxiDAO {

    public Taxi SelectTaxiParMatricule(String matricule);
    public Taxi SelectTaxiAleatoire();
    public List <Taxi> SelectTaxisDispo();
    public void UpdateTaxiStatus(Taxi t,String status);
}
