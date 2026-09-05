package db;

import java.sql.*;
import java.util.ArrayList;
import basics.*;

/**
 * 
 * This class contains methods handling the database.
 * 
 */
public class Database 
{
    private static Connection DatabaseConnection;
    
    /**
     * 
     * This method establishes a connection to the configured Oracle database.
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * @return : Returns the connection established.
     * 
     * @throws SQLException if the connection failed.
     * @throws ClassNotFoundException if database driver could not be loaded.
     * 
     */
    public static Connection getDatabaseConnection(String databaseUsername, String databasePassword) throws SQLException, ClassNotFoundException
    {
        if( Database.DatabaseConnection == null )
        {
            // Load database driver & establish connection //
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String databaseUrl = "jdbc:********";
            Database.DatabaseConnection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
            return Database.DatabaseConnection;
        }
        
        return Database.DatabaseConnection;
    }
 
    /**
     * This method creates a person table in the database.
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * 
     * @return : Returns true or false , if the creation was successful or not.
     * 
     */
    public static boolean createPersonTable(String databaseUsername, String databasePassword)
    {
        boolean success = true;

        // SQLException will get caught if the creation failed. //
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement createStatement = connection.createStatement();
        
            createStatement.executeQuery(
                "CREATE TABLE PERSON"+
                "(" +
                "    personID NUMBER GENERATED AS IDENTITY," +                        
                "    personName VARCHAR(30)," +
                "    personCountry VARCHAR(30)," +
                "    personGender VARCHAR(30)," +
                "    personBirthDate VARCHAR(20)," +
                "    personDeathDate VARCHAR(20)," +
                "    CONSTRAINT pk_person PRIMARY KEY(personID)" +
                ")"
                );        
        }
        catch( SQLException | ClassNotFoundException ex  )
        {
            success = false;
        }        
        
