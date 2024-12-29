// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Resource;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_StaticBG_LargestProducer extends Text_StaticBG
{
    public int iResourceID;
    
    public Text_StaticBG_LargestProducer(final String sText, final int iResourceID, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.iResourceID = iResourceID;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = getHoverLargestProducers(this.getCurrent(), true);
    }
    
    public static MenuElement_Hover getHoverLargestProducers(final int resourceID, final boolean addResourceName) {
        try {
            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(Game.lang.get("Resource") + ": ", ResourcesManager.getResourceName(resourceID), resourceID, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LargestProducer") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ResourcesManager.worldResources_LargestProducer_Amount.get(resourceID) / 100.0f, 10), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Resource(resourceID, CFG.PADDING, 0));
            nData.add(new MenuElement_HoverElement_Type_Flag(ResourcesManager.worldResources_LargestProducer.get(resourceID), CFG.PADDING, 0));
            nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2((float)Math.min(100.0, Math.floor(ResourcesManager.worldResources_LargestProducer_Amount.get(resourceID) / (float)ResourcesManager.worldResourcesProduced.get(resourceID) * 100.0f)), 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("WorldProduction") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ResourcesManager.worldResourcesProduced.get(resourceID) / 100.0f, 10), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Resource(resourceID, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (ResourcesManager.worldResourcesProduced.get(resourceID) > 0) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final List<Integer> productionCivID = new ArrayList<Integer>();
                final List<Integer> production = new ArrayList<Integer>();
                for (int i = 0; i < Game.getCivsSize(); ++i) {
                    production.add(0);
                    productionCivID.add(i);
                }
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    if (Game.getProvince(i).getResourceID() == resourceID && !Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0) {
                        production.set(Game.getProvince(i).getCivID(), production.get(Game.getProvince(i).getCivID()) + (int)(ResourcesManager.getProducedGoods(i) * 100.0f));
                    }
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(Game.lang.get("Production") + ": ", ResourcesManager.getResourceName(resourceID), resourceID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                for (int i = 0; i < 10 && i < production.size(); ++i) {
                    int toAdd = 0;
                    for (int j = 1; j < production.size(); ++j) {
                        if (production.get(toAdd) < production.get(j)) {
                            toAdd = j;
                        }
                    }
                    if (production.get(toAdd) == 0) {
                        break;
                    }
                    nData.add(new MenuElement_HoverElement_Type_Text("" + (i + 1) + ".", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_Flag(productionCivID.get(toAdd), CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(production.get(toAdd) / 100.0f, 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Resource(resourceID, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)Math.min(100.0, Math.floor(production.get(toAdd) / (float)ResourcesManager.worldResourcesProduced.get(resourceID) * 100.0f)), 10) + "%", CFG.FONT_REGULAR, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Text(" " + Game.getCiv(productionCivID.get(toAdd)).getCivName(), CFG.FONT_REGULAR, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    production.remove(toAdd);
                    productionCivID.remove(toAdd);
                }
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers") + (addResourceName ? (": " + ResourcesManager.getResourceName(resourceID)) : ""), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            return new MenuElement_Hover(nElements, true);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return null;
        }
    }
    
    @Override
    public int getCurrent() {
        return this.iResourceID;
    }
}
