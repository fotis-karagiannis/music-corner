package basics;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * Abstract class containing fields/methods
 * that are present in both Person and Group class.
 * 
 */
public abstract class Artist implements Serializable
{
    // Artist Fields //
    
    protected String name;
    protected String country;
    protected String artistID;
    protected ArrayList<String> cities;
    protected ArrayList<String> aliases;
    protected ArrayList<String> tags;
    
    // Artist Methods //
    
    // Field Getters //
    public String getName()
    {
        return this.name;
    }
    public String getCountry()
    {
        return this.country;
    }
    public String getArtistID()
    {
        return this.artistID;
    }
    public ArrayList<String> getCities()
    {
        return this.cities; 
    }
    public ArrayList<String> getAliases()
    {
        return this.aliases;
    }
    public ArrayList<String> getTags()
    {
        return this.tags;
    }
    
    // Field Setters //
    public void setName(String name)
    {
        this.name = name;  
    }
    public void setCountry(String country)
    {
        this.country = country;
    }
    public void setArtistID(String artistID)
    {
        this.artistID = artistID;
    }
    
    // List Fillers //
    /**
     * 
     * @param city : Adds the city argument to cities ArrayList.
     * 
     */
    public void addCity(String city)
    {
        this.cities.add(city);
    }
    /**
     * 
     * @param alias : Adds the alias argument to aliases ArrayList.
     * 
     */
    public void addAlias(String alias)
    {
        this.aliases.add(alias);
    }
    /**
     * 
     * @param tag : Adds the tag argument to tags ArrayList.
     * 
     */
    public void addTag(String tag)
    {
        this.tags.add(tag);
    }  
          
    // Group method declared to be overriden by group class. //
    // Declared so it can be used by Artist objects that are groups. // 
    // Nothing is done when called by Artist object that is a person. //
    public void addMember(Artist member){}
}