// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Renderer;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.menus.Settings.Settings_Menu;
import aoc.kingdoms.lukasz.map.SiegeManager;
import aoc.kingdoms.lukasz.map.province.ProvinceNamesManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager;
import aoc.kingdoms.lukasz.map.map.Ship.ShipManager;
import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvincesBG;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.List;

public class RendererGame
{
    public static List<RendererAnimationNuke> animationNukes;
    public static int iAnimationNukesSize;
    public static RendererGameINT rendererGame;
    
    public static final void updateRenderer() {
        if (Game.menuManager.getInInitGameMenu() || Game.menuManager.getInLoadScenario() || Game.menuManager.getInInitGame_Menus()) {
            RendererGame.rendererGame = new RendererGameINT() {
                @Override
                public void drawCurrentScale(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawWithoutScale(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawCurrentScale_Provinces(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawWithoutScale_Provinces(final SpriteBatch oSB) {
                }
            };
        }
        else if (Game.menuManager.getInGame()) {
            RendererGame.rendererGame = new RendererGameINT() {
                @Override
                public void drawCurrentScale_Provinces(final SpriteBatch oSB) {
                    ProvinceDraw.drawProvinces(oSB);
                }
                
                @Override
                public void drawWithoutScale_Provinces(final SpriteBatch oSB) {
                    if (Game.settingsManager.FBO_PROVINCES && FBOProvincesBG.textureProvince_PBG != null && Game.menuManager.getInGame() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        FBOProvincesBG.drawPBG(oSB, ProvinceDraw.getLandProvinces_Alpha());
                    }
                    ProvinceDraw.drawDiplomacyLines_Just(oSB, Game.mapScale.getCurrentScale());
                    ProvinceDraw.drawProvinceDots_Just(oSB, Game.mapScale.getCurrentScale());
                }
                
                @Override
                public void drawCurrentScale(final SpriteBatch oSB) {
                    try {
                        if (Game.settingsManager.FBO_PROVINCES && Game.menuManager.getInGame() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            ProvinceDraw.drawOccupiedProvinces(oSB);
                        }
                        Game.drawActiveProvince(oSB);
                        if (Game.mapScale.getCurrentScale() < 1.0f && Game.mapScale.getCurrentScale() > Game.cloudsAnimation.cloudsSettings.drawCloudsMinScale) {
                            ShipManager.drawCurrentScale(oSB);
                        }
                        ProvinceDraw.drawProvincesBorder(oSB);
                        CivilizationRegionsManager.drawCivNames(oSB);
                        Game.cloudsAnimation.cloudsInterface.drawCloudsInterface(oSB);
                        Game.map.drawMapBorder(oSB);
                        Game.animationManager.draw(oSB);
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                
                @Override
                public void drawWithoutScale(final SpriteBatch oSB) {
                    try {
                        if (Game.mapScale.getCurrentScale() >= 1.0f) {
                            ShipManager.draw(oSB);
                        }
                        ProvinceDrawArmy.updateDrawArmyAlpha();
                        ProvinceNamesManager.drawProvNames(oSB);
                        ProvinceDraw.drawSiegeLines_Just(oSB, Game.mapScale.getCurrentScale());
                        ProvinceDraw.drawBiggestCitiesLines_Just(oSB, Game.mapScale.getCurrentScale());
                        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_ECONOMY_HOVER) {
                            Game.mapCities.drawCities(oSB, Game.mapScale.getCurrentScale());
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS) {
                            Game.mapCities.drawCities(oSB, Game.mapScale.getCurrentScale());
                        }
                        else {
                            Game.mapCities.citiesInGame.draw(oSB, Game.mapScale.getCurrentScale());
                        }
                        ProvinceDraw.oDrawMoveUnits.drawMoveUnits(oSB);
                        Game.animationManager.drawScaled(oSB);
                        try {
                            ProvinceDraw.drawExtraDetails.draw(oSB);
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                        if (Renderer.drawArmyInProvince) {
                            ProvinceDrawArmy.drawProvincesArmy(oSB);
                            SiegeManager.draw(oSB);
                            Game.battleManager.draw(oSB);
                        }
                        else {
                            ProvinceDrawArmy.drawMapModeDetails(oSB);
                        }
                        Game.mapTouchManager.drawSelectMode(oSB);
                        RendererGame.drawNukeAnimation(oSB);
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            };
        }
        else if (Game.menuManager.getInSettingsMenu()) {
            RendererGame.rendererGame = new RendererGameINT() {
                @Override
                public void drawCurrentScale_Provinces(final SpriteBatch oSB) {
                    if (CFG.currentTimeMillis - Settings_Menu.lastUpdateTime > GameValues.value.SETTINGS_MENU_UPDATE_TIME) {
                        Settings_Menu.lastUpdateTime = CFG.currentTimeMillis;
                        Settings_Menu.updateTimes = true;
                    }
                    ProvinceDraw.drawProvinces_Settings(oSB);
                }
                
                @Override
                public void drawWithoutScale_Provinces(final SpriteBatch oSB) {
                    final long time = System.nanoTime();
                    if (Game.settingsManager.FBO_PROVINCES && FBOProvincesBG.textureProvince_PBG != null && Game.menuManager.getInGame() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        FBOProvincesBG.drawPBG(oSB, ProvinceDraw.getLandProvinces_Alpha());
                    }
                    if (Settings_Menu.updateTimes) {
                        Settings_Menu.drawProvincesFBO_Time = System.nanoTime() - time;
                    }
                    ProvinceDraw.drawDiplomacyLines_Just(oSB, Game.mapScale.getCurrentScale());
                    ProvinceDraw.drawProvinceDots_Just(oSB, Game.mapScale.getCurrentScale());
                }
                
                @Override
                public void drawCurrentScale(final SpriteBatch oSB) {
                    try {
                        if (Game.settingsManager.FBO_PROVINCES && Game.menuManager.getInGame() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            ProvinceDraw.drawOccupiedProvinces(oSB);
                        }
                        Game.drawActiveProvince(oSB);
                        long time = System.nanoTime();
                        if (Game.mapScale.getCurrentScale() < 1.0f && Game.mapScale.getCurrentScale() > Game.cloudsAnimation.cloudsSettings.drawCloudsMinScale) {
                            ShipManager.drawCurrentScale(oSB);
                        }
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawShips_Time = System.nanoTime() - time;
                            time = System.nanoTime();
                        }
                        ProvinceDraw.drawProvincesBorder(oSB);
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawProvincesBorder_Time = System.nanoTime() - time;
                            time = System.nanoTime();
                        }
                        CivilizationRegionsManager.drawCivNames(oSB);
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawCivsNames_Time = System.nanoTime() - time;
                            time = System.nanoTime();
                        }
                        Game.cloudsAnimation.cloudsInterface.drawCloudsInterface(oSB);
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawClouds_Time = System.nanoTime() - time;
                            time = System.nanoTime();
                        }
                        Game.map.drawMapBorder(oSB);
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawMapBorder_Time = System.nanoTime() - time;
                            time = System.nanoTime();
                        }
                        Game.animationManager.draw(oSB);
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                
                @Override
                public void drawWithoutScale(final SpriteBatch oSB) {
                    try {
                        long time = System.nanoTime();
                        if (Game.mapScale.getCurrentScale() >= 1.0f) {
                            ShipManager.draw(oSB);
                        }
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawShips2_Time = System.nanoTime() - time;
                        }
                        ProvinceDrawArmy.updateDrawArmyAlpha();
                        time = System.nanoTime();
                        ProvinceNamesManager.drawProvNames(oSB);
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawProvincesNames_Time = System.nanoTime() - time;
                        }
                        ProvinceDraw.drawSiegeLines_Just(oSB, Game.mapScale.getCurrentScale());
                        ProvinceDraw.drawBiggestCitiesLines_Just(oSB, Game.mapScale.getCurrentScale());
                        time = System.nanoTime();
                        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_ECONOMY_HOVER) {
                            Game.mapCities.drawCities(oSB, Game.mapScale.getCurrentScale());
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS) {
                            Game.mapCities.drawCities(oSB, Game.mapScale.getCurrentScale());
                        }
                        else {
                            Game.mapCities.citiesInGame.draw(oSB, Game.mapScale.getCurrentScale());
                        }
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawCities_Time = System.nanoTime() - time;
                            time = System.nanoTime();
                        }
                        ProvinceDraw.oDrawMoveUnits.drawMoveUnits(oSB);
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawMoveUnits_Time = System.nanoTime() - time;
                            Game.animationManager.drawScaled(oSB);
                        }
                        try {
                            ProvinceDraw.drawExtraDetails.draw(oSB);
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                        time = System.nanoTime();
                        if (Renderer.drawArmyInProvince) {
                            ProvinceDrawArmy.drawProvincesArmy(oSB);
                            SiegeManager.draw(oSB);
                            Game.battleManager.draw(oSB);
                        }
                        else {
                            ProvinceDrawArmy.drawMapModeDetails(oSB);
                        }
                        if (Settings_Menu.updateTimes) {
                            Settings_Menu.drawArmies_Time = System.nanoTime() - time;
                        }
                        Game.mapTouchManager.drawSelectMode(oSB);
                        RendererGame.drawNukeAnimation(oSB);
                        Settings_Menu.updateTimes = false;
                    }
                    catch (final Exception ex2) {
                        CFG.exceptionStack(ex2);
                    }
                }
            };
        }
        else if (Game.menuManager.getInMainMenu() || Game.menuManager.getInScenarios_NewGame() || Game.menuManager.getInLoadGamesList() || Game.menuManager.getInGameLegacies() || Game.menuManager.getInNewGame_Scenarios() || Game.menuManager.getInNewGame_ScenariosCampaign()) {
            RendererGame.rendererGame = new RendererGameINT() {
                @Override
                public void drawCurrentScale_Provinces(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawWithoutScale_Provinces(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawCurrentScale(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawWithoutScale(final SpriteBatch oSB) {
                }
            };
        }
        else if (Game.menuManager.getInMapEditorProvinceNamePoints()) {
            RendererGame.rendererGame = new RendererGameINT() {
                @Override
                public void drawCurrentScale_Provinces(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawWithoutScale_Provinces(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawCurrentScale(final SpriteBatch oSB) {
                    ProvinceDraw.drawProvinces(oSB);
                    Game.drawActiveProvince(oSB);
                    ProvinceDraw.drawProvincesBorder(oSB);
                    if (Game.iActiveProvince >= 0) {
                        ProvinceNamesManager.drawProvNamePoints(oSB, Game.iActiveProvince);
                    }
                    Game.map.drawMapBorder(oSB);
                    Game.animationManager.draw(oSB);
                }
                
                @Override
                public void drawWithoutScale(final SpriteBatch oSB) {
                    defaultDrawWithoutScale(oSB);
                }
            };
        }
        else if (Game.menuManager.getInNewGame() || Game.menuManager.getInCloudsMenu() || Game.menuManager.getInGameLost()) {
            RendererGame.rendererGame = new RendererGameINT() {
                @Override
                public void drawCurrentScale_Provinces(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawWithoutScale_Provinces(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawCurrentScale(final SpriteBatch oSB) {
                    ProvinceDraw.drawProvinces(oSB);
                    Game.drawActiveProvince(oSB);
                    ProvinceDraw.drawProvincesBorder(oSB);
                    CivilizationRegionsManager.drawCivNames(oSB);
                    Game.cloudsAnimation.cloudsInterface.drawCloudsInterface(oSB);
                    Game.map.drawMapBorder(oSB);
                }
                
                @Override
                public void drawWithoutScale(final SpriteBatch oSB) {
                    ProvinceDrawArmy.updateDrawArmyAlpha();
                    ProvinceNamesManager.drawProvNames(oSB);
                    Game.mapCities.drawCities_HighAllTheTime(oSB, Game.mapScale.getCurrentScale());
                    Game.mapTouchManager.drawSelectMode(oSB);
                }
            };
        }
        else {
            RendererGame.rendererGame = new RendererGameINT() {
                @Override
                public void drawCurrentScale_Provinces(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawWithoutScale_Provinces(final SpriteBatch oSB) {
                }
                
                @Override
                public void drawCurrentScale(final SpriteBatch oSB) {
                    ProvinceDraw.drawProvinces(oSB);
                    Game.drawActiveProvince(oSB);
                    ProvinceDraw.drawProvincesBorder(oSB);
                    CivilizationRegionsManager.drawCivNames(oSB);
                    Game.cloudsAnimation.cloudsInterface.drawCloudsInterface(oSB);
                    Game.map.drawMapBorder(oSB);
                }
                
                @Override
                public void drawWithoutScale(final SpriteBatch oSB) {
                    defaultDrawWithoutScale(oSB);
                }
            };
        }
    }
    
    public static final void addNuke(final int iProvinceID) {
        RendererGame.animationNukes.add(new RendererAnimationNuke(iProvinceID));
        RendererGame.iAnimationNukesSize = RendererGame.animationNukes.size();
    }
    
    public static final void drawNukeAnimation(final SpriteBatch oSB) {
        try {
            for (int i = RendererGame.iAnimationNukesSize - 1; i >= 0; --i) {
                RendererGame.animationNukes.get(i).draw(oSB);
                if (RendererGame.animationNukes.get(i).remove) {
                    RendererGame.animationNukes.remove(i);
                    RendererGame.iAnimationNukesSize = RendererGame.animationNukes.size();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private static final void defaultDrawWithoutScale(final SpriteBatch oSB) {
        ProvinceDrawArmy.updateDrawArmyAlpha();
        ProvinceNamesManager.drawProvNames(oSB);
        Game.mapCities.drawCities(oSB, Game.mapScale.getCurrentScale());
        ProvinceDraw.oDrawMoveUnits.drawMoveUnits(oSB);
        if (Renderer.drawArmyInProvince) {
            ProvinceDrawArmy.drawProvincesArmy(oSB);
        }
        else {
            ProvinceDrawArmy.drawMapModeDetails(oSB);
        }
        Game.mapTouchManager.drawSelectMode(oSB);
    }
    
    public static final void drawCapitalFlags(final SpriteBatch oSB, final float nScale) {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getProvince(Game.getCiv(i).getCapitalProvinceID()).getDrawProvince()) {
                drawCapitalFlag(oSB, Game.getCiv(i).getCapitalProvinceID(), nScale, 1.0f);
            }
        }
    }
    
    private static final void drawCapitalFlag(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha) {
        final int nPosX = getDrawPosX(nProvinceID, nScale);
        final int nPosY = getDrawPosY(nProvinceID, nScale);
        oSB.setColor(Color.WHITE);
        final int flagImgID = 0;
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.flagMask.get(flagImgID)).draw(oSB, nPosX - 1, nPosY - 1, 70, 46);
        oSB.setColor(Color.WHITE);
        oSB.setShader(Renderer.shaderAlpha);
        Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getFlag().getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.flagMask.get(flagImgID)).draw(oSB, nPosX, nPosY, 68, 44);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.flagOver.get(flagImgID)).draw(oSB, nPosX - 1, nPosY - 1, 70, 46);
    }
    
    public static final int getDrawPosX(final int nProvinceID, final float nScale) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftX + Game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
    }
    
    public static final int getDrawPosY(final int nProvinceID, final float nScale) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftY + Game.mapCoords.getPosY()) * nScale);
    }
    
    static {
        RendererGame.animationNukes = new ArrayList<RendererAnimationNuke>();
        RendererGame.iAnimationNukesSize = 0;
        RendererGame.rendererGame = new RendererGameINT() {
            @Override
            public void drawCurrentScale_Provinces(final SpriteBatch oSB) {
            }
            
            @Override
            public void drawWithoutScale_Provinces(final SpriteBatch oSB) {
            }
            
            @Override
            public void drawCurrentScale(final SpriteBatch oSB) {
            }
            
            @Override
            public void drawWithoutScale(final SpriteBatch oSB) {
            }
        };
    }
    
    interface RendererGameINT
    {
        void drawCurrentScale(final SpriteBatch p0);
        
        void drawWithoutScale(final SpriteBatch p0);
        
        void drawCurrentScale_Provinces(final SpriteBatch p0);
        
        void drawWithoutScale_Provinces(final SpriteBatch p0);
    }
}
