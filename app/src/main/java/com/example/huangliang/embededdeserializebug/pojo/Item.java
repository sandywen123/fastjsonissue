package com.example.huangliang.embededdeserializebug.pojo;


/**
 * Created by huangliang on 17/5/8.
 */

public class Item implements Area {
    public String name;

    @Override
    public String getName() {
        return name;
    }
}
