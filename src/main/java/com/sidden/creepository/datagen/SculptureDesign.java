package com.sidden.creepository.datagen;

import com.google.common.collect.ImmutableList;

import java.util.List;

public enum SculptureDesign {
    NONE("blank", 0),
    FACE("face", 1),
    HOLE("holes", 2),
    RUNE("rune", 3),
    SCRIPTURE("scripture", 4);

    public String Name;
    public int Id;

    public List<Design> All;
    SculptureDesign(String name, int Id){
        this.Name= name;
        this.Id = Id;


        this.All = ImmutableList.copyOf(new Design[]{new Design(name, Id)});
    }

    public static List<SculptureDesign> getDesigns() {
        List list;
        list = ImmutableList.of(NONE, FACE, HOLE, RUNE, SCRIPTURE);

        return list;
    }


    public record Design(String name, int Id){}

}
