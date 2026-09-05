package tests;

import java.util.ArrayList;
import basics.*;
import db.*;

/**
 * 
 * Class testing database connection & more,
 * Test database is used as an example for the text. Tables are dropped in the case that there are leftovers from previous tests.
 * 
 */
public class DemoDatabase 
{ 
    // Database credentials. //
    public static final String userName = "*******";
    public static final String password = "*******"; 
    
    public static void main(String[] args)
    {
        // Testing Database functions that send/recieve person objects. //
        Person firstPerson = new Person("Artist 1", "Artist 1 Country", "male", "Artist 1 Birth Date", "Artist 1 Death Date");
        Person secondPerson = new Person("Artist 2", "Artist 2 Country", "male", "Artist 2 Birth Date", "Artist 2 Death Date");
        
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(firstPerson);
        persons.add(secondPerson);

        // Drops the table if it already exists (for test reasons). //
        Database.dropTable(userName, password, "PERSON");
        Database.createPersonTable(userName, password);
        boolean checkPersonInsert = Database.insertPerson(userName, password, firstPerson);
        int insertedPersonsCount = Database.insertPersons(userName, password, persons);
        
        System.out.println("Person insert: "+checkPersonInsert);
        System.out.println("Multiple person inserts count: "+insertedPersonsCount);
        
        ArrayList<Person> personResults = Database.getPersonsByName(userName, password, "Artist");
        
                
        // Testing Database functions that send/recieve album objects. //
        
        // For simplicity reasons, a simple, new, only-name artist is given to the constructors, because only artist name is kept in the database. //
        Album firstAlbum = new Album("Album 1 Title", "Album 1 Status", "Album 1 Language", "Album 1 Release Date", "Album 1 Format", 1, new Person("Artist 1"));
        Album secondAlbum = new Album("Album 2 Title", "Album 2 Status", "Album 2 Language", "Album 2 Release Date", "Album 2 Format", 2, new Person("Artist 2"));
        
        ArrayList<Album> albums = new ArrayList<>();
        albums.add(firstAlbum);
        albums.add(secondAlbum);
        
        // Drop the table if it already exists (for test reasons). //
        Database.dropTable(userName, password, "ALBUM");
        Database.createAlbumTable("testTable", "testTable");
        boolean checkAlbumInsert = Database.insertAlbum(userName, password, firstAlbum);
        int insertedAlbumCount = Database.insertAlbums(userName, password, albums);
        
        System.out.println("Album insert: "+checkAlbumInsert);
        System.out.println("Multiple album inserts count: "+insertedAlbumCount);
        
        ArrayList<Album> albumResults = Database.getAlbumsByTitle(userName, password, "Album");
    }    
}
