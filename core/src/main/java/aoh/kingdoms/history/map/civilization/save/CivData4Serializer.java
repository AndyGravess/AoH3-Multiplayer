// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.save;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class CivData4Serializer implements Json.Serializer<CivData4>
{
    public void write(final Json json, final CivData4 data, final Class knownType) {
        json.writeObjectStart();
        if (data.c != 0) {
            json.writeValue("c", (Object)data.c);
        }
        if (data.g != 0) {
            json.writeValue("g", (Object)data.g);
        }
        if (data.m != 0) {
            json.writeValue("m", (Object)data.m);
        }
        if (data.s != 0) {
            json.writeValue("s", (Object)data.s);
        }
        if (data.n != 0) {
            json.writeValue("n", (Object)data.n);
        }
        if (data.u != 0) {
            json.writeValue("u", (Object)data.u);
        }
        if (data.b != 0) {
            json.writeValue("b", (Object)data.b);
        }
        if (data.t != 1) {
            json.writeValue("t", (Object)data.t);
        }
        if (data.r != 1) {
            json.writeValue("r", (Object)data.r);
        }
        if (data.y != 1) {
            json.writeValue("y", (Object)data.y);
        }
        if (data.e != -1) {
            json.writeValue("e", (Object)data.e);
        }
        if (data.d != 0) {
            json.writeValue("d", (Object)data.d);
        }
        if (data.v != 0) {
            json.writeValue("v", (Object)data.v);
        }
        json.writeObjectEnd();
    }
    
    public CivData4 read(final Json json, final JsonValue jsonData, final Class type) {
        final CivData4 out = new CivData4();
        return out;
    }
}
