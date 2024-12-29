// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.battles;

public class BattleReport
{
    public String key;
    public CivReport civReportLeft;
    public CivReport civReportRight;
    public float fWarScore;
    public boolean leftSideWon;
    public boolean playerWon;
    public int iProvinceID;
    public int iTurnID;
    
    public BattleReport(final String key, final int iProvinceID, final float fWarScore, final boolean leftSideWon, final boolean playerWon, final int iTurnID, final CivReport civReportLeft, final CivReport civReportRight) {
        this.key = key;
        this.iProvinceID = iProvinceID;
        this.fWarScore = fWarScore;
        this.leftSideWon = leftSideWon;
        this.playerWon = playerWon;
        this.civReportLeft = civReportLeft;
        this.civReportRight = civReportRight;
        this.iTurnID = iTurnID;
    }
    
    public static class CivReport
    {
        public int iCivID;
        public int iSoldiers;
        public int iCasualties;
        public int iRetreated;
        
        public CivReport(final int iCivID, final int iSoldiers, final int iCasualties, final int iRetreated) {
            this.iCivID = iCivID;
            this.iSoldiers = iSoldiers;
            this.iCasualties = iCasualties;
            this.iRetreated = iRetreated;
        }
    }
}
