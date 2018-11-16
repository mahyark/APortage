package be.ap.edu.aportage;

public class Location {

    public String name;
    public String abbreviation;

    public Location() {}

    public Location(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString(){
        return this.name + " " + this.abbreviation;
    }

}
