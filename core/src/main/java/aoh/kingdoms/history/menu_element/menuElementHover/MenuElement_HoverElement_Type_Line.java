// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class MenuElement_HoverElement_Type_Line implements MenuElement_HoverElement_Type
{
    private int iWidth;
    private int iHeight;
    
    public MenuElement_HoverElement_Type_Line() {
        this.iWidth = CFG.PADDING * 3;
        this.iHeight = CFG.PADDING * 2;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(Colors.HOVER_LINE1.r, Colors.HOVER_LINE1.g, Colors.HOVER_LINE1.b, Colors.HOVER_LINE1.a * nAlpha));
        Images.pix.draw(oSB, nPosX + CFG.PADDING * 2, nPosY + CFG.PADDING + CFG.PADDING / 2 + 1, iMaxWidth - CFG.PADDING * 4, 1);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY + CFG.PADDING + CFG.PADDING / 2 + 1, CFG.PADDING * 2, 1, true, false);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + iMaxWidth - CFG.PADDING * 2, nPosY + CFG.PADDING + CFG.PADDING / 2 + 1, CFG.PADDING * 2, 1);
        oSB.setColor(new Color(Colors.HOVER_LINE2.r, Colors.HOVER_LINE2.g, Colors.HOVER_LINE2.b, Colors.HOVER_LINE2.a * nAlpha));
        Images.pix.draw(oSB, nPosX + CFG.PADDING * 2, nPosY + CFG.PADDING + CFG.PADDING / 2 + 2, iMaxWidth - CFG.PADDING * 4, 1);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY + CFG.PADDING + CFG.PADDING / 2 + 2, CFG.PADDING * 2, 1, true, false);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + iMaxWidth - CFG.PADDING * 2, nPosY + CFG.PADDING + CFG.PADDING / 2 + 2, CFG.PADDING * 2, 1);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public int getWidth() {
        return this.iWidth;
    }
    
    @Override
    public int getHeight() {
        return this.iHeight;
    }
}
