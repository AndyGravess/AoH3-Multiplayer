// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;

public class MenuElement_HoverElement_Type_Space implements MenuElement_HoverElement_Type
{
    private static final String SPACE_TEXT = "-----";
    private int iTextWidth;
    
    public MenuElement_HoverElement_Type_Space() {
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), "-----");
        this.iTextWidth = (int)Renderer.glyphLayout.width;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, "-----", nPosX, nPosY + CFG.PADDING + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.9f) / 2.0f), new Color(Colors.HOVER_RIGHT2.r, Colors.HOVER_RIGHT2.g, Colors.HOVER_RIGHT2.b, nAlpha));
    }
    
    @Override
    public int getWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT;
    }
}
