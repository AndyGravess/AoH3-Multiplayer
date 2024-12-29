// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_StaticBG_EconomyRanking extends Text_StaticBG
{
    public int id;
    public static int activeID;
    public static int resourceID;
    
    public Text_StaticBG_EconomyRanking(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.id = id;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered()) {
            if (Text_StaticBG_EconomyRanking.activeID != this.id) {
                Text_StaticBG_EconomyRanking.activeID = this.id;
                Text_StaticBG_EconomyRanking.resourceID = ResourcesManager.getLargestGoodsProducedByCiv(this.id);
            }
            if (Text_StaticBG_EconomyRanking.resourceID >= 0) {
                final float tempScale = (CFG.TEXT_HEIGHT + CFG.PADDING * 2) / (float)ResourcesManager.resourceImages.get(Text_StaticBG_EconomyRanking.resourceID).getHeight();
                final int resW = (int)(ResourcesManager.resourceImages.get(Text_StaticBG_EconomyRanking.resourceID).getWidth() * tempScale);
                final int resH = (int)(ResourcesManager.resourceImages.get(Text_StaticBG_EconomyRanking.resourceID).getHeight() * tempScale);
                ResourcesManager.resourceImages.get(Text_StaticBG_EconomyRanking.resourceID).draw(oSB, this.getPosX() + this.getWidth() / 2 - resW / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - resH / 2 + iTranslateY, resW, resH);
            }
            else {
                Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
            }
        }
        else {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
        }
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.lang.get("ProducedGoods")));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        final List<Float> goodsProduced = new ArrayList<Float>();
        for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
            goodsProduced.add(0.0f);
        }
        int bestResourceID = -1;
        for (int j = 0; j < Game.getCiv(this.id).getNumOfProvinces(); ++j) {
            if (Game.getProvince(Game.getCiv(this.id).getProvinceID(j)).getResourceID() >= 0) {
                goodsProduced.set(Game.getProvince(Game.getCiv(this.id).getProvinceID(j)).getResourceID(), goodsProduced.get(Game.getProvince(Game.getCiv(this.id).getProvinceID(j)).getResourceID()) + ResourcesManager.getProducedGoods(Game.getCiv(this.id).getProvinceID(j)));
                bestResourceID = Game.getProvince(Game.getCiv(this.id).getProvinceID(j)).getResourceID();
            }
        }
        if (bestResourceID >= 0) {
            int numAdded = 0;
            while (numAdded++ < 6) {
                for (int k = 0; k < ResourcesManager.iResourcesSize; ++k) {
                    if (goodsProduced.get(bestResourceID) < goodsProduced.get(k)) {
                        bestResourceID = k;
                    }
                }
                if (goodsProduced.get(bestResourceID) <= 0.0f) {
                    break;
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(ResourcesManager.getResourceName(bestResourceID) + ": ", CFG.getPrecision2(goodsProduced.get(bestResourceID), 10), bestResourceID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                goodsProduced.set(bestResourceID, 0.0f);
            }
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("None"), "", Images.resourceNone, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        goodsProduced.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    static {
        Text_StaticBG_EconomyRanking.activeID = 0;
        Text_StaticBG_EconomyRanking.resourceID = 0;
    }
}
