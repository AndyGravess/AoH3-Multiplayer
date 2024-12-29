// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

public class Text_StaticGoods_ProgressBar extends Text_StaticGoods
{
    public float fPerc;
    public static Color progressBarBG;
    public Color progressBar;
    
    public Text_StaticGoods_ProgressBar(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final float fPerc, final int iCurrent) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight, iCurrent);
        this.fPerc = 0.0f;
        this.progressBar = new Color(0.29411766f, 0.49019608f, 0.64705884f, 1.0f);
        this.fPerc = fPerc;
        this.iCurrent = iCurrent;
        if (fPerc < 0.25f) {
            this.progressBar = new Color(0.8039216f, 0.19607843f, 0.003921569f, 1.0f);
        }
        else if (fPerc < 0.55f) {
            this.progressBar = new Color(1.0f, 0.52156866f, 0.20784314f, 1.0f);
        }
        else if (fPerc < 0.75f) {
            this.progressBar = new Color(1.0f, 0.78431374f, 0.003921569f, 1.0f);
        }
        else if (fPerc < 0.98f) {
            this.progressBar = new Color(0.09803922f, 0.78431374f, 0.039215688f, 1.0f);
        }
        else {
            this.progressBar = new Color(0.09019608f, 0.5882353f, 0.023529412f, 1.0f);
        }
    }
    
    public Text_StaticGoods_ProgressBar(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final float fPerc, final int iCurrent, final boolean worldShare) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight, iCurrent);
        this.fPerc = 0.0f;
        this.progressBar = new Color(0.29411766f, 0.49019608f, 0.64705884f, 1.0f);
        this.fPerc = fPerc;
        this.iCurrent = iCurrent;
        if (fPerc < 0.05f) {
            this.progressBar = new Color(0.8039216f, 0.19607843f, 0.003921569f, 1.0f);
        }
        else if (fPerc < 0.125f) {
            this.progressBar = new Color(1.0f, 0.52156866f, 0.20784314f, 1.0f);
        }
        else if (fPerc < 0.2f) {
            this.progressBar = new Color(1.0f, 0.78431374f, 0.003921569f, 1.0f);
        }
        else if (fPerc < 0.3f) {
            this.progressBar = new Color(0.09803922f, 0.78431374f, 0.039215688f, 1.0f);
        }
        else {
            this.progressBar = new Color(0.09019608f, 0.5882353f, 0.023529412f, 1.0f);
        }
    }
    
    @Override
    public void drawBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        super.drawBG(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(Text_StaticGoods_ProgressBar.progressBarBG);
        ImageManager.getImage(Images.progressBar).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBar).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (this.iTextHeight + CFG.PADDING + ImageManager.getImage(Images.progressBarOver).getHeight()) / 2 + this.iTextHeight + CFG.PADDING + (ImageManager.getImage(Images.progressBarOver).getHeight() - ImageManager.getImage(Images.progressBar).getHeight()) / 2 + iTranslateY);
        oSB.setColor(this.progressBar);
        ImageManager.getImage(Images.progressBar).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBar).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (this.iTextHeight + CFG.PADDING + ImageManager.getImage(Images.progressBarOver).getHeight()) / 2 + this.iTextHeight + CFG.PADDING + (ImageManager.getImage(Images.progressBarOver).getHeight() - ImageManager.getImage(Images.progressBar).getHeight()) / 2 + iTranslateY, (int)(ImageManager.getImage(Images.progressBar).getWidth() * this.fPerc), ImageManager.getImage(Images.progressBar).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.progressBarOver).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.progressBarOver).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (this.iTextHeight + CFG.PADDING + ImageManager.getImage(Images.progressBarOver).getHeight()) / 2 + this.iTextHeight + CFG.PADDING + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (this.iTextHeight + CFG.PADDING + ImageManager.getImage(Images.progressBarOver).getHeight()) / 2 + iTranslateY, this.getIsHovered() ? this.getColor(isActive) : this.progressBar);
    }
    
    static {
        Text_StaticGoods_ProgressBar.progressBarBG = new Color(0.09803922f, 0.09803922f, 0.15686275f, 1.0f);
    }
}