        return success;        
    }
    
    /**
     * This method creates an album table in the database.
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * 
     * @return : Returns true or false , if the creation was successful or not.
     * 
     */
    public static boolean createAlbumTable(String databaseUsername, String databasePassword)
    {
        boolean success = true;

        // SQLException will get caught if the creation failed. //
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement createStatement = connection.createStatement();
        
            createStatement.executeQuery(
                "CREATE TABLE ALBUM"+
                "(" +
                "    albumID NUMBER GENERATED AS IDENTITY," +                        
                "    albumTitle VARCHAR(30)," +
                "    albumStatus VARCHAR(30)," +
                "    albumLanguage VARCHAR(30)," +
                "    albumReleaseDate VARCHAR(20)," +
                "    albumFormat VARCHAR(30)," +
                "    albumTrackCount NUMBER,"+
                "    albumArtistName VARCHAR(30),"+
                "    CONSTRAINT pk_album PRIMARY KEY(albumID)" +
                ")"
                );        
        }
        catch( SQLException | ClassNotFoundException ex  )
        {
            success = false;
        }        
        
        return success;           
    }
    
    /**
     * This method creates a groupArtist table in the database.
     * 
     * @param databaseUsername : Database owner  username.
     * @param databasePassword : Database owner password.
     * 
     * @return : Returns true or false, if the creation was successful or not.
     * 
     */
    public static boolean createGroupTable(String databaseUsername, String databasePassword)
    {
        boolean success = true;

        // SQLException will get caught if the creation failed. //
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement createStatement = connection.createStatement();
        
            createStatement.executeQuery(
                "CREATE TABLE GROUPARTIST"+
                "(" +
                "    groupID NUMBER GENERATED AS IDENTITY," +                        
                "    groupName VARCHAR(30)," +
                "    groupCountry VARCHAR(30)," +
                "    groupBeginDate VARCHAR(20)," +
                "    groupEndDate VARCHAR(20)," +
                "    CONSTRAINT pk_group PRIMARY KEY(groupID)" +
                ")"
                );        
        }
        catch( SQLException | ClassNotFoundException ex  )
        {
            success = false;
        }        
        
        return success;          
    }
    
    /**
     * 
     * This method drops a table from the database. 
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * @param tableName
     * @return : Returns true or false , if the dropping was successful or not.
     * 
     */    
    public static boolean dropTable(String databaseUsername, String databasePassword, String tableName)
    {
        boolean success = true;
        
        // SQLException will be caught if the dropping failed. /
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement dropStatement = connection.createStatement();
        
            dropStatement.executeQuery("DROP TABLE "+tableName);        
        }
        catch( SQLException | ClassNotFoundException ex )
        {
            success = false;
        }        
        
        return success;         
    }
    
    /**
     * 
     * This method inserts a person object to the person table.
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * @param person : Artist to be inserted to the database table.
     * 
     * @return : Returns true or false , if the insertion was successful or not.
     * 
     */
    public static boolean insertPerson(String databaseUsername, String databasePassword, Person person)
    {
        boolean success = true;

        // SQLException will get caught if the insertion failed. //
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement insertStatement = connection.createStatement();
            
            insertStatement.executeQuery("INSERT INTO PERSON(personName,personCountry,personGender,personBirthDate,personDeathDate) VALUES('"
                    +person.getName()+"','"+person.getCountry()+"','"+person.getGender()+"','"+person.getBirthDate()+"','"+person.getDeathDate()+"')");
        }
        catch( SQLException | ClassNotFoundException ex )
        {
            success = false;
        }        
        
        return success;
    }
    
    /**
     * 
     * This method inserts multiple person objects to the person table.
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * @param persons : Artists to be inserted to the database table.
     * 
     * @return : Returns the number of successful inserts.
     * 
     */
    public static int insertPersons(String databaseUsername, String databasePassword, ArrayList<Person> persons)
    {
        int insertedCounter = 0;

        for(Person person : persons)
        {
            if( insertPerson(databaseUsername, databasePassword, person) )
            {
                insertedCounter++;
            }
        }
        
        return insertedCounter;        
    }
    
    /**
     * 
     * This method searches the database and finds persons matching(or containing) the personName. 
     * LIKE is used, so that persons that contain the personName will be also returned.
     * 
     * @param databaseUsername : Database owner username
     * @param databasePassword : Database owner password
     * @param personName : Name key  
     * 
     * @return : Returns an ArrayList or null in case of failure, or in case of no results.
     * 
     */
    public static ArrayList<Person> getPersonsByName(String databaseUsername, String databasePassword, String personName)
    {
        ArrayList<Person> loadedPersons = new ArrayList<>();
        
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement selectStatement = connection.createStatement();
            Person tempPerson;
            
            ResultSet results = selectStatement.executeQuery("SELECT * FROM PERSON WHERE personName LIKE '%"+personName+"%'");
            while( results.next() )
            {
                tempPerson = new Person(results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6) );
                tempPerson.setArtistID(results.getString(1));
                
                loadedPersons.add(tempPerson);
            }
        }
        catch( SQLException | ClassNotFoundException ex )
        {
            return null;
        }
        
        if( loadedPersons.size() > 0 )
        {
            return loadedPersons;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * 
     * This method inserts a group object to the groupArtist table.
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * @param group : Group to be inserted to the database table.
     * 
     * @return : Returns true or false , if the insertion was successful or not.
     * 
     */
    public static boolean insertGroup(String databaseUsername, String databasePassword, Group group)
    {
        boolean success = true;

        // SQLException will get caught if the insertion failed. //
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement insertStatement = connection.createStatement();
            
            insertStatement.executeQuery("INSERT INTO GROUPARTIST(groupName,groupCountry,groupBeginDate,groupEndDate) VALUES('"
                    +group.getName()+"','"+group.getCountry()+"','"+group.getBeginDate()+"','"+group.getEndDate()+"')");
        }
        catch( SQLException | ClassNotFoundException ex )
        {
            success = false;
        }        
        
        return success;        
    }
    
    /**
     * 
     * This method inserts multiple person objects to the groups table.
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * @param groups : Groups to be inserted to the database table.
     * 
     * @return : Returns the number of successful inserts.
     * 
     */
    public static int insertGroups(String databaseUsername, String databasePassword, ArrayList<Group> groups)
    {
        int insertedCounter = 0;
        
        for(Group group : groups)
        {
            if( insertGroup(databaseUsername, databasePassword, group) )
            {
                insertedCounter++;
            }
        }
        
        return insertedCounter;
    }
 
    /**
    * 
    * This method searches the database and finds groups matching(or containing) the groupName. 
    * LIKE is used, so that persons that contain the personName will be also returned.
    * 
    * @param databaseUsername : Database owner username
    * @param databasePassword : Database owner password
    * @param groupName : Name key  
    * 
    * @return : Returns an ArrayList or null in case of failure, or in case of no results.
    * 
    */    
    public static ArrayList<Group> getGroupsByName(String databaseUsername, String databasePassword, String groupName)
    {
        ArrayList<Group> loadedGroups = new ArrayList<>();
        
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement selectStatement = connection.createStatement();
            Group tempGroup;
            
            ResultSet results = selectStatement.executeQuery("SELECT * FROM GROUPARTIST WHERE groupName LIKE '%"+groupName+"%'");
            while( results.next() )
            {
                tempGroup = new Group(results.getString(2), results.getString(3), results.getString(4), results.getString(5));
                tempGroup.setArtistID(results.getString(1));
                
                loadedGroups.add(tempGroup);
            }
        }
        catch( SQLException | ClassNotFoundException ex )
        {
            return null;
        }
        
        if( loadedGroups.size() > 0 )
        {
            return loadedGroups;
        }
        else
        {
            return null;
        }        
    }
    
    /**
     * 
     * This method inserts an album to the album table.
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * @param album : Album to be inserted.
     * 
     * @return : Returns true or false , if the insertion was successful or not.
     * 
     */
    public static boolean insertAlbum(String databaseUsername, String databasePassword, Album album)
    {
        boolean success = true;

        // SQLException will get caught if the insertion failed. //
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement insertStatement = connection.createStatement();
            
            insertStatement.executeQuery("INSERT INTO ALBUM(albumTitle,albumStatus,albumLanguage,albumReleaseDate,albumFormat,albumTrackCount,albumArtistName) VALUES('"
                    +album.getTitle()+"','"+album.getStatus()+"','"+album.getLanguage()+"','"+album.getReleaseDate()+"','"+album.getFormat()+"','"+album.getTrackCount()+"','"
                    +album.getArtist().getName()+"')");
        }
        catch( SQLException | ClassNotFoundException ex )
        {
            success = false;
        }        
        
        return success;        
    }

    /**
     * 
     * This method inserts multiple album objects to the person table.
     * 
     * @param databaseUsername : Database owner username.
     * @param databasePassword : Database owner password.
     * @param albums : Artists to be inserted to the database table.
     * 
     * @return : Returns the number of successful inserts.
     * 
     */    
    public static int insertAlbums(String databaseUsername, String databasePassword, ArrayList<Album> albums)
    {
        int insertedCounter = 0;

        for(Album album : albums)
        {
            if( insertAlbum(databaseUsername, databasePassword,album) )
            {
                insertedCounter++;
            }
        }
        
        return insertedCounter;  
    }
   
    /**
    * 
    * This method searches the database and finds persons matching(or containing) the personName. 
    * LIKE is used, so that persons that contain the personName will be also returned.
    * 
    * @param databaseUsername : Database owner username
    * @param databasePassword : Database owner password
    * @param albumTitle : Name key  
    * 
    * @return : Returns an ArrayList or null in case of failure, or in case of no results.
    * 
    */
    public static ArrayList<Album> getAlbumsByTitle(String databaseUsername, String databasePassword, String albumTitle)
    {
        ArrayList<Album> loadedAlbums = new ArrayList<>();
        
        try
        {
            Connection connection = getDatabaseConnection(databaseUsername,databasePassword);
            Statement selectStatement = connection.createStatement();
            Album tempAlbum;
            
            ResultSet results = selectStatement.executeQuery("SELECT * FROM ALBUM WHERE albumTitle LIKE '%"+albumTitle+"%'");
            while( results.next() )
            {
                tempAlbum = new Album(results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6), results.getInt(7), new Person(results.getString(8)));
                tempAlbum.setReleaseID(results.getString(1));
                
                loadedAlbums.add(tempAlbum);
            }
        }
        catch( SQLException | ClassNotFoundException ex )
        {
            return null;
        }
        
        if( loadedAlbums.size() > 0 )
        {
            return loadedAlbums;
        }
        else
        {
            return null;
        }      
    }
}
