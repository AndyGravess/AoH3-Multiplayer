// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Budget;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.AA_KeyManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Economy;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Resource;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Balance;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Right2;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_BudgetIncomeProduction extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public int iActiveCivID;
    public static int iSortID;
    
    public InGame_BudgetIncomeProduction() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING;
        this.iActiveCivID = Game.player.iCivID;
        final int tIconMaxW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        final int tButtonH2 = CFG.BUTTON_HEIGHT * 4 / 5;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD), "+999 999");
        final int tButtonRightW = (int)glyphLayout.width + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonW = menuWidth - paddingLeft * 2 - CFG.PADDING - tButtonRightW;
        int iRow = 0;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("TotalIncome"), Images.goldPositive, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0));
        menuElements.add(new ButtonStatsBudget_Right2("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(this.iActiveCivID).civBonuses.MonthlyIncome, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(InGame_BudgetIncomeProduction.this.iActiveCivID).civBonuses.MonthlyIncome) {
                    final float fVal = Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(InGame_BudgetIncomeProduction.this.iActiveCivID).civBonuses.MonthlyIncome;
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
        float fGold = Game.getCiv(this.iActiveCivID).fTotalExpensesPerMonth;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("TotalExpenses"), Images.goldNegative, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0));
        menuElements.add(new ButtonStatsBudget_Right2(((fGold >= 0.0f) ? "+" : "-") + CFG.getPrecision2(Game.getCiv(this.iActiveCivID).fTotalExpensesPerMonth, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth) {
                    final float fVal = Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth;
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
        fGold = Game.getCiv(this.iActiveCivID).fTotalIncomePerMonth + Game.getCiv(this.iActiveCivID).civBonuses.MonthlyIncome - Game.getCiv(this.iActiveCivID).fTotalExpensesPerMonth;
        menuElements.add(new ButtonStatsBudget_Balance(Game.lang.get("Balance") + ": ", ((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 10) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Budget();
                Game.menuManager.setVisibleInGame_Budget(true);
                InGame_Budget.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Back"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = Images.boxTitleBORDERWIDTH;
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.3f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        menuElements.add(new Text_TitleBlueSort(InGame_BudgetIncomeProduction.iSortID == 0 || InGame_BudgetIncomeProduction.iSortID == 1, InGame_BudgetIncomeProduction.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_BudgetIncomeProduction.iSortID == 0) {
                    InGame_BudgetIncomeProduction.iSortID = 1;
                }
                else {
                    InGame_BudgetIncomeProduction.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_BudgetIncomeProduction();
                InGame_BudgetIncomeProduction.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_BudgetIncomeProduction.iSortID == 2 || InGame_BudgetIncomeProduction.iSortID == 3, InGame_BudgetIncomeProduction.iSortID == 3, Game.lang.get("Resource"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_BudgetIncomeProduction.iSortID == 2) {
                    InGame_BudgetIncomeProduction.iSortID = 3;
                }
                else {
                    InGame_BudgetIncomeProduction.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_BudgetIncomeProduction();
                InGame_BudgetIncomeProduction.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Resource"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_BudgetIncomeProduction.iSortID == 4 || InGame_BudgetIncomeProduction.iSortID == 5, InGame_BudgetIncomeProduction.iSortID == 5, Game.lang.get("Economy"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_BudgetIncomeProduction.iSortID == 4) {
                    InGame_BudgetIncomeProduction.iSortID = 5;
                }
                else {
                    InGame_BudgetIncomeProduction.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_BudgetIncomeProduction();
                InGame_BudgetIncomeProduction.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Economy"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_BudgetIncomeProduction.iSortID == 6 || InGame_BudgetIncomeProduction.iSortID == 7, InGame_BudgetIncomeProduction.iSortID == 7, Game.lang.get("Income"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_BudgetIncomeProduction.iSortID == 6) {
                    InGame_BudgetIncomeProduction.iSortID = 7;
                }
                else {
                    InGame_BudgetIncomeProduction.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_BudgetIncomeProduction();
                InGame_BudgetIncomeProduction.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Income"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.3f);
        r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.2f);
        final List<Integer> tProvinces = new ArrayList<Integer>();
        for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getResourceID() >= 0) {
                tProvinces.add(Game.getCiv(Game.player.iCivID).getProvinceID(i));
            }
        }
        if (tProvinces.size() > 0) {
            int tID = 1;
            while (tProvinces.size() > 0) {
                int toAddID = 0;
                if (InGame_BudgetIncomeProduction.iSortID == 0) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_BudgetIncomeProduction.iSortID == 1) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_BudgetIncomeProduction.iSortID == 2) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(o)).getResourceID()))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_BudgetIncomeProduction.iSortID == 3) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(o)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_BudgetIncomeProduction.iSortID == 4) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getEconomyWithBonuses() > Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_BudgetIncomeProduction.iSortID == 5) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.getProvince(tProvinces.get(o)).getEconomyWithBonuses() < Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_BudgetIncomeProduction.iSortID == 6) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (ResourcesManager.getMonthlyIncome(tProvinces.get(toAddID)) < ResourcesManager.getMonthlyIncome(tProvinces.get(o))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_BudgetIncomeProduction.iSortID == 7) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (ResourcesManager.getMonthlyIncome(tProvinces.get(toAddID)) > ResourcesManager.getMonthlyIncome(tProvinces.get(o))) {
                            toAddID = o;
                        }
                    }
                }
                buttonX = paddingLeft;
                menuElements.add(new Text_StaticBG_ID("" + tID++ + ". " + Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).isCapital ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        if (Game.iActiveProvince == this.getCurrent()) {
                            Game.menuManager.setVisibleInGame_Budget(false);
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
                if (Game.getProvince(tProvinces.get(toAddID)).getResourceID() >= 0) {
                    menuElements.add(new ButtonStatsRectIMG_Resource(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()), Game.getProvince(tProvinces.get(toAddID)).getResourceID(), buttonX, buttonY, r0W, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID)) {
                        @Override
                        public void buildElementHover() {
                            this.menuElementHover = InGame_ProvinceInfo.getHoverResource(this.getCurrent(), this.getValue1(), false);
                        }
                    });
                }
                else {
                    menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W, buttonH));
                }
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_Economy("" + CFG.getPrecision2(Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses(), 100), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                            if (AA_KeyManager.SHIFT_HOLD || AA_KeyManager.CTRL_HOLD) {
                                for (int i = 0; i < 5; ++i) {
                                    if (Game.canInvestInEconomy(this.getCurrent())) {
                                        Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(this.getCurrent()), 10), Game_Calendar.IMG_ECONOMY);
                                        break;
                                    }
                                    if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(this.getCurrent())) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(this.getCurrent()), 100), Images.legacy);
                                    }
                                    else {
                                        if (!Game.getProvince(this.getCurrent()).addInvestInProvince()) {
                                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(this.getCurrent()), 100), Images.gold);
                                            break;
                                        }
                                        ProvinceDraw.addProvinceDot_Economy(this.getCurrent());
                                    }
                                }
                            }
                            else if (Game.canInvestInEconomy(this.getCurrent())) {
                                Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(this.getCurrent()), 10), Game_Calendar.IMG_ECONOMY);
                            }
                            else if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(this.getCurrent())) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(this.getCurrent()), 100), Images.legacy);
                            }
                            else if (!Game.getProvince(this.getCurrent()).addInvestInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(this.getCurrent()), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_Economy(this.getCurrent());
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.getCurrent(), false);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                fGold = ResourcesManager.getMonthlyIncome(tProvinces.get(toAddID), Game.getProvince(tProvinces.get(toAddID)).getResourceID());
                menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                    @Override
                    public String getTextToDraw() {
                        if (this.lastValue != ResourcesManager.getMonthlyIncome(this.id, Game.getProvince(this.id).getResourceID())) {
                            final float fVal = ResourcesManager.getMonthlyIncome(this.id, Game.getProvince(this.id).getResourceID());
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
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        final float fGold = Game.getIncomeFromEconomy(this.id);
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("IncomeProduction") + ": ", CFG.FONT_BOLD));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.gold, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                tProvinces.remove(toAddID);
            }
        }
        else {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.lang.get("IncomeProduction"), Game.getCiv(this.iActiveCivID).getCivName(), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_BudgetIncomeProduction.this.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_BudgetIncomeProduction.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_BudgetIncomeProduction.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_BudgetIncomeProduction.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.budgetOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.budgetOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.budgetOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("IncomeProduction"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_BudgetIncomeProduction.lTime2 = CFG.currentTimeMillis;
    }
    
    static {
        InGame_BudgetIncomeProduction.lTime = 0L;
        InGame_BudgetIncomeProduction.lTime2 = 0L;
        InGame_BudgetIncomeProduction.iSortID = 6;
    }
}
