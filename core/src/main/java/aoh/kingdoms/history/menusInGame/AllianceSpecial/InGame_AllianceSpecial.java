// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.AllianceSpecial;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.Button_HRE_Reform_Red;
import aoh.kingdoms.history.menu_element.button.Button_HRE_Reform_Green;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu_element.button.Button_HRE_Reform;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Special;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Rank;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu_element.button.ButtonGame_Image;
import aoh.kingdoms.history.menu_element.button.ButtonFlagRect;
import aoh.kingdoms.history.menusInGame.InGame_CivBonuses;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_TitleSpecial_ID;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.map.allianceHRE.HREManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title_AllianceSpecial;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_AllianceSpecial;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Horizontal;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmperor;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.allianceHRE.Alliance;
import aoh.kingdoms.history.menu_element.button.ButtonRuler_Diplomacy;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_AllianceSpecial extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int allianceID;
    public static boolean showFirstTier;
    public static boolean showSecondTier;
    public boolean leaveAllianceConfirm;
    
    public InGame_AllianceSpecial(final int nAllianceID) {
        this.leaveAllianceConfirm = false;
        InGame_AllianceSpecial.allianceID = nAllianceID;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = CFG.PADDING;
        int buttonX = paddingLeft;
        final int topHeight = ButtonRuler_Diplomacy.getButtonHeight() + CFG.PADDING * 2;
        RulersManager.loadRulerIMG_DiplomacyLeft(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID);
        int statsY;
        buttonY = (statsY = buttonY + CFG.PADDING);
        final int topTitleH = CFG.BUTTON_HEIGHT3 - CFG.PADDING * 2;
        menuElements.add(new ButtonRuler_Diplomacy(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID, menuWidth - ButtonRuler_Diplomacy.getButtonWidth() - paddingLeft, statsY) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
                super.actionElement();
            }
        });
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmperor(Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_Leader) + ": ", Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCivName(), CFG.FONT_REGULAR, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING - ButtonRuler_Diplomacy.getButtonWidth(), topTitleH, Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID) {
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID() >= 0) {
                    if (Game.iActiveProvince == Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID()) {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.setVisibleInGame_Civ(false);
                        Game.setActiveProvinceID(Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID());
                        Game.menuManager.rebuildInGame_ProvinceInfo(true);
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.mapCoords.centerToProvinceID(Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID());
                        Game.setActiveProvinceID(Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                }
            }
        });
        int statsY2;
        statsY = (statsY2 = statsY + (topTitleH + CFG.PADDING));
        final int innerWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING - ButtonRuler_Diplomacy.getButtonWidth() - CFG.PADDING) / 2;
        menuElements.add(new TextIcon2_Horizontal((Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID() >= 0) ? Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID()).getProvinceName() : "", Images.capital, buttonX, statsY, innerWidth, topTitleH, ImageManager.getImage(Images.capital).getWidth() + CFG.PADDING * 2) {
            @Override
            public void buildElementHover() {
                if (Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID() >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID, Game.lang.get("Capital") + ": " + Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID()).getProvinceName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                else {
                    this.menuElementHover = null;
                }
            }
            
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID() >= 0) {
                    if (Game.iActiveProvince == Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID()) {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.setVisibleInGame_Civ(false);
                        Game.setActiveProvinceID(Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID());
                        Game.menuManager.rebuildInGame_ProvinceInfo(true);
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.mapCoords.centerToProvinceID(Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID());
                        Game.setActiveProvinceID(Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                }
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonFlag_AllianceSpecial(InGame_AllianceSpecial.allianceID, buttonX + innerWidth / 2 - ButtonFlag_AllianceSpecial.getButtonWidth() / 2, statsY + (ButtonRuler_Diplomacy.getButtonHeight() - topTitleH * 2 - CFG.PADDING * 2) / 2 - ButtonFlag_AllianceSpecial.getButtonHeight() / 2) {
            @Override
            public void actionElement() {
            }
        });
        final int innerWidth2 = (innerWidth - CFG.PADDING) / 2;
        final int statH2 = (ButtonRuler_Diplomacy.getButtonHeight() - topTitleH - CFG.PADDING * 2) / 2;
        menuElements.add(new TextIcon2("" + CFG.getNumberWithSpaces("" + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getNumOfCivilizations()), this.getCivilizationsIMG(), buttonX + innerWidth + CFG.PADDING, statsY2, innerWidth2, statH2) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title_AllianceSpecial(InGame_AllianceSpecial.allianceID, Game.lang.get("Civilizations") + ": " + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getNumOfCivilizations()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Civilizations") + ": ", CFG.getNumberWithSpaces("" + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getNumOfCivilizations()), InGame_AllianceSpecial.this.getCivilizationsIMG(), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextIcon2("" + CFG.getNumberWithSpaces("" + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getProvinces()), Images.provinces, buttonX + innerWidth + CFG.PADDING * 2 + innerWidth2, statsY2, innerWidth2, statH2) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title_AllianceSpecial(InGame_AllianceSpecial.allianceID, Game.lang.get("Civilizations") + ": " + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getNumOfCivilizations()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", CFG.getNumberWithSpaces("" + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getProvinces()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        statsY2 += statH2 + CFG.PADDING;
        menuElements.add(new TextIcon2("" + CFG.getShortNumber(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getPopulation()), Images.population, buttonX + innerWidth + CFG.PADDING, statsY2, innerWidth2, statH2) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title_AllianceSpecial(InGame_AllianceSpecial.allianceID, Game.lang.get("Civilizations") + ": " + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getNumOfCivilizations()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getPopulation()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorPopulation(isActive, this.getIsHovered());
            }
        });
        menuElements.add(new TextIcon2("" + CFG.getShortNumber(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getEconomy()), Game_Calendar.IMG_ECONOMY, buttonX + innerWidth + CFG.PADDING * 2 + innerWidth2, statsY2, innerWidth2, statH2) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title_AllianceSpecial(InGame_AllianceSpecial.allianceID, Game.lang.get("Civilizations") + ": " + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getNumOfCivilizations()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getNumberWithSpaces("" + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getEconomy()), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY -= CFG.PADDING;
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonY, menuWidth - paddingLeft2 * 2, topHeight));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        if (Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).typeOfAlliance == 0) {
            final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
            final int statW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 2) / 3;
            final int statH3 = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
            menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + CFG.getPrecision2(HREManager.getIncome_Emperor(InGame_AllianceSpecial.allianceID), 100), Images.gold, buttonX, buttonY, statW, statH3, maxIconW) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID, Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_Leader)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", "+" + CFG.getPrecision2(HREManager.getIncome_Emperor(InGame_AllianceSpecial.allianceID), 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + CFG.getPrecision2((float)HREManager.getManpower_Emperor(InGame_AllianceSpecial.allianceID), 1), Game_Calendar.IMG_MANPOWER, buttonX + statW + CFG.PADDING, buttonY, statW, statH3, maxIconW) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID, Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_Leader)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "+" + CFG.getPrecision2((float)HREManager.getManpower_Emperor(InGame_AllianceSpecial.allianceID), 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + CFG.getPrecision2(GameValues.hre.HRE_EMPEROR_REGIMENTS, 1), Images.regimentsLimit, buttonX + statW * 2 + CFG.PADDING * 2, buttonY, statW, statH3, maxIconW) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID, Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_Leader)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RegimentsLimit") + ": ", "+" + CFG.getPrecision2(GameValues.hre.HRE_EMPEROR_REGIMENTS, 1), Images.regimentsLimit, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).typeOfAlliance == 1) {
            menuElements.add(new TextIcon2_Horizontal(Game.lang.get("DefensiveAlliance"), Images.defensivePact, paddingLeft, buttonY, menuWidth - paddingLeft * 2, topTitleH, ImageManager.getImage(Images.defensivePact).getWidth() + CFG.PADDING * 2) {
                @Override
                public void actionElement() {
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        int buttonYBefore = buttonY;
        buttonY += CFG.PADDING;
        menuElements.add(new Text_StaticBG_TitleSpecial_ID(Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_FirstTier), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, topTitleH, InGame_AllianceSpecial.allianceID) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(this.getText() + ": ", CFG.getNumberWithSpaces("" + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getNumOfCivilizations_FirstTier()), InGame_AllianceSpecial.this.getCivilizationsIMG(), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                InGame_AllianceSpecial.showFirstTier = !InGame_AllianceSpecial.showFirstTier;
                Game.menuManager.rebuildInGame_AllianceSpecial_SavePos(InGame_AllianceSpecial.allianceID);
                InGame_AllianceSpecial.lTime = 0L;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        if (InGame_AllianceSpecial.showFirstTier) {
            for (int i = 0; i < Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.size(); ++i) {
                if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth - paddingLeft) {
                    buttonY += ButtonFlag_Diplomacy.getButtonHeight() + CFG.PADDING;
                    buttonX = paddingLeft;
                }
                if (Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.get(i)).getNumOfProvinces() > 0) {
                    menuElements.add(new ButtonFlag_Diplomacy((int)Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.get(i), buttonX, buttonY, true) {
                        @Override
                        public void actionElement() {
                            if (this.iCivID >= 0 && this.iCivID != InGame_Civ.iActiveCivID) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                                InGame_Civ.iRebuildToCivID = this.iCivID;
                                Game.menuManager.rebuildInGame_Civ();
                                InGame_Civ.lTime = 0L;
                                if (Game.menuManager.getVisibleInGame_CivBonuses() && InGame_Civ.iActiveCivID != 0 && InGame_CivBonuses.iCivID != InGame_Civ.iActiveCivID) {
                                    InGame_CivBonuses.iCivID = InGame_Civ.iActiveCivID;
                                    Game.menuManager.rebuildInGame_CivBonuses();
                                    Game.menuManager.setVisibleInGame_CivBonuses(true);
                                    InGame_CivBonuses.lTime = 0L;
                                }
                            }
                            else if (Game.getCiv(this.iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.iCivID).getCapitalProvinceID()).getCivID() == this.iCivID) {
                                Game.mapCoords.centerToProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                                Game.setActiveProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                                ProvinceBorderManager.action.setProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
            }
            for (int i = menuElements.size() - 1; i >= 0; --i) {
                buttonY = Math.max(buttonY, menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING);
            }
        }
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonYBefore, menuWidth - paddingLeft2 * 2, buttonY - buttonYBefore));
        buttonY += CFG.PADDING;
        buttonX = paddingLeft;
        buttonYBefore = buttonY;
        buttonY += CFG.PADDING;
        menuElements.add(new Text_StaticBG_TitleSpecial_ID(Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_Rest), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, topTitleH, InGame_AllianceSpecial.allianceID) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(this.getText() + ": ", CFG.getNumberWithSpaces("" + Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).getNumOfCivilizations_SecondTier()), InGame_AllianceSpecial.this.getCivilizationsIMG(), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                InGame_AllianceSpecial.showSecondTier = !InGame_AllianceSpecial.showSecondTier;
                Game.menuManager.rebuildInGame_AllianceSpecial_SavePos(InGame_AllianceSpecial.allianceID);
                InGame_AllianceSpecial.lTime = 0L;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        if (InGame_AllianceSpecial.showSecondTier) {
            for (int i = 0; i < Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.size(); ++i) {
                if (buttonX + ButtonFlagRect.getButtonWidth() > menuWidth - paddingLeft) {
                    buttonY += ButtonFlagRect.getButtonHeight() + CFG.PADDING / 2;
                    buttonX = paddingLeft;
                }
                if (Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.get(i)).getNumOfProvinces() > 0) {
                    menuElements.add(new ButtonFlagRect((int)Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.get(i), buttonX, buttonY, true) {
                        @Override
                        public void actionElement() {
                            if (this.iCivID >= 0 && this.iCivID != InGame_Civ.iActiveCivID) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                                InGame_Civ.iRebuildToCivID = this.iCivID;
                                Game.menuManager.rebuildInGame_Civ();
                                InGame_Civ.lTime = 0L;
                                if (Game.menuManager.getVisibleInGame_CivBonuses() && InGame_Civ.iActiveCivID != 0 && InGame_CivBonuses.iCivID != InGame_Civ.iActiveCivID) {
                                    InGame_CivBonuses.iCivID = InGame_Civ.iActiveCivID;
                                    Game.menuManager.rebuildInGame_CivBonuses();
                                    Game.menuManager.setVisibleInGame_CivBonuses(true);
                                    InGame_CivBonuses.lTime = 0L;
                                }
                            }
                            else if (Game.getCiv(this.iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.iCivID).getCapitalProvinceID()).getCivID() == this.iCivID) {
                                Game.mapCoords.centerToProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                                Game.setActiveProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                                ProvinceBorderManager.action.setProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
            }
            for (int i = menuElements.size() - 1; i >= 0; --i) {
                buttonY = Math.max(buttonY, menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING);
            }
        }
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonYBefore, menuWidth - paddingLeft2 * 2, buttonY - buttonYBefore));
        buttonY += CFG.PADDING;
        buttonX = paddingLeft;
        if (Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).isInAlliance(Game.player.iCivID)) {
            menuElements.add(new ButtonGame_Image(Game.lang.get("LeaveAlliance"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, true, Images.x) {
                @Override
                public void actionElement() {
                    if (InGame_AllianceSpecial.this.leaveAllianceConfirm) {
                        Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).removeCiv(Game.player.iCivID);
                        Game.getCiv(Game.player.iCivID).removeInAllianceSpecial(InGame_AllianceSpecial.allianceID);
                        InGame_Info.iCivID = Game.player.iCivID;
                        InGame_Info.iCivID2 = Game.player.iCivID;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_Alliance), Game.lang.get("LeaveAlliance"));
                        InGame_Info.imgID = Images.infoDiplomacy;
                        InGame_AllianceSpecial.this.setVisible(false);
                    }
                    else {
                        InGame_AllianceSpecial.this.leaveAllianceConfirm = true;
                        this.setText(Game.lang.get("Confirm") + ": " + Game.lang.get("LeaveAlliance"));
                    }
                }
                
                @Override
                public boolean getIsHovered() {
                    return super.getIsHovered() || InGame_AllianceSpecial.this.leaveAllianceConfirm;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LeaveAlliance") + ": " + Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_Alliance), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.x, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).typeOfAlliance == 0) {
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("NextElections"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, "") {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_Alliance), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("CivilizationWithTheHighestRankWillWinNextElection"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final List<Integer> tCivs = new ArrayList<Integer>();
            tCivs.add(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID);
            for (int j = Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.size() - 1; j >= 0; --j) {
                if (!tCivs.contains(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.get(j)) && Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.get(j)).getNumOfProvinces() > 0 && Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.get(j)).iCivRankPosition >= 0) {
                    tCivs.add(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.get(j));
                }
            }
            for (int j = Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.size() - 1; j >= 0; --j) {
                if (!tCivs.contains(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.get(j)) && Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.get(j)).getNumOfProvinces() > 0 && Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.get(j)).iCivRankPosition >= 0) {
                    tCivs.add(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.get(j));
                }
            }
            final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
            final int r0W0 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.2f);
            final int r0W2 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.6f);
            final int r1W = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.2f);
            for (int o = 0; o < GameValues.hre.HRE_VIEW_ELECTIONS_TOP_CIVS && o < tCivs.size(); ++o) {
                int toAddID = 0;
                for (int k = tCivs.size() - 1; k > 0; --k) {
                    if (Game.getCiv(tCivs.get(k)).iCivRankPosition < Game.getCiv(tCivs.get(toAddID)).iCivRankPosition) {
                        toAddID = k;
                    }
                }
                menuElements.add(new ButtonStatsRectIMG_Rank("" + Game.getCiv(tCivs.get(toAddID)).iCivRankPosition, CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(tCivs.get(toAddID)), buttonX, buttonY, r0W0, buttonH, ImageManager.getImage(Images.rankGold).getWidth()) {});
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv(tCivs.get(toAddID)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W2, buttonH, (int)tCivs.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                                Game.menuManager.rebuildInGame_Civ();
                            }
                            else {
                                Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_ID_Special("" + CFG.getPrecision2(Game.getCiv(tCivs.get(toAddID)).iCivRankScore, 1), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tCivs.get(toAddID)) {
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                    }
                });
                buttonX = paddingLeft;
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                tCivs.remove(toAddID);
            }
            tCivs.clear();
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Reforms"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iReformsPassed + " / " + HREManager.numOfReforms));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            for (int l = 0; l < HREManager.numOfReforms; ++l) {
                if (l == Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iReformsPassed) {
                    menuElements.add(new Button_HRE_Reform(Game.lang.get("Reform" + l), Game.lang.get("Reform" + l + ".d"), paddingLeft + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 2, l, l) {
                        @Override
                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 36 && InGame_AllianceSpecialReformHRE.allianceID == InGame_AllianceSpecial.allianceID && InGame_AllianceSpecialReformHRE.reformID == this.getCurrent()) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                            else {
                                Game.menuManager.rebuildInGame_AllianceSpecialReform(InGame_AllianceSpecial.allianceID, this.getCurrent());
                            }
                        }
                        
                        @Override
                        public void buildElementHover() {
                            this.menuElementHover = HREManager.getHoverReform(InGame_AllianceSpecial.allianceID, this.reformID);
                        }
                    });
                }
                else if (l < Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iReformsPassed) {
                    menuElements.add(new Button_HRE_Reform_Green(Game.lang.get("Reform" + l), Game.lang.get("Reform" + l + ".d"), paddingLeft + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 2, l, l) {
                        @Override
                        public void actionElement() {
                        }
                        
                        @Override
                        public void buildElementHover() {
                            this.menuElementHover = HREManager.getHoverReform(InGame_AllianceSpecial.allianceID, this.reformID);
                        }
                    });
                }
                else {
                    menuElements.add(new Button_HRE_Reform_Red(Game.lang.get("Reform" + l), Game.lang.get("Reform" + l + ".d"), paddingLeft + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 2, l, l) {
                        @Override
                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 36 && InGame_AllianceSpecialReformHRE.allianceID == InGame_AllianceSpecial.allianceID && InGame_AllianceSpecialReformHRE.reformID == this.getCurrent()) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                            else {
                                Game.menuManager.rebuildInGame_AllianceSpecialReform(InGame_AllianceSpecial.allianceID, this.getCurrent());
                            }
                        }
                        
                        @Override
                        public void buildElementHover() {
                            this.menuElementHover = HREManager.getHoverReform(InGame_AllianceSpecial.allianceID, this.reformID);
                        }
                    });
                }
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).Name_Alliance), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_AllianceSpecial.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, true, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_AllianceSpecial.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_AllianceSpecial.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_AllianceSpecial.lTime = CFG.currentTimeMillis;
        InGame_AllianceSpecial.lTime2 = InGame_AllianceSpecial.lTime;
    }
    
    public int getCivilizationsIMG() {
        if (Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).typeOfAlliance == 0) {
            return Images.hre;
        }
        return Images.council;
    }
    
    static {
        InGame_AllianceSpecial.lTime = 0L;
        InGame_AllianceSpecial.lTime2 = 0L;
        InGame_AllianceSpecial.showFirstTier = true;
        InGame_AllianceSpecial.showSecondTier = false;
    }
}
