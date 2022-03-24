package com.engeto.projekt1;
import java.io.*;
import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class StatesReadingFromFile {
    private final String File_Name = "vat-eu.csv";
    private final String Delimiter = "\t";
    ArrayList<StateInfo> stateList = new ArrayList<>();

    public void loadPlantsFromFile (String fileName) throws StateException {
        String delimiter = "\t";
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (s.hasNext()) {
                String fromFile = s.nextLine();
                String[] stateInformations = fromFile.split(Delimiter);
                String stateAbbreviation;
                String stateName;
                int fullIncomeTax;
                int loweredIncomeTax;
                boolean hasSpecialTax;
                try {
                } catch (StateException e) {
                    throw new StateException()
                            ("Unavailable file (" + fileName + "), " + e.getLocalizedMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new StateException("Input file: "+fileName+" wasn't found"+e.getLocalizedMessage());
        }
    }
}
