// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_StaticBG_ID_FlagCiv_Religion extends Text_StaticBG
{
    public int id;
    public int religionID;
    public int religionW;
    public int religionH;
    public int maxW;
    
    public Text_StaticBG_ID_FlagCiv_Religion(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.id = id;
        this.religionID = Game.getCiv(id).getReligionID();
        final float tempScale = CFG.TEXT_HEIGHT / (float)Game.religionManager.religionImages.get(this.religionID).getHeight();
        this.religionW = (int)(Game.religionManager.religionImages.get(this.religionID).getWidth() * tempScale);
        this.religionH = (int)(Game.religionManager.religionImages.get(this.religionID).getHeight() * tempScale);
        this.maxW = (int)(Game.religionManager.maxWidth * tempScale);
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - (CFG.PADDING * 5 + this.maxW + ImageManager.getImage(Images.flagRect2).getWidth()) && this.getText().length() > 4 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.85f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        oSB.setShader(Renderer.shaderAlpha);
        if (this.id >= 0) {
            Game.getCiv(this.id).getFlag().getTexture().bind(1);
        }
        else {
            ImageManager.getImage(Images.randomCivilizationFlag).getTexture().bind(1);
        }
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.flagRect2Mask).draw(oSB, this.getPosX() + iTranslateX + CFG.PADDING * 2, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.flagRect2).draw(oSB, this.getPosX() + iTranslateX + CFG.PADDING * 2, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flagRect2).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flagRect2).getWidth(), ImageManager.getImage(Images.flagRect2).getHeight());
        Game.religionManager.religionImages.get(this.religionID).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING - this.maxW / 2 - this.religionW / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.religionH / 2 + iTranslateY, this.religionW, this.religionH);
        this.drawText(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    public void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        this.drawTextCivCapital(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    public void drawTextCiv(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 4 + ImageManager.getImage(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    public void drawTextCivCapital(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() && Game.getCiv(this.id).getCapitalProvinceID() >= 0) {
            Renderer.drawTextWithShadow(oSB, this.fontID, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName(), this.getPosX() + CFG.PADDING * 4 + ImageManager.getImage(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
        }
        else {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 4 + ImageManager.getImage(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
        }
    }
    
    @Override
    public String getTextToDraw() {
        return this.sText;
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        if (isHovered) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = this.id;
        }
    }
    
    @Override
    public void actionElementPPM() {
        if (this.getCurrent() > 0 && Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && this.getCurrent() == Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID()) {
            Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
        }
    }
}
