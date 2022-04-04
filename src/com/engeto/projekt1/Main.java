package com.engeto.projekt1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws StateException {
        final String fileNameMain = "vat-eu.csv";
        final String line = "====================";
        final String delimiter = "\t";
        final String n = "\n";
        //final String vatOver20Main = "vat-over-20.txt";

        System.out.println(line);
        StatesReadingFromFile states1 = new StatesReadingFromFile();
        try {
            states1.loadStatesFromFile(fileNameMain);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getLocalizedMessage());
        }
//2
        System.out.println("výstup aplikace č.2 - výpis:");
        System.out.println(line);

        for (StateInfo s : states1.getStatesFromFile()) {
            System.out.println(s.getStateName() + " (" + s.getStateAbbreviation() + "): " + s.getFullIncomeTax() + " %");
        }
        System.out.println(n);
//3,4

        System.out.println("výstup aplikace č.3 a 4 - výpis států s daní nad 20% bez speciální sazby seřazený sestupně:");
        System.out.println(n);
        states1.compareVAT();
        String outputFile1 = "";
        for (StateInfo s : states1.getStatesFromFile()) {
            if (s.getFullIncomeTax() > 20 && s.isHasSpecialTax() == false) {
                System.out.println(s.getStateName() + " (" + s.getStateAbbreviation() + "): "
                        + s.getFullIncomeTax() + " % ("+s.getLoweredIncomeTax()+"%)");
                outputFile1+= (s.getStateName() + " (" + s.getStateAbbreviation() + "):"+delimiter
                        + s.getFullIncomeTax() + " % ("+s.getLoweredIncomeTax()+"%)\n");
            }
        }
        System.out.println(line);

//5.
        String shout = "Sazba VAT 20 % nebo nižší nebo používají speciální sazbu:";
        for (StateInfo s : states1.getStatesFromFile()) {
            if (s.getFullIncomeTax() <= 20) {
                shout+=" " + s.getStateAbbreviation() + ",";

            } else if (s.isHasSpecialTax() == true){
                shout+=" " + s.getStateAbbreviation() + ",";
            }
        }
        shout +=".";
        StringBuffer sb= new StringBuffer(shout);
        sb.deleteCharAt(sb.length()-2);
        System.out.println(sb);
        System.out.println(n);

        System.out.println("výstup aplikace č.6 \n - nahrání do souboru");
//6.
        String outputFile = outputFile1+"\n"+sb;
        try (PrintWriter writer = new PrintWriter(new FileWriter("vat-over-20.txt"))) {
            writer.print(outputFile);
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(n);
        System.out.println("výstup z aplikace č.7 - zadávání z klávesnice");
//7.

        System.out.println(n);
        System.out.println("Enter VAT value or press enter");

        Scanner fromKeyboardScan = new Scanner(System.in);
        String value = fromKeyboardScan.nextLine();
        int defaultVAT = 20;
        int enteredNumber = 0;
        if(value.equals("")) {
            enteredNumber = defaultVAT;
        } else {
            enteredNumber = Integer.parseInt(value);
        }
        System.out.println("Searching for states with VAT above: "+enteredNumber +"%.");

        for (StateInfo s : states1.getStatesFromFile()) {
            if (s.getFullIncomeTax() > enteredNumber) {
                System.out.println(s.getStateName() + " (" + s.getStateAbbreviation() + "): "
                        + s.getFullIncomeTax() + " % ("+s.getLoweredIncomeTax()+"%)");
             }
        }

        try{
            String generatedFileName = "vat-over-"+enteredNumber+".txt";
            File generatedVATFile = new File(generatedFileName);
            String outputFile2 = "";
            if (generatedVATFile.createNewFile()) {
                System.out.println("Generated file: " + generatedFileName);
            } else {
                System.out.println("Generated file already exists: " + generatedFileName);
            }
            for (StateInfo s : states1.getStatesFromFile()) {
                if (s.getFullIncomeTax() > enteredNumber) {
                    outputFile2 += (s.getStateName() + " (" + s.getStateAbbreviation() + "):" + delimiter
                            + s.getFullIncomeTax() + " % (" + s.getLoweredIncomeTax() + "%)\n");
                    try (PrintWriter writer = new PrintWriter(new FileWriter(generatedFileName))) {
                        writer.print(outputFile2);
                    } catch (FileNotFoundException e) {
                        System.err.println(e.getLocalizedMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    }
                }
            } catch(IOException e){
                System.err.println(e.getLocalizedMessage());}
        }
}
