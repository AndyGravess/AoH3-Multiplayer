// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Player.Notification.Notification;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;

public class Button_Notification extends Button
{
    public int ANIMATION_TIME;
    public String sText2;
    public int iTextWidth2;
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    public int notificationID;
    public long lTime;
    float fAlpha;
    
    public Button_Notification(final String sText, final String sText2, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int imageID, final int notificationID, final long lTime) {
        super(sText, CFG.FONT_REGULAR_SMALL, -1, nPosX, nPosY, nWidth, nHeight, true);
        this.ANIMATION_TIME = 350;
        this.iTextWidth2 = 0;
        this.fAlpha = 1.0f;
        this.imageID = imageID;
        this.notificationID = notificationID;
        this.lTime = lTime;
        this.sText2 = sText2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText2);
        this.iTextWidth2 = (int)Renderer.glyphLayout.width;
        this.updateIcon();
        this.updateTextWidth();
    }
    
    public void updateTextWidth() {
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - this.iTextWidth2 - CFG.PADDING * 7 - this.iconWidth && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
        }
    }
    
    public void updateIcon() {
        final float iconScale = this.getImageScale(this.imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(this.imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(this.imageID).getHeight() * iconScale);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        if (this.lTime + this.ANIMATION_TIME >= CFG.currentTimeMillis) {
            this.fAlpha = Math.min(1.0f, (CFG.currentTimeMillis - this.lTime) / (float)this.ANIMATION_TIME);
            iTranslateX += (int)(CFG.BUTTON_WIDTH - CFG.BUTTON_WIDTH * this.fAlpha);
        }
        super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG.r, Colors.COLOR_NOTIFICATION_BG.g, Colors.COLOR_NOTIFICATION_BG.b, ((this.getIsHovered() || isActive) ? 0.975f : 0.925f) * this.fAlpha));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.3f * this.fAlpha));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.35f * this.fAlpha));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.3f * this.fAlpha));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f * this.fAlpha));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f * this.fAlpha));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG.r, Colors.COLOR_NOTIFICATION_BG.g, Colors.COLOR_NOTIFICATION_BG.b, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2, 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f * this.fAlpha));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.85f * this.fAlpha));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f * this.fAlpha));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER.r, Colors.COLOR_NOTIFICATION_OVER.g, Colors.COLOR_NOTIFICATION_OVER.b, 0.9f * this.fAlpha));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        this.drawIcon(oSB, iTranslateX, iTranslateY, isActive);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.iconWidth + CFG.PADDING * 4 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor2(this.getColor(isActive)));
        Renderer.drawText(oSB, this.fontID, this.sText2, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iTextWidth2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor2(this.getColor(isActive)));
    }
    
    protected void drawIcon(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.fAlpha));
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iconHeight / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButton(isActive, this.getIsHovered());
    }
    
    protected Color getColor2(final Color outColor) {
        return new Color(outColor.r, outColor.g, outColor.b, this.fAlpha);
    }
    
    public float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    public void actionElement() {
        try {
            Game.player.lNotifications.get(this.notificationID).onAction();
            Game.player.removeNotification(this.notificationID);
        }
        catch (final Exception ex) {}
    }
    
    @Override
    public void actionElementPPM() {
        Game.player.removeNotification(this.notificationID);
    }
    
    @Override
    public void buildElementHover() {
        try {
            this.menuElementHover = Game.player.lNotifications.get(this.notificationID).buildMenuElementHover();
        }
        catch (final Exception ex) {}
    }
}
