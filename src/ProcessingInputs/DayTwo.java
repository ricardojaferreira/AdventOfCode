package ProcessingInputs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DayTwo {
    private final static byte TWO_OCCURRENCES = 0x2; //0010
    private final static byte THREE_OCCURRENCES = 0x4; //0100

    private List<String> processInput;

    public DayTwo(List<String> processInput) {
        this.processInput = processInput;
    }

    private int countOcurrences(char letter, String word)
    {
        int result = 1; //the letter itself is an occurrence
        for(int i=1;i<word.length();i++) {
            char letterAt = word.charAt(i);
            if(letter == letterAt) {
                result++;
            }
        }
        return result;
    }

    /**
     * This function can return the following:
     * 0010 - It has 1 letter with exactly two occurrences
     * 0100 - It has 1 letter with exactly three occurrences
     * 0110 - It has 1 letter with exactly two occurences and 1 letter with exactly three
     * @param boxId
     * @return
     */
    private byte processBoxId(String boxId)
    {
        byte result = 0x0;
        List<Character> processedLetters = new ArrayList<>();

        if (boxId == null || boxId.isEmpty()) {
            return 0;
        }
        for (int i=0;i<boxId.length();i++) {
            String currentString = boxId.substring(i);
            char currentLetter = currentString.charAt(0);
            if(processedLetters.contains(currentLetter)){
                continue;
            }
            int occurences = this.countOcurrences(currentLetter, currentString);

            switch (occurences) {
                case 2:
                    result|=TWO_OCCURRENCES;
                    break;

                case 3:
                     result|=THREE_OCCURRENCES;
                     break;
            }

            if((TWO_OCCURRENCES|THREE_OCCURRENCES) == result) {
                break;
            }
            processedLetters.add(currentLetter);
        }
        return result;
    }

    public int processCheckSum() {
        int numberOfTwos = 0;
        int numberOfThrees = 0;
        for (String boxId: this.processInput) {
            byte aux = this.processBoxId(boxId);
            switch (aux) {
                case TWO_OCCURRENCES:
                    numberOfTwos++;
                    break;
                case THREE_OCCURRENCES:
                    numberOfThrees++;
                    break;
                case TWO_OCCURRENCES|THREE_OCCURRENCES:
                    numberOfTwos++;
                    numberOfThrees++;
                    break;
            }
        }
        //DEBUG
//        System.out.println("Number of twos is: " + numberOfTwos);
//        System.out.println("Number of threes is: " + numberOfThrees);
        return numberOfTwos*numberOfThrees;
    }

    private String getSimilarBoxId(String boxId, List<String> list) {
        for (String comparingId:list) {
            if(comparingId.length() != boxId.length()) {
                continue;
            }
            int differentLetters=0;
            for (int i=0;i<boxId.length();i++) {
                char currentLetterA = boxId.charAt(i);
                char currentLetterB = comparingId.charAt(i);
                if(currentLetterA!=currentLetterB) {
                    differentLetters++;
                }
                if(differentLetters>1){
                    break;
                }
            }
            if(differentLetters==1){
                return comparingId;
            }
        }
        return "";
    }

    private String removeDifferentLetter(String first, String second)
    {
        StringBuilder result = new StringBuilder();
        for (int i=0;i<first.length();i++) {
            if(first.charAt(i) == second.charAt(i)) {
                result.append(first.charAt(i));
            }
        }
        return result.toString();
    }

    public String getCommonLetters() {
        String boxIdA = "";
        String boxIdB = "";
        String commonLetters = "";
        List<String> processedInputs = new ArrayList<>();
        List<String> auxList = new ArrayList<>(this.processInput);
        for(String boxId:this.processInput) {
            processedInputs.add(boxId);
            auxList.removeAll(processedInputs);
            boxIdA = this.getSimilarBoxId(boxId,auxList);
            if(!boxIdA.isEmpty()) {
                boxIdB = boxId;
                break;
            }
        }
        commonLetters = removeDifferentLetter(boxIdA,boxIdB);
        return commonLetters;

    }

}
