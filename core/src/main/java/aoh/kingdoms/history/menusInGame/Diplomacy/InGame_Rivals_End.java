// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.button.ButtonGame_ImageSparks;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonFlag;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Rivals_End extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int endRivalryWithCivID;
    
    public InGame_Rivals_End() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        final int buttonX = paddingLeft;
        final int rightW = menuWidth - paddingLeft * 2 - ButtonFlag.getButtonWidth() - CFG.PADDING * 2;
        final int rightH = (ButtonFlag.getButtonHeight() - CFG.PADDING) / 2;
        menuElements.add(new ButtonFlag(InGame_Rivals_End.endRivalryWithCivID, buttonX, buttonY, true) {
            @Override
            public int getFlagCivID() {
                return this.iCivID;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCivID, Game.lang.get("Provinces") + ": " + Game.getCiv(this.iCivID).getNumOfProvinces()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Population") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.getCiv(this.iCivID).getPopulationTotal()), CFG.FONT_BOLD_SMALL, Colors.COLOR_POPULATION));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new Text_StaticBG_RulerTitle(Game.getCiv(InGame_Rivals_End.endRivalryWithCivID).getCivName(), buttonX + ButtonFlag.getButtonWidth() + CFG.PADDING * 2, buttonY, rightW, rightH) {
            @Override
            public void actionElement() {
                Game.mapCoords.centerToProvinceID(Game.getCiv(InGame_Rivals_End.endRivalryWithCivID).getCapitalProvinceID());
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Rivals_End.endRivalryWithCivID, Game.lang.get("Provinces") + ": " + Game.getCiv(InGame_Rivals_End.endRivalryWithCivID).getNumOfProvinces()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Capital") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.getCiv(InGame_Rivals_End.endRivalryWithCivID).getCapitalProvinceID()).getProvinceName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Legacy") + ": ", "" + CFG.getPrecision2((float)GameValues.rivals.END_RIVALRY_COST_LEGACY, 100), Images.legacy, buttonX + ButtonFlag.getButtonWidth() + CFG.PADDING * 2, buttonY + rightH + CFG.PADDING, rightW, rightH, ImageManager.getImage(Images.gold).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.getColorNegative(false, this.getIsHovered());
            }
        });
        buttonY += ButtonFlag.getButtonHeight() + buttonYPadding;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("EndRivalry"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true, Images.rivals) {
            @Override
            public void actionElement() {
                InGame_Rivals_End.confirm();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("EndRivalry") + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear("" + Game.getCiv(InGame_Rivals_End.endRivalryWithCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rivals, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Legacy") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)GameValues.rivals.END_RIVALRY_COST_LEGACY, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("EndRivalry"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Rivals_End.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, Math.min((int)(CFG.GAME_HEIGHT * 0.2f), CFG.GAME_HEIGHT / 2 - (menuHeight + titleHeight) / 2), menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Rivals_End.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_Rivals_End.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Rivals_End.lTime = CFG.currentTimeMillis;
    }
    
    public static void confirm() {
        if (Game.getCiv(Game.player.iCivID).fLegacy < Math.abs(GameValues.rivals.END_RIVALRY_COST_LEGACY)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)GameValues.rivals.END_RIVALRY_COST_LEGACY, 100), Images.legacy);
            return;
        }
        if (Game.getCiv(Game.player.iCivID).diplomacy.isRival(InGame_Rivals_End.endRivalryWithCivID)) {
            Game.getCiv(Game.player.iCivID).diplomacy.removeRival(InGame_Rivals_End.endRivalryWithCivID);
            final Civilization civ = Game.getCiv(Game.player.iCivID);
            civ.fLegacy += GameValues.rivals.END_RIVALRY_COST_LEGACY;
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = InGame_Rivals_End.endRivalryWithCivID;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("EndOfRivalry"), Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(InGame_Rivals_End.endRivalryWithCivID).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
            if (Game.menuManager.getVisibleInGame_Civ()) {
                Game.menuManager.rebuildInGame_Civ(true);
                InGame_Civ.lTime = 0L;
            }
        }
        else {
            Game.menuManager.addToast_Error(Game.lang.get("NotFound"), Images.rivals);
        }
        Game.menuManager.setVisibleInGame_PopUp(false);
    }
    
    static {
        InGame_Rivals_End.lTime = 0L;
    }
}
