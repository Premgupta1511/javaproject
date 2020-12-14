import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
import java.util.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.io.*;
public class booking {
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
	 int j,sr;
	 String fr,to;
	 
	booking(String i)throws Exception
	{ 
	  while(true)
	  {  
		UIManager.put("OptionPane.messageFont",new Font("Arial",Font.BOLD,18));
	    UIManager.put("OptionPane.messageForeground",Color.red); 
		JFrame jp=new JFrame();
		jp.setAlwaysOnTop(true);
		try {
		    System.out.println("****************************************");
		    System.out.println("Passenger Details");
		    System.out.println("Name :");
		    String n=br.readLine();
		    boolean bn=Pattern.matches("^[a-zA-Z][a-zA-Z\s]*", n);   //NAME validation using REGULAR EXPRESSION
		     if(bn==false) {
		        JOptionPane.showMessageDialog(jp,"* ENTER Valid NAME\n* NAME CANNOT contain DIGITS or SPECIAL CHARACTERS","VALIDATION",JOptionPane.ERROR_MESSAGE);
				  continue;
		        }
		    System.out.println("Age :");
		    int a=Integer.parseInt(br.readLine());
		      if(a==0) {
			     JOptionPane.showMessageDialog(jp,"Enter Valid AGE","VALIDATION",JOptionPane.ERROR_MESSAGE);
			     continue;
		         }
		    System.out.println("Date Of Journey (DD/MM/2020) :");
		    String doj=br.readLine();
		    boolean bd=Pattern.matches("^([12][0-9]|3[0-1]|0[1-9])/(1[0-2]|0[1-9])/2020", doj);  //DATE validation using REGULAR EXPRESSION
		       if(bd==false) {
			     JOptionPane.showMessageDialog(jp,"Enter Valid DATE in the given format (ex:09/05/2020)","VALIDATION",JOptionPane.ERROR_MESSAGE);
			     continue;
		         }
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
             UIManager.put("OptionPane.messageForeground",Color.black); 
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
	           System.out.println("\nEnter a  serial number, 0 to cancel");
	           sr=Integer.parseInt(br.readLine());
	                 if(sr==0)
	            	   break;
	                 if(sr>j-1) {
	                	 UIManager.put("OptionPane.messageForeground",Color.red); 
	            	   JOptionPane.showMessageDialog(jp,"Enter Valid Serial Number","VALIDATION",JOptionPane.ERROR_MESSAGE);
	  			       continue;
	                 }
		       int ra=ThreadLocalRandom.current().nextInt(100000000, 999999999); // Function to input random number within specified range
		       String pnr=String.valueOf(ra);
		       java.util.Date date=new java.util.Date();     //Object that gives the CURRENT System DATE and TIME
		       String d=date.toString();
		       
		       UIManager.put("OptionPane.messageFont",new Font("Arial",Font.BOLD,20));
			   String q="Name           : "+n+"\nAge              : "+a+"\nFrom            : "+fr+"\nTo                 : "+to+"\nDate             : "+doj+"\nTrain name  : "+Tname[sr]+"\nTrain no       : "+Tno[sr]+"\n\nConfirm Booking?";
			   int conf=JOptionPane.showConfirmDialog(jp,q,"confirmation",JOptionPane.OK_CANCEL_OPTION);
			          if(conf==2||conf==-1)
				        break;
			          else 
			          {
			        	  con=Sqliteconnect.sqlite();
			    	   String sq="insert into ticketinfo(username,name,age,fromd,tod,pnr,datee,dateob,Tname,Tno,arrival,departure) values(?,?,?,?,?,?,?,?,?,?,?,?)";        //SQL command tp insert into database
			           pst=con.prepareStatement(sq); 
			          // Assigning each placeholder(i.e.?) with the value entered by user
			           pst.setString(1, i);             
			           pst.setString(2, n);
			           pst.setInt(3, a);
			           pst.setString(4, fr);
			           pst.setString(5, to);
			           pst.setString(6,pnr);
			           pst.setString(7, d);
			           pst.setString(8,doj);
			           pst.setString(9,Tname[sr]);
			           pst.setInt(10, Tno[sr]);
			           pst.setString(11,arr[sr]);
			           pst.setString(12,dept[sr]);
			           pst.executeUpdate();
		               String q1="\n\nNOTE: PLEASE RECORD PNR FOR FURTHER TICKET INTERACTIONS ";
		               JOptionPane.showMessageDialog(jp,"Booking successfull At "+d+"\nPNR :"+ra+q1,"Important",JOptionPane.INFORMATION_MESSAGE);
		               pst.close();
		               con.close();
		                break;
			           }
		          }catch(Exception e) {  
				          JOptionPane.showMessageDialog(jp,e,"ERROR",JOptionPane.ERROR_MESSAGE);
		           }
		 
		
		}
	}
}