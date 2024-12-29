// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;

public class Button_GoldenAge extends Button
{
    public int imageID;
    public int progressIcon;
    public int progressIcon2;
    public int titleH;
    public int maxIconWidth;
    public String sProgressLeft;
    public String sProgressRight;
    public int iProgressLeftW;
    public int iProgressH;
    public String sProgressLeft2;
    public String sProgressRight2;
    public int iProgressLeftW2;
    public String sLevel;
    public int iLevelW;
    
    public Button_GoldenAge(final String sText, final int iPosX, final int iPosY, final int nWidth, final int imageID, final int progressIcon, final int progressIcon2, final String sProgressLeft, final String sProgressRight, final String sProgressLeft2, final String sProgressRight2, final String sLevel) {
        this.progressIcon = progressIcon;
        this.progressIcon2 = progressIcon2;
        this.imageID = imageID;
        this.maxIconWidth = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4;
        this.titleH = Math.max(ImageManager.getImage(imageID).getHeight(), CFG.TEXT_HEIGHT);
        this.init(sText, CFG.FONT_REGULAR, 0, iPosX, iPosY, nWidth, this.getButtonHeight2(), true, true, false, false);
        this.sProgressLeft = sProgressLeft;
        this.sProgressRight = sProgressRight;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sProgressLeft);
        this.iProgressLeftW = (int)Renderer.glyphLayout.width;
        this.iProgressH = (int)Renderer.glyphLayout.height;
        this.sProgressLeft2 = sProgressLeft2;
        this.sProgressRight2 = sProgressRight2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sProgressLeft2);
        this.iProgressLeftW2 = (int)Renderer.glyphLayout.width;
        this.sLevel = sLevel;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sLevel);
        this.iLevelW = (int)Renderer.glyphLayout.width;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG.r, Colors.COLOR_NOTIFICATION_BG.g, Colors.COLOR_NOTIFICATION_BG.b, (this.getIsHovered() || isActive) ? 0.5f : 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
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
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getWidth() - CFG.PADDING * 2, this.titleH + CFG.PADDING * 4, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getWidth() - CFG.PADDING * 2, this.titleH + CFG.PADDING * 4);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.45f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.titleH + CFG.PADDING * 6 + iTranslateY, this.getWidth() - CFG.PADDING * 2, this.getStatH(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.titleH + CFG.PADDING * 6 + iTranslateY, this.maxIconWidth, this.getStatH(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.45f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.titleH + CFG.PADDING * 6 + this.getStatH() + CFG.PADDING + iTranslateY, this.getWidth() - CFG.PADDING * 2, this.getStatH(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.titleH + CFG.PADDING * 6 + this.getStatH() + CFG.PADDING + iTranslateY, this.maxIconWidth, this.getStatH(), 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - (this.getTextWidth() + ImageManager.getImage(this.imageID).getWidth() + CFG.PADDING * 2) / 2 + iTranslateX, this.getPosY() + this.titleH / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + CFG.PADDING * 3 + iTranslateY);
        Renderer.drawText(oSB, this.fontID, this.sText, this.getPosX() + this.getWidth() / 2 - (this.getTextWidth() + ImageManager.getImage(this.imageID).getWidth() + CFG.PADDING * 2) / 2 + ImageManager.getImage(this.imageID).getWidth() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.titleH / 2 - this.getTextHeight() / 2 + CFG.PADDING * 3 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sLevel, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iLevelW + iTranslateX, this.getPosY() + this.titleH / 2 - this.iProgressH / 2 + CFG.PADDING * 3 + iTranslateY, Colors.HOVER_RIGHT3);
        ImageManager.getImage(this.progressIcon).draw(oSB, this.getPosX() + CFG.PADDING + this.maxIconWidth / 2 - ImageManager.getImage(this.progressIcon).getWidth() / 2 + iTranslateX, this.getPosY() + this.titleH + CFG.PADDING * 6 + this.getStatH() / 2 - ImageManager.getImage(this.progressIcon).getHeight() / 2 + iTranslateY);
        ImageManager.getImage(this.progressIcon2).draw(oSB, this.getPosX() + CFG.PADDING + this.maxIconWidth / 2 - ImageManager.getImage(this.progressIcon2).getWidth() / 2 + iTranslateX, this.getPosY() + this.titleH + this.getStatH() + CFG.PADDING + CFG.PADDING * 6 + this.getStatH() / 2 - ImageManager.getImage(this.progressIcon2).getHeight() / 2 + iTranslateY);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sProgressLeft, this.getPosX() + this.maxIconWidth + CFG.PADDING * 3 + iTranslateX, this.getPosY() + this.titleH + CFG.PADDING * 6 + this.getStatH() / 2 - this.iProgressH / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sProgressRight, this.getPosX() + this.maxIconWidth + CFG.PADDING * 3 + this.iProgressLeftW + iTranslateX, this.getPosY() + this.titleH + CFG.PADDING * 6 + this.getStatH() / 2 - this.iProgressH / 2 + iTranslateY, Colors.HOVER_GOLD);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sProgressLeft2, this.getPosX() + this.maxIconWidth + CFG.PADDING * 3 + iTranslateX, this.getPosY() + this.titleH + CFG.PADDING * 6 + this.getStatH() + CFG.PADDING + this.getStatH() / 2 - this.iProgressH / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sProgressRight2, this.getPosX() + this.maxIconWidth + CFG.PADDING * 3 + this.iProgressLeftW2 + iTranslateX, this.getPosY() + this.titleH + CFG.PADDING * 6 + this.getStatH() + CFG.PADDING + this.getStatH() / 2 - this.iProgressH / 2 + iTranslateY, Colors.HOVER_GOLD);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover2(isActive, this.getIsHovered());
    }
    
    public int getButtonHeight2() {
        return this.titleH + CFG.PADDING * 8 + this.getStatH() * 2;
    }
    
    public int getStatH() {
        return this.titleH + CFG.PADDING * 4;
    }
}
