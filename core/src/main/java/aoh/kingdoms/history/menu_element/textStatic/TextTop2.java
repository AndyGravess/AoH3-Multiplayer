// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.menu_element.MenuElement;

public class TextTop2 extends MenuElement
{
    public String sText;
    public int iTextWidth;
    public int iTextHeight;
    public int iTextPosX;
    public int imageID;
    
    public TextTop2(final int imageID, final String sText, final int iPosX, final int iPosY) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = CFG.FONT_REGULAR_SMALL;
        this.imageID = imageID;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeight(CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 3);
        this.setText(sText);
        this.iTextPosX = TextTop.EXTRA_WIDTH_BOX_PADDING + ImageManager.getImage(imageID).getWidth() + CFG.PADDING * 2;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + TextTop.EXTRA_WIDTH_BOX_PADDING + iTranslateX, this.getPosY() + (this.getHeight() - ImageManager.getImage(this.imageID).getHeight()) / 2 + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.iTextPosX + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    protected Color getColor(final boolean isActive) {
        return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : new Color(0.82f, 0.82f, 0.82f, 1.0f)) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }
    
    protected Color getColor2(final boolean isActive) {
        return this.getColor(isActive);
    }
    
    @Override
    public final String getText() {
        return this.sText;
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        try {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
            this.iTextWidth = (int)Renderer.glyphLayout.width;
            this.iTextHeight = (int)Renderer.glyphLayout.height;
            if (super.getWidth() < this.iTextWidth + this.extraWidth()) {
                this.setWidth(this.iTextWidth + this.extraWidth());
            }
        }
        catch (final NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final IndexOutOfBoundsException ex2) {
            CFG.exceptionStack(ex2);
        }
        catch (final IllegalArgumentException ex3) {
            CFG.exceptionStack(ex3);
        }
    }
    
    public final void setWidthOfButton() {
        this.setWidth(this.extraWidth());
    }
    
    public final int extraWidth() {
        return ImageManager.getImage(this.imageID).getWidth() + CFG.PADDING * 2 + TextTop.EXTRA_WIDTH_BOX_PADDING * 2;
    }
    
    @Override
    public int getTextWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public int getTextHeight() {
        return this.iTextHeight;
    }
}
