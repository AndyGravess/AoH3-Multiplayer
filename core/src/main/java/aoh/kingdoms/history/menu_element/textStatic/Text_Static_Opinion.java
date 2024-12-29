// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_Static_Opinion extends Text_Static
{
    public float lastValue;
    public boolean textMode;
    
    public Text_Static_Opinion(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int nCurrent, final boolean textMode) {
        this.lastValue = -945878.5f;
        this.textMode = false;
        this.typeOfElement = MenuElement_Type.TEXT;
        this.textMode = textMode;
        this.fontID = fontID;
        this.iCurrent = nCurrent;
        this.lastValue = Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID);
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.iCurrent = nCurrent;
        this.updateTextPosition();
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (this.iCurrent >= GameValues.diplomacy.DIPLOMACY_RELATION_NEUTRAL) {
            if (isActive) {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
            }
            if (isActive) {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
            }
            return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
        }
        else {
            if (this.iCurrent >= GameValues.diplomacy.DIPLOMACY_RELATION_DETACHED) {
                return super.getColor(isActive);
            }
            if (isActive) {
                return Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
            }
            if (isActive) {
                return Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
            }
            return Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
        }
    }
    
    @Override
    public String getTextToDraw() {
        try {
            if (this.lastValue != Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID)) {
                this.lastValue = Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID);
                this.iCurrent = (int)this.lastValue;
                if (this.textMode) {
                    this.setText(DiplomacyManager.getOpinion_String(this.iCurrent));
                }
                else {
                    this.setText(((this.iCurrent > 0) ? "+" : "") + CFG.getPrecision2(this.lastValue, 1));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return this.sText;
    }
}
