package inventory;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.JScrollPane;
import javax.swing.SortOrder;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InventoryMain extends JFrame implements MustHaveFunctions {
	Random random = new Random(); // for item id
	String checkName = ""; // holder of name checker
	boolean sorted = false;
	int rowSelected;
	static DefaultTableModel inventoryTable = new DefaultTableModel();
	private JPanel mainPane;
	static JTable table;
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextField descriptionTextField;
	private JTextField pathFileTextField;
	private JTextField searchTextField;
	private JTextField quantityTextField;

	
	public InventoryMain() {
		setBackground(Color.DARK_GRAY);
		//set main design and settings
		setForeground(new Color(0, 0, 0));
		setTitle("Computer & Devices Inventory System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		

		mainPane = new JPanel();
		mainPane.setBackground(Color.WHITE);
		setBounds(100, 100, 1296, 645);
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPane.setLayout(null);
		setContentPane(mainPane);

		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel greenPanel = new JPanel();
		JPanel yellowPanel = new JPanel();
		nameTextField = new JTextField();
		priceTextField = new JTextField();
		JLabel invetoryTableLabel = new JLabel(" Inventory Information Table");
		JLabel nameLabel = new JLabel("Item Name");
		JLabel priceLabel = new JLabel("Item Price(PHP)");
		nameLabel.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		JLabel categoryLabel = new JLabel("Item Category");
		JLabel quantityLabel = new JLabel("Item Quantity");
		JLabel descriptionLabel = new JLabel("Description");
		
		
		JButton updateButton = new JButton("Update Item");
		JButton exportButton = new JButton("Export");
		JButton deleteButton = new JButton("Delete Item");
		JButton importButton = new JButton("Import");
		JButton backButton = new JButton("Back");
		JButton searchButton = new JButton("Search");
		JButton addButton = new JButton("Add Item");
		JButton signoutButton = new JButton("Sign-out");
		JButton sortButton = new JButton("Sort Items");
		JScrollPane myScrollPane = new JScrollPane(); 
		JComboBox<Object> categoryCombo = new JComboBox<Object>();

		quantityTextField = new JTextField();
		pathFileTextField = new JTextField();
		searchTextField = new JTextField();
		descriptionTextField = new JTextField();

		greenPanel.setBackground(new Color(0, 102, 51));
		yellowPanel.setBackground(new Color(255, 250, 205));
		invetoryTableLabel.setForeground(Color.DARK_GRAY);
		priceLabel.setForeground(Color.WHITE);
		priceLabel.setBackground(Color.WHITE);
		descriptionLabel.setBackground(Color.WHITE);
		quantityLabel.setForeground(Color.WHITE);
		descriptionLabel.setForeground(Color.WHITE);
		quantityLabel.setBackground(Color.WHITE);
		categoryLabel.setForeground(Color.WHITE);
		categoryLabel.setBackground(Color.WHITE);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBackground(Color.WHITE);

		invetoryTableLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
		priceLabel.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		descriptionLabel.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		quantityLabel.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		categoryLabel.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		

		categoryCombo.setModel(new DefaultComboBoxModel(
				new String[] {"Computer", "Mouse", "Speaker", "Projector", "Cable", "Remote", "Monitor", "Laptop", "CPU", "Keyboard", "Charger"}));

		
		greenPanel.setBounds(0, 0, 352, 656);
		greenPanel.setLayout(null);
		yellowPanel.setBounds(352, 0, 938, 59);
		yellowPanel.setLayout(null);


		pathFileTextField.setBounds(12, 559, 161, 38);
		nameTextField.setColumns(10);
		priceTextField.setColumns(10);
		descriptionTextField.setColumns(10);
		pathFileTextField.setColumns(10);
		searchTextField.setColumns(10);
		quantityTextField.setColumns(10);
		nameTextField.setBounds(149, 96, 190, 38);
		descriptionTextField.setBounds(119, 303, 221, 107);
		priceTextField.setBounds(149, 148, 190, 38);
		searchTextField.setBounds(12, 27, 161, 38);
		quantityTextField.setBounds(149, 201, 190, 38);
		invetoryTableLabel.setBounds(366, 13, 302, 29);
		nameLabel.setBounds(12, 108, 116, 16);
		categoryCombo.setBounds(149, 252, 190, 38);
		categoryLabel.setBounds(12, 264, 116, 16);
		quantityLabel.setBounds(12, 213, 116, 16);
		priceLabel.setBounds(12, 160, 139, 16);
		descriptionLabel.setBounds(12, 320, 116, 16);
		addButton.setBounds(12, 423, 161, 45);
		updateButton.setBounds(178, 423, 161, 45);
		sortButton.setBounds(12, 474, 161, 45);
		deleteButton.setBounds(178, 474, 161, 45);
		exportButton.setBounds(262, 559, 76, 38);
		importButton.setBounds(178, 559, 79, 38);
		backButton.setBounds(262, 27, 76, 38);
		searchButton.setBounds(178, 27, 79, 38);
		signoutButton.setBounds(1181, 578, 97, 25);		// scroll for the table
		myScrollPane.setBounds(364, 72, 914, 507);
		

		mainPane.add(yellowPanel);
		mainPane.add(signoutButton);
		mainPane.add(myScrollPane);
		mainPane.add(greenPanel);
		greenPanel.add(descriptionTextField);
		greenPanel.add(pathFileTextField);
		greenPanel.add(searchTextField);
		greenPanel.add(quantityTextField);
		greenPanel.add(nameTextField);
		greenPanel.add(priceTextField);
		greenPanel.add(nameLabel);
		greenPanel.add(descriptionLabel);
		greenPanel.add(priceLabel);
		greenPanel.add(categoryLabel);
		greenPanel.add(quantityLabel);
		greenPanel.add(categoryCombo);
		greenPanel.add(sortButton);
		greenPanel.add(addButton);
		greenPanel.add(updateButton);
		greenPanel.add(deleteButton);
		greenPanel.add(searchButton);
		greenPanel.add(importButton);
		greenPanel.add(exportButton);
		greenPanel.add(backButton);
		yellowPanel.add(invetoryTableLabel);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		// define columns
		table.getTableHeader().setEnabled(false);
		Object[] columns = { "Product ID", "Name", "Price", "Description", "Quantity", "Category"};
		inventoryTable.setColumnIdentifiers(columns);
		table.setModel(inventoryTable);
		myScrollPane.setViewportView(table); // add table to the scroll pane


		// set alignment of columns and size
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.setRowHeight(50);

	
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(rootPane, "Add this to table?", "Confirm addition",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (response == 0) { 

					List<Integer> itemIDHolder = new ArrayList<>();
					for (int i = 0; i < 10; i++) {
						int alreadyRandomed = random.nextInt(999999);
						while (itemIDHolder.contains(alreadyRandomed))
							alreadyRandomed = random.nextInt(999999);
						itemIDHolder.add(alreadyRandomed);
					}
					if (!isDuplicate("productID", nameTextField.getText().toString())) {
						JOptionPane.showMessageDialog(null, "Error Must Have Unique Name", "Duplicate",
								JOptionPane.ERROR_MESSAGE);
					}else if (nameTextField.getText().trim().equals("") || priceTextField.getText().trim().equals("")
							|| quantityTextField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please fill up the required fields.", "Null",
								JOptionPane.ERROR_MESSAGE);
					}else if (!isInteger(quantityTextField.getText()) || !isInteger(priceTextField.getText()) ||
							priceTextField.getText().equals("0") || quantityTextField.getText().equals("0")) {
						JOptionPane.showMessageDialog(null,
								"Please input a valid number in quantity and price fields", "Input Error",
								JOptionPane.WARNING_MESSAGE);
					}else { 
						String descriptionText;
						if (descriptionTextField.getText().equals("")) descriptionText = "None";
						else descriptionText = descriptionTextField.getText();
						
						String itemId = String.format("%06d", itemIDHolder.get(0));
						String categoryText = categoryCombo.getSelectedItem().toString();
						inventoryTable.addRow(new Object[] {
								itemId, nameTextField.getText(), priceTextField.getText(), descriptionText,
								quantityTextField.getText(), categoryText});
					}

				}

			}

		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowSelected = table.getSelectedRow();
				if (sorted) rowSelected = table.convertRowIndexToModel(rowSelected);
				try {// try to get values of a row and if empty then catch

					nameTextField.setText(inventoryTable.getValueAt(rowSelected, 1).toString());
					priceTextField.setText(inventoryTable.getValueAt(rowSelected, 2).toString());
					descriptionTextField.setText(inventoryTable.getValueAt(rowSelected, 3).toString());
					quantityTextField.setText(inventoryTable.getValueAt(rowSelected, 4).toString());
					String categoryText = inventoryTable.getValueAt(rowSelected, 5).toString();
				    categoryCombo.setSelectedItem(categoryText);
				    
				} catch (NullPointerException e1) {
				}
			}
		}); // mouseclicklistener for update butoon to get the values of a row and put it in the textfield
		
		updateButton.addActionListener(new ActionListener() {
			String categoryText = categoryCombo.getSelectedItem().toString();
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(rootPane, "Update this row?", "Confirm addition",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == 0) { 
					checkName = inventoryTable.getValueAt(rowSelected, 1).toString();
					try {
						if (!isDuplicate("productID", nameTextField.getText().toString())){
								if(!nameTextField.getText().toString().equals(checkName)){
									JOptionPane.showMessageDialog(null, "Error Must Have Unique Name", "Duplicate",
											JOptionPane.ERROR_MESSAGE);	
								}else {
									if (nameTextField.getText().trim().equals("") || priceTextField.getText().trim().equals("")
											|| quantityTextField.getText().trim().equals("")) {
										JOptionPane.showMessageDialog(null, "Please fill up the required fields.", "Null",
												JOptionPane.ERROR_MESSAGE);
									}else if (!isInteger(quantityTextField.getText()) || !isInteger(priceTextField.getText()) ||
											priceTextField.getText().equals("0") || quantityTextField.getText().equals("0")) {
										JOptionPane.showMessageDialog(null,
												"Please input a valid number in quantity and price fields", "Input Error",
												JOptionPane.WARNING_MESSAGE);
									}else { 

										rowSelected = table.getSelectedRow();
										if (sorted) rowSelected = table.convertRowIndexToModel(rowSelected);

										String descriptionText;
										if (descriptionTextField.getText().trim().equals("")) descriptionText = "None";
										else descriptionText = descriptionTextField.getText();
										String categoryText = categoryCombo.getSelectedItem().toString();
										
										inventoryTable.setValueAt(nameTextField.getText(), rowSelected, 1);
										inventoryTable.setValueAt(priceTextField.getText(), rowSelected, 2);
										inventoryTable.setValueAt(descriptionText, rowSelected, 3);
										inventoryTable.setValueAt(quantityTextField.getText(), rowSelected, 4);
										inventoryTable.setValueAt(categoryText, rowSelected, 5);
									}	
								}}else {
									if (nameTextField.getText().trim().equals("") || priceTextField.getText().trim().equals("")
										|| quantityTextField.getText().trim().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill up the required fields.", "Null",
											JOptionPane.ERROR_MESSAGE);
									}else if (!isInteger(quantityTextField.getText()) || !isInteger(priceTextField.getText()) ||
											priceTextField.getText().equals("0") || quantityTextField.getText().equals("0")) {
										JOptionPane.showMessageDialog(null,
												"Please input a valid number in quantity and price fields", "Input Error",
												JOptionPane.WARNING_MESSAGE);
									}else { 

										rowSelected = table.getSelectedRow();
										if (sorted) rowSelected = table.convertRowIndexToModel(rowSelected);
										
										String descriptionText;
										if (descriptionTextField.getText().equals("")) descriptionText = "None";
										else descriptionText = descriptionTextField.getText();
										
										String categoryText = categoryCombo.getSelectedItem().toString();
										
										inventoryTable.setValueAt(nameTextField.getText(), rowSelected, 1);
										inventoryTable.setValueAt(priceTextField.getText(), rowSelected, 2);
										inventoryTable.setValueAt(descriptionText, rowSelected, 3);
										inventoryTable.setValueAt(quantityTextField.getText(), rowSelected, 4);
										inventoryTable.setValueAt(categoryText, rowSelected, 5);
										
									}
								}
							}catch(NullPointerException e1) {
									JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error",
											JOptionPane.INFORMATION_MESSAGE);
							}
					}}});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rowSelected >= 0) {
					int response = JOptionPane.showConfirmDialog(rootPane, "Sure to Delete this Row?",
							"Confirm delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response == 0) { // if confirmed then delete
						inventoryTable.removeRow(rowSelected);
					}
				} //if has no selected row show error dialog
				else {
					JOptionPane.showMessageDialog(null, "Select Row First.", "ClickError",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		

		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(rootPane, "Sure in exporting the file?", "Confirm action",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == 0) {
						String pathFileText = pathFileTextField.getText();
						BufferedWriter bufferedWriter;
						try {
							bufferedWriter = new BufferedWriter(new FileWriter(pathFileText));
							for (int i = 0; i < table.getColumnCount(); i++) {
								bufferedWriter.write(table.getColumnName(i));
								bufferedWriter.write(",");
							}
							// then check the rows and put it below the columns separated by ','
							for (int i = 0; i < table.getRowCount(); i++) {
								bufferedWriter.newLine();
								for (int j = 0; j < table.getColumnCount(); j++) {
									bufferedWriter.write((String) (table.getValueAt(i, j)));
									bufferedWriter.write(",");
									;
								}
							}
							JOptionPane.showMessageDialog(null, "Exporting Done.");
							
							
							// secure file
							bufferedWriter.close();

						} // if not file found show error
						catch (FileNotFoundException filenotfound) {
							JOptionPane.showMessageDialog(null, "File Path does not exist.");
						} catch (IOException ioexcept) {
							// catch block
							ioexcept.printStackTrace();
						}
					}
					;

				}
		});
		
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int startingRowCount = inventoryTable.getRowCount();
				int response = JOptionPane.showConfirmDialog(rootPane, "Sure in importing a file?", "Confirm Action",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == 0) {
					String filePath = pathFileTextField.getText();
					File file = new File(filePath);

					try {// create br object
						BufferedReader br = new BufferedReader(new FileReader(file));
						// get columns then the rows and put to array
						String firstLine = br.readLine().trim();
						String[] columnsName = firstLine.split(",");
						inventoryTable.setColumnIdentifiers(columnsName);
						Object[] tableLines = br.lines().toArray();

						// if has no duplicate then proceed
						for (int i = 0; i < tableLines.length; i++) {

							String line = tableLines[i].toString().trim();
							String[] dataRow = line.split(",");
							inventoryTable.addRow(dataRow);

						}
						br.close();

					} // if path file input does not exist then show error message
					catch (FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, "File does not exist.");
					} catch (IOException ex) {

					} catch (ArrayIndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(null, "Too many tries.");
					}

					finally {

						int finalRowCounter = inventoryTable.getRowCount();
						gotoloop: for (int k = 0; k < finalRowCounter; k++) {
							Object obj = inventoryTable.getValueAt(k, 0);
							Object obj1 = inventoryTable.getValueAt(k, 1);
							for (int j = 0; j < finalRowCounter; j++) {
								if (k == j)
									continue;
								else {
									if (obj.equals(inventoryTable.getValueAt(j, 0))
											|| obj1.equals(inventoryTable.getValueAt(j, 1))) {
										for (int n = finalRowCounter; n > startingRowCount; n--)
											((DefaultTableModel) table.getModel()).removeRow(n - 1);
										JOptionPane.showMessageDialog(null, "Sorry, Duplicate Values of ID or Name Found on File.");
										break gotoloop;
									}
								}
							}

						}
						table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(3).setPreferredWidth(200);
						table.setRowHeight(50);
					}

				}
			}

		});
		
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedColumn(); 
				try {
					List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
					sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
					sorter.setSortKeys(sortKeys);
					sorted = true;
				} catch (IllegalArgumentException e2) {
				}

			}
		});
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchString = searchTextField.getText();
				sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ searchString));

			}
		});
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchString = "";
				sorter.setRowFilter(RowFilter.regexFilter(searchString));
			}
		});

		signoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(rootPane, "Sure to log-out?", "Confirm logout",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == 0) {
					dispose();
					LoginWindow loginwindow = new LoginWindow();
					loginwindow.setVisible(true);
					dispose();
				}

			}
		});
	}
	@Override
	public boolean isInteger(String name) {
		return name.matches("[0-9]+");
	}


	@Override
	public boolean isDuplicate(String itemID, String itemName) {
		String localItemID = "";
		String localItemName = "";
		boolean isduplicate = true;
		int row = table.getRowCount();

		for (int i = 0; i < row; i++) {
			if (sorted) {
				localItemID = inventoryTable.getValueAt(table.convertRowIndexToModel(i), 0).toString();
				localItemName = inventoryTable.getValueAt(table.convertRowIndexToModel(i), 1).toString();}
			else {
				localItemID = inventoryTable.getValueAt(i, 0).toString();
				localItemName = inventoryTable.getValueAt(i, 1).toString();
			}
			
			if (itemID.equals(localItemID) || itemName.equals(localItemName)) {
				isduplicate = false;
				break;
			}
		}
		return isduplicate;
	}	
}
