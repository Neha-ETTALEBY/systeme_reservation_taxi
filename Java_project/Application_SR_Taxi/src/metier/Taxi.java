package metier;

public class Taxi {
    private String matricule;
    private String modele;
    private String status;
    private String affectationConduteur;
   public Taxi (){ }

    public Taxi(String matricule, String modele, String status, String affectationConduteur) {

        this.matricule = matricule;
        this.modele = modele;
        this.status = status;
        this.affectationConduteur = affectationConduteur;
    }
//getters--------------------
    public String getMatricule() {
        return matricule;
    }

    public String getModele() {
        return modele;
    }

    public String getStatus() {
        return status;
    }

    public String getAffectationConduteur() {
        return affectationConduteur;
    }
    //setters -------

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAffectationConduteur(String affectationConduteur) {
        this.affectationConduteur = affectationConduteur;
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "matricule='" + matricule + '\'' +
                ", modele='" + modele + '\'' +
                ", status='" + status + '\'' +
                ", affectationConduteur='" + affectationConduteur + '\'' +
                '}';
    }
}
