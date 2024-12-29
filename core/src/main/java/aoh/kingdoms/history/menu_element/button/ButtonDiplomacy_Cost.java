// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;

public class ButtonDiplomacy_Cost extends ButtonDiplomacy
{
    public String sValue;
    public int iTextBonusW;
    public int iTextBonusH;
    
    public ButtonDiplomacy_Cost(final String sText, final int imageID, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final String sValue) {
        super(sText, imageID, iPosX, iPosY, iWidth, iHeight);
        this.sValue = sValue;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sValue);
        this.iTextBonusW = (int)glyphLayout.width;
        this.iTextBonusH = (int)glyphLayout.height;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getClickable()) {
            ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.imageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.iconY + iTranslateY);
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.33f));
            ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.imageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.iconY + iTranslateY);
            oSB.setColor(Color.WHITE);
        }
        Renderer.drawTextWithShadowRotated(oSB, CFG.FONT_REGULAR_SMALL, this.sValue, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iTextBonusH + iTranslateX, this.getPosY() + this.iconH - CFG.PADDING + iTranslateY, Colors.HOVER_RIGHT3, 90.0f);
        for (int i = 0; i < this.iLineSize; ++i) {
            Renderer.drawText(oSB, this.fontID, this.sLines.get(i), this.getPosX() + this.getWidth() / 2 - this.iLinesWidth.get(i) / 2 + iTranslateX, this.getPosY() + this.iY_Text + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
        }
    }
    
    public final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
}
