package ecobudget;


public class Item {
	
	private String name;
	private int price;
	private String type;
	private String date;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Item() 
	{
	}
	public Item(String Name,int Price,String Type,String Date) 
	{
		this.name = Name;
		this.price = Price;
		this.type = Type;
		this.date = Date;
	}
	public String[] ToStringArray() 
	{
		String []a = new String[4];
		a[0] = "N"+name ;
		a[1] = "P"+price;
		a[2] = "T"+type;
		a[3] = "D"+date;
		return a;
	}
	public String ToString() 
	{
		String a = "N"+name+" P"+price+" T"+type+" D"+date;
		return a;
	}
}
