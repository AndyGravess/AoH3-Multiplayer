// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.FlagsEditor;

import com.badlogic.gdx.graphics.PixmapIO;
import aoc.kingdoms.lukasz.menusEditor.CreateCiv;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.math.Rectangle;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class FlagManager
{
    public List<Flag_Division> lDivisions;
    public List<Flag_Overlay> lOverlays;
    public static final int FLAG_WIDTH = 68;
    public static final int FLAG_HEIGHT = 44;
    public Flag_GameData flagEdit;
    public int activeColorID;
    private List<Image> divisionLayers;
    private List<Flag_OverlayImage> lOverlaysImages;
    
    public FlagManager() {
        this.lDivisions = new ArrayList<Flag_Division>();
        this.lOverlays = new ArrayList<Flag_Overlay>();
        this.activeColorID = 0;
        this.divisionLayers = new ArrayList<Image>();
        this.lOverlaysImages = new ArrayList<Flag_OverlayImage>();
    }
    
    public final void drawFlag(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.drawDivision(oSB, nPosX, nPosY);
        for (int i = 0; i < this.flagEdit.lOverlays.size(); ++i) {
            this.drawOverlay(oSB, nPosX, nPosY, i);
        }
    }
    
    public final void drawFlag_FlagFrameSize(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.drawDivision_FlagFrameSize(oSB, nPosX, nPosY);
        for (int i = 0; i < this.flagEdit.lOverlays.size(); ++i) {
            this.drawOverlay_FlagFrameSize(oSB, nPosX, nPosY, i);
        }
    }
    
    public final void drawDivision(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.beginClip(oSB, nPosX, nPosY);
        oSB.setColor((Color)this.flagEdit.lDivisionColors.get(0));
        Images.pix.draw(oSB, nPosX, nPosY, 68, 44);
        for (int i = 0; i < this.divisionLayers.size(); ++i) {
            oSB.setColor((Color)this.flagEdit.lDivisionColors.get(i + 1));
            this.divisionLayers.get(i).draw(oSB, nPosX, nPosY, 68, 44);
        }
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }
    
    public final void drawDivision_FlagFrameSize(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.beginClip_FlagFrameSize(oSB, nPosX, nPosY);
        oSB.setColor((Color)this.flagEdit.lDivisionColors.get(0));
        Images.pix.draw(oSB, nPosX, nPosY, 68, 44);
        for (int i = 0; i < this.divisionLayers.size(); ++i) {
            oSB.setColor((Color)this.flagEdit.lDivisionColors.get(i + 1));
            this.divisionLayers.get(i).draw(oSB, nPosX, nPosY, 68, 44);
        }
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }
    
    public final void drawDivisionBG(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.beginClip(oSB, nPosX, nPosY);
        oSB.setColor((Color)this.flagEdit.lDivisionColors.get(0));
        Images.pix.draw(oSB, nPosX, nPosY, 68, 44);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }
    
    public final void drawDivision(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nID) {
        this.beginClip(oSB, nPosX, nPosY);
        oSB.setColor((Color)this.flagEdit.lDivisionColors.get(0));
        Images.pix.draw(oSB, nPosX, nPosY, 68, 44);
        oSB.setColor((Color)this.flagEdit.lDivisionColors.get(nID));
        this.divisionLayers.get(nID - 1).draw(oSB, nPosX, nPosY, 68, 44);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }
    
    public final void drawDivision_FlagFrameSize(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nID) {
        this.beginClip_FlagFrameSize(oSB, nPosX, nPosY);
        oSB.setColor((Color)this.flagEdit.lDivisionColors.get(0));
        Images.pix.draw(oSB, nPosX, nPosY, 68, 44);
        oSB.setColor((Color)this.flagEdit.lDivisionColors.get(nID));
        this.divisionLayers.get(nID - 1).draw(oSB, nPosX, nPosY, 68, 44);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }
    
    public final void drawOverlay(final SpriteBatch oSB, final int nPosX, final int nPosY, final int id) {
        this.beginClip(oSB, nPosX, nPosY);
        oSB.setColor(this.flagEdit.lOverlays.get(id).oColor);
        this.getOverlay(this.flagEdit.lOverlays.get(id).iOverlayID).draw(oSB, nPosX + this.flagEdit.lOverlays.get(id).iPosX, nPosY + this.flagEdit.lOverlays.get(id).iPosY, this.flagEdit.lOverlays.get(id).iWidth, this.flagEdit.lOverlays.get(id).iHeight);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }
    
    public final void drawOverlay_FlagFrameSize(final SpriteBatch oSB, final int nPosX, final int nPosY, final int id) {
        this.beginClip_FlagFrameSize(oSB, nPosX, nPosY);
        final float tScale = 1.0f;
        oSB.setColor(this.flagEdit.lOverlays.get(id).oColor);
        this.getOverlay(this.flagEdit.lOverlays.get(id).iOverlayID).draw(oSB, nPosX + (int)(this.flagEdit.lOverlays.get(id).iPosX * tScale), nPosY + (int)(this.flagEdit.lOverlays.get(id).iPosY * tScale), (int)(this.flagEdit.lOverlays.get(id).iWidth * tScale), (int)(this.flagEdit.lOverlays.get(id).iHeight * tScale));
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }
    
    private final void beginClip(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        final Rectangle clipBounds = new Rectangle((float)nPosX, (float)(CFG.GAME_HEIGHT - nPosY), 68.0f, -44.0f);
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
    }
    
    private final void beginClip_FlagFrameSize(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        final Rectangle clipBounds = new Rectangle((float)nPosX, (float)(CFG.GAME_HEIGHT - nPosY), 68.0f, -44.0f);
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
    }
    
    private final void endClip(final SpriteBatch oSB) {
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (final IllegalStateException ex) {}
    }
    
    public final void initFlagEdit() {
        this.flagEdit = new Flag_GameData();
        this.loadDivision();
        this.loadOverlays();
    }
    
    public final void updateDivision(final boolean add) {
        final Flag_GameData flagEdit = this.flagEdit;
        flagEdit.iDivisionID += (add ? 1 : -1);
        if (this.flagEdit.iDivisionID < 0) {
            this.flagEdit.iDivisionID = this.lDivisions.size() - 1;
        }
        else if (this.flagEdit.iDivisionID >= this.lDivisions.size()) {
            this.flagEdit.iDivisionID = 0;
        }
        this.loadDivision();
    }
    
    public final void loadDivision() {
        for (int i = 0; i < this.divisionLayers.size(); ++i) {
            this.divisionLayers.get(i).getTexture().dispose();
        }
        this.divisionLayers.clear();
        for (int i = 0; i < this.lDivisions.get(this.flagEdit.iDivisionID).iLayers - 1; ++i) {
            this.divisionLayers.add(new Image(new Texture(Gdx.files.internal("gfx/editorFlags/divisions/" + this.lDivisions.get(this.flagEdit.iDivisionID).sName + "_" + i + ".png")), Texture.TextureFilter.Nearest));
        }
        for (int i = this.flagEdit.lDivisionColors.size(); i < this.lDivisions.get(this.flagEdit.iDivisionID).iLayers; ++i) {
            if (i == 0) {
                this.flagEdit.lDivisionColors.add(Color.WHITE);
            }
            else if (i == 1) {
                this.flagEdit.lDivisionColors.add(new Color(0.9843137f, 0.0f, 0.2f, 1.0f));
            }
            else if (i == 2) {
                this.flagEdit.lDivisionColors.add(new Color(0.0f, 0.19607843f, 0.39607844f, 1.0f));
            }
            else if (i == 3) {
                this.flagEdit.lDivisionColors.add(new Color(1.0f, 0.80784315f, 0.0f, 1.0f));
            }
            else {
                final Color tempColor = CFG.getRandomColor();
                this.flagEdit.lDivisionColors.add(new Color(tempColor.r, tempColor.g, tempColor.b, 1.0f));
            }
        }
    }
    
    public final void updateOverlay(final int nID, final boolean add) {
        final int tempOver = this.flagEdit.lOverlays.get(nID).iOverlayID;
        final Flag_Overlay_GameData flag_Overlay_GameData = this.flagEdit.lOverlays.get(nID);
        flag_Overlay_GameData.iOverlayID += (add ? 1 : -1);
        if (this.flagEdit.lOverlays.get(nID).iOverlayID < 0) {
            this.flagEdit.lOverlays.get(nID).iOverlayID = this.lOverlays.size() - 1;
        }
        else if (this.flagEdit.lOverlays.get(nID).iOverlayID >= this.lOverlays.size()) {
            this.flagEdit.lOverlays.get(nID).iOverlayID = 0;
        }
        this.tryRemoveOverlay(tempOver);
        this.loadOverlayImage(this.flagEdit.lOverlays.get(nID).iOverlayID);
        this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iWidth = (int)Math.abs(this.getOverlay(this.flagEdit.lOverlays.get(nID).iOverlayID).getWidth() * this.lOverlays.get(this.flagEdit.lOverlays.get(nID).iOverlayID).Scale);
        this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iHeight = (int)Math.abs(this.getOverlay(this.flagEdit.lOverlays.get(nID).iOverlayID).getHeight() * this.lOverlays.get(this.flagEdit.lOverlays.get(nID).iOverlayID).Scale);
        this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iPosX = 34 - this.flagEdit.lOverlays.get(nID).iWidth / 2;
        this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iPosY = 22 - this.flagEdit.lOverlays.get(nID).iHeight / 2;
    }
    
    public final void addOverlay() {
        final int tempOverlayID = 0;
        this.flagEdit.lOverlays.add(new Flag_Overlay_GameData(tempOverlayID));
        this.loadOverlayImage(tempOverlayID);
        this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iWidth = (int)Math.abs(this.getOverlay(tempOverlayID).getWidth() * this.lOverlays.get(tempOverlayID).Scale);
        this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iHeight = (int)Math.abs(this.getOverlay(tempOverlayID).getHeight() * this.lOverlays.get(tempOverlayID).Scale);
        this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iPosX = 34 - this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iWidth / 2;
        this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iPosY = 22 - this.flagEdit.lOverlays.get(this.flagEdit.lOverlays.size() - 1).iHeight / 2;
    }
    
    public final void removeOverlay(final int nID) {
        final int tempOverlayID = this.flagEdit.lOverlays.get(nID).iOverlayID;
        this.flagEdit.lOverlays.remove(nID);
        this.tryRemoveOverlay(tempOverlayID);
    }
    
    public final void moveOverlayUp(final int nID) {
        if (nID > 0) {
            final Flag_Overlay_GameData tempD = this.flagEdit.lOverlays.get(nID);
            this.flagEdit.lOverlays.set(nID, this.flagEdit.lOverlays.get(nID - 1));
            this.flagEdit.lOverlays.set(nID - 1, tempD);
        }
    }
    
    public final void loadOverlayImage(final int iOverlayID) {
        for (int i = 0; i < this.lOverlaysImages.size(); ++i) {
            if (iOverlayID == this.lOverlaysImages.get(i).iOverlayID) {
                return;
            }
        }
        this.lOverlaysImages.add(new Flag_OverlayImage(iOverlayID));
    }
    
    public final void tryRemoveOverlay(final int iOverlayID) {
        for (int i = 0; i < this.flagEdit.lOverlays.size(); ++i) {
            if (this.flagEdit.lOverlays.get(i).iOverlayID == iOverlayID) {
                return;
            }
        }
        for (int i = 0; i < this.lOverlaysImages.size(); ++i) {
            if (iOverlayID == this.lOverlaysImages.get(i).iOverlayID) {
                this.lOverlaysImages.get(i).imageOverlay.getTexture().dispose();
                this.lOverlaysImages.remove(i);
                return;
            }
        }
    }
    
    public final Image getOverlay(final int iOverlayID) {
        for (int i = 0; i < this.lOverlaysImages.size(); ++i) {
            if (iOverlayID == this.lOverlaysImages.get(i).iOverlayID) {
                return this.lOverlaysImages.get(i).imageOverlay;
            }
        }
        return Images.pix;
    }
    
    public final void loadDivisions() {
        if (this.lDivisions != null) {
            this.lDivisions.clear();
        }
        this.lDivisions = new ArrayList<Flag_Division>();
        try {
            final FileHandle fileList = FileManager.loadFile("gfx/editorFlags/divisions.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigDivisionsData.class, "Division", (Class)Data_Divisions.class);
            ConfigDivisionsData data = new ConfigDivisionsData();
            data = (ConfigDivisionsData)json.fromJson((Class)ConfigDivisionsData.class, fileContent);
            for (final Object e : data.Division) {
                final Data_Divisions tempData = (Data_Divisions)e;
                this.lDivisions.add(new Flag_Division(tempData.Name, tempData.Layers));
            }
        }
        catch (final GdxRuntimeException ex) {}
    }
    
    public final void loadOverlays() {
        if (this.lOverlays != null) {
            this.lOverlays.clear();
        }
        this.lOverlays = new ArrayList<Flag_Overlay>();
        try {
            final FileHandle fileList = FileManager.loadFile("gfx/editorFlags/overlays.json");
            final String fileContent = fileList.readString();
            final Json json = new Json();
            json.setElementType((Class)ConfigOverlayData.class, "Overlay", (Class)Data_Overlays.class);
            ConfigOverlayData data = new ConfigOverlayData();
            data = (ConfigOverlayData)json.fromJson((Class)ConfigOverlayData.class, fileContent);
            for (final Object e : data.Overlay) {
                final Data_Overlays tempData = (Data_Overlays)e;
                this.lOverlays.add(new Flag_Overlay(tempData.Name, tempData.Scale));
            }
            for (int i = 0; i < this.flagEdit.lOverlays.size(); ++i) {
                this.loadOverlayImage(this.flagEdit.lOverlays.get(i).iOverlayID);
            }
        }
        catch (final GdxRuntimeException ex) {}
    }
    
    public final void loadData() {
        this.clearData();
        this.loadDivisions();
    }
    
    public final void clearData() {
        if (this.lDivisions != null) {
            this.lDivisions.clear();
        }
        if (this.lOverlays != null) {
            this.lOverlays.clear();
        }
        for (int i = 0; i < this.divisionLayers.size(); ++i) {
            this.divisionLayers.get(i).getTexture().dispose();
        }
        this.divisionLayers.clear();
        for (int i = 0; i < this.lOverlaysImages.size(); ++i) {
            this.lOverlaysImages.get(i).imageOverlay.getTexture().dispose();
        }
        this.lOverlaysImages.clear();
    }
    
    public final void saveFlagTexture(final SpriteBatch oSB) {
        this.drawFlag(oSB, 0, 0);
        Image tempFlagImage = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));
        try {
            tempFlagImage.getTexture().getTextureData().prepare();
        }
        catch (final GdxRuntimeException ex) {}
        if (FileManager.IS_MAC) {
            PixmapIO.writePNG(Gdx.files.external("mods/GameCivs/gfx/flagsH/" + CreateCiv.nCiv.Tag + ".png"), tempFlagImage.getTexture().getTextureData().consumePixmap());
        }
        else {
            PixmapIO.writePNG(Gdx.files.local("mods/GameCivs/gfx/flagsH/" + CreateCiv.nCiv.Tag + ".png"), tempFlagImage.getTexture().getTextureData().consumePixmap());
        }
        oSB.setColor(Color.BLACK);
        Images.pix.draw(oSB, 0, 0, 68, 44);
        oSB.setColor(Color.WHITE);
        tempFlagImage.getTexture().dispose();
        tempFlagImage = null;
    }
    
    public static class ConfigDivisionsData
    {
        public String Age_of_Civilizations;
        public ArrayList Division;
    }
    
    public static class Data_Divisions
    {
        public String Name;
        public int Layers;
    }
    
    public static class ConfigOverlayData
    {
        public String Age_of_Civilizations;
        public ArrayList Overlay;
    }
    
    public static class Data_Overlays
    {
        public String Name;
        public float Scale;
    }
}
