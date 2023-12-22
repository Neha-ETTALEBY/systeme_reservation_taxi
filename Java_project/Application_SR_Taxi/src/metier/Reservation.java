package metier;

import java.sql.Date;
import java.sql.Time;

public class Reservation {
    private Client client;
    private Conducteur conducteur;
    private int id;
    private String lieuSource;//à verifier son type
    private String lieuDestination;//à verifier son type
    private  double tarif;
    private String typePaiement; //à verifier son type

    private Date date;
    private Time heure ;

    public Reservation () { }

    public Reservation(Client client, Conducteur conducteur,  String lieuSource, String lieuDestination, double tarif, String typePaiement, Date date, Time heure) {
        this.client = client;
        this.conducteur = conducteur;
        this.lieuSource = lieuSource;
        this.lieuDestination = lieuDestination;
        this.tarif = tarif;
        this.typePaiement = typePaiement;
        this.date = date;
        this.heure = heure;
    }

    // getters-------------
    public String getLieuSource() {
        return lieuSource;
    }
    public String getLieuDestination() {
        return lieuDestination;
    }
    public double getTarif() {
        return tarif;
    }
    public String getTypePaiement() {
        return typePaiement;
    }

    public Client getClient() { //retourne les infos du client qui a fait la reservation et ainsi de suite
        return client;
    }

    public Conducteur getConducteur() {
        return conducteur;
    }

    public int getId() {
        return id;
    }

    //setters -----------
    public void setId(int idreservation) {
        this.id = idreservation;
    }

    public void setLieuSource(String lieuSource) {
        this.lieuSource = lieuSource;
    }

    public void setLieuDestination(String lieuDestination) {
        this.lieuDestination = lieuDestination;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public void setClient(Client idClient) {
        this.client = idClient;
    }

    public void setConducteur(Conducteur idConducteur) {
        this.conducteur = idConducteur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "client=" + client.toString() +
                ", conducteur=" + conducteur.toString() +
                ", id=" + id +
                ", lieuSource='" + lieuSource + '\'' +
                ", lieuDestination='" + lieuDestination + '\'' +
                ", tarif=" + tarif +
                ", typePaiement='" + typePaiement + '\'' +
                ", date=" + date +
                ", heure=" + heure +
                '}';
    }
}
