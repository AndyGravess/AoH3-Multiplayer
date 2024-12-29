// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class Text_Title_BattleLine extends Text_Title_v2
{
    public int activeLine;
    
    public Text_Title_BattleLine(final String sText, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int activeLine) {
        super(sText, CFG.FONT_BOLD, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.activeLine = activeLine;
    }
    
    @Override
    public void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.325f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        int tX = ImageManager.getImage(Images.battleArmy1).getWidth() + CFG.PADDING * 2;
        for (int i = 0; i < 2; ++i) {
            oSB.setColor(new Color(Game.getCiv(Game.player.iCivID).getR(), Game.getCiv(Game.player.iCivID).getG(), Game.getCiv(Game.player.iCivID).getB(), (1 == this.activeLine) ? 1.0f : 0.225f));
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, ImageManager.getImage(Images.battleArmy0).getWidth(), ImageManager.getImage(Images.battleArmy0).getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, ImageManager.getImage(Images.battleArmy0).getWidth(), ImageManager.getImage(Images.battleArmy0).getHeight());
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (1 == this.activeLine) ? 1.0f : 0.4f));
            ImageManager.getImage(Images.battleArmy1).draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY);
            tX += ImageManager.getImage(Images.battleArmy0).getWidth() + CFG.PADDING / 2;
        }
        for (int i = 0; i < 6; ++i) {
            oSB.setColor(new Color(Game.getCiv(Game.player.iCivID).getR(), Game.getCiv(Game.player.iCivID).getG(), Game.getCiv(Game.player.iCivID).getB(), (0 == this.activeLine) ? 1.0f : 0.225f));
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, ImageManager.getImage(Images.battleArmy0).getWidth(), ImageManager.getImage(Images.battleArmy0).getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, ImageManager.getImage(Images.battleArmy0).getWidth(), ImageManager.getImage(Images.battleArmy0).getHeight());
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (0 == this.activeLine) ? 1.0f : 0.4f));
            ImageManager.getImage(Images.battleArmy0).draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY);
            tX += ImageManager.getImage(Images.battleArmy0).getWidth() + CFG.PADDING / 2;
        }
        for (int i = 0; i < 2; ++i) {
            oSB.setColor(new Color(Game.getCiv(Game.player.iCivID).getR(), Game.getCiv(Game.player.iCivID).getG(), Game.getCiv(Game.player.iCivID).getB(), (1 == this.activeLine) ? 1.0f : 0.225f));
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, ImageManager.getImage(Images.battleArmy0).getWidth(), ImageManager.getImage(Images.battleArmy0).getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, ImageManager.getImage(Images.battleArmy0).getWidth(), ImageManager.getImage(Images.battleArmy0).getHeight());
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (1 == this.activeLine) ? 1.0f : 0.4f));
            ImageManager.getImage(Images.battleArmy1).draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY);
            tX += ImageManager.getImage(Images.battleArmy0).getWidth() + CFG.PADDING / 2;
        }
        tX = ImageManager.getImage(Images.battleArmy1).getWidth() + CFG.PADDING * 2;
        for (int i = 0; i < 10; ++i) {
            oSB.setColor(new Color(Game.getCiv(Game.player.iCivID).getR(), Game.getCiv(Game.player.iCivID).getG(), Game.getCiv(Game.player.iCivID).getB(), (2 == this.activeLine) ? 1.0f : 0.225f));
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING * 2 + ImageManager.getImage(Images.battleArmy0).getHeight() + iTranslateY, ImageManager.getImage(Images.battleArmy0).getWidth(), ImageManager.getImage(Images.battleArmy0).getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, ImageManager.getImage(Images.battleArmy0).getWidth(), ImageManager.getImage(Images.battleArmy0).getHeight());
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (2 == this.activeLine) ? 1.0f : 0.4f));
            ImageManager.getImage(Images.battleArmy2).draw(oSB, this.getPosX() + this.getWidth() - tX + iTranslateX, this.getPosY() + CFG.PADDING * 2 + ImageManager.getImage(Images.battleArmy0).getHeight() + iTranslateY);
            tX += ImageManager.getImage(Images.battleArmy0).getWidth() + CFG.PADDING / 2;
        }
        oSB.setColor(Color.WHITE);
        super.drawText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats2(isActive, this.getIsHovered());
    }
}
