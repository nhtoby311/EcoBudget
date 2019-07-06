package ecobudget;

import java.awt.BorderLayout;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CategoryEdit extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private Category cat ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		try {
			CategoryEdit dialog = new CategoryEdit();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CategoryEdit() {
		cat = new Category();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textField.setBounds(37, 96, 192, 41);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblCategoryName = new JLabel("Category Name");
			lblCategoryName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblCategoryName.setBounds(37, 52, 142, 33);
			panel.add(lblCategoryName);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(250, 27, 161, 201);
			panel.add(panel_1);
			panel_1.setBorder(BorderFactory.createTitledBorder("Lists"));
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table = new JTable(){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int row, int column) {                				//not editable table
	            return false;               
	    };};
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Categories"
				}
			));
			AddTable();
			scrollPane.setViewportView(table);
			
			JButton btnNewButton = new JButton("Add");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Database d = new Database();
					int count =0;
					while (cat.CategoryReader()[count] != null) 
					{
						count++;
					} 
					if (Search(cat.CategoryReader(),count,textField.getText()) == true)
					{
						JOptionPane.showMessageDialog(null,
							    "Already have");
					}
					else if (textField.getText().equals("") == false) 
					{
					d.CategoryAdding(textField.getText(),1);
					AddTable();
					}
					else 
					{
						JOptionPane.showMessageDialog(null,
							    "Input category name");
					}
				}
			});
			btnNewButton.setBounds(91, 148, 89, 33);
			panel.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Delete");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(row);
					
					Database d = new Database();
					int rowC = table.getRowCount();
					Object[] value = new Object[rowC];
					System.out.println(rowC);
					if (rowC == 0) 
					{
						d.CategoryEmpty();
					}
					else {
						for (int j = 0; j  < rowC; j++) {															//Lay thong tin tu bang, cho vao array value[]
							value[j] = table.getValueAt(j,0);
							System.out.println(value[j]);
							}
						for (int j = 0; j  < rowC; j++) 
						{
							d.CategoryAdding(value[j].toString(),j);
						}
					}
				}
			});
			btnNewButton_1.setBounds(90, 195, 89, 33);
			panel.add(btnNewButton_1);
		}
	}
	public void AddTable() 											
	{
		int count =0;
		while (cat.CategoryReader()[count] != null) 
		{
			count++;
		} 
		DefaultTableModel model = (DefaultTableModel) table.getModel(); 
		model.setRowCount(0);
		Object []categories =  new Object[1];
		for (int i = 0;i<count;i++) {
			categories[0] = cat.CategoryReader()[i];
			model.addRow(categories);
		}
	}
	
	
	public Boolean Search(String []cat,int n, String key) 
	{
		if(n<0) 
		{
			return false;
		}
		else if (key.equals(cat[n]) == true) 
		{
			return true;
		}
		return Search(cat,n-1,key);
	}
}
