// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_HoverElement_Type_Building implements MenuElement_HoverElement_Type
{
    public int building;
    public int buildingID;
    
    public MenuElement_HoverElement_Type_Building(final int building, final int buildingID) {
        this.building = building;
        this.buildingID = buildingID;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(this.building).ImageID[this.buildingID]).draw(oSB, nPosX, nPosY);
        ImageManager.getImage(Images.buildingsFrame).draw(oSB, nPosX, nPosY);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public int getWidth() {
        return ImageManager.getImage(Images.buildingsFrame).getWidth();
    }
    
    @Override
    public int getHeight() {
        return ImageManager.getImage(Images.buildingsFrame).getHeight();
    }
}
