// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Game;
import java.util.List;

public class MenuElement_HoverElement_Type_Terrain implements MenuElement_HoverElement_Type
{
    private int iTerrainID;
    private int iTerrainID2;
    private int offsetLeft;
    private int offsetRight;
    private int iWidth;
    private int iHeight;
    
    public MenuElement_HoverElement_Type_Terrain(final int iTerrainID, final int provinceID, final int offsetLeft, final int offsetRight) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.iWidth = 0;
        this.iHeight = 0;
        this.iTerrainID = iTerrainID;
        this.iTerrainID2 = Math.max(0, provinceID) % Game.terrainManager.terrainImages.get(iTerrainID).size();
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
        this.iWidth = (int)(Game.terrainManager.terrainImages.get(iTerrainID).get(this.iTerrainID2).getWidth() * (CFG.CIV_FLAG_HEIGHT * this.getImageScale()) / Game.terrainManager.terrainImages.get(iTerrainID).get(this.iTerrainID2).getHeight());
        this.iHeight = (int)(Game.terrainManager.terrainImages.get(iTerrainID).get(this.iTerrainID2).getHeight() * (CFG.CIV_FLAG_HEIGHT * this.getImageScale()) / Game.terrainManager.terrainImages.get(iTerrainID).get(this.iTerrainID2).getHeight());
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        Game.terrainManager.terrainImages.get(this.iTerrainID).get(this.iTerrainID2).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING + CFG.PADDING / 2 + CFG.TEXT_HEIGHT / 2 - this.iHeight / 2, this.iWidth, this.iHeight);
    }
    
    @Override
    public int getWidth() {
        return this.offsetRight + this.offsetLeft + this.iWidth;
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING;
    }
    
    private final float getImageScale() {
        return CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
    }
}
