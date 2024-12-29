// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_StaticBG_ID_FlagCiv_GreenRed extends Text_StaticBG
{
    public int id;
    public boolean isActiveRedGreen;
    
    public Text_StaticBG_ID_FlagCiv_GreenRed(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.isActiveRedGreen = false;
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.id = id;
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - (CFG.PADDING * 4 + ImageManager.getImage(Images.flag_rect).getWidth()) && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        if (this.getCheckboxState()) {
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG_GREEN.r, Colors.COLOR_NOTIFICATION_BG_GREEN.g, Colors.COLOR_NOTIFICATION_BG_GREEN.b, 0.5f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG_GREEN.r, Colors.COLOR_NOTIFICATION_BG_GREEN.g, Colors.COLOR_NOTIFICATION_BG_GREEN.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_GREEN.r, Colors.COLOR_NOTIFICATION_OVER_GREEN.g, Colors.COLOR_NOTIFICATION_OVER_GREEN.b, 0.3f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_GREEN.r, Colors.COLOR_NOTIFICATION_OVER_GREEN.g, Colors.COLOR_NOTIFICATION_OVER_GREEN.b, 0.35f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_GREEN.r, Colors.COLOR_NOTIFICATION_OVER_GREEN.g, Colors.COLOR_NOTIFICATION_OVER_GREEN.b, 0.3f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_GREEN.r, Colors.COLOR_NOTIFICATION_OVER_GREEN.g, Colors.COLOR_NOTIFICATION_OVER_GREEN.b, 0.85f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_GREEN.r, Colors.COLOR_NOTIFICATION_OVER_GREEN.g, Colors.COLOR_NOTIFICATION_OVER_GREEN.b, 0.9f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        }
        else {
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG_RED.r, Colors.COLOR_NOTIFICATION_BG_RED.g, Colors.COLOR_NOTIFICATION_BG_RED.b, 0.5f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG_RED.r, Colors.COLOR_NOTIFICATION_BG_RED.g, Colors.COLOR_NOTIFICATION_BG_RED.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_RED.r, Colors.COLOR_NOTIFICATION_OVER_RED.g, Colors.COLOR_NOTIFICATION_OVER_RED.b, 0.3f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_RED.r, Colors.COLOR_NOTIFICATION_OVER_RED.g, Colors.COLOR_NOTIFICATION_OVER_RED.b, 0.35f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_RED.r, Colors.COLOR_NOTIFICATION_OVER_RED.g, Colors.COLOR_NOTIFICATION_OVER_RED.b, 0.3f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_RED.r, Colors.COLOR_NOTIFICATION_OVER_RED.g, Colors.COLOR_NOTIFICATION_OVER_RED.b, 0.85f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_RED.r, Colors.COLOR_NOTIFICATION_OVER_RED.g, Colors.COLOR_NOTIFICATION_OVER_RED.b, 0.9f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        }
        oSB.setColor(Color.WHITE);
        Game.getCiv(this.id).getFlag().draw(oSB, this.getPosX() + iTranslateX + CFG.PADDING * 2, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + iTranslateX + CFG.PADDING * 2, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 4 + ImageManager.getImage(Images.flag_rect).getWidth() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public boolean getCheckboxState() {
        return this.isActiveRedGreen;
    }
    
    @Override
    public void setCheckboxState(final boolean checkboxState) {
        this.isActiveRedGreen = checkboxState;
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
