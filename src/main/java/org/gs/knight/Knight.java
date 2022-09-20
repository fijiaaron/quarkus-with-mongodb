package org.gs.knight;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Knight extends PanacheMongoEntity {
    public String name;
    public String sword;

    public Knight() {

    }

    public Knight(String name, String sword) {
        this.name = name;
        this.sword = sword; 
    }

    public static final TypeReference<ArrayList<Knight>> listType = new TypeReference<ArrayList<Knight>>() {};
    public static final TypeReference<Knight> knightType = new TypeReference<Knight>() {};
}
