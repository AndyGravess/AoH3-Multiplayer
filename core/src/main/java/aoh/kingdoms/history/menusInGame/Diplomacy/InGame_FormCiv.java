// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_TitleSpecial_ID;
import aoh.kingdoms.history.menu_element.button.ButtonGame_ImageSparks;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.map.FormableCivManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Formable;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_FormCiv extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int formCivID;
    public static boolean GO_BACK_TO_COURT;
    
    public InGame_FormCiv(final int playerCivID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        int buttonX = paddingLeft;
        InGame_FormCiv.formCivID = playerCivID;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        buttonY += CFG.PADDING;
        buttonX = paddingLeft2;
        menuElements.add(new ButtonFlag_Formable(InGame_FormCiv.formCivID, buttonX, buttonY) {
            @Override
            public void actionElement() {
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        final int bHeight = (ButtonFlag_Formable.getButtonHeight() - CFG.PADDING) / 2;
        menuElements.add(new Text_StaticBG_RulerTitle(Game.lang.getCiv(Game.getCiv(Game.player.iCivID).sTagsCanForm.get(InGame_FormCiv.formCivID)), buttonX, buttonY, menuWidth - buttonX - paddingLeft2, bHeight) {
            @Override
            public void actionElement() {
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Provinces") + ": ", Game.player.formableCivs.get(InGame_FormCiv.formCivID).getControlledProvinces(Game.player.iCivID) + " / " + Game.player.formableCivs.get(InGame_FormCiv.formCivID).getProvincesSize_WithoutNeutral(), Images.provinces, buttonX, buttonY + bHeight + CFG.PADDING, menuWidth - buttonX - paddingLeft2, bHeight, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ControlsProvinces") + ": ", this.sText2, this.imageID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX = paddingLeft;
        buttonY += ButtonFlag_Formable.getButtonHeight() + CFG.PADDING;
        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY - (ButtonFlag_Formable.getButtonHeight() + CFG.PADDING * 2), menuWidth - paddingLeft * 2, ButtonFlag_Formable.getButtonHeight() + CFG.PADDING * 2));
        buttonY += CFG.PADDING;
        long population = 0L;
        float economy = 0.0f;
        for (int i = 0; i < Game.player.formableCivs.get(InGame_FormCiv.formCivID).getProvincesSize(); ++i) {
            if (Game.getProvince(Game.player.formableCivs.get(InGame_FormCiv.formCivID).Provinces.get(i)).getCivID() > 0) {
                population += Game.getProvince(Game.player.formableCivs.get(InGame_FormCiv.formCivID).Provinces.get(i)).getPopulationTotal();
                economy += Game.getProvince(Game.player.formableCivs.get(InGame_FormCiv.formCivID).Provinces.get(i)).getEconomyWithBonuses();
            }
        }
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + population), Images.population, buttonX, buttonY, menuWidth - buttonX * 2, bHeight, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_POPULATION;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Economy") + ": ", "" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(economy, 1)), Game_Calendar.IMG_ECONOMY, buttonX, buttonY, menuWidth - buttonX * 2, bHeight, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_ECONOMY;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
                if (InGame_FormCiv.GO_BACK_TO_COURT) {
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }
                    Game.menuManager.rebuildInGame_Government();
                    Game.menuManager.setVisibleInGame_Court(true);
                }
                else {
                    InGame_Civ.iRebuildToCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_Civ(true);
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                }
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("FormCivilization"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, FormableCivManager.canFormACiv(Game.player.iCivID, Game.player.formableCivs.get(InGame_FormCiv.formCivID).FormableCivTag), Images.v) {
            @Override
            public void actionElement() {
                InGame_FormCiv.confirm();
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = FormableCivManager.getHover(InGame_FormCiv.formCivID);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final List<Integer> civs = new ArrayList<Integer>();
        for (int j = 0; j < Game.player.formableCivs.get(InGame_FormCiv.formCivID).getProvincesSize(); ++j) {
            if (Game.getProvince(Game.player.formableCivs.get(InGame_FormCiv.formCivID).Provinces.get(j)).getCivID() > 0 && !civs.contains(Game.getProvince(Game.player.formableCivs.get(InGame_FormCiv.formCivID).Provinces.get(j)).getCivID())) {
                civs.add(Game.getProvince(Game.player.formableCivs.get(InGame_FormCiv.formCivID).Provinces.get(j)).getCivID());
            }
        }
        if (!civs.isEmpty()) {
            final int buttonYBefore = buttonY;
            buttonY += CFG.PADDING;
            final int topTitleH = CFG.BUTTON_HEIGHT3 - CFG.PADDING * 2;
            menuElements.add(new Text_StaticBG_TitleSpecial_ID(Game.lang.get("Civilizations"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, topTitleH, civs.size()) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(this.getText() + ": ", CFG.getNumberWithSpaces("" + this.id), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            buttonX = paddingLeft;
            for (int k = 0; k < civs.size(); ++k) {
                if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth - paddingLeft) {
                    buttonY += ButtonFlag_Diplomacy.getButtonHeight() + CFG.PADDING;
                    buttonX = paddingLeft;
                }
                if (Game.getCiv(civs.get(k)).getNumOfProvinces() > 0) {
                    menuElements.add(new ButtonFlag_Diplomacy((int)civs.get(k), buttonX, buttonY, true) {
                        @Override
                        public void actionElement() {
                            if (Game.getCiv(this.iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.iCivID).getCapitalProvinceID()).getCivID() == this.iCivID) {
                                Game.mapCoords.centerToProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                            }
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCivID, (Game.getCiv(this.iCivID).iCivRankPosition >= 0) ? (Game.lang.get("Ranking") + ": " + Game.getCiv(this.iCivID).iCivRankPosition) : Game.getProvince(Game.getCiv(this.iCivID).getCapitalProvinceID()).getProvinceName()));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            int numOfProvinces = 0;
                            for (int i = 0; i < Game.player.formableCivs.get(InGame_FormCiv.formCivID).getProvincesSize(); ++i) {
                                if (Game.getProvince(Game.player.formableCivs.get(InGame_FormCiv.formCivID).Provinces.get(i)).getCivID() == this.iCivID) {
                                    ++numOfProvinces;
                                }
                            }
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", CFG.getNumberWithSpaces("" + numOfProvinces), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
            }
            for (int k = menuElements.size() - 1; k >= 0; --k) {
                buttonY = Math.max(buttonY, menuElements.get(k).getPosY() + menuElements.get(k).getHeight() + CFG.PADDING);
            }
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonYBefore, menuWidth - paddingLeft2 * 2, buttonY - buttonYBefore));
            buttonY += CFG.PADDING;
            buttonX = paddingLeft;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("FormCivilization"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_FormCiv.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_FormCiv.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_FormCiv.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_FormCiv.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    public static void confirm() {
        if (FormableCivManager.canFormACiv(Game.player.iCivID, Game.player.formableCivs.get(InGame_FormCiv.formCivID).FormableCivTag) && FormableCivManager.formCiv(Game.player.iCivID, Game.player.formableCivs.get(InGame_FormCiv.formCivID).FormableCivTag)) {
            Game.soundsManager.playSound(SoundsManager.SOUND_FORMABLE);
            Game.menuManager.addToastPositive(Game.lang.get("Civilization") + ": ", Game.getCiv(Game.player.iCivID).getCivName(), Images.v);
        }
        Game.menuManager.setVisibleInGame_PopUp(false);
        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEFAULT) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
    }
    
    static {
        InGame_FormCiv.lTime = 0L;
        InGame_FormCiv.formCivID = 0;
        InGame_FormCiv.GO_BACK_TO_COURT = true;
    }
}
