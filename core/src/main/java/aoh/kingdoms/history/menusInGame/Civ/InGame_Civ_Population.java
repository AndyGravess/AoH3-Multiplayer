// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Civ;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menusInGame.InGame_Ranking;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_WithStatsFlags;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Value;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Data;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Civ_Population extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iSortID;
    public static boolean goBackToRank;
    
    public InGame_Civ_Population() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        int buttonY = buttonYPadding;
        final int buttonH = CFG.BUTTON_HEIGHT3;
        if (InGame_Civ_Population.goBackToRank) {
            if (InGame_Civ.iActiveCivID <= 0) {
                InGame_Civ.iActiveCivID = Game.player.iCivID;
            }
        }
        else if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
            InGame_Civ.iActiveCivID = Game.getProvince(Game.iActiveProvince).getCivID();
        }
        else if (InGame_Civ.iActiveCivID <= 0) {
            InGame_Civ.iActiveCivID = Game.player.iCivID;
        }
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.3f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        int c0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 4) / 3.0f);
        buttonX = paddingLeft + CFG.PADDING;
        final PieChart_Data nPieChartData = new PieChart_Data();
        final List<Integer> pieCivs = new ArrayList<Integer>();
        final List<Integer> pieData = new ArrayList<Integer>();
        final Civilization civ = Game.getCiv(InGame_Civ.iActiveCivID);
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            for (int j = 0; j < Game.getProvince(civ.getProvinceID(i)).getPopulationSize(); ++j) {
                boolean added = false;
                for (int k = pieCivs.size() - 1; k >= 0; --k) {
                    if (pieCivs.get(k).equals(Game.getProvince(civ.getProvinceID(i)).getPopulationCivID(j))) {
                        pieData.set(k, pieData.get(k) + Game.getProvince(civ.getProvinceID(i)).getPopulationID(j));
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    pieCivs.add(Game.getProvince(civ.getProvinceID(i)).getPopulationCivID(j));
                    pieData.add(Game.getProvince(civ.getProvinceID(i)).getPopulationID(j));
                }
            }
        }
        for (int i = pieCivs.size() - 1; i >= 0; --i) {
            nPieChartData.addPieChartValues(new PieChart_Value(pieCivs.get(i), pieData.get(i)));
        }
        final int pieDim = buttonH * 3 + CFG.PADDING * 2;
        menuElements.add(new PieChart_WithStatsFlags(buttonX, buttonY + CFG.PADDING, pieDim - CFG.PADDING * 2, pieDim - CFG.PADDING * 2, nPieChartData, null) {
            @Override
            protected void drawPieChart(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int nWidth_LEFT) {
                oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
                Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight() + CFG.PADDING * 2, 1.0f);
                oSB.setColor(Color.WHITE);
                super.drawPieChart(oSB, iTranslateX, iTranslateY, isActive, scrollableY, nPosX, nPosY, nWidth, nHeight, nWidth_LEFT);
            }
            
            @Override
            protected float animationPerc() {
                return Math.min(1.0f, (CFG.currentTimeMillis - InGame_ProvinceInfo.lTime) / 150.0f);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Population"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                for (int i = 0; i < this.pieChartData.getPieChartValuesSize() && i < 10; ++i) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.pieChartData.getCivID(i)).getCivName() + ": ", CFG.getNumberWithSpaces("" + CFG.getPrecision2(this.pieChartData.getPieChartValue(i).getValue(), 1)), this.pieChartData.getCivID(i), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING * 2;
        c0W = menuWidth - paddingLeft - buttonX;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ.iActiveCivID).getPopulationTotal()), Images.population, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.population).getWidth(), 0, CFG.FONT_BOLD_SMALL) {
            @Override
            public void actionElement() {
                if (InGame_Civ_Population.goBackToRank) {
                    InGame_Ranking.sSearch = "";
                    Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                }
                else {
                    Game.menuManager.rebuildInGame_Civ();
                }
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorPopulation(isActive, this.getIsHovered());
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (InGame_Civ_Population.goBackToRank) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Ranking"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rank, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ.iActiveCivID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click("" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getAverageGrowthRate(), 100) + "%", Images.populationGrowth, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.population).getWidth(), 0) {
            @Override
            public void actionElement() {
                if (InGame_Civ_Population.goBackToRank) {
                    InGame_Ranking.sSearch = "";
                    Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                }
                else {
                    Game.menuManager.rebuildInGame_Civ();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (InGame_Civ_Population.goBackToRank) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Ranking"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rank, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GrowthRate") + ": ", CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getAverageGrowthRate(), 100) + "%", Images.populationGrowth, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).fManpowerMax, 1)), Game_Calendar.IMG_MANPOWER, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.population).getWidth(), 0) {
            @Override
            public void actionElement() {
                if (InGame_Civ_Population.goBackToRank) {
                    InGame_Ranking.sSearch = "";
                    Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                }
                else {
                    Game.menuManager.rebuildInGame_Civ();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (InGame_Civ_Population.goBackToRank) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Ranking"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rank, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).fManpowerMax, 1)), Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Population.iSortID == 0 || InGame_Civ_Population.iSortID == 1, InGame_Civ_Population.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Population.iSortID == 0) {
                    InGame_Civ_Population.iSortID = 1;
                }
                else {
                    InGame_Civ_Population.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_Civ_Population();
                InGame_Civ_Population.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Name"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Population.iSortID == 2 || InGame_Civ_Population.iSortID == 3, InGame_Civ_Population.iSortID == 3, Game.lang.get("Population"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Population.iSortID == 2) {
                    InGame_Civ_Population.iSortID = 3;
                }
                else {
                    InGame_Civ_Population.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_Civ_Population();
                InGame_Civ_Population.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Population"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Population.iSortID == 4 || InGame_Civ_Population.iSortID == 5, InGame_Civ_Population.iSortID == 5, Game.lang.get("GrowthRate"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Population.iSortID == 4) {
                    InGame_Civ_Population.iSortID = 5;
                }
                else {
                    InGame_Civ_Population.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_Civ_Population();
                InGame_Civ_Population.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("GrowthRate"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_Population.iSortID == 6 || InGame_Civ_Population.iSortID == 7, InGame_Civ_Population.iSortID == 7, Game.lang.get("Manpower"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_Population.iSortID == 6) {
                    InGame_Civ_Population.iSortID = 7;
                }
                else {
                    InGame_Civ_Population.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_Civ_Population();
                InGame_Civ_Population.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MaximumManpower"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.3f);
        r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.2f);
        final List<Integer> tProvinces = new ArrayList<Integer>();
        for (int l = 0; l < Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces(); ++l) {
            if (!Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(l)).isOccupied()) {
                tProvinces.add(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(l));
            }
        }
        while (tProvinces.size() > 0) {
            int toAddID = 0;
            if (InGame_Civ_Population.iSortID == 0) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Population.iSortID == 1) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Population.iSortID == 2) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal() < Game.getProvince(tProvinces.get(o)).getPopulationTotal()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Population.iSortID == 3) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal() > Game.getProvince(tProvinces.get(o)).getPopulationTotal()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Population.iSortID == 4) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getGrowthRateWithBonuses() < Game.getProvince(tProvinces.get(o)).getGrowthRateWithBonuses()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Population.iSortID == 5) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getGrowthRateWithBonuses() > Game.getProvince(tProvinces.get(o)).getGrowthRateWithBonuses()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Population.iSortID == 6) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getManpowerMaxFromProvinceManpowerLvl(tProvinces.get(toAddID)) < Game.getManpowerMaxFromProvinceManpowerLvl(tProvinces.get(o))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Civ_Population.iSortID == 7) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getManpowerMaxFromProvinceManpowerLvl(tProvinces.get(toAddID)) > Game.getManpowerMaxFromProvinceManpowerLvl(tProvinces.get(o))) {
                        toAddID = o;
                    }
                }
            }
            buttonX = paddingLeft;
            menuElements.add(new Text_StaticBG_ID(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.iActiveProvince == this.getCurrent()) {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.setVisibleInGame_Civ(false);
                        Game.setActiveProvinceID(this.getCurrent());
                        Game.menuManager.rebuildInGame_ProvinceInfo(true);
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                    else {
                        Game.mapCoords.centerToProvinceID(this.getCurrent());
                        Game.setActiveProvinceID(this.getCurrent());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverProvince(this.id);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getNumberWithSpaces("" + Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverPopulation(this.getCurrent(), false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2(Game.getProvince(tProvinces.get(toAddID)).getGrowthRateWithBonuses(), 100) + "%", CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverPopulationGrowth(this.getCurrent(), false, false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + Game.getManpowerMaxFromProvinceManpowerLvl(tProvinces.get(toAddID)), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverManpower(this.getCurrent(), false, false);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            tProvinces.remove(toAddID);
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Game.lang.get("Population"), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_Civ.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_Civ_Population.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Civ_Population.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Civ_Population.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Civ_Population.lTime = CFG.currentTimeMillis;
        InGame_Civ_Population.lTime2 = InGame_Civ_Population.lTime;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_Civ_Population.lTime = 0L;
        InGame_Civ_Population.lTime2 = 0L;
        InGame_Civ_Population.iSortID = 2;
        InGame_Civ_Population.goBackToRank = false;
    }
}
