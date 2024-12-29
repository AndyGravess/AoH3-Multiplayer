// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonCurrentSituation_Value_Mods extends ButtonCurrentSituation_Value
{
    public ButtonCurrentSituation_Value_Mods(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final boolean row, final int id, final boolean checkbox) {
        super(sText, imageID, iPosX, iPosY, nWidth, nHeight, maxIconWidth, row, id);
        this.setCheckboxState(checkbox);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        if (this.getCheckboxState()) {
            oSB.setColor(new Color(Colors.HOVER_POSITIVE.r, Colors.HOVER_POSITIVE.g, Colors.HOVER_POSITIVE.b, this.getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxIconWidth + CFG.PADDING * 2, this.getHeight(), 1.0f);
            oSB.setColor(new Color(Colors.HOVER_POSITIVE.r, Colors.HOVER_POSITIVE.g, Colors.HOVER_POSITIVE.b, 0.75f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxIconWidth + CFG.PADDING * 2, this.getHeight());
        }
        else {
            oSB.setColor(new Color(Colors.HOVER_NEGATIVE.r, Colors.HOVER_NEGATIVE.g, Colors.HOVER_NEGATIVE.b, this.getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxIconWidth + CFG.PADDING * 2, this.getHeight(), 1.0f);
            oSB.setColor(new Color(Colors.HOVER_NEGATIVE.r, Colors.HOVER_NEGATIVE.g, Colors.HOVER_NEGATIVE.b, 0.75f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxIconWidth + CFG.PADDING * 2, this.getHeight());
        }
        oSB.setColor(Color.WHITE);
        if (this.row) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
            Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - Images.line_32_off1.getHeight() + iTranslateY, this.getWidth(), 1);
            Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - Images.line_32_off1.getHeight() + iTranslateY, this.getWidth(), 1, false, true);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_TITLE_BLUE.r, Colors.COLOR_GRADIENT_TITLE_BLUE.g, Colors.COLOR_GRADIENT_TITLE_BLUE.b, 0.35f));
            Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - Images.line_32_off1.getHeight() + iTranslateY, this.getWidth(), 1);
            Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - Images.line_32_off1.getHeight() + iTranslateY, this.getWidth(), 1, false, true);
        }
        else {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        }
        if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.325f));
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2, this.getHeight());
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + this.maxIconWidth + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2, this.getHeight(), true, false);
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + this.maxIconWidth + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_BOX_GOLD.r, Colors.COLOR_BOX_GOLD.g, Colors.COLOR_BOX_GOLD.b, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() / 2, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, 1, this.getHeight() / 2);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.maxIconWidth + CFG.PADDING * 2 - 2 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() / 2, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.maxIconWidth + CFG.PADDING * 2 - 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, 1, this.getHeight() / 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() / 2, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, 1, this.getHeight() / 2);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.maxIconWidth + CFG.PADDING * 2 - 3 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() / 2, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.maxIconWidth + CFG.PADDING * 2 - 3 + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, 1, this.getHeight() / 2);
        oSB.setColor(Color.WHITE);
    }
}
