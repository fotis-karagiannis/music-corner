package db_setup;

import db.Database;

/**
 * 
 * This class drops the basic tables used in the application in the specified database.
 * 
 */
public class DropTables
{
    public static final String userName = "*******";
    public static final String password = "*******";
        
    public static void main(String[] args)
    {
        Database.dropTable(userName, password, "Person");
        Database.dropTable(userName, password, "Album");
        Database.dropTable(userName, password, "GroupArtist");
    }
}
