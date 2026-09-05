package basics;

import java.io.Serializable;

/**
 * 
 * Abstract class containing fields/methods
 * that are present in both Album and Compilation class.
 * 
 */
public abstract class Release implements Serializable 
{
     // Release Fields //   
    
    protected String title;
    protected String status;
    protected String language;
    protected String releaseDate;
    protected String format;
    protected String releaseID;
    protected int trackCount;
    
    // Release Methods //
    
    // Field Getters //
    public String getTitle()
    {
        return this.title;
    }
    public String getStatus()
    {
        return this.status;
    }
    public String getLanguage()
    {
        return this.language;
    }    
    public String getReleaseDate()
    {
        return this.releaseDate;
    }
    public String getFormat()
    {
        return this.format;
    }
    public String getReleaseID()
    {
        return this.releaseID;
    }
    public int getTrackCount()
    {
        return this.trackCount;
    }
    
    // Field Settets //
    public void setTitle(String title)
    {
        this.title = title;  
    }
    /**
     * 
     * @param status : Accepted values are: official and unofficial.
     * 
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    public void setLanguage(String language)
    {
        this.language = language;  
    }
    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;  
    }
    public void setFormat(String format)
    {
        this.format = format;
    }
    public void setReleaseID(String releaseID)
    {
        this.releaseID = releaseID;
    }
    public void setTrackCount(int trackCount)
    {
        this.trackCount = trackCount;
    }
    
    // Compilation method declared to be overriden by compilation class. //
    // Declared so it can be used by Release objects that are compilations. // 
    // Nothing is done when called by Release object that is an album. //
    public void addArtist(Artist artist){}
}        