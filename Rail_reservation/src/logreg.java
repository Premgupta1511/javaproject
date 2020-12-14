import java.awt.Color;
import java.io.*;
import javax.swing.*;

public class logreg {
	@SuppressWarnings("unused")
	String manage()throws Exception
	{
		JFrame j=new JFrame();
		j.setAlwaysOnTop(true);
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	register r=new register();
	login l=new login();
	String i=null;
	while(i==null) 
	{
    System.out.println("****************************************");
    System.out.println("1=>>Check Train Availability");
	System.out.println("2=>>Register New User");
	System.out.println("3=>>Login");
	System.out.println("4=>>EXIT");
	int a=Integer.parseInt(br.readLine());
	switch(a)
	{
	case 1: trainAvail av=new trainAvail();
	        break;
	case 2: r.reg();
	        break;
	case 3:  i=l.log();
	        break;
	case 4: System.exit(0);
	default:UIManager.put("OptionPane.messageForeground",Color.red);
		    JOptionPane.showMessageDialog(j,"Enter Valid Choice","ERROR",JOptionPane.ERROR_MESSAGE);
	         break;
	}
	
	}
	return i;
}
}
