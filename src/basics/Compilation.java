package basics;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * Class describing a compilation release.
 * 
 */
public class Compilation extends Release implements Serializable
{
    // Compilation-only fields //
    
    private ArrayList<Artist> artists;
    
    // Compilation Methods //
    
    // Field Getter //
    public ArrayList<Artist> getArtists()
    {
        return this.artists;
    }
    
    // List Filler //
    public void addArtist(Artist artist)
    {
        this.artists.add(artist);
    }
    
    // Constructors //
    
    /**
     * Used as Default Class Constructor
     * Following arguments are set to the instance fields:
     * 
     * @param title
     * @param status : Accepted field values are: official and unofficial.
     * @param language
     * @param releaseDate
     * @param format
     * @param trackCount 
     * 
     * IllegalArgumentException is thrown when wrong status value is given.
     * 
     * Empty ArrayList is created for artists. 
     * ArrayList can be filled later by using the add function.
     * 
     */
    public Compilation(String title, String status, String language, String releaseDate, String format, int trackCount)
    {
        this.title = title;
        setStatus(status);
        this.language = language;
        this.releaseDate = releaseDate;
        this.format = format;
        this.trackCount = trackCount;
        this.artists = new ArrayList<>();
    }
    
    /**
     * Following arguments are set to the instance fields:
     * 
     * @param title
     * @param status : Accepted field values are: official and unofficial.
     * @param language
     * @param releaseDate
     * @param format
     * @param trackCount 
     * @param artists
     * 
     * IllegalArgumentException is thrown when wrong status value is given.
     * 
     * Already filled ArrayList (artists) is directly passed as argument.
     * 
     */
    public Compilation(String title, String status, String language, String releaseDate, String format, int trackCount, ArrayList<Artist> artists)
    {
        this.title = title;
        this.status = status;
        this.language = language;
        this.releaseDate = releaseDate;
        this.format = format;
        this.trackCount = trackCount;
        this.artists = artists;
    }
}
