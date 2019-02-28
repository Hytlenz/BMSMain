
package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.awt.image.*;


/**
 * _________________________________________________________________________
 * name Book Monitoring System
 * @author Allen Baluyot 
 * @version 1.3
 * description UNFINISHED W.I.P PROGRAM
 * - v1.0 - Design
 * - v1.1 - Connecting SQL
 * - v1.2 - Cleanup and Added Tables
 * - v1.3 - Adding Functions of Buttons
 * _________________________________________________________________________
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        
        //Main Core of JFrame
        
        JFrame frame_main = new JFrame("Book Monitoring System - Main Form");
         frame_main.setLayout(new FlowLayout());
         frame_main.setSize(800,500);
         frame_main.setLocationRelativeTo(null);
         frame_main.setVisible(true);
         
         ImageIcon img = new ImageIcon("images/bms_icon.png");
         frame_main.setIconImage(img.getImage());
        
         
        JLabel labelMain = new JLabel(
                "<html>Welcome to Book Monitoring System<br> Coded by: Group IV of 11 - PASCAL<br> Version: v1.3</html>"
        );
        Font fo = new Font("Times New Roman",Font.BOLD,20);
         labelMain.setFont(fo);
        
        JPanel panel_main = new JPanel();
         panel_main.setBackground(Color.BLACK);
         panel_main.setPreferredSize(new Dimension(800,500));
        
        JPanel panel_choose = new JPanel(new GridBagLayout());
         panel_choose.setBackground(Color.WHITE);
         panel_choose.setPreferredSize(new Dimension(400,300));
         panel_choose.add(labelMain);
        
        //JTextField txt1 = new JTextField();
        //txt1.setPreferredSize(new Dimension(100,30));
        
        GridBagConstraints grid = new GridBagConstraints();
        
        
 /*
 _________________________________________________________________________
   ___       __  __              ___
  / _ )__ __/ /_/ /____  ___    <  /
 / _  / // / __/ __/ _ \/ _ \   / / 
/____/\_,_/\__/\__/\___/_//_/  /_/  
                                    
_________________________________________________________________________
  */
        JButton buttonMain1 = new JButton("LIST OF BORROWED BOOKS");
        

         buttonMain1.setBounds(150, 220, 100, 25);
         buttonMain1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_main.setVisible(true);
                
        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_books?zeroDateTimeBehavior=convertToNull","root","");
            Statement stat = conn.createStatement();
            
             ResultSet rs = stat.executeQuery("SELECT * FROM tbl_brbooks");
                
                JFrame frame_listbor = new JFrame("Book Monitoring System - List of Borrowed Books");
                 frame_listbor.setLayout(new FlowLayout());
                 frame_listbor.setSize(800,500);
                 frame_listbor.setLocationRelativeTo(null);
                 frame_listbor.setVisible(true);
                 
                 DefaultTableModel model = new DefaultTableModel();
                 JTable table = new JTable(model);
           
                  model.addColumn("ID");
                  model.addColumn("Book Name");
                  model.addColumn("Book Author");
                  model.addColumn("Borrowed User");
                  model.addColumn("Timestamp");
           
           while (rs.next()){
           model.addRow(new Object[]{
               rs.getString("id"),
               rs.getString("bookname"),
               rs.getString("bookauthor"),
               rs.getString("user"),
               rs.getString("timestamp")
           });
           }
                 
                 JTextField textId = new JTextField();
                 JTextField textBookname = new JTextField();
                 JTextField textBookauthor = new JTextField();
                 JTextField textUser = new JTextField();
                 
                 textId.setPreferredSize(new Dimension(100,30));
                 textBookname.setPreferredSize(new Dimension(100,30));
                 textBookauthor.setPreferredSize(new Dimension(100,30));
                 textUser.setPreferredSize(new Dimension(100,30));
                 
                 textId.setBounds(20, 220, 100, 25);
                 textBookname.setBounds(20, 250, 100, 25);
                 textBookauthor.setBounds(20, 280, 100, 25);
                 textUser.setBounds(20, 310, 100, 25);
           
                JScrollPane scroll = new JScrollPane(table);
                 frame_listbor.add(scroll,BorderLayout.CENTER);
                 
                 // create an array of objects to set the row data
                 Object[] row = new Object[4];
               
                //String name = txt1.getText();
                
                // button for add
                JButton buttonAdd = new JButton("ADD A USER");
                 buttonAdd.setBounds(150, 220, 100, 25);
                 buttonAdd.setToolTipText("ADD A USER WHEN A BOOK IS BORROWED");
                 buttonAdd.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        row[0] = textId.getText();
                        row[1] = textBookname.getText();
                        row[2] = textBookauthor.getText();
                        row[3] = textUser.getText();
                        
                        // Add row for the model
                        
                        model.addRow(row);
                    }
                });
                
                // button for delete
                JButton buttonDel = new JButton("DELETE A USER");
                 buttonDel.setBounds(150, 310, 100, 25);
                 buttonDel.setToolTipText("TAP A ROW TO DELETE A USER");
                 buttonDel.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        int i = table.getSelectedRow();
                        if(i >= 0){
                        // remove a row from jtable
                        model.removeRow(i);
                        }
                     else{
                       System.out.println("Delete Error");
                     }  
                    }
                });
                
                // - Output for buttonMain1 - 
                //panel.add(label);
                frame_listbor.add(buttonAdd);
                frame_listbor.add(buttonDel);
                frame_listbor.add(textId);
                frame_listbor.add(textBookname);
                frame_listbor.add(textBookauthor);
                frame_listbor.add(textUser);
                
                } catch(Exception ex){
            
             }
            }
        });
        // ==================== END OF LINE IN BUTTON 1 ===================== //
        // ==================== END OF LINE IN BUTTON 1 ===================== //
        
        
 /*
_________________________________________________________________________
   ___       __  __              ___ 
  / _ )__ __/ /_/ /____  ___    |_  |
 / _  / // / __/ __/ _ \/ _ \  / __/ 
/____/\_,_/\__/\__/\___/_//_/ /____/ 
                                     
_________________________________________________________________________
  */
        JButton buttonMain2 = new JButton("LIST OF BOOKS");
         buttonMain2.setBounds(150, 310, 100, 25);
         buttonMain2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_main.setVisible(true);
                
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_books?zeroDateTimeBehavior=convertToNull","root","");
            Statement stat = conn.createStatement();
            
             ResultSet rs = stat.executeQuery("SELECT * FROM tbl_books");
                
                JFrame frame_listbooks = new JFrame("Book Monitoring System - List of Books");
                 frame_listbooks.setLayout(new FlowLayout());
                 frame_listbooks.setSize(850,600);
                 frame_listbooks.setLocationRelativeTo(null);
                 frame_listbooks.setVisible(true);
                 
                 DefaultTableModel model = new DefaultTableModel();
                 JTable table = new JTable(model);
           
                  model.addColumn("ID");
                  model.addColumn("Book Name");
                  model.addColumn("Book Author");
                  model.addColumn("Category");
                  model.addColumn("Registered Since");
                  
           while (rs.next()){
           model.addRow(new Object[]{
               rs.getString("id"),
               rs.getString("bookname"),
               rs.getString("bookauthor"),
               rs.getString("category"),
               rs.getString("timestamp")
           });
           }
           
                JTextField textId = new JTextField();
                 JTextField textBookname = new JTextField();
                 JTextField textBookauthor = new JTextField();
                 JTextField textCategory = new JTextField();
                 
                 textId.setPreferredSize(new Dimension(100,30));
                 textBookname.setPreferredSize(new Dimension(100,30));
                 textBookauthor.setPreferredSize(new Dimension(100,30));
                 textCategory.setPreferredSize(new Dimension(100,30));
                 
                 textId.setBounds(20, 220, 100, 25);
                 textBookname.setBounds(20, 250, 100, 25);
                 textBookauthor.setBounds(20, 280, 100, 25);
                 textCategory.setBounds(20, 310, 100, 25);
           
                JScrollPane scroll = new JScrollPane(table);
                 frame_listbooks.add(scroll,BorderLayout.CENTER);
                  
                Object[] row = new Object[4];
                //String name = txt1.getText();
                
                // - Button Add For Books -
                JButton buttonAdd = new JButton("REGISTER BOOK");
                buttonAdd.setToolTipText("REGISTERS A BOOK TO ADD IN TABLE");
                buttonAdd.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        row[0] = textId.getText();
                        row[1] = textBookname.getText();
                        row[2] = textBookauthor.getText();
                        row[3] = textCategory.getText();
                        
                        // Add row for the model
                        model.addRow(row);
                    }
                });
                
                // - Button Delete For Books - 
                JButton buttonDel = new JButton("DELETE BOOK");
                buttonDel.setToolTipText("DELETES A BOOK IN THE TABLE");
                buttonDel.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        int i = table.getSelectedRow();
                        if(i >= 0){
                        // remove a row from jtable
                        model.removeRow(i);
                        } else {
                       System.out.println("Delete Error");
                        }
                    }
                });
                
                /**JLabel label = new JLabel(
                 "<html>W.I.P<BR>JUST AN IDEA<BR>TABLE OF BOOKS AND REGISTER SOME BOOK</html>"
                 );
                frame_listbooks.add(label);
                */
               
                
                frame_listbooks.add(buttonAdd);
                frame_listbooks.add(buttonDel);
                frame_listbooks.add(textId);
                frame_listbooks.add(textBookname);
                frame_listbooks.add(textBookauthor);
                frame_listbooks.add(textCategory);
                 
                } catch(Exception ex){
            
             }
            }
        });
        // ==================== END OF LINE IN BUTTON 2 ===================== //
        // ==================== END OF LINE IN BUTTON 2 ===================== //
        
        // - Main Output for Whole Program -
        frame_main.add(panel_main);
        panel_main.add(panel_choose);
        //pn1.add(txt1);
        grid.insets = new Insets(10,10,10,10);
         grid.gridx = 0; grid.gridy = 1;
        panel_choose.add(buttonMain1, grid);
         grid.gridx = 0; grid.gridy = 2; 
        panel_choose.add(buttonMain2, grid);
         
        
    }
    
}
