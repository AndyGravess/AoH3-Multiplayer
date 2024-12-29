// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuTitleTechTree extends MenuTitle
{
    public MenuTitleTechTree(final String sText, final int iHeight, final boolean moveable, final boolean resizable) {
        super(sText, iHeight, moveable, resizable);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        ImageManager.getImage(Images.titleTechTree).draw2(oSB, nPosX, nPosY - this.getHeight(), nWidth - ImageManager.getImage(Images.titleTechTree).getWidth(), this.getHeight());
        ImageManager.getImage(Images.titleTechTree).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.titleTechTree).getWidth(), nPosY - this.getHeight(), ImageManager.getImage(Images.titleTechTree).getWidth(), this.getHeight(), true, false);
        ImageManager.getImage(Images.titleTechTreeOver).draw2(oSB, nPosX, nPosY - this.getHeight(), nWidth, this.getHeight());
        this.drawGradient(oSB, nPosX, nPosY, nWidth, titleStatus);
        oSB.setColor(new Color(0.57254905f, 0.49411765f, 0.29411766f, 1.0f));
        Images.gradientFull.draw(oSB, nPosX, nPosY - 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, 0.45f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY - this.getHeight() / 2, nWidth / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2, 1, true, false);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + nWidth / 2 + this.getTextWidth() / 2 + CFG.PADDING * 2, nPosY - this.getHeight() / 2, nWidth / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY - 1 - this.getHeight() / 2, nWidth / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2, 1, true, false);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + nWidth / 2 + this.getTextWidth() / 2 + CFG.PADDING * 2, nPosY - 1 - this.getHeight() / 2, nWidth / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2, 1);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY + 1 - this.getHeight() / 2, nWidth / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2, 1, true, false);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + nWidth / 2 + this.getTextWidth() / 2 + CFG.PADDING * 2, nPosY + 1 - this.getHeight() / 2, nWidth / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2, 1);
        oSB.setColor(Color.WHITE);
        this.drawText(oSB, nPosX, nPosY, nWidth, titleStatus);
    }
    
    public void draw2(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        ImageManager.getImage(Images.titleTechTree).draw2(oSB, nPosX, nPosY - this.getHeight(), nWidth - ImageManager.getImage(Images.titleTechTree).getWidth(), this.getHeight());
        ImageManager.getImage(Images.titleTechTree).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.titleTechTree).getWidth(), nPosY - this.getHeight(), ImageManager.getImage(Images.titleTechTree).getWidth(), this.getHeight(), true, false);
        ImageManager.getImage(Images.titleTechTreeOver).draw2(oSB, nPosX, nPosY - this.getHeight(), nWidth, this.getHeight());
        this.drawGradient(oSB, nPosX, nPosY, nWidth, titleStatus);
        oSB.setColor(Color.WHITE);
        this.drawText(oSB, nPosX, nPosY, nWidth, titleStatus);
    }
}
