package basics;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * Class describing a group Artist object
 * 
 */
public class Group extends Artist implements Serializable
{
    // Group-only Fields //
    
    private String beginDate;
    private String endDate;
    private ArrayList<Artist> members;
    
    // Group Methods //
    
    // Field Getters //
    public String getBeginDate()
    {
        return this.beginDate;  
    }
    public String getEndDate()
    {
        return this.endDate;
    }
    public ArrayList<Artist> getMembers()
    {
        return this.members;
    }
    
    // Field Setters //
    public void setBeginDate(String beginDate)
    {
        this.beginDate = beginDate;  
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
        
    // List Fillers //
    /**
     * 
     * @param member : Adds the member argument to members ArrayList.
     * 
     */
    public void addMember(Artist member)
    {
        this.members.add(member);
    }
    
    // Constructors //
    
    /**
     * Used as Default Class Constructor
     * Following arguments are set to the instance fields:
     * 
     * @param name
     * @param country
     * @param beginDate
     * @param endDate : "Active" is entered to mark a group that is still active.
     * 
     * Empty ArrayLists are created for cities, aliases, tags and members fields. 
     * ArrayLists can be filled later by using add functions(implemented in Artist class)
     * 
     */
    public Group(String name, String country, String beginDate, String endDate)
    {
        this.name = name;
        this.country = country;
        this.cities = new ArrayList<>();
        this.aliases = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.members = new ArrayList<>();
    }
    /**
     * Following arguments are set to the instance fields:
     * 
     * @param name
     * @param country
     * @param cities
     * @param aliases
     * @param tags
     * @param beginDate
     * @param endDate: "Active" is entered to mark a group that is still active. 
     * @param members
     * 
     * Already filled ArrayLists(cities, aliases, tags, members) are directly passed as arguments.
     * 
     */
    public Group(String name, String country, ArrayList<String> cities, ArrayList<String> aliases, ArrayList<String> tags, String beginDate, String endDate, ArrayList<Artist> members)
    {
        this.name = name;
        this.country = country;
        this.cities = cities;
        this.aliases = aliases;
        this.tags = tags;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.members = members;
    }
    
    /**
     * Following arguments are set to the instance fields:
     * 
     * @param name
     * @param country
     * @param cities
     * @param aliases
     * @param tags
     * @param beginDate
     * @param endDate: "Active" is entered to mark a group that is still active. 
     * 
     * Already filled ArrayLists(cities, aliases, tags) are directly passed as arguments.
     * This constructor is used when getting information online, because no members ArrayList exists in the returned JSONObjects. 
     * 
     */
    public Group(String name, String country, ArrayList<String> cities, ArrayList<String> aliases, ArrayList<String> tags, String beginDate, String endDate)
    {
        this.name = name;
        this.country = country;
        this.cities = cities;
        this.aliases = aliases;
        this.tags = tags;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.members = new ArrayList<>();
    }
    
    public String groupDetails()
    {
        return "Name: " +this.name+ "\nCountry: " +this.country+ "\nBegin Date: " +this.beginDate+ "\nEnd Date: " +this.endDate;        
    }
    
    @Override
    public String toString()
    {
        return "Name: "+this.name+", "+" Country: "+this.country;
    }
}