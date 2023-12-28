package DAO;

import metier.*;

import java.util.List;

public interface IConducteurDAO extends IPersonneDAO {
   public Conducteur SelectConductParId(int id );

   public Conducteur SelectConducteurOfTaxi(Taxi taxi);//pour savoir le conducteur d'un taxi
    public int getRandomConducteur(); //cette methode retourne l'id d'un conducteur aléatoirement (util pour une reservation)

}
