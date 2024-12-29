// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.menu_element.MenuElement;

public class Text_Static extends MenuElement
{
    protected String sText;
    protected int iTextWidth;
    protected int iTextHeight;
    protected int iTextPositionX;
    protected TextPosition textPosition;
    
    protected Text_Static() {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
    }
    
    public Text_Static(final String sText, final int iPosX, final int iPosY) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.typeOfElement = MenuElement_Type.TEXT;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeight(CFG.TEXT_HEIGHT);
        this.setText(sText);
        this.textPosition = new TextPosition() {
            @Override
            public int getTextPosition() {
                return 0;
            }
        };
    }
    
    public Text_Static(final String sText, final int iPosX, final int iPosY, final int fontID) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = fontID;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeight(CFG.TEXT_HEIGHT);
        this.setText(sText);
        this.textPosition = new TextPosition() {
            @Override
            public int getTextPosition() {
                return 0;
            }
        };
    }
    
    public Text_Static(final String sText, final int iTextPositionX, final int iPosX, final int iPosY, final int iHeight) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.typeOfElement = MenuElement_Type.TEXT;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
    }
    
    public Text_Static(final String sText, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.typeOfElement = MenuElement_Type.TEXT;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
    }
    
    public Text_Static(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = fontID;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
    }
    
    public Text_Static(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int nCurrent) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = fontID;
        this.iCurrent = nCurrent;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
    }
    
    protected void updateTextPosition() {
        if (this.iTextPositionX < 0) {
            this.textPosition = new TextPosition() {
                @Override
                public int getTextPosition() {
                    return Text_Static.this.getWidth() / 2 - Text_Static.this.iTextWidth / 2;
                }
            };
        }
        else {
            this.textPosition = new TextPosition() {
                @Override
                public int getTextPosition() {
                    return Text_Static.this.iTextPositionX;
                }
            };
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    protected Color getColor(final boolean isActive) {
        return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : new Color(0.82f, 0.82f, 0.82f, 1.0f)) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }
    
    @Override
    public final String getText() {
        return this.sText;
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        try {
            final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
            this.iTextWidth = (int)glyphLayout.width;
            this.iTextHeight = (int)glyphLayout.height;
            if (super.getWidth() < this.iTextWidth) {
                this.setWidth(this.iTextWidth);
            }
            if (this.getHeight() < this.iTextHeight) {
                this.setHeight(this.iTextHeight);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public int getTextWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public int getTextHeight() {
        return this.iTextHeight;
    }
    
    @Override
    public int getCurrent() {
        return this.iCurrent;
    }
    
    interface TextPosition
    {
        int getTextPosition();
    }
}
