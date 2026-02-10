package Employees;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class RetriveEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;
	private JTextArea result;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetriveEmployee frame = new RetriveEmployee();
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
	public RetriveEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(17, 24, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Get Employee Details");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(229, 231, 235));
		lblNewLabel.setBounds(114, 10, 226, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the ID :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(44, 67, 97, 29);
		contentPane.add(lblNewLabel_1);
		
		id = new JTextField();
		id.setForeground(new Color(255, 255, 255));
		id.setBackground(new Color(55, 65, 81));
		id.setBounds(135, 68, 105, 29);
		contentPane.add(id);
		id.setColumns(10);
		
		JTextArea result = new JTextArea();
		result.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 15));
		result.setForeground(new Color(229, 231, 235));
		result.setBackground(new Color(31, 41, 55));
		result.setBounds(44, 127, 215, 113);
		contentPane.add(result);

		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.setForeground(new Color(229, 231, 235));
		btnNewButton.setBackground(new Color(17, 24, 39));
		btnNewButton.setBounds(305, 156, 91, 27);
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				int inid = Integer.parseInt(id.getText());
				if(id.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(RetriveEmployee.this, "Please enter ID");
					id.requestFocus();
					return;
					
				}
				int inid = 0;
				try {
				 inid = Integer.parseInt(id.getText());
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(RetriveEmployee.this, "ID must be a number");
					id.setText("");
					id.requestFocus();
					return;
					
				}
				
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String userHome = System.getProperty("user.home");
				String url = "jdbc:sqlite:" + userHome + "/employees.db";

				
				String sql = "select * from employee where id = ?";
				
				try {
					Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection(url);
					ps = con.prepareStatement(sql);
					ps.setInt(1, inid);
//				
					rs = ps.executeQuery();		
					
					if(rs.next()) {
//						int id = rs.getInt(1);
						String name = rs.getString(2);
						String gender = rs.getString(3);
						int salary = rs.getInt(4);
						
						result.setText(
								"ID: " + inid + "\n"+
								"Name:" + name + "\n" +
								"Gender:" + gender + "\n" +
								"Salary:" + salary
								);
						
					}
					else {
						JOptionPane.showMessageDialog(RetriveEmployee.this, "No Employee found with ID : " + inid);
					}
					
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				finally {
					try {
						if(con != null) con.close();
						if(ps != null) ps.close();
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton.setBounds(274, 67, 97, 25);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(305, 156, 91, 27);
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
