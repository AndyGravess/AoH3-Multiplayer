// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;

public class ButtonArmyGeneral2 extends ButtonArmyGeneral
{
    public ButtonArmyGeneral2(final String sName, final int iCivID, final int iAttack, final int iDefense, final int iPosX, final int iPosY, final int imageID, final int iDay, final int iMonth, final int iYear, final String sIMG, final int combatExperience) {
        super(sName, iCivID, iAttack, iDefense, iPosX, iPosY, imageID, iDay, iMonth, iYear, sIMG, combatExperience);
        this.setWidth(ImageManager.getImage(Images.generalFrameBattle).getWidth());
        this.setHeight(ImageManager.getImage(Images.generalFrameBattle).getHeight());
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrameBattle).getWidth(), ImageManager.getImage(Images.generalFrameBattle).getHeight());
        }
        if (this.generalImage != null) {
            this.generalImage.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrameBattle).getWidth(), ImageManager.getImage(Images.generalFrameBattle).getHeight());
        }
        ImageManager.getImage(Images.generalFrameBattle).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + iTranslateY, this.getWidth(), getStatsHeight());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + iTranslateY, this.getWidth(), getStatsHeight(), false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + iTranslateY, 1, getStatsHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + iTranslateY, 1, getStatsHeight());
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getText(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + CFG.PADDING + iTranslateY, this.getColor(isActive));
        ImageManager.getImage(Images.attack).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() - CFG.PADDING - ButtonArmyGeneral2.attackIconHeight + iTranslateY, ButtonArmyGeneral2.attackIconWidth, ButtonArmyGeneral2.attackIconHeight);
        ImageManager.getImage(Images.defense).draw(oSB, this.getPosX() + this.getWidth() - ButtonArmyGeneral2.defenseIconWidth - CFG.PADDING + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() - CFG.PADDING - ButtonArmyGeneral2.defenseIconHeight + iTranslateY, ButtonArmyGeneral2.defenseIconWidth, ButtonArmyGeneral2.defenseIconHeight);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sAttack, this.getPosX() + ButtonArmyGeneral2.attackIconWidth + CFG.PADDING + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT_SMALL + iTranslateY, Colors.BUTTON_TEXT_HOVERED);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sDefense, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - ButtonArmyGeneral2.defenseIconWidth - this.iDefenseWidth + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT_SMALL + iTranslateY, Colors.BUTTON_TEXT_HOVERED);
    }
    
    public static int getStatsHeight() {
        return CFG.TEXT_HEIGHT_SMALL + CFG.PADDING * 2;
    }
}
