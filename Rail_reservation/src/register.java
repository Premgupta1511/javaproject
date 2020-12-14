import java.sql.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.util.regex.*;
public class register {
	Connection con=null;
	PreparedStatement pst=null;
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	Console cs=System.console();
   void reg()throws Exception
   {  
	   JFrame j=new JFrame();
	   j.setAlwaysOnTop(true);
	   UIManager.put("OptionPane.messageFont",new Font("Arial",Font.BOLD,18));
	   UIManager.put("OptionPane.messageForeground",Color.red);
	   while(true) {
	   try {                                  
		   System.out.println("****************************************");
		   System.out.println("Create Username");
		   String name=br.readLine();
		   Pattern p=Pattern.compile("^[a-zA-z][\\w]{2,30}");          //USERNAME validation using Regular Expression 
		   Matcher m=p.matcher(name);
		   System.out.println("Create password");
		    String p1=br.readLine();
		   System.out.println("re-enter password");
		   String p2=br.readLine();
		 if( p1.isBlank() ||p2.isBlank()) {
			   JOptionPane.showMessageDialog(j,"ALL FIELDS ARE MANDATORY","Warning",JOptionPane.ERROR_MESSAGE);
			   continue;
		   }
		 if(m.matches()) 
		 {
		     if(p1.equals(p2)) 
		             {
		    	     con=Sqliteconnect.sqlite(); 
			         pst=con.prepareStatement("insert into logininfo(username,password) values(?,?)");
			         pst.setString(1,name);
			         pst.setString(2,p1);
			         pst.executeUpdate();
			         UIManager.put("OptionPane.messageFont",new Font("Arial",Font.BOLD,18));
					 UIManager.put("OptionPane.messageForeground",Color.blue);
			         JOptionPane.showMessageDialog(j,
			        		 "Registration Successful");
			         pst.close();
			         con.close();
			         break;
		              }
		      else 
			      JOptionPane.showMessageDialog(j,"Password doesnt match","Warning",JOptionPane.WARNING_MESSAGE);
		 }
		 else 
		 {
			 String po="USERNAME:\n* MUST contain MINIMUM 3 characters\n* CANNOT start with a DIGIT or SPECIAL CHARACTER\n* CANNOT contain special characters other than UNDERSCORE(_)\n* DOES NOT exceed 30 characters";
			 JOptionPane.showMessageDialog(j, po,"Rules",JOptionPane.ERROR_MESSAGE);
			 continue;
		 }
	       }catch(Exception e) {
	    	   JOptionPane.showMessageDialog(j, "Username Already Exists","Validation",JOptionPane.ERROR_MESSAGE);
	          }
     }
   }
}
