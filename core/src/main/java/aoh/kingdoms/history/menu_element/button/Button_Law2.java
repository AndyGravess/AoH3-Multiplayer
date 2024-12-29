// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.LawsManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;

public class Button_Law2 extends Button
{
    public int imageID;
    public String sText2;
    public int iText2Width;
    public int iText2Height;
    
    public Button_Law2(final String sText, final String sText2, final int iPosX, final int iPosY, final int nWidth, final int imageID) {
        this.init(sText, CFG.FONT_REGULAR, 0, iPosX, iPosY, nWidth, getButtonHeight(), true, true, false, false);
        this.imageID = imageID;
        this.sText2 = sText2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sText2);
        this.iText2Width = (int)Renderer.glyphLayout.width;
        this.iText2Height = (int)Renderer.glyphLayout.height;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG.r, Colors.COLOR_NOTIFICATION_BG.g, Colors.COLOR_NOTIFICATION_BG.b, (this.getIsHovered() || isActive) ? 0.75f : 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 1.0f : 0.9f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, getButtonHeight(), getButtonHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.3f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG.r, Colors.COLOR_NOTIFICATION_BG.g, Colors.COLOR_NOTIFICATION_BG.b, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2, 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.85f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.5f : 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, (getButtonHeight() - CFG.PADDING * 3) / 2, 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(Color.WHITE);
        LawsManager.lawsImages.get(this.imageID).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.sText, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - this.iTextWidth / 2 + iTranslateX, this.getPosY() + this.getHeight() / 4 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sText2, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - this.iText2Width / 2 + iTranslateX, this.getPosY() + (getButtonHeight() - CFG.PADDING * 3) / 2 + CFG.PADDING + (this.getHeight() - (getButtonHeight() - CFG.PADDING * 3) / 2 - CFG.PADDING) / 2 - this.iText2Height / 2 + iTranslateY, Colors.getColorButtonHover2(isActive, this.getIsHovered()));
    }
    
    public static int getButtonHeight() {
        return LawsManager.lawsImages.get(0).getHeight() + getPaddingIMG() * 2;
    }
    
    public static int getPaddingIMG() {
        return CFG.PADDING + CFG.PADDING / 2;
    }
    
    public int getInnerPosX() {
        return getPaddingIMG() * 2 + LawsManager.lawsImages.get(this.imageID).getWidth();
    }
    
    public int getInnerWidth() {
        return this.getWidth() - this.getInnerPosX();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats(isActive, this.getIsHovered());
    }
}
