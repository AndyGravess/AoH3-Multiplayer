// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldSearch;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.map.map.Map_Data;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menusInGame.InGame_HideUI;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menusInGame.InGame_CivBonuses;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menusInGame.Graph.InGame_GraphPopulation;
import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldCivs;
import aoh.kingdoms.history.menusInGame.Goods.InGame_Goods;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Value_Levels;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical_Data_Type;
import aoh.kingdoms.history.map.diplomacy.Vassal;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialCiv;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Special;
import aoh.kingdoms.history.menusInGame.InGame_ReleaseAVassal;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Value;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.button.ButtonAdvisorGeneral;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Right;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Advisor;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Steam.SteamAchievementsManager;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Advisor_Skill;
import aoh.kingdoms.history.map.advisors.AdvisorManager;
import aoh.kingdoms.history.menus.Dialog;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menusInGame.InGame_AdvisorRecruit;
import aoh.kingdoms.history.menu_element.button.ButtonAdvisor_No;
import aoh.kingdoms.history.menu_element.button.ButtonAdvisor;
import aoh.kingdoms.history.menu_element.button.Button_OutlinerEspionageMission;
import aoh.kingdoms.history.menu_element.button.Button_OutlinerEspionageMissionReport;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.map.diplomacy.DiplomacyEspionageMission;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menusInGame.InGame_Encyclopedia;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonIcon;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_Diplomacy;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2;
import aoh.kingdoms.history.menu_element.button.ButtonReligion2;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Ruler;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.button.ButtonRuler2;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iActiveCivID;
    public static int FIRE_ID;
    public static boolean inCourt;
    public static boolean inSearchProvinces;
    public static int modeID;
    public static boolean UPDATE_INCOME_MANPOWER_FROM_VASSAL;
    
    public InGame_Court() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = paddingLeft;
        int buttonY = 0;
        InGame_Court.inCourt = true;
        InGame_Court.inSearchProvinces = false;
        if (InGame_Court.iActiveCivID != Game.player.iCivID) {
            InGame_Court.modeID = 0;
        }
        RulersManager.loadRulerIMG(InGame_Court.iActiveCivID);
        buttonX = paddingLeft;
        menuElements.add(new Text_Title_v2Center(Game.ideologiesManager.getIdeology(Game.getCiv(InGame_Court.iActiveCivID).getIdeologyID()).RulerTitle, -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int rulerBGY = buttonY - CFG.PADDING;
        menuElements.add(new ButtonRuler2(InGame_Court.iActiveCivID, buttonX, buttonY) {
            @Override
            public void actionElement() {
                if (Game.getCiv(InGame_Court.iActiveCivID).getCapitalProvinceID() >= 0) {
                    Game.setActiveProvinceID(Game.getCiv(InGame_Court.iActiveCivID).getCapitalProvinceID());
                    ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    Game.menuManager.rebuildInGame_Civ();
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        int statsH = 0;
        menuElements.add(new Text_StaticBG_RulerTitle(Game.getCiv(InGame_Court.iActiveCivID).ruler.Name, buttonX, buttonY, menuWidth - buttonX - paddingLeft, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void buildElementHover() {
                this.menuElementHover = ButtonRuler2.getHoverRuler(InGame_Court.iActiveCivID, false);
            }
            
            @Override
            public String getTextToDraw() {
                try {
                    if (Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RULER_NAME) {
                        if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).ruler.Name)) {
                            this.setText(Game.getCiv(InGame_Court.iActiveCivID).ruler.Name);
                        }
                        return Game.getCiv(InGame_Court.iActiveCivID).ruler.Name + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RULER_NAME) ? Keyboard.getKeyboardVerticalLine() : "");
                    }
                }
                catch (final Exception ex) {}
                return super.getTextToDraw();
            }
            
            @Override
            public void actionElement() {
                if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RULER_NAME) {
                    Game.keyboard.hideKeyboard();
                }
                else {
                    Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.INGAME_RULER_NAME, Game.getCiv(InGame_Court.iActiveCivID).ruler.Name);
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        statsH += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_StaticBG_Ruler("" + Game.getCiv(InGame_Court.iActiveCivID).ruler.BornDay + " " + Game_Calendar.getMonthName(Game.getCiv(InGame_Court.iActiveCivID).ruler.BornMonth) + " " + Game.getCiv(InGame_Court.iActiveCivID).ruler.BornYear, Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(InGame_Court.iActiveCivID).ruler.BornYear)), buttonX, buttonY, menuWidth - buttonX - paddingLeft, CFG.TEXT_HEIGHT + CFG.PADDING * 3) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).ruler.Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Born") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).ruler.BornDay + " " + Game_Calendar.getMonthName(Game.getCiv(InGame_Court.iActiveCivID).ruler.BornMonth) + " " + Game.getCiv(InGame_Court.iActiveCivID).ruler.BornYear, Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(InGame_Court.iActiveCivID).ruler.BornYear)), "", Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        statsH += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        int tWidth = (menuWidth - buttonX - paddingLeft - CFG.PADDING * 2) / 3;
        statsH = ButtonRuler2.getButtonHeight() - statsH;
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.UnitsAttack != 0 || Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.UnitsDefense != 0 || Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralAttack != 0 || Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralDefense != 0) {
            tWidth = (menuWidth - buttonX - paddingLeft - CFG.PADDING * 3) / 4;
        }
        menuElements.add(new ButtonReligion2(Game.getCiv(InGame_Court.iActiveCivID).getReligionID(), buttonX, buttonY, tWidth, statsH) {
            @Override
            public void buildElementHover() {
                this.menuElementHover = Game.religionManager.getHoverReligion(this.religionID, InGame_Court.iActiveCivID);
            }
            
            @Override
            public void actionElement() {
                InGame_Court_Government.modeID = 0;
                InGame_Court.iActiveCivID = Game.player.iCivID;
                InGame_CourtOptions.iActiveID = InGame_CourtOptions.iGovernmentID;
                InGame_CourtOptions2.disableAllViews();
                Game.menuManager.rebuildInGame_Government();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.MonthlyIncome != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.MonthlyIncome, 100), Images.gold, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MonthlyIncome") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.TaxEfficiency != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("TaxEfficiency") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.tax, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ProductionEfficiency != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ProductionEfficiency") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ProvinceMaintenance != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ProvinceMaintenance, 100) + "%", Images.provinces, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ProvinceMaintenance") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.BuildingsMaintenanceCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.BuildingsMaintenanceCost * 100.0f, 100) + "%", Images.buildings, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("BuildingsMaintenanceCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.buildings, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.MaxManpower != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.MaxManpower, 1), Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MaximumManpower") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_MANPOWER_UP, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.RecruitmentTime != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RecruitmentTime") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_MANPOWER_TIME, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ResearchPoints != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ResearchPoints, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ResearchPerMonth") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.Devastation != 0.0f) {
            menuElements.add(new TextIcon2("+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.Devastation * 100.0f, 100) + "%", Images.devastation, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Devastation") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.devastation, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.Research != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.Research, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Research") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralCost * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("GeneralCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ConstructionCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ConstructionCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ConstructionCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.construction, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.AdministrationBuildingsCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("AdministrationBuildingsCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.construction, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.EconomyBuildingsCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("EconomyBuildingsCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.construction, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.MilitaryBuildingsCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MilitaryBuildingsCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.construction, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.InvestInEconomyCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("InvestInEconomyCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_ECONOMY_UP, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.IncreaseTaxEfficiencyCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.taxUp, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.IncreaseGrowthRateCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.IncreaseGrowthRateCost * 100.0f, 100) + "%", Images.populationUp, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("IncreaseGrowthRateCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.populationUp, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.DevelopInfrastructureCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("DevelopInfrastructureCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.infrastructureUp, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.IncreaseManpowerCost != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("IncreaseManpowerCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_MANPOWER_UP, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ImproveRelationsModifier != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.ImproveRelationsModifier, 100) + "%", Images.diplomacy, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ImproveRelationsModifier") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.diplomacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.LoanInterest != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.LoanInterest, 100) + "%", Images.loan, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Interest") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.MonthlyLegacy != 0.0f) {
            menuElements.add(new TextIcon2(CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.MonthlyLegacy, 100), Images.legacy, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MonthlyLegacy") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.legacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.UnitsAttack != 0) {
            menuElements.add(new TextIcon2("" + ((Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.UnitsAttack > 0) ? "+" : "") + Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.UnitsAttack, Images.attack, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("UnitsAttack") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.attack, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.UnitsDefense != 0) {
            menuElements.add(new TextIcon2("" + ((Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.UnitsDefense > 0) ? "+" : "") + Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.UnitsDefense, Images.defense, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("UnitsDefense") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.defense, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralAttack != 0) {
            menuElements.add(new TextIcon2("" + ((Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralAttack > 0) ? "+" : "") + Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralAttack, Images.attack, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("GeneralsAttack") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.attack, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        if (Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralDefense != 0) {
            menuElements.add(new TextIcon2("" + ((Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralDefense > 0) ? "+" : "") + Game.getCiv(InGame_Court.iActiveCivID).ruler.rulerBonuses.GeneralDefense, Images.defense, buttonX, buttonY, tWidth, statsH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("GeneralsDefense") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.defense, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        }
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, rulerBGY, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonRuler2.getButtonHeight() + CFG.PADDING * 2));
        buttonY = menuElements.get(1).getPosY() + menuElements.get(1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get(GameValues.court.COUNCIL_NAME), Images.boxTitleBORDERWIDTH, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3, CFG.BUTTON_HEIGHT4, InGame_Court.modeID == 0) {
            @Override
            public void actionElement() {
                InGame_Court.modeID = 0;
                InGame_Court.iActiveCivID = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
                Game.setRegroupArmyMode(false);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get(Game_Ages.getVassals()), Images.boxTitleBORDERWIDTH + (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3, CFG.BUTTON_HEIGHT4, InGame_Court.modeID == 1 || InGame_Court.modeID == 11) {
            @Override
            public void actionElement() {
                InGame_Court.modeID = 1;
                InGame_Court.iActiveCivID = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
                Game.setRegroupArmyMode(false);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get("More"), Images.boxTitleBORDERWIDTH + (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3 * 2, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3, CFG.BUTTON_HEIGHT4, InGame_Court.modeID == 2) {
            @Override
            public void actionElement() {
                InGame_Court.modeID = 2;
                InGame_Court.iActiveCivID = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
                Game.setRegroupArmyMode(false);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.world, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        if (InGame_Court.modeID == 0) {
            if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                int typeX = paddingLeft;
                final int typeW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 4) / 5;
                final int typeH = CFG.BUTTON_HEIGHT4;
                menuElements.add(new ButtonIcon("", Images.missions, typeX, buttonY, typeW, typeH) {
                    @Override
                    public void actionElement() {
                        InGame_Court.inCourt = false;
                        InGame_Court.inSearchProvinces = false;
                        InGame_CourtOptions.disableAllViews();
                        Game.menuManager.rebuildInGame_CourtMissions();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Events"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.missions, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.getClickSound_CivOptions();
                    }
                });
                typeX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonIcon("", Images.encyclopedia, typeX, buttonY, typeW, typeH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 15) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            InGame_Encyclopedia.sSearch = "";
                            Game.menuManager.rebuildInGame_Encyclopedia();
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Encyclopedia"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.encyclopedia, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.getClickSound_CivOptions();
                    }
                });
                typeX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonIcon("", Images.development, typeX, buttonY, typeW, typeH) {
                    @Override
                    public void actionElement() {
                        InGame_Court.inCourt = false;
                        InGame_Court.inSearchProvinces = false;
                        InGame_CourtOptions.disableAllViews();
                        Game.menuManager.rebuildInGame_CourtStatistics();
                        Game.menuManager.setVisibleInGame_Court(true);
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Statistics"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.development, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Court.iActiveCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.getClickSound_CivOptions();
                    }
                });
                typeX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonIcon("", Images.provinces, typeX, buttonY, typeW, typeH) {
                    @Override
                    public void actionElement() {
                        InGame_CourtOptions2.actionProvinces(InGame_CourtOptions2.idProvinces);
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Provinces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Court.iActiveCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.getClickSound_CivOptions();
                    }
                });
                typeX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonIcon("", Images.settings, typeX, buttonY, typeW, typeH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_Escape()) {
                            Game.menuManager.setVisibleInGame_Escape(false);
                        }
                        else {
                            InGame_CourtOptions2.disableAllViews();
                            Game.menuManager.setVisibleInGame_Escape(true);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Options"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.settings, CFG.PADDING, 0));
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
            }
            if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                Game.player.playerData.espionage.removeExpiredEspionageMissions();
                if (Game.player.playerData.espionage.iEspionageMissionsSize > 0) {
                    menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("EspionageMission"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    final int buttonH_Spy = Math.max(CFG.BUTTON_HEIGHT3, Math.max(Math.max(ImageManager.getImage(Images.flag_rect).getHeight(), ImageManager.getImage(Images.spy).getHeight()), CFG.TEXT_HEIGHT) + CFG.PADDING * 2);
                    try {
                        for (int i = 0; i < Game.player.playerData.espionage.iEspionageMissionsSize; ++i) {
                            if (Game.player.playerData.espionage.espionageMissions.get(i).iReportTurnID <= Game_Calendar.TURN_ID) {
                                menuElements.add(new Button_OutlinerEspionageMissionReport(Game.lang.get("Completed"), "", paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH_Spy, Images.spy, Game.player.playerData.espionage.espionageMissions.get(i).iCivID, Game.player.playerData.espionage.espionageMissions.get(i).iReportTurnID - DiplomacyManager.sendSpyTime(Game.player.iCivID, Game.player.playerData.espionage.espionageMissions.get(i).iCivID), Game.player.playerData.espionage.espionageMissions.get(i).iReportExpiresTurnID - Game_Calendar.TURN_ID, false) {
                                    @Override
                                    public void actionElement() {
                                        InGame_CourtOptions2.disableAllViews();
                                        Game.menuManager.rebuildInGame_EspionageReportCourt(this.getCurrent(), Game.player.playerData.espionage.espionageMission_ReportEndTurn(this.getCurrent()));
                                        Game.menuManager.setVisibleInGame_Court(true);
                                        InGame_Court.lTime = 0L;
                                    }
                                });
                                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                            }
                        }
                        for (int i = 0; i < Game.player.playerData.espionage.iEspionageMissionsSize; ++i) {
                            if (Game.player.playerData.espionage.espionageMissions.get(i).iReportTurnID > Game_Calendar.TURN_ID) {
                                menuElements.add(new Button_OutlinerEspionageMission(Game.lang.get("Progress"), "%", paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH_Spy, Images.spy, Game.player.playerData.espionage.espionageMissions.get(i).iCivID, Game.player.playerData.espionage.espionageMissions.get(i).iReportTurnID - DiplomacyManager.sendSpyTime(Game.player.iCivID, Game.player.playerData.espionage.espionageMissions.get(i).iCivID), Game.player.playerData.espionage.espionageMissions.get(i).iReportTurnID, false) {
                                    @Override
                                    public void actionElement() {
                                    }
                                });
                                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                            }
                        }
                    }
                    catch (final Exception ex2) {}
                }
            }
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(GameValues.court.ADVISOR_NAME_ADMINISTRATIVE), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName != null) ? Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iYearOfBirth)) : ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            tWidth = menuWidth - ButtonAdvisor.getButtonWidth() - paddingLeft * 2 - CFG.PADDING;
            buttonX = paddingLeft;
            final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
            if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName == null) {
                menuElements.add(new ButtonAdvisor_No(buttonX, buttonY) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 0) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 0;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, tWidth, ButtonAdvisor.getButtonHeight()) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 0) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 0;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToHireAnAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    }
                });
            }
            else {
                menuElements.add(new ButtonAdvisor(buttonX, buttonY, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.imageID, InGame_Court.iActiveCivID, -1, 0, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sIMG) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 0) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 0;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                    
                    @Override
                    public void actionElementPPM() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            InGame_Court.FIRE_ID = 0;
                            Dialog.setDialogType(Dialog.DialogType.FIRE_ADVISOR);
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                int statsY = 0;
                final int statW = menuWidth - buttonX - paddingLeft;
                final int statH = (ButtonAdvisor.getButtonHeight() - CFG.PADDING * 2) / 3;
                menuElements.add(new Text_StaticBG_Advisor_Skill(Game.lang.get("Skill") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), buttonX, buttonY, CFG.PADDING, statH) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            if (Game.getCiv(InGame_Court.iActiveCivID).fGold < AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel), 100), Images.gold);
                            }
                            else if (Game.getCiv(InGame_Court.iActiveCivID).fLegacy < AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel), 100), Images.legacy);
                            }
                            if (AdvisorManager.promoteAdvisor(Game.player.iCivID, 0, false)) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                Game.menuManager.rebuildInGame_Info(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName, Game.lang.get("AdvisorSkills") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID));
                                InGame_Info.imgID = Images.infoCrown;
                                SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.PROMOTE_ADVISOR);
                            }
                            Game.menuManager.rebuildInGame_CourtSavePos();
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PromoteAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.skill, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorSkills") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.advisors.ADVISOR_BONUSES_UPGRADE_PER_LEVEL * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                menuElements.get(menuElements.size() - 1).setPosX(menuWidth - paddingLeft - menuElements.get(menuElements.size() - 1).getWidth());
                menuElements.add(new Text_StaticBG_Advisor(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName, buttonX, buttonY, menuElements.get(menuElements.size() - 1).getPosX() - buttonX - CFG.PADDING, statH) {
                    @Override
                    public String getTextToDraw() {
                        try {
                            if (Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_ADMINISTRATIVE_NAME) {
                                if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName)) {
                                    this.setText(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName);
                                }
                                return Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_ADMINISTRATIVE_NAME) ? Keyboard.getKeyboardVerticalLine() : "");
                            }
                        }
                        catch (final Exception ex) {}
                        return super.getTextToDraw();
                    }
                    
                    @Override
                    public void actionElement() {
                        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_ADMINISTRATIVE_NAME) {
                            Game.keyboard.hideKeyboard();
                        }
                        else {
                            Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.INGAME_ADVISOR_ADMINISTRATIVE_NAME, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.TaxEfficiency != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("TaxEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ProvinceMaintenance != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ProvinceMaintenance, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.GrowthRate != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GrowthRate") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.GrowthRate, 100) + "%", Images.populationGrowth, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ConstructionCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ConstructionCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.AdministrationBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("AdministrationBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.EconomyBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("EconomyBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.MilitaryBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MilitaryBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.InvestInEconomyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.IncreaseTaxEfficiencyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.IncreaseGrowthRateCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseGrowthRateCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.IncreaseGrowthRateCost * 100.0f, 100) + "%", Images.populationUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.DevelopInfrastructureCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ProductionEfficiency != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProductionEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.Research != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ResearchPerMonth") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.Research, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.MonthlyLegacy != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MonthlyLegacy") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.MonthlyLegacy, 100), Images.legacy, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.GeneralAttack != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.GeneralAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.GeneralDefense != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.GeneralDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ArmyMaintenance != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ArmyMaintenance, 100) + "%", Images.armyMaintenance, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.RecruitArmyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.RecruitArmyCost, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ConstructionTime != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionTime") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ConstructionTime * 100.0f, 100) + "%", Images.buildTime, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.IncreaseManpowerCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.RecruitmentTime != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RecruitmentTime") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.LoanInterest != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("LoanInterest") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.LoanInterest, 100) + "%", Images.loan, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.CoreCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("CoreConstruction") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.CoreCost, 100) + "%", Images.core, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ReligionCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ReligionConversionCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ReligionCost, 100) + "%", Images.religion, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.IncomeProduction != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncomeProduction") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.IncomeProduction, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.MaxManpower != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MaximumManpower") + "", "+" + (int)Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.UnitsAttack != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.UnitsAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.UnitsDefense != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.UnitsDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.RegimentsLimit != 0) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RegimentsLimit") + "", "+" + CFG.getPrecision2((float)Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.RegimentsLimit, 1), Images.regimentsLimit, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ImproveRelationsModifier != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ImproveRelationsModifier") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ImproveRelationsModifier, 100) + "%", Images.relations, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ArmyMovementSpeed != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMovementSpeed") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.SiegeEffectiveness != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("SiegeEffectiveness") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.SiegeEffectiveness * 100.0f, 100) + "%", Images.siege, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                buttonX = paddingLeft;
            }
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonY - CFG.PADDING, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2));
            buttonY += ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(GameValues.court.ADVISOR_NAME_ECONOMIC), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName != null) ? Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iYearOfBirth)) : ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            buttonX = paddingLeft;
            if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName == null) {
                menuElements.add(new ButtonAdvisor_No(buttonX, buttonY) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 1) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 1;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, tWidth, ButtonAdvisor.getButtonHeight()) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 1) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 1;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToHireAnAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    }
                });
            }
            else {
                menuElements.add(new ButtonAdvisor(buttonX, buttonY, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.imageID, InGame_Court.iActiveCivID, -1, 1, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sIMG) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 1) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 1;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                    
                    @Override
                    public void actionElementPPM() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            InGame_Court.FIRE_ID = 1;
                            Dialog.setDialogType(Dialog.DialogType.FIRE_ADVISOR);
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                int statsY = 0;
                final int statW = menuWidth - buttonX - paddingLeft;
                final int statH = (ButtonAdvisor.getButtonHeight() - CFG.PADDING * 2) / 3;
                menuElements.add(new Text_StaticBG_Advisor_Skill(Game.lang.get("Skill") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), buttonX, buttonY, CFG.PADDING, statH) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            if (Game.getCiv(InGame_Court.iActiveCivID).fGold < AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel), 100), Images.gold);
                            }
                            else if (Game.getCiv(InGame_Court.iActiveCivID).fLegacy < AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel), 100), Images.legacy);
                            }
                            if (AdvisorManager.promoteAdvisor(Game.player.iCivID, 1, false)) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                Game.menuManager.rebuildInGame_Info(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName, Game.lang.get("AdvisorSkills") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID));
                                InGame_Info.imgID = Images.infoCrown;
                                SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.PROMOTE_ADVISOR);
                            }
                            Game.menuManager.rebuildInGame_CourtSavePos();
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PromoteAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.skill, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorSkills") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.advisors.ADVISOR_BONUSES_UPGRADE_PER_LEVEL * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                menuElements.get(menuElements.size() - 1).setPosX(menuWidth - paddingLeft - menuElements.get(menuElements.size() - 1).getWidth());
                menuElements.add(new Text_StaticBG_Advisor(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName, buttonX, buttonY, menuElements.get(menuElements.size() - 1).getPosX() - buttonX - CFG.PADDING, statH) {
                    @Override
                    public String getTextToDraw() {
                        try {
                            if (Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_ECONOMIC_NAME) {
                                if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName)) {
                                    this.setText(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName);
                                }
                                return Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_ECONOMIC_NAME) ? Keyboard.getKeyboardVerticalLine() : "");
                            }
                        }
                        catch (final Exception ex) {}
                        return super.getTextToDraw();
                    }
                    
                    @Override
                    public void actionElement() {
                        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_ECONOMIC_NAME) {
                            Game.keyboard.hideKeyboard();
                        }
                        else {
                            Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.INGAME_ADVISOR_ECONOMIC_NAME, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.TaxEfficiency != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("TaxEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ProvinceMaintenance != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ProvinceMaintenance, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.GrowthRate != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GrowthRate") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.GrowthRate, 100) + "%", Images.populationGrowth, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ConstructionCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ConstructionCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.AdministrationBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("AdministrationBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.EconomyBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("EconomyBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.MilitaryBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MilitaryBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.InvestInEconomyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.IncreaseTaxEfficiencyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.IncreaseGrowthRateCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseGrowthRateCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.IncreaseGrowthRateCost * 100.0f, 100) + "%", Images.populationUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.DevelopInfrastructureCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ProductionEfficiency != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProductionEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.Research != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ResearchPerMonth") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.Research, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.MonthlyLegacy != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MonthlyLegacy") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.MonthlyLegacy, 100), Images.legacy, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.GeneralAttack != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.GeneralAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.GeneralDefense != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.GeneralDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ArmyMaintenance != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ArmyMaintenance, 100) + "%", Images.armyMaintenance, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.RecruitArmyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.RecruitArmyCost, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ConstructionTime != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionTime") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ConstructionTime * 100.0f, 100) + "%", Images.buildTime, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.IncreaseManpowerCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.RecruitmentTime != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RecruitmentTime") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.RecruitmentTime, 100) + "%", Images.time, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.LoanInterest != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("LoanInterest") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.LoanInterest, 100) + "%", Images.loan, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.CoreCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("CoreConstruction") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.CoreCost, 100) + "%", Images.core, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ReligionCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ReligionConversionCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ReligionCost, 100) + "%", Images.religion, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.IncomeProduction != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncomeProduction") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.IncomeProduction, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.MaxManpower != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MaximumManpower") + "", "+" + (int)Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.UnitsAttack != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.UnitsAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.UnitsDefense != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.UnitsDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.RegimentsLimit != 0) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RegimentsLimit") + "", "+" + CFG.getPrecision2((float)Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.RegimentsLimit, 1), Images.regimentsLimit, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ImproveRelationsModifier != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ImproveRelationsModifier") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ImproveRelationsModifier, 100) + "%", Images.relations, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ArmyMovementSpeed != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMovementSpeed") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.SiegeEffectiveness != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("SiegeEffectiveness") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.SiegeEffectiveness * 100.0f, 100) + "%", Images.siege, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                buttonX = paddingLeft;
            }
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonY - CFG.PADDING, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2));
            buttonY += ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(GameValues.court.ADVISOR_NAME_INNOVATION), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName != null) ? Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iYearOfBirth)) : ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            buttonX = paddingLeft;
            if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName == null) {
                menuElements.add(new ButtonAdvisor_No(buttonX, buttonY) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 2) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 2;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, tWidth, ButtonAdvisor.getButtonHeight()) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 2) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 2;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToHireAnAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    }
                });
            }
            else {
                menuElements.add(new ButtonAdvisor(buttonX, buttonY, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.imageID, InGame_Court.iActiveCivID, -1, 2, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sIMG) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 2) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 2;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                    
                    @Override
                    public void actionElementPPM() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            InGame_Court.FIRE_ID = 2;
                            Dialog.setDialogType(Dialog.DialogType.FIRE_ADVISOR);
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                int statsY = 0;
                final int statW = menuWidth - buttonX - paddingLeft;
                final int statH = (ButtonAdvisor.getButtonHeight() - CFG.PADDING * 2) / 3;
                menuElements.add(new Text_StaticBG_Advisor_Skill(Game.lang.get("Skill") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), buttonX, buttonY, CFG.PADDING, statH) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            if (Game.getCiv(InGame_Court.iActiveCivID).fGold < AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel), 100), Images.gold);
                            }
                            else if (Game.getCiv(InGame_Court.iActiveCivID).fLegacy < AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel), 100), Images.legacy);
                            }
                            if (AdvisorManager.promoteAdvisor(Game.player.iCivID, 2, false)) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                Game.menuManager.rebuildInGame_Info(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName, Game.lang.get("AdvisorSkills") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID));
                                InGame_Info.imgID = Images.infoCrown;
                                SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.PROMOTE_ADVISOR);
                            }
                            Game.menuManager.rebuildInGame_CourtSavePos();
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PromoteAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.skill, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorSkills") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.advisors.ADVISOR_BONUSES_UPGRADE_PER_LEVEL * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                menuElements.get(menuElements.size() - 1).setPosX(menuWidth - paddingLeft - menuElements.get(menuElements.size() - 1).getWidth());
                menuElements.add(new Text_StaticBG_Advisor(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName, buttonX, buttonY, menuElements.get(menuElements.size() - 1).getPosX() - buttonX - CFG.PADDING, statH) {
                    @Override
                    public String getTextToDraw() {
                        try {
                            if (Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_INNOVATION_NAME) {
                                if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName)) {
                                    this.setText(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName);
                                }
                                return Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_INNOVATION_NAME) ? Keyboard.getKeyboardVerticalLine() : "");
                            }
                        }
                        catch (final Exception ex) {}
                        return super.getTextToDraw();
                    }
                    
                    @Override
                    public void actionElement() {
                        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_INNOVATION_NAME) {
                            Game.keyboard.hideKeyboard();
                        }
                        else {
                            Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.INGAME_ADVISOR_INNOVATION_NAME, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.TaxEfficiency != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("TaxEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ProvinceMaintenance != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ProvinceMaintenance, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.GrowthRate != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GrowthRate") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.GrowthRate, 100) + "%", Images.populationGrowth, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ConstructionCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ConstructionCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.AdministrationBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("AdministrationBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.EconomyBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("EconomyBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.MilitaryBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MilitaryBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.InvestInEconomyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.IncreaseTaxEfficiencyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.IncreaseGrowthRateCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseGrowthRateCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.IncreaseGrowthRateCost * 100.0f, 100) + "%", Images.populationUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.DevelopInfrastructureCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ProductionEfficiency != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProductionEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.Research != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ResearchPerMonth") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.Research, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.MonthlyLegacy != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MonthlyLegacy") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.MonthlyLegacy, 100), Images.legacy, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.GeneralAttack != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.GeneralAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.GeneralDefense != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.GeneralDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ArmyMaintenance != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ArmyMaintenance, 100) + "%", Images.armyMaintenance, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.RecruitArmyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.RecruitArmyCost, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ConstructionTime != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionTime") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ConstructionTime * 100.0f, 100) + "%", Images.buildTime, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.IncreaseManpowerCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.RecruitmentTime != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RecruitmentTime") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.LoanInterest != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("LoanInterest") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.LoanInterest, 100) + "%", Images.loan, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.CoreCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("CoreConstruction") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.CoreCost, 100) + "%", Images.core, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ReligionCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ReligionConversionCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ReligionCost, 100) + "%", Images.religion, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.IncomeProduction != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncomeProduction") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.IncomeProduction, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.MaxManpower != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MaximumManpower") + "", "+" + (int)Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.UnitsAttack != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.UnitsAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.UnitsDefense != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.UnitsDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.RegimentsLimit != 0) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RegimentsLimit") + "", "+" + CFG.getPrecision2((float)Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.RegimentsLimit, 1), Images.regimentsLimit, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ImproveRelationsModifier != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ImproveRelationsModifier") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ImproveRelationsModifier, 100) + "%", Images.relations, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ArmyMovementSpeed != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMovementSpeed") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.SiegeEffectiveness != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("SiegeEffectiveness") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.SiegeEffectiveness * 100.0f, 100) + "%", Images.siege, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                buttonX = paddingLeft;
            }
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonY - CFG.PADDING, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2));
            buttonY += ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(GameValues.court.ADVISOR_NAME_MILITARY), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName != null) ? Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iYearOfBirth)) : ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            buttonX = paddingLeft;
            if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName == null) {
                menuElements.add(new ButtonAdvisor_No(buttonX, buttonY) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 3) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 3;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, tWidth, ButtonAdvisor.getButtonHeight()) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 3) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 3;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToHireAnAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    }
                });
            }
            else {
                menuElements.add(new ButtonAdvisorGeneral(buttonX, buttonY, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.imageID, InGame_Court.iActiveCivID, -1, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sIMG) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 3) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            }
                            else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 3;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }
                    }
                    
                    @Override
                    public void actionElementPPM() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            InGame_Court.FIRE_ID = 3;
                            Dialog.setDialogType(Dialog.DialogType.FIRE_ADVISOR);
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                int statsY = 0;
                final int statW = menuWidth - buttonX - paddingLeft;
                final int statH = (ButtonAdvisor.getButtonHeight() - CFG.PADDING * 2) / 3;
                menuElements.add(new Text_StaticBG_Advisor_Skill(Game.lang.get("Skill") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), buttonX, buttonY, CFG.PADDING, statH) {
                    @Override
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            if (Game.getCiv(InGame_Court.iActiveCivID).fGold < AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel), 100), Images.gold);
                            }
                            else if (Game.getCiv(InGame_Court.iActiveCivID).fLegacy < AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel), 100), Images.legacy);
                            }
                            if (AdvisorManager.promoteAdvisor(Game.player.iCivID, 3, false)) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                Game.menuManager.rebuildInGame_Info(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName, Game.lang.get("AdvisorSkills") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID));
                                InGame_Info.imgID = Images.infoCrown;
                                SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.PROMOTE_ADVISOR);
                            }
                            Game.menuManager.rebuildInGame_CourtSavePos();
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PromoteAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.skill, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorSkills") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.advisors.ADVISOR_BONUSES_UPGRADE_PER_LEVEL * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                menuElements.get(menuElements.size() - 1).setPosX(menuWidth - paddingLeft - menuElements.get(menuElements.size() - 1).getWidth());
                menuElements.add(new Text_StaticBG_Advisor(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName, buttonX, buttonY, menuElements.get(menuElements.size() - 1).getPosX() - buttonX - CFG.PADDING, statH) {
                    @Override
                    public String getTextToDraw() {
                        try {
                            if (Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_MILITARY_NAME) {
                                if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName)) {
                                    this.setText(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName);
                                }
                                return Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName + ((Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_MILITARY_NAME) ? Keyboard.getKeyboardVerticalLine() : "");
                            }
                        }
                        catch (final Exception ex) {}
                        return super.getTextToDraw();
                    }
                    
                    @Override
                    public void actionElement() {
                        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_ADVISOR_MILITARY_NAME) {
                            Game.keyboard.hideKeyboard();
                        }
                        else {
                            Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.INGAME_ADVISOR_MILITARY_NAME, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.TaxEfficiency != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("TaxEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ProvinceMaintenance != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ProvinceMaintenance, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.GrowthRate != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GrowthRate") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.GrowthRate, 100) + "%", Images.populationGrowth, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ConstructionCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ConstructionCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.AdministrationBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("AdministrationBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.EconomyBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("EconomyBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.MilitaryBuildingsCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MilitaryBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.InvestInEconomyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.IncreaseTaxEfficiencyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.IncreaseGrowthRateCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseGrowthRateCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.IncreaseGrowthRateCost * 100.0f, 100) + "%", Images.populationUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.DevelopInfrastructureCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ProductionEfficiency != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProductionEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.Research != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ResearchPerMonth") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.Research, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.MonthlyLegacy != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MonthlyLegacy") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.MonthlyLegacy, 100), Images.legacy, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.GeneralAttack != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.GeneralAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.GeneralDefense != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.GeneralDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ArmyMaintenance != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ArmyMaintenance, 100) + "%", Images.armyMaintenance, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.RecruitArmyCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.RecruitArmyCost, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ConstructionTime != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionTime") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ConstructionTime * 100.0f, 100) + "%", Images.buildTime, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.IncreaseManpowerCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.RecruitmentTime != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RecruitmentTime") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.LoanInterest != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("LoanInterest") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.LoanInterest, 100) + "%", Images.loan, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.CoreCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("CoreConstruction") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.CoreCost, 100) + "%", Images.core, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ReligionCost != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ReligionConversionCost") + "", "" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ReligionCost, 100) + "%", Images.religion, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.IncomeProduction != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncomeProduction") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.IncomeProduction, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.MaxManpower != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MaximumManpower") + "", "+" + (int)Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.UnitsAttack != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.UnitsAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.UnitsDefense != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.UnitsDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.RegimentsLimit != 0) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RegimentsLimit") + "", "+" + CFG.getPrecision2((float)Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.RegimentsLimit, 1), Images.regimentsLimit, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ImproveRelationsModifier != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ImproveRelationsModifier") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ImproveRelationsModifier, 100) + "%", Images.relations, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ArmyMovementSpeed != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMovementSpeed") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.SiegeEffectiveness != 0.0f) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("SiegeEffectiveness") + "", "+" + CFG.getPrecision2(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.SiegeEffectiveness * 100.0f, 100) + "%", Images.siege, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                buttonX = paddingLeft;
            }
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonY - CFG.PADDING, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2));
            buttonY += CFG.PADDING;
            buttonY += ButtonAdvisor.getButtonHeight() + CFG.PADDING;
            if (InGame_Court.iActiveCivID == Game.player.iCivID || Game.SPECTATOR_MODE) {
                menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("BattleTactics"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, "") {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("BattleTacticsDesc"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                buttonX = paddingLeft;
                menuElements.add(new ButtonCurrentSituation(Game.lang.get(GameValues.battleTactics.BATTLE_TACTICS[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()]), Images.battle, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.BUTTON_HEIGHT2 * 2 - CFG.PADDING * 2, CFG.BUTTON_HEIGHT2, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 26) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_BattleTactics();
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("BattleTactics"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.battle, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("BattleTacticsDesc"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Value(((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.attack, buttonX, buttonY, CFG.BUTTON_HEIGHT2, CFG.BUTTON_HEIGHT2, Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    protected Color getColor(final boolean isActive) {
                        if (this.getIsHovered()) {
                            return super.getColor(isActive);
                        }
                        return (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Value(((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.defense, buttonX, buttonY, CFG.BUTTON_HEIGHT2, CFG.BUTTON_HEIGHT2, Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    protected Color getColor(final boolean isActive) {
                        if (this.getIsHovered()) {
                            return super.getColor(isActive);
                        }
                        return (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] == 0) ? Colors.HOVER_NEUTRAL : ((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE);
                    }
                });
                buttonX = paddingLeft;
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Military"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                menuElements.add(new ButtonCurrentSituation(Game.lang.get("ArmyControlledByAI") + ": " + (Game.player.allowAIMove ? Game.lang.get("On") : Game.lang.get("Off")), Images.ai, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                    @Override
                    public void actionElement() {
                        Game.player.allowAIMove = !Game.player.allowAIMove;
                        if (Game.menuManager.getVisibleInGame_Court()) {
                            Game.menuManager.rebuildInGame_CourtSavePos();
                            Game.menuManager.setVisibleInGame_Court(true);
                            InGame_Court.lTime = 0L;
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ArmyControlledByAI"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.ai, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                    
                    @Override
                    protected Color getColor(final boolean isActive) {
                        if (Game.player.allowAIMove) {
                            return Colors.getColorPositive(isActive, this.getIsHovered());
                        }
                        return super.getColor(isActive);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                menuElements.add(new ButtonCurrentSituation(Game.lang.get("Armies") + ": " + Game.lang.get("Colors"), Images.brush, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.rebuildInGame_CustomizeArmy();
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Armies") + ": " + Game.lang.get("Colors"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.brush, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                if (GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD || (GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_PLAYER_TRIBAL && Game.ideologiesManager.getIdeology(Game.getCiv(Game.player.iCivID).getIdeologyID()).TRIBAL)) {
                    menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Colonization"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    menuElements.add(new ButtonCurrentSituation(Game.lang.get("Colonize") + ": " + Game.lang.get("ChooseAProvince"), Images.populationGrowth, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                        @Override
                        public void actionElement() {
                            if (Game.getCiv(Game.player.iCivID).fGold < GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST) {
                                Game.menuManager.addToast_Error(Game.lang.get("InsufficientGold") + ": " + CFG.getPrecision2(GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST, 1), Images.gold);
                            }
                            if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                            }
                            else {
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                                Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE);
                            }
                        }
                        
                        @Override
                        protected Color getColor(final boolean isActive) {
                            if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
                                return Colors.HOVER_GOLD;
                            }
                            return super.getColor(isActive);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Colonize") + ": " + Game.lang.get("ChooseAProvince"), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.populationGrowth, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
            }
        }
        else if (InGame_Court.modeID == 11) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Back"), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public int getSFX() {
                    return SoundsManager.SOUND_CLICK_TOP;
                }
                
                @Override
                public void actionElement() {
                    InGame_Court.modeID = 1;
                    InGame_Court.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_Court();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    Game.setRegroupArmyMode(false);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getVassals()), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(Game_Ages.getReleaseAVassal()), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final int tempElementsBefore = menuElements.size();
            final List<Game.VassalsToRelease> listRelease = Game.getVassalsToRelease(InGame_Court.iActiveCivID);
            if (listRelease.size() > 0) {
                final int r0W0 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.25f);
                final int r0W2 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.75f);
                final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
                while (listRelease.size() > 0) {
                    int bestID = 0;
                    for (int j = listRelease.size() - 1; j > 0; --j) {
                        if (CFG.compareAlphabetic_TwoString(Game.getCiv(listRelease.get(bestID).iCivID).getCivName(), Game.getCiv(listRelease.get(j).iCivID).getCivName())) {
                            bestID = j;
                        }
                    }
                    menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv(listRelease.get(bestID).iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W2, buttonH, listRelease.get(bestID).iCivID) {
                        @Override
                        public void actionElement() {
                            InGame_ReleaseAVassal.buildData(Game.player.iCivID, this.getCurrent());
                            InGame_ReleaseAVassal.buildData_MapMode(Game.player.iCivID, this.getCurrent());
                            Game.menuManager.rebuildInGame_ReleaseAVassal();
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_RELEASE_VASSAL);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getReleaseAVassal()), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Flag(this.getCurrent(), 0, CFG.PADDING));
                            nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            final List<Integer> tProvinces = Game.getVassalsToRelease_Provinces(InGame_Court.iActiveCivID, this.getCurrent());
                            for (int i = 0; i < 10 && tProvinces.size() > 0; ++i) {
                                int bestID = 0;
                                for (int j = tProvinces.size() - 1; j > 0; --j) {
                                    if (Game.getProvince(tProvinces.get(bestID)).getPopulationTotal() < Game.getProvince(tProvinces.get(j)).getPopulationTotal()) {
                                        bestID = j;
                                    }
                                }
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.getProvince(tProvinces.get(bestID)).getProvinceName() + "  ", CFG.getNumberWithSpaces("" + Game.getProvince(tProvinces.get(bestID)).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                tProvinces.remove(bestID);
                            }
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    menuElements.add(new Text_StaticBG_ID_Special("" + listRelease.get(bestID).iNumOfProvinces, CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W0, buttonH, listRelease.get(bestID).iCivID) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", this.getText(), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX = paddingLeft;
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    listRelease.remove(bestID);
                }
            }
            if (tempElementsBefore == menuElements.size()) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        else if (InGame_Court.modeID == 1) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get(Game_Ages.getReleaseAVassal()), Images.vassal, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public int getSFX() {
                    return SoundsManager.SOUND_CLICK_TOP;
                }
                
                @Override
                public void actionElement() {
                    InGame_Court.modeID = 11;
                    InGame_Court.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_Court();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    Game.setRegroupArmyMode(false);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getReleaseAVassal()), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            if (Game.getCiv(Game.player.iCivID).getPuppetOfCivID() != Game.player.iCivID) {
                menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(Game_Ages.getLord()), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                final int buttonH2 = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
                final int statsRightW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 6) / 7;
                final int statsRightH = CFG.BUTTON_HEIGHT;
                final int emptyBGH = buttonH2 + CFG.PADDING + statsRightH;
                final int nCivID = Game.getCiv(Game.player.iCivID).getPuppetOfCivID();
                menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialCiv(Game.getCiv(nCivID).getCivName(), CFG.FONT_REGULAR, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH2, nCivID, Images.population, CFG.getNumberWithSpaces("" + Game.getCiv(nCivID).getPopulationTotal())) {
                    @Override
                    public void actionElement() {
                        if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                                Game.menuManager.hideCourtCiv();
                                Game.menuManager.setVisibleInGame_Civ(false);
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
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(this.id).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    public Color getColor2(final boolean isActive) {
                        return Colors.getColorPopulation(isActive, this.getIsHovered());
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight();
                buttonX = paddingLeft;
                menuElements.add(new TextIcon2_Value(Game.lang.get("Tribute"), CFG.FONT_REGULAR_SMALL, Images.tax, buttonX, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH, nCivID) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute"), "", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Value("" + CFG.getPrecision2(Game.getIncomeFromVassal(nCivID, Game.player.iCivID, Game.getCiv(nCivID).diplomacy.getVassal_TributeLevel(Game.player.iCivID)), 100), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH, nCivID) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[Game.getCiv(this.id).diplomacy.getVassal_TributeLevel(Game.player.iCivID)] * 100.0f, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(this.id, Game.player.iCivID, Game.getCiv(this.id).diplomacy.getVassal_TributeLevel(Game.player.iCivID)), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Value(Game.lang.get("Manpower"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH, nCivID) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower"), "", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                final double tVal = Game.getManpowerFromVassal_INFO(nCivID, Game.player.iCivID, Game.getCiv(nCivID).diplomacy.getVassal_ManpowerLevel(Game.player.iCivID));
                menuElements.add(new TextIcon2_Value("" + CFG.getPrecision2(tVal, 1), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY, statsRightW, statsRightH, nCivID) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.id, 0, CFG.PADDING));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.id).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        final double tVal = Game.getManpowerFromVassal_INFO(this.id, Game.player.iCivID, Game.getCiv(this.id).diplomacy.getVassal_ManpowerLevel(Game.player.iCivID));
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((tVal > 0.0) ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                buttonX = paddingLeft;
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonY - emptyBGH, menuWidth - paddingLeft2 * 2, emptyBGH));
                buttonY += CFG.PADDING;
            }
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(Game_Ages.getManageVassals()), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, "" + CFG.getNumberWithSpaces("" + Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize)));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final int tempElementsBefore = menuElements.size();
            try {
                if (Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize > 0) {
                    Graph_Vertical.graphCivs.clear();
                    for (int a = 0; a < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++a) {
                        Graph_Vertical.graphCivs.add(Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(a).c);
                    }
                    if (Graph_Vertical.graphCivs.size() == 1 || GameValues.court.COUNCIL_VIEW_VASSAL_GRAPH_INCLUDE_PLAYER) {
                        Graph_Vertical.graphCivs.add(Game.player.iCivID);
                    }
                    if (!Graph_Vertical.graphCivs.isEmpty()) {
                        if (Game.oR.nextInt(100) < 50) {
                            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_LIST_PROVINCES, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, (int)(menuWidth * 0.35f), true) {};
                            menuElements.add(graphVertical);
                        }
                        else {
                            final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_LIST_POPULATION, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, (int)(menuWidth * 0.35f), true) {};
                            menuElements.add(graphVertical);
                        }
                        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
                    }
                    final int buttonH3 = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
                    final int statsRightW2 = (menuWidth - paddingLeft * 2 - CFG.PADDING * 6) / 7;
                    final int statsRightH2 = CFG.BUTTON_HEIGHT;
                    final int emptyBGH2 = buttonH3 + CFG.PADDING * 2 + statsRightH2 * 2;
                    float goldFromVassals = 0.0f;
                    float manpowerFromVassals = 0.0f;
                    for (int k = 0; k < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++k) {
                        goldFromVassals += Game.getIncomeFromVassal(Game.player.iCivID, Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(k).c, Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(k).tL);
                        manpowerFromVassals += (float)Game.getManpowerFromVassal_INFO(Game.player.iCivID, Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(k).c, Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(k).mL);
                    }
                    final int maxIconW2 = ImageManager.getImage(Images.gold).getWidth();
                    final int statW2 = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
                    final int statH2 = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
                    menuElements.add(new ButtonStatsRectIMG_Bonuses("", ((goldFromVassals > 0.0f) ? "+" : "") + CFG.getPrecision2(goldFromVassals, 100), Images.gold, buttonX, buttonY, statW2, statH2, maxIconW2) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.lang.get("Lord")));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", "" + this.sText2, Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                        
                        @Override
                        public String getTextToDraw() {
                            if (InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL) {
                                float goldFromVassals = 0.0f;
                                for (int i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++i) {
                                    goldFromVassals += Game.getIncomeFromVassal(Game.player.iCivID, Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i).c, Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i).tL);
                                }
                                this.setText2(((goldFromVassals > 0.0f) ? "+" : "") + CFG.getPrecision2(goldFromVassals, 100));
                                InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = false;
                            }
                            return super.getTextToDraw();
                        }
                    });
                    menuElements.add(new ButtonStatsRectIMG_Bonuses("", ((manpowerFromVassals > 0.0f) ? "+" : "") + CFG.getPrecision2(manpowerFromVassals, 1), Game_Calendar.IMG_MANPOWER, buttonX + statW2 + CFG.PADDING, buttonY, statW2, statH2, maxIconW2) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.lang.get("Lord")));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "" + this.sText2, Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                        
                        @Override
                        public String getTextToDraw() {
                            if (InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL) {
                                float manpowerFromVassals = 0.0f;
                                for (int i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++i) {
                                    manpowerFromVassals += (float)Game.getManpowerFromVassal_INFO(Game.player.iCivID, Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i).c, Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i).mL);
                                }
                                this.setText2(((manpowerFromVassals > 0.0f) ? "+" : "") + CFG.getPrecision2(manpowerFromVassals, 1));
                            }
                            return super.getTextToDraw();
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    final List<Integer> tVassals = new ArrayList<Integer>();
                    for (int l = 0; l < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++l) {
                        if (Game.getCiv(Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(l).c).getNumOfProvinces() > 0) {
                            tVassals.add(l);
                        }
                    }
                    while (!tVassals.isEmpty()) {
                        int bestID2 = 0;
                        for (int m = tVassals.size() - 1; m > 0; --m) {
                            if (Game.getCiv(Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(tVassals.get(m)).c).getNumOfProvinces() > Game.getCiv(Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(tVassals.get(bestID2)).c).getNumOfProvinces()) {
                                bestID2 = m;
                            }
                        }
                        if (Game.getCiv(Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(tVassals.get(bestID2)).c).getNumOfProvinces() > 0) {
                            final int nCivID2 = Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(tVassals.get(bestID2)).c;
                            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialCiv(Game.getCiv(nCivID2).getCivName(), CFG.FONT_REGULAR, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH3, nCivID2, Images.population, CFG.getNumberWithSpaces("" + Game.getCiv(nCivID2).getPopulationTotal())) {
                                @Override
                                public void actionElement() {
                                    if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                                        if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                                            Game.menuManager.hideCourtCiv();
                                            Game.menuManager.setVisibleInGame_Civ(false);
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
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(this.id).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getCiv(this.id).getEconomyTotal(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    float fGold = Game.getCiv(this.id).fTotalIncomePerMonth;
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Income") + ": ", CFG.getPrecision2(fGold, 10), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    fGold = Game.getCiv(this.id).fTotalIncomePerMonth + Game.getCiv(this.id).civBonuses.MonthlyIncome - Game.getCiv(this.id).fTotalExpensesPerMonth;
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Balance") + ": ", CFG.getPrecision2(fGold, 10), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(Game.getCiv(this.id).fManpower, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", CFG.getPrecision2(Game.getCiv(this.id).fManpowerMax, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                                
                                @Override
                                public Color getColor2(final boolean isActive) {
                                    return Colors.getColorPopulation(isActive, this.getIsHovered());
                                }
                            });
                            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
                            buttonX = paddingLeft;
                            menuElements.add(new TextIcon2_Value(Game.lang.get("Tribute"), CFG.FONT_REGULAR_SMALL, Images.tax, buttonX, buttonY, statsRightW2 * 2 + CFG.PADDING, statsRightH2, nCivID2) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute"), "", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Low"), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW2, statsRightH2, nCivID2, 0) {
                                @Override
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_TributeLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return Colors.HOVER_GOLD;
                                        }
                                    });
                                }
                                
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[this.iLevel] * 100.0f, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, this.iLevel), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                                
                                @Override
                                public int getSFX() {
                                    return SoundsManager.SOUND_GOLD_LEVEL_0;
                                }
                                
                                @Override
                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Medium"), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW2, statsRightH2, nCivID2, 1) {
                                @Override
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_TributeLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return Colors.HOVER_GOLD;
                                        }
                                    });
                                }
                                
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[this.iLevel] * 100.0f, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, this.iLevel), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                                
                                @Override
                                public int getSFX() {
                                    return SoundsManager.SOUND_GOLD_LEVEL_1;
                                }
                                
                                @Override
                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("High"), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW2, statsRightH2, nCivID2, 2) {
                                @Override
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_TributeLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return Colors.HOVER_GOLD;
                                        }
                                    });
                                }
                                
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, 0, CFG.PADDING));
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[this.iLevel] * 100.0f, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, this.iLevel), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                                
                                @Override
                                public int getSFX() {
                                    return SoundsManager.SOUND_GOLD_LEVEL_2;
                                }
                                
                                @Override
                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value("" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, nCivID2, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(nCivID2)), 100), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW2 * 2 + CFG.PADDING, statsRightH2, nCivID2) {
                                int lastLevel = 0;
                                
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id)] * 100.0f, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id)), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                                
                                @Override
                                public void setCurrent(final int nCurrent) {
                                    this.lastLevel = nCurrent;
                                }
                                
                                @Override
                                public String getTextToDraw() {
                                    if (this.lastLevel != Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id)) {
                                        this.lastLevel = Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id);
                                        this.setText("" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, this.lastLevel), 100));
                                    }
                                    return super.getTextToDraw();
                                }
                            });
                            menuElements.get(menuElements.size() - 1).setCurrent(Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(nCivID2));
                            buttonX = paddingLeft;
                            buttonY += statsRightH2 + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value(Game.lang.get("Manpower"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW2 * 2 + CFG.PADDING, statsRightH2, nCivID2) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower"), "", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Low"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW2, statsRightH2, nCivID2, 0) {
                                @Override
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_ManpowerLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return DiplomacyManager.COLOR_WAR;
                                        }
                                    });
                                }
                                
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_MANPOWER_TO_LORD[this.iLevel] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    final double tVal = Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, this.iLevel);
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((tVal > 0.0) ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                                
                                @Override
                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Medium"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW2, statsRightH2, nCivID2, 1) {
                                @Override
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_ManpowerLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return DiplomacyManager.COLOR_WAR;
                                        }
                                    });
                                }
                                
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Medium"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_MANPOWER_TO_LORD[this.iLevel] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    final double tVal = Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, this.iLevel);
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((tVal > 0.0) ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                                
                                @Override
                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("High"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW2, statsRightH2, nCivID2, 2) {
                                @Override
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_ManpowerLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return DiplomacyManager.COLOR_WAR;
                                        }
                                    });
                                }
                                
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_MANPOWER_TO_LORD[this.iLevel] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    final double tVal = Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, this.iLevel);
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((tVal > 0.0) ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                                
                                @Override
                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            final double tVal2 = Game.getManpowerFromVassal_INFO(Game.player.iCivID, nCivID2, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(nCivID2));
                            menuElements.add(new TextIcon2_Value("" + CFG.getPrecision2(tVal2, 1), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY, statsRightW2, statsRightH2, nCivID2) {
                                int lastLevel = 0;
                                
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, 0, CFG.PADDING));
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    final double tVal = Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id));
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((tVal > 0.0) ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                                
                                @Override
                                public void setCurrent(final int nCurrent) {
                                    this.lastLevel = nCurrent;
                                }
                                
                                @Override
                                public String getTextToDraw() {
                                    if (this.lastLevel != Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id)) {
                                        this.lastLevel = Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id);
                                        this.setText("" + CFG.getPrecision2(Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, this.lastLevel), 1));
                                    }
                                    return super.getTextToDraw();
                                }
                            });
                            menuElements.get(menuElements.size() - 1).setCurrent(Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(nCivID2));
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Wars"), CFG.FONT_REGULAR_SMALL, Images.war, buttonX, buttonY, statsRightW2, statsRightH2, nCivID2, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_CanDeclareWar(nCivID2)) {
                                @Override
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_CanDeclareWar(this.id, !Game.getCiv(Game.player.iCivID).diplomacy.getVassal_CanDeclareWar(this.id));
                                    this.iLevel = (Game.getCiv(Game.player.iCivID).diplomacy.getVassal_CanDeclareWar(this.id) ? 1 : 0);
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return DiplomacyManager.COLOR_WAR;
                                        }
                                    });
                                }
                                
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CanDeclareWars"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG((this.iLevel == 1) ? Images.v : Images.x, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    if (this.iLevel == 0) {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LibertyDesire") + ": ", "+" + CFG.getPrecision2(GameValues.vassal.LIBERTY_DESIRE_CANT_DECLARE_WAR, 100), Images.revolutionRisk, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_NEGATIVE));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    this.menuElementHover = new MenuElement_Hover(nElements, this.iLevel != 0);
                                }
                                
                                @Override
                                public boolean isLeveLActive() {
                                    return 1 == this.iLevel;
                                }
                                
                                @Override
                                public float getScale() {
                                    return Math.min(1.0f, this.getImageScale(this.imageID));
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            buttonX = paddingLeft;
                            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonY - emptyBGH2, menuWidth - paddingLeft2 * 2, emptyBGH2));
                            buttonY += CFG.PADDING;
                        }
                        tVassals.remove(bestID2);
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            if (tempElementsBefore == menuElements.size()) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        else if (InGame_Court.modeID == 2) {
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Tutorial"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Encyclopedia"), Images.encyclopedia, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 15) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        InGame_Encyclopedia.sSearch = "";
                        Game.menuManager.rebuildInGame_Encyclopedia();
                    }
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Encyclopedia"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.encyclopedia, CFG.PADDING, 0));
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
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Goods"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, "") {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Production2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("LargestGoodsProducers"), Images.goods, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Goods();
                    Game.menuManager.setVisibleInGame_Goods(true);
                    InGame_Goods.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Production2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Search"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Search") + ": " + Game.lang.get("Civilizations"), Images.world, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    InGame_Court_WorldCivs.sSearch = "";
                    Game.menuManager.rebuildInGame_CourtSearchCivs();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Search") + ": " + Game.lang.get("Civilizations"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.world, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Search") + ": " + Game.lang.get("Provinces"), Images.world, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_Court.actionSearchProvinces();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Search") + ": " + Game.lang.get("Provinces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.world, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (CFG.isDesktop()) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                        nData.add(new MenuElement_HoverElement_Type_Text("F", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.world, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    this.menuElementHover = new MenuElement_Hover(nElements, !CFG.isDesktop());
                }
                
                @Override
                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Statistics"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Missions") + ": " + Game.lang.get("GoldenAge"), Images.goldenGold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtGoldenAges();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Missions") + ": " + Game.lang.get("GoldenAge"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goldenGold, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Statistics"), Images.stats, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtStatistics();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Statistics"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.stats, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Graph"), Images.stats, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_GraphPopulation.activeModeID = 0;
                    Game.menuManager.rebuildInGame_GraphPopulation();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Graph"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.stats, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("CivilizationBonuses"), CivilizationRanking.getCivilizationRank_IMG(Game.player.iCivID), paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_CivBonuses.iCivID = Game.player.iCivID;
                    InGame_Civ.actionCivBonuses();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CivilizationBonuses"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(CivilizationRanking.getCivilizationRank_IMG(Game.player.iCivID), CFG.PADDING, 0));
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
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("More"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Console"), Images.console, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_Console()) {
                        Game.menuManager.setVisibleInGame_Console(false);
                    }
                    else {
                        InGame_CourtOptions2.disableAllViews();
                        Game.menuManager.rebuildInGame_Console();
                        Game.menuManager.setVisibleInGame_Court(false);
                    }
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Console"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.console, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Hide") + ": UI", Images.x, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_HideUI.addDate = false;
                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME_HIDE_UI);
                    Game.menuManager.addToastGold(Game.lang.get("Close") + " - Click the TOP LEFT", Images.x);
                    Game.menuManager.addToastGold(Game.lang.get("Close") + " - Click the TOP LEFT", Images.x);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Hide") + ": UI", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.x, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Hide") + ": UI - " + Game.lang.get("Date"), Images.x, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_HideUI.addDate = true;
                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME_HIDE_UI);
                    Game.menuManager.addToastGold(Game.lang.get("Close") + " - Click the TOP LEFT", Images.x);
                    Game.menuManager.addToastGold(Game.lang.get("Close") + " - Click the TOP LEFT", Images.x);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Hide") + ": UI - " + Game.lang.get("Date"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.x, CFG.PADDING, 0));
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
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Diplomacy"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("CurrentWars"), Images.war, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Wars();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("CurrentWars"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Alliances"), Images.alliance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Alliances();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Alliances"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get(Game_Ages.getVassals()), Images.vassal, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Vassals();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get(Game_Ages.getVassals()), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("DefensivePacts"), Images.defensivePact, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Defensive();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("DefensivePacts"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.defensivePact, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("NonAggressionPacts"), Images.nonAggression, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_NonAggression();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("NonAggressionPacts"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nonAggression, CFG.PADDING, 0));
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
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Truces"), Images.truce, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Truces();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Truces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.truce, CFG.PADDING, 0));
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
        }
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get(GameValues.court.COUNCIL_NAME));
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
        if (!visible && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }
    
    public static final void actionSearchProvinces() {
        InGame_Court.inCourt = false;
        InGame_Court.inSearchProvinces = true;
        InGame_CourtOptions.disableAllViews();
        InGame_Court_WorldSearch.sSearch = "";
        Game.menuManager.rebuildInGame_CourtSearch();
        Game.menuManager.setVisibleInGame_Court(true);
        InGame_Court.lTime = 0L;
    }
    
    static {
        InGame_Court.lTime = 0L;
        InGame_Court.lTime2 = 0L;
        InGame_Court.iActiveCivID = 0;
        InGame_Court.FIRE_ID = 0;
        InGame_Court.inCourt = true;
        InGame_Court.inSearchProvinces = false;
        InGame_Court.modeID = 0;
        InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = false;
    }
}
