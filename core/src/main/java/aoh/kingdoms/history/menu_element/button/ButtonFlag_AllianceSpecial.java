// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.map.allianceHRE.Alliance;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title_AllianceSpecial;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Game_Ages;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonFlag_AllianceSpecial extends Button
{
    private int flagImgID;
    public int iAllianceID;
    
    public ButtonFlag_AllianceSpecial(final int iAllianceID, final int iPosX, final int iPosY) {
        this.flagImgID = 0;
        this.init("", this.fontID, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
        this.iAllianceID = iAllianceID;
        this.updateLanguage();
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        this.drawFlag(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    public int getFlagCivID() {
        return this.iAllianceID;
    }
    
    protected void drawFlag(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_BELOW) {
            ImageManager.getImage(Images.flagBelow).draw(oSB, this.getPosX() + (ImageManager.getImage(Images.flagOver.get(this.flagImgID)).getWidth() - ImageManager.getImage(Images.flagBelow).getWidth()) / 2 + iTranslateX, this.getPosY() + iTranslateY);
        }
        oSB.setShader(Renderer.shaderAlpha);
        try {
            Game.alliancesSpecial_Flag.get(this.iAllianceID).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.flagMask.get(this.flagImgID)).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getWidth(), ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getHeight());
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.flagOver.get(this.flagImgID)).draw(oSB, this.getPosX() + (ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getWidth() - ImageManager.getImage(Images.flagOver.get(this.flagImgID)).getWidth()) / 2 + iTranslateX, this.getPosY() + iTranslateY);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.3f));
            ImageManager.getImage(Images.flagOver.get(this.flagImgID)).draw(oSB, this.getPosX() + (ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getWidth() - ImageManager.getImage(Images.flagOver.get(this.flagImgID)).getWidth()) / 2 + iTranslateX, this.getPosY() + iTranslateY);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.flagOver.get(0)).getWidth();
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.flagOver.get(0)).getHeight();
    }
    
    @Override
    public void updateLanguage() {
        this.flagImgID = Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[0] + ((Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[1] - Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[0] > 0) ? ((ButtonFlag.EXTRA_RANDOM + this.getFlagCivID()) % (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[1] - Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[0])) : 0);
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title_AllianceSpecial(this.iAllianceID, Game.lang.get("Civilizations") + ": " + Game.getAllianceSpecialNumOfCivs(this.iAllianceID)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        try {
            if (Game.alliancesSpecial.get(this.iAllianceID).iLeaderCivID >= 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game.alliancesSpecial.get(this.iAllianceID).Name_Leader) + ": ", CFG.FONT_BOLD, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.alliancesSpecial.get(this.iAllianceID).iLeaderCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.alliancesSpecial.get(this.iAllianceID).iLeaderCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public int getCurrent() {
        return this.iAllianceID;
    }
    
    @Override
    public void actionElementPPM() {
        try {
            if (this.iAllianceID >= 0 && Game.alliancesSpecial.get(this.iAllianceID).iLeaderCivID >= 0 && Game.getCiv(Game.alliancesSpecial.get(this.iAllianceID).iLeaderCivID).getCapitalProvinceID() >= 0 && Game.alliancesSpecial.get(this.iAllianceID).iLeaderCivID == Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(this.iAllianceID).iLeaderCivID).getCapitalProvinceID()).getCivID()) {
                Game.mapCoords.centerToProvinceID(Game.getCiv(Game.alliancesSpecial.get(this.iAllianceID).iLeaderCivID).getCapitalProvinceID());
            }
        }
        catch (final Exception ex) {}
    }
}
