package basics;

import java.io.Serializable;
import java.util.ArrayList; 

/**
 * 
 * Class describing a single-person Artist object
 * 
 */
public class Person extends Artist implements Serializable
{
    // Person-only Fields //
    private String gender;
    private String birthDate;
    private String deathDate;   
    
    // Person Methods //
    
    // Field Getters //
    public String getGender()
    {
        return this.gender;  
    }
    public String getBirthDate()
    {
        return this.birthDate;
    }
    public String getDeathDate()
    {
        return this.deathDate;
    }
    
    // Field Setters //
    /**
     * 
     * @param gender : Accepted field values are: male , female and Other.
     * On different value, IllegalArgumentException is thrown.
     * 
     */
    public void setGender(String gender)
    {
        if( !gender.equals("male") && !gender.equals("female") && !gender.equals("other") && !gender.equals("Unknown") )
        {
            throw new IllegalArgumentException("Gender must be male, female or Unknown.");
        }
        this.gender = gender;  
    }
    public void setBirthDate(String birthDate)
    {
        this.birthDate = birthDate;
    }
    public void setDeathName(String deathDate)
    {
        this.deathDate = deathDate;
    }
    
    // Constructors //
    
    /**
     * Used as Default Class Constructor
     * Following arguments are set to the instance fields:
     * 
     * @param name
     * @param country
     * @param gender : Accepted field values are: male , female and Other.
     * @param birthDate
     * @param deathDate : "Alive" is entered to mark an artist that is still alive.
     * 
     * IllegalArgumentException is thrown when wrong gender value is given.
     * 
     * Empty ArrayLists are created for cities, aliases and tags fields. 
     * ArrayLists can be filled later by using add functions(implemented in Artist class)
     * 
     */
    public Person(String name, String country, String gender, String birthDate, String deathDate)
    {
        this.name = name;
        this.country = country;
        setGender(gender);
        this.cities = new ArrayList<>();
        this.aliases = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }
    /**
     * Following arguments are set to the instance fields:
     * 
     * @param name
     * @param country
     * @param gender : Accepted field values are: male , female and Other.
     * @param cities
     * @param aliases
     * @param tags
     * @param birthDate
     * @param deathDate : "Alive" is entered to mark an artist that is still alive.
     * 
     * IllegalArgumentException is thrown when wrong gender value is given.
     * 
     * Already filled ArrayLists(cities, aliases, tags) are directly passed as arguments.
     * 
     */
    public Person(String name, String country, String gender, ArrayList<String> cities, ArrayList<String> aliases, ArrayList<String> tags, String birthDate, String deathDate)
    {
        this.name = name;
        this.country = country;
        setGender(gender);
        this.cities = cities;
        this.aliases = aliases;
        this.tags = tags;
        this.birthDate = birthDate;
        this.deathDate = deathDate;       
    }
    /**
     * Following arguments are set to the instance fields:
     * 
     * @param name 
     * 
     * This constructor is used for initializing simple - almost empty artists when getting album/compilation info online.  
     */
    public Person(String name)
    {
        this.name = name;
        this.country = "Unknown";
        this.gender = "Unknown";
        this.cities = new ArrayList<>();
        this.aliases = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.birthDate = "Unknown";
        this.deathDate = "Unknown";
    }
        
    public String personDetails() 
    {
        return "Name: " +this.name+ "\nCountry: " +this.country+ "\nGender: " +this.gender+ "\nBirth Date: " +this.birthDate+ "\nDeath Date: " +this.deathDate;
    }

    @Override
    public String toString() 
    {
        return "Name: "+this.name+", "+" Country: "+this.country;
    }
}