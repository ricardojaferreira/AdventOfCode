package ProcessingInputs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DaySix {
    private List<String> rawCoordinateList = new ArrayList<>();

    public DaySix(List<String> rawCoordinateList) {
        this.rawCoordinateList = rawCoordinateList;
    }

    private Map<Integer, String> giveIdsToCoordinates() {
        Map<Integer, String> map = new HashMap<>();
        AtomicInteger i = new AtomicInteger();
        this.rawCoordinateList
                .forEach((value) -> map.put(i.incrementAndGet(),value));
        return map;
    }


    List<Integer> getMaxXandMaxY(Map<Integer, String> map) {
        List<Integer> list = new ArrayList<>();
        List<Integer> listX = new ArrayList<>();
        List<Integer> listY = new ArrayList<>();
        map.values().forEach(s-> {
            listX.add(Integer.parseInt(s.substring(0, s.indexOf(','))));
            listY.add(Integer.parseInt(s.substring(s.indexOf(',')+2)));
        });

        list.add(listX.stream().max(Comparator.naturalOrder()).get());
        list.add(listY.stream().max(Comparator.naturalOrder()).get());

        return list;
    }

    public void getMaxNotInfiniteArea() {
        Map<Integer, String> myMap = this.giveIdsToCoordinates();
        List<Integer> maxXY = getMaxXandMaxY(myMap);
        System.out.println(maxXY.toString());
    }
}
