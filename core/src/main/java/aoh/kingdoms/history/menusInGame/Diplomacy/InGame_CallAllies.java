// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonGame_ImageSparks;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_GreenRed;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.map.war.War;
import aoh.kingdoms.history.map.war.WarManager;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_ID;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class InGame_CallAllies extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iCivID;
    public static String warKey;
    public static List<Integer> callToWar;
    
    public InGame_CallAllies(final int nCivID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title600).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        final int buttonX = paddingLeft;
        InGame_CallAllies.callToWar.clear();
        InGame_CallAllies.iCivID = nCivID;
        final int maxWidth = ImageManager.getImage(Images.warBig).getWidth() + CFG.PADDING * 4;
        final int tempTitlePaddingY = CFG.PADDING;
        final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new ButtonFlag_Diplomacy(Game.player.iCivID, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
        menuElements.add(new ButtonFlag_Diplomacy(InGame_CallAllies.iCivID, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
        menuElements.add(new Text_Static_ID(InGame_CallAllies.iCivID, Game.getCiv(InGame_CallAllies.iCivID).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
        menuElements.add(new Text_Static_ID(Game.player.iCivID, Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
        menuElements.add(new TextIcon_Diplomacy(Images.warBig, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
            @Override
            public Color getColorBar() {
                return DiplomacyManager.COLOR_WAR;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        try {
            final int buttonH = CFG.BUTTON_HEIGHT3;
            final List<Integer> allies = DiplomacyManager.declareWar_AlliesAttacker(Game.player.iCivID, InGame_CallAllies.iCivID);
            if (WarManager.lWars.containsKey(InGame_CallAllies.warKey)) {
                for (int i = allies.size() - 1; i >= 0; --i) {
                    if (WarManager.lWars.get(InGame_CallAllies.warKey).isAggressor(allies.get(i)) || WarManager.lWars.get(InGame_CallAllies.warKey).isDefender(allies.get(i))) {
                        allies.remove(i);
                    }
                }
            }
            if (allies.size() == 0) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            else {
                menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.lang.get("CallAllies"), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, Game.player.iCivID) {
                    @Override
                    public void actionElement() {
                        final List<Integer> allies = DiplomacyManager.declareWar_AlliesAttacker(Game.player.iCivID, InGame_CallAllies.iCivID);
                        if (allies.size() > 0) {
                            for (int i = allies.size() - 1; i >= 0; --i) {
                                if (InGame_CallAllies.callToWar.contains(allies.get(i))) {
                                    for (int a = InGame_CallAllies.callToWar.size() - 1; a >= 0; --a) {
                                        if (InGame_CallAllies.callToWar.get(a).equals(allies.get(i))) {
                                            InGame_CallAllies.callToWar.remove(a);
                                            break;
                                        }
                                    }
                                }
                                else {
                                    InGame_CallAllies.callToWar.add(allies.get(i));
                                }
                            }
                        }
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                for (int i = 0; i < allies.size(); ++i) {
                    menuElements.add(new Text_StaticBG_ID_FlagCiv_GreenRed("" + Game.getCiv(allies.get(i)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, (int)allies.get(i)) {
                        @Override
                        public void actionElement() {
                            if (InGame_CallAllies.callToWar.contains(this.getCurrent())) {
                                for (int i = InGame_CallAllies.callToWar.size() - 1; i >= 0; --i) {
                                    if (InGame_CallAllies.callToWar.get(i) == this.getCurrent()) {
                                        InGame_CallAllies.callToWar.remove(i);
                                        break;
                                    }
                                }
                            }
                            else {
                                InGame_CallAllies.callToWar.add(this.getCurrent());
                            }
                        }
                        
                        @Override
                        public boolean getCheckboxState() {
                            return InGame_CallAllies.callToWar.contains(this.getCurrent());
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CallAlly") + ": " + Game.getCiv(this.getCurrent()).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        paddingLeft += CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("Confirm"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true, Images.v) {
            @Override
            public void actionElement() {
                InGame_CallAllies.confirm();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Confirm"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.v, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.DIPLOMACY_CLICK;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("CallAllies"), true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_CallAllies.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, Math.min((int)(CFG.GAME_HEIGHT * 0.2f), CFG.GAME_HEIGHT / 2 - (menuHeight + titleHeight) / 2), menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_CallAllies.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_CallAllies.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_CallAllies.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    public static final void confirm() {
        if (InGame_CallAllies.callToWar.size() > 0 && WarManager.lWars.containsKey(InGame_CallAllies.warKey)) {
            if (WarManager.lWars.get(InGame_CallAllies.warKey).isDefender(Game.player.iCivID)) {
                for (int i = 0; i < InGame_CallAllies.callToWar.size(); ++i) {
                    WarManager.lWars.get(InGame_CallAllies.warKey).addDefender(InGame_CallAllies.callToWar.get(i));
                }
            }
            else if (WarManager.lWars.get(InGame_CallAllies.warKey).isAggressor(Game.player.iCivID)) {
                for (int i = 0; i < InGame_CallAllies.callToWar.size(); ++i) {
                    WarManager.lWars.get(InGame_CallAllies.warKey).addAggressor(InGame_CallAllies.callToWar.get(i));
                }
            }
        }
        Game.menuManager.setVisibleInGame_PopUp(false);
    }
    
    static {
        InGame_CallAllies.lTime = 0L;
        InGame_CallAllies.iCivID = 0;
        InGame_CallAllies.warKey = "";
        InGame_CallAllies.callToWar = new ArrayList<Integer>();
    }
}
