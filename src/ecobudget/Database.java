package ecobudget;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Database{
	public void ItemAdding (Item item,int j) 
	{
		try
			{
				File file = new File("Items.txt");
				FileWriter fr = new FileWriter(file, true);
				BufferedWriter br = new BufferedWriter(fr);
				if (j == 0) 
				{
					PrintWriter writer = new PrintWriter(file);
					writer.print("");
					writer.close();
				}
				for (int i = 0;i<4;i++) 
				{
					br.write(item.ToStringArray()[i]);						//write to txt.file
					br.newLine();
				}
				br.close();
				fr.close();

			} catch (IOException e) {
				
			}
	}
	public void ItemEmpty () 													//Last member of item case
	{
		try
			{
				File file = new File("Items.txt");
				FileWriter fr = new FileWriter(file, true);
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();
				fr.close();

			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	}
	
	public void CategoryAdding(String Cinput,int i) 
	{
		try
		{
			File file = new File("Categories.txt");
			FileWriter fr = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fr);
			if (i == 0) 
			{
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();
			}
			br.write(Cinput);						//write to txt.file
			br.newLine();
			br.close();
			fr.close();

		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}	
	public void CategoryEmpty() 														//Last member of category case
	{
		try
		{
			File file = new File("Categories.txt");
			FileWriter fr = new FileWriter(file, true);
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
			fr.close();

		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	public void FromTableToTxt() 
	{
		
	}
}
