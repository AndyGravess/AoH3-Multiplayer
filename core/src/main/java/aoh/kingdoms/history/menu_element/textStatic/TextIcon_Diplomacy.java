// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon_Diplomacy extends Button
{
    public int imageID;
    public int maxWidth;
    public long lTimeAnimation;
    public int animationState;
    public final int ANIMATION_T = 1500;
    
    public TextIcon_Diplomacy(final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int maxWidth) {
        this.lTimeAnimation = 0L;
        this.animationState = 0;
        this.init(null, CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        this.maxWidth = maxWidth;
        this.lTimeAnimation = CFG.currentTimeMillis;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        int tX = this.getPosX();
        int tWidth = this.getWidth();
        if (DiplomacyManager.inAnimation) {
            tWidth = (int)(this.getWidth() * DiplomacyManager.fAnimationPerc);
            tX = tX + this.getWidth() / 2 - tWidth / 2;
        }
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
        Renderer.drawBox(oSB, Images.statsRectBG, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientFull.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight());
        if (DiplomacyManager.inAnimation) {
            oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.65f * (1.0f - DiplomacyManager.fAnimationPerc)));
            Images.gradientFull.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight());
            oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.45f + 0.6f * (1.0f - DiplomacyManager.fAnimationPerc)));
        }
        else {
            oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.45f));
        }
        Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight());
        if (this.animationState >= 0) {
            if (this.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - this.lTimeAnimation) / 1500.0f, 1.0f);
                oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.55f * (1.0f - drawPerc)));
                Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, tWidth, this.getHeight() / 2);
                if (this.lTimeAnimation < CFG.currentTimeMillis - 1500L) {
                    ++this.animationState;
                    this.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - this.lTimeAnimation) / 1500.0f, 1.0f);
                oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.55f * drawPerc));
                Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, tWidth, this.getHeight() / 2);
                if (this.lTimeAnimation < CFG.currentTimeMillis - 1500L) {
                    this.animationState = 0;
                    this.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, tWidth, CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight(), 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, tX + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, tWidth, 1);
        Images.gradientFull.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public Color getColorBar() {
        return DiplomacyManager.COLOR_RED;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.imageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateY);
    }
    
    @Override
    public boolean canBeHovered() {
        return false;
    }
}
