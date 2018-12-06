package ProcessingInputs;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class DayFour {
    private SortedMap<GregorianCalendar,String> orderedShifts = new TreeMap<>();

    public DayFour(List<String> processInput) {
        this.orderShifts(processInput); //Not cool to process things on the constructor but it is faster to have the ordered list
    }

    private void orderShifts(List<String> processInput) {
        for (String entry: processInput) {
            int year = Integer.parseInt(entry.substring(1,5));
            int month = Integer.parseInt(entry.substring(6,8));
            int day = Integer.parseInt(entry.substring(9,11));
            int hour = Integer.parseInt(entry.substring(12,14));
            int minute = Integer.parseInt(entry.substring(15,17));
            String text = entry.substring(19);
            GregorianCalendar date = new GregorianCalendar(year,month,day,hour,minute);
            this.orderedShifts.put(date,text);
        }
    }

    public void printTreeMap() {
        Set s = this.orderedShifts.entrySet();

        // Using iterator in SortedMap
        Iterator i = s.iterator();

        // Traversing map. Note that the traversal
        // produced sorted (by keys) output .
        while (i.hasNext())
        {
            Map.Entry m = (Map.Entry)i.next();

            GregorianCalendar key = (GregorianCalendar) m.getKey();
            String value = (String)m.getValue();

            System.out.println("Key : " +
                    key.get(Calendar.YEAR) + "-" + key.get(Calendar.MONTH) + "-" + key.get(Calendar.DATE) + " "
                            + key.get(Calendar.HOUR) + ":" + key.get(Calendar.MINUTE) + ":" + key.get(Calendar.SECOND) +
                    "  value : " + value);
        }
    }

    public void getSleepingGuardAndMinute() {
        Map<String, List<Integer>> mapOfShifts = new HashMap<>();

        Set s = this.orderedShifts.entrySet();
        Iterator i = s.iterator();
        while (i.hasNext()) {
            Map.Entry m = (Map.Entry) i.next();
            GregorianCalendar date = (GregorianCalendar) m.getKey();
            String value = (String) m.getValue();

//            String guardID = value.


//            if(mapOfShifts.containsKey(date.get()))

        }

    }
}
