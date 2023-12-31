package metier;

public class Conducteur extends Personne{
    private  Taxi  taxi; // on doit affecter les taxis aux conducteurs
    public Conducteur(String nom, String prenom, String telephone, String email,String password) {
        super(nom, prenom, telephone, email,password);
    }
    public Conducteur() {}

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    @Override
    public String toString() {
        return "Conducteur{" +
               // "taxi=" + taxi.toString() +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
