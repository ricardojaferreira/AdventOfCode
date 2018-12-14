package ProcessingInputs;

import java.util.ArrayList;
import java.util.List;

public class DayFive {
    private String polarity;
    private List<Integer> minimumPolymers = new ArrayList<>();

    public DayFive(List<String> processInput) {
        polarity = processInput.get(0);
        System.out.println(polarity);
    }

    public void processPolarities() {
        int end = polarity.length();
        for (int i=0;i<end-1;i++) {
            if(this.polarity.charAt(i) == this.polarity.charAt(i+1)+32 ||
                    this.polarity.charAt(i) == this.polarity.charAt(i+1)-32
            ) {
                String first = this.polarity.substring(0,i);
                String second = this.polarity.substring(i+2);

                this.polarity = first+second;
                end = this.polarity.length();
                i=-1;
            }
        }

        System.out.println(this.polarity);
        System.out.println("Answer: " + this.polarity.length());
    }


    public void calculateMinumumPolymers() {
        int i = 65; //Ascii code of A
        //90 is the Ascii code of Z
        while(i<=90) {
            String strippedPolarity = this.removeCharFromString(i);
            int lengthAfterReaction = this.processStripedPolarities(strippedPolarity);
            this.minimumPolymers.add(lengthAfterReaction);
            i++;
        }

        System.out.println(this.minimumPolymers); //Debug

        int minimumLength = getMinimumValueFromList(this.minimumPolymers);

        System.out.println("Answer: " + minimumLength);
    }

    private int getMinimumValueFromList(List<Integer> list) {
        int minimum = list.get(0);
        for (int m:list) {
            if (minimum>=m) {
               minimum = m;
            }
        }

        return minimum;
    }

    private String removeCharFromString(int asciiCode) {
        StringBuilder strippedPolarity = new StringBuilder();
        for (int i=0;i<this.polarity.length();i++) {
            if (this.polarity.charAt(i) != asciiCode && this.polarity.charAt(i) != asciiCode+32) {
                strippedPolarity.append(this.polarity.charAt(i));
            }
        }
        return strippedPolarity.toString();
    }

    private int processStripedPolarities(String strippedPolarities) {
        int end = strippedPolarities.length();
        for (int i=0;i<end-1;i++) {
            if(strippedPolarities.charAt(i) == strippedPolarities.charAt(i+1)+32 ||
                    strippedPolarities.charAt(i) == strippedPolarities.charAt(i+1)-32
            ) {
                String first = strippedPolarities.substring(0,i);
                String second = strippedPolarities.substring(i+2);

                strippedPolarities = first+second;
                end = strippedPolarities.length();
                i=-1;
            }
        }

        return strippedPolarities.length();
    }

}
