package com.brick.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.brick.database.DatabaseHelper;
import com.mysql.jdbc.ResultSetMetaData;

public class EmployeeRecords extends JPanel implements TableModelListener {
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblEmployeeRecords = new JLabel("Employee Records");
	private final JScrollPane scrollPane = new JScrollPane();
	JTable table;
	DatabaseHelper databaseHelper = new DatabaseHelper();
	Vector row;
	Vector columnNames = new Vector();
    Vector data = new Vector();
    DefaultTableModel test;
    String numToken = "[\\p{Digit}]+";
    boolean isTableInit=false;
    EmployeeRecords record;
    EmployeeAdvance employeeAdvance;
    EmployeeAttendance attendance = new EmployeeAttendance();


	/**
	 * Create the panel.
	 */
	public EmployeeRecords() {

		initGUI();
	}
	public void populateTable(){
		
		try {
			ResultSet resultSet = databaseHelper.fetchEmployee();
			ResultSetMetaData metaData = (ResultSetMetaData) resultSet
					.getMetaData();
			int columns = metaData.getColumnCount();
			
			
			for (int i = 1; i <= columns; i++) {
				columnNames.addElement(metaData.getColumnLabel(i));
				}
			data.removeAllElements();
			while (resultSet.next()) {
				row = new Vector(columns);
				for (int i = 1; i <= columns; i++) {
					row.addElement(resultSet.getObject(i));
				}

				data.addElement(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		
		
		if(!isTableInit){
			test = new DefaultTableModel(data, columnNames) {
				public boolean isCellEditable(int row, int column) {
					// only columns 0 and 1 are editable
					return column == 1 || column == 3 || column == 4 || column ==5 || column ==6 || column ==7;
				}
			};
			test.addTableModelListener(this);
			table = new JTable(test){
				  public Component prepareRenderer
				  (TableCellRenderer renderer,int Index_row, int Index_col) {
				  Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
				  //even index, selected or not selected
				  if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
				  comp.setBackground(Color.lightGray);
				  } 
				  else if (isRowSelected(Index_row))
				  {
					  comp.setBackground(Color.BLUE);
				  }
				  else {
				  comp.setBackground(Color.white);
				  }
				  return comp;
				  }
				  };
			//table.getTableHeader().setBackground(Color.blue);
			//table.setBorder(BorderFactory.createLineBorder(Color.black,4));
			table.getColumnModel().getColumn(0).setMaxWidth(0);
		    table.getColumnModel().getColumn(0).setMinWidth(0);
		    table.getColumnModel().getColumn(0).setPreferredWidth(0);
			isTableInit = true;
			table.setRowHeight(20);
			//Dimension dim = new Dimension(20,1);
			//table.setIntercellSpacing(new Dimension(dim));
			
		}

		int count = table.getRowCount();
		System.err.println(count);
		int count1 = scrollPane.countComponents();
		System.err.println(count1);
		table.revalidate();
		scrollPane.setViewportView(table);
		table.isCellEditable(1, 1);
		table.getColumn("Remove").setCellRenderer(new ButtonRenderer());
		table.getColumn("Remove").setCellEditor(new ButtonEditor(new JCheckBox(),-1,-1));
		

		
	}

	private void initGUI() {
		System.err.println("step 3");
		setLayout(new BorderLayout(0, 0));
		
		add(panel, BorderLayout.CENTER);
		panel_1.setBackground(Color.GRAY);
		
		add(panel_1, BorderLayout.NORTH);
		lblEmployeeRecords.setForeground(new Color(0, 191, 255));
		lblEmployeeRecords.setFont(new Font("Dialog", Font.BOLD, 16));
		
		panel_1.add(lblEmployeeRecords);
		
		add(scrollPane, BorderLayout.CENTER);
		populateTable();

	}
public void tableChanged(TableModelEvent e) {
		
		if (e==null)
		{
			table.getColumn("Remove").setCellEditor(new ButtonEditor(new JCheckBox(),-1,-1));
			return;
		}
		System.err.println("step 4");
		int row = e.getFirstRow();
		int column = e.getColumn();
			
		int id = Integer.valueOf( table.getValueAt(row, 0).toString());
		String name = table.getValueAt(row, 1).toString();
		String paddress = table.getValueAt(row, 3).toString();
		String taddress = table.getValueAt(row, 4).toString();
		String salary = table.getValueAt(row, 5).toString();
		String phone = table.getValueAt(row, 6).toString();
		String type = table.getValueAt(row, 2).toString();
		
		
		if (!salary.matches(numToken)) {
			JOptionPane.showMessageDialog(null, "Must be integer", "Error",
					JOptionPane.DEFAULT_OPTION);
			//int original = databaseHelper.fetchbrickamount(id);
			int original = databaseHelper.fetchsalary(id);
			table.setValueAt(original, row, column);

			System.err.println("gooooo");
			return;
		}
		if (!phone.matches(numToken)) {
			JOptionPane.showMessageDialog(null, "Must be integer", "Error",
					JOptionPane.DEFAULT_OPTION);
			//int original = databaseHelper.fetchbrickamount(id);
			int original = databaseHelper.fetchphone(id);
			table.setValueAt(original, row, column);

			System.err.println("gooooo");
			return;
		}
		
		if (column==7)
		{

			databaseHelper.updateemployee(id);
			table.getModel().removeTableModelListener(this);
			populateTable();
			employeeAdvance.populateEmployeeAdvance();
			attendance.populateAttendance();
			table.getModel().addTableModelListener(this);
		}
		int	sa = Integer.parseInt(salary);
		int ph = Integer.parseInt(phone);
		databaseHelper.updateemployee(id, name, type, paddress,taddress,sa,ph);

		}

		void setrecord(EmployeeRecords records)
		{
			this.record = records;
		}
		
		public void setEmployeeAdvance(EmployeeAdvance employeeAdvances){
			
			this.employeeAdvance = employeeAdvances;
		}
		public void setattendance(EmployeeAttendance attendances)
		{
			this.attendance = attendances;
		}


}
