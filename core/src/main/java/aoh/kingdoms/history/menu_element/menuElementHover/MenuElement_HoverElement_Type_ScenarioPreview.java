// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.button.Button_Preview;

public class MenuElement_HoverElement_Type_ScenarioPreview implements MenuElement_HoverElement_Type
{
    public Button_Preview buttonPreview;
    
    public MenuElement_HoverElement_Type_ScenarioPreview(final int scenarioID) {
        this.buttonPreview = new Button_Preview(0, 0, scenarioID);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        this.buttonPreview.draw(oSB, nPosX, nPosY, false, false);
    }
    
    @Override
    public int getWidth() {
        return this.buttonPreview.getWidth();
    }
    
    @Override
    public int getHeight() {
        return this.buttonPreview.getHeight();
    }
    
    public int getHeight2() {
        return this.buttonPreview.getHeight();
    }
}
