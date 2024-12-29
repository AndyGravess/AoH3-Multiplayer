// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class ProvinceData2Serializer implements Json.Serializer<ProvinceData2>
{
    public void write(final Json json, final ProvinceData2 data, final Class knownType) {
        json.writeObjectStart();
        if (data.d != 0.0f) {
            json.writeValue("d", (Object)data.d);
        }
        if (data.l != 1.0f) {
            json.writeValue("l", (Object)data.l);
        }
        json.writeObjectEnd();
    }
    
    public ProvinceData2 read(final Json json, final JsonValue jsonData, final Class type) {
        final ProvinceData2 out = new ProvinceData2();
        return out;
    }
}
