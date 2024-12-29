// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu.Menu;

public class CloudsMenu extends Menu
{
    public static View goToView;
    public int iAnimationTime;
    public long lTime;
    public List<CloudAnimation> lClouds;
    public boolean viewChanged;
    
    public CloudsMenu() {
        this.iAnimationTime = 750;
        this.lTime = 0L;
        this.lClouds = new ArrayList<CloudAnimation>();
        this.viewChanged = false;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Empty(0, 0, 1, 1));
        this.lTime = CFG.currentTimeMillis;
        if (Game.cloudsAnimation.imageCloud.size() > 0) {
            for (int i = 0; i < CFG.GAME_WIDTH / 250; ++i) {
                final int tCloudID = Game.oR.nextInt(Game.cloudsAnimation.imageCloud.size());
                this.lClouds.add(new CloudAnimation(tCloudID, Game.oR.nextInt(CFG.GAME_WIDTH), Game.oR.nextInt(CFG.GAME_HEIGHT / 4)));
                this.lClouds.add(new CloudAnimation(tCloudID, Game.oR.nextInt(CFG.GAME_WIDTH), CFG.GAME_HEIGHT - Game.oR.nextInt(CFG.GAME_HEIGHT / 4)));
                this.lClouds.add(new CloudAnimation(tCloudID, Game.oR.nextInt(CFG.GAME_WIDTH / 4), Game.oR.nextInt(CFG.GAME_HEIGHT)));
                this.lClouds.add(new CloudAnimation(tCloudID, CFG.GAME_WIDTH - Game.oR.nextInt(CFG.GAME_WIDTH / 4), Game.oR.nextInt(CFG.GAME_HEIGHT)));
            }
            for (int i = 0; i < CFG.GAME_WIDTH / 600; ++i) {
                final int tCloudID = Game.oR.nextInt(Game.cloudsAnimation.imageCloud.size());
                this.lClouds.add(new CloudAnimation(tCloudID, Game.oR.nextInt(CFG.GAME_WIDTH), Game.oR.nextInt(CFG.GAME_HEIGHT)));
            }
        }
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        final float fPos;
        float fAlpha = fPos = Math.min(1.0f, (CFG.currentTimeMillis - this.lTime) / (float)this.iAnimationTime);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f * fAlpha));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT / 2);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.BUTTON_HEIGHT / 2 + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT / 2, false, true);
        oSB.setColor(Color.WHITE);
        fAlpha = 1.0f - Math.min(1.0f, (CFG.currentTimeMillis - this.lTime) / (float)this.iAnimationTime);
        final int gradientH = (int)(CFG.GAME_HEIGHT * 0.2f * fAlpha);
        for (int i = this.lClouds.size() - 1; i >= 0; --i) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.lClouds.get(i).fAlpha * fAlpha));
            if (this.lClouds.get(i).iPosX < CFG.GAME_WIDTH / 2) {
                Game.cloudsAnimation.imageCloud.get(this.lClouds.get(i).iCloudID).draw(oSB, this.lClouds.get(i).iPosX - (int)(CFG.GAME_WIDTH / 10 * fPos), this.lClouds.get(i).iPosY, 1.0f, this.lClouds.get(i).fRotation);
            }
            else {
                Game.cloudsAnimation.imageCloud.get(this.lClouds.get(i).iCloudID).draw(oSB, this.lClouds.get(i).iPosX + (int)(CFG.GAME_WIDTH / 10 * fPos), this.lClouds.get(i).iPosY, 1.0f, this.lClouds.get(i).fRotation);
            }
        }
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        if (CFG.currentTimeMillis - this.lTime > this.iAnimationTime && !this.viewChanged) {
            this.lClouds.clear();
            Game.menuManager.setViewID(CloudsMenu.goToView);
            this.viewChanged = true;
        }
    }
    
    static {
        CloudsMenu.goToView = View.NEW_GAME;
    }
    
    public class CloudAnimation
    {
        int iCloudID;
        int iPosX;
        int iPosY;
        float fAlpha;
        float fRotation;
        
        public CloudAnimation(final int iCloudID, final int iPosX, final int iPosY) {
            this.iCloudID = iCloudID;
            this.iPosX = iPosX - Game.cloudsAnimation.imageCloud.get(iCloudID).getWidth() / 2;
            this.iPosY = iPosY - Game.cloudsAnimation.imageCloud.get(iCloudID).getHeight() / 2;
            this.fAlpha = 0.1f + Game.oR.nextInt(250) / 1000.0f;
            this.fRotation = (float)Game.oR.nextInt(360);
        }
    }
}
