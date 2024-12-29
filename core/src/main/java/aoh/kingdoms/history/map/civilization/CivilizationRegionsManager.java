// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization;

import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.math.Matrix4;
import java.util.List;

public class CivilizationRegionsManager
{
    public static List<Boolean> isProvinceAssigned;
    private static Renderer_CivRegionNames oRenderer_CivRegionNames;
    public static boolean updateRegionsInView;
    public static float civsRegionsAlpha;
    public static Matrix4 oldTransformMatrix;
    private static List<Integer> lRegions_Civs;
    private static List<List<Integer>> lRegions_Civs_RegionsID;
    public static int NUM_OF_REGIONS_IN_VIEW;
    public static boolean drawHideAnimation;
    public static float CIVILIZATION_NAMES_ALPHA;
    public static long CIVILIZATIONS_NAMES_TIME;
    public static long CIVILIZATIONS_NAMES_TIME_HIDE;
    
    public static final void buildCivilizationsRegions() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).clearCivRegions_Just();
        }
        CivilizationRegionsManager.isProvinceAssigned.clear();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).setCivRegionID(-1);
            CivilizationRegionsManager.isProvinceAssigned.add(false);
        }
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() != 0 && !CivilizationRegionsManager.isProvinceAssigned.get(i)) {
                updateIsProvinceAssigned(i, true);
                Game.getCiv(Game.getProvince(i).getCivID()).createCivilizationRegion(i);
            }
        }
        CivilizationRegionsManager.isProvinceAssigned.clear();
        buildCivilizationsRegions_TextOver();
    }
    
    public static final void buildCivilizationsRegion(final int nCivID) {
        try {
            Game.getCiv(nCivID).clearCivRegions_Just();
            CivilizationRegionsManager.isProvinceAssigned.clear();
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                CivilizationRegionsManager.isProvinceAssigned.add(false);
            }
            for (int i = 0; i < Game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).setCivRegionID(-1);
            }
            for (int i = 0; i < Game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                if (!CivilizationRegionsManager.isProvinceAssigned.get(Game.getCiv(nCivID).getProvinceID(i))) {
                    Game.getCiv(nCivID).createCivilizationRegion(Game.getCiv(nCivID).getProvinceID(i));
                    updateIsProvinceAssigned(Game.getCiv(nCivID).getProvinceID(i), true);
                }
            }
            CivilizationRegionsManager.isProvinceAssigned.clear();
            Renderer.addSimpleTaskCivsNames(new Game.SimpleTask("" + nCivID, nCivID) {
                @Override
                public void update() {
                    CivilizationRegionsManager.buildCivilizationsRegions_TextOver(this.id);
                }
            });
        }
        catch (final Exception ex) {}
    }
    
    public static void updateIsProvinceAssigned(final int nProvinceID, final boolean nIsProvinceAssigned) {
        try {
            CivilizationRegionsManager.isProvinceAssigned.set(nProvinceID, nIsProvinceAssigned);
        }
        catch (final Exception ex) {}
    }
    
    public static final void buildCivilizationsRegions_TextOver() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            buildCivilizationsRegions_TextOver(i);
        }
    }
    
    public static final void buildCivilizationsRegions_TextOver(final int iCivID) {
        for (int j = 0; j < Game.getCiv(iCivID).getCivRegionsSize(); ++j) {
            Game.getCiv(iCivID).getCivRegion(j).buildRegionPath_TriedToUse();
            Game.getCiv(iCivID).getCivRegion(j).buildRegionPath();
        }
    }
    
    public static final void drawCivNames(final SpriteBatch oSB) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            if (CivilizationRegionsManager.drawHideAnimation) {
                if (CivilizationRegionsManager.updateRegionsInView) {
                    CivilizationRegionsManager.oRenderer_CivRegionNames.update();
                    CivilizationRegionsManager.updateRegionsInView = false;
                }
                drawCivNames_Begin(oSB);
                drawCivRegions_Names_UpdateTimeHide();
                CivilizationRegionsManager.oRenderer_CivRegionNames.draw(oSB);
                drawCivNames_End(oSB);
            }
            else {
                CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME = 0L;
            }
        }
        else {
            if (CivilizationRegionsManager.updateRegionsInView) {
                CivilizationRegionsManager.oRenderer_CivRegionNames.update();
                CivilizationRegionsManager.updateRegionsInView = false;
            }
            drawCivNames_Begin(oSB);
            drawCivRegions_Names_UpdateTime();
            CivilizationRegionsManager.oRenderer_CivRegionNames.draw(oSB);
            drawCivNames_End(oSB);
            CivilizationRegionsManager.drawHideAnimation = true;
            CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME_HIDE = 0L;
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static final synchronized void drawCivNames_Begin(final SpriteBatch oSB) {
        CivilizationRegionsManager.civsRegionsAlpha = CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA * (1.0f + 0.25f * (1.0f - Game.mapScale.getCurrentScale()));
        CivilizationRegionsManager.oldTransformMatrix = oSB.getTransformMatrix().cpy();
        Renderer.fontBorder.get(0).setColor(new Color(1.0f, 1.0f, 1.0f, CivilizationRegionsManager.civsRegionsAlpha));
    }
    
    public static final void drawCivNames_End(final SpriteBatch oSB) {
        oSB.setTransformMatrix(CivilizationRegionsManager.oldTransformMatrix);
    }
    
    public static final void updateRenderer_CivNames() {
        if (!Game.menuManager.getInInitGameMenu() && !Game.menuManager.getInInitGame_Menus() && !Game.menuManager.getInMainMenu() && !Game.menuManager.getInLoadGamesList() && !Game.menuManager.getInScenarios_NewGame() && !Game.menuManager.getInGameLegacies() && !Game.menuManager.getInLoadScenario()) {
            if (Game.settingsManager.SETTINGS_CIV_NAMES == 2) {
                CivilizationRegionsManager.oRenderer_CivRegionNames = new Renderer_CivRegionNames() {
                    @Override
                    public void draw(final SpriteBatch oSB) {
                        CivilizationRegionsManager.drawCivRegions_Names2(oSB);
                    }
                    
                    @Override
                    public void update() {
                        updateRegionsInView2();
                    }
                };
            }
            else if (Game.settingsManager.SETTINGS_CIV_NAMES == 1) {
                CivilizationRegionsManager.oRenderer_CivRegionNames = new Renderer_CivRegionNames() {
                    @Override
                    public void draw(final SpriteBatch oSB) {
                        CivilizationRegionsManager.drawCivRegions_Names2_Low(oSB);
                    }
                    
                    @Override
                    public void update() {
                        updateRegionsInView2();
                    }
                };
            }
            else {
                CivilizationRegionsManager.oRenderer_CivRegionNames = new Renderer_CivRegionNames() {
                    @Override
                    public void draw(final SpriteBatch oSB) {
                    }
                    
                    @Override
                    public void update() {
                    }
                };
            }
        }
        else {
            CivilizationRegionsManager.oRenderer_CivRegionNames = new Renderer_CivRegionNames() {
                @Override
                public void draw(final SpriteBatch oSB) {
                }
                
                @Override
                public void update() {
                }
            };
        }
    }
    
    private static final void updateRegionsInView2() {
        CivilizationRegionsManager.lRegions_Civs.clear();
        CivilizationRegionsManager.lRegions_Civs_RegionsID.clear();
        try {
            final int[] tempCivs = new int[Game.getCivsSize()];
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                processProvince(Game.getProvinceInViewID(i), tempCivs);
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                processProvince(Game.getExtraProvinceInViewID(i), tempCivs);
            }
            CivilizationRegionsManager.NUM_OF_REGIONS_IN_VIEW = CivilizationRegionsManager.lRegions_Civs.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private static void processProvince(final int provinceID, final int[] tempCivs) {
        if (Game.getProvince(provinceID).getCivID() > 0) {
            final int civID = Game.getProvince(provinceID).getCivID();
            int civIndex = tempCivs[civID];
            if (civIndex > 0) {
                if (!CivilizationRegionsManager.lRegions_Civs_RegionsID.get(civIndex - 1).contains(Game.getProvince(provinceID).getCivRegionID())) {
                    CivilizationRegionsManager.lRegions_Civs_RegionsID.get(civIndex - 1).add(Game.getProvince(provinceID).getCivRegionID());
                }
            }
            else {
                CivilizationRegionsManager.lRegions_Civs.add(civID);
                civIndex = CivilizationRegionsManager.lRegions_Civs.size();
                tempCivs[civID] = civIndex;
                CivilizationRegionsManager.lRegions_Civs_RegionsID.add(new ArrayList<Integer>());
                if (Game.getProvince(provinceID).getCivRegionID() >= 0) {
                    CivilizationRegionsManager.lRegions_Civs_RegionsID.get(civIndex - 1).add(Game.getProvince(provinceID).getCivRegionID());
                }
            }
        }
    }
    
    public static final void drawCivRegions_Names_UpdateTime() {
        if (CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME == 0L) {
            CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME = CFG.currentTimeMillis;
            CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA = 0.0f;
        }
        else if (CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA < Game.settingsManager.CIV_NAMES_TEXT_ALPHA) {
            CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA = Game.settingsManager.CIV_NAMES_TEXT_ALPHA * ((CFG.currentTimeMillis - CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME) / (float)Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL);
            if (CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA > Game.settingsManager.CIV_NAMES_TEXT_ALPHA) {
                CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA = Game.settingsManager.CIV_NAMES_TEXT_ALPHA;
            }
        }
    }
    
    public static final void drawCivRegions_Names_UpdateTimeHide() {
        if (CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME_HIDE == 0L) {
            CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME_HIDE = CFG.currentTimeMillis;
            CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA = Game.settingsManager.CIV_NAMES_TEXT_ALPHA;
        }
        else {
            CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA = Game.settingsManager.CIV_NAMES_TEXT_ALPHA - Game.settingsManager.CIV_NAMES_TEXT_ALPHA * ((CFG.currentTimeMillis - CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME_HIDE) / (Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL / 2.0f));
            if (CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA <= 0.0f) {
                CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA = 0.0f;
                CivilizationRegionsManager.drawHideAnimation = false;
            }
        }
    }
    
    protected static final void drawCivRegions_Names2(final SpriteBatch oSB) {
        try {
            for (int i = 0; i < CivilizationRegionsManager.lRegions_Civs_RegionsID.size(); ++i) {
                try {
                    for (int j = CivilizationRegionsManager.lRegions_Civs_RegionsID.get(i).size() - 1; j >= 0; --j) {
                        try {
                            Game.getCiv(CivilizationRegionsManager.lRegions_Civs.get(i)).getCivRegion(CivilizationRegionsManager.lRegions_Civs_RegionsID.get(i).get(j)).drawCivRegion(oSB);
                        }
                        catch (final IndexOutOfBoundsException ex) {
                            updateRegionsInView2();
                            break;
                        }
                        catch (final Exception ex2) {
                            CFG.exceptionStack(ex2);
                        }
                    }
                }
                catch (final Exception exr) {
                    CFG.exceptionStack(exr);
                }
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
    }
    
    protected static final void drawCivRegions_Names2_Low(final SpriteBatch oSB) {
        try {
            for (int i = 0; i < CivilizationRegionsManager.NUM_OF_REGIONS_IN_VIEW; ++i) {
                try {
                    for (int j = CivilizationRegionsManager.lRegions_Civs_RegionsID.get(i).size() - 1; j >= 0; --j) {
                        try {
                            Game.getCiv(CivilizationRegionsManager.lRegions_Civs.get(i)).getCivRegion(CivilizationRegionsManager.lRegions_Civs_RegionsID.get(i).get(j)).drawCivRegion_Low(oSB);
                        }
                        catch (final IndexOutOfBoundsException ex) {
                            updateRegionsInView2();
                            break;
                        }
                        catch (final Exception ex2) {
                            CFG.exceptionStack(ex2);
                        }
                    }
                }
                catch (final Exception exr) {
                    CFG.exceptionStack(exr);
                }
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
    }
    
    static {
        CivilizationRegionsManager.isProvinceAssigned = new ArrayList<Boolean>();
        CivilizationRegionsManager.updateRegionsInView = true;
        CivilizationRegionsManager.civsRegionsAlpha = 1.0f;
        CivilizationRegionsManager.lRegions_Civs = new ArrayList<Integer>();
        CivilizationRegionsManager.lRegions_Civs_RegionsID = new ArrayList<List<Integer>>();
        CivilizationRegionsManager.NUM_OF_REGIONS_IN_VIEW = 0;
        CivilizationRegionsManager.drawHideAnimation = false;
        CivilizationRegionsManager.CIVILIZATION_NAMES_ALPHA = 0.1f;
        CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME = 0L;
        CivilizationRegionsManager.CIVILIZATIONS_NAMES_TIME_HIDE = 0L;
    }
    
    public interface Renderer_CivRegionNames
    {
        void draw(final SpriteBatch p0);
        
        void update();
    }
}
