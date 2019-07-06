package ecobudget;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Balance {
	
	private int balance = 0;
	private int temp = 0;
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void BalanceInput() {
		try
		{
			File file = new File("Balance.txt");
			FileWriter fr = new FileWriter(file, true);
			PrintWriter writer = new PrintWriter(file);
			writer.print("");					//Empty file
			writer.print(balance+"");
			writer.close();						//write to txt.file
			fr.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	public String BalanceReader() 
	{
		String line="";
		try (BufferedReader br = new BufferedReader(new FileReader("Balance.txt")))
		{	

			line =br.readLine();
			return line;

		} 
		catch (IOException e) {
			e.printStackTrace();
		}			
		return line;
	}
	
	
	public String ToString() 
	{
		String a;
		if (BalanceReader() == null) 								//First time running
		{
			a = "0";
			return a;
		}
		balance = Integer.parseInt(BalanceReader())+temp;			//calculate total balance
		a = ""+balance;
		return a;
	}
	
	public void BalanceCalculate(List<Object>cost) 
	{
		temp =0;									//keep temp unchanged 
		for(Object a :cost) 
		{
			int b = 0 -Integer.valueOf((String) a);	//from Object to int negative
			temp = temp + b;	
			System.out.println(temp);
		}
	}
	
}