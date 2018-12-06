package ProcessingInputs;

import java.util.ArrayList;
import java.util.List;

public class DayOne {
    private List<String> inputList;

    public DayOne(List<String> inputList) {
        this.inputList = inputList;
    }

    private int processValueLine(String value, int in) {
        switch (value.substring(0,1)) {
            case "+":
                in+=Integer.parseInt(value.substring(1));
                break;
            case "-":
                in-=Integer.parseInt(value.substring(1));
                break;
        }
        return in;
    }

    public int processFrequencies(int offset) {
        if(this.inputList.isEmpty()) {
            return 0;
        }
        for(String value: this.inputList) {
            offset=this.processValueLine(value, offset);
        }

        return offset;
    }

    public int processRepeatedFrequencies(List<Integer> valuesProcessed) {
        int currentValue = 0;
        if(valuesProcessed != null && !valuesProcessed.isEmpty()){
            currentValue = valuesProcessed.get(valuesProcessed.size()-1);
        } else {
            valuesProcessed = new ArrayList<>();
            valuesProcessed.add(currentValue);
        }

        for(String value: this.inputList) {
            currentValue=this.processValueLine(value, currentValue);
            if(valuesProcessed.contains(currentValue)) {
                return currentValue;
            }
            valuesProcessed.add(currentValue);
        }

        return processRepeatedFrequencies(valuesProcessed);
    }


}
