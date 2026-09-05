package files;

import basics.Album;
import basics.Compilation;
import basics.Group;
import basics.Person;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * Class containing file read/write methods.
 * 
 */
public class FileWrapper 
{
    
    // Write to file / Read from file methods. //
    
    /**
     * Write person artists to a file.
     * 
     * @param personArtists
     * @throws IOException 
     * 
     */  
    
    public static void writePersonArtistsToFile( ArrayList<Person> personArtists ) throws IOException
    {
        FileOutputStream fileOut = new FileOutputStream(new File("personArtists.txt"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        
        for (Person personArtist : personArtists) 
        {
            objectOut.writeObject(personArtist);                    
        }        

        objectOut.flush();
        fileOut.close();
        objectOut.close();
    }
    /**
     * Write group artists to a file.
     * 
     * @param groupArtists
     * @throws IOException 
     * 
     */
    public static void writeGroupArtistsToFile( ArrayList<Group> groupArtists ) throws IOException
    {
        FileOutputStream fileOut = new FileOutputStream(new File("groupArtists.txt"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        
        for(Group groupArtist : groupArtists)
        {
            objectOut.writeObject(groupArtist);
        }
        
        fileOut.close();
        objectOut.close();
    }
    /**
     * Write album releases to a file.
     * 
     * @param albumReleases
     * @throws IOException 
     * 
     */
    public static void writeAlbumReleasesToFile( ArrayList<Album> albumReleases ) throws IOException
    {
        FileOutputStream fileOut = new FileOutputStream(new File("albumReleases.txt"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);      
        
        for(Album albumRelease : albumReleases)
        {
            objectOut.writeObject(albumRelease);
        }

        fileOut.close();
        objectOut.close();        
    }
    /**
     * Write compilation releases to a file.
     * 
     * @param compilationReleases
     * @throws IOException 
     * 
     */
    public static void writeCompilationReleasesToFile( ArrayList<Compilation> compilationReleases ) throws IOException
    {
        FileOutputStream fileOut = new FileOutputStream(new File("compilationReleases.txt"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);  
        
        for(Compilation compilationRelease : compilationReleases )
        {
            objectOut.writeObject(compilationRelease);
        }
      
        fileOut.close();
        objectOut.close();        
    }
    /**
     * Read person artists from file.
     * 
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     * 
     */
    public static ArrayList<Person> readPersonArtistsFromFile(String fileName) throws IOException, ClassNotFoundException
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("personArtists.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);    

            ArrayList<Person> loadedPersonArtists = new ArrayList<>();        

            while( fileIn.available()!=0 )
            {
                loadedPersonArtists.add(  (Person)objectIn.readObject()   );
            }       

            return loadedPersonArtists;
        }
        catch( FileNotFoundException e )
        {
            return null;
        }
    }
    /**
     * Read group artists from file.
     * 
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     * 
     */
    public static ArrayList<Group> readGroupArtistsFromFile(String fileName) throws IOException, ClassNotFoundException
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("groupArtists.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);    

            ArrayList<Group> loadedGroupArtists = new ArrayList<>();        

            while( fileIn.available()!=0 )
            {
                loadedGroupArtists.add(  (Group)objectIn.readObject()   );
            }       

            return loadedGroupArtists;
        }
        catch( FileNotFoundException e )
        {
            return null;
        }        
    }
    /**
     * Read album releases from file.
     * 
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * 
     */
    public static ArrayList<Album> readAlbumReleasesFromFile(String fileName) throws IOException, ClassNotFoundException
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("albumReleases.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);    

            ArrayList<Album> loadedAlbumReleases = new ArrayList<>();        

            while( fileIn.available()!=0 )
            {
                loadedAlbumReleases.add(  (Album)objectIn.readObject()   );
            }       

            return loadedAlbumReleases;
        }
        catch( FileNotFoundException e )
        {
            return null;
        }         
    }
    /**
     * Read compilation  releases from file.
     * 
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     * 
     */
    public static ArrayList<Compilation> readCompilationReleasesFromFile(String fileName) throws IOException, ClassNotFoundException
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("compilationReleases.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);    

            ArrayList<Compilation> loadedCompilationReleases = new ArrayList<>();        

            while( fileIn.available()!=0 )
            {
                loadedCompilationReleases.add(  (Compilation)objectIn.readObject()   );
            }       

            return loadedCompilationReleases;
        }
        catch( FileNotFoundException e )
        {
            return null;
        }         
    }     
}
