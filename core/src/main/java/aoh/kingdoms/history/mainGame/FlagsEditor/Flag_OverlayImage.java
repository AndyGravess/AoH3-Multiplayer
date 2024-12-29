// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.FlagsEditor;

import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.textures.Image;

public class Flag_OverlayImage
{
    public int iOverlayID;
    public Image imageOverlay;
    
    public Flag_OverlayImage(final int iOverlayID) {
        this.iOverlayID = 0;
        this.iOverlayID = iOverlayID;
        this.imageOverlay = new Image(new Texture(Gdx.files.internal("gfx/editorFlags/overlays/" + Game.flagManager.lOverlays.get(iOverlayID).sName + ".png")), Texture.TextureFilter.Linear);
    }
}
