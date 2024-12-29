// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;

public class Button_Notification_Flag extends Button_Notification
{
    public int civID;
    
    public Button_Notification_Flag(final String sText, final String sText2, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int imageID, final int notificationID, final long lTime, final int civID) {
        super(sText, sText2, nPosX, nPosY, nWidth, nHeight, imageID, notificationID, lTime);
        this.civID = civID;
    }
    
    @Override
    public void updateTextWidth() {
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - this.iTextWidth2 - CFG.PADDING * 8 - ImageManager.getImage(Images.flagRect2).getWidth() - this.iconWidth && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        this.drawIcon(oSB, iTranslateX, iTranslateY, isActive);
        oSB.setShader(Renderer.shaderAlpha);
        if (this.civID >= 0) {
            Game.getCiv(this.civID).getFlag().getTexture().bind(1);
        }
        else {
            ImageManager.getImage(Images.randomCivilizationFlag).getTexture().bind(1);
        }
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.flagRect2Mask).draw(oSB, this.getPosX() + this.iconWidth + CFG.PADDING * 4 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.flagRect2).draw(oSB, this.getPosX() + this.iconWidth + CFG.PADDING * 4 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.iconWidth + CFG.PADDING * 6 + ImageManager.getImage(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor2(this.getColor(isActive)));
        Renderer.drawText(oSB, this.fontID, this.sText2, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iTextWidth2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor2(this.getColor(isActive)));
    }
}
