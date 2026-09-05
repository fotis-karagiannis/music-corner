package files;

import basics.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * Class containing MusicBrainz API methods.
 * 
 */
public class APIWrapper 
{
    // MusicBrainz - JSON Parsing Methods. //
    // Getting artists/releases through MusicBrainz //    
    /**
     * 
     * @param  request
     * @return JSONArray returned by MusicBrainz.
     * @throws JSONException : In case JSONObject cannot be created. This will happen only if the MusicBrainz API changes.
     * @throws ParseException : In case returned string cannot be parsed. This will happen only if the MusicBrainz API changes.
     * @throws MalformedURLException : In case the URL is invalid. Since it is set by the class functions, this is always valid
     * @throws IOException  : In case an IOException occurs. 
     * 
     */
    private static JSONArray getResults(String request) throws JSONException, ParseException, MalformedURLException, IOException
    {
        // Edit request string, in case it contains spaces that must be replaced with %20 on the url. //
        // If the string contains a space ,it is replaced by %20. If not, it remains intact. //
        request = request.replaceAll("\\s", "%20");

        // Url connection //        
        URL requestUrl;
        URLConnection connection;

        // URL constructor will never fail because request string is specified by the class functions. //
        requestUrl = new URL(request);
        // Attempt online connection. //
        connection = requestUrl.openConnection();
        connection.setRequestProperty("accept", "application/json");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) 
        {
            response.append(line);
        }
        reader.close();   
        
        JSONParser parser = new JSONParser();
        JSONObject responseJSONObject = new JSONObject(parser.parse(response.toString()).toString());
        
