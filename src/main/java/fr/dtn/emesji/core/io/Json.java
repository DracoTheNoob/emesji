package fr.dtn.emesji.core.io;

import fr.dtn.jll.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

public class Json {
    private static final String SPLITTER = "/";
    private final File file;
    private JSONObject json;

    public Json(){
        this.file = null;
        this.json = new JSONObject();
    }

    public Json(File file){
        this.file = file;

        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            this.json = (JSONObject) new JSONParser().parse(reader);
        }catch(IOException | ParseException e){
            e.printStackTrace();
        }

        Log.info("'" + file.getPath() + "' loaded");
    }

    private Json(JSONObject object){
        this.file = null;
        this.json = object;
    }

    public <E> E get(String key, Class<E> dataType, E ifNull){
        key = key.replace(".", SPLITTER);

        if(!key.contains(SPLITTER)){
            Object value = json.get(key);

            if(value == null)
                return ifNull;

            if(dataType.isInstance(value))
                return dataType.cast(value);

            return ifNull;
        }

        try{
            String[] keys = key.split(SPLITTER);
            JSONObject value = (JSONObject) json.get(keys[0]);

            for(int i = 1; i < keys.length - 1; i++)
                value = (JSONObject) value.get(keys[i]);

            if(value == null)
                return ifNull;

            String last = keys[keys.length - 1];

            Object lastValue = value.get(last);

            if(dataType == Integer.class)
                return dataType.cast(Integer.parseInt(lastValue.toString()));
            if(dataType == Long.class)
                return dataType.cast(Long.parseLong(lastValue.toString()));
            if(dataType == Float.class)
                return dataType.cast(Float.parseFloat(lastValue.toString()));
            if(dataType == Double.class)
                return dataType.cast(Double.parseDouble(lastValue.toString()));
            if(dataType == Boolean.class)
                return dataType.cast(Boolean.parseBoolean(lastValue.toString()));
            if(dataType == Byte.class)
                return dataType.cast(Byte.parseByte(lastValue.toString()));
            if(dataType == Short.class)
                return dataType.cast(Short.parseShort(lastValue.toString()));

            if(dataType.isInstance(lastValue))
                return dataType.cast(lastValue);
        }catch(NullPointerException ignored){}

        return ifNull;
    }

    public <E> List<E> getList(String key, Class<E> a, List<E> ifNull){
        key = key.replace(".", SPLITTER);

        try{
            if(!key.contains(SPLITTER)){
                Object value = json.get(key);

                if(value == null)
                    return ifNull;

                JSONArray list = (JSONArray) value;
                return (List<E>) list;
            }

            String[] keys = key.split(SPLITTER);
            JSONObject value = (JSONObject) json.get(keys[0]);

            for(int i = 1; i < keys.length - 1; i++)
                value = (JSONObject) value.get(keys[i]);

            if(value == null)
                return ifNull;

            String last = keys[keys.length - 1];

            JSONArray list = (JSONArray) value.get(last);
            return (List<E>) list;
        }catch(NullPointerException ignored){}

        return ifNull;
    }

    public Json getJson(String key, Json ifNull){
        key = key.replace(".", SPLITTER);

        try{
            if(!key.contains(SPLITTER)){
                Object value = json.get(key);

                if(value == null)
                    return ifNull;

                return new Json((JSONObject) value);
            }

            String[] keys = key.split(SPLITTER);
            JSONObject value = (JSONObject) json.get(keys[0]);

            for(int i = 1; i < keys.length - 1; i++)
                value = (JSONObject) value.get(keys[i]);

            if(value == null)
                return ifNull;

            String last = keys[keys.length - 1];
            return new Json((JSONObject) value.get(last));
        }catch(NullPointerException ignored){}

        return ifNull;
    }

    public void set(String key, Object value){
        key = key.replace(".", SPLITTER);

        if(key.contains(SPLITTER))
            throw new RuntimeException("This class does not support complex keys for setting values");

        this.json.put(key, value);
    }

    public <E> E get(String key, Class<E> dataType){ return get(key, dataType, null); }
    public <E> List<E> getList(String key, Class<E> dataType){ return getList(key, dataType, null); }
    public <E> Json getJson(String key){ return getJson(key, null); }

    public String getString(String key){ return get(key, String.class); }
    public int getInt(String key){ return get(key, Integer.class); }
    public byte getByte(String key){ return get(key, Byte.class); }
    public short getShort(String key){ return get(key, Short.class); }
    public long getLong(String key){ return get(key, Long.class); }
    public float getFloat(String key){ return get(key, Float.class); }
    public double getDouble(String key){ return get(key, Double.class); }
    public boolean getBoolean(String key){ return get(key, Boolean.class); }

    public String getString(String key, String ifNull){ return get(key, String.class, ifNull); }
    public int getInt(String key, Integer ifNull){ return get(key, Integer.class, ifNull); }
    public byte getByte(String key, Byte ifNull){ return get(key, Byte.class, ifNull); }
    public short getShort(String key, Short ifNull){ return get(key, Short.class, ifNull); }
    public long getLong(String key, Long ifNull){ return get(key, Long.class, ifNull); }
    public float getFloat(String key, Float ifNull){ return get(key, Float.class, ifNull); }
    public double getDouble(String key, Double ifNull){ return get(key, Double.class, ifNull); }
    public boolean getBoolean(String key, Boolean ifNull){ return get(key, Boolean.class, ifNull); }

    @Override
    public String toString(){
        return json.toJSONString();
    }

    public JSONObject toJsonObject(){
        try {
            return (JSONObject) new JSONParser().parse(json.toJSONString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}