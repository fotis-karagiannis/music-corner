package gui;

import basics.*;
import db.*;
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

/**
 * 
 * Frame-Class Displaying search in database window.
 * 
 */
public class DatabaseFrame extends javax.swing.JFrame 
{
    // Database credentials. //
    public static final String userName = "*******";
    public static final String password = "*******";    
    
    DefaultListModel listModel; 

    public DatabaseFrame() 
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
        resultMsglabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        clearButton = new javax.swing.JButton();
        getSelectedButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search on Database");
        setResizable(false);

        searchField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        searchField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                searchFieldActionPerformed(evt);
            }
        });

        searchOption.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        searchOption.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Person by name", "Group by name", "Album by title" }));
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
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jList1);

        resultMsglabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        resultMsglabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

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

        getSelectedButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        getSelectedButton.setText("Print details of selected");
        getSelectedButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getSelectedButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                getSelectedButtonActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resultMsglabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(299, 299, 299))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchOption, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(getSelectedButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField)
                    .addComponent(searchOption)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(getSelectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(133, 133, 133))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addComponent(resultMsglabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchOptionActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_searchOptionActionPerformed

    }//GEN-LAST:event_searchOptionActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_searchButtonActionPerformed
        String userInput = searchField.getText();
        listModel.clear();
        resultMsglabel.setText("");
        jTextArea1.setText("");
        
        if (searchOption.getSelectedItem().equals("Person by name") && searchField.getText().length() >= 1) 
        {
            ArrayList<Person> queryResults = Database.getPersonsByName(userName, password, userInput);
            
            if (queryResults != null) 
            {          
                int found = 0;
                
                for (Person tempPerson : queryResults) 
                {
                    found++;
                    listModel.addElement(tempPerson);
                    resultMsglabel.setText("Found" + " [" + found + "] " + "items");
                }
            } 
            else 
            {
                resultMsglabel.setText("Nothing found after searching on the database.");
            }
        } 
        else if (searchOption.getSelectedItem().equals("Album by title") && searchField.getText().length() >= 1) 
        {                        
            ArrayList<Album> queryResults = Database.getAlbumsByTitle(userName, password, userInput);
            
            if (queryResults != null) 
            {     
                int found = 0;
                
                for (Album tempAlbum : queryResults) 
                {
                    found++;
                    listModel.addElement(tempAlbum);
                    resultMsglabel.setText("Found" + " [" + found + "] " + "items");
                }
            } 
            else 
            {
                resultMsglabel.setText("Nothing found after searching on the database.");
            }
        }
        else if (searchOption.getSelectedItem().equals("Group by name") && searchField.getText().length() >= 1)
        {
            ArrayList<Group> queryResults = Database.getGroupsByName(userName, password, userInput);
            
            if (queryResults != null)
            {
                int found = 0;
                
                for (Group tempGroup : queryResults)
                {
                    found++;
                    listModel.addElement(tempGroup);
                    resultMsglabel.setText("Found" + " [" + found + "] " + "items");
                }
            }
            {
                resultMsglabel.setText("Nothing found after searching on the database.");
            }            
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_searchFieldActionPerformed

    }//GEN-LAST:event_searchFieldActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_clearButtonActionPerformed
        listModel.clear();
        searchField.setText("");
        resultMsglabel.setText("");
        jTextArea1.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void getSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_getSelectedButtonActionPerformed
        if (searchOption.getSelectedItem().equals("Person by name")) 
        {
            List selection = jList1.getSelectedValuesList();
            jTextArea1.setText("");
            
            for (Object tempPerson : selection) 
            {
                jTextArea1.append(((Person) tempPerson).personDetails() + "\n\n");
            }
        } 
        else if (searchOption.getSelectedItem().equals("Album by title")) 
        {
            List selection = jList1.getSelectedValuesList();
            jTextArea1.setText("");
            
            for (Object tempAlbum : selection) 
            {
                jTextArea1.append(((Album) tempAlbum).albumDetails() + "\n\n");
            }
        }
        else if (searchOption.getSelectedItem().equals("Group by name"))
        {
            List selection = jList1.getSelectedValuesList();
            jTextArea1.setText("");
            
            for (Object tempGroup : selection)
            {
                jTextArea1.append(((Group) tempGroup).groupDetails() + "\n\n");
            }
        }
    }//GEN-LAST:event_getSelectedButtonActionPerformed

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
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(DatabaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new DatabaseFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton getSelectedButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel resultMsglabel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> searchOption;
    // End of variables declaration//GEN-END:variables
}
