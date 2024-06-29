package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton login,clear,signup; //assigning as global variable so that it can be used to store the actions performed ourside the constructor
    JTextField cardtext; // only for normal text
    JPasswordField pintext; // only for password so that it is not visible
    Login(){
        setTitle("Automated teller machine");
        
        setLayout(null); //by default takes the property and arranges it to the desired position or else border layout is used. 
        //SetBound only works if setlayout is set to nuull
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2= i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT); //scaling the imageicon
        ImageIcon i3 = new ImageIcon(i2);  //since image cant be put inside jlabel. only imageicon can be put
        JLabel j1 = new JLabel(i3); //since we cantr place imageicon directly so we need a label
        j1.setBounds(70,10,100,100); //chaning the location of labels also see line 15
        add(j1); //adding label to the pop up window
        
        getContentPane().setBackground(Color.white); //contentpane selects the frame and then the background is set
        
        JLabel text = new JLabel("Welcome to the ATM"); //JLabel to add any text
        text.setFont(new Font("Osward",Font.BOLD, 38)); //select the type of font
        text.setBounds(200,40,400,40); //tell where to show in the frame
        //we hve setlayout=null so it wont show unless u setbound
        add(text);
        
        JLabel cardno = new JLabel("Card No."); //JLabel to add any text
        cardno.setFont(new Font("Raleway",Font.BOLD, 28)); //select the type of font
        cardno.setBounds(120,150,400,30); //tell where to show in the frame
        add(cardno);
        
        cardtext = new JTextField();
        cardtext.setBounds(300,150,230,30);
        cardtext.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardtext);
        
        JLabel pin = new JLabel("PIN"); //JLabel to add any text
        pin.setFont(new Font("Raleway",Font.BOLD, 28)); //select the type of font
        pin.setBounds(120,220,250,30); //tell where to show in the frame
        add(pin);
        
        pintext = new JPasswordField();
        pintext.setBounds(300,220,230,30);
        pintext.setFont(new Font("Arial", Font.BOLD, 14));
        add(pintext);
        
        login = new JButton("SIGN IN");
        login.setBounds(300,300,100,30);
        login.setOpaque(true);
        login.setBorderPainted(false);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);
        
        clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setOpaque(true);
        clear.setBorderPainted(false);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);
        
        signup = new JButton("SIGN UP");
        signup.setBounds(300,350,230,30);
        signup.setOpaque(true);
        signup.setBorderPainted(false);
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);
        
        setSize(800,500);
        setVisible(true); //since the window is not visible so we have to make it visible
        setLocation(350,200); //pops up from the location given
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
            Conn c = new Conn();
            String cardnumber = cardtext.getText();
            String pinnumber = pintext.getText();
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";
            try{
                ResultSet res = c.s.executeQuery(query); //table returned from msql is of resultset datatype
                if(res.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or Pin" );
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        else if(ae.getSource()==clear){
            cardtext.setText("");
            pintext.setText("");
        }
        else if(ae.getSource()==signup){
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
}
