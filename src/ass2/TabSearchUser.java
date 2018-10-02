package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TabSearchUser extends JPanel {
    
    private final Library LIBRARY;
    
    private JTextArea output;
    
    private JTabbedPane backPane;
    private TabEditUser tabEdit;
    private TabDeleteUser tabDelete;
    
    //searches for user and switches to the edit tab if one is found, also sets the details on it
    //also enable usage of edit and delete tab
    private void searchUser(String search) {
        User u = LIBRARY.findUser(search);
        if (u != null) {
            switch(u.getUserType()) {
                case 1:
                    backPane.setSelectedIndex(2);
                    backPane.setEnabledAt(2, true);
                    backPane.setEnabledAt(3, true);
                    tabEdit.setDetails(u);
                    tabDelete.setDetails(u);
                    output.setText(u.getDetails());
                    break;
                case 2:
                    backPane.setSelectedIndex(2);
                    backPane.setEnabledAt(2, true);
                    backPane.setEnabledAt(3, true);
                    tabEdit.setDetails(u);
                    tabDelete.setDetails(u);
                    output.setText(u.getDetails());
                    break;
                case 3:
                    output.setText("User is not a student or librarian and cannot be edited\n");
                    output.append(u.getDetails());
                    break;
            }
        }
        else {
            output.setText("User not found\n");
        }
    }
    
    public TabSearchUser(JTabbedPane backPane, TabEditUser tabEdit, TabDeleteUser tabDelete, Library lib) {
        super();
        this.backPane = backPane;
        this.tabEdit = tabEdit;
        this.tabDelete = tabDelete;
        this.LIBRARY = lib;
        
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for Search field
        JLabel labelSearch = new JLabel("Find user: ");
        labelSearch.setFont(tabFont);
        this.add(labelSearch);
        
        //text field for search field
        JTextField fieldSearch = new JTextField();
        fieldSearch.setFont(tabFont);
        fieldSearch.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                //if pressed key is Enter
                if (evt.getKeyChar()==KeyEvent.VK_ENTER) {
                    searchUser(fieldSearch.getText());
                }
            }
        });
        this.add(fieldSearch);
        
        //create some output fields
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create search button
        JButton buttonSearch = new JButton("Search");
        buttonSearch.setFont(tabFont);
        buttonSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                searchUser(fieldSearch.getText());
            }
        });
        this.add(buttonSearch);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelSearch)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(fieldSearch)
                        .addComponent(buttonSearch)
                        .addComponent(output)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSearch)
                        .addComponent(fieldSearch)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSearch)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
        );
        
    }
    
}
