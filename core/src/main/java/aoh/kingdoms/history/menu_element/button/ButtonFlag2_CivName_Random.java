// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class ButtonFlag2_CivName_Random extends Button
{
    public ButtonFlag2_CivName_Random(final int iPosX, final int iPosY, final boolean isClickable) {
        this.init(Game.lang.get("Random"), CFG.FONT_REGULAR_SMALL, -1, iPosX, iPosY, Math.max(CFG.BUTTON_WIDTH, ImageManager.getImage(Images.flagOverDefault).getHeight()), ImageManager.getImage(Images.flagOverDefault).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2, isClickable, true, false, false);
        int tWMax = 0;
        while (this.iTextWidth >= this.getWidth() - ((this.iTextPositionX > 0) ? this.iTextPositionX : 0) - CFG.PADDING && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        int nY;
        int nH;
        if (this.getIsHovered() || isActive) {
            nY = this.getPosY();
            nH = this.getHeight();
        }
        else {
            nY = this.getPosY() + CFG.TEXT_HEIGHT + CFG.PADDING * 2;
            nH = this.getHeight() - CFG.TEXT_HEIGHT - CFG.PADDING * 2;
        }
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, nY + iTranslateY, this.getWidth(), nH, 1.0f);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.dice).draw(oSB, this.getPosX() + iTranslateX + (this.getWidth() - ImageManager.getImage(Images.dice).getWidth()) / 2, nY + iTranslateY + (ImageManager.getImage(Images.flagOverDefault).getHeight() - ImageManager.getImage(Images.dice).getHeight()) / 2);
        if (this.getIsHovered() || isActive) {
            Renderer.drawText(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.flagOverDefault).getHeight() + (CFG.TEXT_HEIGHT + CFG.PADDING * 2) / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RandomCivilization"), Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.dice, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements, true);
    }
    
    @Override
    public void actionElement() {
        try {
            final List<Integer> civs = new ArrayList<Integer>();
            for (int i = 0; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    civs.add(i);
                }
            }
            if (!civs.isEmpty()) {
                final int civID = civs.get(Game.oR.nextInt(civs.size()));
                if (Game.player.iCivID != civID) {
                    Game.player.iCivID = civID;
                    Game.menuManager.rebuildNewGameCiv();
                    if (Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0) {
                        Game.mapCoords.centerToProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
                        Game.setActiveProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.menuManager.addToastGold(Game.getCiv(Game.player.iCivID).getCivName(), Images.dice);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
