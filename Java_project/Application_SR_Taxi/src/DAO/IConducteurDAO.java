package DAO;

import metier.Conducteur;

public interface IConducteurDAO {
   public Conducteur SelectConductParId(int id );
   public void InsererConducteur(Conducteur c);
   public Conducteur SelectConducteurAleatoire();
   public int SelectIdOfConducteur(Conducteur c);
}
