package me.ufida;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ExampleDaoGenerator {
    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1,"me.ufida.greendao");

        addNode(schema);

        new DaoGenerator().generateAll(schema,"../AppLearn/mygreendao/src/main/java-gen");
    }

    private  static  void addNode(Schema schema){
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }
}
