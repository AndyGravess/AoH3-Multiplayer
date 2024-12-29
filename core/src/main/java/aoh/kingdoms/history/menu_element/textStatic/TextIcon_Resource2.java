// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menusInGame.Goods.InGame_GoodsMarket;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Resource;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon_Resource2 extends Button
{
    public int resourceID;
    public int maxWidth;
    
    public TextIcon_Resource2(final String sText, final int resourceID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int maxWidth) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.resourceID = resourceID;
        this.maxWidth = maxWidth;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.65f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxWidth, this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.6f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.9f : 0.65f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientFull.draw(oSB, this.getPosX() + this.maxWidth + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - this.maxWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, this.getPosX() + this.maxWidth + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - this.maxWidth, CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + this.maxWidth + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth() - this.maxWidth, CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, this.getPosX() + this.maxWidth + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth() - this.maxWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + this.maxWidth + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - this.maxWidth, 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.85f));
        Images.gradientFull.draw(oSB, this.getPosX() + this.maxWidth + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - this.maxWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + this.maxWidth + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - this.maxWidth, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ResourcesManager.resourceImages.get(this.resourceID).draw(oSB, this.getPosX() + this.getWidth() / 2 - ResourcesManager.resourceImages.get(this.resourceID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ResourcesManager.resourceImages.get(this.resourceID).getHeight() / 2 + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() - CFG.PADDING - this.iTextWidth + iTranslateX, this.getPosY() + this.getHeight() - this.iTextHeight - CFG.PADDING + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public int getCurrent() {
        return this.resourceID;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (this.resourceID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(Game.lang.get("Resource") + ": ", ResourcesManager.getResourceName(this.resourceID), this.resourceID, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(Game.lang.get("Position") + ": ", this.getText().equals("#1") ? Game.lang.get("LargestProducer") : this.getText(), this.resourceID, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Resource") + ": ", CFG.FONT_BOLD));
            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("None"), CFG.FONT_BOLD, Colors.HOVER_RIGHT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
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
        nData.add(new MenuElement_HoverElement_Type_Empty());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_GoodsMarket.iActiveCivID).getCivName() + ": "));
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(ResourcesManager.getResourceName(this.resourceID), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements, true);
    }
}
