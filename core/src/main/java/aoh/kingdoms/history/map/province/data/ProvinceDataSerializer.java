// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class ProvinceDataSerializer implements Json.Serializer<ProvinceData>
{
    public void write(final Json json, final ProvinceData data, final Class knownType) {
        json.writeObjectStart();
        if (data.c != 0) {
            json.writeValue("c", (Object)data.c);
        }
        if (data.o != 0) {
            json.writeValue("o", (Object)data.o);
        }
        if (data.w != -1) {
            json.writeValue("w", (Object)data.w);
        }
        json.writeObjectEnd();
    }
    
    public ProvinceData read(final Json json, final JsonValue jsonData, final Class type) {
        final ProvinceData out = new ProvinceData();
        return out;
    }
}
