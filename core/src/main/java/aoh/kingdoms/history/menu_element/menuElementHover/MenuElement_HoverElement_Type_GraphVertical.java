// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical_Data_Type;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical;

public class MenuElement_HoverElement_Type_GraphVertical implements MenuElement_HoverElement_Type
{
    public Graph_Vertical graphVertical;
    
    public MenuElement_HoverElement_Type_GraphVertical() {
        this.graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT, Game.lang.get("Civilizations"), Game.lang.get("Provinces"), 0, 0, 500, 200, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        this.graphVertical.draw(oSB, nPosX, nPosY + CFG.PADDING, false, false);
    }
    
    @Override
    public int getWidth() {
        return this.graphVertical.getWidth();
    }
    
    @Override
    public int getHeight() {
        return this.graphVertical.getHeight();
    }
}
