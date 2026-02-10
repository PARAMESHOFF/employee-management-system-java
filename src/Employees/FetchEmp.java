package Employees;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class FetchEmp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FetchEmp frame = new FetchEmp();
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
	public FetchEmp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(17, 24, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEES ");
		lblNewLabel.setForeground(new Color(229, 231, 235));
		lblNewLabel.setBackground(new Color(229, 231, 235));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel.setBounds(128, 10, 133, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("VIEW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
				
				String userHome = System.getProperty("user.home");
				String url = "jdbc:sqlite:" + userHome + "/employees.db";

				String sql = "Select * from employee";
				
				
				try {
					Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection(url);
					
					stmt = con.createStatement();
					
					rs = stmt.executeQuery(sql);
					
					DefaultTableModel model = (DefaultTableModel) result.getModel();
					model.setRowCount(0);
					
					boolean hasData = false;
					
					while(rs.next()) {
						
						hasData = true;
						int id = rs.getInt(1);
						String name = rs.getString(2);
						String gender = rs.getString(3);
						int salary = rs.getInt(4);
						
						model.addRow(new Object[] {id, name, gender, salary});
						
					}
					
					if(!hasData) {
						JOptionPane.showMessageDialog(FetchEmp.this, "No Employees Found!");

					}
			}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				finally {
					try {
						if(con != null) con.close();
						if(rs != null) con.close();
						if(stmt != null) stmt.close();
					}
					catch (Exception x) {
						x.printStackTrace();
					}
				}
				
				
				
			}
		});
		
		btnNewButton.setBounds(325, 116, 101, 20);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(new Color(59, 130, 246));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnNewButton.setBackground(new Color(37, 99, 235));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnNewButton.setBackground(new Color(59, 130, 246));
		    }
		});
		
		result = new JTable();
		result.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		result.setSurrendersFocusOnKeystroke(true);
		result.setForeground(new Color(255, 255, 255));
		result.setBackground(new Color(31, 41, 55));
		JScrollPane scrollPane = new JScrollPane(result);
		scrollPane.setBounds(35, 48, 283, 205);
		contentPane.add(scrollPane);


		result.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NAME", "GENDER", "SALARY"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		result.getTableHeader().setBackground(new Color(17, 24, 39));
		result.getTableHeader().setForeground(Color.WHITE);
		result.getTableHeader().setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		result.setFillsViewportHeight(true);

		result.getColumnModel().getColumn(0).setPreferredWidth(58);
		result.setColumnSelectionAllowed(true);
		result.setCellSelectionEnabled(true);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(329, 164, 97, 21);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setBackground(new Color(59, 130, 246));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnNewButton_1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnNewButton_1.setBackground(new Color(37, 99, 235));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnNewButton_1.setBackground(new Color(59, 130, 246));
		    }
		});

	}
}
