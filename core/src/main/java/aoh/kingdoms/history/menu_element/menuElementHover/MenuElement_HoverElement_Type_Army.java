// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import java.util.ArrayList;
import aoh.kingdoms.history.map.army.ArmyManager;

public class MenuElement_HoverElement_Type_Army implements MenuElement_HoverElement_Type
{
    public String sText;
    public int iTextWidth;
    public String sTextName;
    public int iTextNameWidth;
    public int iUnitTypeID;
    public int iArmyID;
    public int iAttackWidth;
    public int iDefenseWidth;
    public int attackIconWidth;
    public int attackIconHeight;
    public int defenseIconWidth;
    public int defenseIconHeight;
    public int iCivID;
    
    public MenuElement_HoverElement_Type_Army(final String nArmy, final int niUnitTypeID, final int nArmyID, final int iCivID) {
        this.iUnitTypeID = niUnitTypeID;
        this.iArmyID = nArmyID;
        this.iCivID = iCivID;
        this.sText = nArmy;
        this.sTextName = ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).Name;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sText);
        this.iTextWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), this.sTextName);
        this.iTextNameWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getAttack(iCivID));
        this.iAttackWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getDefense(iCivID));
        this.iDefenseWidth = (int)Renderer.glyphLayout.width;
        final float iconScale = this.getImageScale(Images.attack);
        this.attackIconWidth = (int)(ImageManager.getImage(Images.attack).getWidth() * iconScale);
        this.attackIconHeight = (int)(ImageManager.getImage(Images.attack).getHeight() * iconScale);
        this.defenseIconWidth = (int)(ImageManager.getImage(Images.defense).getWidth() * iconScale);
        this.defenseIconHeight = (int)(ImageManager.getImage(Images.defense).getHeight() * iconScale);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        ArmyManager.armyImages.get(ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).ImageID).draw(oSB, nPosX, nPosY, ImageManager.getImage(Images.unitsFrame).getWidth(), ImageManager.getImage(Images.unitsFrame).getHeight());
        ImageManager.getImage(Images.unitsFrame).draw(oSB, nPosX, nPosY);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight(), this.getWidth(), getStatsHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight(), 1, getStatsHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + this.getWidth() - 1, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight(), 1, getStatsHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        Images.gradientFull.draw(oSB, nPosX, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() - 2 - CFG.PADDING * 2 - CFG.TEXT_HEIGHT_SMALL, this.iAttackWidth + CFG.PADDING * 5 + this.attackIconWidth, CFG.PADDING * 2 + CFG.TEXT_HEIGHT_SMALL);
        Images.gradientFull.draw(oSB, nPosX + this.getWidth() - CFG.PADDING * 5 - this.iDefenseWidth - this.defenseIconWidth, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() - 2 - CFG.PADDING * 2 - CFG.TEXT_HEIGHT_SMALL, this.iDefenseWidth + CFG.PADDING * 5 + this.iDefenseWidth, CFG.PADDING * 2 + CFG.TEXT_HEIGHT_SMALL);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        Images.gradientXY.draw(oSB, nPosX, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() - 2 - CFG.PADDING * 2 - CFG.TEXT_HEIGHT_SMALL, this.iAttackWidth + CFG.PADDING * 5 + this.attackIconWidth, CFG.PADDING * 2 + CFG.TEXT_HEIGHT_SMALL);
        Images.gradientXY.draw(oSB, nPosX + this.getWidth() - CFG.PADDING * 5 - this.iDefenseWidth - this.defenseIconWidth, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() - 2 - CFG.PADDING * 2 - CFG.TEXT_HEIGHT_SMALL, this.iDefenseWidth + CFG.PADDING * 5 + this.iDefenseWidth, CFG.PADDING * 2 + CFG.TEXT_HEIGHT_SMALL);
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD_SMALL, this.sTextName, nPosX + this.getWidth() / 2 - this.iTextNameWidth / 2, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() + CFG.PADDING, Colors.HOVER_GOLD);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sText, nPosX + this.getWidth() / 2 - this.iTextWidth / 2, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() + CFG.PADDING * 2 + CFG.TEXT_HEIGHT_SMALL, Colors.BUTTON_TEXT);
        ImageManager.getImage(Images.attack).draw(oSB, nPosX + CFG.PADDING * 2, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() - 2 - CFG.TEXT_HEIGHT_SMALL / 2 - this.attackIconHeight / 2 - CFG.PADDING, this.attackIconWidth, this.attackIconHeight);
        ImageManager.getImage(Images.defense).draw(oSB, nPosX + this.getWidth() - CFG.PADDING * 2 - this.defenseIconWidth, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() - 2 - CFG.TEXT_HEIGHT_SMALL / 2 - this.defenseIconHeight / 2 - CFG.PADDING, this.defenseIconWidth, this.defenseIconHeight);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD_SMALL, "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getAttack(this.iCivID), nPosX + CFG.PADDING * 3 + this.attackIconWidth, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() - 2 - CFG.TEXT_HEIGHT_SMALL - CFG.PADDING, Colors.HOVER_IMPORTANT);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD_SMALL, "" + ArmyManager.lArmy.get(this.iUnitTypeID).get(this.iArmyID).getDefense(this.iCivID), nPosX + this.getWidth() - CFG.PADDING * 3 - this.iDefenseWidth - this.defenseIconWidth, nPosY + ImageManager.getImage(Images.unitsFrame).getHeight() - 2 - CFG.TEXT_HEIGHT_SMALL - CFG.PADDING, Colors.HOVER_IMPORTANT);
    }
    
    @Override
    public int getWidth() {
        return ImageManager.getImage(Images.unitsFrame).getWidth();
    }
    
    @Override
    public int getHeight() {
        return ImageManager.getImage(Images.unitsFrame).getHeight() + getStatsHeight();
    }
    
    public static int getStatsHeight() {
        return CFG.TEXT_HEIGHT_SMALL * 2 + CFG.PADDING * 3;
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
}
