package db_setup;

import db.Database;

/**
 * 
 * This class creates basic tables used in the application in the specified database.
 * 
 */
public class CreateTables
{
    public static final String userName = "*******";
    public static final String password = "*******";
    
    public static void main(String[] args)
    {
        Database.createPersonTable(userName, password);
        Database.createAlbumTable(userName, password);
        Database.createGroupTable(userName, password);
    }
}
