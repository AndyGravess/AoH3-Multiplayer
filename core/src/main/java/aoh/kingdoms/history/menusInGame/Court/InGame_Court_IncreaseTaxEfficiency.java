// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageFull;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Value;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.AA_KeyManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_TaxEfficiency;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Population;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRect;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc2_Special;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_IncreaseTaxEfficiency extends Menu
{
    public static int iSortID;
    public static int CLICK_X_TIMES;
    
    public InGame_Court_IncreaseTaxEfficiency() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = Images.boxTitleBORDERWIDTH;
        int buttonY = CFG.PADDING;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        final int c0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 4) * 0.6f);
        final int c1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 4) * 0.2f);
        buttonX = paddingLeft;
        if (Game.settingsManager.COUNCIL_TIPS) {
            menuElements.add(new Text_Desc2_Special(Game.lang.get("TaxEfficiency0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorButton(isActive, this.getIsHovered());
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        buttonX = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click("", Images.tax, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.gold).getWidth(), 0) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES) {
                    this.setText("" + Game.lang.get("PerformTheActionXTimes", InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES));
                    this.lastValue = InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES;
                }
                return this.sText;
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsRect("-", buttonX, buttonY, c1W, buttonH) {
            @Override
            public void actionElement() {
                --InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES;
                if (InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES < 1) {
                    InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES = 1;
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsRect("+", buttonX, buttonY, c1W, buttonH) {
            @Override
            public void actionElement() {
                ++InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES;
                if (InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES > 9) {
                    InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES = 9;
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Court_IncreaseTaxEfficiency.iSortID == 0 || InGame_Court_IncreaseTaxEfficiency.iSortID == 1, InGame_Court_IncreaseTaxEfficiency.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_IncreaseTaxEfficiency.iSortID == 0) {
                    InGame_Court_IncreaseTaxEfficiency.iSortID = 1;
                }
                else {
                    InGame_Court_IncreaseTaxEfficiency.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_IncreaseTaxEfficiency();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_IncreaseTaxEfficiency.iSortID == 2 || InGame_Court_IncreaseTaxEfficiency.iSortID == 3, InGame_Court_IncreaseTaxEfficiency.iSortID == 3, Game.lang.get("Population"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_IncreaseTaxEfficiency.iSortID == 2) {
                    InGame_Court_IncreaseTaxEfficiency.iSortID = 3;
                }
                else {
                    InGame_Court_IncreaseTaxEfficiency.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_IncreaseTaxEfficiency();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_IncreaseTaxEfficiency.iSortID == 4 || InGame_Court_IncreaseTaxEfficiency.iSortID == 5, InGame_Court_IncreaseTaxEfficiency.iSortID == 5, Game.lang.get("TaxEfficiency"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_IncreaseTaxEfficiency.iSortID == 4) {
                    InGame_Court_IncreaseTaxEfficiency.iSortID = 5;
                }
                else {
                    InGame_Court_IncreaseTaxEfficiency.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_IncreaseTaxEfficiency();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("TaxEfficiency"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_IncreaseTaxEfficiency.iSortID == 6 || InGame_Court_IncreaseTaxEfficiency.iSortID == 7, InGame_Court_IncreaseTaxEfficiency.iSortID == 7, Game.lang.get("Cost"), -1, buttonX, buttonY, r1W * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_IncreaseTaxEfficiency.iSortID == 6) {
                    InGame_Court_IncreaseTaxEfficiency.iSortID = 7;
                }
                else {
                    InGame_Court_IncreaseTaxEfficiency.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_IncreaseTaxEfficiency();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Cost"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 6) * 0.2f);
        r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 6) * 0.2f);
        final List<Integer> tProvinces = new ArrayList<Integer>();
        for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
            if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied()) {
                tProvinces.add(Game.getCiv(Game.player.iCivID).getProvinceID(i));
            }
        }
        while (tProvinces.size() > 0) {
            int toAddID = 0;
            if (InGame_Court_IncreaseTaxEfficiency.iSortID == 0) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_IncreaseTaxEfficiency.iSortID == 1) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_IncreaseTaxEfficiency.iSortID == 2) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(o)).getPopulationTotal() > Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_IncreaseTaxEfficiency.iSortID == 3) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(o)).getPopulationTotal() < Game.getProvince(tProvinces.get(toAddID)).getPopulationTotal()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_IncreaseTaxEfficiency.iSortID == 4) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(o)).getTaxEfficiencyWithBonuses() > Game.getProvince(tProvinces.get(toAddID)).getTaxEfficiencyWithBonuses()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_IncreaseTaxEfficiency.iSortID == 5) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(o)).getTaxEfficiencyWithBonuses() < Game.getProvince(tProvinces.get(toAddID)).getTaxEfficiencyWithBonuses()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_IncreaseTaxEfficiency.iSortID == 6) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getIncreaseTaxEfficiencyCost(tProvinces.get(toAddID)) < Game.getIncreaseTaxEfficiencyCost(tProvinces.get(o))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_IncreaseTaxEfficiency.iSortID == 7) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getIncreaseTaxEfficiencyCost(tProvinces.get(toAddID)) > Game.getIncreaseTaxEfficiencyCost(tProvinces.get(o))) {
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
                    this.menuElementHover = InGame_ProvinceInfo.getHoverPopulation(this.getCurrent(), false);
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
            menuElements.add(new Text_StaticBG_TaxEfficiency("" + CFG.getPrecision2(Game.getProvince(tProvinces.get(toAddID)).getTaxEfficiencyWithBonuses(), 100) + "%", CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                        boolean out = false;
                        if (AA_KeyManager.SHIFT_HOLD || AA_KeyManager.CTRL_HOLD) {
                            for (int i = 0; i < 5; ++i) {
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addIncreaseTaxEfficiencyInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(this.getCurrent()), 100), Images.gold);
                                        break;
                                    }
                                    ProvinceDraw.addProvinceDot_TaxEfficiency(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        else {
                            for (int u = 0; u < InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES; ++u) {
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addIncreaseTaxEfficiencyInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(this.getCurrent()), 100), Images.gold);
                                        break;
                                    }
                                    ProvinceDraw.addProvinceDot_TaxEfficiency(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        if (out) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_IncreaseTaxEfficiency.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_IncreaseTaxEfficiency.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.HOVER_YELLOW;
                                }
                            });
                        }
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverTaxEfficiency(this.getCurrent(), false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Active_Value("" + Game.getIncreaseTaxEfficiencyCostLegacy(tProvinces.get(toAddID)), Images.legacy, buttonX, buttonY, r1W, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                        boolean out = false;
                        if (AA_KeyManager.SHIFT_HOLD || AA_KeyManager.CTRL_HOLD) {
                            for (int i = 0; i < 5; ++i) {
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addIncreaseTaxEfficiencyInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(this.getCurrent()), 100), Images.gold);
                                        break;
                                    }
                                    ProvinceDraw.addProvinceDot_TaxEfficiency(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        else {
                            for (int u = 0; u < InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES; ++u) {
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addIncreaseTaxEfficiencyInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(this.getCurrent()), 100), Images.gold);
                                        break;
                                    }
                                    ProvinceDraw.addProvinceDot_TaxEfficiency(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        if (out) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_IncreaseTaxEfficiency.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_IncreaseTaxEfficiency.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.HOVER_YELLOW;
                                }
                            });
                        }
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverTaxEfficiency(this.getCurrent(), false);
                }
                
                @Override
                public String getTextToDraw() {
                    if (this.lastValue != Game.getIncreaseTaxEfficiencyCostLegacy(this.id) * InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES) {
                        this.setText("" + CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(this.id) * InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES, 100));
                        this.lastValue = Game.getIncreaseTaxEfficiencyCostLegacy(this.id) * InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES;
                    }
                    return this.sText;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Active_Value("" + Game.getIncreaseTaxEfficiencyCost(tProvinces.get(toAddID)), Images.gold, buttonX, buttonY, r1W, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                        boolean out = false;
                        if (AA_KeyManager.SHIFT_HOLD || AA_KeyManager.CTRL_HOLD) {
                            for (int i = 0; i < 5; ++i) {
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addIncreaseTaxEfficiencyInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(this.getCurrent()), 100), Images.gold);
                                        break;
                                    }
                                    ProvinceDraw.addProvinceDot_TaxEfficiency(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        else {
                            for (int u = 0; u < InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES; ++u) {
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addIncreaseTaxEfficiencyInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(this.getCurrent()), 100), Images.gold);
                                        break;
                                    }
                                    ProvinceDraw.addProvinceDot_TaxEfficiency(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        if (out) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_IncreaseTaxEfficiency.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_IncreaseTaxEfficiency.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.HOVER_YELLOW;
                                }
                            });
                        }
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverTaxEfficiency(this.getCurrent(), false);
                }
                
                @Override
                public String getTextToDraw() {
                    if (this.lastValue != Game.getIncreaseTaxEfficiencyCost(this.id) * InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES) {
                        this.setText("" + CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(this.id) * InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES, 10));
                        this.lastValue = Game.getIncreaseTaxEfficiencyCost(this.id) * InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES;
                    }
                    return this.sText;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            tProvinces.remove(toAddID);
        }
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("IncreaseTaxEfficiency"));
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Court.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Court.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - InGame_CourtOptions.menuH + iTranslateY, this.getWidth(), this.getHeight() + InGame_CourtOptions.menuH + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civOptionsOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civOptionsOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civOptionsOver).getHeight()));
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
    
    public static MenuElement_Hover getHoverSupremeCourt() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("SupremeCourt"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_ImageFull(Images.supremeCourtBig, 0, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Level") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.player.iCivID).getSupremeCourtLevel() + " / " + Game.getSupremeCourt_MaxLvl(Game.player.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Corruption") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getCorruption() * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, (Game.getCiv(Game.player.iCivID).getCorruption() > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.corruption, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CorruptionPerLevel") + ": ", CFG.getPrecision2(GameValues.supremeCourt.SUPREME_COURT_CORRUPTION_REDUCTION_PER_LVL * 100.0f, 100) + "%", Images.corruption, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getCiv(Game.player.iCivID).getSupremeCourtLevel() >= Game.getSupremeCourt_MaxLvl(Game.player.iCivID)) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumLevel"), CFG.FONT_BOLD_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.stability, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UpgradeSupremeCourt"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.stability, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getSupremeCourt_Cost(Game.player.iCivID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CorruptionPerLevel") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.supremeCourt.SUPREME_COURT_CORRUPTION_REDUCTION_PER_LVL * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.corruption, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        return new MenuElement_Hover(nElements);
    }
    
    public static final void upgradeSupremeCourt() {
        if (Game.getCiv(Game.player.iCivID).getSupremeCourtLevel() >= Game.getSupremeCourt_MaxLvl(Game.player.iCivID)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getSupremeCourtLevel() + " / " + Game.getSupremeCourt_MaxLvl(Game.player.iCivID), Images.stability);
        }
        else if (Game.getCiv(Game.player.iCivID).fGold < Game.getSupremeCourt_Cost(Game.player.iCivID)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getSupremeCourt_Cost(Game.player.iCivID), 100), Images.gold);
        }
        else {
            Game.getCiv(Game.player.iCivID).upgradeSupremeCourt();
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = 0;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("SupremeCourt"), Game.lang.get("Level") + ": " + Game.getCiv(Game.player.iCivID).getSupremeCourtLevel() + " / " + Game.getSupremeCourt_MaxLvl(Game.player.iCivID));
            InGame_Info.imgID = Images.infoCrown;
            Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
        }
    }
    
    static {
        InGame_Court_IncreaseTaxEfficiency.iSortID = 0;
        InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES = 1;
    }
}
