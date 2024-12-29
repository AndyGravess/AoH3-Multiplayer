// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ReligionTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget;
import aoh.kingdoms.history.menu_element.button.ButtonReligion2;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_ConvertReligion extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iProvinceID;
    
    public InGame_ConvertReligion() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING * 2;
        final int buttonX = Images.boxTitleBORDERWIDTH;
        final int tIconMaxW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        menuElements.add(new ButtonReligion2(Game.getProvince(InGame_ConvertReligion.iProvinceID).getReligion(), paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 4));
        menuElements.add(new ButtonReligion2(Game.getCiv(Game.player.iCivID).getReligionID(), paddingLeft + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 4));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Cost") + ": " + Game.religionManager.getReligionConversionCost(InGame_ConvertReligion.iProvinceID), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tButtonH, tIconMaxW, false));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("ReligionConversionTime") + ": " + Game.lang.get("XDays", Game.religionManager.getReligionConversionTime(InGame_ConvertReligion.iProvinceID)), Images.time, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tButtonH, tIconMaxW, false));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("Confirm"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                InGame_ConvertReligion.confirm();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ConvertReligion"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Religion") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.religionManager.getReligion(Game.getCiv(Game.player.iCivID).getReligionID()).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ReligionTitle(Game.getCiv(Game.player.iCivID).getReligionID(), CFG.PADDING, 0));
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
        this.initMenu(new MenuTitleIMG_DoubleText(Game.lang.get("ConvertReligion"), Game.getProvince(InGame_ConvertReligion.iProvinceID).getProvinceName(), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_ConvertReligion.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, CFG.GAME_HEIGHT / 4, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_ConvertReligion.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_ConvertReligion.lTime) / 60.0f));
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
        InGame_ConvertReligion.lTime = CFG.currentTimeMillis;
    }
    
    public static final void confirm() {
        if (Game.getCiv(Game.player.iCivID).fGold < Game.religionManager.getReligionConversionCost(InGame_ConvertReligion.iProvinceID)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", "" + CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(InGame_ConvertReligion.iProvinceID), 10), Images.gold);
            return;
        }
        Game.getProvince(InGame_ConvertReligion.iProvinceID).addReligionConversion();
        Game.menuManager.setVisibleInGame_PopUp(false);
    }
    
    static {
        InGame_ConvertReligion.lTime = 0L;
        InGame_ConvertReligion.iProvinceID = 0;
    }
}
