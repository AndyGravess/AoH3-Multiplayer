// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.GameValues;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class ButtonFlag extends Button
{
    public static int EXTRA_RANDOM;
    private int flagImgID;
    public int iCivID;
    
    public ButtonFlag(final int iPosX, final int iPosY, final boolean isClickable) {
        this.flagImgID = 0;
        this.init("", this.fontID, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), isClickable, true, false, false);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
    }
    
    public ButtonFlag(final int iCivID, final int iPosX, final int iPosY, final boolean isClickable) {
        this.flagImgID = 0;
        this.init("", this.fontID, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), isClickable, true, false, false);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.iCivID = iCivID;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        this.drawFlag(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    public int getFlagCivID() {
        if (Game.iActiveProvince >= 0) {
            return Game.getProvince(Game.iActiveProvince).getCivID();
        }
        return this.getCurrent();
    }
    
    protected void drawFlag(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_BELOW) {
            ImageManager.getImage(Images.flagBelow).draw(oSB, this.getPosX() + (ImageManager.getImage(Images.flagOver.get(this.flagImgID)).getWidth() - ImageManager.getImage(Images.flagBelow).getWidth()) / 2 + iTranslateX, this.getPosY() + iTranslateY);
        }
        if (Game.getCiv(this.getFlagCivID()).isFlagNearest) {
            oSB.setShader(Renderer.shaderAlpha);
            Game.getCiv(this.getFlagCivID()).getFlag().getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.flagMask.get(this.flagImgID)).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getWidth(), ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getHeight());
        }
        else {
            Renderer.setShaderWater3(oSB);
            Renderer.shaderWater3.setUniformf("u_maskScale", 1.0f);
            Renderer.shaderWater3.setUniformf("u_maskScaleY", 1.0f);
            ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            Game.getCiv(this.getFlagCivID()).getFlag().draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getWidth(), ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getHeight());
        }
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        if (GameValues.inGame.ENABLE_VASSAL_LORD_FLAG && Game.getCiv(this.getFlagCivID()).getPuppetOfCivID() != this.getFlagCivID()) {
            if (Game.getCiv(Game.getCiv(this.getFlagCivID()).getPuppetOfCivID()).isFlagNearest) {
                oSB.setShader(Renderer.shaderAlpha);
                Game.getCiv(Game.getCiv(this.getFlagCivID()).getPuppetOfCivID()).getFlag().getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getWidth(), ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getHeight());
            }
            else {
                Renderer.setShaderWater3(oSB);
                Renderer.shaderWater3.setUniformf("u_maskScale", 1.0f);
                Renderer.shaderWater3.setUniformf("u_maskScaleY", 1.0f);
                ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                Game.getCiv(Game.getCiv(this.getFlagCivID()).getPuppetOfCivID()).getFlag().draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getWidth(), ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getHeight());
            }
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
        }
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
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        if (isHovered) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = this.getFlagCivID();
        }
    }
    
    @Override
    public void actionElementPPM() {
        try {
            if (this.getFlagCivID() > 0 && Game.getCiv(this.getFlagCivID()).getCapitalProvinceID() >= 0 && this.getFlagCivID() == Game.getProvince(Game.getCiv(this.getFlagCivID()).getCapitalProvinceID()).getCivID()) {
                Game.mapCoords.centerToProvinceID(Game.getCiv(this.getFlagCivID()).getCapitalProvinceID());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        ButtonFlag.EXTRA_RANDOM = 0;
    }
}
