package DAO;
import metier.Client;
import metier.Reservation;
public interface IReservationDAO {
    public void getAllReservations();
    public void getReservationOfSpecificClient(Client client); // should has an argument Client {maria}
    public void InsertReservation( Reservation  r);
}
