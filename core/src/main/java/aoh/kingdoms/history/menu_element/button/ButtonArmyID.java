// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceArmy;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonArmyID extends Button
{
    public int iCivID;
    public int iProvinceID;
    
    public ButtonArmyID(final int iCivID, final int iProvinceID, final String nArmy, final int iPosX, final int iPosY, final int nID) {
        this.iCivID = 0;
        this.iProvinceID = 0;
        this.init(nArmy, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, 75, getButtonHeight(), true, true, false, false);
        this.setCurrent(nID);
        this.iCivID = iCivID;
        this.iProvinceID = iProvinceID;
        this.setWidth(ImageManager.getImage(Images.flag_rect).getWidth() + this.getTextWidth() + CFG.PADDING * 3);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, this.isArmyActive() ? 1.0f : getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.iCivID < 0) {
            ImageManager.getImage(Images.rebelsFlag).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
        }
        else {
            Game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    public static int getButtonHeight() {
        return CFG.BUTTON_HEIGHT / 2 + CFG.PADDING;
    }
    
    public boolean isArmyActive() {
        return InGame_ProvinceArmy.iActiveID == this.getCurrent();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(this.iProvinceID).getProvinceName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText() + " ", CFG.FONT_BOLD, Colors.HOVER_IMPORTANT));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Units"), CFG.FONT_BOLD));
        if (this.iCivID < 0) {
            nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.rebelsFlag, CFG.PADDING, 0));
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.iCivID, CFG.PADDING, 0));
        }
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
