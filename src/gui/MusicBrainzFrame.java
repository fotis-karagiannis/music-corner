package gui;

import basics.*;
import db.Database;
import files.*;
import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

/**
 * 
 * Frame-Class Displaying search in MusicBrainz window.
 * 
 */
public class MusicBrainzFrame extends javax.swing.JFrame 
{
    // Database credentials. //
    public static final String userName = "*******";
    public static final String password = "*******"; 
    
    DefaultListModel listModel;

    public MusicBrainzFrame() 
	{
        listModel = new DefaultListModel();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        searchField = new javax.swing.JTextField();
        searchOption = new javax.swing.JComboBox<String>();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<String>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        resultMsg = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        getSelected = new javax.swing.JButton();
        saveFileButton = new javax.swing.JButton();
        saveDBbutton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search on MusicBrainz");
        setResizable(false);

        searchField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                searchFieldActionPerformed(evt);
            }
        });

        searchOption.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        searchOption.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Person by name", "Person by name,country", "Group by name", "Group by name,country", "Album by title", "Album by title,language" }));
        searchOption.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchOption.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                searchOptionActionPerformed(evt);
            }
        });

        searchButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        searchButton.setText("Search");
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                searchButtonActionPerformed(evt);
            }
        });

        jList1.setModel(listModel);
        jScrollPane1.setViewportView(jList1);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        resultMsg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        clearButton.setText("Clear");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                clearButtonActionPerformed(evt);
            }
        });

        getSelected.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        getSelected.setText("Print details of selected");
        getSelected.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getSelected.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                getSelectedActionPerformed(evt);
            }
        });

        saveFileButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        saveFileButton.setText("Save selected on the local file");
        saveFileButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveFileButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveFileButtonActionPerformed(evt);
            }
        });

        saveDBbutton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        saveDBbutton.setText("Save selected on the database");
        saveDBbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveDBbutton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveDBbuttonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Powered by: MusicBrainz");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                jLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resultMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(332, 332, 332))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
                            .addComponent(searchField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(getSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(saveFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(saveDBbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchOption, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(searchOption, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(getSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveDBbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)))
                .addGap(26, 26, 26)
                .addComponent(resultMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_searchFieldActionPerformed

    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchOptionActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_searchOptionActionPerformed

    }//GEN-LAST:event_searchOptionActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_clearButtonActionPerformed
        resultMsg.setText("");
        searchField.setText("");
        jTextArea1.setText("");
        listModel.clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_searchButtonActionPerformed
        String input = searchField.getText();
        listModel.clear();
        resultMsg.setText("");
        jTextArea1.setText("");

        if (searchOption.getSelectedItem().equals("Person by name") && searchField.getText().length() >= 1) 
        {
            try 
            {
                ArrayList<Person> apiResult = APIWrapper.getPersonArtistsDefault(input);
                
                if (apiResult != null) 
                {
                    int found = 0;
                    
                    for (Person tempPerson : apiResult)
                    {
                        found++;
                        listModel.addElement(tempPerson);
                        resultMsg.setText("Found" + " [" + found + "] " + "items");
                    }
                } 
                else 
                {
                    resultMsg.setText("Cant find a person with name: " + input);
                }
            } 
            catch (IOException | JSONException | ParseException ex) 
            {
                Logger.getLogger(MusicBrainzFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (searchOption.getSelectedItem().equals("Person by name,country") && searchField.getText().length() >= 1)
        {
            String[] inputValues = input.split(", *");
            listModel.clear();
            resultMsg.setText("");
            jTextArea1.setText("");
            
            if( inputValues.length == 2 )
            {
                try
                {
                    ArrayList<Person> apiResult = APIWrapper.getPersonArtistsFromCountry(inputValues[0], inputValues[1]);
                    
                    if (apiResult != null) 
                    {
                        int found = 0;

                        for (Person tempPerson : apiResult)
                        {
                            found++;
                            listModel.addElement(tempPerson);
                            resultMsg.setText("Found" + " [" + found + "] " + "items");
                        }
                    } 
                    else 
                    {
                        resultMsg.setText("Cant find a person with name, country: " + inputValues[0] + " " + inputValues[1]);
                    }                    
                }
                catch (IOException | JSONException | ParseException ex) 
                {
                    Logger.getLogger(MusicBrainzFrame.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }   
            else
            {
                resultMsg.setText("No country (or more than one) has been entered");
            }
        }
        else if (searchOption.getSelectedItem().equals("Album by title") && searchField.getText().length() >= 1) 
        {
            try 
            {
                ArrayList<Album> apiResult = APIWrapper.getAlbumReleasesDefault(input);
                
                if (apiResult != null) 
                {
                    int found = 0;
                    
                    for (Album tempAlbum : apiResult) 
                    {
                        found++;
                        listModel.addElement(tempAlbum);
                        resultMsg.setText("Found" + " [" + found + "] " + "items");
                    }
                } 
                else 
                {
                    resultMsg.setText("Cant find an album with title: " + input);
                }
            } 
            catch (IOException | JSONException | ParseException ex) 
            {
                Logger.getLogger(MusicBrainzFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (searchOption.getSelectedItem().equals("Album by title,language") && searchField.getText().length() >= 1) 
        {
            String[] inputValues = input.split(", *");
            listModel.clear();
            resultMsg.setText("");
            jTextArea1.setText("");
            
            if( inputValues.length == 2 )
            {
                try
                {
                    ArrayList<Album> apiResult = APIWrapper.getAlbumReleasesFromLanguage(inputValues[0], inputValues[1]);
                    
                    if (apiResult != null) 
                    {
                        int found = 0;

                        for (Album tempAlbum : apiResult)
                        {
                            found++;
                            listModel.addElement(tempAlbum);
                            resultMsg.setText("Found" + " [" + found + "] " + "items");
                        }
                    } 
                    else 
                    {
                        resultMsg.setText("Cant find an album with title, language: " + inputValues[0] + " " + inputValues[1]);
                    }                    
                }
                catch (IOException | JSONException | ParseException ex) 
                {
                    Logger.getLogger(MusicBrainzFrame.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }   
            else
            {
                resultMsg.setText("No language (or more than one) has been entered");
            }              
        }
        else if (searchOption.getSelectedItem().equals("Group by name") && searchField.getText().length() >= 1) 
        {
            try 
            {
                ArrayList<Group> apiResult = APIWrapper.getGroupArtistsDefault(input);
                
                if (apiResult != null) 
                {
                    int found = 0;
                    
                    for (Group tempGroup : apiResult) 
                    {
                        found++;
                        listModel.addElement(tempGroup);
                        resultMsg.setText("Found" + " [" + found + "] " + "items");
                    }
                } 
                else 
                {
                    resultMsg.setText("Cant find a group with title: " + input);
                }
            } 
            catch (IOException | JSONException | ParseException ex) 
            {
                Logger.getLogger(MusicBrainzFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        else if (searchOption.getSelectedItem().equals("Group by name,country") && searchField.getText().length() >= 1)
        {
            String[] inputValues = input.split(", *");
            listModel.clear();
            resultMsg.setText("");
            jTextArea1.setText("");
            
            if( inputValues.length == 2 )
            {
                try
                {
                    ArrayList<Group> apiResult = APIWrapper.getGroupArtistsFromCountry(inputValues[0], inputValues[1]);
                    
                    if (apiResult != null) 
                    {
                        int found = 0;

                        for (Group tempGroup : apiResult)
                        {
                            found++;
                            listModel.addElement(tempGroup);
                            resultMsg.setText("Found" + " [" + found + "] " + "items");
                        }
                    } 
                    else 
                    {
                        resultMsg.setText("Cant find a group with name, country: " + inputValues[0] + " " + inputValues[1]);
                    }                    
                }
                catch (IOException | JSONException | ParseException ex) 
                {
                    Logger.getLogger(MusicBrainzFrame.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }   
            else
            {
                resultMsg.setText("No country (or more than one) has been entered");
            }            
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void getSelectedActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_getSelectedActionPerformed
        if (searchOption.getSelectedItem().equals("Person by name") || searchOption.getSelectedItem().equals("Person by name,country")) 
        {
            List selection = jList1.getSelectedValuesList();
            jTextArea1.setText("");
            
            for (Object tempPerson : selection) 
            {
                jTextArea1.append(((Person) tempPerson).personDetails() + "\n\n");
            }
        } 
        else if (searchOption.getSelectedItem().equals("Album by title") || searchOption.getSelectedItem().equals("Album by title,language")) 
        {
            List selection = jList1.getSelectedValuesList();
            jTextArea1.setText("");
            
            for (Object tempAlbum : selection) 
            {
                jTextArea1.append(((Album) tempAlbum).albumDetails() + "\n\n");
            }
        }
        else if (searchOption.getSelectedItem().equals("Group by name") || searchOption.getSelectedItem().equals("Group by name,country"))
        {
            List selection = jList1.getSelectedValuesList();
            jTextArea1.setText("");
            
            for (Object tempGroup : selection)
            {
                jTextArea1.append(((Group) tempGroup).groupDetails() + "\n\n");
            }
        }
    }//GEN-LAST:event_getSelectedActionPerformed

    private void saveFileButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_saveFileButtonActionPerformed
        if (searchOption.getSelectedItem().equals("Person by name") || searchOption.getSelectedItem().equals("Person by name,country")) 
        {
            List selection = jList1.getSelectedValuesList();
            resultMsg.setText("");
            ArrayList<Person> toFile = new ArrayList(selection);
            
            try 
            {
                FileWrapper.writePersonArtistsToFile(toFile);
                resultMsg.setText(" [" + toFile.size() + "] " + "items saved");
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(MusicBrainzFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        else if (searchOption.getSelectedItem().equals("Album by title") || searchOption.getSelectedItem().equals("Album by title,language")) 
        {
            List selection = jList1.getSelectedValuesList();
            resultMsg.setText("");
            ArrayList<Album> toFile = new ArrayList(selection);
            
            try 
            {
                FileWrapper.writeAlbumReleasesToFile(toFile);
                resultMsg.setText(" [" + toFile.size() + "] " + "items saved");
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(MusicBrainzFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (searchOption.getSelectedItem().equals("Group by name") || searchOption.getSelectedItem().equals("Group by name,country"))
        {
            List selection = jList1.getSelectedValuesList();
            resultMsg.setText("");
            ArrayList<Group> toFile = new ArrayList(selection);
            
            try
            {
                FileWrapper.writeGroupArtistsToFile(toFile);
                resultMsg.setText(" [" + toFile.size() + "] " + "items saved");
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(MusicBrainzFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveFileButtonActionPerformed

    private void saveDBbuttonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_saveDBbuttonActionPerformed
        if (searchOption.getSelectedItem().equals("Person by name") || searchOption.getSelectedItem().equals("Person by name,country")) 
        {
            List selection = jList1.getSelectedValuesList();
            ArrayList<Person> toDb = new ArrayList(selection);
            resultMsg.setText("");
            
            Database.insertPersons(userName, password, toDb);
            resultMsg.setText(" [" + toDb.size() + "] " + "items saved to database");
        } 
        else if (searchOption.getSelectedItem().equals("Album by title") || searchOption.getSelectedItem().equals("Album by title,language")) 
        {
            List selection = jList1.getSelectedValuesList();
            ArrayList<Album> toDb = new ArrayList(selection);
            resultMsg.setText("");
            
            Database.insertAlbums(userName, password, toDb);
            resultMsg.setText(" [" + toDb.size() + "] " + "items saved to database");
        }
        else if (searchOption.getSelectedItem().equals("Group by name") || searchOption.getSelectedItem().equals("Group by name,country"))
        {
            List selection = jList1.getSelectedValuesList();
            ArrayList<Group> toDb = new ArrayList(selection);
            resultMsg.setText("");
            
            Database.insertGroups(userName, password, toDb);
            resultMsg.setText(" [" + toDb.size() + "] " + "items saved to database");
        }
    }//GEN-LAST:event_saveDBbuttonActionPerformed

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLabel2MouseEntered
    {//GEN-HEADEREND:event_jLabel2MouseEntered
        jLabel2.setForeground(Color.BLUE.brighter());
        jLabel2.setToolTipText("Click to visit MusicBrainz!");
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLabel2MouseExited
    {//GEN-HEADEREND:event_jLabel2MouseExited
        jLabel2.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLabel2MousePressed
    {//GEN-HEADEREND:event_jLabel2MousePressed
        try
        {
            Desktop.getDesktop().browse(new URI("https://musicbrainz.org"));
        } 
        catch (IOException | URISyntaxException ex)
        {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel2MousePressed

    
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex) 
        {
            java.util.logging.Logger.getLogger(MusicBrainzFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(MusicBrainzFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(MusicBrainzFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(MusicBrainzFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new MusicBrainzFrame().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton getSelected;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel resultMsg;
    private javax.swing.JButton saveDBbutton;
    private javax.swing.JButton saveFileButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> searchOption;
    // End of variables declaration//GEN-END:variables
}
