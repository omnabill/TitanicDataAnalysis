
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
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
public class XchartDao implements xchartinterface{
   
    @Override
    public List<TitanicPassengers> getPassengersFromJsonFile() throws FileNotFoundException{
        List<TitanicPassengers> passengers = new ArrayList<TitanicPassengers> ();
            ObjectMapper objectMapper = new ObjectMapper ();
            objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            InputStream input = new FileInputStream ("E:\\CS courses\\ITI_Material\\java_course\\Session6Xchart\\Session6Xchart\\titanic_csv.json");
        try {
            passengers= objectMapper.readValue (input, new TypeReference<List<TitanicPassengers>> () { });
        } catch (IOException ex) {
            Logger.getLogger(XchartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return passengers;
        
    }
    @Override
    public void graphPassengerAges(List<TitanicPassengers> passengerList){
            
        List<Float> pAges = passengerList.stream ().map (TitanicPassengers::getAge).limit (8).collect (Collectors.toList ());
            List<String> pNames = passengerList.stream().map (TitanicPassengers::getName).limit (8).collect (Collectors.toList ());
             CategoryChart  chart = new CategoryChartBuilder().width (1024).height (768).title ("Age Histogram").xAxisTitle ("Names").yAxisTitle("Age").build ();
             chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
             chart.getStyler ().setHasAnnotations (true);
             chart.addSeries ("Passenger's Ages", pNames, pAges);
               chart.setTitle("Passengers Age Data");
             // 4.Show it 
             new SwingWrapper (chart).displayChart ();
             
             
}
    @Override
    public void getPclass(List<TitanicPassengers> L){
     Map<String, Long> result =L.stream ().collect (Collectors.groupingBy (TitanicPassengers::getPclass, Collectors.counting () ) );
     PieChart chart = new PieChartBuilder ().width (800).height (600).title (getClass ().getSimpleName ()).build ();
     Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};
     chart.getStyler ().setSeriesColors (sliceColors);
         chart.addSeries ("First Class", result.get ("1"));
             chart.addSeries ("Second Class", result.get ("2"));
                 chart.addSeries ("Third Class", result.get ("3"));
                 chart.setTitle("Passengers Pclass Data");
                 new SwingWrapper (chart).displayChart ();
     
     
    }   
    @Override
     public void getPassengerSurvived(List<TitanicPassengers> L){
     Map<String, Long> result =L.stream ().collect (Collectors.groupingBy (TitanicPassengers::getSurvived, Collectors.counting () ) );
     PieChart chart = new PieChartBuilder ().width (800).height (600).title (getClass ().getSimpleName ()).build ();
     Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};
     chart.getStyler ().setSeriesColors (sliceColors);
         chart.addSeries ("Didn't Survive", result.get ("0"));
             chart.addSeries ("Survived", result.get ("1"));
             chart.setTitle("Passengers Survived Data");
                 new SwingWrapper (chart).displayChart ();
     
     
    } 
    @Override
      public void getPassengerSurvivedGender(List<TitanicPassengers> L){
     Map<String, Long> result =L.stream ().filter(r->"1".equals(r.getSurvived())).collect (Collectors.groupingBy (TitanicPassengers::getSex, Collectors.counting () ) );
     PieChart chart = new PieChartBuilder ().width (800).height (600).title (getClass ().getSimpleName ()).build ();
     Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};
     chart.getStyler ().setSeriesColors (sliceColors);
         chart.addSeries ("Female", result.get ("female"));
             chart.addSeries ("Male", result.get ("male"));
             chart.setTitle("Passengers Survived Data per each Gender");
                 new SwingWrapper (chart).displayChart ();
     } 
}
