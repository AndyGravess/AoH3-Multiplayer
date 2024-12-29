// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GlyphLayout_Game
{
    public float width;
    public float height;
    
    public GlyphLayout_Game() {
        this.width = 1.0f;
        this.height = 1.0f;
    }
    
    public boolean setText(final BitmapFont font, final CharSequence str) {
        try {
            if (str != null && str.length() > 0) {
                final GlyphLayout glyphLayout = new GlyphLayout();
                glyphLayout.setText(font, str);
                this.width = glyphLayout.width;
                this.height = glyphLayout.height;
                return true;
            }
            this.width = 1.0f;
            this.height = (float)CFG.TEXT_HEIGHT;
            return false;
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public float getHeight() {
        return this.height;
    }
}
