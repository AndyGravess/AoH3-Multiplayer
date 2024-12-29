// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;

public class ButtonTopFlag extends Button
{
    private int flagImgID;
    public boolean inAnimation;
    private long currentTime;
    private int ANIMATION_TIME;
    
    public ButtonTopFlag(final int iPosX, final int iPosY, final boolean isClickable) {
        this.flagImgID = 0;
        this.inAnimation = false;
        this.currentTime = 0L;
        this.ANIMATION_TIME = 750;
        this.init("", this.fontID, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.flagBG).getWidth(), ImageManager.getImage(Images.flagBG).getHeight(), isClickable, true, false, false);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.updateLanguage();
    }
    
    protected void drawBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(Images.flagBG).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        this.drawBG(oSB, iTranslateX, iTranslateY, isActive);
        if (this.inAnimation) {
            if (this.currentTime == 0L) {
                this.currentTime = CFG.currentTimeMillis;
            }
            Renderer.clipView_Start(oSB, iTranslateX, CFG.GAME_HEIGHT - (this.getPosY() + GameValues.inGame.INGAME_FLAG_PADDING_Y[CFG.UIScale] + iTranslateY), CFG.GAME_WIDTH, -(int)(ImageManager.getImage(Images.flagOver.get(this.flagImgID)).getHeight() * ((CFG.currentTimeMillis - this.currentTime) / (float)this.ANIMATION_TIME)));
            this.drawFlag(oSB, iTranslateX, iTranslateY, isActive);
            Renderer.clipView_End(oSB);
            if (CFG.currentTimeMillis - this.currentTime > this.ANIMATION_TIME) {
                this.inAnimation = false;
                this.currentTime = 0L;
            }
        }
        else {
            this.drawFlag(oSB, iTranslateX, iTranslateY, isActive);
        }
    }
    
    public int getFlagCivID() {
        return Game.player.iCivID;
    }
    
    protected void drawFlag(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_BELOW) {
            ImageManager.getImage(Images.flagBelow).draw(oSB, this.getPosX() + GameValues.inGame.INGAME_FLAG_PADDING_X[CFG.UIScale] + (ImageManager.getImage(Images.flagOver.get(this.flagImgID)).getWidth() - ImageManager.getImage(Images.flagBelow).getWidth()) / 2 + iTranslateX, this.getPosY() + GameValues.inGame.INGAME_FLAG_PADDING_Y[CFG.UIScale] + iTranslateY);
        }
        if (Game.getCiv(this.getFlagCivID()).isFlagNearest) {
            oSB.setShader(Renderer.shaderAlpha);
            Game.getCiv(this.getFlagCivID()).getFlag().getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.flagMask.get(this.flagImgID)).draw(oSB, this.getPosX() + GameValues.inGame.INGAME_FLAG_PADDING_X[CFG.UIScale] + iTranslateX, this.getPosY() + GameValues.inGame.INGAME_FLAG_PADDING_Y[CFG.UIScale] + iTranslateY, ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getWidth(), ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getHeight());
        }
        else {
            Renderer.setShaderWater3(oSB);
            Renderer.shaderWater3.setUniformf("u_maskScale", 1.0f);
            Renderer.shaderWater3.setUniformf("u_maskScaleY", 1.0f);
            ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            Game.getCiv(this.getFlagCivID()).getFlag().draw(oSB, this.getPosX() + GameValues.inGame.INGAME_FLAG_PADDING_X[CFG.UIScale] + iTranslateX, this.getPosY() + GameValues.inGame.INGAME_FLAG_PADDING_Y[CFG.UIScale] + iTranslateY, ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getWidth(), ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getHeight());
        }
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        if (GameValues.inGame.ENABLE_VASSAL_LORD_FLAG && Game.getCiv(this.getFlagCivID()).getPuppetOfCivID() != this.getFlagCivID()) {
            if (Game.getCiv(Game.getCiv(this.getFlagCivID()).getPuppetOfCivID()).isFlagNearest) {
                oSB.setShader(Renderer.shaderAlpha);
                Game.getCiv(Game.getCiv(this.getFlagCivID()).getPuppetOfCivID()).getFlag().getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).draw(oSB, this.getPosX() + GameValues.inGame.INGAME_FLAG_PADDING_X[CFG.UIScale] + iTranslateX, this.getPosY() + GameValues.inGame.INGAME_FLAG_PADDING_Y[CFG.UIScale] + iTranslateY, ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getWidth(), ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getHeight());
            }
            else {
                Renderer.setShaderWater3(oSB);
                Renderer.shaderWater3.setUniformf("u_maskScale", 1.0f);
                Renderer.shaderWater3.setUniformf("u_maskScaleY", 1.0f);
                ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                Game.getCiv(Game.getCiv(this.getFlagCivID()).getPuppetOfCivID()).getFlag().draw(oSB, this.getPosX() + GameValues.inGame.INGAME_FLAG_PADDING_X[CFG.UIScale] + iTranslateX, this.getPosY() + GameValues.inGame.INGAME_FLAG_PADDING_Y[CFG.UIScale] + iTranslateY, ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getWidth(), ImageManager.getImage(Images.flagMaskLord.get(this.flagImgID)).getHeight());
            }
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
        }
        ImageManager.getImage(Images.flagOver.get(this.flagImgID)).draw(oSB, this.getPosX() + GameValues.inGame.INGAME_FLAG_PADDING_X[CFG.UIScale] + (ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getWidth() - ImageManager.getImage(Images.flagOver.get(this.flagImgID)).getWidth()) / 2 + iTranslateX, this.getPosY() + GameValues.inGame.INGAME_FLAG_PADDING_Y[CFG.UIScale] + iTranslateY);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.3f));
            ImageManager.getImage(Images.flagOver.get(this.flagImgID)).draw(oSB, this.getPosX() + GameValues.inGame.INGAME_FLAG_PADDING_X[CFG.UIScale] + (ImageManager.getImage(Images.flagMask.get(this.flagImgID)).getWidth() - ImageManager.getImage(Images.flagOver.get(this.flagImgID)).getWidth()) / 2 + iTranslateX, this.getPosY() + GameValues.inGame.INGAME_FLAG_PADDING_Y[CFG.UIScale] + iTranslateY);
        }
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public void updateLanguage() {
        this.flagImgID = Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[0] + ((Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[1] - Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[0] > 0) ? ((ButtonFlag.EXTRA_RANDOM + this.getFlagCivID()) % (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[1] - Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FLAG_GROUP[0])) : 0);
        this.inAnimation = true;
        this.currentTime = 0L;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        if (isHovered) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = Game.player.iCivID;
        }
    }
    
    @Override
    public void actionElementPPM() {
        if (Game.getCiv(this.getFlagCivID()).getCapitalProvinceID() > 0 && this.getFlagCivID() == Game.getProvince(Game.getCiv(this.getFlagCivID()).getCapitalProvinceID()).getCivID()) {
            Game.mapCoords.centerToProvinceID(Game.getCiv(this.getFlagCivID()).getCapitalProvinceID());
        }
    }
}
