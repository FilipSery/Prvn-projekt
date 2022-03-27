package com.engeto.projekt1;

import java.util.Comparator;

public class VatComparator implements Comparator<StateInfo> {
    @Override
    public int compare(StateInfo o1, StateInfo o2) {
        return (o1.getFullIncomeTax()>o2.getFullIncomeTax()) ? -1 :
                (o1.getFullIncomeTax()<o2.getFullIncomeTax()) ? 1 : 0;
    }
}
