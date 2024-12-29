// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.mainGame.Steam.SteamAchievementsManager;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonGame_ImageSparks;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.textStatic.TextBonus;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.map.RulersManager;
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
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_SendSpy extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iCivID;
    
    public InGame_SendSpy(final int nCivID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title600).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        final int buttonX = paddingLeft;
        InGame_SendSpy.iCivID = nCivID;
        final int maxWidth = ImageManager.getImage(Images.allianceBig).getWidth() + CFG.PADDING * 4;
        final int tempTitlePaddingY = CFG.PADDING;
        final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new ButtonFlag_Diplomacy(Game.player.iCivID, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
        menuElements.add(new ButtonFlag_Diplomacy(InGame_SendSpy.iCivID, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
        menuElements.add(new Text_Static_ID(InGame_SendSpy.iCivID, Game.getCiv(InGame_SendSpy.iCivID).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
        menuElements.add(new Text_Static_ID(Game.player.iCivID, Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
        menuElements.add(new TextIcon_Diplomacy(Images.spyBig, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
            @Override
            public Color getColorBar() {
                return DiplomacyManager.COLOR_SPY;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int statsX = paddingLeft + ButtonRuler_Diplomacy.getButtonWidth() + CFG.PADDING * 2;
        final int statsW = menuWidth / 2 - statsX - CFG.PADDING / 2;
        final int statsH = (ButtonRuler_Diplomacy.getButtonHeight() - CFG.PADDING * 2) / 3;
        final int maxIconW = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() + CFG.PADDING * 2;
        menuElements.add(new ButtonStatsRectIMG_Diplomacy(Game.lang.get("Opinion") + ": " + CFG.getPrecision2(Game.getCiv(InGame_SendSpy.iCivID).diplomacy.getRelation(Game.player.iCivID), 1), Images.relations, statsX, buttonY, statsW * 2 + CFG.PADDING / 2 * 2, statsH, maxIconW, 0) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorTopStats(isActive, this.getIsHovered());
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations(InGame_SendSpy.iCivID, Game.player.iCivID, Game.getCiv(Game.player.iCivID).diplomacy.isImprovingRelations(InGame_SendSpy.iCivID), Game.getCiv(Game.player.iCivID).diplomacy.isDamagingRelations(InGame_SendSpy.iCivID));
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(Game.getCiv(Game.player.iCivID).iRegimentsLimit), Images.regimentsLimit, statsX, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RegimentsLimit") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).iRegimentsLimit, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.regimentsLimit, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), Images.provinces, statsX, buttonY + (CFG.PADDING + statsH) * 2, statsW, statsH, maxIconW, 0) {
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
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getShortNumber(Game.getCiv(InGame_SendSpy.iCivID).iRegimentsLimit), Images.regimentsLimit, statsX + CFG.PADDING + statsW, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RegimentsLimit") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(InGame_SendSpy.iCivID).iRegimentsLimit, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.regimentsLimit, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + Game.getCiv(InGame_SendSpy.iCivID).getNumOfProvinces(), Images.provinces, statsX + CFG.PADDING + statsW, buttonY + (CFG.PADDING + statsH) * 2, statsW, statsH, maxIconW, 0) {
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
        RulersManager.loadRulerIMG_DiplomacyLeft(Game.player.iCivID);
        RulersManager.loadRulerIMG_DiplomacyRight(InGame_SendSpy.iCivID);
        menuElements.add(new ButtonRuler_Diplomacy(Game.player.iCivID, buttonX + CFG.PADDING, buttonY));
        menuElements.add(new ButtonRuler_Diplomacy(InGame_SendSpy.iCivID, menuWidth - paddingLeft - ButtonRuler_Diplomacy.getButtonWidth() - CFG.PADDING, buttonY) {
            @Override
            public Image getRulerImage() {
                return RulersManager.rulerIMG_DiplomacyRight;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int iconWidth = (int)Math.ceil(ImageManager.getImage(Images.gold).getWidth() * 1.5f);
        final int costW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        menuElements.add(new TextBonus(Game.lang.get("Cost") + ": ", "" + DiplomacyManager.sendSpyCost(Game.player.iCivID, InGame_SendSpy.iCivID), Images.gold, paddingLeft, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth));
        menuElements.add(new TextBonus("", "" + Game.lang.get("DaysX", DiplomacyManager.sendSpyTime(Game.player.iCivID, InGame_SendSpy.iCivID)), Images.time, paddingLeft + costW + CFG.PADDING, buttonY, costW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        paddingLeft += CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("SendSpy"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, Game.getCiv(Game.player.iCivID).fGold >= DiplomacyManager.sendSpyCost(Game.player.iCivID, InGame_SendSpy.iCivID), Images.spy) {
            @Override
            public void actionElement() {
                InGame_SendSpy.confirm();
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.DIPLOMACY_CLICK;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("EspionageMission"), true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_SendSpy.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, Math.min((int)(CFG.GAME_HEIGHT * 0.2f), CFG.GAME_HEIGHT / 2 - (menuHeight + titleHeight) / 2), menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_SendSpy.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_SendSpy.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_SendSpy.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    public static void confirm() {
        DiplomacyManager.startEspionageMission(InGame_SendSpy.iCivID);
        SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.SPY);
        InGame_Info.iCivID = Game.player.iCivID;
        InGame_Info.iCivID2 = InGame_SendSpy.iCivID;
        Game.menuManager.rebuildInGame_Info(Game.lang.get("EspionageMission"), Game.getCiv(InGame_SendSpy.iCivID).getCivName());
        InGame_Info.imgID = Images.infoDiplomacy;
        Game.menuManager.setVisibleInGame_PopUp(false);
        Game.menuManager.rebuildInGame_Right();
    }
    
    static {
        InGame_SendSpy.lTime = 0L;
        InGame_SendSpy.iCivID = 0;
    }
}
