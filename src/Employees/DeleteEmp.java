package Employees;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class DeleteEmp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEmp frame = new DeleteEmp();
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
	public DeleteEmp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(17, 24, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DELETE EMPLOYEE");
		lblNewLabel.setBackground(new Color(229, 231, 235));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNewLabel.setBounds(153, 10, 127, 27);
		lblNewLabel.setForeground(new Color(229, 231, 235));

		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter ID  : ");
		lblNewLabel_1.setForeground(new Color(209, 213, 219));

		lblNewLabel_1.setBounds(51, 79, 80, 18);
		contentPane.add(lblNewLabel_1);
		
		JTextField id = new JTextField();
		id.setForeground(new Color(255, 255, 255));
		id.setBackground(new Color(55, 65, 81));
		id.setCaretColor(Color.WHITE);
//		idField.setForeground(Color.WHITE);
		id.setBorder(BorderFactory.createLineBorder(new Color(75, 85, 99)));
		id.setBounds(119, 75, 102, 27);
		contentPane.add(id);
		id.setColumns(10);
		
		JButton btnNewButton = new JButton("DELETE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(id.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(DeleteEmp.this, "Please enter ID");
					id.requestFocus();
					return;
					
				}
				int inid = 0;
				try {
				 inid = Integer.parseInt(id.getText());
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(DeleteEmp.this, "ID must be a number");
					id.setText("");
					id.requestFocus();
					return;
					
				}
				

				Connection con = null;
				PreparedStatement ps = null;
				
				
				
				String userHome = System.getProperty("user.home");
				String url = "jdbc:sqlite:" + userHome + "/employees.db";

				
				String sql = "delete from employee where id = ?";
				
				try {
					Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection(url);
					
					ps = con.prepareStatement(sql);
					
					ps.setInt(1,inid);
					
					int rs = ps.executeUpdate();
					if(rs > 0) {
						JOptionPane.showMessageDialog(DeleteEmp.this, "Employee Deleted Successfully!");
						
						id.setText("");
						
						id.requestFocus();
					
					}
					else {
					 JOptionPane.showMessageDialog(DeleteEmp.this, "No Employee Found in this ID : " + inid);
					 	id.setText("");
						
						id.requestFocus();
					}
					
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				finally {
					try {
						if(con != null) con.close();
						if(ps != null) ps.close();
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton.setBackground(new Color(220, 38, 38));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(51, 158, 91, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(189, 156, 91, 29);
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
