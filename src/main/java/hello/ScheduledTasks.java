package hello;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws FileNotFoundException {
        CSVReader reader = new CSVReader(new FileReader("test.csv"), ',', '\"', 1);

        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(Hello.class);
        String[] columns = new String[] {"message", "count"}; // the fields to bind do in your JavaBean
        strat.setColumnMapping(columns);

        CsvToBean csv = new CsvToBean();
        List list = csv.parse(strat, reader);
        // System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
