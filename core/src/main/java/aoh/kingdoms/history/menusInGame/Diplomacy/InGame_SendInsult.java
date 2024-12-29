// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonGame_ImageSparks;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.textStatic.TextBonus;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc;
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

public class InGame_SendInsult extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iCivID;
    
    public InGame_SendInsult(final int nCivID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title600).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        final int buttonX = paddingLeft;
        InGame_SendInsult.iCivID = nCivID;
        final int maxWidth = ImageManager.getImage(Images.insultBig).getWidth() + CFG.PADDING * 4;
        final int tempTitlePaddingY = CFG.PADDING;
        final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new ButtonFlag_Diplomacy(Game.player.iCivID, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
        menuElements.add(new ButtonFlag_Diplomacy(InGame_SendInsult.iCivID, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
        menuElements.add(new Text_Static_ID(InGame_SendInsult.iCivID, Game.getCiv(InGame_SendInsult.iCivID).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
        menuElements.add(new Text_Static_ID(Game.player.iCivID, Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
        menuElements.add(new TextIcon_Diplomacy(Images.insultBig, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
            @Override
            public Color getColorBar() {
                return DiplomacyManager.COLOR_INSULT;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int statsX = paddingLeft + ButtonRuler_Diplomacy.getButtonWidth() + CFG.PADDING * 2;
        final int statsW = menuWidth / 2 - statsX - CFG.PADDING / 2;
        final int statsH = (ButtonRuler_Diplomacy.getButtonHeight() - CFG.PADDING * 2) / 3;
        final int maxIconW = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() + CFG.PADDING * 2;
        menuElements.add(new ButtonStatsRectIMG_Diplomacy(Game.lang.get("Opinion") + ": " + CFG.getPrecision2(Game.getCiv(InGame_SendInsult.iCivID).diplomacy.getRelation(Game.player.iCivID), 1), Images.relations, statsX, buttonY, statsW * 2 + CFG.PADDING / 2 * 2, statsH, maxIconW, 0) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorTopStats(isActive, this.getIsHovered());
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations(InGame_SendInsult.iCivID, Game.player.iCivID, Game.getCiv(Game.player.iCivID).diplomacy.isImprovingRelations(InGame_SendInsult.iCivID), Game.getCiv(Game.player.iCivID).diplomacy.isDamagingRelations(InGame_SendInsult.iCivID));
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
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getShortNumber(Game.getCiv(InGame_SendInsult.iCivID).iRegimentsLimit), Images.regimentsLimit, statsX + CFG.PADDING + statsW, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RegimentsLimit") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(InGame_SendInsult.iCivID).iRegimentsLimit, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.regimentsLimit, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + Game.getCiv(InGame_SendInsult.iCivID).getNumOfProvinces(), Images.provinces, statsX + CFG.PADDING + statsW, buttonY + (CFG.PADDING + statsH) * 2, statsW, statsH, maxIconW, 0) {
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
        RulersManager.loadRulerIMG_DiplomacyRight(InGame_SendInsult.iCivID);
        menuElements.add(new ButtonRuler_Diplomacy(Game.player.iCivID, buttonX + CFG.PADDING, buttonY));
        menuElements.add(new ButtonRuler_Diplomacy(InGame_SendInsult.iCivID, menuWidth - paddingLeft - ButtonRuler_Diplomacy.getButtonWidth() - CFG.PADDING, buttonY) {
            @Override
            public Image getRulerImage() {
                return RulersManager.rulerIMG_DiplomacyRight;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Desc(DiplomacyManager.getInsult(), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorButtonHover2(isActive, this.getIsHovered());
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int iconWidth = (int)Math.ceil(ImageManager.getImage(Images.gold).getWidth() * 1.5f);
        menuElements.add(new TextBonus(Game.lang.get("LegacyPoints") + ": ", "" + CFG.getPrecision2(Math.abs(GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_COST_LEGACY), 10), Images.legacy, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LegacyPoints") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_COST_LEGACY, 10), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextBonus(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_SEND_INSULT_COST, 100), Images.diplomacy, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiplomacyPoints") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_SEND_INSULT_COST, 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        paddingLeft += CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("SendAnInsult"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true, Images.insult) {
            @Override
            public void actionElement() {
                InGame_SendInsult.confirm();
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Civ.getHoverInsult(Game.player.iCivID, InGame_SendInsult.iCivID);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.DIPLOMACY_CLICK;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("SendAnInsult"), true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_SendInsult.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, Math.min((int)(CFG.GAME_HEIGHT * 0.2f), CFG.GAME_HEIGHT / 2 - (menuHeight + titleHeight) / 2), menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_SendInsult.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_SendInsult.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_SendInsult.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    public static final void confirm() {
        if (Game.getCiv(Game.player.iCivID).fLegacy < -GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_COST_LEGACY) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Math.abs(GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_COST_LEGACY), 100), Images.legacy);
            return;
        }
        if (Game.getCiv(Game.player.iCivID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_SEND_INSULT_COST) {
            Game.menuManager.addToastInsufficient(Game.lang.get("Cost") + ", " + Game.lang.get("DiplomacyPoints") + ": ", CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_SEND_INSULT_COST, 100), Images.diplomacy);
            return;
        }
        DiplomacyManager.sendInsult(Game.player.iCivID, InGame_SendInsult.iCivID);
        InGame_Info.iCivID = Game.player.iCivID;
        InGame_Info.iCivID2 = InGame_SendInsult.iCivID;
        Game.menuManager.rebuildInGame_Info(Game.lang.get("AnInsultHasBeenSent"), Game.lang.get("Opinion") + ": " + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_DAMAGE, 1));
        InGame_Info.imgID = Images.infoDiplomacy;
        ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(InGame_SendInsult.iCivID).getCapitalProvinceID(), DiplomacyManager.COLOR_INSULT);
        Game.menuManager.setVisibleInGame_PopUp(false);
    }
    
    static {
        InGame_SendInsult.lTime = 0L;
        InGame_SendInsult.iCivID = 0;
    }
}
