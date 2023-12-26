package DAO;

import metier.Client;
import metier.Personne;

public interface IClientDAO extends IPersonneDAO{
    //public void gererProfil();
    //public void donnerAvis(Reservation t);
    public Client SelectClientParId(int id);
    public int SelectIdOfClient( Client  c);
    public void  InsererClient (Client c);

}
