package se.backend.RecipeObject;

import java.util.ArrayList;

public class ExtendedIngredient {
    public int id;
    public String aisle;
    public String image;
    public String name;
    public double amount;
    public String unit;
    public String unitShort;
    public String unitLong;
    public String originalString;
    public ArrayList<String> metaInformation;

    public Measures measures;
}

 class Measures{
    public Us us;
    public Metric metric;
}

 class Metric{
    public double amount;
    public String unitShort;
    public String unitLong;
}

class Us{
    public double amount;
    public String unitShort;
    public String unitLong;
}
