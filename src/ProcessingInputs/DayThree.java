package ProcessingInputs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayThree {
    private List<String> processInput;
    private Map<String,Integer> mapPositions = new HashMap<>();
    private Map<Integer,List<String>> coordinates = new HashMap<>();


//    public DayThree(List<String> processInput) {
//        this.processInput = processInput;
//        this.removeIds();
//        System.out.println(this.processInput.toString());
//    }

    public DayThree(List<String> processInput) {
        this.processInput = processInput;
        this.removeSpaces();
        System.out.println(this.processInput.toString());
    }

    private void removeSpaces() {
        List<String> temp = new ArrayList<>();
        for(String value:this.processInput) {
            String aux = value.replaceAll("\\s+","");
            temp.add(aux);
        }
        this.processInput = new ArrayList<>(temp);
    }

    private void removeIds() {
        List<String> temp = new ArrayList<>();
        for(String value:this.processInput) {
            int atPosition = value.indexOf('@');
            String aux = value.substring(atPosition+1);
            aux = aux.replaceAll("\\s+","");
            temp.add(aux);
        }
        this.processInput = new ArrayList<>(temp);
    }

    private void populateMapPositions(int fromLeft, int toLeft, int fromTop, int toTop) {
        for(int i=fromTop;i<fromTop+toTop;i++) {
            for(int j=fromLeft;j<fromLeft+toLeft;j++) {
                String mapPosition = i + "," + j;
                if (this.mapPositions.containsKey(mapPosition)) {
                    this.mapPositions.put(mapPosition,2);
                } else {
                    this.mapPositions.put(mapPosition,1);
                }
            }
        }
    }

    private void populateMapPositions2(int id, int fromLeft, int toLeft, int fromTop, int toTop) {
        List<String> coordsAux = new ArrayList<>();
        for(int i=fromTop;i<fromTop+toTop;i++) {
            for(int j=fromLeft;j<fromLeft+toLeft;j++) {
                String mapPosition = i + "," + j;
                coordsAux.add(mapPosition);
                if (this.mapPositions.containsKey(mapPosition)) {
                    this.mapPositions.put(mapPosition,2);
                } else {
                    this.mapPositions.put(mapPosition,1);
                }
            }
        }
        this.coordinates.put(id,coordsAux);
    }

    public int getIdNoOverlaps() {
        int finalId = 0;

        for(String value:this.processInput) {
            int arroba = value.indexOf('@');
            int commaPos = value.indexOf(',');
            int colonPos = value.indexOf(':');
            int timesPos = value.indexOf('x');

            int id = Integer.parseInt(value.substring(1,arroba));
            int fromLeft = Integer.parseInt(value.substring(arroba+1,commaPos));
            int toLeft = Integer.parseInt(value.substring(colonPos+1,timesPos));
            int fromTop = Integer.parseInt(value.substring(commaPos+1,colonPos));
            int toTop = Integer.parseInt(value.substring(timesPos+1));

            this.populateMapPositions2(id,fromLeft,toLeft,fromTop,toTop);
        }

        for (Map.Entry<Integer,List<String>> idAndCoords: this.coordinates.entrySet()) {
            boolean isThisOne = true;
            List<String> coordsLocal = idAndCoords.getValue();
            for (String coord:coordsLocal) {
                if (this.mapPositions.get(coord) == 1) {
                    continue;
                }
                if (this.mapPositions.get(coord) == 2) {
                    isThisOne=false;
                }
            }
            if(isThisOne) {
                finalId = idAndCoords.getKey();
            }
        }

        return finalId;
    }

    public int getOverlapClaims() {
        int count = 0;
        for(String value:this.processInput) {
            int commaPos = value.indexOf(',');
            int colonPos = value.indexOf(':');
            int timesPos = value.indexOf('x');

            int fromLeft = Integer.parseInt(value.substring(0,commaPos));
            int toLeft = Integer.parseInt(value.substring(colonPos+1,timesPos));
            int fromTop = Integer.parseInt(value.substring(commaPos+1,colonPos));
            int toTop = Integer.parseInt(value.substring(timesPos+1));

            this.populateMapPositions(fromLeft,toLeft,fromTop,toTop);
        }

        for(Map.Entry<String, Integer> entry: this.mapPositions.entrySet()){
            int value = entry.getValue();
            if(value==2) {
                count++;
            }
        }
        return count;
    }
}
