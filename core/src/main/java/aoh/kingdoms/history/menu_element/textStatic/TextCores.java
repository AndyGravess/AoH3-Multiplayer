// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextCores extends Button
{
    public List<Integer> lCivID;
    public int iCivsSize;
    public int flagWidth;
    public int flagHeight;
    public int iProvinceID;
    public boolean drawRed;
    
    public TextCores(final String sText, final List<Integer> tCivs, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int iProvinceID, final boolean drawRed) {
        this.lCivID = new ArrayList<Integer>();
        this.iCivsSize = 0;
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.iProvinceID = iProvinceID;
        this.lCivID = tCivs;
        this.iCivsSize = this.lCivID.size();
        this.drawRed = drawRed;
        this.flagWidth = (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale());
        this.flagHeight = (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale());
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        if (this.drawRed) {
            oSB.setColor(new Color(Colors.HOVER_NEGATIVE.r, Colors.HOVER_NEGATIVE.g, Colors.HOVER_NEGATIVE.b, (Game.getProvince(this.iProvinceID).getCivID() == Game.player.iCivID) ? 0.5f : 0.3f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        if (this.iCivsSize != Game.getProvince(this.iProvinceID).iCoresSize) {
            this.lCivID.clear();
            for (int i = 0; i < Game.getProvince(this.iProvinceID).iCoresSize; ++i) {
                this.lCivID.add(Game.getProvince(this.iProvinceID).getCore(i));
            }
            this.iCivsSize = this.lCivID.size();
        }
        try {
            if (Game.getProvince(this.iProvinceID).coreCreation != null) {
                oSB.setColor(new Color(ProvinceDraw.progressBar.r, ProvinceDraw.progressBar.g, ProvinceDraw.progressBar.b, 0.5f));
                Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
                oSB.setColor(new Color(ProvinceDraw.progressBar));
                Renderer.drawBoxProgress(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, (int)(this.getWidth() * (1.0f - Game.getProvince(this.iProvinceID).coreCreation.daysLeft / (float)Game.getProvince(this.iProvinceID).coreCreation.investTime)), this.getHeight(), this.getWidth());
                oSB.setColor(Color.WHITE);
                oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.2f));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                oSB.setColor(Color.WHITE);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        for (int i = 0; i < this.iCivsSize; ++i) {
            try {
                if (this.lCivID.get(i) >= 0) {
                    Game.getCiv(this.lCivID.get(i)).getFlag().draw(oSB, this.getPosX() + (this.flagWidth + CFG.PADDING) * i + CFG.PADDING * 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, this.flagWidth, this.flagHeight);
                }
                else {
                    ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + (this.flagWidth + CFG.PADDING) * i + CFG.PADDING * 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, this.flagWidth, this.flagHeight);
                }
            }
            catch (final IndexOutOfBoundsException e) {
                ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + (this.flagWidth + CFG.PADDING) * i + CFG.PADDING * 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, this.flagWidth, this.flagHeight);
            }
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + (this.flagWidth + CFG.PADDING) * i + CFG.PADDING * 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, this.flagWidth, this.flagHeight);
        }
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
    
    private final float getImageScale() {
        return CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = InGame_ProvinceInfo.getHoverCores(this.iProvinceID, true);
    }
}
