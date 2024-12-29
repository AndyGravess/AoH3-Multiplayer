// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import java.util.TreeMap;

public class RomanNumber
{
    private static final TreeMap<Integer, String> map;
    
    public static final String getRoman(final int number) {
        try {
            final int l = RomanNumber.map.floorKey(number);
            if (number == l) {
                return RomanNumber.map.get(number);
            }
            return RomanNumber.map.get(l) + getRoman(number - l);
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    static {
        (map = new TreeMap<Integer, String>()).put(1000, "M");
        RomanNumber.map.put(900, "CM");
        RomanNumber.map.put(500, "D");
        RomanNumber.map.put(400, "CD");
        RomanNumber.map.put(100, "C");
        RomanNumber.map.put(90, "XC");
        RomanNumber.map.put(50, "L");
        RomanNumber.map.put(40, "XL");
        RomanNumber.map.put(10, "X");
        RomanNumber.map.put(9, "IX");
        RomanNumber.map.put(5, "V");
        RomanNumber.map.put(4, "IV");
        RomanNumber.map.put(1, "I");
    }
}
