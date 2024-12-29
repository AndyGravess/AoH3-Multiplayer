// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court.World;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Iterator;
import aoh.kingdoms.history.map.map.Map_Data;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.button.ButtonStats_WarScore;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Diplomacy;
import aoh.kingdoms.history.menu_element.MessageWar;
import aoh.kingdoms.history.menusInGame.InGame_War;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_ID;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.map.war.WarCivilization;
import aoh.kingdoms.history.map.war.War;
import aoh.kingdoms.history.map.war.WarManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.SoundsManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_WorldWars extends Menu
{
    public InGame_Court_WorldWars() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = CFG.PADDING * 2 + InGame_CourtOptions2.getMenuWidth();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING * 2;
        final int buttonX = paddingLeft;
        int buttonY = CFG.PADDING;
        menuElements.add(new ButtonCurrentSituation(Game.lang.get(GameValues.court.COUNCIL_NAME), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.council).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
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
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
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
        final int maxWidth = ImageManager.getImage(Images.war).getWidth() + CFG.PADDING * 4;
        final int tempTitlePaddingY = CFG.PADDING;
        final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        final int warscoreW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 2) / 2;
        final int casualtiesW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 2) / 4;
        final int statsH = CFG.TEXT_HEIGHT + CFG.PADDING * 6;
        final int maxIconW = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() + CFG.PADDING * 2;
        final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new Text_Title_v2Center(Game.lang.get("CurrentWars"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (WarManager.iWarsSize > 0) {
            for (final War nWar : WarManager.lWars.values()) {
                try {
                    menuElements.add(new ButtonFlag_Diplomacy(WarManager.lWars.get(nWar.key).lAggressors.get(0).iCivID, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
                    menuElements.add(new ButtonFlag_Diplomacy(WarManager.lWars.get(nWar.key).lDefenders.get(0).iCivID, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
                    menuElements.add(new Text_Static_ID(WarManager.lWars.get(nWar.key).lDefenders.get(0).iCivID, Game.getCiv(WarManager.lWars.get(nWar.key).lDefenders.get(0).iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
                    menuElements.add(new Text_Static_ID(WarManager.lWars.get(nWar.key).lAggressors.get(0).iCivID, Game.getCiv(WarManager.lWars.get(nWar.key).lAggressors.get(0).iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
                    menuElements.add(new TextIcon_Diplomacy(Images.war, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
                        public String warKey;
                        
                        @Override
                        public void setText2(final String sText) {
                            this.warKey = sText;
                        }
                        
                        @Override
                        public void actionElement() {
                            if (this.warKey != null) {
                                if (Game.menuManager.getVisibleInGame_War() && InGame_War.key.equals(this.warKey)) {
                                    Game.menuManager.setVisibleInGame_War(false);
                                }
                                else {
                                    Game.gameActiveProvince.resetLastActiveProvince();
                                    Game.setActiveProvinceID(-1);
                                    Game.clearActiveArmy();
                                    Game.menuManager.showInGame_Battle_HideMenus();
                                    InGame_War.key = this.warKey;
                                    Game.menuManager.rebuildInGame_War();
                                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_WAR_VIEW) {
                                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_WAR_VIEW);
                                    }
                                    else {
                                        Game.mapModes.updateWarView(InGame_War.key);
                                    }
                                }
                            }
                        }
                        
                        @Override
                        public void buildElementHover() {
                            this.menuElementHover = MessageWar.getHoverWar(this.warKey, WarManager.lWars.get(this.warKey).lDefenders.get(0).iCivID);
                        }
                    });
                    menuElements.get(menuElements.size() - 1).setText2(nWar.key);
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(WarManager.lWars.get(nWar.key).getCasualties_Aggressors()), Images.skull, paddingLeft, buttonY, casualtiesW, statsH, maxIconW, 0) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Casualties") + ": ", CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.skull, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(WarManager.lWars.get(nWar.key).getCasualties_Aggressors()), Images.skull, menuWidth - paddingLeft - casualtiesW, buttonY, casualtiesW, statsH, maxIconW, 0) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Casualties") + ": ", CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.skull, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    menuElements.add(new ButtonStats_WarScore(Game.lang.get("WarScore") + ": ", paddingLeft + casualtiesW + CFG.PADDING, buttonY, warscoreW, statsH, WarManager.lWars.get(nWar.key).lAggressors.get(0).iCivID, WarManager.lWars.get(nWar.key).lDefenders.get(0).iCivID, nWar.key) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            try {
                                final float lastValue2 = (float)WarManager.lWars.get(this.key).getWarScore_Side(this.getCurrent());
                                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("WarScore") + ": ", CFG.FONT_BOLD));
                                nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(Math.min(Math.max(WarManager.lWars.get(this.key).warScore * lastValue2, -100.0f), 100.0f), 1) + "%", CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.victoryPoints, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                if (Math.abs(WarManager.lWars.get(this.key).warScore) >= 1.0f) {
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("InFavorOf") + ": ", CFG.FONT_REGULAR));
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv((WarManager.lWars.get(this.key).warScore > 0.0f) ? WarManager.lWars.get(this.key).lAggressors.get(0).iCivID : WarManager.lWars.get(this.key).lDefenders.get(0).iCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_POSITIVE));
                                    nData.add(new MenuElement_HoverElement_Type_FlagTitle((WarManager.lWars.get(this.key).warScore > 0.0f) ? WarManager.lWars.get(this.key).lAggressors.get(0).iCivID : WarManager.lWars.get(this.key).lDefenders.get(0).iCivID, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("OccupiedProvinces") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(WarManager.lWars.get(this.key).warScoreFromOccupiedProvinces * lastValue2, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("WarScoreFromBattles") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(WarManager.lWars.get(this.key).warScoreFromBattles * lastValue2, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TickingWarScore") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(WarManager.lWars.get(this.key).tickingWarScore * lastValue2, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("EachMonthTheWinningSideGainsXOfTheTickingWarScoreBasedOnTheCurrentOverallWarScoreFromBattlesAndOccupiedProvinces", CFG.getPrecision2(GameValues.war.TICKING_WAR_SCORE_EACH_MONTH * 100.0f, 100)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            catch (final Exception ex) {}
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                        
                        @Override
                        public void actionElement() {
                            if (this.key != null) {
                                if (Game.menuManager.getVisibleInGame_War() && InGame_War.key.equals(this.key)) {
                                    Game.menuManager.setVisibleInGame_War(false);
                                }
                                else {
                                    Game.gameActiveProvince.resetLastActiveProvince();
                                    Game.setActiveProvinceID(-1);
                                    Game.clearActiveArmy();
                                    Game.menuManager.showInGame_Battle_HideMenus();
                                    InGame_War.key = this.key;
                                    Game.menuManager.rebuildInGame_War();
                                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_WAR_VIEW) {
                                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_WAR_VIEW);
                                    }
                                    else {
                                        Game.mapModes.updateWarView(InGame_War.key);
                                    }
                                }
                            }
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        else {
            menuElements.add(new Text_StaticBG(Game.lang.get("None") + ".", CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, ImageManager.getImage(Images.generalFrameBattle).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.Name));
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
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }
}
