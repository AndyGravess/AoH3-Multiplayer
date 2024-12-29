// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.civilization.CivilizationAdvisorsPool;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_AddAdvisor extends EventOutcome
{
    public int advisorTypeID;
    
    public EventOutcome_AddAdvisor(final int advisorTypeID) {
        this.advisorTypeID = advisorTypeID;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            switch (this.advisorTypeID) {
                case 0: {
                    if (Game.getCiv(iCivID).advisorAdministration.sName != null) {
                        final AdvisorManager advisorManager = Game.advisorManager;
                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, -1);
                    }
                    Game.getCiv(iCivID).advisorAdministration = CivilizationAdvisorsPool.generateAdvisor_Random(iCivID, this.advisorTypeID);
                    final AdvisorManager advisorManager2 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, 1);
                    break;
                }
                case 1: {
                    if (Game.getCiv(iCivID).advisorEconomy.sName != null) {
                        final AdvisorManager advisorManager3 = Game.advisorManager;
                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, -1);
                    }
                    Game.getCiv(iCivID).advisorEconomy = CivilizationAdvisorsPool.generateAdvisor_Random(iCivID, this.advisorTypeID);
                    final AdvisorManager advisorManager4 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, 1);
                    break;
                }
                case 2: {
                    if (Game.getCiv(iCivID).advisorTechnology.sName != null) {
                        final AdvisorManager advisorManager5 = Game.advisorManager;
                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, -1);
                    }
                    Game.getCiv(iCivID).advisorTechnology = CivilizationAdvisorsPool.generateAdvisor_Random(iCivID, this.advisorTypeID);
                    final AdvisorManager advisorManager6 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, 1);
                    break;
                }
                default: {
                    if (Game.getCiv(iCivID).advisorMilitary.sName != null) {
                        final AdvisorManager advisorManager7 = Game.advisorManager;
                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, -1);
                    }
                    Game.getCiv(iCivID).advisorMilitary = CivilizationAdvisorsPool.generateAdvisor_Random(iCivID, this.advisorTypeID);
                    final AdvisorManager advisorManager8 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, 1);
                    break;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("HireAdvisor") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return "" + AdvisorManager.getAdvisorGroupName(this.advisorTypeID);
    }
    
    @Override
    public int getImage() {
        return Images.council;
    }
}
