import java.sql.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.io.*;
public class login
{
	Connection con=null;
	PreparedStatement pst=null;
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	String log()throws Exception
	{
		while(true)
		{   UIManager.put("OptionPane.messageFont",new Font("Arial",Font.BOLD,18));
		    UIManager.put("OptionPane.messageForeground",Color.red);
			JFrame j=new JFrame();
			j.setAlwaysOnTop(true);
	      try{
	    	    con=Sqliteconnect.sqlite();
			    System.out.println("****************************************");
				System.out.println("Username :");
			    String name=br.readLine();
			    pst=con.prepareStatement("select * from logininfo where username=?");
			    pst.setString(1, name);
		        ResultSet rs=pst.executeQuery();
		        System.out.println("Password :");
			    String p=br.readLine();
		          if(rs.getString("password").contentEquals(p)) 
		          {
		           UIManager.put("OptionPane.messageForeground",Color.blue);
			       JOptionPane.showMessageDialog(j,"Login Successfull");
			       JOptionPane.showMessageDialog(j,"Welcome "+name,"welcome",JOptionPane.PLAIN_MESSAGE);
			       pst.close();
			       return name;
		          }
		          else 
		          {
			        JOptionPane.showMessageDialog(j,"wrong password","WARNING",JOptionPane.WARNING_MESSAGE);
		           }
		            pst.close();
		            rs.close();
		        
	           } catch(Exception e) {
			             JOptionPane.showMessageDialog(j,"This username doesnt exists","NOT FOUND",JOptionPane.ERROR_MESSAGE);
		            }
		  } 
	  }
}

