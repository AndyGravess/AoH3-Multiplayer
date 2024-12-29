// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.menusInGame.InGame_Legacies;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.LegacyManager;
import aoh.kingdoms.history.mainGame.Game;

public class ButtonLegacy extends Button
{
    public int legacyID;
    public int currentLvl;
    public String sUnlock;
    public int iUnlockWidth;
    
    public ButtonLegacy(final int iPosX, final int iPosY, final int legacyID, final int currentLvl) {
        this.legacyID = legacyID;
        this.currentLvl = currentLvl;
        this.init(Game.lang.get(LegacyManager.legacies.get(legacyID).Name), CFG.FONT_REGULAR_SMALL, -1, iPosX, iPosY, LegacyManager.legacyImages.get(LegacyManager.legacies.get(legacyID).ImageID).getWidth(), LegacyManager.legacyImages.get(LegacyManager.legacies.get(legacyID).ImageID).getHeight(), true, true, false, false);
        if (currentLvl + 1 < LegacyManager.legacies.get(legacyID).CostLegacy.length) {
            this.sUnlock = Game.lang.get("Unlock");
        }
        else {
            this.sUnlock = Game.lang.get("Max");
        }
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sUnlock);
        this.iUnlockWidth = (int)Renderer.glyphLayout.width;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.currentLvl == LegacyManager.legacies.get(this.legacyID).CostLegacy.length - 1) {
            LegacyManager.legacyImages.get(LegacyManager.legacies.get(this.legacyID).ImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        }
        else if (this.getIsHovered() || isActive) {
            LegacyManager.legacyImages.get(LegacyManager.legacies.get(this.legacyID).ImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        }
        else {
            oSB.setShader(Renderer.shaderBlackWhite);
            LegacyManager.legacyImages.get(LegacyManager.legacies.get(this.legacyID).ImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
            oSB.setShader(Renderer.shaderDefault);
            if (this.currentLvl >= 0) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f + 0.75f * ((this.currentLvl + 1) / (float)LegacyManager.legacies.get(this.legacyID).CostLegacy.length)));
                LegacyManager.legacyImages.get(LegacyManager.legacies.get(this.legacyID).ImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
                oSB.setColor(Color.WHITE);
            }
        }
        if (InGame_Legacies.bgAlpha < 1.0f) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (1.0f - InGame_Legacies.bgAlpha) / 4.0f));
            oSB.setShader(Renderer.shaderAlpha);
            LegacyManager.legacyImages.get(LegacyManager.legacies.get(this.legacyID).ImageID).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, LegacyManager.legacyImages.get(LegacyManager.legacies.get(this.legacyID).ImageID).getWidth(), LegacyManager.legacyImages.get(LegacyManager.legacies.get(this.legacyID).ImageID).getHeight(), false, true);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, LegacyManager.legacyImages.get(LegacyManager.legacies.get(this.legacyID).ImageID).getWidth(), LegacyManager.legacyImages.get(LegacyManager.legacies.get(this.legacyID).ImageID).getHeight());
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, this.getWidth(), CFG.PADDING);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
            Renderer.drawBoxCornerAlpha(oSB, this.getPosX() - CFG.PADDING + this.getWidth() / 2 - this.iUnlockWidth / 2 + iTranslateX, this.getPosY() - CFG.PADDING * 3 + this.getHeight() - this.getTextHeight() + iTranslateY, this.iUnlockWidth + CFG.PADDING * 2, this.getTextHeight() + CFG.PADDING * 2);
            oSB.setColor(Color.WHITE);
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sUnlock, this.getPosX() + this.getWidth() / 2 - this.iUnlockWidth / 2 + iTranslateX, this.getPosY() + this.getHeight() - this.getTextHeight() - CFG.PADDING * 2 + iTranslateY, Colors.COLOR_TEXT_MODIFIER_POSITIVE);
            oSB.setColor(ProvinceDraw.progressBarBG);
            ImageManager.getImage(Images.levelMask).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.levelFrame).getHeight() + iTranslateY);
            if (this.currentLvl >= 0) {
                oSB.setColor(ProvinceDraw.progressBar);
                ImageManager.getImage(Images.levelMask).draw2(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.levelFrame).getHeight() + iTranslateY, ImageManager.getImage(Images.levelMask).getWidth() * (this.currentLvl + 1) / LegacyManager.legacies.get(this.legacyID).CostLegacy.length, ImageManager.getImage(Images.levelMask).getHeight());
            }
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.levelFrame).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.levelFrame).getHeight() + iTranslateY);
        }
        else {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextHeight() - CFG.PADDING * 2 + iTranslateY, this.getColor(isActive));
        }
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats4(isActive, this.getIsHovered());
    }
    
    @Override
    public int getCurrent() {
        return this.legacyID;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(" [" + Game.lang.get("Unlocked") + ": " + (this.currentLvl + 1) + "/" + LegacyManager.legacies.get(this.legacyID).CostLegacy.length + "]", CFG.FONT_REGULAR, Colors.HOVER_RIGHT2));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (LegacyManager.legacies.get(this.legacyID).ConstructionCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).ConstructionCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).ConstructionCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).ConstructionCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).ConstructionCost[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).ConstructionCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.construction, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).AdministrationBuildingsCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdministrationBuildingsCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).AdministrationBuildingsCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).AdministrationBuildingsCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).AdministrationBuildingsCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).AdministrationBuildingsCost[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).AdministrationBuildingsCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.construction, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MilitaryBuildingsCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MilitaryBuildingsCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MilitaryBuildingsCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MilitaryBuildingsCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MilitaryBuildingsCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).MilitaryBuildingsCost[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MilitaryBuildingsCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.construction, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).EconomyBuildingsCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("EconomyBuildingsCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).EconomyBuildingsCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).EconomyBuildingsCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).EconomyBuildingsCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).EconomyBuildingsCost[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).EconomyBuildingsCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.construction, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).ConstructionTime != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionTime") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).ConstructionTime.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).ConstructionTime[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).ConstructionTime[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).ConstructionTime[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).ConstructionTime.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.buildTime, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).WonderConstructionCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("WonderConstructionCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).WonderConstructionCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).WonderConstructionCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).WonderConstructionCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).WonderConstructionCost[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).WonderConstructionCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.mapModesWonders, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaximumAmountOfGold != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumAmountOfGold") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaximumAmountOfGold.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaximumAmountOfGold[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaximumAmountOfGold[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).MaximumAmountOfGold[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaximumAmountOfGold.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).Loot != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Loot") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).Loot.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).Loot[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).Loot[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).Loot[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).Loot.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.loot, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).TaxEfficiency != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TaxEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).TaxEfficiency.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).TaxEfficiency[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).TaxEfficiency[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).TaxEfficiency[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).TaxEfficiency.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.tax, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).ProvinceMaintenance != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceMaintenance") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).ProvinceMaintenance.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).ProvinceMaintenance[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).ProvinceMaintenance[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).ProvinceMaintenance[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).ProvinceMaintenance.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).BuildingsMaintenanceCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingsMaintenanceCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).BuildingsMaintenanceCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).BuildingsMaintenanceCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).BuildingsMaintenanceCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).BuildingsMaintenanceCost[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).BuildingsMaintenanceCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.buildings, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).ManpowerRecoverySpeed != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ManpowerRecoverySpeed") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).ManpowerRecoverySpeed.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).ManpowerRecoverySpeed[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).ManpowerRecoverySpeed[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).ManpowerRecoverySpeed[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).ManpowerRecoverySpeed.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_TIME, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaxManpower != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumManpower") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaxManpower.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaxManpower[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaxManpower[i] > 0) ? "+" : "") + CFG.getNumberWithSpaces("" + LegacyManager.legacies.get(this.legacyID).MaxManpower[i]) + ""), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaxManpower.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_UP, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).Research != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Research") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).Research.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).Research[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).Research[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).Research[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).Research.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).ResearchPoints != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ResearchPerMonth") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).ResearchPoints.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).ResearchPoints[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).ResearchPoints[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).ResearchPoints[i], 10)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).ResearchPoints.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).GrowthRate != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).GrowthRate.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).GrowthRate[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).GrowthRate[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).GrowthRate[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).GrowthRate.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).IncomeProduction != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncomeProduction") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).IncomeProduction.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).IncomeProduction[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).IncomeProduction[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).IncomeProduction[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).IncomeProduction.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).InvestInEconomyCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InvestInEconomyCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).InvestInEconomyCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).InvestInEconomyCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).InvestInEconomyCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).InvestInEconomyCost[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).InvestInEconomyCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY_UP, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).IncreaseTaxEfficiencyCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).IncreaseTaxEfficiencyCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).IncreaseTaxEfficiencyCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).IncreaseTaxEfficiencyCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).IncreaseTaxEfficiencyCost[i] * 100.0f, 100) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).IncreaseTaxEfficiencyCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.taxUp, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).IncreaseGrowthRateCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseGrowthRateCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).IncreaseGrowthRateCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).IncreaseGrowthRateCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).IncreaseGrowthRateCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).IncreaseGrowthRateCost[i] * 100.0f, 100) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).IncreaseGrowthRateCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).DevelopInfrastructureCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DevelopInfrastructureCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).DevelopInfrastructureCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).DevelopInfrastructureCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).DevelopInfrastructureCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).DevelopInfrastructureCost[i] * 100.0f, 100) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).DevelopInfrastructureCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.infrastructureUp, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).ProductionEfficiency != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProductionEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).ProductionEfficiency.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).ProductionEfficiency[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).ProductionEfficiency[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).ProductionEfficiency[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).ProductionEfficiency.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).GeneralAttack != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GeneralsAttack") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).GeneralAttack.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).GeneralAttack[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).GeneralAttack[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).GeneralAttack[i], 10)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).GeneralAttack.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.attack, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).GeneralDefense != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GeneralsDefense") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).GeneralDefense.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).GeneralDefense[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).GeneralDefense[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).GeneralDefense[i], 10)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).GeneralDefense.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.defense, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).RegimentsLimit != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RegimentsLimit") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).RegimentsLimit.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).RegimentsLimit[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).RegimentsLimit[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).RegimentsLimit[i], 1)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).RegimentsLimit.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.regimentsLimit, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).BattleWidth != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattleWidth") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).BattleWidth.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).BattleWidth[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).BattleWidth[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).BattleWidth[i], 1)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).BattleWidth.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.battleWidth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).AllCharactersLifeExpectancy != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AllCharactersLifeExpectancy") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).AllCharactersLifeExpectancy.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).AllCharactersLifeExpectancy[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).AllCharactersLifeExpectancy[i] > 0) ? "+" : "") + Game.lang.get("YearsX", LegacyManager.legacies.get(this.legacyID).AllCharactersLifeExpectancy[i])), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).AllCharactersLifeExpectancy.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.council, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).ManpowerRecoveryFromADisbandedArmy != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ManpowerRecoveryFromADisbandedArmy") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).ManpowerRecoveryFromADisbandedArmy.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).ManpowerRecoveryFromADisbandedArmy[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).ManpowerRecoveryFromADisbandedArmy[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).ManpowerRecoveryFromADisbandedArmy[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).ManpowerRecoveryFromADisbandedArmy.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_DISBAND, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).Discipline != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Discipline") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).Discipline.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).Discipline[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).Discipline[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).Discipline[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).Discipline.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.discipline, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).UnitsAttack != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UnitsAttack") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).UnitsAttack.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).UnitsAttack[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).UnitsAttack[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).UnitsAttack[i], 10)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).UnitsAttack.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.attack, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).UnitsDefense != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UnitsDefense") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).UnitsDefense.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).UnitsDefense[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).UnitsDefense[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).UnitsDefense[i], 10)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).UnitsDefense.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.defense, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaxMorale != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaxMorale") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaxMorale.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaxMorale[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaxMorale[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).MaxMorale[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaxMorale.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.morale, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).SiegeEffectiveness != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SiegeEffectiveness") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).SiegeEffectiveness.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).SiegeEffectiveness[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).SiegeEffectiveness[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).SiegeEffectiveness[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).SiegeEffectiveness.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.siege, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).ImproveRelationsModifier != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ImproveRelationsModifier") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).ImproveRelationsModifier.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).ImproveRelationsModifier[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).ImproveRelationsModifier[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).ImproveRelationsModifier[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).ImproveRelationsModifier.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.relations, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).IncomeFromVassals != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncomeFromVassals") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).IncomeFromVassals.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).IncomeFromVassals[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).IncomeFromVassals[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).IncomeFromVassals[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).IncomeFromVassals.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).DiplomacyPoints != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DiplomacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).DiplomacyPoints.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).DiplomacyPoints[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).DiplomacyPoints[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).DiplomacyPoints[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).DiplomacyPoints.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).LoanInterest != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LoanInterest") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).LoanInterest.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).LoanInterest[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).LoanInterest[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).LoanInterest[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).LoanInterest.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.loan, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaxNumberOfLoans != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumNumberOfLoans") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaxNumberOfLoans.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaxNumberOfLoans[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaxNumberOfLoans[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).MaxNumberOfLoans[i], 1)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaxNumberOfLoans.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.loan, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaxNumOfAlliances != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaxNumOfAlliances") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaxNumOfAlliances.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaxNumOfAlliances[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaxNumOfAlliances[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).MaxNumOfAlliances[i], 1)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaxNumOfAlliances.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).AdvisorMaxLevel != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumAdvisorSkillLevel") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).AdvisorMaxLevel.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).AdvisorMaxLevel[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).AdvisorMaxLevel[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).AdvisorMaxLevel[i], 1)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).AdvisorMaxLevel.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.skill, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).AdvisorPoolSize != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorPool") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).AdvisorPoolSize.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).AdvisorPoolSize[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).AdvisorPoolSize[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).AdvisorPoolSize[i], 1)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).AdvisorPoolSize.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.council, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).AdvisorCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).AdvisorCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).AdvisorCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).AdvisorCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).AdvisorCost[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).AdvisorCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.council, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademyForGenerals != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademyForGenerals.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademyForGenerals[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademyForGenerals[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademyForGenerals[i], 1)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademyForGenerals.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademy != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumLevelOfTheMilitaryAcademy") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademy.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademy[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademy[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademy[i], 10)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheMilitaryAcademy.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheSupremeCourt != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumLevelOfTheSupremeCourt") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheSupremeCourt.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheSupremeCourt[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheSupremeCourt[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheSupremeCourt[i], 10)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaximumLevelOfTheSupremeCourt.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.stability, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaximumLevelOfCapitalCity != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumLevelOfCapitalCity") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaximumLevelOfCapitalCity.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaximumLevelOfCapitalCity[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaximumLevelOfCapitalCity[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).MaximumLevelOfCapitalCity[i], 10)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaximumLevelOfCapitalCity.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).GeneralCost != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GeneralCost") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).GeneralCost.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).GeneralCost[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).GeneralCost[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).GeneralCost[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).GeneralCost.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).AggressiveExpansion != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AggressiveExpansion") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).AggressiveExpansion.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).AggressiveExpansion[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).AggressiveExpansion[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).AggressiveExpansion[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).AggressiveExpansion.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.war, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).DiseaseDeathRate != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DiseasesDeathRate") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).DiseaseDeathRate.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).DiseaseDeathRate[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).DiseaseDeathRate[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).DiseaseDeathRate[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).DiseaseDeathRate.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.disease, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).RecruitmentTime != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RecruitmentTime") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).RecruitmentTime.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).RecruitmentTime[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).RecruitmentTime[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).RecruitmentTime[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).RecruitmentTime.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_TIME, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).ArmyMovementSpeed != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyMovementSpeed") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).ArmyMovementSpeed.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).ArmyMovementSpeed[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).ArmyMovementSpeed[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).ArmyMovementSpeed[i], 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).ArmyMovementSpeed.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.movementSpeed, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).BuildingSlot != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdditionalBuildingsInProvince") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).BuildingSlot.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).BuildingSlot[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).BuildingSlot[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).BuildingSlot[i], 1)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).BuildingSlot.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).MaxInfrastructure != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumInfrastructureLevel") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).MaxInfrastructure.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).MaxInfrastructure[i] == 0) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).MaxInfrastructure[i] > 0) ? "+" : "") + CFG.getPrecision2((float)LegacyManager.legacies.get(this.legacyID).MaxInfrastructure[i], 10)), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).MaxInfrastructure.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.infrastructure, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (LegacyManager.legacies.get(this.legacyID).Devastation != null) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Devastation") + ": ", CFG.FONT_REGULAR_SMALL));
            for (int i = 0; i < LegacyManager.legacies.get(this.legacyID).Devastation.length; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Text((LegacyManager.legacies.get(this.legacyID).Devastation[i] == 0.0f) ? "-" : ("" + ((LegacyManager.legacies.get(this.legacyID).Devastation[i] > 0.0f) ? "+" : "") + CFG.getPrecision2(LegacyManager.legacies.get(this.legacyID).Devastation[i] * 100.0f, 10) + "%"), (i == this.currentLvl) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL, (i == this.currentLvl) ? Colors.HOVER_POSITIVE : Colors.HOVER_RIGHT));
                if (i != LegacyManager.legacies.get(this.legacyID).Devastation.length - 1) {
                    nData.add(new MenuElement_HoverElement_Type_Text(" / ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                }
            }
            nData.add(new MenuElement_HoverElement_Type_Image(Images.devastation, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (this.currentLvl + 1 >= LegacyManager.legacies.get(this.legacyID).CostLegacy.length) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Max"), CFG.FONT_REGULAR_SMALL));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + LegacyManager.legacies.get(this.legacyID).CostLegacy[this.currentLvl + 1], CFG.FONT_BOLD_SMALL, (Game.getCiv(Game.player.iCivID).fLegacy >= LegacyManager.legacies.get(this.legacyID).CostLegacy[this.currentLvl + 1]) ? Colors.HOVER_GOLD : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
