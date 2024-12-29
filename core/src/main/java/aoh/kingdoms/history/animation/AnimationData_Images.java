// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.animation;

import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class AnimationData_Images
{
    protected List<Image> lAnimation;
    private int iNumOfFrames;
    protected AnimationData_Type animationType;
    protected int ANIMATION_DURATION;
    protected int ANIMATION_FRAME_TIME;
    
    public AnimationData_Images(final String sImagesPath, final int numOfImages, final AnimationData_Type animationType, final int ANIMATION_DURATION, final int ANIMATION_FRAME_TIME) {
        this.lAnimation = new ArrayList<Image>();
        this.iNumOfFrames = 0;
        this.ANIMATION_DURATION = 0;
        this.ANIMATION_FRAME_TIME = 0;
        for (int i = 0; i < numOfImages; ++i) {
            this.lAnimation.add(ImageManager.loadImage(sImagesPath.replaceAll("%03u", "" + i), Texture.TextureFilter.Nearest));
        }
        this.iNumOfFrames = this.lAnimation.size();
        this.animationType = animationType;
        this.ANIMATION_DURATION = ANIMATION_DURATION;
        this.ANIMATION_FRAME_TIME = ANIMATION_FRAME_TIME;
    }
    
    public final void drawFrame(final SpriteBatch oSB, final int nPosX, final int nPosY, final int currentFrameID) {
        this.lAnimation.get(currentFrameID).draw(oSB, Game.mapCoords.getPosX() + nPosX - this.lAnimation.get(currentFrameID).getWidth() / 2, Game.mapCoords.getPosY() + nPosY - this.lAnimation.get(currentFrameID).getHeight() / 2);
    }
    
    public final int getNumOfFrames() {
        return this.iNumOfFrames;
    }
}
