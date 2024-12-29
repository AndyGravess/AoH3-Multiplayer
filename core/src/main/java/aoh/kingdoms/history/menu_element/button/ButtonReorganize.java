// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;

public class ButtonReorganize extends Button
{
    public String sNumOfUnits;
    public int iNumOfUnitsWidth;
    public int iconWidth;
    public int iconHeight;
    
    public ButtonReorganize(final String sText, final String sNumOfUnits, final int nX, final int nY, final int nWidth) {
        this.iNumOfUnitsWidth = 0;
        this.init(sText, this.fontID, -1, nX, nY, nWidth, 1, true, true, false, false);
        this.sNumOfUnits = sNumOfUnits;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sNumOfUnits);
        this.iNumOfUnitsWidth = (int)Renderer.glyphLayout.width;
        final float iconScale = this.getImageScale(Game_Calendar.IMG_MANPOWER);
        this.iconWidth = (int)(ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getHeight() * iconScale);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + CFG.PADDING * 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sNumOfUnits, this.getPosX() + this.getWidth() / 2 - (this.iNumOfUnitsWidth + CFG.PADDING + this.iconWidth) / 2 + CFG.PADDING + this.iconWidth + iTranslateX, this.getPosY() + CFG.TEXT_HEIGHT + CFG.TEXT_HEIGHT / 2 - CFG.TEXT_HEIGHT_SMALL / 2 + CFG.PADDING * 4 + iTranslateY, Colors.HOVER_RIGHT);
        ImageManager.getImage(Game_Calendar.IMG_MANPOWER).draw(oSB, this.getPosX() + this.getWidth() / 2 - (this.iNumOfUnitsWidth + CFG.PADDING + this.iconWidth) / 2 + iTranslateX, this.getPosY() + CFG.TEXT_HEIGHT + CFG.TEXT_HEIGHT / 2 - this.iconHeight / 2 + CFG.PADDING * 4 + iTranslateY, this.iconWidth, this.iconHeight);
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 6;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats(isActive, this.getIsHovered());
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT_SMALL / (float)ImageManager.getImage(iImageID).getHeight();
    }
}
