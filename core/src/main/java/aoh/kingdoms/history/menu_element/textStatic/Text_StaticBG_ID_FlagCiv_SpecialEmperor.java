// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_StaticBG_ID_FlagCiv_SpecialEmperor extends Text_StaticBG
{
    public int id;
    public String text;
    public int iTextW;
    public int iTextH;
    
    public Text_StaticBG_ID_FlagCiv_SpecialEmperor(final String text, final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.id = id;
        this.text = text;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(fontID), text);
        this.iTextW = (int)Renderer.glyphLayout.width;
        this.iTextH = (int)Renderer.glyphLayout.height;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.5f : 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        Game.getCiv(this.id).getFlag().draw(oSB, this.getPosX() + iTranslateX + CFG.PADDING * 2, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + iTranslateX + CFG.PADDING * 2, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.text, this.getPosX() + CFG.PADDING * 4 + ImageManager.getImage(Images.flag_rect).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextH / 2 + iTranslateY, this.getColor2(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + CFG.PADDING * 4 + this.iTextW + ImageManager.getImage(Images.flag_rect).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextH / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
    
    public Color getColor2(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover2(isActive, this.getIsHovered());
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        if (isHovered) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = this.id;
        }
    }
}