        // If the search was about an artist, artists array will be present. //
        if( responseJSONObject.has("artists") )
        {
            return responseJSONObject.getJSONArray("artists");   
        }
        // if the search was about a release, releases array will be present. //
        else if( responseJSONObject.has("releases") )
        {
            return responseJSONObject.getJSONArray("releases");
        }
        // Return null if there are no results. //
        else
        {
            return null;
        }
    }
    /**
     * 
     * @param  personName
     * @return ArrayList of person artists.
     * @throws IOException : Thrown by getResults.
     * @throws JSONException : Thrown by getResults.
     * @throws MalformedURLException : Thrown by getResults.
     * @throws ParseException  : Thrown by getResults.
     * 
     */
    public static ArrayList<Person> getPersonArtistsDefault(String personName) throws IOException, JSONException, MalformedURLException, ParseException
    {
        // Create request  string and connect using getResults() method. //
        String request = "http://musicbrainz.org/ws/2/artist/?query=artist:" + personName + "%20AND%20type:person&fmt=json";         
        JSONArray artists = getResults(request);
        if( artists == null || artists.length() == 0 )
        {
            return null;
        }        
        
        // Collect person data //  
        ArrayList<Person> createdPersonArtists = new ArrayList<>();

        for( int i = 0; i < artists.length(); i++ )
        {
            // Initialize temp variables. //
            String name;
            String country = "Unknown";
            ArrayList<String> cities = new ArrayList<>();
            ArrayList<String> aliases = new ArrayList<>();
            ArrayList<String> tags = new ArrayList<>();
            String gender = "Unknown";
            String birthDate = "Unknown";
            String deathDate = "Unknown";
            
            
            // Current JSONObject we are workign on. //
            JSONObject currentObject = artists.getJSONObject(i);
            // Get name, which exists in every artist JSONObject. //
            name = currentObject.getString("name");
            // Get country or a city, which may exist under the area JSONObject. //
            // If area doesn't exist, use country field of the JSONObject, which contains the country in two letters. //
            if( currentObject.has("area") )
            {
                JSONObject currentArea = currentObject.getJSONObject("area");
                
                if( currentArea.getString("type").equals("Country") )
                {
                    country = currentArea.getString("name");
                }
                
                if( currentArea.getString("type").equals("City") )
                {
                    cities.add( currentArea.getString("name") );
                }
            }
            else if( currentObject.has("country") )
            {
                country = currentObject.getString("country");
            }
            // Get cities, which may exist under begin-area or end-area JSONObjects. //
            if( currentObject.has("begin-area") )
            {
                JSONObject currentBeginArea = currentObject.getJSONObject("begin-area");
                
                cities.add( currentBeginArea.getString("name") );
            }
            if( currentObject.has("end-area") )
            {
                JSONObject currentEndArea = currentObject.getJSONObject("end-area");
                
                cities.add( currentEndArea.getString("name") );
            }
            // Get aliases, which exist in objects of the aliases JSONArray, under the name field. // 
            if( currentObject.has("aliases") )
            {
                JSONArray currentAliases = currentObject.getJSONArray("aliases");
                
                for( int j = 0; j < currentAliases.length(); j++  )
                {
                    JSONObject currentAlias = currentAliases.getJSONObject(j);
                    
                    aliases.add( currentAlias.getString("name") );
                }
            }
            // Get tags, which exist in objects of the tags JSONArray, under the name field. //
            if( currentObject.has("tags") )
            {
                JSONArray currentTags = currentObject.getJSONArray("tags");
                
                for( int j = 0; j < currentTags.length(); j++ )
                {
                    JSONObject currentTag = currentTags.getJSONObject(j);
                    
                    tags.add( currentTag.getString("name") );
                }
            }
            // Get gender field, if it exists. //
            if( currentObject.has("gender") )
            {
                gender = currentObject.getString("gender");
            }
            // Get birthDate and deathDate fields, that exist in the life-span JSONObject. //
            if( currentObject.has("life-span") )
            {
                JSONObject currentLifeSpan = currentObject.getJSONObject("life-span");
                
                if( currentLifeSpan.has("begin") )
                {
                    birthDate = currentLifeSpan.getString("begin");
                    deathDate = "Alive"; // Set to alive in case end does not exist. //             
                }
                if( currentLifeSpan.has("end") )
                {
                    deathDate = currentLifeSpan.getString("end");
                }
            }
            
            // Create person object//
            createdPersonArtists.add( new Person(name, country, gender, cities, aliases, tags, birthDate, deathDate) );
        }
        
        if( createdPersonArtists.size() > 0 )
        {
            return createdPersonArtists;
        }
        else
        {
            return null;
        }
    }
    /**
     * 
     * @param  personName
     * @param  personCountry : Only keep the artists that match this country.
     * @return ArrayList of person artists.
     * @throws IOException : Thrown by getResults.
     * @throws JSONException : Thrown by getResults.
     * @throws MalformedURLException : Thrown by getResults.
     * @throws ParseException  : Thrown by getResults.
     * 
     */
    public static ArrayList<Person> getPersonArtistsFromCountry(String personName, String personCountry) throws IOException, JSONException, MalformedURLException, ParseException
    {
        // Create request  string and connect using getResults() method. //
        String request = "http://musicbrainz.org/ws/2/artist/?query=artist:" + personName + "%20AND%20type:person&fmt=json";         
        JSONArray artists = getResults(request);
        if( artists == null || artists.length() == 0 )
        {
            return null;
        }        
        
        // Collect person data. //  
        ArrayList<Person> createdPersonArtists = new ArrayList<>();

        for( int i = 0; i < artists.length(); i++ )
        {
            // Initialize temp variables. //
            String name;
            String country = "Unknown";
            String sortCountry = "Unknown";
            ArrayList<String> cities = new ArrayList<>();
            ArrayList<String> aliases = new ArrayList<>();
            ArrayList<String> tags = new ArrayList<>();
            String gender = "Unknown";
            String birthDate = "Unknown";
            String deathDate = "Unknown";
            
            
            // Current JSONObject we are workign on. //
            JSONObject currentObject = artists.getJSONObject(i);
            // Get name, which exists in every artist JSONObject. //
            name = currentObject.getString("name");
            // Get the two letter country name. //
            if( currentObject.has("country") )
            {
                sortCountry = currentObject.getString("country");
            }
            // Get country or a city, which may exist under the area JSONObject. //
            // If area doesn't exist, use country field of the JSONObject, which contains the country in two letters. //
            if( currentObject.has("area") )
            {
                JSONObject currentArea = currentObject.getJSONObject("area");
                
                if( currentArea.getString("type").equals("Country") )
                {
                    country = currentArea.getString("name");
                }
                
                if( currentArea.getString("type").equals("City") )
                {
                    cities.add( currentArea.getString("name") );
                }
            }
            else if( currentObject.has("country") )
            {
                country = currentObject.getString("country");
            }
            // Now that we have all possible country related values, compare them to artistCountry argument. //
            // If neither of them matches artistCountry, skip this iteration of the loop and create no objects. //
            if( !country.equals(personCountry) && !sortCountry.equals(personCountry) )
            {
                continue;
            }
            // Get cities, which may exist under begin-area or end-area JSONObjects. //
            if( currentObject.has("begin-area") )
            {
                JSONObject currentBeginArea = currentObject.getJSONObject("begin-area");
                
                cities.add( currentBeginArea.getString("name") );
            }
            if( currentObject.has("end-area") )
            {
                JSONObject currentEndArea = currentObject.getJSONObject("end-area");
                
                cities.add( currentEndArea.getString("name") );
            }
            // Get aliases, which exist in objects of the aliases JSONArray, under the name field. // 
            if( currentObject.has("aliases") )
            {
                JSONArray currentAliases = currentObject.getJSONArray("aliases");
                
                for( int j = 0; j < currentAliases.length(); j++  )
                {
                    JSONObject currentAlias = currentAliases.getJSONObject(j);
                    
                    aliases.add( currentAlias.getString("name") );
                }
            }
            // Get tags, which exist in objects of the tags JSONArray, under the name field. //
            if( currentObject.has("tags") )
            {
                JSONArray currentTags = currentObject.getJSONArray("tags");
                
                for( int j = 0; j < currentTags.length(); j++ )
                {
                    JSONObject currentTag = currentTags.getJSONObject(j);
                    
                    tags.add( currentTag.getString("name") );
                }
            }
            // Get gender field, if it exists. //
            if( currentObject.has("gender") )
            {
                gender = currentObject.getString("gender");
            }
            // Get birthDate and deathDate fields, that exist in the life-span JSONObject. //
            if( currentObject.has("life-span") )
            {
                JSONObject currentLifeSpan = currentObject.getJSONObject("life-span");
                
                if( currentLifeSpan.has("begin") )
                {
                    birthDate = currentLifeSpan.getString("begin");
                    deathDate = "Alive"; // Set to alive in case end does not exist. //             
                }
                if( currentLifeSpan.has("end") )
                {
                    deathDate = currentLifeSpan.getString("end");
                }
            }
            
            // Create person object//
            createdPersonArtists.add( new Person(name, country, gender, cities, aliases, tags, birthDate, deathDate) );
        }
        
        if( createdPersonArtists.size() > 0 )
        {
            return createdPersonArtists;
        }
        else
        {
            return null;
        }
    }
    /**
     * 
     * @param  groupName
     * @return ArrayList of group artists.
     * @throws IOException : Thrown by getResults.
     * @throws JSONException : Thrown by getResults.
     * @throws MalformedURLException : Thrown by getResults.
     * @throws ParseException  : Thrown by getResults.
     * 
     */
    public static ArrayList<Group> getGroupArtistsDefault(String groupName) throws IOException, JSONException, MalformedURLException, ParseException
    {
        // Create request  string and connect using getResults() method. //        
        String request = "http://musicbrainz.org/ws/2/artist/?query=artist:" + groupName + "%20AND%20type:group&fmt=json";         
        JSONArray artists = getResults(request);
        if( artists == null || artists.length() == 0 )
        {
            return null;
        }        
        
        // Collect group data. //  
        ArrayList<Group> createdGroupArtists = new ArrayList<>();
                
        for( int i = 0; i < artists.length(); i++ )
        {
            // Initialize temp variables. //
            String name;
            String country = "Unknown";
            ArrayList<String> cities = new ArrayList<>();
            ArrayList<String> aliases = new ArrayList<>();
            ArrayList<String> tags = new ArrayList<>();
            String beginDate = "Unknown";
            String endDate = "Unknown";
            
            
            // Current JSONObject we are workign on. //
            JSONObject currentObject = artists.getJSONObject(i);
            // Get name, which exists in every artist JSONObject. //
            name = currentObject.getString("name");
            // Get country or a city, which may exist under the area JSONObject. //
            // If area doesn't exist, use country field of the JSONObject, which contains the country in two letters. //
            if( currentObject.has("area") )
            {
                JSONObject currentArea = currentObject.getJSONObject("area");
                
                if( currentArea.getString("type").equals("Country") )
                {
                    country = currentArea.getString("name");
                }
                
                if( currentArea.getString("type").equals("City") )
                {
                    cities.add( currentArea.getString("name") );
                }
            }
            else if( currentObject.has("country") )
            {
                country = currentObject.getString("country");
            }
            // Get cities, which may exist under begin-area or end-area JSONObjects. //
            if( currentObject.has("begin-area") )
            {
                JSONObject currentBeginArea = currentObject.getJSONObject("begin-area");
                
                cities.add( currentBeginArea.getString("name") );
            }
            if( currentObject.has("end-area") )
            {
                JSONObject currentEndArea = currentObject.getJSONObject("end-area");
                
                cities.add( currentEndArea.getString("name") );
            }
            // Get aliases, which exist in objects of the aliases JSONArray, under the name field. // 
            if( currentObject.has("aliases") )
            {
                JSONArray currentAliases = currentObject.getJSONArray("aliases");
                
                for( int j = 0; j < currentAliases.length(); j++  )
                {
                    JSONObject currentAlias = currentAliases.getJSONObject(j);
                    
                    aliases.add( currentAlias.getString("name") );
                }
            }
            // Get tags, which exist in objects of the tags JSONArray, under the name field. //
            if( currentObject.has("tags") )
            {
                JSONArray currentTags = currentObject.getJSONArray("tags");
                
                for( int j = 0; j < currentTags.length(); j++ )
                {
                    JSONObject currentTag = currentTags.getJSONObject(j);
                    
                    tags.add( currentTag.getString("name") );
                }
            }
            // Get birthDate and deathDate fields, that exist in the life-span JSONObject. //
            if( currentObject.has("life-span") )
            {
                JSONObject currentLifeSpan = currentObject.getJSONObject("life-span");
                
                if( currentLifeSpan.has("begin") )
                {
                    beginDate = currentLifeSpan.getString("begin");
                    endDate = "Active"; // Set to alive in case end does not exist. //             
                }
                if( currentLifeSpan.has("end") )
                {
                    endDate = currentLifeSpan.getString("end");
                }
            }
            
            // Create group object //
            createdGroupArtists.add( new Group(name, country, cities, aliases, tags, beginDate, endDate) );
        }        
        
        if(createdGroupArtists.size() > 0)
        {
            return createdGroupArtists;
        }
        else
        {
            return null;
        }
    }
    /**
     * 
     * @param  groupName
     * @param  groupCountry : Only keep the artists that match this country.
     * @return ArrayList of group artists.
     * @throws IOException : Thrown by getResults.
     * @throws JSONException : Thrown by getResults.
     * @throws MalformedURLException : Thrown by getResults.
     * @throws ParseException  : Thrown by getResults.
     * 
     */
    public static ArrayList<Group> getGroupArtistsFromCountry(String groupName, String groupCountry) throws IOException, JSONException, MalformedURLException, ParseException
    {
        // Create request  string and connect using getResults() method. //        
        String request = "http://musicbrainz.org/ws/2/artist/?query=artist:" + groupName + "%20AND%20type:group&fmt=json";         
        JSONArray artists = getResults(request);
        if( artists == null || artists.length() == 0 )
        {
            return null;
        }        
        
        // Collect group data. //  
        ArrayList<Group> createdGroupArtists = new ArrayList<>();
                  
        for( int i = 0; i < artists.length(); i++ )
        {
            // Initialize temp variables. //
            String name;
            String country = "Unknown";
            String sortCountry = "Unknown";
            ArrayList<String> cities = new ArrayList<>();
            ArrayList<String> aliases = new ArrayList<>();
            ArrayList<String> tags = new ArrayList<>();
            String beginDate = "Unknown";
            String endDate = "Unknown";
            
            
            // Current JSONObject we are workign on. //
            JSONObject currentObject = artists.getJSONObject(i);
            // Get name, which exists in every artist JSONObject. //
            name = currentObject.getString("name");
            // Get the two letter country name. //
            if( currentObject.has("country") )
            {
                sortCountry = currentObject.getString("country");
            }
            // Get country or a city, which may exist under the area JSONObject. //
            // If area doesn't exist, use country field of the JSONObject, which contains the country in two letters. //
            if( currentObject.has("area") )
            {
                JSONObject currentArea = currentObject.getJSONObject("area");
                
                if( currentArea.getString("type").equals("Country") )
                {
                    country = currentArea.getString("name");
                }
                
                if( currentArea.getString("type").equals("City") )
                {
                    cities.add( currentArea.getString("name") );
                }
            }
            else if( currentObject.has("country") )
            {
                country = currentObject.getString("country");
            }
            // Now that we have all possible country related values, compare them to artistCountry argument. //
            // If neither of them matches artistCountry, skip this iteration of the loop and create no objects. //
            if( !country.equals(groupCountry) && !sortCountry.equals(groupCountry) )
            {
                continue;
            }            
            // Get cities, which may exist under begin-area or end-area JSONObjects. //
            if( currentObject.has("begin-area") )
            {
                JSONObject currentBeginArea = currentObject.getJSONObject("begin-area");
                
                cities.add( currentBeginArea.getString("name") );
            }
            if( currentObject.has("end-area") )
            {
                JSONObject currentEndArea = currentObject.getJSONObject("end-area");
                
                cities.add( currentEndArea.getString("name") );
            }
            // Get aliases, which exist in objects of the aliases JSONArray, under the name field. // 
            if( currentObject.has("aliases") )
            {
                JSONArray currentAliases = currentObject.getJSONArray("aliases");
                
                for( int j = 0; j < currentAliases.length(); j++  )
                {
                    JSONObject currentAlias = currentAliases.getJSONObject(j);
                    
                    aliases.add( currentAlias.getString("name") );
                }
            }
            // Get tags, which exist in objects of the tags JSONArray, under the name field. //
            if( currentObject.has("tags") )
            {
                JSONArray currentTags = currentObject.getJSONArray("tags");
                
                for( int j = 0; j < currentTags.length(); j++ )
                {
                    JSONObject currentTag = currentTags.getJSONObject(j);
                    
                    tags.add( currentTag.getString("name") );
                }
            }
            // Get birthDate and deathDate fields, that exist in the life-span JSONObject. //
            if( currentObject.has("life-span") )
            {
                JSONObject currentLifeSpan = currentObject.getJSONObject("life-span");
                
                if( currentLifeSpan.has("begin") )
                {
                    beginDate = currentLifeSpan.getString("begin");
                    endDate = "Active"; // Set to alive in case end does not exist. //             
                }
                if( currentLifeSpan.has("end") )
                {
                    endDate = currentLifeSpan.getString("end");
                }
            }
            
            // Create group object //
            createdGroupArtists.add( new Group(name, country, cities, aliases, tags, beginDate, endDate) );            
        }        
        
        if(createdGroupArtists.size() > 0)
        {
            return createdGroupArtists;
        }
        else
        {
            return null;
        }
    }
    /**
     * 
     * @param albumName
     * @return ArrayList of album releases.
     * @throws IOException : Thrown by getResults.
     * @throws JSONException : Thrown by getResults.
     * @throws MalformedURLException : Thrown by getResults.
     * @throws ParseException  : Thrown by getResults.
     * 
     */
    public static ArrayList<Album> getAlbumReleasesDefault(String albumName) throws IOException, JSONException, MalformedURLException, ParseException
    {
        // Create request  string and connect using getResults() method. //
        String request = "http://musicbrainz.org/ws/2/release/?query=release:" + albumName + "%20AND%20type:album&fmt=json";         
        JSONArray releases = getResults(request);
        if( releases == null || releases.length() == 0 )
        {
            return null;
        }               
        
        // Collect album data. //
        ArrayList<Album> createdAlbumReleases = new ArrayList<>();
        
        for( int i = 0; i < releases.length(); i++ )
        {
            // Initialize temp variables. //
            String title = "Unknown";
            String status = "Unknown";
            String language = "Unknown";
            String releaseDate = "Unknown";
            String format = "Unknown";
            int trackCount = 0;
            Person artist = new Person("Unknown");
            
            // Current JSONObject that we are working on. //
            JSONObject currentObject = releases.getJSONObject(i);
            // Get title, which exists in every release JSONObject. //
            title = currentObject.getString("title");
            // Get status field, if it exists. //
            if( currentObject.has("status") )
            {
                status = currentObject.getString("status");
            }
            // Get language, which exists under text-representation JSONObject. //
            if( currentObject.has("text-representation") )
            {
                JSONObject currentTextRepresentation = currentObject.getJSONObject("text-representation");
                
                if( currentTextRepresentation.has("language") )
                {
                    language = currentTextRepresentation.getString("language");
                }
            }
            // Get release date, which exists under release-events JSONObject. //
            if( currentObject.has("release-events") )
            {
                JSONArray currentReleaseEvents = currentObject.getJSONArray("release-events");
                JSONObject currentReleaseEvent = currentReleaseEvents.getJSONObject(0);
                
                if( currentReleaseEvent.has("date") )
                {
                    releaseDate = currentReleaseEvent.getString("date");
                }
            }
            // Get format and track count, which exist in media array. //
            // Because our classes are made this way, only one format and trackCount value will be saved (the first one), even if multiple exist. //
            if( currentObject.has("media") )
            {
                JSONObject currentMedia = currentObject.getJSONArray("media").getJSONObject(0);
                
                if( currentMedia.has("format") )
                {
                    format = currentMedia.getString("format");
                }
                if( currentMedia.has("track-count") )
                {
                    trackCount = (int)currentMedia.get("track-count");
                }
            }
            // Get artist name, which exists in an array of artists. Because we are working on albums, only 1 artist per album can exist. //
            if( currentObject.has("artist-credit") )
            {
                JSONArray currentArtists = currentObject.getJSONArray("artist-credit");
                JSONObject currentArtist = currentArtists.getJSONObject(0).getJSONObject("artist");
                // Artist name exists in every artist JSONObject. //
                artist = new Person(currentArtist.getString("name"));                
            }
            
            // Create album object. //
            createdAlbumReleases.add( new Album(title, status, language, releaseDate, format, trackCount, artist) );
        }
        
        if( createdAlbumReleases.size() > 0 )
        {
            return createdAlbumReleases;
        }
        else
        {
            return null;
        }
    }
    /**
     * 
     * @param albumName
     * @param albumLanguage : Only keep the albums that match this language.
     * @return
     * @throws IOException
     * @throws JSONException
     * @throws MalformedURLException
     * @throws ParseException 
     * 
     */
    public static ArrayList<Album> getAlbumReleasesFromLanguage(String albumName, String albumLanguage) throws IOException, JSONException, MalformedURLException, ParseException
    {
        // Create request  string and connect using getResults() method. //
        String request = "http://musicbrainz.org/ws/2/release/?query=release:" + albumName + "%20AND%20type:album&fmt=json";         
        JSONArray releases = getResults(request);
        if( releases == null || releases.length() == 0 )
        {
            return null;
        }               
        
        // Collect album data. //
        ArrayList<Album> createdAlbumReleases = new ArrayList<>();
        
        for( int i = 0; i < releases.length(); i++ )
        {
            // Initialize temp variables. //
            String title = "Unknown";
            String status = "Unknown";
            String language = "Unknown";
            String releaseDate = "Unknown";
            String format = "Unknown";
            int trackCount = 0;
            Person artist = new Person("Unknown");
            
            // Current JSONObject that we are working on. //
            JSONObject currentObject = releases.getJSONObject(i);
            // Get title, which exists in every release JSONObject. //
            title = currentObject.getString("title");
            // Get status field, if it exists. //
            if( currentObject.has("status") )
            {
                status = currentObject.getString("status");
            }
            // Get language, which exists under text-representation JSONObject. //
            if( currentObject.has("text-representation") )
            {
                JSONObject currentTextRepresentation = currentObject.getJSONObject("text-representation");
                
                if( currentTextRepresentation.has("language") )
                {
                    language = currentTextRepresentation.getString("language");
                }
            }
            // Now that we have language, or that we know language does not exist, we continue creating the object, if the country ..
            // exists and matches albumLanguage argument, or skip it. //
            if( !language.equals(albumLanguage) )
            {
                continue;
            }
            // Get release date, which exists under release-events JSONObject. //
            if( currentObject.has("release-events") )
            {
                JSONArray currentReleaseEvents = currentObject.getJSONArray("release-events");
                JSONObject currentReleaseEvent = currentReleaseEvents.getJSONObject(0);
                
                if( currentReleaseEvent.has("date") )
                {
                    releaseDate = currentReleaseEvent.getString("date");
                }
            }
            // Get format and track count, which exist in media array. //
            // Because our classes are made this way, only one format and trackCount value will be saved (the first one), even if multiple exist. //
            if( currentObject.has("media") )
            {
                JSONObject currentMedia = currentObject.getJSONArray("media").getJSONObject(0);
                
                if( currentMedia.has("format") )
                {
                    format = currentMedia.getString("format");
                }
                if( currentMedia.has("track-count") )
                {
                    trackCount = (int)currentMedia.get("track-count");
                }
            }
            // Get artist name, which exists in an array of artists. Because we are working on albums, only 1 artist per album can exist. //
            if( currentObject.has("artist-credit") )
            {
                JSONArray currentArtists = currentObject.getJSONArray("artist-credit");
                JSONObject currentArtist = currentArtists.getJSONObject(0).getJSONObject("artist");
                // Artist name exists in every artist JSONObject. //
                artist = new Person(currentArtist.getString("name"));                
            }
            
            // Create album object. //
            createdAlbumReleases.add( new Album(title, status, language, releaseDate, format, trackCount, artist) );
        }
        
        if( createdAlbumReleases.size() > 0 )
        {
            return createdAlbumReleases;
        }
        else
        {
            return null;
        }
    }    
    
    /**
     * 
     * @param compilationName
     * @return ArrayList of compilation releases.
     * @throws IOException : Thrown by getResults.
     * @throws JSONException : Thrown by getResults.
     * @throws MalformedURLException : Thrown by getResults.
     * @throws ParseException  : Thrown by getResults.
     * 
     */
    public static ArrayList<Compilation> getCompilationReleasesDefault(String compilationName) throws IOException, JSONException, MalformedURLException, ParseException
    {
        // Create request  string and connect using getResults() method. //
        String request = "http://musicbrainz.org/ws/2/release/?query=release-group:"+compilationName+"&fmt=json";         
        JSONArray releases = getResults(request);
        if( releases == null || releases.length() == 0 )
        {
            return null;
        }               
        
        // Collect album data. //
        ArrayList<Compilation> createdCompilationReleases = new ArrayList<>();
        
        for( int i = 0; i < releases.length(); i++ )
        {
            // Initialize temp variables. //
            String title = "Unknown";
            String status = "Unknown";
            String language = "Unknown";
            String releaseDate = "Unknown";
            String format = "Unknown";
            int trackCount = 0;
            ArrayList<Artist> artists = new ArrayList<>();
            
            // Current JSONObject that we are working on. //
            JSONObject currentObject = releases.getJSONObject(i);
            // Get title, which exists in every release JSONObject. //
            title = currentObject.getString("title");
            // Get status field, if it exists. //
            if( currentObject.has("status") )
            {
                status = currentObject.getString("status");
            }
            // Get language, which exists under text-representation JSONObject. //
            if( currentObject.has("text-representation") )
            {
                JSONObject currentTextRepresentation = currentObject.getJSONObject("text-representation");
                
                if( currentTextRepresentation.has("language") )
                {
                    language = currentTextRepresentation.getString("language");
                }
            }
            // Get release date, which exists under release-events JSONObject. //
            if( currentObject.has("release-events") )
            {
                JSONArray currentReleaseEvents = currentObject.getJSONArray("release-events");
                JSONObject currentReleaseEvent = currentReleaseEvents.getJSONObject(0);
                
                if( currentReleaseEvent.has("date") )
                {
                    releaseDate = currentReleaseEvent.getString("date");
                }
            }
            // Get format and track count, which exist in media array. //
            // Because our classes are made this way, only one format and trackCount value will be saved (the first one), even if multiple exist. //
            if( currentObject.has("media") )
            {
                JSONObject currentMedia = currentObject.getJSONArray("media").getJSONObject(0);
                
                if( currentMedia.has("format") )
                {
                    format = currentMedia.getString("format");
                }
                if( currentMedia.has("track-count") )
                {
                    trackCount = (int)currentMedia.get("track-count");
                }
            }
            // Get artists names, which exists in an array of artists. Because we are working on compilations, multiple artists may exist. //
            if( currentObject.has("artist-credit") )
            {
                JSONArray currentArtists = currentObject.getJSONArray("artist-credit");
                for( int j=0; j < currentArtists.length(); j++  )
                {
                    JSONObject currentArtist = currentArtists.getJSONObject(0).getJSONObject("artist");
                    // Artist name exists in every artist JSONObject. //
                    artists.add( new Person(currentArtist.getString("name")) );  
                }
            }
            
            // Create compilation object. //
            createdCompilationReleases.add( new Compilation(title, status, language, releaseDate, format, trackCount, artists) );
        }
        
        if( createdCompilationReleases.size() > 0 )
        {
            return createdCompilationReleases;
        }
        else
        {
            return null;
        }
    }
    
    public static ArrayList<Compilation> getCompilationReleasesFromLanguage(String compilationName, String compilationLanguage) throws IOException, JSONException, MalformedURLException, ParseException
    {
        // Create request  string and connect using getResults() method. //
        String request = "http://musicbrainz.org/ws/2/release/?query=release-group:"+compilationName+"&fmt=json";         
        JSONArray releases = getResults(request);
        if( releases == null || releases.length() == 0 )
        {
            return null;
        }               
        
        // Collect album data. //
        ArrayList<Compilation> createdCompilationReleases = new ArrayList<>();
        
        for( int i = 0; i < releases.length(); i++ )
        {
            // Initialize temp variables. //
            String title = "Unknown";
            String status = "Unknown";
            String language = "Unknown";
            String releaseDate = "Unknown";
            String format = "Unknown";
            int trackCount = 0;
            ArrayList<Artist> artists = new ArrayList<>();
            
            // Current JSONObject that we are working on. //
            JSONObject currentObject = releases.getJSONObject(i);
            // Get title, which exists in every release JSONObject. //
            title = currentObject.getString("title");
            // Get status field, if it exists. //
            if( currentObject.has("status") )
            {
                status = currentObject.getString("status");
            }
            // Get language, which exists under text-representation JSONObject. //
            if( currentObject.has("text-representation") )
            {
                JSONObject currentTextRepresentation = currentObject.getJSONObject("text-representation");
                
                if( currentTextRepresentation.has("language") )
                {
                    language = currentTextRepresentation.getString("language");
                }
            }
            // Now that we have language, or that we know language does not exist, we continue creating the object, if the country ..
            // exists and matches albumLanguage argument, or skip it. //
            if( !language.equals(compilationLanguage) )
            {
                continue;
            }
            // Get release date, which exists under release-events JSONObject. //
            if( currentObject.has("release-events") )
            {
                JSONArray currentReleaseEvents = currentObject.getJSONArray("release-events");
                JSONObject currentReleaseEvent = currentReleaseEvents.getJSONObject(0);
                
                if( currentReleaseEvent.has("date") )
                {
                    releaseDate = currentReleaseEvent.getString("date");
                }
            }
            // Get format and track count, which exist in media array. //
            // Because our classes are made this way, only one format and trackCount value will be saved (the first one), even if multiple exist. //
            if( currentObject.has("media") )
            {
                JSONObject currentMedia = currentObject.getJSONArray("media").getJSONObject(0);
                
                if( currentMedia.has("format") )
                {
                    format = currentMedia.getString("format");
                }
                if( currentMedia.has("track-count") )
                {
                    trackCount = (int)currentMedia.get("track-count");
                }
            }
            // Get artists names, which exists in an array of artists. Because we are working on compilations, multiple artists may exist. //
            if( currentObject.has("artist-credit") )
            {
                JSONArray currentArtists = currentObject.getJSONArray("artist-credit");
                for( int j=0; j < currentArtists.length(); j++  )
                {
                    JSONObject currentArtist = currentArtists.getJSONObject(0).getJSONObject("artist");
                    // Artist name exists in every artist JSONObject. //
                    artists.add( new Person(currentArtist.getString("name")) );  
                }
            }
            
            // Create compilation object. //
            createdCompilationReleases.add( new Compilation(title, status, language, releaseDate, format, trackCount, artists) );
        }
        
        if(createdCompilationReleases.size() > 0)
        {
            return createdCompilationReleases;
        }
        else 
        {
            return null;
        }
    }   
}   
