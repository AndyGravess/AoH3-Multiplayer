// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu.MenuManager;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;

public class ButtonMainTitle extends Button
{
    private final int LOGO_PADDING = 30;
    private int logoWidth;
    private int logoHeight;
    
    public ButtonMainTitle(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, ImageManager.getImage(Images.mainTitle).getHeight(), isClickable, true, false, false);
        final float fScale = (ImageManager.getImage(Images.mainTitle).getHeight() - 60.0f) / ImageManager.getImage(Images.logo).getHeight();
        this.logoWidth = (int)(ImageManager.getImage(Images.logo).getWidth() * fScale);
        this.logoHeight = (int)(ImageManager.getImage(Images.logo).getHeight() * fScale);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawBox_EDGE_TOP_LR(oSB, Images.mainTitle, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, 0.25f));
        MenuManager.sparksAnimationHover.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.57254905f, 0.49411765f, 0.29411766f, 1.0f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (isActive) {
            oSB.setColor(ButtonMainTitle.COLOR_BUTTON_MENU_HOVER_BG);
        }
        else if (this.getIsHovered() && this.getClickable()) {
            oSB.setColor(ButtonMainTitle.COLOR_BUTTON_MENU_HOVER_BG);
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (this.getIsHovered() || isActive) ? 0.9f : 1.0f));
        ImageManager.getImage(Images.logo).draw(oSB, this.getPosX() + (this.getWidth() - this.logoWidth) / 2 + iTranslateX, this.getPosY() + 30 + iTranslateY, this.logoWidth, this.logoHeight);
        oSB.setColor(Color.WHITE);
    }
}
