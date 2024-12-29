// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.pieChart;

public class PieChart_Value
{
    private int iDataID;
    private double fValue;
    private float fPercentage;
    
    public PieChart_Value(final int iDataID, final float fValue) {
        this.fValue = 1.0;
        this.iDataID = iDataID;
        this.fValue = fValue;
    }
    
    public final int getDataID() {
        return this.iDataID;
    }
    
    public final double getValue() {
        return this.fValue;
    }
    
    public final float getPercentage() {
        return this.fPercentage;
    }
    
    public final void setPercentage(final float fPercentage) {
        this.fPercentage = fPercentage;
    }
}
