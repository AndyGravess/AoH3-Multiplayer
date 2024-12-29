// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Hover
{
    void draw(final SpriteBatch p0, final int p1, final int p2);
    
    void drawAlwaysBelow(final SpriteBatch p0, final int p1, final int p2);
    
    void drawAlwaysOver(final SpriteBatch p0, final int p1, final int p2);
    
    void drawAlwaysOver_Mobile(final SpriteBatch p0, final int p1, final int p2);
    
    void drawProvinceInfo(final SpriteBatch p0, final int p1, final int p2);
    
    void draw_Hover(final SpriteBatch p0, final int p1, final int p2);
    
    void draw_HoverWithoutAnimation(final SpriteBatch p0, final int p1, final int p2);
}
