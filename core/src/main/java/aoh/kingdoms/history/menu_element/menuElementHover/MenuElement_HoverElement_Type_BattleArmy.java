// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import java.util.ArrayList;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;

public class MenuElement_HoverElement_Type_BattleArmy implements MenuElement_HoverElement_Type
{
    public String sText;
    public int iTextWidth;
    public int iUnitTypeID;
    public int iArmyID;
    public int unitImgWidth;
    public int offsetLeft;
    
    public MenuElement_HoverElement_Type_BattleArmy(final String nArmy, final int niUnitTypeID, final int nArmyID, final int offsetLeft) {
        this.unitImgWidth = 0;
        this.sText = CFG.getNumberWithSpaces(nArmy);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sText);
        this.iTextWidth = (int)Renderer.glyphLayout.width;
        this.iUnitTypeID = niUnitTypeID;
        this.iArmyID = nArmyID;
        this.offsetLeft = offsetLeft;
        this.unitImgWidth = (int)(ArmyManager.armyImages.get(ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).ImageID).getWidth() * (ImageManager.getImage(Images.unitsFrameBattle).getHeight() / (float)ArmyManager.armyImages.get(ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).ImageID).getHeight()));
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
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + ImageManager.getImage(Images.unitsFrameBattle).getHeight(), ImageManager.getImage(Images.unitsFrameBattle).getWidth(), getStatsHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + ImageManager.getImage(Images.unitsFrameBattle).getHeight(), 1, getStatsHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + ImageManager.getImage(Images.unitsFrameBattle).getWidth() - 1, nPosY + ImageManager.getImage(Images.unitsFrameBattle).getHeight(), 1, getStatsHeight());
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sText, nPosX + ImageManager.getImage(Images.unitsFrameBattle).getWidth() / 2 - this.iTextWidth / 2, nPosY + ImageManager.getImage(Images.unitsFrameBattle).getHeight() + CFG.PADDING, Colors.BUTTON_TEXT);
    }
    
    public static int getStatsHeight() {
        return CFG.TEXT_HEIGHT_SMALL + CFG.PADDING * 2;
    }
    
    @Override
    public int getWidth() {
        return ImageManager.getImage(Images.unitsFrameBattle).getWidth() + this.offsetLeft;
    }
    
    @Override
    public int getHeight() {
        return ImageManager.getImage(Images.unitsFrameBattle).getHeight() + getStatsHeight();
    }
}
