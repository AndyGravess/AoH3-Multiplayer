// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Budget;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Population;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
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
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_BudgetExpensesBuildingsMaintenance extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public int iActiveCivID;
    public static int iSortID;
    
    public InGame_BudgetExpensesBuildingsMaintenance() {
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
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD), "+999 999");
        final int tButtonRightW = (int)Renderer.glyphLayout.width + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonW = menuWidth - paddingLeft * 2 - CFG.PADDING - tButtonRightW;
        int iRow = 0;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("TotalIncome"), Images.goldPositive, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, iRow++ % 2 == 0));
        menuElements.add(new ButtonStatsBudget_Right2("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(this.iActiveCivID).civBonuses.MonthlyIncome, 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(InGame_BudgetExpensesBuildingsMaintenance.this.iActiveCivID).civBonuses.MonthlyIncome) {
                    final float fVal = Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(InGame_BudgetExpensesBuildingsMaintenance.this.iActiveCivID).civBonuses.MonthlyIncome;
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
        menuElements.add(new Text_TitleBlueSort(InGame_BudgetExpensesBuildingsMaintenance.iSortID == 0 || InGame_BudgetExpensesBuildingsMaintenance.iSortID == 1, InGame_BudgetExpensesBuildingsMaintenance.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 0) {
                    InGame_BudgetExpensesBuildingsMaintenance.iSortID = 1;
                }
                else {
                    InGame_BudgetExpensesBuildingsMaintenance.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_BudgetExpensesBuildingsMaintenance();
                InGame_BudgetExpensesBuildingsMaintenance.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_BudgetExpensesBuildingsMaintenance.iSortID == 2 || InGame_BudgetExpensesBuildingsMaintenance.iSortID == 3, InGame_BudgetExpensesBuildingsMaintenance.iSortID == 3, Game.lang.get("Population"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 2) {
                    InGame_BudgetExpensesBuildingsMaintenance.iSortID = 3;
                }
                else {
                    InGame_BudgetExpensesBuildingsMaintenance.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_BudgetExpensesBuildingsMaintenance();
                InGame_BudgetExpensesBuildingsMaintenance.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_BudgetExpensesBuildingsMaintenance.iSortID == 4 || InGame_BudgetExpensesBuildingsMaintenance.iSortID == 5, InGame_BudgetExpensesBuildingsMaintenance.iSortID == 5, Game.lang.get("Buildings"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 4) {
                    InGame_BudgetExpensesBuildingsMaintenance.iSortID = 5;
                }
                else {
                    InGame_BudgetExpensesBuildingsMaintenance.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_BudgetExpensesBuildingsMaintenance();
                InGame_BudgetExpensesBuildingsMaintenance.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Buildings"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_BudgetExpensesBuildingsMaintenance.iSortID == 6 || InGame_BudgetExpensesBuildingsMaintenance.iSortID == 7, InGame_BudgetExpensesBuildingsMaintenance.iSortID == 7, Game.lang.get("BuildingsMaintenance"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 6) {
                    InGame_BudgetExpensesBuildingsMaintenance.iSortID = 7;
                }
                else {
                    InGame_BudgetExpensesBuildingsMaintenance.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_BudgetExpensesBuildingsMaintenance();
                InGame_BudgetExpensesBuildingsMaintenance.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("BuildingsMaintenance"), Colors.HOVER_GOLD));
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
            tProvinces.add(Game.getCiv(Game.player.iCivID).getProvinceID(i));
        }
        int tID = 1;
        while (tProvinces.size() > 0) {
            int toAddID = 0;
            if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 0) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 1) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 2) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(o)).getPopulationTotal() > Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 3) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(o)).getPopulationTotal() < Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 4) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(o)).getUsedBuildingsSlots() > Game.getProvince(tProvinces.get(toAddID)).getUsedBuildingsSlots()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 5) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(o)).getUsedBuildingsSlots() < Game.getProvince(tProvinces.get(toAddID)).getUsedBuildingsSlots()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 6) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getProvinceBuildingsMaintenance() < Game.getProvince(tProvinces.get(o)).getProvinceBuildingsMaintenance()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_BudgetExpensesBuildingsMaintenance.iSortID == 7) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getProvinceBuildingsMaintenance() > Game.getProvince(tProvinces.get(o)).getProvinceBuildingsMaintenance()) {
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
            menuElements.add(new Text_StaticBG_Population(CFG.getNumberWithSpaces("" + Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverPopulation(this.getCurrent(), false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID("" + Game.getProvince(tProvinces.get(toAddID)).getUsedBuildingsSlots(), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, tProvinces.get(toAddID)));
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            fGold = Game.getProvince(tProvinces.get(toAddID)).getProvinceBuildingsMaintenance();
            menuElements.add(new ButtonStatsBudget_Right2(((fGold > 0.0f) ? "-" : "") + CFG.getPrecision2(fGold, 1000), buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public String getTextToDraw() {
                    if (this.lastValue != Game.getProvince(this.id).getProvinceBuildingsMaintenance()) {
                        final float fVal = Game.getProvince(this.id).getProvinceBuildingsMaintenance();
                        this.setText(((fVal > 0.0f) ? "-" : "") + CFG.getPrecision2(fVal, 1000));
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
                    final float fGold = Game.getProvince(this.id).getProvinceBuildingsMaintenance();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("BuildingsMaintenance") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(((fGold > 0.0f) ? "-" : "") + CFG.getPrecision2(fGold, 1000), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            tProvinces.remove(toAddID);
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.lang.get("BuildingsMaintenance"), Game.getCiv(this.iActiveCivID).getCivName(), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_BudgetExpensesBuildingsMaintenance.this.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_BudgetExpensesBuildingsMaintenance.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_BudgetExpensesBuildingsMaintenance.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_BudgetExpensesBuildingsMaintenance.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.budgetOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.budgetOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.budgetOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("BuildingsMaintenance"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_BudgetExpensesBuildingsMaintenance.lTime2 = CFG.currentTimeMillis;
    }
    
    static {
        InGame_BudgetExpensesBuildingsMaintenance.lTime = 0L;
        InGame_BudgetExpensesBuildingsMaintenance.lTime2 = 0L;
        InGame_BudgetExpensesBuildingsMaintenance.iSortID = 6;
    }
}
