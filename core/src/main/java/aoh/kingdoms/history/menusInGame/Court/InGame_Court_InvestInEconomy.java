// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_GrowthRate;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Economy;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlue;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Value;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.AA_KeyManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Economy2;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Resource;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.map.ResourcesManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRect;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc2_Special;
import aoh.kingdoms.history.mainGame.SoundsManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_InvestInEconomy extends Menu
{
    public static int iSortID;
    public static int CLICK_X_TIMES;
    
    public InGame_Court_InvestInEconomy() {
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
        final int c0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.6f);
        final int c1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.2f);
        menuElements.add(new ButtonCurrentSituation(Game.lang.get("ExploitEconomy"), Game_Calendar.IMG_ECONOMY_DOWN, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.encyclopedia).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                if (InGame_CourtOptions2.idExploitEconomy != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = InGame_CourtOptions2.idExploitEconomy;
                    InGame_CourtOptions2.disableAllViews();
                    Game.menuManager.rebuildInGame_ExploitEconomy();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ExploitEconomy"), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getEconomyWithBonuses();
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(fAverage / Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
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
        if (Game.settingsManager.COUNCIL_TIPS) {
            menuElements.add(new Text_Desc2_Special(Game.lang.get("Economy0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorButton(isActive, this.getIsHovered());
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        buttonX = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click("", Game_Calendar.IMG_ECONOMY, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Images.gold).getWidth(), 0) {
            @Override
            public String getTextToDraw() {
                if (this.lastValue != InGame_Court_InvestInEconomy.CLICK_X_TIMES) {
                    this.setText("" + Game.lang.get("PerformTheActionXTimes", InGame_Court_InvestInEconomy.CLICK_X_TIMES));
                    this.lastValue = InGame_Court_InvestInEconomy.CLICK_X_TIMES;
                }
                return this.sText;
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsRect("-", buttonX, buttonY, c1W, buttonH) {
            @Override
            public void actionElement() {
                --InGame_Court_InvestInEconomy.CLICK_X_TIMES;
                if (InGame_Court_InvestInEconomy.CLICK_X_TIMES < 1) {
                    InGame_Court_InvestInEconomy.CLICK_X_TIMES = 1;
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsRect("+", buttonX, buttonY, c1W, buttonH) {
            @Override
            public void actionElement() {
                ++InGame_Court_InvestInEconomy.CLICK_X_TIMES;
                if (InGame_Court_InvestInEconomy.CLICK_X_TIMES > 9) {
                    InGame_Court_InvestInEconomy.CLICK_X_TIMES = 9;
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_Court_InvestInEconomy.iSortID == 0 || InGame_Court_InvestInEconomy.iSortID == 1, InGame_Court_InvestInEconomy.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_InvestInEconomy.iSortID == 0) {
                    InGame_Court_InvestInEconomy.iSortID = 1;
                }
                else {
                    InGame_Court_InvestInEconomy.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_InvestInEconomy();
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_InvestInEconomy.iSortID == 2 || InGame_Court_InvestInEconomy.iSortID == 3, InGame_Court_InvestInEconomy.iSortID == 3, Game.lang.get("Resource"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_InvestInEconomy.iSortID == 2) {
                    InGame_Court_InvestInEconomy.iSortID = 3;
                }
                else {
                    InGame_Court_InvestInEconomy.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_InvestInEconomy();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_InvestInEconomy.iSortID == 4 || InGame_Court_InvestInEconomy.iSortID == 5, InGame_Court_InvestInEconomy.iSortID == 5, Game.lang.get("Economy"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_InvestInEconomy.iSortID == 4) {
                    InGame_Court_InvestInEconomy.iSortID = 5;
                }
                else {
                    InGame_Court_InvestInEconomy.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_InvestInEconomy();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
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
        menuElements.add(new Text_TitleBlueSort(InGame_Court_InvestInEconomy.iSortID == 6 || InGame_Court_InvestInEconomy.iSortID == 7, InGame_Court_InvestInEconomy.iSortID == 7, Game.lang.get("Cost"), -1, buttonX, buttonY, r1W * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_InvestInEconomy.iSortID == 6) {
                    InGame_Court_InvestInEconomy.iSortID = 7;
                }
                else {
                    InGame_Court_InvestInEconomy.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_InvestInEconomy();
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
        final List<Integer> tProvincesMaxLvl = new ArrayList<Integer>();
        for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
            if (Game.canInvestInEconomy(Game.getCiv(Game.player.iCivID).getProvinceID(i))) {
                tProvincesMaxLvl.add(Game.getCiv(Game.player.iCivID).getProvinceID(i));
            }
            else {
                tProvinces.add(Game.getCiv(Game.player.iCivID).getProvinceID(i));
            }
        }
        while (tProvinces.size() > 0) {
            int toAddID = 0;
            if (InGame_Court_InvestInEconomy.iSortID == 0) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_InvestInEconomy.iSortID == 1) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_InvestInEconomy.iSortID == 2) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(o)).getResourceID()))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_InvestInEconomy.iSortID == 3) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(o)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvinces.get(toAddID)).getResourceID()))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_InvestInEconomy.iSortID == 4) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses() < Game.getProvince(tProvinces.get(o)).getEconomyWithBonuses()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_InvestInEconomy.iSortID == 5) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses() > Game.getProvince(tProvinces.get(o)).getEconomyWithBonuses()) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_InvestInEconomy.iSortID == 6) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getInvestCost(tProvinces.get(toAddID)) < Game.getInvestCost(tProvinces.get(o))) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_Court_InvestInEconomy.iSortID == 7) {
                for (int o = 1; o < tProvinces.size(); ++o) {
                    if (Game.getInvestCost(tProvinces.get(toAddID)) > Game.getInvestCost(tProvinces.get(o))) {
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
            menuElements.add(new Text_StaticBG_Economy2("" + CFG.getPrecision2(Game.getProvince(tProvinces.get(toAddID)).getEconomyWithBonuses(), 100), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                        boolean out = false;
                        if (AA_KeyManager.SHIFT_HOLD || AA_KeyManager.CTRL_HOLD) {
                            for (int i = 0; i < 5; ++i) {
                                if (Game.canInvestInEconomy(this.getCurrent())) {
                                    Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(this.getCurrent()), 10), Game_Calendar.IMG_ECONOMY);
                                    return;
                                }
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addInvestInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(this.getCurrent()), 100), Images.gold);
                                        return;
                                    }
                                    ProvinceDraw.addProvinceDot_Economy(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        else {
                            for (int u = 0; u < InGame_Court_InvestInEconomy.CLICK_X_TIMES; ++u) {
                                if (Game.canInvestInEconomy(this.getCurrent())) {
                                    Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(this.getCurrent()), 10), Game_Calendar.IMG_ECONOMY);
                                    return;
                                }
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addInvestInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(this.getCurrent()), 100), Images.gold);
                                        return;
                                    }
                                    ProvinceDraw.addProvinceDot_Economy(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        if (out) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_InvestInEconomy.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_InvestInEconomy.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.COLOR_TEXT_ECONOMY;
                                }
                            });
                        }
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.getCurrent(), false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Active_Value("" + CFG.getPrecision2(Game.getInvestCost_Legacy(tProvinces.get(toAddID)), 10), Images.legacy, buttonX, buttonY, r1W, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                        boolean out = false;
                        if (AA_KeyManager.SHIFT_HOLD || AA_KeyManager.CTRL_HOLD) {
                            for (int i = 0; i < 5; ++i) {
                                if (Game.canInvestInEconomy(this.getCurrent())) {
                                    Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(this.getCurrent()), 10), Game_Calendar.IMG_ECONOMY);
                                    return;
                                }
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addInvestInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(this.getCurrent()), 100), Images.gold);
                                        return;
                                    }
                                    ProvinceDraw.addProvinceDot_Economy(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        else {
                            for (int u = 0; u < InGame_Court_InvestInEconomy.CLICK_X_TIMES; ++u) {
                                if (Game.canInvestInEconomy(this.getCurrent())) {
                                    Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(this.getCurrent()), 10), Game_Calendar.IMG_ECONOMY);
                                    return;
                                }
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addInvestInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(this.getCurrent()), 100), Images.gold);
                                        return;
                                    }
                                    ProvinceDraw.addProvinceDot_Economy(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        if (out) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_InvestInEconomy.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_InvestInEconomy.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.COLOR_TEXT_ECONOMY;
                                }
                            });
                        }
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.getCurrent(), false);
                }
                
                @Override
                public String getTextToDraw() {
                    if (this.lastValue != Game.getInvestCost_Legacy(this.id) * InGame_Court_InvestInEconomy.CLICK_X_TIMES) {
                        this.setText("" + CFG.getPrecision2(Game.getInvestCost_Legacy(this.id) * InGame_Court_InvestInEconomy.CLICK_X_TIMES, 10));
                        this.lastValue = Game.getInvestCost_Legacy(this.id) * InGame_Court_InvestInEconomy.CLICK_X_TIMES;
                    }
                    return this.sText;
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Active_Value("" + CFG.getPrecision2(Game.getInvestCost(tProvinces.get(toAddID)), 10), Images.gold, buttonX, buttonY, r1W, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                        boolean out = false;
                        if (AA_KeyManager.SHIFT_HOLD || AA_KeyManager.CTRL_HOLD) {
                            for (int i = 0; i < 5; ++i) {
                                if (Game.canInvestInEconomy(this.getCurrent())) {
                                    Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(this.getCurrent()), 10), Game_Calendar.IMG_ECONOMY);
                                    return;
                                }
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addInvestInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(this.getCurrent()), 100), Images.gold);
                                        return;
                                    }
                                    ProvinceDraw.addProvinceDot_Economy(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        else {
                            for (int u = 0; u < InGame_Court_InvestInEconomy.CLICK_X_TIMES; ++u) {
                                if (Game.canInvestInEconomy(this.getCurrent())) {
                                    Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(this.getCurrent()), 10), Game_Calendar.IMG_ECONOMY);
                                    return;
                                }
                                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(this.getCurrent())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(this.getCurrent()), 100), Images.legacy);
                                }
                                else {
                                    if (!Game.getProvince(this.getCurrent()).addInvestInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(this.getCurrent()), 100), Images.gold);
                                        return;
                                    }
                                    ProvinceDraw.addProvinceDot_Economy(this.getCurrent());
                                    out = true;
                                }
                            }
                        }
                        if (out) {
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_InvestInEconomy.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_InvestInEconomy.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return Colors.COLOR_TEXT_ECONOMY;
                                }
                            });
                        }
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.getCurrent(), false);
                }
                
                @Override
                public String getTextToDraw() {
                    if (this.lastValue != Game.getInvestCost(this.id) * InGame_Court_InvestInEconomy.CLICK_X_TIMES) {
                        this.setText("" + CFG.getPrecision2(Game.getInvestCost(this.id) * InGame_Court_InvestInEconomy.CLICK_X_TIMES, 10));
                        this.lastValue = Game.getInvestCost(this.id) * InGame_Court_InvestInEconomy.CLICK_X_TIMES;
                    }
                    return this.sText;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            tProvinces.remove(toAddID);
        }
        menuElements.add(new Text_Title_v2Center(Game.lang.get("MaximumEconomy"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Economy1"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        if (tProvincesMaxLvl.size() == 0) {
            buttonY += CFG.PADDING;
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        else {
            buttonX = Images.boxTitleBORDERWIDTH;
            r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.3f);
            r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
            menuElements.add(new Text_TitleBlue(Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
                @Override
                public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                }
                
                @Override
                public void actionElement() {
                    if (InGame_Court_InvestInEconomy.iSortID == 0) {
                        InGame_Court_InvestInEconomy.iSortID = 1;
                    }
                    else {
                        InGame_Court_InvestInEconomy.iSortID = 0;
                    }
                    Game.menuManager.rebuildInGame_InvestInEconomy();
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
            menuElements.add(new Text_TitleBlue(Game.lang.get("Resource"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
                @Override
                public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                }
                
                @Override
                public void actionElement() {
                    if (InGame_Court_InvestInEconomy.iSortID == 2) {
                        InGame_Court_InvestInEconomy.iSortID = 3;
                    }
                    else {
                        InGame_Court_InvestInEconomy.iSortID = 2;
                    }
                    Game.menuManager.rebuildInGame_InvestInEconomy();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
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
            menuElements.add(new Text_TitleBlue(Game.lang.get("Economy"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
                @Override
                public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                }
                
                @Override
                public void actionElement() {
                    if (InGame_Court_InvestInEconomy.iSortID == 4) {
                        InGame_Court_InvestInEconomy.iSortID = 5;
                    }
                    else {
                        InGame_Court_InvestInEconomy.iSortID = 4;
                    }
                    Game.menuManager.rebuildInGame_InvestInEconomy();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
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
            menuElements.add(new Text_TitleBlue(Game.lang.get("GrowthRate"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
                @Override
                public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                }
                
                @Override
                public void actionElement() {
                    if (InGame_Court_InvestInEconomy.iSortID == 6) {
                        InGame_Court_InvestInEconomy.iSortID = 7;
                    }
                    else {
                        InGame_Court_InvestInEconomy.iSortID = 6;
                    }
                    Game.menuManager.rebuildInGame_InvestInEconomy();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
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
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.3f);
            r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.2f);
            while (tProvincesMaxLvl.size() > 0) {
                int toAddID = 0;
                if (InGame_Court_InvestInEconomy.iSortID == 0) {
                    for (int o = 1; o < tProvincesMaxLvl.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvincesMaxLvl.get(toAddID)).getProvinceName(), Game.getProvince(tProvincesMaxLvl.get(o)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_InvestInEconomy.iSortID == 1) {
                    for (int o = 1; o < tProvincesMaxLvl.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvincesMaxLvl.get(o)).getProvinceName(), Game.getProvince(tProvincesMaxLvl.get(toAddID)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_InvestInEconomy.iSortID == 2) {
                    for (int o = 1; o < tProvincesMaxLvl.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvincesMaxLvl.get(toAddID)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvincesMaxLvl.get(o)).getResourceID()))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_InvestInEconomy.iSortID == 3) {
                    for (int o = 1; o < tProvincesMaxLvl.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(ResourcesManager.getResourceName(Game.getProvince(tProvincesMaxLvl.get(o)).getResourceID()), ResourcesManager.getResourceName(Game.getProvince(tProvincesMaxLvl.get(toAddID)).getResourceID()))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_InvestInEconomy.iSortID == 4) {
                    for (int o = 1; o < tProvincesMaxLvl.size(); ++o) {
                        if (Game.getProvince(tProvincesMaxLvl.get(toAddID)).getEconomyWithBonuses() < Game.getProvince(tProvincesMaxLvl.get(o)).getEconomyWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_InvestInEconomy.iSortID == 5) {
                    for (int o = 1; o < tProvincesMaxLvl.size(); ++o) {
                        if (Game.getProvince(tProvincesMaxLvl.get(toAddID)).getEconomyWithBonuses() > Game.getProvince(tProvincesMaxLvl.get(o)).getEconomyWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_InvestInEconomy.iSortID == 6) {
                    for (int o = 1; o < tProvincesMaxLvl.size(); ++o) {
                        if (Game.getProvince(tProvincesMaxLvl.get(toAddID)).getGrowthRateWithBonuses() < Game.getProvince(tProvincesMaxLvl.get(o)).getGrowthRateWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_InvestInEconomy.iSortID == 7) {
                    for (int o = 1; o < tProvincesMaxLvl.size(); ++o) {
                        if (Game.getProvince(tProvincesMaxLvl.get(toAddID)).getGrowthRateWithBonuses() > Game.getProvince(tProvincesMaxLvl.get(o)).getGrowthRateWithBonuses()) {
                            toAddID = o;
                        }
                    }
                }
                buttonX = paddingLeft;
                menuElements.add(new Text_StaticBG_ID(Game.getProvince(tProvincesMaxLvl.get(toAddID)).getProvinceName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)tProvincesMaxLvl.get(toAddID)) {
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
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                if (Game.getProvince(tProvincesMaxLvl.get(toAddID)).getResourceID() >= 0) {
                    menuElements.add(new ButtonStatsRectIMG_Resource(ResourcesManager.getResourceName(Game.getProvince(tProvincesMaxLvl.get(toAddID)).getResourceID()), Game.getProvince(tProvincesMaxLvl.get(toAddID)).getResourceID(), buttonX, buttonY, r0W, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvincesMaxLvl.get(toAddID)) {
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
                menuElements.add(new Text_StaticBG_Economy("" + CFG.getPrecision2(Game.getProvince(tProvincesMaxLvl.get(toAddID)).getEconomyWithBonuses(), 100), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvincesMaxLvl.get(toAddID)) {
                    @Override
                    public void actionElement() {
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.getCurrent(), false);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_GrowthRate("" + Game.getProvince(tProvincesMaxLvl.get(toAddID)).getGrowthRateWithBonuses() + "%", CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvincesMaxLvl.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID) {
                            boolean out = false;
                            if (AA_KeyManager.SHIFT_HOLD || AA_KeyManager.CTRL_HOLD) {
                                for (int i = 0; i < 5; ++i) {
                                    if (!Game.getProvince(this.getCurrent()).addIncreaseGrowthRateInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseGrowthRateCost(this.getCurrent()), 100), Images.gold);
                                        break;
                                    }
                                    ProvinceDraw.addProvinceDot_GrowthRate(this.getCurrent());
                                    out = true;
                                }
                            }
                            else if (!Game.getProvince(this.getCurrent()).addIncreaseGrowthRateInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseGrowthRateCost(this.getCurrent()), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_GrowthRate(this.getCurrent());
                                out = true;
                            }
                            if (out) {
                                final MenuManager menuManager = Game.menuManager;
                                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_InvestInEconomy.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_InvestInEconomy.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                    @Override
                                    public Color getColor() {
                                        return Colors.COLOR_POPULATION;
                                    }
                                });
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverPopulationGrowth(this.getCurrent(), false, true);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                tProvincesMaxLvl.remove(toAddID);
            }
        }
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("InvestInEconomy"));
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
    
    static {
        InGame_Court_InvestInEconomy.iSortID = 0;
        InGame_Court_InvestInEconomy.CLICK_X_TIMES = 1;
    }
}
