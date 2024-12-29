// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class MenuElement_HoverElement
{
    private List<MenuElement_HoverElement_Type> lElements;
    private int iMaxHeight;
    public boolean drawElement;
    
    public MenuElement_HoverElement(final List<MenuElement_HoverElement_Type> nElements) {
        this.iMaxHeight = 0;
        this.drawElement = true;
        this.lElements = new ArrayList<MenuElement_HoverElement_Type>();
        for (int i = 0; i < nElements.size(); ++i) {
            this.lElements.add(nElements.get(i));
            this.iMaxHeight = Math.max(this.iMaxHeight, nElements.get(i).getHeight());
        }
    }
    
    public MenuElement_HoverElement(final List<MenuElement_HoverElement_Type> nElements, final boolean drawElement) {
        this.iMaxHeight = 0;
        this.drawElement = true;
        this.drawElement = drawElement;
        this.lElements = new ArrayList<MenuElement_HoverElement_Type>();
        for (int i = 0; i < nElements.size(); ++i) {
            this.lElements.add(nElements.get(i));
            this.iMaxHeight = Math.max(this.iMaxHeight, nElements.get(i).getHeight());
        }
    }
    
    public final void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        int i = 0;
        int tX = 0;
        while (i < this.lElements.size()) {
            this.lElements.get(i).draw(oSB, nPosX + tX, nPosY, nAlpha, iMaxWidth);
            tX += this.lElements.get(i).getWidth();
            ++i;
        }
    }
    
    public final int getWidth() {
        int out = 0;
        for (int i = 0; i < this.lElements.size(); ++i) {
            out += this.lElements.get(i).getWidth();
        }
        return out;
    }
    
    public int getHeight() {
        return this.iMaxHeight;
    }
}
