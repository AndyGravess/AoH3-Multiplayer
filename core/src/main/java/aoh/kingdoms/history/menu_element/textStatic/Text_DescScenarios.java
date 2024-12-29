// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_DescScenarios extends Text_Desc
{
    public Text_DescScenarios(final String sText, final int iPosX, final int iPosY, final int iWidth) {
        super(sText, iPosX, iPosY, iWidth);
        this.typeOfElement = MenuElement_Type.TRANSPARENT_BACKGROUND;
    }
    
    public Text_DescScenarios(final String sText, final int iPosX, final int iPosY, final int iWidth, final int fontID) {
        super(sText, iPosX, iPosY, iWidth, fontID);
        this.typeOfElement = MenuElement_Type.TRANSPARENT_BACKGROUND;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        for (int i = 0; i < this.iLineSize; ++i) {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sLines.get(i), this.getPosX() + this.getPadding() + iTranslateX, this.getPosY() + this.getPaddingY() + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
        }
    }
    
    @Override
    public void setText(final String sText) {
        this.updateTextPosition();
        this.sLines.clear();
        this.iLineSize = 0;
        this.iTextWidth = 0;
        final String[] words = sText.split(" ");
        int textPosX = 0;
        final int maxW = this.getWidth() - this.getPadding() * 2;
        String currentLine = "";
        for (int i = 0, iSize = words.length; i < iSize; ++i) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), words[i] + " ");
            this.iTextWidth = (int)Renderer.glyphLayout.width;
            textPosX += this.iTextWidth;
            if (textPosX < maxW) {
                currentLine = currentLine + words[i] + " ";
            }
            else {
                this.sLines.add(currentLine);
                currentLine = words[i] + " ";
                textPosX = this.iTextWidth;
            }
        }
        if (currentLine.length() > 0) {
            this.sLines.add(currentLine);
        }
        if (this.sLines.size() > 0 && this.sLines.get(0).length() > 0) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sLines.get(0));
            this.iTextHeight = (int)Renderer.glyphLayout.height;
        }
        this.iLineSize = this.sLines.size();
        this.setHeight(this.iTextHeight * this.sLines.size() + (this.sLines.size() - 1) * CFG.PADDING * 2 + this.getPaddingY() * 2);
    }
}
