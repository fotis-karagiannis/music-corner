package tests;

import gui.DatabaseFrame;
import gui.MainFrame;
import gui.MusicBrainzFrame;

/**
 *
 * Class testing GUI classes.
 * 
 */
public class DemoGUI
{
    public static void main(String[] args)
    {
        MainFrame mFrame = new MainFrame();
        DatabaseFrame dFrame = new DatabaseFrame();
        MusicBrainzFrame mbFrame = new MusicBrainzFrame();
        
        mFrame.setVisible(true);
        dFrame.setVisible(true);
        mbFrame.setVisible(true);
    }
}
