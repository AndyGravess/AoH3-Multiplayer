// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusEditor;

import aoh.kingdoms.history.menu.View;
import com.badlogic.gdx.files.FileHandle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.menu_element.button.ButtonMainReverse;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonMain;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class GameCivsAlphabet extends Menu
{
    private List<Character> lCharacters;
    private String nSearch;
    
    public GameCivsAlphabet() {
        this.lCharacters = new ArrayList<Character>();
        this.nSearch = "";
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int nPosX = 0;
        menuElements.add(new ButtonMain("", 1, -1, nPosX, 0, CFG.BUTTON_WIDTH * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("AddCivilization"));
            }
        });
        nPosX += menuElements.get(menuElements.size() - 1).getWidth();
        final Keyboard keyboard = Game.keyboard;
        if (Keyboard.keyboardMode) {
            menuElements.add(new ButtonMainReverse("", 1, -1, nPosX, 0, CFG.BUTTON_WIDTH * 2, true) {
                @Override
                public void updateLanguage() {
                    if (GameCivs.sSearch.length() > 0) {
                        this.setText(Game.lang.get("Search") + ": " + GameCivs.sSearch);
                    }
                    else {
                        this.setText(Game.lang.get("Search"));
                    }
                }
                
                @Override
                public String getTextToDraw() {
                    return super.getTextToDraw() + Keyboard.getKeyboardVerticalLine();
                }
            });
        }
        else {
            menuElements.add(new ButtonMain("", 1, -1, nPosX, 0, CFG.BUTTON_WIDTH * 2, true) {
                @Override
                public void updateLanguage() {
                    if (GameCivs.sSearch.length() > 0) {
                        this.setText(Game.lang.get("Search") + ": " + GameCivs.sSearch);
                    }
                    else {
                        this.setText(Game.lang.get("Search"));
                    }
                }
            });
        }
        nPosX += menuElements.get(menuElements.size() - 1).getWidth();
        final Keyboard keyboard2 = Game.keyboard;
        if (!Keyboard.keyboardMode && GameCivs.chosen_AlphabetCharachter.length() == 0) {
            menuElements.add(new ButtonMainReverse("", 0, -1, nPosX, 0, CFG.BUTTON_WIDTH * 2, true) {
                @Override
                public void updateLanguage() {
                    this.setText(Game.lang.get("AllCivilizations"));
                }
            });
        }
        else {
            menuElements.add(new ButtonMain("", 1, -1, nPosX, 0, CFG.BUTTON_WIDTH * 2, true) {
                @Override
                public void updateLanguage() {
                    this.setText(Game.lang.get("AllCivilizations"));
                }
            });
        }
        nPosX += menuElements.get(menuElements.size() - 1).getWidth();
        final FileHandle tempFileT = FileManager.loadFile("game/Civilizations.txt");
        final String tempT = tempFileT.readString();
        final String[] tagsSPLITED = tempT.split(";");
        for (int i = 0, iSize = tagsSPLITED.length; i < iSize; ++i) {
            boolean addChar = true;
            for (int a = 0; a < this.lCharacters.size(); ++a) {
                if (this.lCharacters.get(a) == Game.lang.getCiv(tagsSPLITED[i]).charAt(0)) {
                    addChar = false;
                    break;
                }
            }
            if (addChar) {
                this.lCharacters.add(Game.lang.getCiv(tagsSPLITED[i]).charAt(0));
            }
        }
        for (int i = 0; i < this.lCharacters.size() - 1; ++i) {
            for (int j = i + 1; j < this.lCharacters.size(); ++j) {
                if (this.lCharacters.get(i) > this.lCharacters.get(j)) {
                    final char temp = this.lCharacters.get(i);
                    this.lCharacters.set(i, this.lCharacters.get(j));
                    this.lCharacters.set(j, temp);
                }
            }
        }
        for (int i = 0; i < this.lCharacters.size(); ++i) {
            if (GameCivs.chosen_AlphabetCharachter.length() > 0 && this.lCharacters.get(i) == GameCivs.chosen_AlphabetCharachter.charAt(0)) {
                menuElements.add(new ButtonMainReverse("[" + this.lCharacters.get(i) + "]", 0, -1, nPosX, 0, CFG.BUTTON_HEIGHT, true));
                nPosX += menuElements.get(menuElements.size() - 1).getWidth();
            }
            else {
                menuElements.add(new ButtonMain("[" + this.lCharacters.get(i) + "]", 1, -1, nPosX, 0, CFG.BUTTON_HEIGHT, true));
                nPosX += menuElements.get(menuElements.size() - 1).getWidth();
            }
        }
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, true);
    }
    
    @Override
    public void actionElement(final int nMenuElementID) {
        switch (nMenuElementID) {
            case 0: {
                GameCivsEdit.nCiv = new Game.LoadCivilizationData();
                GameCivsEdit.nCiv.Tag = "";
                GameCivsEdit.nCiv.iR = Game.oR.nextInt(255);
                GameCivsEdit.nCiv.iG = Game.oR.nextInt(255);
                GameCivsEdit.nCiv.iB = Game.oR.nextInt(255);
                GameCivsEdit.nCiv.Wiki = "";
                GameCivsEdit.goBackTo = View.EDITOR_GAMECIVS;
                Game.menuManager.setViewID(View.EDITOR_GAMECIVS_EDIT);
                return;
            }
            case 1: {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.SEARCH_GAMECIVS, GameCivs.sSearch);
                Game.menuManager.rebuildEditorGameCivs();
                return;
            }
            case 2: {
                if (GameCivs.chosen_AlphabetCharachter.length() <= 0 && GameCivs.sSearch.length() <= 0) {
                    final Keyboard keyboard = Game.keyboard;
                    if (!Keyboard.keyboardMode) {
                        return;
                    }
                }
                GameCivs.chosen_AlphabetCharachter = "";
                GameCivs.sSearch = "";
                Game.menuManager.setViewID(View.EDITOR_GAMECIVS);
                return;
            }
            default: {
                if (GameCivs.chosen_AlphabetCharachter.length() == 0 || GameCivs.sSearch.length() > 0 || (GameCivs.chosen_AlphabetCharachter.length() > 0 && GameCivs.chosen_AlphabetCharachter.charAt(0) != this.lCharacters.get(nMenuElementID - 3))) {
                    GameCivs.chosen_AlphabetCharachter = "" + this.lCharacters.get(nMenuElementID - 3);
                    GameCivs.sSearch = "";
                    Game.menuManager.setViewID(View.EDITOR_GAMECIVS);
                }
            }
        }
    }
}
