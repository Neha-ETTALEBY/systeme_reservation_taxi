package DAO;

import metier.Taxi;
import  java.util.*;

public interface ITaxiDAO {

    public Taxi SelectTaxiParMatricule(String matricule);
    public String selectRandomMatricule();

    public void UpdateTaxiStatus(Taxi t,String status); //cette methode met à jour le statut du taxi apres avoir ete choisi pour une reservation
    public void updateTaxiAffectationConducteur(String matricule);
}

