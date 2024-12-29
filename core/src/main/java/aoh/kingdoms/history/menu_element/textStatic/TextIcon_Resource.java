// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Resource;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ResourceTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon_Resource extends Button
{
    public int resourceID;
    public int iconWidth;
    public int iconHeight;
    
    public TextIcon_Resource(final String sText, final int resourceID, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.resourceID = resourceID;
        if (resourceID >= 0) {
            final float iconScale = Math.min(1.0f, (this.getHeight() - this.getTextH() - CFG.PADDING * 2) / (float)ResourcesManager.resourceImages.get(resourceID).getHeight());
            this.iconWidth = (int)(ResourcesManager.resourceImages.get(resourceID).getWidth() * iconScale);
            this.iconHeight = (int)(ResourcesManager.resourceImages.get(resourceID).getHeight() * iconScale);
        }
        else {
            final float iconScale = Math.min(1.0f, (this.getHeight() - this.getTextH() - CFG.PADDING * 2) / (float)ImageManager.getImage(Images.resourceNone).getHeight());
            this.iconWidth = (int)(ImageManager.getImage(Images.resourceNone).getWidth() * iconScale);
            this.iconHeight = (int)(ImageManager.getImage(Images.resourceNone).getHeight() * iconScale);
        }
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - CFG.PADDING * 2 && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, (this.getIsHovered() || isActive) ? 0.6f : 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), this.getTextH(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(TextIcon2.getColor_gradientXY());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), this.getTextH());
        oSB.setColor(TextIcon2.getColor_gradientFull());
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
        if (this.resourceID >= 0) {
            ResourcesManager.resourceImages.get(this.resourceID).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextH()) / 2 - this.iconHeight / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        }
        else {
            ImageManager.getImage(Images.resourceNone).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextH()) / 2 - this.iconHeight / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        }
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getTextHeight() + iTranslateY, this.getColor(isActive));
    }
    
    public int getTextH() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Resource") + ": ", CFG.FONT_BOLD));
        if (this.resourceID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle(ResourcesManager.lResources.get(this.resourceID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ResourceTitle(this.resourceID, CFG.PADDING, 0));
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("None"), CFG.FONT_BOLD, Colors.HOVER_RIGHT));
        }
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (this.resourceID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Price") + ": ", CFG.FONT_BOLD_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ResourcesManager.getPrice(this.resourceID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (ResourcesManager.lResources.get(this.resourceID).uniqueBuildingID >= 0) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UniqueBuilding") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + BuildingsManager.buildings.get(ResourcesManager.lResources.get(this.resourceID).uniqueBuildingID).Name[0], CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nData.add(new MenuElement_HoverElement_Type_Resource(this.resourceID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.getResourceGroupName(ResourcesManager.lResources.get(this.resourceID).GroupID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.menuManager.getVisibleInGame_Goods()) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Provinces") + ": " + ResourcesManager.lResources.get(this.resourceID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover(nElements, true);
        }
        else {
            this.menuElementHover = new MenuElement_Hover(nElements);
        }
    }
    
    @Override
    public int getCurrent() {
        return this.resourceID;
    }
}
