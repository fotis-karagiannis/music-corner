package basics;

import java.io.Serializable;

/**
 *
 * Class describing an album release.
 * 
 */
public class Album extends Release implements Serializable
{
    // Album-only fields //
    private Artist artist;
    
    // Album Methods //
    
    // Field Setter & Getter //
    public Artist getArtist()
    {
        return this.artist;
    }
    
    public void setArtist(Artist artist)
    {
        this.artist = artist;
    }
    
    // Constructor //
    
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
     * @param artist 
     * 
     * IllegalArgumentException is thrown when wrong status value is given.
     *
     */
    public Album(String title, String status, String language, String releaseDate, String format, int trackCount, Artist artist)
    {
        this.title = title;
        this.status = status;
        this.language = language;
        this.releaseDate = releaseDate;
        this.format = format;
        this.trackCount = trackCount;
        this.artist = artist;
    }
    
    public String albumDetails() 
    {
        return "Title: " +title+ "\nStatus: " +status+ "\nLanguage: " +language+ "\nRelease Date: " +releaseDate+ "\nFormat: " +format+ "\nTrack Count: " +trackCount+ "\nArtist: " +artist;
    }

    @Override
    public String toString() 
    {
        return "Title:" +title+ ", "+"Artist:" +artist.getName();
    }
}
