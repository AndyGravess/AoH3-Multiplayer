// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.LoadSave;

import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menus.InitGame;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu.Menu;

public class Menu_Load_Workshop extends Menu
{
    public int iStepID;
    public int iNumOfSteps;
    public static View goToMenu;
    public static boolean uploaded;
    public String loadingName;
    
    public Menu_Load_Workshop() {
        this.iStepID = 0;
        this.iNumOfSteps = 3600;
        this.loadingName = "";
        this.iStepID = 0;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        this.setLoadText(Game.lang.get("Progress") + " #0");
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        this.loadAction();
        if (InitGame.background != null) {
            oSB.setColor(new Color(0.047058824f, 0.047058824f, 0.047058824f, 1.0f));
            Images.pix.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.iStepID / (float)this.iNumOfSteps * 0.25f));
            InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.95f + this.iStepID / (float)this.iNumOfSteps * 0.05f));
            oSB.setShader(Renderer.shaderAlpha);
            InitGame.background.getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            Images.gradientXY.draw(oSB, this.getPosX() + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, this.getPosY() + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        Renderer.drawLoading(oSB, iTranslateX, iTranslateY, this.iStepID % 3600 / (float)this.iNumOfSteps);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.loadingName, iTranslateX + CFG.PADDING * 4, iTranslateY + CFG.PADDING * 4, new Color(1.0f, 1.0f, 1.0f, 0.15f));
    }
    
    public void setLoadText(final String sText) {
        this.loadingName = sText;
    }
    
    public final void loadAction() {
        try {
            this.setLoadText(Game.lang.get("Progress") + " #" + this.iStepID);
            if (Menu_Load_Workshop.uploaded) {
                Game.menuManager.setViewIDWithoutAnimation(Menu_Load_Workshop.goToMenu);
            }
        }
        catch (final Exception ex) {
            CFG.LOG(Game.lang.get("Workshop") + ": " + this.iStepID);
            CFG.exceptionStack(ex);
        }
        ++this.iStepID;
    }
    
    static {
        Menu_Load_Workshop.goToMenu = View.WORKSHOP;
        Menu_Load_Workshop.uploaded = false;
    }
}
