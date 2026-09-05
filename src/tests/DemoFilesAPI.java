package tests;

import basics.*;
import db.Database;
import files.*;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

/**
 * 
 * Class testing APIWrapper package classes.
 * 
 */
public class DemoFilesAPI
{
    public static void main(String[] args)
    {
        // Try getting person artists online. //
        // Try writting / reading to files. //
        try
        {
            // Test getting objects from MusicBrainz & write functions. //
            ArrayList<Person> personArtists = APIWrapper.getPersonArtistsDefault("fred");
            FileWrapper.writePersonArtistsToFile(personArtists);
            
            ArrayList<Group> groupArtists = APIWrapper.getGroupArtistsDefault("nirvana"); 
            FileWrapper.writeGroupArtistsToFile(groupArtists);

            ArrayList<Album> albumReleases = APIWrapper.getAlbumReleasesDefault("Bleach");
            FileWrapper.writeAlbumReleasesToFile(albumReleases);

            ArrayList<Compilation> compilationReleases = APIWrapper.getCompilationReleasesDefault("Tenance"); //Example found in MusicBrainz for release-group searches
            FileWrapper.writeCompilationReleasesToFile(compilationReleases);
            
            // Test read functions. //
            ArrayList<Person> loadedPersonArtists = FileWrapper.readPersonArtistsFromFile("personArtists.txt");      
            ArrayList<Group> loadedGroupArtists = FileWrapper.readGroupArtistsFromFile("groupArtists.txt");
            ArrayList<Album> loadedAlbumArtists = FileWrapper.readAlbumReleasesFromFile("albumReleases.txt");
            ArrayList<Compilation> loadedCompilationArtists = FileWrapper.readCompilationReleasesFromFile("compilationReleases.txt");
            
            Database.dropTable("tableName", "tableName", "Person");
        }
        // Basic exception handling. //
        catch( IOException | ParseException | JSONException | ClassNotFoundException e )
        {
            System.out.println(e.getMessage());
        }        
        

    }   
}
