package DAO;

import metier.Client;
import metier.Personne;

public interface IClientDAO extends IPersonneDAO{
    public Client SelectClientParId(int id);
    public void updateNom(Personne p, String nom);
    public void updatePrenom(Personne p, String email);
    public void updateTelephone(Personne p, String telephone);

}
