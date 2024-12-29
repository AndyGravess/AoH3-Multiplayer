// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.graph;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.menusInGame.Goods.InGame_Goods_LargestProducers;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightReligion;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Religion;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import java.util.List;

public class Graph_Vertical_Data
{
    private int iCivID;
    private List<Graph_Vertical_Data_Value> lValues;
    private boolean inView;
    private long lTime;
    private static final int ANIMATION_TIME = 300;
    
    public Graph_Vertical_Data(final int iCivID) {
        this.lValues = new ArrayList<Graph_Vertical_Data_Value>();
        this.inView = true;
        this.lTime = 0L;
        this.iCivID = iCivID;
    }
    
    protected final void buildContinentData() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add(0);
        }
        try {
            for (int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                final int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                numOfProvincesByContinents.set(tID, numOfProvincesByContinents.get(tID) + 1);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int j = 0; j < numOfProvincesByContinents.size(); ++j) {
                if (numOfProvincesByContinents.get(j) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(j), j));
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int k = 1; k < tempValues.size(); ++k) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(k).getValue()) {
                    tempMaxID = k;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_Population() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add(0);
        }
        try {
            for (int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                final int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                numOfProvincesByContinents.set(tID, numOfProvincesByContinents.get(tID) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getPopulationTotal());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int j = 0; j < numOfProvincesByContinents.size(); ++j) {
                if (numOfProvincesByContinents.get(j) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(j), j));
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int k = 1; k < tempValues.size(); ++k) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(k).getValue()) {
                    tempMaxID = k;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_GovernmentCivs() {
        this.lValues.clear();
        final List<Integer> populationData = new ArrayList<Integer>();
        populationData.add((int)Game.getCiv(this.iCivID).getPopulationTotal());
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int i = 0; i < populationData.size(); ++i) {
                if (populationData.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(populationData.get(i), i));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int j = 1; j < tempValues.size(); ++j) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(j).getValue()) {
                    tempMaxID = j;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_ReligionCivs() {
        this.lValues.clear();
        final List<Integer> populationReligion = new ArrayList<Integer>();
        populationReligion.add(0);
        try {
            for (int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                if (Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getReligion() == InGame_Civ_Religion.iReligionID) {
                    populationReligion.set(0, populationReligion.get(0) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getPopulationTotal());
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int j = 0; j < populationReligion.size(); ++j) {
                if (populationReligion.get(j) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(populationReligion.get(j), j));
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int k = 1; k < tempValues.size(); ++k) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(k).getValue()) {
                    tempMaxID = k;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_ReligionCivs_Right() {
        this.lValues.clear();
        final List<Integer> populationReligion = new ArrayList<Integer>();
        populationReligion.add(0);
        try {
            for (int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                if (Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getReligion() == InGame_RightReligion.iReligionID) {
                    populationReligion.set(0, populationReligion.get(0) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getPopulationTotal());
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int j = 0; j < populationReligion.size(); ++j) {
                if (populationReligion.get(j) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(populationReligion.get(j), j));
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int k = 1; k < tempValues.size(); ++k) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(k).getValue()) {
                    tempMaxID = k;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_ConstructedBuildings() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add(0);
        }
        try {
            for (int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                final int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                numOfProvincesByContinents.set(tID, numOfProvincesByContinents.get(tID) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).iBuildingsSize);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int j = 0; j < numOfProvincesByContinents.size(); ++j) {
                if (numOfProvincesByContinents.get(j) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(j), j));
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int k = 1; k < tempValues.size(); ++k) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(k).getValue()) {
                    tempMaxID = k;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_Infrastructure() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add(0);
        }
        try {
            for (int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                final int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                numOfProvincesByContinents.set(tID, numOfProvincesByContinents.get(tID) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getInfrastructure());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int j = 0; j < numOfProvincesByContinents.size(); ++j) {
                if (numOfProvincesByContinents.get(j) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(j), j));
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int k = 1; k < tempValues.size(); ++k) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(k).getValue()) {
                    tempMaxID = k;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_UnlockedTechnologies() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        numOfProvincesByContinents.add(Game.getCiv(this.iCivID).getResearchedTechnologies());
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if (numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(i), i));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int j = 1; j < tempValues.size(); ++j) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(j).getValue()) {
                    tempMaxID = j;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_Prestige() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        numOfProvincesByContinents.add((int)Game.getCiv(this.iCivID).iCivRankScore);
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if (numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(i), i));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int j = 1; j < tempValues.size(); ++j) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(j).getValue()) {
                    tempMaxID = j;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_CivsProvinces() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        numOfProvincesByContinents.add(Game.getCiv(this.iCivID).getNumOfProvinces());
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if (numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(i), i));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int j = 1; j < tempValues.size(); ++j) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(j).getValue()) {
                    tempMaxID = j;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_CivsPopulation() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        numOfProvincesByContinents.add((int)Game.getCiv(this.iCivID).getPopulationTotal());
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if (numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(i), i));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int j = 1; j < tempValues.size(); ++j) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(j).getValue()) {
                    tempMaxID = j;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_ResourceProduction() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        numOfProvincesByContinents.add((int)ResourcesManager.getProducedGoods_ResourceCiv(this.iCivID, InGame_Goods_LargestProducers.RESOURCE_ID));
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if (numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(i), i));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int j = 1; j < tempValues.size(); ++j) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(j).getValue()) {
                    tempMaxID = j;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_RegimentsLimit() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        numOfProvincesByContinents.add(Game.getCiv(this.iCivID).iRegimentsLimit);
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if (numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(i), i));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int j = 1; j < tempValues.size(); ++j) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(j).getValue()) {
                    tempMaxID = j;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void buildContinentData_Economy() {
        this.lValues.clear();
        final List<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        final List<Float> FloatNumOfProvincesByContinents = new ArrayList<Float>();
        for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
            FloatNumOfProvincesByContinents.add(0.0f);
        }
        try {
            for (int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                final int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                FloatNumOfProvincesByContinents.set(tID, FloatNumOfProvincesByContinents.get(tID) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getEconomyWithBonuses());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add((int)(float)FloatNumOfProvincesByContinents.get(i - 1));
        }
        final List<Graph_Vertical_Data_Value> tempValues = new ArrayList<Graph_Vertical_Data_Value>();
        try {
            for (int j = 0; j < numOfProvincesByContinents.size(); ++j) {
                if (numOfProvincesByContinents.get(j) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value(numOfProvincesByContinents.get(j), j));
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int k = 1; k < tempValues.size(); ++k) {
                if (tempValues.get(tempMaxID).getValue() < tempValues.get(k).getValue()) {
                    tempMaxID = k;
                }
            }
            this.lValues.add(tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }
    
    protected final void drawData(final SpriteBatch oSB, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<Color> nColors, final List<Integer> tSorted) {
        if (this.lTime == 0L) {
            this.lTime = CFG.currentTimeMillis;
        }
        int tempValuesHeight = 0;
        if (this.lTime + 300L > CFG.currentTimeMillis) {
            int tempHeight = 0;
            for (int i = 0; i < this.lValues.size(); ++i) {
                tempHeight += this.lValues.get(i).getHeight();
            }
            tempHeight = (tempValuesHeight = (int)(tempHeight * ((CFG.currentTimeMillis - this.lTime) / 300.0f)));
            int i = 0;
            int tempAnimationHeight = 0;
            while (i < this.lValues.size()) {
                if (this.lValues.get(i).getValue() > 0) {
                    try {
                        this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempAnimationHeight, (tempHeight >= this.lValues.get(i).getHeight()) ? this.lValues.get(i).getHeight() : tempHeight, nColors.get(tSorted.get(this.lValues.get(i).getDataTypeID())));
                    }
                    catch (final Exception ex) {
                        this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempAnimationHeight, (tempHeight >= this.lValues.get(i).getHeight()) ? this.lValues.get(i).getHeight() : tempHeight, Color.WHITE);
                    }
                    tempAnimationHeight += this.lValues.get(i).getHeight();
                    tempHeight -= this.lValues.get(i).getHeight();
                    if (tempHeight <= 0) {
                        break;
                    }
                }
                ++i;
            }
        }
        else {
            for (int j = 0; j < this.lValues.size(); ++j) {
                if (this.lValues.get(j).getValue() > 0) {
                    try {
                        this.lValues.get(j).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, nColors.get(tSorted.get(this.lValues.get(j).getDataTypeID())));
                    }
                    catch (final Exception ex2) {
                        this.lValues.get(j).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, Color.WHITE);
                    }
                    tempValuesHeight += this.lValues.get(j).getHeight();
                }
            }
        }
        oSB.setColor(Color.WHITE);
        try {
            Game.getCiv(this.iCivID).getFlag().draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        catch (final Exception ex3) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
    }
    
    protected final void drawDataTextValue(final SpriteBatch oSB, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        Renderer.drawTextRotated(oSB, "" + this.getValue(), iPosX + iWidth / 2 - CFG.TEXT_HEIGHT / 2, iPosY + iHeight - CFG.PADDING, new Color(1.0f, 1.0f, 1.0f, 0.85f), 90.0f);
    }
    
    protected final void drawDataTextValue_Splitted(final SpriteBatch oSB, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        Renderer.drawTextRotated(oSB, "" + this.getValue() / 100.0f, iPosX + iWidth / 2 - CFG.TEXT_HEIGHT / 2, iPosY + iHeight - CFG.PADDING, new Color(1.0f, 1.0f, 1.0f, 0.45f), 90.0f);
    }
    
    protected final void drawDataTextValue_Short(final SpriteBatch oSB, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        try {
            Renderer.drawTextRotated(oSB, "" + CFG.getShortNumber(this.getValue()), iPosX + iWidth / 2 - CFG.TEXT_HEIGHT / 2, iPosY + iHeight - CFG.PADDING, new Color(1.0f, 1.0f, 1.0f, 0.45f), 90.0f);
        }
        catch (final Exception ex) {}
    }
    
    protected final void buildHeights(final int nGraphHeight, final int nMaxValue) {
        for (int i = 0; i < this.lValues.size(); ++i) {
            this.lValues.get(i).setHeight((int)(this.lValues.get(i).getValue() / (float)nMaxValue * nGraphHeight));
        }
    }
    
    public final int getCivID() {
        return this.iCivID;
    }
    
    protected final int getValue() {
        int tOut = 0;
        for (int i = 0; i < this.lValues.size(); ++i) {
            tOut += this.lValues.get(i).getValue();
        }
        return tOut;
    }
    
    protected final boolean getInView() {
        return this.inView;
    }
    
    protected final void setInView(final boolean inView) {
        this.inView = inView;
    }
    
    protected final void resetAnimation() {
        this.lTime = 0L;
    }
    
    protected final int getValuesSize() {
        return this.lValues.size();
    }
    
    protected final int getValue(final int i) {
        return this.lValues.get(i).getValue();
    }
    
    protected final int getValueDataTypeID(final int i) {
        return this.lValues.get(i).getDataTypeID();
    }
}
