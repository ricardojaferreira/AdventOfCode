package ProcessingInputs;

import java.util.*;

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
        Map<Integer, List<Integer>> mapOfShifts = this.getMapOfShifts();
        System.out.println(mapOfShifts.toString());

        int sleepingCount = 0;
        int sleepingGuardId = -1;
        for(Map.Entry<Integer, List<Integer>> entry: mapOfShifts.entrySet()){
            List<Integer> value = entry.getValue();
            if (sleepingCount<value.size()) {
                sleepingCount = value.size();
                sleepingGuardId = entry.getKey();
            }
        }

        int bestTime = -1;
        int occurences = 0;
        List<Integer> sleepingTimes = mapOfShifts.get(sleepingGuardId);
        for(int value: sleepingTimes) {
//            System.out.println("This value: " + value + "appears" + Collections.frequency(sleepingTimes,value));
            if (occurences<=Collections.frequency(sleepingTimes,value)) {
               occurences = Collections.frequency(sleepingTimes,value);
               bestTime = value;
            }
        }
//        System.out.println("Sleeping Guard: " + sleepingGuardId);
//        System.out.println("Best Time: " + bestTime);
        System.out.println("Answer: " + sleepingGuardId*bestTime);

    }

    public void guardSleepingMoreDuringMinute() {
        Map<Integer, List<Integer>> mapOfShifts = this.getMapOfShifts();
        Map<String,Integer> guardVsMinuteSleeping = new HashMap<>();
        int minute = 0;
        while (minute <= 59) {
            for(Map.Entry<Integer, List<Integer>> entry: mapOfShifts.entrySet()){
                List<Integer> value = entry.getValue();
                guardVsMinuteSleeping.put(minute+"_"+entry.getKey(),Collections.frequency(value,minute));
            }
            minute++;
        }

        String minuteGuard = "";
        int count = 0;
        for(Map.Entry<String, Integer> entry:guardVsMinuteSleeping.entrySet()) {
            if (count<=entry.getValue()) {
               count = entry.getValue();
               minuteGuard = entry.getKey();
            }
        }

        int minuteSleeping = Integer.parseInt(minuteGuard.substring(0,minuteGuard.indexOf("_")));
        int guardId = Integer.parseInt(minuteGuard.substring(minuteGuard.indexOf("_")+1));
        System.out.println("Answer: " + (guardId*minuteSleeping));

    }


    private Map<Integer, List<Integer>> getMapOfShifts() {
        Map<Integer, List<Integer>> mapOfShifts = new HashMap<>();

        Set s = this.orderedShifts.entrySet();
        Iterator i = s.iterator();
        int guardId = -1;
        String lastEntry = "";
        while (i.hasNext()) {
            Map.Entry m = (Map.Entry) i.next();
            GregorianCalendar date = (GregorianCalendar) m.getKey();
            String value = (String) m.getValue();

            if (value.contains("#")) {
                guardId = Integer.parseInt(value.substring(
                        value.indexOf('#')+1,
                        value.indexOf(
                                ' ',
                                value.indexOf('#')
                        )
                ));
                continue;
            }
            if (value.contains("asleep") && !lastEntry.contains("asleep")) {
                if (mapOfShifts.containsKey(guardId)) {
                    mapOfShifts.get(guardId).add(date.get(Calendar.MINUTE));
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(date.get(Calendar.MINUTE));
                    mapOfShifts.put(guardId,list);
                }
            }

            if (value.contains("up") && !lastEntry.contains("up")) {
                int fallAsleepAt = mapOfShifts.get(guardId).get(mapOfShifts.get(guardId).size()-1);
                int wakesUp = date.get(Calendar.MINUTE);
                while(fallAsleepAt<wakesUp-1) {
                    mapOfShifts.get(guardId).add(++fallAsleepAt);
                }
            }
            lastEntry = value;
        }

        return mapOfShifts;
    }
}
