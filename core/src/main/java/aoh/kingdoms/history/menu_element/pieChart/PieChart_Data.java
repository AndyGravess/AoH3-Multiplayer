// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.pieChart;

import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import java.util.List;

public class PieChart_Data
{
    private List<PieChart_Value> pieChartValues;
    private int pieChartValuesSize;
    
    public PieChart_Data() {
        this.pieChartValues = new ArrayList<PieChart_Value>();
        this.pieChartValuesSize = 0;
    }
    
    public float getPieChartValue_ColorR(final int i) {
        return Game.getCiv(this.getPieChartValue(i).getDataID()).getR();
    }
    
    public float getPieChartValue_ColorG(final int i) {
        return Game.getCiv(this.getPieChartValue(i).getDataID()).getG();
    }
    
    public float getPieChartValue_ColorB(final int i) {
        return Game.getCiv(this.getPieChartValue(i).getDataID()).getB();
    }
    
    public int getCivID(final int i) {
        return this.getPieChartValue(i).getDataID();
    }
    
    public final void sortAndBuild_PieChartValues() {
        final List<PieChart_Value> tempPieChartValues = new ArrayList<PieChart_Value>();
        float countedValues = 0.0f;
        for (int i = 0; i < this.getPieChartValuesSize(); ++i) {
            tempPieChartValues.add(this.getPieChartValue(i));
            countedValues += (float)this.getPieChartValue(i).getValue();
        }
        this.pieChartValues.clear();
        while (tempPieChartValues.size() > 0) {
            int nMinID = 0;
            for (int j = 1; j < tempPieChartValues.size(); ++j) {
                if (tempPieChartValues.get(nMinID).getValue() < tempPieChartValues.get(j).getValue()) {
                    nMinID = j;
                }
            }
            this.pieChartValues.add(tempPieChartValues.get(nMinID));
            tempPieChartValues.remove(nMinID);
        }
        for (int i = 0; i < this.getPieChartValuesSize(); ++i) {
            this.getPieChartValue(i).setPercentage((float)(this.getPieChartValue(i).getValue() * 100.0 / countedValues));
        }
    }
    
    public final void addPieChartValues(final PieChart_Value nPieChartValue) {
        this.pieChartValues.add(nPieChartValue);
        this.pieChartValuesSize = this.pieChartValues.size();
    }
    
    public final PieChart_Value getPieChartValue(final int i) {
        return this.pieChartValues.get(i);
    }
    
    public final int getPieChartValuesSize() {
        return this.pieChartValuesSize;
    }
}
