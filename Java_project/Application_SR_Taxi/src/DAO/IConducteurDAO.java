package DAO;

import metier.*;

public interface IConducteurDAO {
   public Conducteur SelectConductParId(int id );
   public void InsererConducteur(Conducteur c);
   public Conducteur SelectConducteurAleatoire();
   public int SelectIdOfConducteur(Conducteur c);
   public Conducteur SelectConducteurOfTaxi(Taxi taxi);//pour savoir le conducteur d'un taxi
}
