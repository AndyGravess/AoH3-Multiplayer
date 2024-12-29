// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map.Waves;

import java.util.ArrayList;
import java.util.List;

public class WavesManager
{
    public static List<WavesLine> wavesLines;
    public static int wavesLinesSize;
    
    public static final void addWavesLine(final WavesLine wavesLine) {
        WavesManager.wavesLines.add(wavesLine);
        WavesManager.wavesLinesSize = WavesManager.wavesLines.size();
    }
    
    static {
        WavesManager.wavesLines = new ArrayList<WavesLine>();
        WavesManager.wavesLinesSize = 0;
    }
}
