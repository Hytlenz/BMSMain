package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

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
 * ________________________________________________________________________
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        
        //Main Core of JFrame
        JFrame frame_main = new JFrame("Book Monitoring System - Main Form");
         frame_main.setLayout(new FlowLayout());
         frame_main.setSize(500,500);
         frame_main.setLocationRelativeTo(null);
         frame_main.setVisible(true);
          
        JLabel labelMain = new JLabel("WELCOME TO");
        Font fo = new Font("Comic Sans MS",Font.BOLD,20);
         labelMain.setFont(fo);
         labelMain.setForeground(Color.GREEN);
         labelMain.setBounds(430,20,300,100);
         
        JLabel label1 = new JLabel("BOOK");
        Font fon = new Font("Comic Sans MS",Font.BOLD,20);
         label1.setFont(fon);
         label1.setForeground(Color.BLUE);
         label1.setBounds(470,95,150,50);
         
        JLabel label2 = new JLabel("MONITORING SYSTEM");
        Font font = new Font("Comic Sans MS",Font.BOLD,20);
         label2.setFont(font);
         label2.setForeground(Color.BLUE);
         label2.setBounds(380,150,600,50);
        
        JPanel panel_main = new JPanel();
         panel_main.setLayout(null);
         panel_main.setBounds(10,50,60,20);
         panel_main.setBackground(Color.GRAY);
         panel_main.setPreferredSize(new Dimension(1000,500));
         panel_main.add(labelMain);
         panel_main.add(label1);
         panel_main.add(label2);
        
        // button for borrowed books
        JButton buttonBorrowed = new JButton("LIST OF BORROWED BOOKS");
         buttonBorrowed.setBounds(350, 250, 150, 50);
         buttonBorrowed.setBackground(Color.WHITE);
         buttonBorrowed.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_main.setVisible(false);
                
        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_books?zeroDateTimeBehavior=convertToNull","root","");
            Statement stat = conn.createStatement();
            
             ResultSet rs = stat.executeQuery("SELECT * FROM tbl_brbooks");
                
                JFrame frame_listbor = new JFrame("Book Monitoring System - List of Borrowed Books");
                 frame_listbor.setLayout(new FlowLayout());
                 frame_listbor.setSize(1000,500);
                 frame_listbor.setLocationRelativeTo(null);
                 frame_listbor.setVisible(true);
                 
                JPanel panel_listbor = new JPanel();
                 panel_listbor.setPreferredSize(new Dimension(1000,500));
                 panel_listbor.setBackground(Color.BLACK);
                 
                 DefaultTableModel model = new DefaultTableModel();
                 JTable table = new JTable(model);
                  table.setGridColor(Color.BLACK);
                  table.setBounds(600,200,300,300);
                  table.setShowGrid(true);
                
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
                 
                 JLabel labelId = new JLabel("[ID]");
                 JLabel labelBook = new JLabel("[BOOK NAME]");
                 JLabel labelAuthor = new JLabel("[BOOK AUTHOR]");
                 JLabel labelUser = new JLabel("[BORROWED USER]");
                 
                 labelId.setBounds(100, 200, 1500, 25);
                 labelBook.setBounds(20, 250, 100, 25);
                 labelAuthor.setBounds(20, 280, 100, 25);
                 labelUser.setBounds(20, 310, 100, 25);
                 
                 panel_listbor.add(labelId);
                 //frame_listbor.add(labelId);
                 //frame_listbor.add(labelBook);
                 //frame_listbor.add(labelAuthor);
                 //frame_listbor.add(labelUser);

                 textId.setPreferredSize(new Dimension(100,30));
                 textBookname.setPreferredSize(new Dimension(100,30));
                 textBookauthor.setPreferredSize(new Dimension(100,30));
                 textUser.setPreferredSize(new Dimension(100,30));
                 
                 textId.setBounds(800, 250, 150, 500);
                 textBookname.setBounds(700, 250, 100, 500);
                 textBookauthor.setBounds(700, 280, 100, 30);
                 textUser.setBounds(700, 310, 100, 25);
           
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
                 
                JButton buttonBack = new JButton("BACK TO MENU");
                 buttonBack.setToolTipText("BACK TO THE MAIN MENU OF THE SYSTEM");
                 buttonBack.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        frame_main.setVisible(true);
                        frame_listbor.setVisible(false);
                        
                    }
                });
                
                // - Output for buttonMain1 - 
                //panel.add(label);
                //frame_listbor.add(buttonAdd);
                //frame_listbor.add(buttonDel);
                //frame_listbor.add(buttonBack);
                frame_listbor.add(panel_listbor);
                //panel_listbor.add(textId);
                panel_listbor.add(table);
                panel_listbor.add(textId);
                //frame_listbor.add(textBookname);
                //frame_listbor.add(textBookauthor);
                //frame_listbor.add(textUser);
                frame_listbor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                } catch(Exception ex){
                
            
             }
            }
        });
          
        // button for books 
        JButton buttonBooks = new JButton("LIST OF BOOKS");
         buttonBooks.setBounds(550, 250, 150,50);
         buttonBooks.setBackground(Color.WHITE);
         buttonBooks.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_main.setVisible(false);
                
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
                
                JButton buttonBack = new JButton("BACK TO MENU");
                 buttonBack.setToolTipText("BACK TO THE MAIN MENU OF THE SYSTEM");
                 buttonBack.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        frame_main.setVisible(true);
                        frame_listbooks.setVisible(false);
                        
                    }
                });
            
               
                frame_listbooks.add(buttonAdd);
                frame_listbooks.add(buttonDel);
                frame_listbooks.add(buttonBack);
                frame_listbooks.add(textId);
                frame_listbooks.add(textBookname);
                frame_listbooks.add(textBookauthor);
                frame_listbooks.add(textCategory);
                frame_listbooks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 
                } catch(Exception ex){
            
             }
            }
        });
         
         // button for auth
         JButton buttonAuth = new JButton("Lets Go!");
          buttonAuth.setBounds(420, 250, 150, 50);
          buttonAuth.setBackground(Color.WHITE);
          buttonAuth.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_main.setVisible(false);
                
                JFrame frame_auth = new JFrame("Book Monitoring System - Authentication Form");
                 frame_auth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 frame_auth.setLayout(new FlowLayout());
                 frame_auth.setSize(500,500);
                 frame_auth.setLocationRelativeTo(null);
                 frame_auth.setVisible(true);
                 
                JLabel label1 = new JLabel("> User:");
                 Font fo = new Font("Calibri (Body)",Font.BOLD,12);
                 label1.setForeground(Color.WHITE);
                 label1.setFont(fo);
                 label1.setBounds(50,20,300,100);
         
                JLabel label2 = new JLabel("> Password:");
                 Font fon = new Font("Calibri (Body)",Font.BOLD,12);
                 label2.setForeground(Color.WHITE);
                 label2.setFont(fon);
                 label2.setBounds(50,95,150,50);
                 
                JPanel panel_log = new JPanel();
                 panel_log.setLayout(null);
                 panel_log.setBounds(10,50,60,20);
                 panel_log.setBackground(Color.GRAY);
                 panel_log.setPreferredSize(new Dimension(500,500));
                 
                 JLabel label3 = new JLabel("LOG - IN");
                  Font font = new Font("Comic Sans MS",Font.BOLD,20);
                  label3.setFont(font);
                  label3.setForeground(Color.WHITE);
                  label3.setBounds(10,20,150,20);
                  label3.add(panel_log);
        
                JPanel panel_auth = new JPanel();
                 panel_auth.setLayout(null);
                 panel_auth.setBounds(10,50,60,20);
                 panel_auth.setBackground(Color.GRAY);
                 panel_auth.setPreferredSize(new Dimension(500,500));
                 panel_auth.add(label1);
                 panel_auth.add(label2);
                 
                JTextField user = new JTextField();
                 user.setBounds(150,50,150,30);
                JTextField pass = new JTextField();
                 pass.setBounds(150,100,150,30);
                 
                 JButton buttonLogin = new JButton("Login Access");
                  buttonLogin.setBounds(50,160,150,30);
                 JButton buttonRegis = new JButton("Register");
                  buttonRegis.setBounds(50,200,150,30); 
                 
                 
                 frame_auth.add(panel_auth);
                 panel_auth.add(user);
                 panel_auth.add(pass);
                 panel_auth.add(buttonLogin);
                 panel_auth.add(buttonRegis);
                 panel_auth.add(panel_log);
               
                
            }
         });
        
        // - Main Output for Whole Program -
        frame_main.add(panel_main);
        //pn1.add(txt1);
        frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //panel_main.add(buttonAuth);
        panel_main.add(buttonBooks);
        panel_main.add(buttonBorrowed);
           
    }
    
}


