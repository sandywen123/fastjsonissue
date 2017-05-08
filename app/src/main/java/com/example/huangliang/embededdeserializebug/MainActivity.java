package com.example.huangliang.embededdeserializebug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.example.huangliang.embededdeserializebug.pojo.Area;
import com.example.huangliang.embededdeserializebug.pojo.Data;
import com.example.huangliang.embededdeserializebug.pojo.Floor;
import com.example.huangliang.embededdeserializebug.pojo.Item;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    static String jsonData = "{\n" +
            "    \"areaList\":[\n" +
            "        {\n" +
            "            \"type\":\"floor\",\n" +
            "            \"name\":\"I'm floor\",\n" +
            "            \"children\":[{\n" +
            "                \"type\":\"item\",\n" +
            "            \"name\":\"I'm item 0\"\n" +
            "            },\n" +
            "        {\n" +
            "              \"type\":\"item\",\n" +
            "            \"name\":\"I'm item 1\"\n" +
            "        }\n" +
            "\n" +
            "            ]\n" +
            "        },{\n" +
            "            \"type\":\"item\",\n" +
            "            \"name\":\"I'm item 2\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectDeserialize();

        Data data = JsonUtil.json2pojo(jsonData, Data.class);

        Item item = (Item) ((Floor)(data.areaList.get(0))).children.get(0);

    }

    void injectDeserialize(){
        ParserConfig.getGlobalInstance().putDeserializer(Area.class, new ObjectDeserializer() {
            @Override
            public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
                JSONObject jsonObject = (JSONObject) parser.parse();
                String areaType;

                if (jsonObject.get("type") instanceof String) {
                    areaType = (String) jsonObject.get("type");
                } else {
                    return null;
                }
                if (Area.TYPE_FLOOR.equals(areaType)) {
                    return (T) JSON.toJavaObject(jsonObject, Floor.class);
                } else if (Area.TYPE_ITEM.equals(areaType)) {
                    return (T) JSON.toJavaObject(jsonObject, Item.class);
                }

                return null;
            }

            @Override
            public int getFastMatchToken() {
                return JSONToken.LBRACE;
            }
        });
    }
}
