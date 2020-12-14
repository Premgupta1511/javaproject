import java.sql.*;
import javax.swing.*;

import java.awt.*;
import java.io.*;
public class viewticket 
{
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	String pnr=null;
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	viewticket(String i)throws Exception
	{
		while(true)
		{
		   JFrame j=new JFrame();
		   j.setAlwaysOnTop(true);
		   UIManager.put("OptionPane.messageFont",new Font("Arial",Font.BOLD,18));
		   UIManager.put("OptionPane.messageForeground",Color.black);
		   pnr=JOptionPane.showInputDialog(j,"PNR :");
		      if(pnr==null) 
		    	 break;
		      else if(pnr.isBlank()) {
		    	    UIManager.put("OptionPane.messageForeground",Color.red);
		    	    JOptionPane.showMessageDialog(j,"Enter PNR","VALIDATION",JOptionPane.ERROR_MESSAGE);
			    	continue;
		          }
		       else try{
		    	         con=Sqliteconnect.sqlite();
		    	         pst=con.prepareStatement("select * from ticketinfo where username=? and pnr=?");
		    	         pst.setString(1, i);
		    	         pst.setString(2, pnr);
		                 rs=pst.executeQuery();
		                 UIManager.put("OptionPane.messageFont",new Font("Arial",Font.BOLD,20));
		      		     UIManager.put("OptionPane.messageForeground",Color.black);
		                 String disp="Ticket Booked at "+rs.getString(7)+"\n  Name           : "+rs.getString(2)+"\n  Age              : "+rs.getInt(3)+"\n  From            : "+rs.getString(4)+"\n  To                 : "+rs.getString(5)+"\n  Date             : "+rs.getString(8);
		                 String disp2="\n  Train name  : "+rs.getString(9)+"\n  Train no       : "+rs.getString(10)+"\n  Departure    :"+rs.getString(12)+"\n  Arrival         : "+rs.getString(11);
		                 JOptionPane.showMessageDialog(j,disp+disp2,"Booking details",JOptionPane.INFORMATION_MESSAGE);
		                 pst.close();
		                 break;
		                }
		    	         catch(Exception e) {
		    		        UIManager.put("OptionPane.messageForeground",Color.red);
			                JOptionPane.showMessageDialog(j,"Enter Valid PNR","NOT FOUND",JOptionPane.ERROR_MESSAGE);
		                         }
	      }
     }
}