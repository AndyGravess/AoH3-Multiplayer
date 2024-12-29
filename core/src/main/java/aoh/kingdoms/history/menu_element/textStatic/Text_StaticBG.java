// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_StaticBG extends Text_Static
{
    public Text_StaticBG(final String sText, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.typeOfElement = MenuElement_Type.TEXT;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        if (super.getWidth() < this.iTextWidth) {
            this.setWidth(this.iTextWidth);
        }
        this.updateTextPosition();
    }
    
    public Text_StaticBG(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = fontID;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
        int tWMax = 0;
        while (this.iTextWidth >= this.getWidth() - ((iTextPositionX > 0) ? iTextPositionX : 0) && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        try {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
            this.iTextWidth = (int)Renderer.glyphLayout.width;
            this.iTextHeight = (int)Renderer.glyphLayout.height;
            if (this.getHeight() < this.iTextHeight) {
                this.setHeight(this.iTextHeight);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
