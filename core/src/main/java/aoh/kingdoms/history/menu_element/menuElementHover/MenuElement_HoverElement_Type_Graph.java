// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.graph.Graph;

public class MenuElement_HoverElement_Type_Graph implements MenuElement_HoverElement_Type
{
    public Graph graph;
    public int iGraphWidth;
    
    public MenuElement_HoverElement_Type_Graph(final String sText, final Graph.GraphType graphType, final boolean split100) {
        this.iGraphWidth = 0;
        this.iGraphWidth = CFG.BUTTON_WIDTH * 3;
        this.graph = new Graph("", sText, 0, 0, this.iGraphWidth, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, true, 1, graphType, split100);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        if (this.iGraphWidth != iMaxWidth) {
            this.iGraphWidth = iMaxWidth;
            this.graph = new Graph("", this.graph.sTextY, 0, 0, this.iGraphWidth, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, true, 1, this.graph.graphType, this.graph.split100);
        }
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.9f * nAlpha));
        Images.pix.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, this.getHeight2() + CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.75f * nAlpha));
        Images.gradientXY.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, this.getHeight2() + CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 1.0f * nAlpha));
        Images.gradientXY.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, this.getHeight2() + CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f * nAlpha));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 1.0f * nAlpha));
        Images.gradientFull.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + 1, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, 1);
        Images.gradientFull.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + this.getHeight2() + CFG.PADDING * 2 - 2, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f * nAlpha));
        Images.gradientFull.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, 1);
        Images.gradientFull.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + this.getHeight2() + CFG.PADDING * 2 - 1, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f * nAlpha));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + this.getHeight2() + CFG.PADDING * 2, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING);
        Images.gradientXY.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + this.getHeight2() + CFG.PADDING * 2, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY - CFG.PADDING, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING, false, true);
        Images.gradientXY.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY - CFG.PADDING, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING);
        oSB.setColor(Color.WHITE);
        this.graph.draw(oSB, nPosX, nPosY + CFG.PADDING * 2, false, false);
    }
    
    @Override
    public int getWidth() {
        return this.graph.getWidth();
    }
    
    @Override
    public int getHeight() {
        return this.graph.getHeight() + CFG.PADDING;
    }
    
    public int getHeight2() {
        return this.graph.getHeight() + CFG.PADDING;
    }
}
