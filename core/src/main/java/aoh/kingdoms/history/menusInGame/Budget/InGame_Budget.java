// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Budget;

import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.map.Loan;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.map.WondersManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.allianceHRE.HREManager;
import aoh.kingdoms.history.map.allianceHRE.Alliance;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Resource;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.MenuManager;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Icon;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Right2;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Graph;
import aoh.kingdoms.history.menu_element.graph.Graph;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Balance;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Balance_Spy;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_Diplomacy;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Budget extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public int iActiveCivID;
    
    public InGame_Budget() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = paddingLeft;
        int buttonY = 0;
        this.iActiveCivID = Game.player.iCivID;
        final int tIconMaxW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        final int tButtonH2 = CFG.BUTTON_HEIGHT * 4 / 5;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD), "+999 999");
        final int tButtonRightW = (int)Renderer.glyphLayout.width + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonW = menuWidth - paddingLeft * 2 - CFG.PADDING - tButtonRightW;
        int iRow = 0;
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Budget"), Images.boxTitleBORDERWIDTH, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 2, CFG.BUTTON_HEIGHT4, true) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetBalanceProvinces();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Provinces"), Images.boxTitleBORDERWIDTH + (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 2, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 2, CFG.BUTTON_HEIGHT4, false) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetBalanceProvinces();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
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
        float fGold = Game.getCiv(this.iActiveCivID).fGold;
        menuElements.add(new ButtonStatsBudget_Balance_Spy(Game.lang.get("Treasury") + ": ", ((fGold > 0.0f) ? "" : "") + CFG.getPrecision2(fGold, 100), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, Math.max(CFG.TEXT_HEIGHT, ImageManager.getImage(Images.gold).getHeight()) + CFG.PADDING * 6) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Budget.this.iActiveCivID, CivilizationRanking.getCivilizationRank_Name(Game.getCiv(InGame_Budget.this.iActiveCivID).iCivRankID)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final float fVal = Game.getCiv(InGame_Budget.this.iActiveCivID).fGold;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Treasury") + ": ", "" + ((fVal > 0.0f) ? "" : "") + CFG.getPrecision2(fVal, 100), Images.gold, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public String getText2() {
                if (this.lastValue != Game.getCiv(InGame_Budget.this.iActiveCivID).fGold) {
                    final float fGold = Game.getCiv(InGame_Budget.this.iActiveCivID).fGold;
                    this.setText2(((fGold >= 100000.0f) ? CFG.getShortNumber((int)fGold) : ((fGold >= 1000.0f) ? CFG.getNumberWithSpaces("" + (int)fGold) : CFG.getPrecision2(fGold, (fGold >= 100.0f) ? 10 : 100))) + " / " + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)Game.getMaxAmountOfGold(Game.player.iCivID), 1)));
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
        fGold = Game.getCiv(this.iActiveCivID).getBalance();
        menuElements.add(new ButtonStatsBudget_Balance(Game.lang.get("Balance") + ": ", ((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 10) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Balance") + ": " + Game.lang.get("Provinces"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final float fVal = Game.getCiv(Game.player.iCivID).getBalance();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Balance") + ": ", "" + ((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100), Images.gold, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                try {
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Graph(Game.lang.get("Balance"), Graph.GraphType.PLAYER_BALANCE, true));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetBalanceProvinces();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Right2("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(this.iActiveCivID).civBonuses.MonthlyIncome, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY + 1 + (int)Math.ceil((CFG.TEXT_HEIGHT + CFG.PADDING * 6 - tButtonH) / 2.0f), tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(InGame_Budget.this.iActiveCivID).civBonuses.MonthlyIncome) {
                    final float fVal = Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(InGame_Budget.this.iActiveCivID).civBonuses.MonthlyIncome;
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
        menuElements.add(new Text_Title_v2(Game.lang.get("MonthlyIncome"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("TaxationLevel"), Images.tax, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 3 - tButtonH2 * 3, tButtonH2, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetIncomeProvinces();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Income") + ": " + Game.lang.get("Provinces"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Icon(Images.gold, buttonX, buttonY, tButtonH2, tButtonH2, 0) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_GOLD_LEVEL_0;
            }
            
            @Override
            public boolean isLeveLActive() {
                return this.iLevel == Game.getCiv(Game.player.iCivID).getTaxationLevel();
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).updateTaxationLevel(0);
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Budget.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Budget.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    @Override
                    public Color getColor() {
                        return Colors.HOVER_GOLD;
                    }
                });
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Icon(Images.gold, buttonX, buttonY, tButtonH2, tButtonH2, 1) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_GOLD_LEVEL_1;
            }
            
            @Override
            public boolean isLeveLActive() {
                return this.iLevel == Game.getCiv(Game.player.iCivID).getTaxationLevel();
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).updateTaxationLevel(1);
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Budget.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Budget.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    @Override
                    public Color getColor() {
                        return Colors.HOVER_GOLD;
                    }
                });
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Icon(Images.gold, buttonX, buttonY, tButtonH2, tButtonH2, 2) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_GOLD_LEVEL_2;
            }
            
            @Override
            public boolean isLeveLActive() {
                return this.iLevel == Game.getCiv(Game.player.iCivID).getTaxationLevel();
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).updateTaxationLevel(2);
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Budget.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Budget.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    @Override
                    public Color getColor() {
                        return Colors.HOVER_GOLD;
                    }
                });
            }
            
            @Override
            protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                if (this.isLeveLActive()) {
                    oSB.setColor(1.0f, 1.0f, 1.0f, this.isLeveLActive() ? 1.0f : (this.getIsHovered() ? 0.65f : 0.15f));
                    ImageManager.getImage(Images.revolutionRisk).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.revolutionRisk).getWidth() / 2 + iTranslateX, this.getPosY() + (this.getHeight() - ImageManager.getImage(Images.revolutionRisk).getHeight()) / 2 + iTranslateY, ImageManager.getImage(Images.revolutionRisk).getWidth(), ImageManager.getImage(Images.revolutionRisk).getHeight());
                    oSB.setColor(Color.WHITE);
                }
                else {
                    super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        buttonX = paddingLeft;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(this.iActiveCivID).getIncomeTaxation();
        menuElements.add(new ButtonStatsBudget(Game.lang.get("IncomeTaxation"), Images.tax, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetIncomeTaxation();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MonthlyIncomeTaxation"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.tax, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).getIncomeTaxation()) {
                    final float fVal = Game.getCiv(Game.player.iCivID).getIncomeTaxation();
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float fGold = Game.getCiv(InGame_Budget.this.iActiveCivID).getIncomeTaxation();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeTaxation") + ": ", ((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final List<ProvValue> tProvinces = new ArrayList<ProvValue>();
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    tProvinces.add(new ProvValue(Game.getCiv(Game.player.iCivID).getProvinceID(i), Game.getIncomePopulationTaxation(Game.getCiv(Game.player.iCivID).getProvinceID(i))));
                }
                int tAdded = 0;
                while (tProvinces.size() > 0 && tAdded++ < 10) {
                    int bestID = 0;
                    for (int j = tProvinces.size() - 1; j > 0; --j) {
                        if (tProvinces.get(j).fValue > tProvinces.get(bestID).fValue) {
                            bestID = j;
                        }
                    }
                    if (tProvinces.get(bestID).fValue == 0.0f) {
                        break;
                    }
                    nData.add(new MenuElement_HoverElement_Type_Text("" + tAdded + ". ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(tProvinces.get(bestID).iProvinceID).getProvinceName() + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(((tProvinces.get(bestID).fValue > 0.0f) ? "+" : "") + CFG.getPrecision2(tProvinces.get(bestID).fValue, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    tProvinces.remove(bestID);
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(this.iActiveCivID).getIncomeEconomy();
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Economy"), Game_Calendar.IMG_ECONOMY, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetIncomeEconomy();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MonthlyIncomeEconomy"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).getIncomeEconomy()) {
                    final float fVal = Game.getCiv(Game.player.iCivID).getIncomeEconomy();
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float fGold = Game.getCiv(InGame_Budget.this.iActiveCivID).getIncomeEconomy();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", ((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final List<ProvValue> tProvinces = new ArrayList<ProvValue>();
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    tProvinces.add(new ProvValue(Game.getCiv(Game.player.iCivID).getProvinceID(i), Game.getIncomeFromEconomy(Game.getCiv(Game.player.iCivID).getProvinceID(i))));
                }
                int tAdded = 0;
                while (tProvinces.size() > 0 && tAdded++ < 10) {
                    int bestID = 0;
                    for (int j = tProvinces.size() - 1; j > 0; --j) {
                        if (tProvinces.get(j).fValue > tProvinces.get(bestID).fValue) {
                            bestID = j;
                        }
                    }
                    if (tProvinces.get(bestID).fValue == 0.0f) {
                        break;
                    }
                    nData.add(new MenuElement_HoverElement_Type_Text("" + tAdded + ". ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(tProvinces.get(bestID).iProvinceID).getProvinceName() + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(((tProvinces.get(bestID).fValue > 0.0f) ? "+" : "") + CFG.getPrecision2(tProvinces.get(bestID).fValue, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    tProvinces.remove(bestID);
                }
                if (tAdded > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProsperityTier") + ": ", CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fProsperity_AverageEconomy, 100), Images.goldPositive, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(this.iActiveCivID).getIncomeProduction();
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Production"), Images.goods, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetIncomeProduction();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("IncomeProduction"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).getIncomeProduction()) {
                    final float fVal = Game.getCiv(Game.player.iCivID).getIncomeProduction();
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float fGold = Game.getCiv(InGame_Budget.this.iActiveCivID).getIncomeProduction();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeProduction") + ": ", ((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final List<ProvValue> tProvinces = new ArrayList<ProvValue>();
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getResourceID() >= 0) {
                        tProvinces.add(new ProvValue(Game.getCiv(Game.player.iCivID).getProvinceID(i), ResourcesManager.getMonthlyIncome(Game.getCiv(Game.player.iCivID).getProvinceID(i), Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getResourceID())));
                    }
                }
                int tAdded = 0;
                if (tProvinces.size() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                while (tProvinces.size() > 0 && tAdded++ < 10) {
                    int bestID = 0;
                    for (int j = tProvinces.size() - 1; j > 0; --j) {
                        if (tProvinces.get(j).fValue > tProvinces.get(bestID).fValue) {
                            bestID = j;
                        }
                    }
                    if (tProvinces.get(bestID).fValue == 0.0f) {
                        break;
                    }
                    if (Game.getProvince(tProvinces.get(bestID).iProvinceID).getResourceID() >= 0) {
                        nData.add(new MenuElement_HoverElement_Type_Text("" + tAdded + ". ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(tProvinces.get(bestID).iProvinceID).getProvinceName() + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text(((tProvinces.get(bestID).fValue > 0.0f) ? "+" : "") + CFG.getPrecision2(tProvinces.get(bestID).fValue, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(bestID).iProvinceID).getResourceID()), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT2));
                        nData.add(new MenuElement_HoverElement_Type_Resource(Game.getProvince(tProvinces.get(bestID).iProvinceID).getResourceID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    tProvinces.remove(bestID);
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(this.iActiveCivID).getIncomeBuildings();
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Buildings"), Images.buildings, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetIncomeBuildings();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Income") + ": " + Game.lang.get("Buildings"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.buildings, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 1000), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).getIncomeBuildings()) {
                    final float fVal = Game.getCiv(Game.player.iCivID).getIncomeBuildings();
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
        fGold = 0.0f;
        for (int i = 0; i < Game.getCiv(Game.player.iCivID).inAllianceSize; ++i) {
            if (Game.alliancesSpecial.get(Game.getCiv(Game.player.iCivID).inAlliance.get(i)).iLeaderCivID == Game.player.iCivID && Game.alliancesSpecial.get(Game.getCiv(Game.player.iCivID).inAlliance.get(i)).typeOfAlliance == 0) {
                fGold += HREManager.getIncome_Emperor(Game.getCiv(Game.player.iCivID).inAlliance.get(i));
            }
        }
        fGold += Game.getCiv(this.iActiveCivID).civBonuses.MonthlyIncome + Game.getCiv(this.iActiveCivID).fIncomeLord + ((this.iActiveCivID == Game.getCiv(this.iActiveCivID).getPuppetOfCivID()) ? GameValues.civRank.CIV_BASE_INCOME[Game.getCiv(this.iActiveCivID).iCivRankID] : GameValues.civRank.CIV_BASE_INCOME_VASSAL[Game.getCiv(this.iActiveCivID).iCivRankID]);
        menuElements.add(new ButtonStatsBudget(Game.lang.get("AdditionalIncome"), Images.diplomacy, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0));
        menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH, fGold) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", this.getText(), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (InGame_Budget.this.iActiveCivID == Game.getCiv(InGame_Budget.this.iActiveCivID).getPuppetOfCivID()) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BaseValue") + ": ", CFG.getPrecision2(GameValues.civRank.CIV_BASE_INCOME[Game.getCiv(InGame_Budget.this.iActiveCivID).iCivRankID], 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Vassal") + ", " + Game.lang.get("BaseValue") + ": ", CFG.getPrecision2(GameValues.civRank.CIV_BASE_INCOME_VASSAL[Game.getCiv(InGame_Budget.this.iActiveCivID).iCivRankID], 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                }
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final List<Integer> tProvinces = new ArrayList<Integer>();
                for (int i = 0; i < Game.getCiv(InGame_Budget.this.iActiveCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(InGame_Budget.this.iActiveCivID).getProvinceID(i)).wonderID >= 0 && Game.getProvince(Game.getCiv(InGame_Budget.this.iActiveCivID).getProvinceID(i)).getWonderBuilt() && WondersManager.wonders.get(Game.getProvince(Game.getCiv(InGame_Budget.this.iActiveCivID).getProvinceID(i)).wonderID).MonthlyIncome != 0.0f) {
                        tProvinces.add(Game.getCiv(InGame_Budget.this.iActiveCivID).getProvinceID(i));
                    }
                }
                if (tProvinces.size() != 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    for (int i = 0; i < tProvinces.size(); ++i) {
                        final float fGold = WondersManager.wonders.get(Game.getProvince(tProvinces.get(i)).wonderID).MonthlyIncome;
                        nData.add(new MenuElement_HoverElement_Type_Text(WondersManager.wonders.get(Game.getProvince(tProvinces.get(i)).wonderID).Name + ": ", CFG.FONT_BOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                try {
                    if (Game.getCiv(InGame_Budget.this.iActiveCivID).ruler.rulerBonuses.MonthlyIncome != 0.0f) {
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.ideologiesManager.getIdeology(Game.getCiv(InGame_Budget.this.iActiveCivID).getIdeologyID()).RulerTitle + ", " + Game.lang.get("MonthlyIncome") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text(((Game.getCiv(InGame_Budget.this.iActiveCivID).ruler.rulerBonuses.MonthlyIncome > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_Budget.this.iActiveCivID).ruler.rulerBonuses.MonthlyIncome, 100), CFG.FONT_BOLD_SMALL, (Game.getCiv(InGame_Budget.this.iActiveCivID).ruler.rulerBonuses.MonthlyIncome > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                if (Game.getCiv(InGame_Budget.this.iActiveCivID).fIncomeLord > 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game_Ages.getVassals()) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(((Game.getCiv(InGame_Budget.this.iActiveCivID).fIncomeLord > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_Budget.this.iActiveCivID).fIncomeLord, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).inAllianceSize; ++i) {
                    if (Game.alliancesSpecial.get(Game.getCiv(Game.player.iCivID).inAlliance.get(i)).iLeaderCivID == Game.player.iCivID && Game.alliancesSpecial.get(Game.getCiv(Game.player.iCivID).inAlliance.get(i)).typeOfAlliance == 0) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game.alliancesSpecial.get(Game.getCiv(Game.player.iCivID).inAlliance.get(i)).Name_Alliance) + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(HREManager.getIncome_Emperor(Game.getCiv(Game.player.iCivID).inAlliance.get(i)), 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                if (Game.getCiv(InGame_Budget.this.iActiveCivID).getCapitalLevel() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CapitalCity") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(((Game.getCapital_Income(InGame_Budget.this.iActiveCivID) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCapital_Income(InGame_Budget.this.iActiveCivID), 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Right2(((fGold >= 0.0f) ? "+" : "-") + CFG.getPrecision2(Game.getCiv(this.iActiveCivID).fTotalExpensesPerMonth, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY + 1 + (int)Math.ceil((CFG.TEXT_HEIGHT + CFG.PADDING * 6 - tButtonH) / 2.0f), tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth) {
                    final float fVal = Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth;
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
        menuElements.add(new Text_Title_v2(Game.lang.get("MonthlyExpenses"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("ArmyMaintenance"), Images.armyMaintenance, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 3 - tButtonH2 * 3, tButtonH2, tIconMaxW, iRow++ % 2 == 0));
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Icon(Images.gold, buttonX, buttonY, tButtonH2, tButtonH2, 0) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_GOLD_LEVEL_0;
            }
            
            @Override
            public boolean isLeveLActive() {
                return Game.getCiv(InGame_Budget.this.iActiveCivID).getMilitaryLevel() == 0;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(InGame_Budget.this.iActiveCivID).updateMilitaryLevel(0);
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Budget.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Budget.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
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
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DecreasedPay"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMaintenance") + ": ", "" + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_ARMY_MAINTENANCE[0] * 100.0f, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ManpowerPerMonth") + ": ", ((GameValues.budget.MILITARY_LEVEL_MANPOWER[0] > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_MANPOWER[0] * 100.0f, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.budget.MILITARY_LEVEL_MANPOWER[0] == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((GameValues.budget.MILITARY_LEVEL_MANPOWER[0] > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MoraleOfArmy") + ": ", "" + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_MORALE[0] * 100.0f, 100) + "%", Images.morale, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.budget.MILITARY_LEVEL_MORALE[0] == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((GameValues.budget.MILITARY_LEVEL_MORALE[0] > 0.99f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReinforcementSpeed") + ": ", "" + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[0] * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[0] == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[0] > 0.99f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Icon(Images.gold, buttonX, buttonY, tButtonH2, tButtonH2, 1) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_GOLD_LEVEL_1;
            }
            
            @Override
            public boolean isLeveLActive() {
                return Game.getCiv(InGame_Budget.this.iActiveCivID).getMilitaryLevel() == 1;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(InGame_Budget.this.iActiveCivID).updateMilitaryLevel(1);
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Budget.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Budget.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
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
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Default"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMaintenance") + ": ", "" + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_ARMY_MAINTENANCE[1] * 100.0f, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ManpowerPerMonth") + ": ", ((GameValues.budget.MILITARY_LEVEL_MANPOWER[1] > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_MANPOWER[1] * 100.0f, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.budget.MILITARY_LEVEL_MANPOWER[1] == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((GameValues.budget.MILITARY_LEVEL_MANPOWER[1] > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MoraleOfArmy") + ": ", "" + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_MORALE[1] * 100.0f, 100) + "%", Images.morale, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.budget.MILITARY_LEVEL_MORALE[1] == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((GameValues.budget.MILITARY_LEVEL_MORALE[1] > 0.99f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReinforcementSpeed") + ": ", "" + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[1] * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[1] == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[1] > 0.99f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Icon(Images.gold, buttonX, buttonY, tButtonH2, tButtonH2, 2) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_GOLD_LEVEL_2;
            }
            
            @Override
            public boolean isLeveLActive() {
                return Game.getCiv(InGame_Budget.this.iActiveCivID).getMilitaryLevel() == 2;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(InGame_Budget.this.iActiveCivID).updateMilitaryLevel(2);
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Budget.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Budget.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
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
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("IncreasedPay"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMaintenance") + ": ", "+" + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_ARMY_MAINTENANCE[2] * 100.0f, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ManpowerPerMonth") + ": ", ((GameValues.budget.MILITARY_LEVEL_MANPOWER[2] > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_MANPOWER[2] * 100.0f, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.budget.MILITARY_LEVEL_MANPOWER[2] == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((GameValues.budget.MILITARY_LEVEL_MANPOWER[2] > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MoraleOfArmy") + ": ", "" + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_MORALE[2] * 100.0f, 100) + "%", Images.morale, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.budget.MILITARY_LEVEL_MORALE[2] == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((GameValues.budget.MILITARY_LEVEL_MORALE[2] > 0.99f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReinforcementSpeed") + ": ", "" + CFG.getPrecision2(GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[2] * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[2] == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[2] > 0.99f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                if (this.isLeveLActive()) {
                    oSB.setColor(1.0f, 1.0f, 1.0f, this.isLeveLActive() ? 1.0f : (this.getIsHovered() ? 0.65f : 0.15f));
                    ImageManager.getImage(Images.morale).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.morale).getWidth() / 2 + iTranslateX, this.getPosY() + (this.getHeight() - ImageManager.getImage(Images.morale).getHeight()) / 2 + iTranslateY, ImageManager.getImage(Images.morale).getWidth(), ImageManager.getImage(Images.morale).getHeight());
                    oSB.setColor(Color.WHITE);
                }
                else {
                    super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        buttonX = paddingLeft;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("ResearchExpenses"), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 3 - tButtonH2 * 3, tButtonH2, tIconMaxW, iRow++ % 2 == 0));
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Icon(Images.gold, buttonX, buttonY, tButtonH2, tButtonH2, 0) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_GOLD_LEVEL_0;
            }
            
            @Override
            public boolean isLeveLActive() {
                return Game.getCiv(InGame_Budget.this.iActiveCivID).getResearchLevel() == 0;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DecreasedPay"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Research") + ": ", "" + CFG.getPrecision2(GameValues.budget.RESEARCH_LEVEL_RESEARCH[0] * 100.0f, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchCost") + ": ", "" + CFG.getPrecision2(GameValues.budget.RESEARCH_LEVEL_COST[0] * 100.0f, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(InGame_Budget.this.iActiveCivID).updateResearchLevel(0);
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Budget.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Budget.this.getMenuPosY(), this.getWidth(), this.getHeight()));
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Icon(Images.gold, buttonX, buttonY, tButtonH2, tButtonH2, 1) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_GOLD_LEVEL_1;
            }
            
            @Override
            public boolean isLeveLActive() {
                return Game.getCiv(InGame_Budget.this.iActiveCivID).getResearchLevel() == 1;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Default"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Research") + ": ", "+" + CFG.getPrecision2(GameValues.budget.RESEARCH_LEVEL_RESEARCH[1] * 100.0f, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchCost") + ": ", "+" + CFG.getPrecision2(GameValues.budget.RESEARCH_LEVEL_COST[1] * 100.0f, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(InGame_Budget.this.iActiveCivID).updateResearchLevel(1);
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Budget.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Budget.this.getMenuPosY(), this.getWidth(), this.getHeight()));
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget_Icon(Images.gold, buttonX, buttonY, tButtonH2, tButtonH2, 2) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_GOLD_LEVEL_2;
            }
            
            @Override
            public boolean isLeveLActive() {
                return Game.getCiv(InGame_Budget.this.iActiveCivID).getResearchLevel() == 2;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("IncreasedPay"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Research") + ": ", "+" + CFG.getPrecision2(GameValues.budget.RESEARCH_LEVEL_RESEARCH[2] * 100.0f, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchCost") + ": ", "+" + CFG.getPrecision2(GameValues.budget.RESEARCH_LEVEL_COST[2] * 100.0f, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(InGame_Budget.this.iActiveCivID).updateResearchLevel(2);
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Budget.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Budget.this.getMenuPosY(), this.getWidth(), this.getHeight()));
            }
            
            @Override
            protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                if (this.isLeveLActive()) {
                    oSB.setColor(1.0f, 1.0f, 1.0f, this.isLeveLActive() ? 1.0f : (this.getIsHovered() ? 0.65f : 0.15f));
                    ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).getWidth() / 2 + iTranslateX, this.getPosY() + (this.getHeight() - ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).getHeight()) / 2 + iTranslateY, ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).getWidth(), ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).getHeight());
                    oSB.setColor(Color.WHITE);
                }
                else {
                    super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        buttonX = paddingLeft;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(this.iActiveCivID).fArmyMaintenance;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Military"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0));
        menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "-" : "") + CFG.getPrecision2(fGold, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(InGame_Budget.this.iActiveCivID).fArmyMaintenance) {
                    final float fVal = Game.getCiv(InGame_Budget.this.iActiveCivID).fArmyMaintenance;
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
        menuElements.add(new ButtonStatsBudget(Game.lang.get("ResearchCost"), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0));
        menuElements.add(new ButtonStatsBudget_Right2("", paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getResearchCost(InGame_Budget.this.iActiveCivID)) {
                    final float fVal = Game.getResearchCost(InGame_Budget.this.iActiveCivID);
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchCost") + ": ", CFG.getPrecision2(Game.getResearchCost(InGame_Budget.this.iActiveCivID), 100), Images.goldNegative, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("ResearchPerMonth") + " * " + CFG.getPrecision2(GameValues.research.RESEARCH_MAINTENANCE_COST, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ResearchPerMonth") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(InGame_Budget.this.iActiveCivID).fResearchPerMonth, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(this.iActiveCivID).getProvinceMaintenance();
        menuElements.add(new ButtonStatsBudget(Game.lang.get("ProvinceMaintenance"), Images.provinces, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetExpensesMaintenance();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ProvinceMaintenance"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "-" : "") + CFG.getPrecision2(fGold, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(InGame_Budget.this.iActiveCivID).getProvinceMaintenance()) {
                    final float fVal = Game.getCiv(InGame_Budget.this.iActiveCivID).getProvinceMaintenance();
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float fGold = Game.getCiv(InGame_Budget.this.iActiveCivID).getProvinceMaintenance();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", ((fGold > 0.0f) ? "-" : "") + CFG.getPrecision2(fGold, 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final List<ProvValue> tProvinces = new ArrayList<ProvValue>();
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    tProvinces.add(new ProvValue(Game.getCiv(Game.player.iCivID).getProvinceID(i), Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getProvinceMaintenance()));
                }
                int tAdded = 0;
                while (tProvinces.size() > 0 && tAdded++ < 10) {
                    int bestID = 0;
                    for (int j = tProvinces.size() - 1; j > 0; --j) {
                        if (tProvinces.get(j).fValue > tProvinces.get(bestID).fValue) {
                            bestID = j;
                        }
                    }
                    if (tProvinces.get(bestID).fValue == 0.0f) {
                        break;
                    }
                    nData.add(new MenuElement_HoverElement_Type_Text("" + tAdded + ". ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(tProvinces.get(bestID).iProvinceID).getProvinceName() + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(((tProvinces.get(bestID).fValue > 0.0f) ? "-" : "") + CFG.getPrecision2(tProvinces.get(bestID).fValue, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    tProvinces.remove(bestID);
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        fGold = Game.getCiv(this.iActiveCivID).getBuildingsMaintenance();
        menuElements.add(new ButtonStatsBudget(Game.lang.get("BuildingsMaintenance"), Images.buildings, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_BudgetExpensesBuildingsMaintenance();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("BuildingsMaintenance"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.buildings, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "-" : "") + CFG.getPrecision2(fGold, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).getBuildingsMaintenance()) {
                    final float fVal = Game.getCiv(Game.player.iCivID).getBuildingsMaintenance();
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final float fGold = Game.getCiv(InGame_Budget.this.iActiveCivID).getBuildingsMaintenance();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingsMaintenance") + ": ", ((fGold > 0.0f) ? "-" : "") + CFG.getPrecision2(fGold, 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Interest"), Images.loan, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_TakeLoanRepay()) {
                    Game.menuManager.setVisibleInGame_TakeLoanRepay(false);
                }
                else {
                    Game.menuManager.rebuildInGame_TakeLoanRepay();
                    Game.menuManager.setVisibleInGame_TakeLoan(false);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Loans") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).iLoansSize, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MaximumNumberOfLoans") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getLoanMaxNumber(Game.player.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).iLoansSize > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    for (int i = 0; i < Game.getCiv(Game.player.iCivID).iLoansSize; ++i) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Total") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).loans.get(i).getLoanValueLeft(), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                        nData.add(new MenuElement_HoverElement_Type_Text(" / " + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).loans.get(i).fLoanValue, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.loan, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("EveryMonth") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).loans.get(i).fInterestPerMonth, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Expires") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game_Calendar.getNumOfDates_ByTurnID(Game.getCiv(Game.player.iCivID).loans.get(i).iExpires_TurnID), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (i != Game.getCiv(Game.player.iCivID).iLoansSize - 1) {
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                    }
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2("0", paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).fLoansCost) {
                    final float fVal = Game.getCiv(Game.player.iCivID).fLoansCost;
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.005f) ? "-" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.005f) ? "-" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
        menuElements.add(new ButtonStatsBudget(Game.lang.get("AdditionalExpenses"), Images.diplomacy, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MilitaryAcademy") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getMilitaryAcademy_MaintenanceCost(InGame_Budget.this.iActiveCivID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MilitaryAcademyForGenerals") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getMilitaryAcademyForGenerals_MaintenanceCost(InGame_Budget.this.iActiveCivID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).fExpenseVassal > 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game_Ages.getLord()) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(((Game.getCiv(Game.player.iCivID).fExpenseVassal > 0.0f) ? "-" : "") + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fExpenseVassal, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2("" + CFG.getPrecision2(-Game.getCiv(this.iActiveCivID).getAdditionalExpenses(), 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", this.getText(), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MilitaryAcademy") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getMilitaryAcademy_MaintenanceCost(InGame_Budget.this.iActiveCivID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MilitaryAcademyForGenerals") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getMilitaryAcademyForGenerals_MaintenanceCost(InGame_Budget.this.iActiveCivID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).fExpenseVassal > 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game_Ages.getLord()) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(((Game.getCiv(Game.player.iCivID).fExpenseVassal > 0.0f) ? "-" : "") + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fExpenseVassal, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).fLoansCost) {
                    final float fVal = Game.getCiv(InGame_Budget.this.iActiveCivID).getAdditionalExpenses();
                    if (fVal >= 1000.0f) {
                        this.setText(((fVal > 0.005f) ? "-" : "") + CFG.getNumberWithSpaces("" + (int)fVal));
                    }
                    else {
                        this.setText(((fVal > 0.005f) ? "-" : "") + CFG.getPrecision2(fVal, 100));
                    }
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
        fGold = Game.getCiv(this.iActiveCivID).getInflation() * 100.0f;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Inflation"), Images.inflation, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0) {
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).reduceInflation()) {
                    Game.player.currSituation.updateCurrentSituation();
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("InflationReduced"), Game.lang.get("Inflation") + ": " + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getInflation() * 100.0f, 100) + "%");
                    InGame_Info.imgID = Images.infoCrown;
                }
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Budget.getHoverInflation(InGame_Budget.this.iActiveCivID);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iActiveCivID).getInflation() * 100.0f, 100) + "%", paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).getInflation()) {
                    final float fVal = Game.getCiv(Game.player.iCivID).getInflation();
                    this.setText(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal * 100.0f, 100) + "%");
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
            
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).reduceInflation()) {
                    Game.player.currSituation.updateCurrentSituation();
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("InflationReduced"), Game.lang.get("Inflation") + ": " + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getInflation() * 100.0f, 100) + "%");
                    InGame_Info.imgID = Images.infoCrown;
                }
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Budget.getHoverInflation(InGame_Budget.this.iActiveCivID);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("ProsperityTier"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, Game.getProsperityLevel(Game.player.iCivID)) {
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Budget.this.getHoverProsperity(Game.player.iCivID);
            }
            
            @Override
            protected Color getColor2(final boolean isActive) {
                return Colors.getEconomyColor((int)(Game.getCiv(Game.player.iCivID).fProsperity_AverageEconomy / GameValues.prosperity.PROSPERITY_INCOME), 1.0f);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("MonthlyIncomeEconomy"), Images.goldPositive, paddingLeft, buttonY, tButtonW, CFG.TEXT_HEIGHT + CFG.PADDING * 6, tIconMaxW, false) {
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Budget.this.getHoverProsperity(Game.player.iCivID);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fProsperity_AverageEconomy * GameValues.prosperity.PROSPERITY_INCOME, 100) + "%", paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Budget.this.getHoverProsperity(Game.player.iCivID);
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorPositive(isActive, this.getIsHovered());
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Loans"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Loans"), Images.loan, paddingLeft, buttonY, tButtonW, CFG.TEXT_HEIGHT + CFG.PADDING * 6, tIconMaxW, false) {
            @Override
            public void actionElement() {
                InGame_Budget.actionTakeLoan();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("TakeLoan"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Loan") + ": ", CFG.getPrecision2(Game.getLoanValue(Game.player.iCivID), 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2("" + Game.getCiv(Game.player.iCivID).iLoansSize + " / " + Game.getLoanMaxNumber(Game.player.iCivID), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            int currentNum = 0;
            
            @Override
            public void setCurrent(final int nCurrent) {
                this.currentNum = nCurrent;
            }
            
            @Override
            public void actionElement() {
                InGame_Budget.actionTakeLoan();
            }
            
            @Override
            public String getTextToDraw() {
                if (this.currentNum != Game.getCiv(Game.player.iCivID).iLoansSize) {
                    this.currentNum = Game.getCiv(Game.player.iCivID).iLoansSize;
                    this.setText("" + Game.getCiv(Game.player.iCivID).iLoansSize + " / " + Game.getLoanMaxNumber(Game.player.iCivID));
                }
                return super.getTextToDraw();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("TakeLoan"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Loan") + ": ", CFG.getPrecision2(Game.getLoanValue(Game.player.iCivID), 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Loans") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).iLoansSize, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MaximumNumberOfLoans") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getLoanMaxNumber(Game.player.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.get(menuElements.size() - 1).setCurrent(Game.getCiv(Game.player.iCivID).iLoansSize);
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        paddingLeft += CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("RepayLoans"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_TakeLoanRepay()) {
                    Game.menuManager.setVisibleInGame_TakeLoanRepay(false);
                }
                else {
                    Game.menuManager.rebuildInGame_TakeLoanRepay();
                    Game.menuManager.setVisibleInGame_TakeLoan(false);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RepayLoans"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Loans") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).iLoansSize, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MaximumNumberOfLoans") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getLoanMaxNumber(Game.player.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("TakeLoan"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                InGame_Budget.actionTakeLoan();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("TakeLoan"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Loan") + ": ", CFG.getPrecision2(Game.getLoanValue(Game.player.iCivID), 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Loans") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).iLoansSize, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MaximumNumberOfLoans") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getLoanMaxNumber(Game.player.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        buttonY += CFG.PADDING;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.lang.get("Treasury"), Game.getCiv(this.iActiveCivID).getCivName(), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_Budget.this.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_Budget.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("Treasury"));
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Budget.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Budget.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.budgetOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.budgetOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.budgetOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Budget.lTime = CFG.currentTimeMillis;
    }
    
    public static MenuElement_Hover getHoverCorruption() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Corruption") + ": ", CFG.FONT_BOLD));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getCorruption() * 100.0f, 100) + "%", CFG.FONT_BOLD, (Game.getCiv(Game.player.iCivID).getCorruption() > 0.005f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.corruption, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ProvinceIncome") + ": ", CFG.FONT_REGULAR));
        nData.add(new MenuElement_HoverElement_Type_TextTitle("-" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getCorruption() * 100.0f, 100) + "%", CFG.FONT_BOLD, (Game.getCiv(Game.player.iCivID).getCorruption() > 0.005f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getCiv(Game.player.iCivID).getSupremeCourtLevel() >= Game.getSupremeCourt_MaxLvl(Game.player.iCivID)) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SupremeCourt"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.stability, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UpgradeTheSupremeCourtLevelToReduceCorruption"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Level") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).getSupremeCourtLevel() + " / " + Game.getSupremeCourt_MaxLvl(Game.player.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.stability, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CorruptionPerLevel") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.supremeCourt.SUPREME_COURT_CORRUPTION_REDUCTION_PER_LVL * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.corruption, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Corruption0"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Corruption1"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static MenuElement_Hover getHoverInflation(final int iActiveCivID) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        final float fInflation = Game.getCiv(iActiveCivID).getInflation() * 100.0f;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Inflation") + ": ", CFG.getPrecision2(fInflation, 100) + "%", Images.inflation, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionCost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(fInflation, 100) + "%", CFG.FONT_BOLD_SMALL, (fInflation > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.construction, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InvestInEconomyCost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(fInflation, 100) + "%", CFG.FONT_BOLD_SMALL, (fInflation > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY_UP, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(fInflation, 100) + "%", CFG.FONT_BOLD_SMALL, (fInflation > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.taxUp, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CoreConstruction") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(fInflation, 100) + "%", CFG.FONT_BOLD_SMALL, (fInflation > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.core, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ReligionConversionCost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(fInflation, 100) + "%", CFG.FONT_BOLD_SMALL, (fInflation > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.religion, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Empty());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ReduceInflation"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.inflation, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getInflationReduceCost_Legacy(Game.player.iCivID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public MenuElement_Hover getHoverProsperity(final int iCivID) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProsperityTier") + ": ", Game.getProsperityLevel(iCivID), Images.goldPositive, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.getEconomyColor((int)(Game.getCiv(iCivID).fProsperity_AverageEconomy / GameValues.prosperity.PROSPERITY_INCOME), 1.0f)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncomeEconomy") + ": ", "+" + CFG.getPrecision2(Game.getCiv(iCivID).fProsperity_AverageEconomy * GameValues.prosperity.PROSPERITY_INCOME, 100) + "%", Images.goldPositive, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_RIGHT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ProsperityTierReflectsTheAverageEconomyOfYourProvincesImpactingIncomeFromEconomy"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ProsperityTier") + " = " + Game.lang.get("AverageEconomybasedOnPopulationOfAllProvinces"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Empty());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static void actionTakeLoan() {
        if (Game.menuManager.getVisibleInGame_TakeLoan()) {
            Game.menuManager.setVisibleInGame_TakeLoan(false);
        }
        else {
            Game.menuManager.rebuildInGame_TakeLoan();
            Game.menuManager.setVisibleInGame_TakeLoanRepay(false);
        }
    }
    
    static {
        InGame_Budget.lTime = 0L;
    }
    
    public class ProvValue
    {
        public int iProvinceID;
        public float fValue;
        
        public ProvValue(final int iProvinceID, final float fValue) {
            this.iProvinceID = iProvinceID;
            this.fValue = fValue;
        }
    }
}
