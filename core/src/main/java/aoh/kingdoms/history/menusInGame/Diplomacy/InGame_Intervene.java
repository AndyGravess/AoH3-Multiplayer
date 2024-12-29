// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.map.war.WarCivilization;
import aoh.kingdoms.history.map.war.WarManager;
import aoh.kingdoms.history.map.war.War;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.mainGame.SoundsManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonGame_ImageSparks;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Diplomacy;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_ID;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Intervene extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iCivID;
    public static int iCivID2;
    public static String warKey;
    
    public InGame_Intervene(final int nCivID, final int nCivID2) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title600).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        final int buttonX = paddingLeft;
        InGame_Intervene.iCivID = nCivID;
        InGame_Intervene.iCivID2 = nCivID2;
        final int maxWidth = ImageManager.getImage(Images.interveneBig).getWidth() + CFG.PADDING * 4;
        final int tempTitlePaddingY = CFG.PADDING;
        final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new ButtonFlag_Diplomacy(InGame_Intervene.iCivID, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
        menuElements.add(new ButtonFlag_Diplomacy(InGame_Intervene.iCivID2, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
        menuElements.add(new Text_Static_ID(InGame_Intervene.iCivID2, Game.getCiv(InGame_Intervene.iCivID2).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorNegative(isActive, this.getIsHovered());
            }
        });
        menuElements.add(new Text_Static_ID(InGame_Intervene.iCivID, Game.getCiv(InGame_Intervene.iCivID).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH) {});
        menuElements.add(new TextIcon_Diplomacy(Images.interveneBig, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
            @Override
            public Color getColorBar() {
                return DiplomacyManager.COLOR_WAR;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int buttonW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, buttonW, buttonH, Game.player.iCivID) {
            @Override
            public void actionElement() {
                if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                    Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                    Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                    ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                }
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
            }
        });
        menuElements.add(new Text_StaticBG("---", CFG.FONT_REGULAR, -1, paddingLeft + buttonW + CFG.PADDING, buttonY, buttonW, buttonH));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        paddingLeft += CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("JoinAWar"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true, Images.war) {
            @Override
            public void actionElement() {
                InGame_Intervene.confirm();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("JoinAWar"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("JoinAWarAgainst") + ": ", Game.getCiv(InGame_Intervene.iCivID2).getCivName(), InGame_Intervene.iCivID2, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", "+" + CFG.getPrecision2(GameValues.diplomacy.INTERVENE_IN_WAR_IMPROVE_RELATIONS_VALUE, 10), Images.relationsUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.DIPLOMACY_CLICK;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("InterveneInWar"), true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_Intervene.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, Math.min((int)(CFG.GAME_HEIGHT * 0.2f), CFG.GAME_HEIGHT / 2 - (menuHeight + titleHeight) / 2), menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_Intervene.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_Intervene.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Intervene.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    public static final void confirm() {
        try {
            if (WarManager.lWars.get(InGame_Intervene.warKey).isAggressor(InGame_Intervene.iCivID)) {
                WarManager.lWars.get(InGame_Intervene.warKey).addAggressor(Game.player.iCivID);
                rebuildWar();
                DiplomacyManager.addMilitaryAccess(Game.player.iCivID, WarManager.lWars.get(InGame_Intervene.warKey).lAggressors.get(0).iCivID);
            }
            else if (WarManager.lWars.get(InGame_Intervene.warKey).isDefender(InGame_Intervene.iCivID)) {
                WarManager.lWars.get(InGame_Intervene.warKey).addDefender(Game.player.iCivID);
                rebuildWar();
                DiplomacyManager.addMilitaryAccess(Game.player.iCivID, WarManager.lWars.get(InGame_Intervene.warKey).lDefenders.get(0).iCivID);
            }
            Game.getCiv(InGame_Intervene.iCivID).diplomacy.setRelation(InGame_Intervene.iCivID, Game.player.iCivID, GameValues.diplomacy.INTERVENE_IN_WAR_IMPROVE_RELATIONS_VALUE);
            Game.getCiv(Game.player.iCivID).diplomacy.setRelation(Game.player.iCivID, InGame_Intervene.iCivID, GameValues.diplomacy.INTERVENE_IN_WAR_IMPROVE_RELATIONS_VALUE);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game.menuManager.setVisibleInGame_PopUp(false);
    }
    
    public static void rebuildWar() {
        InGame_Info.iCivID = Game.player.iCivID;
        InGame_Info.iCivID2 = InGame_Intervene.iCivID2;
        Game.menuManager.rebuildInGame_Info(Game.lang.get("InterveneInWar"), Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(InGame_Intervene.iCivID2).getCivName());
        InGame_Info.imgID = Images.infoWar;
        if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
            Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                @Override
                public void update() {
                    ProvinceBorderManager.updateProvinceBorder();
                }
            });
        }
        Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
            @Override
            public void update() {
                Game.menuManager.rebuildInGame_Wars();
            }
        });
    }
    
    static {
        InGame_Intervene.lTime = 0L;
        InGame_Intervene.iCivID = 0;
        InGame_Intervene.iCivID2 = 0;
        InGame_Intervene.warKey = "";
    }
}
