package com.example.huangliang.embededdeserializebug.pojo;

import java.util.List;

/**
 * Created by huangliang on 17/5/8.
 */

public class Floor implements Area {
    public List<Area> children;

    public String name;

    @Override
    public String getName() {
        return name;
    }
}
