package metier;

public class Client extends Personne {
    public Client () { super();}
    public Client(String nom, String prenom, String telephone, String email) {
        super(nom, prenom, telephone, email);
    }


    @Override
    public String toString() {
        return "Client{" +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
