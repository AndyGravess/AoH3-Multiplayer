// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Renderer;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.textures.Image;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SparksAnimation
{
    public int currentIMG;
    public long ANIMATION_TIME;
    
    public SparksAnimation() {
        this.currentIMG = 0;
        this.ANIMATION_TIME = 0L;
    }
    
    public void draw(final SpriteBatch oSB, final int posX, final int posY, final int width, final int height) {
        Images.sparks.get(this.currentIMG).draw(oSB, posX, posY, width, height);
        this.updateAnimation();
    }
    
    public void draw(final SpriteBatch oSB, final int posX, final int posY, final int width, final int height, final boolean flipX, final boolean flipY) {
        Images.sparks.get(this.currentIMG).draw(oSB, posX, posY, width, height, flipX, flipY);
        this.updateAnimation();
    }
    
    public void draw2(final SpriteBatch oSB, final int posX, final int posY, final int width, final int height) {
        Images.sparks.get(this.currentIMG).draw2(oSB, posX, posY, width, height);
        this.updateAnimation();
    }
    
    public void draw2(final SpriteBatch oSB, final int posX, final int posY, final int width, final int height, final boolean flipX, final boolean flipY) {
        Images.sparks.get(this.currentIMG).draw2(oSB, posX, posY, width, height, flipX, flipY);
        this.updateAnimation();
    }
    
    public void updateAnimation() {
        if (CFG.currentTimeMillis - this.ANIMATION_TIME > 45L) {
            this.ANIMATION_TIME = CFG.currentTimeMillis;
            ++this.currentIMG;
            if (this.currentIMG >= Images.SPARKS_SIZE) {
                this.currentIMG = 0;
            }
        }
    }
}
