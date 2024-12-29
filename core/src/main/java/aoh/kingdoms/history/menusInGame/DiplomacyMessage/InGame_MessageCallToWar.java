// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.DiplomacyMessage;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_Message;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Diplomacy_Flip;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Diplomacy;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.button.ButtonRuler_Diplomacy;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_ID;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.map.war.WarCivilization;
import aoh.kingdoms.history.map.war.WarManager;
import aoh.kingdoms.history.map.war.War;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Player.MessageTypes.PMessage;
import aoh.kingdoms.history.menu.Menu;

public class InGame_MessageCallToWar extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iCivID;
    public static int civRight;
    public static String key;
    
    public InGame_MessageCallToWar(final PMessage message) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title600).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        final int buttonX = paddingLeft;
        InGame_MessageCallToWar.key = message.key;
        final PMessage pMessage = message;
        if (pMessage != null) {
            InGame_MessageCallToWar.iCivID = pMessage.fromCivID;
            InGame_MessageCallToWar.civRight = 0;
            final War war = WarManager.lWars.get(pMessage.key);
            if (war != null) {
                if (war.isAggressor(InGame_MessageCallToWar.iCivID)) {
                    InGame_MessageCallToWar.civRight = war.lDefenders.get(0).iCivID;
                }
                else {
                    InGame_MessageCallToWar.civRight = war.lAggressors.get(0).iCivID;
                }
            }
            final int maxWidth = ImageManager.getImage(Images.giftBig).getWidth() + CFG.PADDING * 4;
            final int tempTitlePaddingY = CFG.PADDING;
            final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
            final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
            menuElements.add(new ButtonFlag_Diplomacy(InGame_MessageCallToWar.iCivID, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
            menuElements.add(new ButtonFlag_Diplomacy(InGame_MessageCallToWar.civRight, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
            menuElements.add(new Text_Static_ID(InGame_MessageCallToWar.civRight, Game.getCiv(InGame_MessageCallToWar.civRight).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
            menuElements.add(new Text_Static_ID(InGame_MessageCallToWar.iCivID, Game.getCiv(InGame_MessageCallToWar.iCivID).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
            menuElements.add(new TextIcon_Diplomacy(Images.warBig, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
                @Override
                public Color getColorBar() {
                    return DiplomacyManager.COLOR_WAR;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final int statsX = paddingLeft + ButtonRuler_Diplomacy.getButtonWidth() + CFG.PADDING * 2;
            final int statsW = menuWidth / 2 - statsX - CFG.PADDING / 2;
            final int statsH = (ButtonRuler_Diplomacy.getButtonHeight() - CFG.PADDING * 2) / 3;
            final int maxIconW = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() + CFG.PADDING * 2;
            menuElements.add(new ButtonStatsRectIMG_Diplomacy(Game.lang.get("AtWar"), Images.relations, statsX, buttonY, statsW * 2 + CFG.PADDING / 2 * 2, statsH, maxIconW, 0) {
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorTopStats(isActive, this.getIsHovered());
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations(InGame_MessageCallToWar.iCivID, InGame_MessageCallToWar.civRight, Game.getCiv(InGame_MessageCallToWar.civRight).diplomacy.isImprovingRelations(InGame_MessageCallToWar.iCivID), Game.getCiv(InGame_MessageCallToWar.civRight).diplomacy.isDamagingRelations(InGame_MessageCallToWar.iCivID));
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + Game.getCiv(InGame_MessageCallToWar.iCivID).getNumOfProvinces(), Images.provinces, statsX, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + Game.getCiv(InGame_MessageCallToWar.civRight).getNumOfProvinces(), Images.provinces, statsX + CFG.PADDING + statsW, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Diplomacy(Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_ALLIANCE_EXPIRES), Images.time, statsX, buttonY + (CFG.PADDING + statsH) * 2, statsW * 2 + CFG.PADDING, statsH, maxIconW, 0) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CallToArms"), "", Images.alliance, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", this.getText(), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            RulersManager.loadRulerIMG_DiplomacyLeft(InGame_MessageCallToWar.iCivID);
            RulersManager.loadRulerIMG_DiplomacyRight(InGame_MessageCallToWar.civRight);
            menuElements.add(new ButtonRuler_Diplomacy(InGame_MessageCallToWar.iCivID, buttonX + CFG.PADDING, buttonY));
            menuElements.add(new ButtonRuler_Diplomacy(InGame_MessageCallToWar.civRight, menuWidth - paddingLeft - ButtonRuler_Diplomacy.getButtonWidth() - CFG.PADDING, buttonY) {
                @Override
                public Image getRulerImage() {
                    return RulersManager.rulerIMG_DiplomacyRight;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            paddingLeft += CFG.PADDING;
            menuElements.add(new ButtonGame(Game.lang.get("Refuse"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
                @Override
                public void actionElement() {
                    final PMessage tMessage = Game.player.getMessage(InGame_MessageCallToWar.key);
                    if (tMessage != null) {
                        tMessage.onAccept();
                    }
                    Game.menuManager.setVisibleInGame_PopUp(false);
                    Game.player.removeMessage(InGame_MessageCallToWar.key);
                }
                
                @Override
                public void buildElementHover() {
                    final PMessage tMessage = Game.player.getMessage(InGame_MessageCallToWar.key);
                    if (tMessage != null) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Refuse"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Legacy"), "" + CFG.getPrecision2(GameValues.diplomacy.CALL_TO_ARMS_REFUSE_LEGACY, 100), Images.legacy, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_NEGATIVE));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    else {
                        this.menuElementHover = null;
                    }
                }
            });
            menuElements.add(new ButtonGame(Game.lang.get("Accept"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
                @Override
                public void actionElement() {
                    InGame_MessageCallToWar.confirm();
                }
                
                @Override
                public void buildElementHover() {
                    final PMessage tMessage = Game.player.getMessage(InGame_MessageCallToWar.key);
                    if (tMessage != null) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("JoinAWar"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                    else {
                        this.menuElementHover = null;
                    }
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        }
        else {
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_MessagesSavePos") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_MessagesSavePos();
                }
            });
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG_Message(Game.lang.get("CallToArms"), true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_MessageCallToWar.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, Math.min((int)(CFG.GAME_HEIGHT * 0.2f), CFG.GAME_HEIGHT / 2 - (menuHeight + titleHeight) / 2), menuWidth, menuHeight, menuElements, pMessage != null, true);
        InGame_MessageCallToWar.lTime = CFG.currentTimeMillis;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_MessageCallToWar.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_MessageCallToWar.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_MessageCallToWar.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    public static void confirm() {
        final PMessage tMessage = Game.player.getMessage(InGame_MessageCallToWar.key);
        if (tMessage != null) {
            tMessage.onRefuse();
        }
        Game.menuManager.setVisibleInGame_PopUp(false);
        Game.player.removeMessage(InGame_MessageCallToWar.key);
    }
    
    static {
        InGame_MessageCallToWar.lTime = 0L;
        InGame_MessageCallToWar.iCivID = 0;
        InGame_MessageCallToWar.civRight = 0;
    }
}
