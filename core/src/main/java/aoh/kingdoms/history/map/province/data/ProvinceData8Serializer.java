// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class ProvinceData8Serializer implements Json.Serializer<ProvinceData8>
{
    public void write(final Json json, final ProvinceData8 data, final Class knownType) {
        json.writeObjectStart();
        if (data.w) {
            json.writeValue("w", (Object)data.w);
        }
        if (data.r != 0.0f) {
            json.writeValue("r", (Object)data.r);
        }
        json.writeObjectEnd();
    }
    
    public ProvinceData8 read(final Json json, final JsonValue jsonData, final Class type) {
        final ProvinceData8 out = new ProvinceData8();
        return out;
    }
}
