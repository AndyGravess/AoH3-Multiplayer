// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonArmyIcon extends Button
{
    public int imageID;
    public static int maxWidth;
    public boolean checkbox;
    public String armyKey;
    
    public ButtonArmyIcon(final int imageID, final int iPosX, final int iPosY) {
        this.checkbox = false;
        this.init("", CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, 20, getButtonHeight(), true, true, false, false);
        this.imageID = imageID;
        this.setWidth(getButtonWidth());
    }
    
    public ButtonArmyIcon(final int imageID, final int iPosX, final int iPosY, final boolean checkbox) {
        this.checkbox = false;
        this.init("", CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, 20, getButtonHeight(), true, true, false, false);
        this.imageID = imageID;
        this.checkbox = checkbox;
        this.setWidth(getButtonWidth());
    }
    
    public ButtonArmyIcon(final int imageID, final int iPosX, final int iPosY, final boolean checkbox, final String armyKey) {
        this.checkbox = false;
        this.init("", CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, 20, getButtonHeight(), true, true, false, false);
        this.imageID = imageID;
        this.checkbox = checkbox;
        this.armyKey = armyKey;
        this.setWidth(getButtonWidth());
    }
    
    public ButtonArmyIcon(final int imageID, final int iPosX, final int iPosY, final boolean checkbox, final String armyKey, final int buttonH) {
        this.checkbox = false;
        this.init("", CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, 20, buttonH, true, true, false, false);
        this.imageID = imageID;
        this.checkbox = checkbox;
        this.armyKey = armyKey;
        this.setWidth(getButtonWidth());
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, ButtonArmyID.getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.getImageID()).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.getImageID()).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.getImageID()).getHeight() / 2 + iTranslateY);
    }
    
    public int getImageID() {
        return this.imageID;
    }
    
    public static int getButtonHeight() {
        return CFG.BUTTON_HEIGHT / 2 + CFG.PADDING;
    }
    
    public static int getButtonWidth() {
        return ButtonArmyIcon.maxWidth + CFG.PADDING * 5;
    }
    
    @Override
    public boolean getCheckboxState() {
        return this.checkbox;
    }
    
    @Override
    public void setCheckboxState(final boolean checkboxState) {
        this.checkbox = checkboxState;
    }
    
    static {
        ButtonArmyIcon.maxWidth = 1;
    }
}
