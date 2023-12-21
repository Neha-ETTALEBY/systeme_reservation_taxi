package DAO;

import metier.Taxi;

public interface ITaxiDAO {
    //public void getAllTaxis();
    public Taxi SelectTaxiParMatricule(String matricule);
}
