// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.menu_element.graph.Graph;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_WithStatsFlags;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Value;
import aoh.kingdoms.history.menu_element.pieChart.PieChart_Data;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Formable;
import aoh.kingdoms.history.menu_element.button.ButtonFlag;
import aoh.kingdoms.history.mainGame.SoundsManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_Stats extends Menu
{
    public InGame_Court_Stats() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING;
        menuElements.add(new ButtonCurrentSituation(Game.lang.get(GameValues.court.COUNCIL_NAME), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.council).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                InGame_Court.iActiveCivID = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
                Game.setRegroupArmyMode(false);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        final int statW = menuWidth - paddingLeft * 2;
        final int statH = CFG.BUTTON_HEIGHT3;
        buttonY += CFG.PADDING;
        buttonX = paddingLeft2;
        menuElements.add(new ButtonFlag(Game.player.iCivID, buttonX, buttonY, true) {
            @Override
            public int getFlagCivID() {
                return Game.player.iCivID;
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        final int bHeight = (ButtonFlag_Formable.getButtonHeight() - CFG.PADDING) / 2;
        menuElements.add(new Text_StaticBG_RulerTitle(Game.getCiv(Game.player.iCivID).getCivName(), buttonX, buttonY, menuWidth - buttonX - paddingLeft2, bHeight) {});
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Provinces") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(Game.player.iCivID).getNumOfProvinces()), Images.provinces, buttonX, buttonY + bHeight + CFG.PADDING, menuWidth - buttonX - paddingLeft2, bHeight, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
        });
        buttonX = paddingLeft;
        buttonY += ButtonFlag_Formable.getButtonHeight() + CFG.PADDING;
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY - (ButtonFlag_Formable.getButtonHeight() + CFG.PADDING * 2), menuWidth - paddingLeft * 2, ButtonFlag_Formable.getButtonHeight() + CFG.PADDING * 2));
        buttonY += CFG.PADDING;
        float tValue = (Game.getCiv(Game.player.iCivID).getNumOfProvinces() / (float)Math.max(1, Game.player.playerStats.startingProvinces) - 1.0f) * 100.0f;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Provinces") + ": ", ((tValue > 0.0f) ? "+" : "") + CFG.getPrecision2(tValue, 100) + "%", Images.provinces, buttonX, buttonY, statW, statH, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float tValue = (Game.getCiv(Game.player.iCivID).getNumOfProvinces() / (float)Math.max(1, Game.player.playerStats.startingProvinces) - 1.0f) * 100.0f;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", ((tValue > 0.0f) ? "+" : "") + CFG.getPrecision2(tValue, 100) + "%", Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game_Calendar.getDate_ByTurnID(1), Images.time, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", CFG.getNumberWithSpaces("" + Game.player.playerStats.startingProvinces), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game_Calendar.getCurrentDate(), Images.time, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(Game.player.iCivID).getNumOfProvinces()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game_Calendar.TURN_ID != 1) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("PlayingTime") + ": ", Game_Calendar.getNumOfDates_ByTurnID(1), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("PlayingTime") + ": ", Game_Calendar.getNumOfDates_ByTurnID(1), Images.time, buttonX, buttonY, statW, statH, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        buttonX = paddingLeft + CFG.PADDING;
        final PieChart_Data nPieChartData = new PieChart_Data();
        final List<Integer> pieCivs = new ArrayList<Integer>();
        final List<Integer> pieData = new ArrayList<Integer>();
        final Civilization civ = Game.getCiv(Game.player.iCivID);
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
                return Math.min(1.0f, (CFG.currentTimeMillis - this.lTime) / 150.0f);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Population"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                for (int i = 0; i < this.pieChartData.getPieChartValuesSize(); ++i) {
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
        final int c0W = menuWidth - paddingLeft - buttonX;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(CFG.getNumberWithSpaces("" + Game.getCiv(Game.player.iCivID).getPopulationTotal()), Images.population, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.population).getWidth(), 0, CFG.FONT_BOLD_SMALL) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorPopulation(isActive, this.getIsHovered());
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(Game.player.iCivID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click("" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getAverageGrowthRate(), 100) + "%", Images.populationGrowth, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.population).getWidth(), 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GrowthRate") + ": ", CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getAverageGrowthRate(), 100) + "%", Images.populationGrowth, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpowerMax, 1)), Game_Calendar.IMG_MANPOWER, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.population).getWidth(), 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpowerMax, 1)), Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        final double tValuePop = (Game.getCiv(Game.player.iCivID).getPopulationTotal() / (float)Math.max(200L, Game.player.playerStats.startingPopulation) - 1.0f) * 100.0f;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Population") + ": ", ((tValuePop > 0.0) ? "+" : "") + CFG.getPrecision2(tValuePop, 100) + "%", Images.population, buttonX, buttonY, statW, statH, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_RIGHT;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final double tValue = (Game.getCiv(Game.player.iCivID).getPopulationTotal() / (float)Math.max(200L, Game.player.playerStats.startingPopulation) - 1.0f) * 100.0f;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", ((tValue > 0.0) ? "+" : "") + CFG.getPrecision2(tValue, 100) + "%", Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game_Calendar.getDate_ByTurnID(1), Images.time, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.player.playerStats.startingPopulation), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game_Calendar.getCurrentDate(), Images.time, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(Game.player.iCivID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game_Calendar.TURN_ID != 1) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("PlayingTime") + ": ", Game_Calendar.getNumOfDates_ByTurnID(1), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonY += CFG.PADDING;
        menuElements.add(new Graph(Game.lang.get("Population"), Game.lang.get("Population"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, (menuWidth - paddingLeft * 2) * 4 / 10, true, 1, Graph.GraphType.PLAYER_POPULATION, false));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        tValue = (Game.getCiv(Game.player.iCivID).getEconomyTotal() / Math.max(1.0f, Game.player.playerStats.startingEconomy) - 1.0f) * 100.0f;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Economy") + ": ", ((tValue > 0.0f) ? "+" : "") + CFG.getPrecision2(tValue, 100) + "%", Game_Calendar.IMG_ECONOMY, buttonX, buttonY, statW, statH, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float tValue = (Game.getCiv(Game.player.iCivID).getEconomyTotal() / Math.max(1.0f, Game.player.playerStats.startingEconomy) - 1.0f) * 100.0f;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", ((tValue > 0.0f) ? "+" : "") + CFG.getPrecision2(tValue, 100) + "%", Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game_Calendar.getDate_ByTurnID(1), Images.time, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.player.playerStats.startingEconomy, 10)), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("", "" + Game_Calendar.getCurrentDate(), Images.time, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getEconomyTotal(), 10)), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game_Calendar.TURN_ID != 1) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("PlayingTime") + ": ", Game_Calendar.getNumOfDates_ByTurnID(1), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Graph(Game.lang.get("Income"), Game.lang.get("Income"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, (menuWidth - paddingLeft * 2) * 4 / 10, true, 1, Graph.GraphType.PLAYER_INCOME, false));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Wars") + ": ", "" + CFG.getNumberWithSpaces("" + Game.player.playerStats.numOfWars), Images.war, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("RecruitedArmy") + ", " + Game.lang.get("Regiments") + ": ", "" + CFG.getNumberWithSpaces("" + Game.player.playerStats.recruitedRegiments), Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Generals") + ": ", "" + CFG.getPrecision2((float)Game.player.playerStats.recruitedGenerals, 1), Images.general, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Advisors") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData3.getRecruitedAdvisors(), 1), Images.council, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Provinces"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ConqueredProvinces") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(Game.player.iCivID).eventsData3.getConqueredProvinces()), Images.provinces, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncreasedTaxEfficiency") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).eventsData.getIncreasedTaxEfficiency() / 100.0f, 10), Images.tax, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("InvestedInEconomy") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).eventsData.getInvestedInEconomy() / 100.0f, 10), Game_Calendar.IMG_ECONOMY, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("DevelopedInfrastructure") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData.getDevelopedInfrastructure(), 10), Images.infrastructure, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncreasedPopulationGrowthRate") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).eventsData.getIncreasedGrowthRate() / 100.0f, 10), Images.populationGrowth, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncreasedManpower") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).eventsData.getIncreasedManpower() / 100.0f, 10), Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Buildings"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("BuildingsConstructed") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData2.getBuildingsConstructed(), 1), Images.buildings, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("AdministrativeBuildingsConstructed") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData2.getAdministrativeBuildingsConstructed(), 1), Images.build, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("EconomyBuildingsConstructed") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData2.getEconomyBuildingsConstructed(), 1), Game_Calendar.IMG_ECONOMY, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MilitaryBuildingsConstructed") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData2.getMilitaryBuildingsConstructed(), 1), Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("UniqueCapitalBuildingsConstructed") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData2.getCapitalBuildingsConstructed(), 1), Images.capital, buttonX, buttonY, statW, statH, maxIconW));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("More"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonY += CFG.PADDING;
        menuElements.add(new Graph(Game.lang.get("Prestige"), Game.lang.get("Prestige"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, (menuWidth - paddingLeft * 2) * 4 / 10, true, 1, Graph.GraphType.PLAYER_PRESTIGE, false));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Graph(Game.lang.get("Balance"), Game.lang.get("Balance"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, (menuWidth - paddingLeft * 2) * 4 / 10, true, 1, Graph.GraphType.PLAYER_BALANCE, false));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("Statistics"));
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Court.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Court.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - InGame_CourtOptions.menuH + iTranslateY, this.getWidth(), this.getHeight() + InGame_CourtOptions.menuH + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Court.lTime = CFG.currentTimeMillis;
        InGame_Court.lTime2 = CFG.currentTimeMillis;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }
}
