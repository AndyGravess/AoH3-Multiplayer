// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;

public class ButtonBuild extends Button
{
    public ButtonBuild(final int iPosX, final int iPosY, final boolean isClickable) {
        this.init(null, 0, 0, iPosX, iPosY, ImageManager.getImage(Images.buildButton).getWidth(), ImageManager.getImage(Images.buildButton).getHeight(), isClickable, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(Images.buildButton).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(Colors.COLOR_GRAY);
            Images.gradientXY.draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + 2 + iTranslateY, this.getWidth() - 4, CFG.PADDING * 2, false, true);
            Images.gradientXY.draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + this.getHeight() - 2 - CFG.PADDING * 2 + iTranslateY, this.getWidth() - 4, CFG.PADDING * 2);
            oSB.setColor(Color.WHITE);
        }
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ConstructNewBuilding"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
