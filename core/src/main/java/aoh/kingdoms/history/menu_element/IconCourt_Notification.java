// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IconCourt_Notification extends IconCourt
{
    public int value;
    public String number;
    public int numW;
    public int numH;
    public int boxW;
    public int boxH;
    
    public IconCourt_Notification(final String sText, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int id, final int widthDraw) {
        super(sText, imageID, nPosX, nPosY, nWidth, nHeight, id, widthDraw);
        this.number = "";
        this.boxW = 0;
        this.boxH = 0;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        this.updateValue();
        if (this.value > 0) {
            oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, (this.getIsHovered() || isActive) ? 0.6f : 0.5f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.boxH + iTranslateY, this.boxW, this.boxH, 1.0f);
            oSB.setColor(Color.WHITE);
            Renderer.drawText(oSB, this.fontID, this.number, this.getPosX() + CFG.PADDING + this.boxW / 2 - this.numW / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.boxH / 2 - this.numH / 2 + iTranslateY, this.getColor(isActive));
        }
    }
    
    public void updateValue() {
    }
    
    public void setNumber(final int iNum) {
        this.value = iNum;
        if (this.value <= 0) {
            final int n = 0;
            this.boxH = n;
            this.boxW = n;
            return;
        }
        this.number = "" + Math.min(GameValues.court.SIDEBAR_MAX_NUMBER, this.value);
        try {
            if (this.number != null) {
                final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.number);
                this.numW = (int)glyphLayout.width;
                this.numH = (int)glyphLayout.height;
            }
            else {
                final int n2 = 0;
                this.numH = n2;
                this.numW = n2;
            }
        }
        catch (final Exception ex) {}
        this.boxW = this.numW + CFG.PADDING * 2;
        this.boxH = this.numH + CFG.PADDING * 2;
    }
}
