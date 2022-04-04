package com.engeto.projekt1;

import javax.swing.plaf.nimbus.State;

public class StateInfo {
    private String stateAbbreviation;
    private String stateName;
    private int fullIncomeTax;
    private double loweredIncomeTax;
    private boolean hasSpecialTax;

    public StateInfo(String stateAbbreviation, String stateName, int fullIncomeTax, double loweredIncomeTax, boolean hasSpecialTax) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.fullIncomeTax = fullIncomeTax;
        this.loweredIncomeTax = loweredIncomeTax;
        this.hasSpecialTax = hasSpecialTax;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getFullIncomeTax() {
        return fullIncomeTax;
    }

    public void setFullIncomeTax(int fullIncomeTax) {
        this.fullIncomeTax = fullIncomeTax;
    }

    public double getLoweredIncomeTax() {
        return loweredIncomeTax;
    }

    public void setLoweredIncomeTax(double loweredIncomeTax) {
        this.loweredIncomeTax = loweredIncomeTax;
    }

    public boolean isHasSpecialTax() {
        return hasSpecialTax;
    }

    public void setHasSpecialTax(boolean hasSpecialTax) {
        this.hasSpecialTax = hasSpecialTax;
    }
    public String stateInfo () {
        return stateName+" ("+stateAbbreviation+"): "+fullIncomeTax;
    }
}
