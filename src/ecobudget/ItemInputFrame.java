package ecobudget;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import javax.swing.JComboBox;

public class ItemInputFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtItem;
	private JTextField txtCost;
	private JTextField txtDate;
	private Item item;
	private Database d = new Database();
	private Category cat = new Category();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemInputFrame frame = new ItemInputFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ItemInputFrame() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(900, 500, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item");
		lblNewLabel.setBounds(10, 11, 48, 14);
		contentPane.add(lblNewLabel);
		
		txtItem = new JTextField();
		txtItem.setBounds(10, 29, 96, 20);
		contentPane.add(txtItem);
		txtItem.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cost");
		lblNewLabel_1.setBounds(10, 68, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCost = new JTextField();
		txtCost.setBounds(10, 88, 96, 20);
		contentPane.add(txtCost);
		txtCost.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Category");
		lblNewLabel_2.setBounds(10, 130, 96, 14);
		contentPane.add(lblNewLabel_2);
		
		
		JLabel lblNewLabel_3 = new JLabel("Date");
		lblNewLabel_3.setBounds(10, 190, 48, 14);
		contentPane.add(lblNewLabel_3);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate Date = LocalDate.now();
		
		txtDate = new JTextField();
		txtDate.setText(dtf.format(Date));
		txtDate.setBounds(10, 214, 96, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		Object[] CatI = cat.CategoryReader();
		JComboBox<Object> comboBox = new JComboBox<Object> ();
		comboBox.setBounds(10, 154, 96, 20);
		for (Object i : CatI) 
		{
			if (i != null)
			{
			comboBox.addItem(i);
			}
		}
		contentPane.add(comboBox);
		
		JButton AddItembutton = new JButton("Add Item");
		AddItembutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboBox.getSelectedItem() == null) 
					{
						JOptionPane.showMessageDialog(null,
							    "Add category first");
					}
					else 
					{
					String [] a = new String[4];
					a[0] =txtItem.getText();
					a[1] =txtCost.getText();
					a[2] =comboBox.getSelectedItem().toString();
					a[3] =txtDate.getText();
					item = new Item(a[0],Integer.parseInt(a[1]),a[2],a[3]);					//Add item, get user input data
					d.ItemAdding(item,1);												//Dont have to reset item text file
					}
				}
				catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
						    "You need to input informations");
					}
			}
		});
		AddItembutton.setBounds(241, 68, 117, 123);
		contentPane.add(AddItembutton);
		
	}

	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
