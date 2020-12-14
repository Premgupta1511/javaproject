import java.sql.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.io.*;
public class cancelticket 
{
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	String pnr=null;
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	cancelticket(String i)throws Exception
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
	                 String disp2="\n  Train name  : "+rs.getString(9)+"\n  Train no       : "+rs.getString(10)+"\n  Departure    :"+rs.getString(12)+"\n  Arrival         : "+rs.getString(11)+"\n\n  Confirm Cancellation??";
		              int conf=JOptionPane.showConfirmDialog(j,disp+disp2,"Booking details",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		              pst.close();
		                if(conf==1||conf==-1)
		                    break;
		                else 
		                {
		        	      con=Sqliteconnect.sqlite();
			    	      pst=con.prepareStatement("delete  from ticketinfo where username=? and pnr=?");
			    	      pst.setString(1, i);
			    	      pst.setString(2, pnr);
			              pst.executeUpdate();
			              JOptionPane.showMessageDialog(j,"PNR :"+pnr+"\nTicket with the above PNR has been canceled");
			              pst.close();
			              break;
		                }
		              }
		    	       catch(Exception e) {
		    	    	   UIManager.put("OptionPane.messageForeground",Color.red);
			                JOptionPane.showMessageDialog(j,"Enter Valid PNR","NOT FOUND",JOptionPane.ERROR_MESSAGE);
		                 }
	       }
       }
}
