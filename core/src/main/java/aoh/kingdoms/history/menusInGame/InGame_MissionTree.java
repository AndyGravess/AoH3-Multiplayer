// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import com.badlogic.gdx.utils.Array;
import space.earlygrey.shapedrawer.JoinType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.menus.InitGame;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleTechTree;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonMissionTree;
import aoh.kingdoms.history.menu_element.button.ButtonMissionTreeCiv;
import aoh.kingdoms.history.mainGame.Missions.Mission;
import aoh.kingdoms.history.mainGame.Missions.MissionTree;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.mainGame.Renderer.SparksAnimation;
import aoh.kingdoms.history.menu.Menu;

public class InGame_MissionTree extends Menu
{
    public static SparksAnimation sparksAnimation;
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public int iMapPosY;
    public List<TechLine> lines;
    public int iLinesSize;
    
    public InGame_MissionTree() {
        this.iMapPosY = 0;
        this.lines = new ArrayList<TechLine>();
        this.iLinesSize = 0;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int paddingTop = CFG.PADDING * 4;
        final int titleHeight = ImageManager.getImage(Images.titleTechTree).getHeight();
        final int menuWidth = CFG.GAME_WIDTH;
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title928).getHeight();
        int buttonY = paddingTop;
        final int buttonX = paddingLeft;
        final int centerToPosX = 0;
        if (Game.getCiv(Game.player.iCivID).iMissionsSize > 0) {
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).iMissionsSize; ++i) {
                final boolean missionUnlocked = MissionTree.haveUnlockedMission_Civ(Game.player.iCivID, i);
                final boolean missionCanBeUnlocked = MissionTree.canRunMission_Civ(Game.player.iCivID, i);
                menuElements.add(new ButtonMissionTreeCiv(i, Game.getCiv(Game.player.iCivID).lMissions.get(i).ImageID, 100 + (MissionTree.iMissionWidth + 100) * Game.getCiv(Game.player.iCivID).lMissions.get(i).TreeColumn, 15 + (MissionTree.iMissionHeight + 15) * Game.getCiv(Game.player.iCivID).lMissions.get(i).TreeRow, missionUnlocked, missionCanBeUnlocked));
                if (Game.getCiv(Game.player.iCivID).lMissions.get(i).RequiredMission >= 0) {
                    this.lines.add(new TechLine(i, 100 + MissionTree.iMissionWidth / 2 + (MissionTree.iMissionWidth + 100) * Game.getCiv(Game.player.iCivID).lMissions.get(Game.getCiv(Game.player.iCivID).lMissions.get(i).RequiredMission).TreeColumn, 15 + MissionTree.iMissionHeight / 2 + (MissionTree.iMissionHeight + 15) * Game.getCiv(Game.player.iCivID).lMissions.get(Game.getCiv(Game.player.iCivID).lMissions.get(i).RequiredMission).TreeRow, 100 + MissionTree.iMissionWidth / 2 + (MissionTree.iMissionWidth + 100) * Game.getCiv(Game.player.iCivID).lMissions.get(i).TreeColumn, 15 + MissionTree.iMissionHeight / 2 + (MissionTree.iMissionHeight + 15) * Game.getCiv(Game.player.iCivID).lMissions.get(i).TreeRow, missionUnlocked));
                }
                if (Game.getCiv(Game.player.iCivID).lMissions.get(i).RequiredMission2 >= 0) {
                    this.lines.add(new TechLine(i, 100 + MissionTree.iMissionWidth / 2 + (MissionTree.iMissionWidth + 100) * Game.getCiv(Game.player.iCivID).lMissions.get(Game.getCiv(Game.player.iCivID).lMissions.get(i).RequiredMission2).TreeColumn, 15 + MissionTree.iMissionHeight / 2 + (MissionTree.iMissionHeight + 15) * Game.getCiv(Game.player.iCivID).lMissions.get(Game.getCiv(Game.player.iCivID).lMissions.get(i).RequiredMission2).TreeRow, 100 + MissionTree.iMissionWidth / 2 + (MissionTree.iMissionWidth + 100) * Game.getCiv(Game.player.iCivID).lMissions.get(i).TreeColumn, 15 + MissionTree.iMissionHeight / 2 + (MissionTree.iMissionHeight + 15) * Game.getCiv(Game.player.iCivID).lMissions.get(i).TreeRow, missionUnlocked));
                }
            }
        }
        else {
            for (int i = 0; i < MissionTree.iMissionsSize; ++i) {
                final boolean missionUnlocked = MissionTree.haveUnlockedMission(Game.player.iCivID, i);
                final boolean missionCanBeUnlocked = MissionTree.canRunMission(Game.player.iCivID, i);
                menuElements.add(new ButtonMissionTree(i, MissionTree.lMissions.get(i).ImageID, 100 + (MissionTree.iMissionWidth + 100) * MissionTree.lMissions.get(i).TreeColumn, 15 + (MissionTree.iMissionHeight + 15) * MissionTree.lMissions.get(i).TreeRow, missionUnlocked, missionCanBeUnlocked));
                if (MissionTree.lMissions.get(i).RequiredMission >= 0) {
                    this.lines.add(new TechLine(i, 100 + MissionTree.iMissionWidth / 2 + (MissionTree.iMissionWidth + 100) * MissionTree.lMissions.get(MissionTree.lMissions.get(i).RequiredMission).TreeColumn, 15 + MissionTree.iMissionHeight / 2 + (MissionTree.iMissionHeight + 15) * MissionTree.lMissions.get(MissionTree.lMissions.get(i).RequiredMission).TreeRow, 100 + MissionTree.iMissionWidth / 2 + (MissionTree.iMissionWidth + 100) * MissionTree.lMissions.get(i).TreeColumn, 15 + MissionTree.iMissionHeight / 2 + (MissionTree.iMissionHeight + 15) * MissionTree.lMissions.get(i).TreeRow, missionUnlocked));
                }
                if (MissionTree.lMissions.get(i).RequiredMission2 >= 0) {
                    this.lines.add(new TechLine(i, 100 + MissionTree.iMissionWidth / 2 + (MissionTree.iMissionWidth + 100) * MissionTree.lMissions.get(MissionTree.lMissions.get(i).RequiredMission2).TreeColumn, 15 + MissionTree.iMissionHeight / 2 + (MissionTree.iMissionHeight + 15) * MissionTree.lMissions.get(MissionTree.lMissions.get(i).RequiredMission2).TreeRow, 100 + MissionTree.iMissionWidth / 2 + (MissionTree.iMissionWidth + 100) * MissionTree.lMissions.get(i).TreeColumn, 15 + MissionTree.iMissionHeight / 2 + (MissionTree.iMissionHeight + 15) * MissionTree.lMissions.get(i).TreeRow, missionUnlocked));
                }
            }
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        int tMaxX = 0;
        for (int j = 0, iSize2 = menuElements.size(); j < iSize2; ++j) {
            if (menuElements.get(j).getPosX() + menuElements.get(j).getWidth() > tMaxX) {
                tMaxX = menuElements.get(j).getPosX() + menuElements.get(j).getWidth();
            }
        }
        tMaxX += 100;
        menuElements.add(new Empty(0, 0, tMaxX, buttonY));
        buttonY = (int)Math.max((float)buttonY, (MissionTree.iMissionHeight + 15) * (GameValues.inGame.TECH_TREE_NUM_OF_ROWS + 0.25f) + paddingTop * 2);
        this.initMenu(new MenuTitleTechTree(Game.getCiv(Game.player.iCivID).getCivName() + ": " + Game.lang.get("Missions"), titleHeight, false, false) {
            @Override
            public long getTime() {
                return InGame_MissionTree.lTime;
            }
            
            @Override
            public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
                this.draw2(oSB, nPosX, nPosY, nWidth, titleStatus);
            }
        }, 0, CFG.GAME_HEIGHT / 2 - tMenuHeight / 2, menuWidth, tMenuHeight, menuElements, false, true);
        this.iMapPosY = (int)(Math.max(0, Game.mapBG.iHeightOfMapTech - CFG.GAME_HEIGHT) * (Game.oR.nextInt(100) / 100.0f));
        this.iLinesSize = this.lines.size();
        if (centerToPosX != 0) {
            this.setMenuPosX(-centerToPosX + CFG.GAME_WIDTH / 4);
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        float fA = 1.0f;
        if (InGame_MissionTree.lTime + 60L >= CFG.currentTimeMillis) {
            fA = (CFG.currentTimeMillis - InGame_MissionTree.lTime) / 60.0f;
        }
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG.r, Colors.COLOR_GRADIENT_BG.g, Colors.COLOR_GRADIENT_BG.b, 0.6f * fA));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, this.getPosY() - this.getTitle().getHeight() - CFG.BUTTON_HEIGHT2 / 2 + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT2 / 2, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT2 / 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG.r, Colors.COLOR_GRADIENT_BG.g, Colors.COLOR_GRADIENT_BG.b, 0.3f * fA));
        Images.gradientXY.draw(oSB, iTranslateX, this.getPosY() - this.getTitle().getHeight() - CFG.BUTTON_HEIGHT2 / 2 + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT2 / 2);
        Images.gradientXY.draw(oSB, iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT2 / 2, false, true);
        oSB.setColor(Color.WHITE);
        if (InGame_MissionTree.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_MissionTree.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        ImageManager.getImage(Images.insideTechTree).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, true);
        Renderer.clipView_Start(oSB, this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - (this.getPosY() + iTranslateY), this.getWidth(), -this.getHeight());
        Renderer.setShaderWater(oSB);
        ImageManager.getImage(Images.waves).draw2(oSB, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, -this.getMenuPosX(), -this.getMenuPosY());
        Renderer.setShaderDefault(oSB);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
        oSB.setShader(Renderer.shaderAlpha);
        InitGame.background.getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        Images.gradientFull.draw(oSB, (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
        Images.gradientXY.draw(oSB, (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(Color.WHITE);
        Renderer.clipView_End(oSB);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.25f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() - 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 1.0f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() - 1, false, true);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.35f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() - 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 4);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 4, false, true);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
        InGame_MissionTree.sparksAnimation.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - Images.sparkHeight + iTranslateY, this.getWidth(), Images.sparkHeight);
        oSB.setColor(Color.WHITE);
        for (int i = 0; i < this.iLinesSize; ++i) {
            this.lines.get(i).lPointsX.get(0);
            for (int j = 0, jSize = this.lines.get(i).lPointsX.size(); j < jSize; ++j) {
                ((Vector2)this.lines.get(i).nPath.get(j)).x = (float)(this.getPosX() + this.getMenuPosX() + iTranslateX + this.lines.get(i).lPointsX.get(j));
                ((Vector2)this.lines.get(i).nPath.get(j)).y = (float)(-(this.getMenuPosY() + iTranslateY + this.lines.get(i).lPointsY.get(j)));
            }
            Renderer.shapeDrawer.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            Renderer.shapeDrawer.path((Iterable)this.lines.get(i).nPath, 3.0f, JoinType.SMOOTH, true);
            if (this.lines.get(i).unlocked) {
                Renderer.shapeDrawer.setColor(new Color(0.39215687f, 0.33333334f, 0.19607843f, 1.0f));
            }
            else {
                Renderer.shapeDrawer.setColor(new Color(0.3137255f, 0.3137255f, 0.3137255f, 1.0f));
            }
            Renderer.shapeDrawer.path((Iterable)this.lines.get(i).nPath, 1.0f, JoinType.SMOOTH, true);
        }
        Renderer.oSBBorder.end();
        Renderer.oSBBorder.begin();
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_MissionTree.lTime = CFG.currentTimeMillis;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_TechnologyTree();
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.menuManager.setVisibleInGame_TechnologyTree(false);
    }
    
    static {
        InGame_MissionTree.sparksAnimation = new SparksAnimation();
        InGame_MissionTree.lTime = 0L;
    }
    
    public class TechLine
    {
        public boolean unlocked;
        public int iMissionID;
        private Array<Vector2> nPath;
        public List<Integer> lPointsX;
        public List<Integer> lPointsY;
        
        public TechLine(final int iMissionID, final int iX, final int iY, final int iX2, final int iY2, final boolean unlocked) {
            this.unlocked = false;
            this.nPath = (Array<Vector2>)new Array();
            this.lPointsX = new ArrayList<Integer>();
            this.lPointsY = new ArrayList<Integer>();
            this.iMissionID = iMissionID;
            this.unlocked = unlocked;
            this.lPointsX.add(iX);
            this.lPointsY.add(iY);
            if (iY != iY2) {
                final int mod = (iY > iY2) ? -1 : 1;
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 - 8);
                this.lPointsY.add(iY);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 - 6);
                this.lPointsY.add(iY + 1 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 - 4);
                this.lPointsY.add(iY + 2 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 - 2);
                this.lPointsY.add(iY + 4 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 - 1);
                this.lPointsY.add(iY + 6 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50);
                this.lPointsY.add(iY + 8 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50);
                this.lPointsY.add(iY2 - 8 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 + 1);
                this.lPointsY.add(iY2 - 6 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 + 2);
                this.lPointsY.add(iY2 - 4 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 + 4);
                this.lPointsY.add(iY2 - 2 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 + 6);
                this.lPointsY.add(iY2 - 1 * mod);
                this.lPointsX.add(iX2 - MissionTree.iMissionWidth / 2 - 50 + 8);
                this.lPointsY.add(iY2);
            }
            this.lPointsX.add(iX2);
            this.lPointsY.add(iY2);
            for (int i = this.lPointsX.size() - 1; i >= 0; --i) {
                this.nPath.add((Object)new Vector2(0.0f, 0.0f));
            }
        }
    }
}
