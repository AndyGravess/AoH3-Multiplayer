// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import com.badlogic.gdx.graphics.Color;

public class Text_InfoTop extends Text_Static
{
    public Color colorText;
    public Color colorTextHover;
    public Color colorTextActive;
    
    public Text_InfoTop(final String sText, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.colorText = new Color(0.17254902f, 0.09019608f, 0.05490196f, 1.0f);
        this.colorTextHover = new Color(0.11764706f, 0.039215688f, 0.023529412f, 1.0f);
        this.colorTextActive = new Color(0.08235294f, 0.023529412f, 0.007843138f, 1.0f);
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = CFG.FONT_BOLD;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
    }
    
    public Text_InfoTop(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.colorText = new Color(0.17254902f, 0.09019608f, 0.05490196f, 1.0f);
        this.colorTextHover = new Color(0.11764706f, 0.039215688f, 0.023529412f, 1.0f);
        this.colorTextActive = new Color(0.08235294f, 0.023529412f, 0.007843138f, 1.0f);
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
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        Renderer.drawText(oSB, this.fontID, this.sText, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return new Color(this.colorTextActive.r, this.colorTextActive.g, this.colorTextActive.b, 1.0f * InGame_Info.fAnimationPerc);
        }
        if (this.getIsHovered()) {
            return new Color(this.colorTextHover.r, this.colorTextHover.g, this.colorTextHover.b, 1.0f * InGame_Info.fAnimationPerc);
        }
        return new Color(this.colorText.r, this.colorText.g, this.colorText.b, 1.0f * InGame_Info.fAnimationPerc);
    }
}
