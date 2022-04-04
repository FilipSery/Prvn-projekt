package com.engeto.projekt1;
import java.io.*;
import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StatesReadingFromFile {
    private final String fileName = "vat-eu.csv";
    private final String delimiter = "\t";
    private final String line = "\n";
    private ArrayList<StateInfo> stateList = new ArrayList<>();

    public void loadStatesFromFile(String fileName2) throws StateException {
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (s.hasNext()) {
                String fromFile = s.nextLine();
                String[] stateInformations = fromFile.split(delimiter);
                String stateAbbreviation = stateInformations[0];
                String stateName = stateInformations[1];
                int fullIncomeTax = Integer.parseInt(stateInformations[2]);
                String replace = stateInformations[3].replace(",", ".");
                double loweredIncomeTax = Double.parseDouble(replace);
                boolean hasSpecialTax = Boolean.parseBoolean(stateInformations[4]);

                StateInfo sI = new StateInfo(stateAbbreviation,stateName,fullIncomeTax,loweredIncomeTax,hasSpecialTax);
                stateList.add(sI);
                }
            } catch(FileNotFoundException e){
                throw new StateException("Input file: " + fileName + " wasn't found " + e.getLocalizedMessage());
            } catch (NumberFormatException e) {
                throw new StateException("Incorrect number format: "+e.getLocalizedMessage());
            }
    }
    public void saveStatesToFile(String fileName) throws StateException {
        //int numberOfLine = 0;
        try (PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (StateInfo s : stateList) {
                String toFile = s.getStateAbbreviation();
                toFile += delimiter + s.getStateName();
                toFile += delimiter + s.getFullIncomeTax();
                toFile += delimiter + s.getLoweredIncomeTax();
                toFile += delimiter + s.isHasSpecialTax() + line;

            }
        } catch (IOException e) {
            throw new StateException("Couldn't write to file: " + fileName + e.getLocalizedMessage());
        }
    }
    public void compareVAT() {
            Collections.sort(stateList, new VatComparator());
    }
    public ArrayList<StateInfo> getStatesFromFile() {
        return new ArrayList<>(stateList);
    }
}
