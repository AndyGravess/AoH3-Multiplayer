// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_BattleArmy;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.map.battles.BattleRegiment;
import aoh.kingdoms.history.menusInGame.Battle.InGame_Battle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextFlagsCasualties extends Button
{
    public String sLeft;
    public String sCasualties;
    public int iCasualtiesWidth;
    public int iCasualtiesHeight;
    public int iconWidth;
    public int iconHeight;
    public int iconWidth2;
    public int iconHeight2;
    public int iconWidth3;
    public int iconHeight3;
    public boolean defenders;
    private List<BattleStatsRegiments> tRegiments;
    
    public TextFlagsCasualties(final String sText, final String sLeft, final String sCasualties, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final boolean defenders) {
        this.tRegiments = new ArrayList<BattleStatsRegiments>();
        this.init(sText, CFG.FONT_REGULAR_SMALL, -1, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.defenders = defenders;
        this.sLeft = sLeft;
        this.sCasualties = sCasualties;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sCasualties);
        this.iCasualtiesWidth = (int)Renderer.glyphLayout.width;
        this.iCasualtiesHeight = (int)Renderer.glyphLayout.height;
        float iconScale = this.getImageScale(Images.skull);
        this.iconWidth = (int)(ImageManager.getImage(Images.skull).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(Images.skull).getHeight() * iconScale);
        iconScale = this.getImageScale(Images.retreat);
        this.iconWidth3 = (int)(ImageManager.getImage(Images.retreat).getWidth() * iconScale);
        this.iconHeight3 = (int)(ImageManager.getImage(Images.retreat).getHeight() * iconScale);
        iconScale = this.getImageScale(Game_Calendar.IMG_MANPOWER);
        this.iconWidth2 = (int)(ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() * iconScale);
        this.iconHeight2 = (int)(ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getHeight() * iconScale);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.skull).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING - this.iconWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iconHeight / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        ImageManager.getImage(Images.retreat).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iconWidth - this.iconWidth3 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iconHeight / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        ImageManager.getImage(Game_Calendar.IMG_MANPOWER).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iconHeight2 / 2 + iTranslateY, this.iconWidth2, this.iconHeight2);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, this.fontID, this.sCasualties, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - this.iconWidth - this.iconWidth3 - this.iCasualtiesWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iCasualtiesHeight / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, this.fontID, this.sLeft, this.getPosX() + CFG.PADDING * 2 + this.iconWidth2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iCasualtiesHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStatsHover(isActive, this.getIsHovered());
    }
    
    private final float getImageScale() {
        return CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NumberOfUnitsOnTheBattlefield") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (InGame_Battle.battleID >= 0) {
            this.tRegiments.clear();
            if (this.defenders) {
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i) != null) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.num);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i) != null) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.num);
                    }
                }
            }
            else {
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i) != null) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.num);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i) != null) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).aR.num);
                    }
                }
            }
            final List<Integer> tSorted = new ArrayList<Integer>();
            final List<Integer> tempReg = new ArrayList<Integer>();
            for (int j = 0, iSize2 = this.tRegiments.size(); j < iSize2; ++j) {
                tempReg.add(j);
            }
            while (tempReg.size() > 0) {
                int toAdd = 0;
                for (int k = 1, iSize3 = tempReg.size(); k < iSize3; ++k) {
                    if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID > this.tRegiments.get(tempReg.get(k)).unitTypeID) {
                        toAdd = k;
                    }
                    else if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID == this.tRegiments.get(tempReg.get(k)).unitTypeID && this.tRegiments.get(tempReg.get(toAdd)).armyID > this.tRegiments.get(tempReg.get(k)).armyID) {
                        toAdd = k;
                    }
                }
                tSorted.add(tempReg.get(toAdd));
                tempReg.remove(toAdd);
            }
            for (int l = 0; l < 3; ++l) {
                for (int k = 0, iSize3 = this.tRegiments.size(); k < iSize3; ++k) {
                    if (ArmyManager.lUnitsTypes.get(this.tRegiments.get(tSorted.get(k)).unitTypeID).Line == l) {
                        nData.add(new MenuElement_HoverElement_Type_BattleArmy("" + this.tRegiments.get(tSorted.get(k)).numOfUnits, this.tRegiments.get(tSorted.get(k)).unitTypeID, this.tRegiments.get(tSorted.get(k)).armyID, (k > 0) ? CFG.PADDING : 0));
                    }
                }
            }
            if (nData.size() > 0) {
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        if (this.defenders) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Casualties") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.iCasualties), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.skull, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Casualties") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.iCasualties), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.skull, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (InGame_Battle.battleID >= 0) {
            this.tRegiments.clear();
            if (this.defenders) {
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i) != null && Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).ca > 0) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).ca);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i) != null && Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).ca > 0) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).ca);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.size(); i < iSize; ++i) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).ca);
                }
            }
            else {
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i) != null && Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).ca > 0) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).ca);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i) != null && Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).ca > 0) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).ca);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.defeated.size(); i < iSize; ++i) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.defeated.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.defeated.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.defeated.get(i).ca);
                }
            }
            final List<Integer> tSorted = new ArrayList<Integer>();
            final List<Integer> tempReg = new ArrayList<Integer>();
            for (int j = 0, iSize2 = this.tRegiments.size(); j < iSize2; ++j) {
                tempReg.add(j);
            }
            while (tempReg.size() > 0) {
                int toAdd = 0;
                for (int k = 1, iSize3 = tempReg.size(); k < iSize3; ++k) {
                    if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID > this.tRegiments.get(tempReg.get(k)).unitTypeID) {
                        toAdd = k;
                    }
                    else if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID == this.tRegiments.get(tempReg.get(k)).unitTypeID && this.tRegiments.get(tempReg.get(toAdd)).armyID > this.tRegiments.get(tempReg.get(k)).armyID) {
                        toAdd = k;
                    }
                }
                tSorted.add(tempReg.get(toAdd));
                tempReg.remove(toAdd);
            }
            for (int l = 0; l < 3; ++l) {
                for (int k = 0, iSize3 = this.tRegiments.size(); k < iSize3; ++k) {
                    if (ArmyManager.lUnitsTypes.get(this.tRegiments.get(tSorted.get(k)).unitTypeID).Line == l) {
                        nData.add(new MenuElement_HoverElement_Type_BattleArmy("" + this.tRegiments.get(tSorted.get(k)).numOfUnits, this.tRegiments.get(tSorted.get(k)).unitTypeID, this.tRegiments.get(tSorted.get(k)).armyID, (k > 0) ? CFG.PADDING : 0));
                    }
                }
            }
            if (nData.size() > 0) {
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        if (this.defenders) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Retreated") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.iRetreated), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.retreat, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Retreated") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.iRetreated), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.retreat, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (InGame_Battle.battleID >= 0) {
            this.tRegiments.clear();
            if (this.defenders) {
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i) != null && Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).re > 0) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).re);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i) != null && Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).re > 0) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(i).re);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.size(); i < iSize; ++i) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.defeated.get(i).re);
                }
            }
            else {
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i) != null && Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).re > 0) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).re);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.size(); i < iSize; ++i) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i) != null && Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).re > 0) {
                        this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(i).re);
                    }
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.defeated.size(); i < iSize; ++i) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.defeated.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.defeated.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.defeated.get(i).re);
                }
            }
            final List<Integer> tSorted = new ArrayList<Integer>();
            final List<Integer> tempReg = new ArrayList<Integer>();
            for (int j = 0, iSize2 = this.tRegiments.size(); j < iSize2; ++j) {
                tempReg.add(j);
            }
            while (tempReg.size() > 0) {
                int toAdd = 0;
                for (int k = 1, iSize3 = tempReg.size(); k < iSize3; ++k) {
                    if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID > this.tRegiments.get(tempReg.get(k)).unitTypeID) {
                        toAdd = k;
                    }
                    else if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID == this.tRegiments.get(tempReg.get(k)).unitTypeID && this.tRegiments.get(tempReg.get(toAdd)).armyID > this.tRegiments.get(tempReg.get(k)).armyID) {
                        toAdd = k;
                    }
                }
                tSorted.add(tempReg.get(toAdd));
                tempReg.remove(toAdd);
            }
            for (int l = 0; l < 3; ++l) {
                for (int k = 0, iSize3 = this.tRegiments.size(); k < iSize3; ++k) {
                    if (ArmyManager.lUnitsTypes.get(this.tRegiments.get(tSorted.get(k)).unitTypeID).Line == l) {
                        nData.add(new MenuElement_HoverElement_Type_BattleArmy("" + this.tRegiments.get(tSorted.get(k)).numOfUnits, this.tRegiments.get(tSorted.get(k)).unitTypeID, this.tRegiments.get(tSorted.get(k)).armyID, (k > 0) ? CFG.PADDING : 0));
                    }
                }
            }
            if (nData.size() > 0) {
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UnitsInReserve") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + this.sLeft, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (InGame_Battle.battleID >= 0) {
            this.tRegiments.clear();
            if (this.defenders) {
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveFirstLine.size(); i < iSize; ++i) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveFirstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveFirstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveFirstLine.get(i).aR.num);
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveSecondLine.size(); i < iSize; ++i) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveSecondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveSecondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.reserveSecondLine.get(i).aR.num);
                }
            }
            else {
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.reserveFirstLine.size(); i < iSize; ++i) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.reserveFirstLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.reserveFirstLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.reserveFirstLine.get(i).aR.num);
                }
                for (int i = 0, iSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.reserveSecondLine.size(); i < iSize; ++i) {
                    this.addRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.reserveSecondLine.get(i).aR.uID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.reserveSecondLine.get(i).aR.aID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.reserveSecondLine.get(i).aR.num);
                }
            }
            final List<Integer> tSorted = new ArrayList<Integer>();
            final List<Integer> tempReg = new ArrayList<Integer>();
            for (int j = 0, iSize2 = this.tRegiments.size(); j < iSize2; ++j) {
                tempReg.add(j);
            }
            while (tempReg.size() > 0) {
                int toAdd = 0;
                for (int k = 1, iSize3 = tempReg.size(); k < iSize3; ++k) {
                    if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID > this.tRegiments.get(tempReg.get(k)).unitTypeID) {
                        toAdd = k;
                    }
                    else if (this.tRegiments.get(tempReg.get(toAdd)).unitTypeID == this.tRegiments.get(tempReg.get(k)).unitTypeID && this.tRegiments.get(tempReg.get(toAdd)).armyID > this.tRegiments.get(tempReg.get(k)).armyID) {
                        toAdd = k;
                    }
                }
                tSorted.add(tempReg.get(toAdd));
                tempReg.remove(toAdd);
            }
            for (int l = 0; l < 3; ++l) {
                for (int k = 0, iSize3 = this.tRegiments.size(); k < iSize3; ++k) {
                    if (ArmyManager.lUnitsTypes.get(this.tRegiments.get(tSorted.get(k)).unitTypeID).Line == l) {
                        nData.add(new MenuElement_HoverElement_Type_BattleArmy("" + this.tRegiments.get(tSorted.get(k)).numOfUnits, this.tRegiments.get(tSorted.get(k)).unitTypeID, this.tRegiments.get(tSorted.get(k)).armyID, (k > 0) ? CFG.PADDING : 0));
                    }
                }
            }
            if (nData.size() > 0) {
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("UnitsThatHaventBeenDeployedOnTheNattlefieldDueToTheCompleteOccupationOfAvailableSpace"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements) {
            @Override
            public int getMinPosX() {
                return InGame_Battle.HOVER_POSX;
            }
            
            @Override
            public void draw(final SpriteBatch oSB, final int nPosX, int nPosY) {
                nPosY = InGame_Battle.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_Battle.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        };
    }
    
    public void addRegiment(final int unitTypeID, final int armyID, final int numOfUnits) {
        for (int i = 0, iSize = this.tRegiments.size(); i < iSize; ++i) {
            if (this.tRegiments.get(i).unitTypeID == unitTypeID && this.tRegiments.get(i).armyID == armyID) {
                final BattleStatsRegiments battleStatsRegiments = this.tRegiments.get(i);
                battleStatsRegiments.numOfUnits += numOfUnits;
                return;
            }
        }
        this.tRegiments.add(new BattleStatsRegiments(unitTypeID, armyID, numOfUnits));
    }
    
    public static class BattleStatsRegiments
    {
        public int unitTypeID;
        public int armyID;
        public int numOfUnits;
        
        public BattleStatsRegiments(final int unitTypeID, final int armyID, final int numOfUnits) {
            this.unitTypeID = unitTypeID;
            this.armyID = armyID;
            this.numOfUnits = numOfUnits;
        }
    }
}
