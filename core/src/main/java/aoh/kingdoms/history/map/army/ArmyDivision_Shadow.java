// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.army;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;

public class ArmyDivision_Shadow
{
    public String sArmy;
    public int iProvinceID;
    public int iArmyWidth;
    public int extraY;
    
    public ArmyDivision_Shadow(final String sArmy, final int iProvinceID, final int extraY) {
        this.extraY = 0;
        this.sArmy = sArmy;
        this.iProvinceID = iProvinceID;
        this.extraY = extraY;
        this.updateArmyWidth_Just();
    }
    
    protected final void updateArmyWidth_Just() {
        try {
            final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText(Renderer.fontArmy_GlyphLayout, this.sArmy);
            this.iArmyWidth = (int)glyphLayout.width;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            this.iArmyWidth = CFG.PADDING;
        }
    }
}
