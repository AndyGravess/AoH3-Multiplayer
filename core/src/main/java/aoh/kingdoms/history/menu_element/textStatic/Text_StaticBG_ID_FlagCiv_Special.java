// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;

public class Text_StaticBG_ID_FlagCiv_Special extends Text_StaticBG
{
    public int id;
    public static int img;
    public static int imgW;
    public static int imgH;
    public String text;
    public int iTextW;
    public int iTextH;
    public static int coreW;
    public static int coreH;
    
    public Text_StaticBG_ID_FlagCiv_Special(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id, final int img, final String text) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.id = id;
        final float iconScale = this.getImageScale(Images.gold);
        this.text = text;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), text);
        this.iTextW = (int)Renderer.glyphLayout.width;
        this.iTextH = (int)Renderer.glyphLayout.height;
        Text_StaticBG_ID_FlagCiv_Special.img = img;
        Text_StaticBG_ID_FlagCiv_Special.imgW = (int)(ImageManager.getImage(img).getWidth() * iconScale);
        Text_StaticBG_ID_FlagCiv_Special.imgH = (int)(ImageManager.getImage(img).getHeight() * iconScale);
        Text_StaticBG_ID_FlagCiv_Special.coreW = (int)(ImageManager.getImage(Images.core).getWidth() * iconScale);
        Text_StaticBG_ID_FlagCiv_Special.coreH = (int)(ImageManager.getImage(Images.core).getHeight() * iconScale);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.5f : 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getWidth(), this.getHeight() - CFG.PADDING * 2, 1.0f);
        if (Game.getProvince(this.id).haveACore) {
            oSB.setColor(Color.WHITE);
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
        }
        ImageManager.getImage(Images.core).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - Text_StaticBG_ID_FlagCiv_Special.coreW + iTranslateX, this.getPosY() + this.getHeight() / 2 - Text_StaticBG_ID_FlagCiv_Special.imgH / 2 + iTranslateY, Text_StaticBG_ID_FlagCiv_Special.coreW, Text_StaticBG_ID_FlagCiv_Special.coreH);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Text_StaticBG_ID_FlagCiv_Special.img).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - Text_StaticBG_ID_FlagCiv_Special.imgW - Text_StaticBG_ID_FlagCiv_Special.coreW + iTranslateX, this.getPosY() + this.getHeight() / 2 - Text_StaticBG_ID_FlagCiv_Special.imgH / 2 + iTranslateY, Text_StaticBG_ID_FlagCiv_Special.imgW, Text_StaticBG_ID_FlagCiv_Special.imgH);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.text, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iTextW - Text_StaticBG_ID_FlagCiv_Special.imgW - Text_StaticBG_ID_FlagCiv_Special.coreW + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextH / 2 + iTranslateY, this.getColor2(isActive));
        Game.getCiv(Game.getProvince(this.id).getCivID()).getFlag().draw(oSB, this.getPosX() + iTranslateX + CFG.PADDING * 2, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + iTranslateX + CFG.PADDING * 2, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + CFG.PADDING * 4 + ImageManager.getImage(Images.flag_rect).getWidth() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
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
    }
    
    private final float getImageScale(final int iImageID) {
        return Math.min(1.0f, CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight() * 1.15f);
    }
}
