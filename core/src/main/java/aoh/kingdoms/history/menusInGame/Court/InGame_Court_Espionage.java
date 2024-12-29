// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonTechnology;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.menu_element.textStatic.TextArmies;
import aoh.kingdoms.history.map.army.ArmyRegiment;
import aoh.kingdoms.history.menu_element.button.ButtonArmyGeneral2_Armies;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.button.ButtonArmyNoGeneral2_Armies;
import aoh.kingdoms.history.menu_element.button.ButtonArmyBattle;
import aoh.kingdoms.history.menusInGame.InGame_Armies;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.map.battles.BattleManager;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Balance_Spy;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Right2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Style;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonFlag;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_Espionage extends Menu
{
    public int iCivID;
    public int iReportEndTurnID;
    
    public InGame_Court_Espionage(final int iCivID, final int iReportEndTurnID) {
        this.iCivID = 1;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.iCivID = iCivID;
        this.iReportEndTurnID = iReportEndTurnID;
        int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING * 2;
        final int rightW = menuWidth - paddingLeft * 2 - ButtonFlag.getButtonWidth() - CFG.PADDING * 2;
        final int rightH = (ButtonFlag.getButtonHeight() - CFG.PADDING) / 2;
        menuElements.add(new ButtonFlag(iCivID, buttonX, buttonY, true) {
            @Override
            public int getFlagCivID() {
                return this.iCivID;
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_Civ() && InGame_Civ.iActiveCivID == this.getCurrent()) {
                    Game.menuManager.setVisibleInGame_Civ(false);
                }
                else {
                    final boolean resetTime = Game.menuManager.getVisibleInGame_Civ();
                    InGame_Civ.iRebuildToCivID = this.iCivID;
                    Game.menuManager.rebuildInGame_Civ();
                    if (resetTime) {
                        InGame_Civ.lTime = 0L;
                    }
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCivID, CivilizationRanking.getCivilizationRank_Name(Game.getCiv(this.iCivID).iCivRankID)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        menuElements.add(new Text_StaticBG_RulerTitle(Game.getCiv(iCivID).getCivName(), buttonX + ButtonFlag.getButtonWidth() + CFG.PADDING * 2, buttonY, rightW, rightH) {
            @Override
            public void actionElement() {
                Game.mapCoords.centerToProvinceID(Game.getCiv(iCivID).getCapitalProvinceID());
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CenterToCivilization"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(iCivID).getCapitalProvinceID() >= 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Capital") + ": ", CFG.FONT_BOLD, Colors.HOVER_LEFT));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).getProvinceName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements, Game.getCiv(iCivID).getCapitalProvinceID() < 0);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(iReportEndTurnID), Images.time, buttonX + ButtonFlag.getButtonWidth() + CFG.PADDING * 2, buttonY + rightH + CFG.PADDING, rightW, rightH, ImageManager.getImage(Images.gold).getWidth()));
        buttonY += ButtonFlag.getButtonHeight() + CFG.PADDING;
        paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int tIconMaxW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD), "+999 999");
        final int tButtonRightW = (int)Renderer.glyphLayout.width + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonW = menuWidth - paddingLeft * 2 - CFG.PADDING - tButtonRightW;
        final int iRow = 0;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Budget"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("TotalIncome"), "", Images.goldPositive, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float fVal = Game.getCiv(iCivID).fTotalIncomePerMonth + Game.getCiv(iCivID).civBonuses.MonthlyIncome;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TotalIncome") + ": ", "" + ((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100), Images.gold, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2("+" + CFG.getPrecision2(Game.getCiv(iCivID).fTotalIncomePerMonth + Game.getCiv(iCivID).civBonuses.MonthlyIncome, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(iCivID).fTotalIncomePerMonth + Game.getCiv(iCivID).civBonuses.MonthlyIncome) {
                    final float fVal = Game.getCiv(iCivID).fTotalIncomePerMonth + Game.getCiv(iCivID).civBonuses.MonthlyIncome;
                    this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100));
                    this.lastValue = fVal;
                    if (fVal == 0.0f) {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_NEUTRAL;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_ACTIVE;
                    }
                    else if (fVal > 0.0f) {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
                    }
                    else {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
                    }
                }
                return super.getTextToDraw();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        float fGold = Game.getCiv(iCivID).fTotalExpensesPerMonth;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("TotalExpenses"), "", Images.goldNegative, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float fVal = Game.getCiv(iCivID).fTotalExpensesPerMonth;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TotalExpenses") + ": ", "" + ((fVal > 0.0f) ? "-" : "") + CFG.getPrecision2(fVal, 100), Images.goldNegative, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_POSITIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2(((fGold >= 0.0f) ? "+" : "-") + CFG.getPrecision2(Game.getCiv(iCivID).fTotalExpensesPerMonth, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(iCivID).fTotalExpensesPerMonth) {
                    final float fVal = Game.getCiv(iCivID).fTotalExpensesPerMonth;
                    this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getPrecision2(fVal, 100));
                    this.lastValue = fVal;
                    if (fVal == 0.0f) {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_NEUTRAL;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_ACTIVE;
                    }
                    else if (fVal > 0.0f) {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
                    }
                    else {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
                    }
                }
                return super.getTextToDraw();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(iCivID).fTotalIncomePerMonth + Game.getCiv(iCivID).civBonuses.MonthlyIncome - Game.getCiv(iCivID).fTotalExpensesPerMonth;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Balance"), "", Images.gold, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float fVal = Game.getCiv(iCivID).fTotalIncomePerMonth + Game.getCiv(iCivID).civBonuses.MonthlyIncome - Game.getCiv(iCivID).fTotalExpensesPerMonth;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TotalIncome") + ": ", "" + ((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100), Images.gold, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2(((fGold >= 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(iCivID).fTotalIncomePerMonth + Game.getCiv(iCivID).civBonuses.MonthlyIncome - Game.getCiv(iCivID).fTotalExpensesPerMonth) {
                    final float fVal = Game.getCiv(iCivID).fTotalIncomePerMonth + Game.getCiv(iCivID).civBonuses.MonthlyIncome - Game.getCiv(iCivID).fTotalExpensesPerMonth;
                    this.setText2(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100));
                    this.lastValue = fVal;
                    if (fVal == 0.0f) {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_NEUTRAL;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_ACTIVE;
                    }
                    else if (fVal > 0.0f) {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
                    }
                    else {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
                    }
                }
                return super.getTextToDraw();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(iCivID).fGold;
        menuElements.add(new ButtonStatsBudget_Balance_Spy(Game.lang.get("Treasury") + ": ", ((fGold > 0.0f) ? "" : "") + CFG.getPrecision2(fGold, 100), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 10) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(iCivID, CivilizationRanking.getCivilizationRank_Name(Game.getCiv(iCivID).iCivRankID)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final float fVal = Game.getCiv(iCivID).fGold;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Treasury") + ": ", "" + ((fVal > 0.0f) ? "" : "") + CFG.getPrecision2(fVal, 100), Images.gold, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public String getText2() {
                if (this.lastValue != Game.getCiv(iCivID).fGold) {
                    final float fGold = Game.getCiv(iCivID).fGold;
                    this.setText2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100));
                    this.lastValue = fGold;
                    if (fGold == 0.0f) {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_NEUTRAL;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_ACTIVE;
                    }
                    else if (fGold > 0.0f) {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
                    }
                    else {
                        this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                        this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
                        this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
                    }
                }
                return this.sText2;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Armies"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Manpower") + ": ", "" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.getCiv(iCivID).fManpower, 1)), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("MaximumManpower") + ": ", "" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.getCiv(iCivID).fManpowerMax, 1)), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("ManpowerPerMonth") + ": ", "+" + CFG.getPrecision2(Game.getCiv(iCivID).fManpowerPerMonth, 10), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int extraX = 0;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("BattleWidth") + ": ", "" + BattleManager.getBattleWidth(iCivID), Images.battleWidth, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.battleWidth).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("BattleWidth") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + BattleManager.getBattleWidth(iCivID), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.battleWidth, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("BattleWidthRefersToTheNumberOfUnitsThatCanBeDeployedInAFormationOnTheBattlefield"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Discipline") + ": ", "" + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.Discipline * 100.0f, 10) + "%", Images.discipline, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.battleWidth).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Discipline") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.Discipline * 100.0f, 10) + "%", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.discipline, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("DisciplineSignifiesOrganizationAndCoordinationOfTheArmyInBattleIncreasingUnitsAttack"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("NumberOfRegiments") + ": ", "" + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)Game.getCiv(iCivID).getArmyRegimentSize(), 1)), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (Game.getCiv(iCivID).iArmyPositionSize == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None") + ".", CFG.FONT_REGULAR_SMALL, -1, paddingLeft + extraX, buttonY, menuWidth - paddingLeft * 2, ImageManager.getImage(Images.generalFrameBattle).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        else {
            buttonX = paddingLeft;
            final List<InGame_Armies.SortedArmies> toSort = new ArrayList<InGame_Armies.SortedArmies>();
            final List<InGame_Armies.SortedArmies> sortedArmies = new ArrayList<InGame_Armies.SortedArmies>();
            for (int i = 0; i < Game.getCiv(iCivID).iArmyPositionSize; ++i) {
                final int nProvinceID = Game.getCiv(iCivID).getArmyPosition(i);
                if (nProvinceID >= 0) {
                    for (int j = Game.getProvince(nProvinceID).getArmySize() - 1; j >= 0; --j) {
                        if (Game.getProvince(nProvinceID).getArmy(j).civID == iCivID && Game.getCiv(iCivID).getArmyPositionKey(i).equals(Game.getProvince(nProvinceID).getArmy(j).key)) {
                            toSort.add(new InGame_Armies.SortedArmies(nProvinceID, j, InGame_Armies.getSortKey(Game.getProvince(nProvinceID).getArmy(j).key)));
                        }
                    }
                }
            }
            while (toSort.size() > 0) {
                int addID = 0;
                for (int k = 1, iSize = toSort.size(); k < iSize; ++k) {
                    if (toSort.get(addID).sortKey > toSort.get(k).sortKey) {
                        addID = k;
                    }
                }
                sortedArmies.add(toSort.get(addID));
                toSort.remove(addID);
            }
            final int pinH = (ButtonArmyBattle.getButtonHeight() - CFG.PADDING) / 2;
            for (int k = 0, iSize = sortedArmies.size(); k < iSize; ++k) {
                final int tTitleY = buttonY;
                buttonY += CFG.TEXT_HEIGHT + CFG.PADDING * 6;
                buttonX += CFG.PADDING;
                if (Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral == null) {
                    menuElements.add(new ButtonArmyNoGeneral2_Armies(Game.lang.get("NoGeneral"), iCivID, buttonX + extraX, buttonY, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).key, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).civID, sortedArmies.get(k).iProvinceID) {
                        @Override
                        public void actionElement() {
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoGeneral"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.iCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                }
                else {
                    menuElements.add(new ButtonArmyGeneral2_Armies(Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral.n, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).civID, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral.getAttack(), Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral.getDefense(), buttonX + extraX, buttonY, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral.g, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral.d, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral.m, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral.y, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).key, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).civID, sortedArmies.get(k).iProvinceID, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral.sI, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).armyGeneral.getCombatExperience()) {
                        @Override
                        public void actionElement() {
                        }
                    });
                }
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                for (int l = 0; l < Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).iArmyRegimentSize; ++l) {
                    int tUnits = Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).lArmyRegiment.get(l).num;
                    int numOfRegiments = 1;
                    for (int o = l + 1; o < Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).iArmyRegimentSize && Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).lArmyRegiment.get(l).uID == Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).lArmyRegiment.get(o).uID && Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).lArmyRegiment.get(l).aID == Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).lArmyRegiment.get(o).aID; ++l, ++numOfRegiments, tUnits += Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).lArmyRegiment.get(l).num, ++o) {}
                    menuElements.add(new ButtonArmyBattle("" + tUnits, numOfRegiments, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).civID, buttonX + extraX, buttonY, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).lArmyRegiment.get(l).uID, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).lArmyRegiment.get(l).aID, false) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                buttonY = menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding + CFG.PADDING;
                menuElements.add(new TextArmies(Game.lang.get("Army"), CFG.getNumberWithSpaces("" + Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).iArmy), paddingLeft + extraX, tTitleY, Math.max(buttonX, menuWidth - paddingLeft * 2), CFG.TEXT_HEIGHT + CFG.PADDING * 5, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).key, Game.getProvince(sortedArmies.get(k).iProvinceID).getArmy(sortedArmies.get(k).iArmyID).civID, sortedArmies.get(k).iProvinceID, menuWidth - paddingLeft * 2) {
                    @Override
                    public void actionElement() {
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = null;
                    }
                });
                buttonX = paddingLeft;
            }
        }
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Technology"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("UnlockedTechnologies") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(iCivID).getResearchedTechnologies()), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("ResearchPerMonth") + ": ", "" + CFG.getPrecision2(Game.getCiv(iCivID).fResearchPerMonth, 100), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        int techID = -1;
        if (Game.getCiv(iCivID).getActiveTechResearch() >= 0) {
            techID = Game.getCiv(iCivID).getActiveTechResearch();
        }
        else if (Game.getCiv(iCivID).getAlternativeTechResearch() >= 0) {
            techID = Game.getCiv(iCivID).getAlternativeTechResearch();
        }
        buttonX = paddingLeft;
        if (techID >= 0) {
            final int statW = menuWidth - paddingLeft * 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.techGray).getWidth();
            menuElements.add(new TextIcon2("" + CFG.getPrecision2(TechnologyTree.getResearchCost(techID, iCivID), 10), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, statW, ImageManager.getImage(Images.techGray).getHeight()) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ResearchCost") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.COLOR_TEXT_RESEARCH));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public int getTextH() {
                    return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new ButtonTechnology(Images.techBlue, techID, buttonX, buttonY, false, -1, iCivID) {
                @Override
                public void actionElement() {
                }
            });
        }
        buttonX = paddingLeft;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("More"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (Game.getCiv(iCivID).getNukes() > 0 || Game.getCiv(iCivID).canBuildNuke) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("AtomicBombs") + ": ", "" + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)Game.getCiv(iCivID).getNukes(), 1)), Images.nuke, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.gold).getWidth()) {
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_GOLD;
                }
                
                @Override
                protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("LegacyPoints") + ": ", "" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.getCiv(iCivID).fLegacy, 1)), Images.legacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Loans") + ": ", "" + Game.getCiv(iCivID).iLoansSize + " / " + Game.getLoanMaxNumber(iCivID), Images.loan, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("EspionageReports"));
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
        if (Game_Calendar.TURN_ID >= this.iReportEndTurnID) {
            InGame_Court.iActiveCivID = Game.player.iCivID;
            Game.menuManager.rebuildInGame_Court();
            Game.menuManager.setVisibleInGame_Court(true);
        }
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
