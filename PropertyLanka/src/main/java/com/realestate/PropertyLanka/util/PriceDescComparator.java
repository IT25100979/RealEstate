package com.realestate.PropertyLanka.util;

import com.realestate.PropertyLanka.model.Property;
import java.util.Comparator;

public class PriceDescComparator implements Comparator<Property> {
    @Override
    public int compare(Property p1, Property p2) {
        return Double.compare(p2.getPrice(), p1.getPrice());
    }
}
