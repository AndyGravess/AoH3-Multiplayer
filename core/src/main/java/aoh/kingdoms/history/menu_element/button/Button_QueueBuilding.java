// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.province.ProvinceConstructionBuilding;

public class Button_QueueBuilding extends Button
{
    public ProvinceConstructionBuilding building;
    public int provinceID;
    
    public Button_QueueBuilding(final int nPosX, final int nPosY, final int provinceID, final ProvinceConstructionBuilding building) {
        this.init(null, CFG.FONT_BOLD_SMALL, -1, nPosX, nPosY, ImageManager.getImage(Images.unitsFrameMap).getWidth(), ImageManager.getImage(Images.unitsFrameMap).getHeight(), true, true, false, false);
        this.provinceID = provinceID;
        this.building = building;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        final Image progressBarFrame = ImageManager.getImage(Images.progressBarFrame);
        final Image progressBarFrameMask = ImageManager.getImage(Images.progressBarFrameMask);
        final Image unitsFrameMap = ImageManager.getImage(Images.unitsFrameMap);
        oSB.setShader(Renderer.shaderAlpha);
        BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(this.building.getBuilding()).ImageID[this.building.getBuildingID()]).getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.unitsFrameMapMask).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        unitsFrameMap.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        final int tCenterX = (progressBarFrame.getWidth() - progressBarFrameMask.getWidth()) / 2;
        final int tCenterY = (progressBarFrame.getHeight() - progressBarFrameMask.getHeight()) / 2;
        oSB.setColor(new Color(ProvinceDraw.progressBarBG));
        progressBarFrameMask.draw(oSB, this.getPosX() + iTranslateX + tCenterX, this.getPosY() + iTranslateY + unitsFrameMap.getHeight() - progressBarFrame.getHeight() + tCenterY);
        oSB.setColor(ProvinceDraw.progressBar);
        progressBarFrameMask.draw(oSB, this.getPosX() + iTranslateX + tCenterX, this.getPosY() + iTranslateY + unitsFrameMap.getHeight() - progressBarFrame.getHeight() + tCenterY, (int)(progressBarFrameMask.getWidth() * (1.0f - this.building.getConstructionTimeLeft() / (float)this.building.getConstructionTime())), progressBarFrameMask.getHeight());
        oSB.setColor(Color.WHITE);
        progressBarFrame.draw(oSB, this.getPosX() + iTranslateX + unitsFrameMap.getWidth() / 2 - progressBarFrame.getWidth() / 2, this.getPosY() + iTranslateY + unitsFrameMap.getHeight() - progressBarFrame.getHeight());
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    public void actionElement() {
        Game.mapCoords.centerToProvinceID(this.provinceID);
    }
    
    @Override
    public void actionElementPPM() {
        Game.getProvince(this.provinceID).cancelBuildingConstruction(Game.player.iCivID, this.building.getBuilding(), this.building.getBuildingID());
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(this.provinceID).getProvinceName(), Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(this.provinceID).getCivID(), CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
