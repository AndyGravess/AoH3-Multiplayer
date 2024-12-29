// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.button.ButtonWonderProvince;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextIcon2_Nuke extends TextIcon2
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    
    public TextIcon2_Nuke(final String sText, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        super(sText, imageID, nPosX, nPosY, nWidth, nHeight);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
        if (TextIcon2_Nuke.animationState >= 0) {
            if (TextIcon2_Nuke.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextIcon2_Nuke.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonWonderProvince.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextIcon2_Nuke.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++TextIcon2_Nuke.animationState;
                    TextIcon2_Nuke.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextIcon2_Nuke.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonWonderProvince.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextIcon2_Nuke.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    TextIcon2_Nuke.animationState = 0;
                    TextIcon2_Nuke.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    static {
        TextIcon2_Nuke.lTimeAnimation = 0L;
        TextIcon2_Nuke.animationState = 0;
    }
}
