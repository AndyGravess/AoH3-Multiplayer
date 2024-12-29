// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menusInGame.InGame_CivilizationAdvantages;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Empty_AdvantageBG extends Empty
{
    public Empty_AdvantageBG(final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        super(iPosX, iPosY, iWidth, iHeight);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.3f));
        Images.pix.draw(oSB, this.getPosX() - InGame_CivilizationAdvantages.iMenuWidth + iTranslateX, this.getPosY() + iTranslateY, InGame_CivilizationAdvantages.iMenuWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() - InGame_CivilizationAdvantages.iMenuWidth + iTranslateX, this.getPosY() + iTranslateY, InGame_CivilizationAdvantages.iMenuWidth, this.getHeight(), false, true);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.75f));
        Images.gradientFull.draw(oSB, this.getPosX() - InGame_CivilizationAdvantages.iMenuWidth + iTranslateX, this.getPosY() + 1 + iTranslateY, InGame_CivilizationAdvantages.iMenuWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() - InGame_CivilizationAdvantages.iMenuWidth + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, InGame_CivilizationAdvantages.iMenuWidth, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() - InGame_CivilizationAdvantages.iMenuWidth + iTranslateX, this.getPosY() + iTranslateY, InGame_CivilizationAdvantages.iMenuWidth, CFG.PADDING * 2);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() - InGame_CivilizationAdvantages.iMenuWidth + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, InGame_CivilizationAdvantages.iMenuWidth, CFG.PADDING * 2, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        Images.gradientFull.draw(oSB, this.getPosX() - InGame_CivilizationAdvantages.iMenuWidth + iTranslateX, this.getPosY() + iTranslateY, InGame_CivilizationAdvantages.iMenuWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() - InGame_CivilizationAdvantages.iMenuWidth + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, InGame_CivilizationAdvantages.iMenuWidth, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() - InGame_CivilizationAdvantages.iMenuWidth + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, InGame_CivilizationAdvantages.iMenuWidth, CFG.PADDING, false, true);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public boolean playSFX_Hovered() {
        return false;
    }
}
