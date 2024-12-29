// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.menu.Colors;
import java.util.ArrayList;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_HoverElement_Type_BattleArmyPerc extends MenuElement_HoverElement_Type_BattleArmy
{
    public float fPerc;
    
    public MenuElement_HoverElement_Type_BattleArmyPerc(final String nArmy, final int niUnitTypeID, final int nArmyID, final int offsetLeft, final float fPerc) {
        super(nArmy, niUnitTypeID, nArmyID, offsetLeft);
        this.fPerc = Math.min(fPerc, 1.0f);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int nPosX, int nPosY, final float nAlpha, final int iMaxWidth) {
        nPosX += this.offsetLeft;
        nPosY += CFG.PADDING;
        Renderer.clipView_Start(oSB, nPosX, CFG.GAME_HEIGHT - nPosY, ImageManager.getImage(Images.unitsFrameBattle).getWidth(), -ImageManager.getImage(Images.unitsFrameBattle).getHeight());
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        ArmyManager.armyImages.get(ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).ImageID).draw(oSB, nPosX - this.unitImgWidth / 2 + ImageManager.getImage(Images.unitsFrameBattle).getWidth() / 2, nPosY, this.unitImgWidth, ImageManager.getImage(Images.unitsFrameBattle).getHeight());
        Renderer.clipView_End(oSB);
        ImageManager.getImage(Images.unitsFrameBattle).draw(oSB, nPosX, nPosY);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + ImageManager.getImage(Images.unitsFrameBattle).getHeight(), ImageManager.getImage(Images.unitsFrameBattle).getWidth(), MenuElement_HoverElement_Type_BattleArmy.getStatsHeight());
        if (this.fPerc >= 0.0f) {
            oSB.setColor(new Color(Colors.HOVER_POSITIVE.r, Colors.HOVER_POSITIVE.g, Colors.HOVER_POSITIVE.b, 0.8f));
            Images.pix.draw(oSB, nPosX + 1, nPosY - 1 + ImageManager.getImage(Images.unitsFrameBattle).getHeight(), (int)((ImageManager.getImage(Images.unitsFrameBattle).getWidth() - 2) * this.fPerc), CFG.PADDING / 2);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + 1, nPosY - 1 + ImageManager.getImage(Images.unitsFrameBattle).getHeight(), ImageManager.getImage(Images.unitsFrameBattle).getWidth() - 2, CFG.PADDING / 2);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + ImageManager.getImage(Images.unitsFrameBattle).getHeight(), 1, MenuElement_HoverElement_Type_BattleArmy.getStatsHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + ImageManager.getImage(Images.unitsFrameBattle).getWidth() - 1, nPosY + ImageManager.getImage(Images.unitsFrameBattle).getHeight(), 1, MenuElement_HoverElement_Type_BattleArmy.getStatsHeight());
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sText, nPosX + ImageManager.getImage(Images.unitsFrameBattle).getWidth() / 2 - this.iTextWidth / 2, nPosY + ImageManager.getImage(Images.unitsFrameBattle).getHeight() + CFG.PADDING, Colors.BUTTON_TEXT);
    }
}
