// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRect_Active_Value;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRect_Active;
import aoh.kingdoms.history.menu_element.Slider;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Audio extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_Audio() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING;
        final int buttonX = paddingLeft;
        menuElements.add(new Slider(Game.lang.get("MasterVolume") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT4, 0, 100, (int)(SoundsManager.masterVolume * 100.0f)) {
            @Override
            public void actionElement() {
                Game.settingsManager.VOLUME_MASTER = this.getCurrent() / 100.0f;
                Game.soundsManager.setMasterVolume(this.getCurrent() / 100.0f);
            }
            
            @Override
            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new ButtonStatsRect_Active(">>", buttonX + menuWidth - paddingLeft * 2 - CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT4) {
            @Override
            public void actionElement() {
                Game.soundsManager.loadNextMusic();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Next"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NowPlaying") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.soundsManager.getCurrentMusicTittle(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("MusicVolume") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 0, 100, (int)(SoundsManager.musicVolume * 100.0f)) {
            @Override
            public void actionElement() {
                Game.settingsManager.VOLUME_MUSIC = this.getCurrent() / 100.0f;
                Game.soundsManager.setMusicVolume(this.getCurrent() / 100.0f);
            }
            
            @Override
            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("AmbienceVolume") + ": ", paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT4, 0, 100, (int)(SoundsManager.ambienceVolume * 100.0f)) {
            @Override
            public void actionElement() {
                Game.settingsManager.VOLUME_AMBIENCE = this.getCurrent() / 100.0f;
                Game.soundsManager.setAmbienceVolume(this.getCurrent() / 100.0f);
            }
            
            @Override
            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Slider(Game.lang.get("EffectVolume") + ": ", paddingLeft + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT4, 0, 100, (int)(SoundsManager.soundsVolume * 100.0f)) {
            @Override
            public void actionElement() {
                Game.settingsManager.VOLUME_SOUNDS = this.getCurrent() / 100.0f;
                Game.soundsManager.setSoundsVolume(this.getCurrent() / 100.0f);
            }
            
            @Override
            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        for (int i = 0; i < Game.soundsManager.lTitles.size(); ++i) {
            menuElements.add(new ButtonStatsRect_Active_Value(Game.soundsManager.lTitles.get(i).replace("_", " "), paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, i) {
                @Override
                public void actionElement() {
                    Game.soundsManager.loadNextMusic(Game.soundsManager.lTitles.get(this.getCurrent()), this.getCurrent());
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    return (this.getCurrent() == Game.soundsManager.iCurrentMusicID) ? Colors.HOVER_GOLD : super.getColor(isActive);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Audio"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Audio.lTime;
            }
        }, CFG.GAME_WIDTH - menuWidth - CFG.GAME_HEIGHT / 8, CFG.GAME_HEIGHT / 8, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Audio.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_Audio.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Audio.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_Audio.lTime = 0L;
    }
}
