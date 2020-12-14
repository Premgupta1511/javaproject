import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
public class trainAvail 
{
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	Set<String> f1=new HashSet<String>();
	Set<String> f2=new HashSet<String>();
	String frop[]=new String[10];
	String Top[]=new String[10];
	String Tname[]=new String[10];
	int Tno[]=new int[10];
	String arr[]=new String[10];
	String dept[]=new String[10];
	String dur[]=new String[10];
	int j;
	String fr,to;
	
	trainAvail()
	{
	while(true)
	{   
		JFrame jp=new JFrame();
		jp.setAlwaysOnTop(true);
		UIManager.put("OptionPane.messageFont",new Font("Arial",Font.BOLD,18));
		UIManager.put("OptionPane.messageForeground",Color.black);
		  try {
			   con=Sqliteconnect.sqlite();
			   pst=con.prepareStatement("select DISTINCT fromd,tod from traininfo  ");
	           rs=pst.executeQuery();
	            while(rs.next()) 
	             {
	        	   f1.add(rs.getString("fromd"));
	        	   f2.add(rs.getString("tod"));
	             }
	           pst.close();
	           rs.close();
	           frop=f1.toArray(new String[f1.size()]);
	           Top=f2.toArray(new String[f2.size()]);
	           fr=(String)JOptionPane.showInputDialog(jp,"FROM :","Selection",JOptionPane.QUESTION_MESSAGE,null,frop,frop[0]);
	             if(fr==null)
	        	    break;
	           to=(String)JOptionPane.showInputDialog(jp,"TO :","title",JOptionPane.QUESTION_MESSAGE,null,Top,Top[0]);
	             if(fr==null)
	        	    break;
		     if(fr.equals(to)) {
				   UIManager.put("OptionPane.messageForeground",Color.red);
				   JOptionPane.showMessageDialog(jp,"From and To destinations Cannot be same ","ERROR",JOptionPane.ERROR_MESSAGE);
				   continue;
			    }
		    
		         pst=con.prepareStatement("select * from traininfo where fromd=? and tod=? ");
		         pst.setString(1, fr);
		         pst.setString(2, to);
		         rs=pst.executeQuery();
		         j=1;
		           while(rs.next()) 
		           {
		         	 Tname[j]=rs.getString("Tname");
		         	 Tno[j]=rs.getInt("Tno");
		         	 arr[j]=rs.getString("arrival");
		         	 dept[j]=rs.getString("departure");
		         	 dur[j]=rs.getString("duration");
		         	 System.out.println("SR NO : "+j+"\t\tTrain name : "+Tname[j]+"\t\tTrain NO : "+Tno[j]+"\t\tDeparture : "+dept[j]+"\t\t Arrival : "+arr[j]+"\t\tDuration : "+dur[j]);
		         	 j++;
		           }
		           pst.close();
		           rs.close();
		           con.close();
		           break;
		    
		         }catch(Exception e) {
			     JOptionPane.showMessageDialog(jp,e,"ERROR",JOptionPane.ERROR_MESSAGE);
		       }
	   }
			 
    }

}
