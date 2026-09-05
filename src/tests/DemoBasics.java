package tests;
import basics.*;

/**
 * 
 * Class testing basics package classes.
 * 
 */
public class DemoBasics 
{
    public static void main(String[] args) 
    {
        // Data taken by musicbrainz.org //
        
        // Try-Catch surrounds all the code, because in case person creation fails //
        // we will be unable to create group object, album object and compilation object as well. //
        try
        {
            Artist person1 = new Person("Kurt Cobain", "U.S.", "Mr.", "20-02-1967", "05-04-1994" );
            person1.addCity("Washington");
            person1.addAlias("Kurt D Cobain");
            person1.addTag("voice of generation x");

            Artist person2 = new Person("Krist Novoselic", "U.S.", "Mr.", "16-05-19", "Alive");
            person2.addCity("Washington");
            person2.addAlias("Krist Anthony Novoselic");
            person2.addTag("No Tags");

            Artist group1 = new Group("Nirvana", "U.S.", "01-01-1987", "01-01-1994");
            group1.addCity("Washington");
            group1.addAlias("Nirvana US");
            group1.addTag("rock");
            group1.addMember(person1);
            group1.addMember(person2);

            Release album1 = new Album("Bleach", "official", "English", "01-06-1989", "CD", 13, group1 );

            Release compilation1 = new Compilation("Silver", "official", "English", "01-09-1990", "Vinyl", 3);
            compilation1.addArtist(group1);
            compilation1.addArtist(person1);
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
    }
    
}
