// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Value;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Flag;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Formable;
import aoh.kingdoms.history.menu_element.button.ButtonFlag;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_SellProvince extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int sellToCivID;
    
    public InGame_SellProvince(final int nSellToCivID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        int buttonX = paddingLeft;
        InGame_SellProvince.sellToCivID = nSellToCivID;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        buttonY += CFG.PADDING;
        buttonX = paddingLeft2;
        menuElements.add(new ButtonFlag(InGame_SellProvince.sellToCivID, buttonX, buttonY, true) {
            @Override
            public void actionElement() {
            }
            
            @Override
            public int getFlagCivID() {
                return InGame_SellProvince.sellToCivID;
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        final int bHeight = (ButtonFlag_Formable.getButtonHeight() - CFG.PADDING) / 2;
        menuElements.add(new Text_StaticBG_RulerTitle(Game.getCiv(InGame_SellProvince.sellToCivID).getCivName(), buttonX, buttonY, menuWidth - buttonX - paddingLeft2, bHeight) {
            @Override
            public void actionElement() {
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Provinces") + ": ", "" + Game.getCiv(InGame_SellProvince.sellToCivID).getNumOfProvinces(), Images.provinces, buttonX, buttonY + bHeight + CFG.PADDING, menuWidth - buttonX - paddingLeft2, bHeight, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public String getText2() {
                return "" + Game.getCiv(InGame_SellProvince.sellToCivID).getNumOfProvinces();
            }
        });
        buttonX = paddingLeft;
        buttonY += ButtonFlag_Formable.getButtonHeight() + CFG.PADDING;
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY - (ButtonFlag_Formable.getButtonHeight() + CFG.PADDING * 2), menuWidth - paddingLeft * 2, ButtonFlag_Formable.getButtonHeight() + CFG.PADDING * 2));
        buttonY += CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("Close"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
                Game.menuManager.rebuildInGame_Civ(true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final List<Integer> tProvinces = new ArrayList<Integer>();
        for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
            if (Game.getCiv(Game.player.iCivID).getProvinceID(i) != Game.getCiv(Game.player.iCivID).getCapitalProvinceID()) {
                tProvinces.add(Game.getCiv(Game.player.iCivID).getProvinceID(i));
            }
        }
        final int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH) * 0.25f);
        final int r0W2 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.75f);
        final int buttonHProvinces = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Provinces"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (!tProvinces.isEmpty()) {
            while (tProvinces.size() > 0) {
                int toAddID = 0;
                for (int o = 1, oSize = tProvinces.size(); o < oSize; ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                        toAddID = o;
                    }
                }
                menuElements.add(new Text_StaticBG_ID_Flag(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W2, buttonHProvinces, (int)tProvinces.get(toAddID), false) {
                    boolean confirm = false;
                    
                    @Override
                    public void actionElement() {
                        if (this.confirm) {
                            if (InGame_SellProvince.getSellProvince(this.getCurrent())) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = InGame_SellProvince.sellToCivID;
                                Game.menuManager.rebuildInGame_Info(Game.lang.get("ProvinceSold"), Game.getProvince(this.getCurrent()).getProvinceName());
                                InGame_Info.imgID = Images.infoCrown;
                                this.setText(Game.lang.get("ProvinceSold") + ": " + Game.getProvince(this.getCurrent()).getProvinceName());
                            }
                        }
                        else {
                            this.confirm = true;
                            this.setText(Game.lang.get("Confirm") + ": " + this.getText());
                        }
                    }
                    
                    @Override
                    public void actionElementPPM() {
                        this.confirm = false;
                        this.setText(Game.getProvince(this.getCurrent()).getProvinceName());
                        Game.mapCoords.centerToProvinceID(this.getCurrent());
                    }
                    
                    @Override
                    public int getFlagID() {
                        if (Game.getProvince(this.getCurrent()).peaceTreatyIsTaken) {
                            return Game.player.iCivID;
                        }
                        return super.getFlagID();
                    }
                    
                    @Override
                    protected Color getColor(final boolean isActive) {
                        if (this.confirm && Game.getProvince(this.getCurrent()).getCivID() != InGame_SellProvince.sellToCivID) {
                            return Colors.getColorNegative(isActive, this.getIsHovered());
                        }
                        return super.getColor(isActive);
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SellProvince") + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.getProvince(this.getCurrent()).getProvinceName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(this.getCurrent()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", CFG.getPrecision2(InGame_SellProvince.getSellProvince_Gold(this.getCurrent()), 10), Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonStatsRectIMG_Active_Value("" + CFG.getPrecision2(getSellProvince_Gold(tProvinces.get(toAddID)), 100), Images.gold, buttonX, buttonY, r1W, buttonHProvinces, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.getProvince(this.getCurrent()).getProvinceName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(this.getCurrent()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(this.getCurrent()).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", CFG.getPrecision2(InGame_SellProvince.getSellProvince_Gold(this.getCurrent()), 10), Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX = paddingLeft;
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                tProvinces.remove(toAddID);
            }
        }
        else {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        for (int j = menuElements.size() - 1; j >= 0; --j) {
            buttonY = Math.max(buttonY, menuElements.get(j).getPosY() + menuElements.get(j).getHeight() + CFG.PADDING);
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("SellProvince"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_SellProvince.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_SellProvince.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_SellProvince.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_SellProvince.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    public static boolean getSellProvince(final int provinceID) {
        if (Game.player.iCivID == Game.getProvince(provinceID).getCivID()) {
            final boolean zeroProvinces = Game.getCiv(InGame_SellProvince.sellToCivID).getNumOfProvinces() == 0;
            Game.getProvince(provinceID).setCivID(InGame_SellProvince.sellToCivID);
            try {
                if (zeroProvinces || Game.getCiv(InGame_SellProvince.sellToCivID).getCapitalProvinceID() == provinceID || Game.getCiv(InGame_SellProvince.sellToCivID).getCapitalProvinceID() < 0 || Game.getProvince(Game.getCiv(InGame_SellProvince.sellToCivID).getCapitalProvinceID()).getCivID() != InGame_SellProvince.sellToCivID) {
                    Game.getCiv(InGame_SellProvince.sellToCivID).moveCapital_ToLargestProvince();
                }
            }
            catch (final Exception ex) {}
            final Civilization civ = Game.getCiv(Game.player.iCivID);
            civ.fGold += getSellProvince_Gold(provinceID);
            return true;
        }
        return false;
    }
    
    public static float getSellProvince_Gold(final int provinceID) {
        return Game.getProvince(provinceID).fProvinceValue * GameValues.diplomacy.SELL_PROVINCE_GOLD_PER_PROVINCE_VALUE;
    }
    
    static {
        InGame_SellProvince.lTime = 0L;
        InGame_SellProvince.sellToCivID = 0;
    }
}
