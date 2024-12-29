// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import aoh.kingdoms.history.menusInGame.Battle.InGame_Battle;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import java.util.List;

public class MenuTitleIMG_Terrain extends MenuTitleIMG
{
    public String sText2;
    public int iText2Width;
    public int iText2Height;
    public int flagWidth;
    public int flagHeight;
    public int terrainID;
    public int terrainID2;
    
    public MenuTitleIMG_Terrain(final String sText, final String sText2, final boolean moveable, final boolean resizable, final int imageID, final int terrainID, final int provinceID) {
        super(sText, moveable, resizable, imageID);
        this.iText2Width = 0;
        this.terrainID = terrainID;
        this.terrainID2 = provinceID % Game.terrainManager.terrainImages.get(terrainID).size();
        this.fontID = CFG.FONT_BOLD;
        this.sText2 = sText2;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sText2);
        this.iText2Width = (int)glyphLayout.width;
        this.iText2Height = (int)glyphLayout.height;
        this.flagHeight = this.getHeight() - Images.boxTitleBORDERWIDTH;
        final float tScale = this.flagHeight / (float)Game.terrainManager.terrainImages.get(terrainID).get(this.terrainID2).getHeight();
        this.flagWidth = (int)(Game.terrainManager.terrainImages.get(terrainID).get(this.terrainID2).getWidth() * tScale);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        ImageManager.getImage(this.imageID).draw(oSB, nPosX, nPosY - this.getHeight(), nWidth, this.getHeight());
        this.drawGradient(oSB, nPosX, nPosY, nWidth, titleStatus);
        try {
            oSB.setShader(Renderer.shaderAlpha);
            Game.terrainManager.terrainImages.get(this.terrainID).get(this.terrainID2).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY - this.getHeight() + Images.boxTitleBORDERWIDTH, this.flagWidth, this.flagHeight);
            Images.gradientXY.draw(oSB, nPosX, nPosY - this.getHeight() + Images.boxTitleBORDERWIDTH, this.flagWidth, this.flagHeight);
            oSB.setColor(Color.WHITE);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
        }
        catch (final IndexOutOfBoundsException ex) {}
        this.drawText(oSB, nPosX, nPosY, nWidth, titleStatus);
    }
    
    @Override
    public void drawText(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, nPosX + CFG.PADDING, 1 + nPosY - this.iText2Height - CFG.PADDING * 3, this.iText2Width + CFG.PADDING * 2, this.iText2Height + CFG.PADDING * 2, 1.0f);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sText2, nPosX + CFG.PADDING * 2, 1 + nPosY - this.iText2Height - CFG.PADDING * 2, MenuTitle.colorHovered);
        Renderer.drawText(oSB, this.fontID, this.getText(), nPosX + nWidth / 2 - this.getTextWidth() / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 - this.getTextHeight()), this.getColorText(titleStatus));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, InGame_Battle.sDay, nPosX + nWidth / 2 - InGame_Battle.iDayWidth / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING), MenuTitle.colorHovered);
    }
}
