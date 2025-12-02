package com.sidden.creepository.datagen;

import com.google.common.collect.ImmutableList;

import java.util.List;

public enum SculptureDesign {
    FACE("face", 0),
    HOLE("holes", 1),
    RUNE("rune", 2),
    SCRIPTURE("scripture", 3),
    NONE("blank", 4);


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
    public static int totalDesigns(){
        return getDesigns().toArray().length == 0 ? 0: getDesigns().toArray().length - 1;
    }

    public record Design(String name, int Id){}

}
