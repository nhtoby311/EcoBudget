package ecobudget;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Category {
	
	private String type;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String ToString() 
	{
		String a = type +"";
		return a;
	}
	public String[] CategoryReader() 
	{
		String cat[] = new String [2000] ;
		int i =0;
		try 
		{	
			BufferedReader br = new BufferedReader(new FileReader("Categories.txt"));
			String line;
			while ((line =br.readLine()) != null) 
			{
				cat[i] = line;
				i++;
			}
			br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return cat;
	}
	

}
