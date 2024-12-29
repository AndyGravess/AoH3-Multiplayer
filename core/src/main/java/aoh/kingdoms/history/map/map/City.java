// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.map.province.ProvinceNamesManager;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.textures.Image;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game;

public class City
{
    public int iPosX;
    public int iPosY;
    public String sCityName;
    public String sCityNameOriginal;
    public int iWidth;
    public int iHeight;
    public int iCityLevel;
    public int imageID;
    public static final int PADDING = 10;
    public static final int PADDING_NAME = 5;
    
    public City(final String sName, final int nPosX, final int nPosY, final int iCityLevel) {
        this.sCityName = null;
        this.sCityNameOriginal = null;
        this.iWidth = 0;
        this.iHeight = 0;
        this.iCityLevel = 0;
        this.imageID = 0;
        this.sCityName = sName;
        this.sCityNameOriginal = sName;
        this.updateCityNameWidth();
        this.iPosX = nPosX;
        this.iPosY = nPosY;
        this.iCityLevel = iCityLevel;
        this.imageID = Game.oR.nextInt(Game.mapCities.imageCity.size());
    }
    
    public final void drawCity(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final float fAlpha2, final float fontScale) {
        final Province province = Game.getProvince(nProvinceID);
        final float tScale = province.cityScale * Game.mapScale.getCurrentScale();
        if (province.getFortLevel() > 0) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha2));
            Game.mapCities.imageCityFort.get(this.imageID).draw(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - Game.mapCities.imageCityFort.get(this.imageID).getWidth() * tScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) - (int)(Game.mapCities.imageCityFort.get(this.imageID).getHeight() * tScale / 2.0f), tScale);
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha2));
            Game.mapCities.imageCity.get(this.imageID).draw(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - Game.mapCities.imageCity.get(this.imageID).getWidth() * tScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) - (int)(Game.mapCities.imageCity.get(this.imageID).getHeight() * tScale / 2.0f), tScale);
        }
    }
    
    public final void drawCity_CivFlagName(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha) {
        this.drawCityName_Capital_Civ(oSB, nProvinceID, nScale, fAlpha);
    }
    
    public final void drawCity_CivFlag(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha) {
        Map.drawProvincesFlags.drawCity_CivFlag(oSB, nProvinceID, nScale, fAlpha, this.getDrawPosX(nProvinceID, nScale), this.getDrawPosY(nProvinceID, nScale));
    }
    
    public final void drawCity_InGame(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final float fAlpha2, final float fontScale) {
        final Province province = Game.getProvince(nProvinceID);
        final float tScale = province.cityScale * Game.mapScale.getCurrentScale();
        if (province.getFortLevel() > 0) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha2));
            final Image image = Game.mapCities.imageCityFort.get(this.imageID);
            image.draw(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - image.getWidth() * tScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) - (int)(image.getHeight() * tScale / 2.0f), tScale);
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha2));
            final Image image = Game.mapCities.imageCity.get(this.imageID);
            image.draw(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - image.getWidth() * tScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) - (int)(image.getHeight() * tScale / 2.0f), tScale);
        }
    }
    
    public final void drawCity_InGame_NamesLow(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final float fAlpha2, final float fontScale) {
        final Province province = Game.getProvince(nProvinceID);
        final float tScale = Game.mapCities.citiesSettings.citiesScale_CurrentScale[this.iCityLevel];
        if (province.getFortLevel() > 0) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
            final Image image = Game.mapCities.imageCityFort.get(this.imageID);
            image.draw(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - image.getWidth() * tScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) - (int)(image.getHeight() * tScale / 2.0f), tScale);
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha2));
            final Image image = Game.mapCities.imageCity.get(this.imageID);
            image.draw(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - image.getWidth() * tScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) - (int)(image.getHeight() * tScale / 2.0f), tScale);
            this.drawCityName(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - this.iWidth * fontScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) + (int)(image.getHeight() * tScale / 2.0f + CFG.PADDING + CFG.PADDING / 2), fAlpha, fontScale);
        }
    }
    
    public final void drawCity_NameNotCapital(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final float fontScale) {
        final Province province = Game.getProvince(nProvinceID);
        final float tScale = Game.mapCities.citiesSettings.citiesScale_CurrentScale[this.iCityLevel];
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        if (province.getFortLevel() > 0) {
            if (!province.isCapital) {
                this.drawCityName(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - this.iWidth * fontScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) + (int)(Game.mapCities.imageCityFort.get(this.imageID).getHeight() * tScale / 2.0f + CFG.PADDING + CFG.PADDING / 2), fAlpha, fontScale);
            }
        }
        else if (!province.isCapital) {
            this.drawCityName(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - this.iWidth * fontScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) + (int)(Game.mapCities.imageCity.get(this.imageID).getHeight() * tScale / 2.0f + CFG.PADDING + CFG.PADDING / 2), fAlpha, fontScale);
        }
    }
    
    public final void drawCity_Name(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final float fontScale) {
        final Province province = Game.getProvince(nProvinceID);
        final float tScale = Game.mapCities.citiesSettings.citiesScale_CurrentScale[this.iCityLevel];
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        if (province.getFortLevel() > 0) {
            if (province.isCapital) {
                this.drawCityName_Capital(oSB, nProvinceID, nScale, fAlpha, tScale);
            }
            else {
                this.drawCityName(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - this.iWidth * fontScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) + (int)(Game.mapCities.imageCityFort.get(this.imageID).getHeight() * tScale / 2.0f + CFG.PADDING + CFG.PADDING / 2), fAlpha, fontScale);
            }
        }
        else if (province.isCapital) {
            this.drawCityName_Capital(oSB, nProvinceID, nScale, fAlpha, tScale);
        }
        else {
            this.drawCityName(oSB, (int)((this.getPosX() + province.getTranslateProvincePosX()) * nScale - this.iWidth * fontScale / 2.0f), (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) + (int)(Game.mapCities.imageCity.get(this.imageID).getHeight() * tScale / 2.0f + CFG.PADDING + CFG.PADDING / 2), fAlpha, fontScale);
        }
    }
    
    private final void drawCityName(final SpriteBatch oSB, final int nX, final int nY, final float fAlpha, final float fontScale) {
        Renderer.fontMain.get(4).getData().setScale(fontScale);
        Renderer.drawText(oSB, 4, this.sCityName, nX, nY, new Color(MapCities.COLOR_CITY_NAME.r, MapCities.COLOR_CITY_NAME.g, MapCities.COLOR_CITY_NAME.b, MapCities.COLOR_CITY_NAME.a * fAlpha));
    }
    
    private final void drawCityName_Capital(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final float tScale) {
        int nPosX = this.getDrawPosX(nProvinceID, nScale);
        final int nPosY = this.getDrawPosY(nProvinceID, nScale);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f * fAlpha));
        final int tWidth = this.getDrawWidth();
        nPosX -= tWidth / 2;
        oSB.setShader(Renderer.shaderAlpha);
        Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getFlag().getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.capitalLeft).draw(oSB, nPosX, nPosY);
        if (Game.iHoveredCapitalProvinceID == nProvinceID) {
            ImageManager.getImage(Images.capitalLeft).draw(oSB, nPosX, nPosY);
        }
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.capitalLeftOver).draw(oSB, nPosX, nPosY);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f * fAlpha));
        ImageManager.getImage(Images.capitalRight).draw2(oSB, nPosX + ImageManager.getImage(Images.capitalLeft).getWidth(), nPosY, this.iWidth + 5 + 10, ImageManager.getImage(Images.capitalRight).getHeight(), true, false);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        Renderer.fontMain.get(4).getData().setScale(Game.settingsManager.CITIES_CAPITAL_FONT_SCALE);
        Renderer.drawText(oSB, 4, "" + this.sCityName, nPosX + ImageManager.getImage(Images.capitalLeft).getWidth() + 5, nPosY + (ImageManager.getImage(Images.capitalLeft).getHeight() - this.iHeight) / 2, new Color(MapCities.COLOR_CITY_CAPITAL_NAME.r, MapCities.COLOR_CITY_CAPITAL_NAME.g, MapCities.COLOR_CITY_CAPITAL_NAME.b, MapCities.COLOR_CITY_CAPITAL_NAME.a * fAlpha));
        oSB.setColor(Color.WHITE);
    }
    
    private final void drawCityName_Capital_Civ(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha) {
        int nPosX = this.getDrawPosX(nProvinceID, nScale);
        final int nPosY = this.getDrawPosY(nProvinceID, nScale);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f * fAlpha));
        nPosX -= this.getDrawWidth() / 2;
        oSB.setShader(Renderer.shaderAlpha);
        Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getFlag().getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.capitalLeft).draw(oSB, nPosX, nPosY);
        if (Game.iHoveredCapitalProvinceID == nProvinceID) {
            ImageManager.getImage(Images.capitalLeft).draw(oSB, nPosX, nPosY);
        }
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.capitalLeftOver).draw(oSB, nPosX, nPosY);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f * fAlpha));
        ImageManager.getImage(Images.capitalRight).draw2(oSB, nPosX + ImageManager.getImage(Images.capitalLeft).getWidth(), nPosY, Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCapitalNameWidth + 5 + 10, ImageManager.getImage(Images.capitalRight).getHeight(), true, false);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        Renderer.fontMain.get(4).getData().setScale(Game.settingsManager.CITIES_CAPITAL_FONT_SCALE);
        Game.mapCities.capitalCityName.draw(oSB, nProvinceID, fAlpha, nPosX + 5, nPosY);
        oSB.setColor(Color.WHITE);
    }
    
    public final void drawCityName_Capital_CivFlag_War(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha) {
        final int nPosX = this.getDrawPosX(nProvinceID, nScale);
        final int nPosY = this.getDrawPosY(nProvinceID, nScale);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * fAlpha));
        if (Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCivRankID == 4) {
            ImageManager.getImage(Images.flagCapitalWar_l).draw(oSB, nPosX - ImageManager.getImage(Images.flagCapitalWar_l).getWidth() / 2, nPosY + ImageManager.getImage(Images.flagCapitalMask_l).getHeight() / 2 - ImageManager.getImage(Images.flagCapitalWar_l).getHeight() / 2);
        }
        else if (Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCivRankID > 1) {
            ImageManager.getImage(Images.flagCapitalWar).draw(oSB, nPosX - ImageManager.getImage(Images.flagCapitalWar).getWidth() / 2, nPosY + ImageManager.getImage(Images.flagCapitalMask).getHeight() / 2 - ImageManager.getImage(Images.flagCapitalWar).getHeight() / 2);
        }
        else {
            ImageManager.getImage(Images.flagCapitalWar_s).draw(oSB, nPosX - ImageManager.getImage(Images.flagCapitalWar_s).getWidth() / 2, nPosY + ImageManager.getImage(Images.flagCapitalMask_s).getHeight() / 2 - ImageManager.getImage(Images.flagCapitalWar_s).getHeight() / 2);
        }
    }
    
    public final int getDrawPosX(final int nProvinceID, final float nScale) {
        return (int)((this.getPosX() + Game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
    }
    
    public final int getDrawPosY(final int nProvinceID, final float nScale) {
        return (int)((this.getPosY() + Game.mapCoords.getPosY()) * nScale) + (int)(Game.mapCities.imageCityFort.get(this.imageID).getHeight() * Game.mapCities.citiesSettings.citiesScale_CurrentScale[this.iCityLevel] / 2.0f + CFG.PADDING + CFG.PADDING / 2);
    }
    
    public final int getDrawWidth() {
        return this.iWidth + ImageManager.getImage(Images.capitalLeft).getWidth() + 10 + 5;
    }
    
    public final void setCityNameOriginal(final int nProvinceID) {
        if (!this.sCityNameOriginal.equals(this.sCityName)) {
            this.sCityName = this.sCityNameOriginal;
            this.updateCityNameWidth();
            Game.getProvince(nProvinceID).setProvinceName(this.sCityName);
            Game.addSimpleTask(new Game.SimpleTask("buildProvNameData" + nProvinceID, nProvinceID) {
                @Override
                public void update() {
                    ProvinceNamesManager.buildProvNameData(this.id, true);
                }
            });
        }
    }
    
    public final void setCityName(final int nProvinceID, final String sName) {
        if (!this.sCityName.equals(sName)) {
            this.sCityName = sName;
            this.updateCityNameWidth();
            Game.getProvince(nProvinceID).setProvinceName(this.sCityName);
            Game.addSimpleTask(new Game.SimpleTask("rebuildProvNameData" + nProvinceID, nProvinceID) {
                @Override
                public void update() {
                    ProvinceNamesManager.buildProvNameData(this.id, true);
                }
            });
        }
    }
    
    public final int getPosX() {
        return this.iPosX;
    }
    
    public final int getPosY() {
        return this.iPosY;
    }
    
    protected final void updateCityNameWidth() {
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(4), this.sCityName);
        this.iWidth = (int)glyphLayout.width;
        this.iHeight = (int)glyphLayout.height;
    }
}
