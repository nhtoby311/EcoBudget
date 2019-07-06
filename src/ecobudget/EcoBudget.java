package ecobudget;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class EcoBudget {
	
	private JFrame frame;
	private JTable table;
	private JLabel Label_1;
	private JTextField txtItem;
	private JTextField txtCost;
	private JTextField txtDate;
	private JComboBox<Object> comboBox ;
	private ItemInputFrame Iteminput ;
	private BalanceEdit BalanceE ;
	private Balance balance ;
	private CategoryEdit CategoryE ;
	private Category cat ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcoBudget window = new EcoBudget();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcoBudget() {
		initialize();
		run();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		File CategoriesFile = new File("Categories.txt");
		try {
			CategoriesFile.createNewFile();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		File ItemsFile = new File("Items.txt");								//Generate file first
		try {
			ItemsFile.createNewFile();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		File BalanceFile = new File("Balance.txt");
		try {
			BalanceFile.createNewFile();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		Iteminput = new ItemInputFrame();
		BalanceE = new BalanceEdit();
		balance = new Balance();
		CategoryE = new CategoryEdit();
		cat = new Category();
		
		frame = new JFrame();
		frame.setBounds(600, 250, 891, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 875, 454);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Main", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Balance:");
		lblNewLabel_4.setBounds(316, 58, 90, 24);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 24));
		
		Label_1 = new JLabel("New label");
		Label_1.setBounds(407, 59, 108, 25);
		panel.add(Label_1);
		Label_1.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 22));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(316, 111, 533, 304);
		panel.add(scrollPane);
		
		table = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                				//not editable table
            return false;               
    };};
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setRowHeight(25);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				int row =table.getSelectedRow();
				txtItem.setText(table.getModel().getValueAt(row,0).toString());
				txtCost.setText(table.getModel().getValueAt(row,1).toString());
				comboBox.setSelectedItem(table.getModel().getValueAt(row,2).toString());
				txtDate.setText(table.getModel().getValueAt(row,3).toString());			
				}
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,
						    "Click on 'Edit' first to edit");
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Items", "Cost", "Category", "Date"
			}
		));
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(30);
		columnModel.getColumn(2).setPreferredWidth(70);
		columnModel.getColumn(3).setPreferredWidth(20);
			
		scrollPane.setViewportView(table);
		
		JButton btnNewItems = new JButton("Add Items");
		btnNewItems.setBounds(145, 146, 118, 23);
		panel.add(btnNewItems);
		btnNewItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iteminput = new ItemInputFrame();							//update information
				Iteminput.setVisible(true);
			}
		});
		btnNewItems.setActionCommand("Add Items");
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(760, 80, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(270, 60, 41, 22);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(651, 80, 89, 23);
		panel.add(btnEdit);
		
		JButton btnNewButton_1 = new JButton("Add Category");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryE.setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Item");
		JLabel lblNewLabel_1 = new JLabel("Cost");
		JLabel lblNewLabel_2 = new JLabel("Category");
		JLabel lblNewLabel_3 = new JLabel("Date");
		
		btnNewButton_1.setBounds(145, 218, 118, 23);
		panel.add(btnNewButton_1);
		btnEdit.addActionListener(new ActionListener() {
			private Boolean status = true;
			public void actionPerformed(ActionEvent e) {
				if (status == true) 
				{
				frame.setSize(1100, 493);
				
				lblNewLabel.setBounds(900, 117, 48, 14);
				frame.getContentPane().add(lblNewLabel);
				
				txtItem = new JTextField();
				txtItem.setBounds(900, 135, 96, 23);
				frame.getContentPane().add(txtItem);
				txtItem.setColumns(10);
				
				lblNewLabel_1.setBounds(900, 174, 48, 14);
				frame.getContentPane().add(lblNewLabel_1);
				
				txtCost = new JTextField();
				txtCost.setBounds(900, 194, 96, 20);
				frame.getContentPane().add(txtCost);
				txtCost.setColumns(10);
				
				lblNewLabel_2.setBounds(900, 233, 48, 14);
				frame.getContentPane().add(lblNewLabel_2);
								
				Object[] CatI = cat.CategoryReader();
				comboBox = new JComboBox<Object> ();
				comboBox.setBounds(900, 253, 96, 20);
				for (Object i : CatI) 
				{
					if (i != null)
					{
					comboBox.addItem(i);
					}
				}
				frame.getContentPane().add(comboBox);
				
				//JLabel lblNewLabel_3 = new JLabel("Date");
				lblNewLabel_3.setBounds(900, 292, 48, 14);
				frame.getContentPane().add(lblNewLabel_3);
				
				txtDate = new JTextField();
				txtDate.setBounds(900, 312, 96, 20);
				frame.getContentPane().add(txtDate);
				txtDate.setColumns(10);
				
				JButton btnDone = new JButton("Done");
				btnDone.setBounds(900, 360, 96, 23);
				frame.getContentPane().add(btnDone);
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						int row =table.getSelectedRow();
						Item item;
						Database d = new Database();
						table.getModel().setValueAt(txtItem.getText(), row, 0);
						table.getModel().setValueAt(txtCost.getText(), row, 1);
						table.getModel().setValueAt(comboBox.getSelectedItem().toString(), row, 2);
						table.getModel().setValueAt(txtDate.getText(), row, 3);
						
						int rowC = table.getRowCount();
						int columnC = table.getColumnCount();
						Object[][] value = new Object[rowC][columnC];
							for (int j = 0; j  < rowC; j++) {															//Lay thong tin tu bang, cho vao array value[]
							    for (int i = 0; i  < columnC; i++) {
							    	value[j][i] = table.getValueAt(j,i);
							        //System.out.println(value[j][i]);
							    }
							}
						for (int j = 0; j  < rowC; j++) {
							item = new Item(value[j][0].toString(),Integer.parseInt(value[j][1].toString()),value[j][2].toString(),value[j][3].toString()); //Cho thong tin vao file text (database)
							d.ItemAdding(item,j);
							}
						Update(Label_1);
						}
						catch (Exception e1)
						{
							JOptionPane.showMessageDialog(null,
								    "Select row in the table first");
						}
						
					}
					
				});
				
				JButton btnDelete = new JButton("Delete");
				btnDelete.setBounds(900, 390, 96, 23);
				frame.getContentPane().add(btnDelete);
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						int row =table.getSelectedRow();
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(row);
						
						Item item;
						Database d = new Database();
						int rowC = table.getRowCount();
						int columnC = table.getColumnCount();
						Object[][] value = new Object[rowC][columnC];
						if (rowC ==0 || columnC ==0) 														//Truong hop gia tri cuoi cung cua bang
						{
							d.ItemEmpty();
						}
						else {
							for (int j = 0; j  < rowC; j++) {															//Lay thong tin tu bang, cho vao array value[]
								for (int i = 0; i  < columnC; i++) {
								    value[j][i] = table.getValueAt(j,i);
								}
							}
							for (int j = 0; j  < rowC; j++) {
								item = new Item(value[j][0].toString(),Integer.parseInt(value[j][1].toString()),value[j][2].toString(),value[j][3].toString()); //Cho thong tin vao file text (database)
								d.ItemAdding(item,j);
							}
						}
							Update(Label_1);
							
						}
						catch (Exception e2)
						{
							JOptionPane.showMessageDialog(null,
								    "Select row in the table first");
						}
					}
				});

				status = false;
				
		}
				else 
				{
					frame.setSize(891, 493);
					frame.setResizable(false);
					status = true;
					frame.getContentPane().remove(txtCost);
					frame.getContentPane().remove(txtItem);
					frame.getContentPane().remove(txtDate);
					frame.getContentPane().remove(comboBox);
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BalanceE.setVisible(true);
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update(Label_1);
			}
		});
	}
	
	
	

	private void run() 
	{
		InputDecode() ;
		DisplayTable();
		Balance(Label_1);
	
}

	public void Balance(JLabel Label) 			//Set balance
	{
		Label.setText(balance.ToString());
	}
	
	public void Update(JLabel Label) 
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);														//update table information
		DisplayTable();
		Balance(Label_1);
	}
	
	
	public void DisplayTable() 
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel(); 	//add row
		int i =0;
		Object b[] = new Object[4];
		List<Object> cost = new ArrayList<>();
		while (InputDecode()[i] != null) 
		{
			b[0]=InputDecode()[i];								//Name*
			i++;
			b[1]=InputDecode()[i];								//Price*
			i++;
			//System.out.println("\n run " +b[1]);
			cost.add(b[1]);
			b[2]=InputDecode()[i];								//Category*
			i++;
			b[3]=InputDecode()[i];								//Date*
			i++;
			model.addRow(b);									//switch to next row
		}
		balance.BalanceCalculate(cost);   						//Calculate Balance, pass in List<Object>
	}
	
	public Object[] InputDecode() 
	{
		Object []table = new Object[2000] ;
		String result="";
		try (BufferedReader br = new BufferedReader(new FileReader("Items.txt")))
		{	
			
			String line;
			
			int i =0;
			while ((line =br.readLine()) != null) 
			{
				result=line.substring(1);
				table[i] = result;
				i++;
			}
			return table;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return table; 
	}
}



