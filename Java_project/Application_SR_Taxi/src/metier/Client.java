package metier;

public class Client extends Personne {
    private String password;
    public Client () { super();}
    public Client(String nom, String prenom, String telephone, String email ,String password) {
        super(nom, prenom, telephone, email,password);
        this.password=password;
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
