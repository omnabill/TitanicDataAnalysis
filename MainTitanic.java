
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class MainTitanic {
             
    public static void main(String[] args) throws FileNotFoundException {
        XchartDao n=new XchartDao();
       List<TitanicPassengers> passengerList = n.getPassengersFromJsonFile();
        n.graphPassengerAges(passengerList);
        n.getPclass(passengerList);
       n.getPassengerSurvived(passengerList);
      n.getPassengerSurvivedGender(passengerList);
    }
    
}
