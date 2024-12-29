// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_StaticGoods extends Text_Static
{
    public Text_StaticGoods(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int iCurrent) {
        this.typeOfElement = MenuElement_Type.TEXT;
        this.iCurrent = iCurrent;
        this.fontID = fontID;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        this.drawBG(oSB, iTranslateX, iTranslateY, isActive);
        Renderer.drawText(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    public void drawBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() - 1 + this.getWidth() + iTranslateX, this.getPosY() + 1 + iTranslateY, 1, this.getHeight() / 2, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() - 1 + this.getWidth() + iTranslateX, this.getPosY() - 1 + this.getHeight() / 2 + iTranslateY, 1, this.getHeight() / 2);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + 1 + this.getWidth() + iTranslateX, this.getPosY() + 1 + iTranslateY, 1, this.getHeight() / 2, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + 1 + this.getWidth() + iTranslateX, this.getPosY() - 1 + this.getHeight() / 2 + iTranslateY, 1, this.getHeight() / 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE3.r, Colors.COLOR_GRADIENT_OVER_BLUE3.g, Colors.COLOR_GRADIENT_OVER_BLUE3.b, 0.9f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() + iTranslateX, this.getPosY() + 1 + iTranslateY, 1, this.getHeight() / 2, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() + iTranslateX, this.getPosY() - 1 + this.getHeight() / 2 + iTranslateY, 1, this.getHeight() / 2);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
}
