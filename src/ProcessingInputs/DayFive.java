package ProcessingInputs;

import javax.print.DocFlavor;
import java.util.List;

public class DayFive {
    private String polarity;

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
}
