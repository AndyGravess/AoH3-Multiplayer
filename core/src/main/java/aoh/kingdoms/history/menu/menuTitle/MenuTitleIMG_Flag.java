// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;

public class MenuTitleIMG_Flag extends MenuTitleIMG
{
    public String sText2;
    public int flagWidth;
    public int flagHeight;
    
    public MenuTitleIMG_Flag(final String sText, final String sText2, final boolean moveable, final boolean resizable, final int imageID) {
        super(sText2, moveable, resizable, imageID);
        this.fontID = CFG.FONT_BOLD;
        this.sText2 = sText;
        this.flagHeight = this.getHeight() - Images.boxTitleBORDERWIDTH;
        final float tScale = this.flagHeight / (float)CFG.CIV_FLAG_HEIGHT;
        this.flagWidth = (int)(CFG.CIV_FLAG_WIDTH * tScale);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        super.draw(oSB, nPosX, nPosY, nWidth, titleStatus);
        try {
            oSB.setShader(Renderer.shaderAlpha);
            if (this.getFlagCivID() < 0) {
                ImageManager.getImage(Images.rebelsFlag).getTexture().bind(1);
            }
            else {
                Game.getCiv(this.getFlagCivID()).getFlag().getTexture().bind(1);
            }
            Gdx.gl.glActiveTexture(33984);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY - this.getHeight() + Images.boxTitleBORDERWIDTH, this.flagWidth, this.flagHeight);
            oSB.setColor(Color.WHITE);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            oSB.setShader(Renderer.shaderAlpha);
            ImageManager.getImage(Images.titleFlagOver).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY - this.getHeight() + Images.boxTitleBORDERWIDTH, this.flagWidth, this.flagHeight);
            oSB.setColor(Color.WHITE);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
        }
        catch (final Exception ex) {}
    }
    
    @Override
    public void drawText(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        Renderer.drawText(oSB, this.fontID, this.getText2(), nPosX + this.flagWidth + CFG.PADDING, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 - this.getTextHeight()), this.getColorText(titleStatus));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.getText(), nPosX + this.flagWidth + CFG.PADDING, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING), MenuTitle.colorHovered);
    }
    
    public int getFlagCivID() {
        return 0;
    }
    
    public String getText2() {
        return this.sText2;
    }
}
