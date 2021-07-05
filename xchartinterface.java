
import java.io.FileNotFoundException;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public interface xchartinterface {
    List<TitanicPassengers> getPassengersFromJsonFile() throws FileNotFoundException;
    void graphPassengerAges(List<TitanicPassengers> passengerList);
    void getPclass(List<TitanicPassengers> L);
    void getPassengerSurvived(List<TitanicPassengers> L);
    void getPassengerSurvivedGender(List<TitanicPassengers> L);
    
    
}
