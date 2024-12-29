// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_BattleArmy;
import aoh.kingdoms.history.map.army.ArmyRegiment;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceArmy;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.GeneralManager;
import com.badlogic.gdx.graphics.Texture;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Image;

public class Button_PinnedArmy_Province extends Button
{
    public String key;
    public int iActiveGeneralIMG_ID;
    public int iArmyNum;
    public int iProvinceID;
    public int iLastTurnID;
    public Image generalImage;
    public int civID;
    
    public Button_PinnedArmy_Province(final int civID, final String key, final int iPosX, final int iPosY, final int iProvinceID) {
        this.key = null;
        this.iActiveGeneralIMG_ID = -1;
        this.iArmyNum = -4894584;
        this.iLastTurnID = -975159;
        this.civID = civID;
        this.init("", CFG.FONT_BOLD_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.pinnedGeneralFrame).getWidth(), getButtonHeight(), true, true, false, false);
        this.key = key;
        this.iProvinceID = iProvinceID;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        try {
            if (Game_Calendar.TURN_ID != this.iLastTurnID) {
                this.iLastTurnID = Game_Calendar.TURN_ID;
                final Game.ArmyPos nArmyPos = Game.findArmy_FullCheck(this.civID, this.key);
                if (nArmyPos != null && nArmyPos.iProvinceID == this.iProvinceID) {
                    if (Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral == null) {
                        this.iActiveGeneralIMG_ID = -1;
                        if (this.generalImage != null) {
                            this.generalImage.dispose();
                            this.generalImage = null;
                        }
                    }
                    else if (this.iActiveGeneralIMG_ID != Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral.g) {
                        this.iActiveGeneralIMG_ID = Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral.g;
                        if (this.generalImage != null) {
                            this.generalImage.dispose();
                            this.generalImage = null;
                        }
                        if (Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral.sI != null) {
                            if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short() + Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral.sI + ".png").exists()) {
                                this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short() + Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral.sI + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                            }
                            else if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short_H() + Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral.sI + ".png").exists()) {
                                this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short_H() + Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral.sI + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                            }
                        }
                        if (this.generalImage == null) {
                            if (FileManager.loadFile("game/generals/" + GeneralManager.getGeneralsImgPath(this.civID) + CFG.getRescouresPath_Short() + Game.getCiv(this.civID).iGroupID + "/" + this.iActiveGeneralIMG_ID + ".png").exists()) {
                                this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/generals/" + GeneralManager.getGeneralsImgPath(this.civID) + CFG.getRescouresPath_Short() + Game.getCiv(this.civID).iGroupID + "/" + this.iActiveGeneralIMG_ID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                            }
                            else {
                                this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/generals/" + GeneralManager.getGeneralsImgPath(this.civID) + CFG.getRescouresPath_Short() + Game.getCiv(this.civID).iGroupID + "/0.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                            }
                        }
                    }
                    if (this.iArmyNum != Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).iArmy) {
                        this.iArmyNum = Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).iArmy;
                        final int checkCivID = Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).civID;
                        String textUnits = "";
                        if (!Game.FOG_OF_WAR || !GameValues.fog.HIDE_ARMIES || checkCivID == Game.player.iCivID || DiplomacyManager.isAlly(checkCivID, Game.player.iCivID)) {
                            textUnits = Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).sArmy;
                        }
                        else {
                            textUnits = GameValues.fog.TEXT_UNKNOWN_ARMIES;
                        }
                        this.setText(textUnits);
                    }
                }
                else {
                    Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_ProvinceInfo_Army") {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_ProvinceInfo_Army();
                        }
                    });
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setShader(Renderer.shaderAlpha);
        try {
            if (this.iActiveGeneralIMG_ID >= 0 && this.generalImage != null) {
                this.generalImage.getTexture().bind(1);
            }
            else {
                Game.generalManager.noGeneral.getTexture().bind(1);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.pinnedGeneralMask).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        if (this.getIsHovered() || isActive) {
            ImageManager.getImage(Images.pinnedGeneralFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        }
        ImageManager.getImage(Images.pinnedGeneralFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - this.iTextHeight - CFG.PADDING - CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        try {
            if (Game.activeArmySize == 1 && Game.activeArmy.get(0).key.equals(this.key)) {
                return Colors.HOVER_GOLD;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return super.getColor(isActive);
    }
    
    @Override
    public String getTextToDraw() {
        return this.sText;
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.pinnedGeneralFrame).getHeight();
    }
    
    @Override
    public void actionElement() {
        final Game.ArmyPos nArmyPos = Game.findArmy_FullCheck(this.civID, this.key);
        if (nArmyPos == null) {
            Game.menuManager.addToast_Error(Game.lang.get("ArmyNotFound"));
            return;
        }
        if (nArmyPos.iProvinceID >= 0 && Game.activeArmySize == 1 && Game.activeArmy.get(0).key.equals(this.key)) {
            Game.mapCoords.centerToProvinceID(nArmyPos.iProvinceID);
            return;
        }
        Game.setActiveArmy(nArmyPos.iProvinceID, this.key);
        InGame_ProvinceArmy.iActiveID = 0;
        InGame_ProvinceArmy.sActiveKEY = this.key;
        Game.menuManager.rebuildInGame_ProvinceArmy();
        Game.menuManager.rebuildInGame_ProvinceArmy_HideMenus();
        Game.gameActiveProvince.resetLastActiveProvince();
        Game.setActiveProvinceID(-1);
        Game.animationHover.resetAnimationData();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        final Game.ArmyPos nArmyPos = Game.findArmy_FullCheck(this.civID, this.key);
        if (nArmyPos != null) {
            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.civID, (Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral == null) ? Game.lang.get("NoGeneral") : Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).armyGeneral.n));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.getProvince(nArmyPos.iProvinceID).getProvinceName(), "", Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT, Colors.HOVER_RIGHT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            for (int i = 0; i < Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).iArmyRegimentSize; ++i) {
                if (ArmyManager.lUnitsTypes.get(Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).uID).Line < 2) {
                    int tUnits = Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).num;
                    for (int numOfRegiments = 1, o = i + 1; o < Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).iArmyRegimentSize && Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).uID == Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(o).uID && Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).aID == Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(o).aID; ++i, ++numOfRegiments, tUnits += Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).num, ++o) {}
                    final int checkCivID = Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).civID;
                    String textUnits = "";
                    if (!Game.FOG_OF_WAR || !GameValues.fog.HIDE_MANPOWER || checkCivID == Game.player.iCivID || DiplomacyManager.isAlly(checkCivID, Game.player.iCivID)) {
                        textUnits = "" + tUnits;
                    }
                    else {
                        textUnits = GameValues.fog.TEXT_UNKNOWN_MANPOWER;
                    }
                    nData.add(new MenuElement_HoverElement_Type_BattleArmy(textUnits, Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).uID, Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).aID, (i > 0) ? CFG.PADDING : 0));
                }
            }
            for (int i = 0; i < Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).iArmyRegimentSize; ++i) {
                if (ArmyManager.lUnitsTypes.get(Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).uID).Line >= 2) {
                    int tUnits = Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).num;
                    for (int numOfRegiments = 1, o = i + 1; o < Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).iArmyRegimentSize && Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).uID == Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(o).uID && Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).aID == Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(o).aID; ++i, ++numOfRegiments, tUnits += Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).num, ++o) {}
                    final int checkCivID = Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).civID;
                    String textUnits = "";
                    if (!Game.FOG_OF_WAR || !GameValues.fog.HIDE_MANPOWER || checkCivID == Game.player.iCivID || DiplomacyManager.isAlly(checkCivID, Game.player.iCivID)) {
                        textUnits = "" + tUnits;
                    }
                    else {
                        textUnits = GameValues.fog.TEXT_UNKNOWN_MANPOWER;
                    }
                    nData.add(new MenuElement_HoverElement_Type_BattleArmy(textUnits, Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).uID, Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).lArmyRegiment.get(i).aID, (i > 0) ? CFG.PADDING : 0));
                }
            }
            if (nData.size() > 0) {
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SelectArmy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public int getSFX() {
        return Game.soundsManager.getSelectedArmy();
    }
}
