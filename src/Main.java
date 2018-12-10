import ProcessingInputs.DayFour;
import ProcessingInputs.DayOne;
import ProcessingInputs.DayThree;
import ProcessingInputs.DayTwo;
import utils.ReadFiles;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String fileName = "";
        List<String> myList;


        System.out.println("---------------------------------");
        System.out.println("Hello Welcome to the first puzzle");
        System.out.print("Choose your input: ");
        fileName = s.nextLine();
        System.out.println("File to open is: " + fileName);
        ReadFiles input = new ReadFiles(fileName);
        myList = input.getListFromFile();
//        DayOne pInputs = new DayOne(myList);
//        int finalV = pInputs.processFrequecies(0);
//        int repeatedValue = pInputs.processRepeatedFrequencies(null);
//        System.out.println(repeatedValue);

//        DayTwo pInputs = new DayTwo(myList);
//        String common = pInputs.getCommonLetters();
//        System.out.println("Common letters: " + common);

//        DayThree day3 = new DayThree(myList);
//        int id = day3.getIdNoOverlaps();
//
//        System.out.println("Id is: " + id);

        DayFour day4 = new DayFour(myList);
        day4.printTreeMap();
        day4.getSleepingGuardAndMinute();


    }
}
