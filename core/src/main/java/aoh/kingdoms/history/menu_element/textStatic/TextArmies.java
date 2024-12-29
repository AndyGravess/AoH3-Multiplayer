// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.map.province.ProvinceTouchExtraAction;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextArmies extends Button
{
    public String key;
    public int iProvinceID;
    public int iCivID;
    public String sArmy;
    public int iArmyWidth;
    public int maxArmyPosX;
    
    public TextArmies(final String sText, final String sArmy, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final String key, final int iCivID, final int iProvinceID, final int maxArmyPosX) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, -1, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.sArmy = sArmy;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sArmy);
        this.iArmyWidth = (int)Renderer.glyphLayout.width;
        this.key = key;
        this.iProvinceID = iProvinceID;
        this.iCivID = iCivID;
        this.maxArmyPosX = maxArmyPosX;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.75f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.75f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.2f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.7f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getWidth(), CFG.PADDING);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, this.fontID, this.sArmy, this.getPosX() + Math.min(this.maxArmyPosX, this.getWidth()) - this.iArmyWidth - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
    }
    
    @Override
    public int getCurrent() {
        return this.iProvinceID;
    }
    
    @Override
    public void actionElement() {
        int nArmyID = Game.getProvince(this.iProvinceID).getArmyKeyID(this.key);
        if (nArmyID < 0) {
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                final int outID = Game.getProvince(i).getArmyKeyID(this.key, this.iCivID);
                if (outID >= 0) {
                    this.iProvinceID = i;
                    nArmyID = outID;
                    break;
                }
            }
        }
        if (nArmyID >= 0) {
            if (Game.activeArmySize == 1 && Game.activeArmy.get(0).key.equals(Game.getProvince(this.iProvinceID).getArmy(nArmyID).key)) {
                Game.mapCoords.centerToProvinceID(this.iProvinceID);
            }
            else {
                Game.clearActiveArmy();
                final Game.HoveredArmy nHA = new Game.HoveredArmy();
                nHA.key = Game.getProvince(this.iProvinceID).getArmy(nArmyID).key;
                nHA.iCivID = Game.getProvince(this.iProvinceID).getArmy(nArmyID).civID;
                nHA.iProvinceID = this.iProvinceID;
                nHA.iArmyID = nArmyID;
                Game.addActiveArmy(nHA);
                ProvinceTouchExtraAction.actionUp_SetActiveArmy();
            }
            if (Game.menuManager.getVisibleInGame_Generals()) {
                Game.menuManager.setVisibleInGame_Generals(false);
            }
            if (Game.menuManager.getVisibleInGame_GeneralRecruit()) {
                Game.menuManager.setVisibleInGame_GeneralRecruit(false);
            }
        }
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SelectArmy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements, true);
    }
}
