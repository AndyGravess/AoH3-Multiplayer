// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter2;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.Button_CapitalMove;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_MoveCapital_PopUp extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int toProvinceID;
    
    public InGame_MoveCapital_PopUp() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING;
        int buttonX = Images.boxTitleBORDERWIDTH;
        int statsY = buttonY;
        menuElements.add(new Button_CapitalMove(paddingLeft, buttonY));
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING * 3;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        final int statW = menuWidth - buttonX - paddingLeft;
        final int statH = (Button_CapitalMove.getButtonHeight() - CFG.PADDING * 2) / 3;
        menuElements.add(new Text_StaticBG_RulerTitle(Game.lang.get("MoveCapital"), buttonX, statsY, statW, statH) {
            @Override
            public void actionElement() {
                if (InGame_MoveCapital_PopUp.toProvinceID >= 0) {
                    Game.mapCoords.centerToProvinceID(InGame_MoveCapital_PopUp.toProvinceID);
                    Game.setActiveProvinceID(InGame_MoveCapital_PopUp.toProvinceID);
                    ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MoveCapitalTo") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(InGame_MoveCapital_PopUp.toProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.getProvince(Math.max(0, InGame_MoveCapital_PopUp.toProvinceID)).getProvinceName(), "", Images.capital, buttonX, statsY, statW, statH, maxIconW) {
            @Override
            public void actionElement() {
                if (InGame_MoveCapital_PopUp.toProvinceID >= 0) {
                    Game.mapCoords.centerToProvinceID(InGame_MoveCapital_PopUp.toProvinceID);
                    Game.setActiveProvinceID(InGame_MoveCapital_PopUp.toProvinceID);
                    ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MoveCapitalTo") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(InGame_MoveCapital_PopUp.toProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.capital.MOVE_CAPITAL_COST, 100), Images.gold, buttonX, statsY, statW, statH, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_RIGHT;
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("Confirm"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                InGame_MoveCapital_PopUp.confirm();
            }
            
            @Override
            public boolean getClickable() {
                return Game.getCiv(Game.player.iCivID).fGold >= GameValues.capital.MOVE_CAPITAL_COST;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MoveCapitalTo") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.getProvince(InGame_MoveCapital_PopUp.toProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.capital.MOVE_CAPITAL_COST, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("AllUniqueBuildingsInTheCapitalWillBeDestroyed"), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter2(Game.lang.get("Capital"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_MoveCapital_PopUp.lTime;
            }
            
            @Override
            public int getFlagCivID() {
                return Game.player.iCivID;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, CFG.GAME_HEIGHT / 4, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_MoveCapital_PopUp.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_MoveCapital_PopUp.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        oSB.setColor(Colors.COLOR_GRADIENT);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_MoveCapital_PopUp.lTime = CFG.currentTimeMillis;
    }
    
    public static final void confirm() {
        if (Game.getCiv(Game.player.iCivID).moveCapital(InGame_MoveCapital_PopUp.toProvinceID)) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
            if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iActiveID == InGame_CourtOptions.iGovernmentID) {
                Game.menuManager.rebuildInGame_Government_SavePos();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = 0;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("CapitalMoved"), Game.getProvince(Game.getCiv(Game.player.iCivID).getCapitalProvinceID()).getProvinceName());
            InGame_Info.imgID = Images.infoCrown;
        }
        Game.menuManager.setVisibleInGame_PopUp(false);
    }
    
    static {
        InGame_MoveCapital_PopUp.lTime = 0L;
        InGame_MoveCapital_PopUp.toProvinceID = 0;
    }
}
