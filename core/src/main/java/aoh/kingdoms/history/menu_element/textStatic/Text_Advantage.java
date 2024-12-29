// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_Advantage extends Text_Static
{
    public Text_Advantage(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = CFG.FONT_REGULAR_SMALL;
        this.iTextPositionX = CFG.PADDING * 2;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.9f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.75f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 1.0f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, this.getWidth(), CFG.PADDING);
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.iTextWidth / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public String getTextToDraw() {
        return this.sText;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats(isActive, this.getIsHovered());
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
