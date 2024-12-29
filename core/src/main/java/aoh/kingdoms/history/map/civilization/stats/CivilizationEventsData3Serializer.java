// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.stats;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class CivilizationEventsData3Serializer implements Json.Serializer<CivilizationEventsData3>
{
    public void write(final Json json, final CivilizationEventsData3 object, final Class knownType) {
        json.writeObjectStart();
        if (object.a != 0) {
            json.writeValue("a", (Object)object.a);
        }
        if (object.p != 0) {
            json.writeValue("p", (Object)object.p);
        }
        json.writeObjectEnd();
    }
    
    public CivilizationEventsData3 read(final Json json, final JsonValue jsonData, final Class type) {
        final CivilizationEventsData3 data = new CivilizationEventsData3();
        data.a = jsonData.getInt("a", 0);
        data.p = jsonData.getInt("p", 0);
        return data;
    }
}
