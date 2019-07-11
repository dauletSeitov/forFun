package just.fo.fun.property.model;

public enum PropertyType {

    STRING("String"),
    BOOLEAN("Boolean"),
    LONG("Long");

    String name;

    PropertyType(String str){
        name = str;
    }
}
