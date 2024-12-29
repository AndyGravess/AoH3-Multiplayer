// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.More;

import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvincesBG;
import aoc.kingdoms.lukasz.map.province.Province;
import java.util.ArrayList;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class PlayerFogOfWar
{
    public void initFogOfWar() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).setFogDrawArmy(false);
        }
    }
    
    public final void buildFogOfWar(final int civID) {
        try {
            if (Game.FOG_OF_WAR) {
                for (int i = 0; i < Game.getCivsSize(); ++i) {
                    Game.getCiv(i).isPlayerAlly = false;
                }
                Game.getCiv(Game.player.iCivID).isPlayerAlly = true;
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    Game.getProvince(i).setFogDrawArmy(false);
                }
                this.buildFogOfWar_CivID(civID);
                final List<Integer> civAllies = DiplomacyManager.civAllies(civID);
                for (int j = civAllies.size() - 1; j >= 0; --j) {
                    Game.getCiv(civAllies.get(j)).isPlayerAlly = true;
                }
                for (int j = civAllies.size() - 1; j >= 0; --j) {
                    this.buildFogOfWar_CivID(civAllies.get(j));
                }
            }
            else {
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    Game.getProvince(i).setFogDrawArmy(true);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game.updateDrawArmy();
    }
    
    public final void buildFogOfWar_CivID(final int civID) {
        if (Game.FOG_OF_WAR) {
            for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                Game.getProvince(Game.getCiv(civID).getProvinceID(i)).setFogDrawArmy(true);
                for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                    Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringProvinces(j)).setFogDrawArmy(true);
                }
                for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringSeaProvincesSize(); ++j) {
                    Game.getProvince(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getNeighboringSeaProvinces(j)).setFogDrawArmy(true);
                }
            }
            for (int i = 0; i < Game.getCiv(civID).iArmyPositionSize; ++i) {
                Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).setFogDrawArmy(true);
                for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getNeighboringProvincesSize(); ++j) {
                    Game.getProvince(Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getNeighboringProvinces(j)).setFogDrawArmy(true);
                }
                for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getNeighboringProvincesSize(); ++j) {
                    for (int k = 0; k < Game.getProvince(Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getNeighboringProvinces(j)).getNeighboringProvincesSize(); ++k) {
                        Game.getProvince(Game.getProvince(Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getNeighboringProvinces(j)).getNeighboringProvinces(k)).setFogDrawArmy(true);
                    }
                }
                for (int j = 0; j < Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getNeighboringSeaProvincesSize(); ++j) {
                    Game.getProvince(Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getNeighboringSeaProvinces(j)).setFogDrawArmy(true);
                }
            }
        }
    }
    
    public final void updateFogOfWar_Civ(final int civID) {
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            this.updateFogOfWar_All(Game.getCiv(civID).getProvinceID(i));
        }
        for (int i = 0; i < Game.getCiv(civID).iArmyPositionSize; ++i) {
            if (Game.getProvince(Game.getCiv(civID).getArmyPosition(i)).getCivID() != civID) {
                this.updateFogOfWar_All(Game.getCiv(civID).getArmyPosition(i));
            }
        }
    }
    
    public final void updateFogOfWar_All_ArmyOneProvinceView(final int provinceID) {
        if (Game.FOG_OF_WAR) {
            if (Game.getCiv(Game.getProvince(provinceID).getCivID()).isPlayerAlly) {
                this.setFogOfWar(provinceID, true);
                return;
            }
            this.updateFogOfWar(provinceID);
            for (int i = 0; i < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++i) {
                this.updateFogOfWar(Game.getProvince(provinceID).getNeighboringProvinces(i));
            }
            for (int i = 0; i < Game.getProvince(provinceID).getNeighboringSeaProvincesSize(); ++i) {
                this.updateFogOfWar(Game.getProvince(provinceID).getNeighboringSeaProvinces(i));
            }
        }
    }
    
    public final void updateFogOfWar_All(final int provinceID) {
        final List<Integer> toUpdate = new ArrayList<Integer>();
        toUpdate.add(provinceID);
        for (int i = 0; i < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++i) {
            if (!toUpdate.contains(Game.getProvince(provinceID).getNeighboringProvinces(i))) {
                toUpdate.add(Game.getProvince(provinceID).getNeighboringProvinces(i));
            }
        }
        for (int i = 0; i < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++i) {
            for (int j = 0; j < Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getNeighboringProvincesSize(); ++j) {
                if (!toUpdate.contains(Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getNeighboringProvinces(j))) {
                    toUpdate.add(Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getNeighboringProvinces(j));
                }
            }
        }
        for (int i = 0; i < Game.getProvince(provinceID).getNeighboringSeaProvincesSize(); ++i) {
            if (!toUpdate.contains(Game.getProvince(provinceID).getNeighboringSeaProvinces(i))) {
                toUpdate.add(Game.getProvince(provinceID).getNeighboringSeaProvinces(i));
            }
        }
        for (int i = toUpdate.size() - 1; i >= 0; --i) {
            this.updateFogOfWar(toUpdate.get(i));
        }
        toUpdate.clear();
    }
    
    public final void updateFogOfWar(final int provinceID) {
        try {
            if (Game.FOG_OF_WAR) {
                final Province province = Game.getProvince(provinceID);
                if (Game.getCiv(province.getCivID()).isPlayerAlly) {
                    this.setFogOfWar(provinceID, true);
                    return;
                }
                for (int i = 0; i < province.getArmySize(); ++i) {
                    if (Game.getCiv(province.getArmy(i).civID).isPlayerAlly) {
                        this.setFogOfWar(provinceID, true);
                        return;
                    }
                }
                for (int i = 0; i < province.getNeighboringProvincesSize(); ++i) {
                    if (Game.getCiv(Game.getProvince(province.getNeighboringProvinces(i)).getCivID()).isPlayerAlly) {
                        this.setFogOfWar(provinceID, true);
                        return;
                    }
                }
                for (int i = 0; i < province.getNeighboringProvincesSize(); ++i) {
                    for (int j = 0; j < Game.getProvince(province.getNeighboringProvinces(i)).getArmySize(); ++j) {
                        if (Game.getCiv(Game.getProvince(province.getNeighboringProvinces(i)).getArmy(j).civID).isPlayerAlly) {
                            this.setFogOfWar(provinceID, true);
                            return;
                        }
                    }
                }
                for (int i = 0; i < province.getNeighboringProvincesSize(); ++i) {
                    for (int j = 0; j < Game.getProvince(province.getNeighboringProvinces(i)).getArmySize(); ++j) {
                        if (Game.getCiv(Game.getProvince(province.getNeighboringProvinces(i)).getArmy(j).civID).isPlayerAlly) {
                            this.setFogOfWar(provinceID, true);
                            return;
                        }
                    }
                }
                for (int i = 0; i < province.getNeighboringProvincesSize(); ++i) {
                    for (int k = 0; k < Game.getProvince(province.getNeighboringProvinces(i)).getNeighboringProvincesSize(); ++k) {
                        for (int l = 0; l < Game.getProvince(Game.getProvince(province.getNeighboringProvinces(i)).getNeighboringProvinces(k)).getArmySize(); ++l) {
                            if (Game.getCiv(Game.getProvince(Game.getProvince(province.getNeighboringProvinces(i)).getNeighboringProvinces(k)).getArmy(l).civID).isPlayerAlly) {
                                this.setFogOfWar(provinceID, true);
                                return;
                            }
                        }
                    }
                }
                for (int i = 0; i < province.getNeighboringSeaProvincesSize(); ++i) {
                    for (int j = 0; j < Game.getProvince(province.getNeighboringSeaProvinces(i)).getArmySize(); ++j) {
                        if (Game.getCiv(Game.getProvince(province.getNeighboringSeaProvinces(i)).getArmy(j).civID).isPlayerAlly) {
                            this.setFogOfWar(provinceID, true);
                            return;
                        }
                    }
                }
                this.setFogOfWar(provinceID, false);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void setFogOfWar(final int provinceID, final boolean state) {
        if (Game.getProvince(provinceID).getFogDrawArmy() != state) {
            Game.getProvince(provinceID).setFogDrawArmy(state);
            Game.getProvince(provinceID).updateDrawArmy();
            if (Game.getProvince(provinceID).getDrawProvince()) {
                FBOProvincesBG.redrawnProvinces();
            }
        }
    }
    
    public final void setFogOfWar_ExtraCheck(final int i, final boolean isVisible) {
        try {
            Game.getProvince(i).setFogDrawArmy(isVisible || Game.getProvince(i).haveArmy(Game.player.iCivID));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
