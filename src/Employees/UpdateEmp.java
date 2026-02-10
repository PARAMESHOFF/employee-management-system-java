package Employees;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class UpdateEmp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;
	private JTextField nname;
	private JTextField ngender;
	private JTextField nsalary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmp frame = new UpdateEmp();
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
	public UpdateEmp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(17, 24, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UPDATE EMPLOYEE");
		lblNewLabel.setForeground(new Color(229, 231, 235));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNewLabel.setBounds(161, 10, 140, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter ID : ");
		lblNewLabel_1.setForeground(new Color(209, 213, 219));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(100, 51, 56, 20);
		contentPane.add(lblNewLabel_1);
		
		id = new JTextField();
		id.setBounds(178, 45, 108, 31);
		contentPane.add(id);
		id.setColumns(10);
		id.setBackground(new Color(55, 65, 81));
		id.setForeground(Color.WHITE);
		id.setCaretColor(Color.WHITE);
		id.setBorder(BorderFactory.createLineBorder(new Color(75, 85, 99)));
		id.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Name    :");
		lblNewLabel_2.setForeground(new Color(209, 213, 219));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(100, 99, 56, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gender  :");
		lblNewLabel_3.setForeground(new Color(209, 213, 219));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(100, 140, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Salary    :");
		lblNewLabel_4.setForeground(new Color(209, 213, 219));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(100, 182, 56, 15);
		contentPane.add(lblNewLabel_4);

		
		nname = new JTextField();
		nname.setBounds(178, 91, 108, 31);
		contentPane.add(nname);
		nname.setColumns(10);
		nname.setBackground(new Color(55, 65, 81));
		nname.setForeground(Color.WHITE);
		nname.setCaretColor(Color.WHITE);
		nname.setBorder(BorderFactory.createLineBorder(new Color(75, 85, 99)));
		nname.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));

		
		ngender = new JTextField();
		ngender.setBounds(178, 132, 108, 31);
		contentPane.add(ngender);
		ngender.setColumns(10);
		ngender.setBackground(new Color(55, 65, 81));
		ngender.setForeground(Color.WHITE);
		ngender.setCaretColor(Color.WHITE);
		ngender.setBorder(BorderFactory.createLineBorder(new Color(75, 85, 99)));
		ngender.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		
		nsalary = new JTextField();
		nsalary.setBounds(178, 173, 108, 31);
		contentPane.add(nsalary);
		nsalary.setColumns(10);
		nsalary.setBackground(new Color(55, 65, 81));
		nsalary.setForeground(Color.WHITE);
		nsalary.setCaretColor(Color.WHITE);
		nsalary.setBorder(BorderFactory.createLineBorder(new Color(75, 85, 99)));
		nsalary.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));


		
		
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(UpdateEmp.this, "Please enter ID");
		            id.requestFocus();
		            return;
		            
		        }

		        int inid;
		        try {
		            inid = Integer.parseInt(id.getText().trim());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(UpdateEmp.this, "ID must be a number");
		            return;
		        }

		        String nameText = nname.getText().trim();
		        String genderText = ngender.getText().trim();
		        String salaryText = nsalary.getText().trim();

		        // ---- Check if at least one field is filled ----
		        if (nameText.isEmpty() && genderText.isEmpty() && salaryText.isEmpty()) {
		            JOptionPane.showMessageDialog(UpdateEmp.this, 
		                    "Enter at least one field to update.");
		            return;
		        }

		        Connection con = null;
		        PreparedStatement ps = null;

		        try {
		            String userHome = System.getProperty("user.home");
		            String url = "jdbc:sqlite:" + userHome + "/employees.db";

		            Class.forName("org.sqlite.JDBC");
		            con = DriverManager.getConnection(url);

		            // ---- Build dynamic SQL ----
		            StringBuilder sql = new StringBuilder("UPDATE employee SET ");
		            boolean first = true;

		            if (!nameText.isEmpty()) {
		                sql.append("name=?");
		                first = false;
		            }

		            if (!genderText.isEmpty()) {
		                if (!first) sql.append(", ");
		                sql.append("gender=?");
		                first = false;
		            }

		            if (!salaryText.isEmpty()) {
		                if (!first) sql.append(", ");
		                sql.append("salary=?");
		            }

		            sql.append(" WHERE id=?");

		            ps = con.prepareStatement(sql.toString());

		            int index = 1;

		            if (!nameText.isEmpty()) {
		                ps.setString(index++, nameText);
		            }

		            if (!genderText.isEmpty()) {
		                ps.setString(index++, genderText);
		            }

		            if (!salaryText.isEmpty()) {
		                try {
		                    ps.setInt(index++, Integer.parseInt(salaryText));
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(UpdateEmp.this, 
		                            "Salary must be a number");
		                    return;
		                }
		            }

		            ps.setInt(index, inid);

		            int rows = ps.executeUpdate();

		            if (rows > 0) {
		                JOptionPane.showMessageDialog(UpdateEmp.this, 
		                        "Employee updated successfully!");
		                
		                id.setText("");
		                nname.setText("");
		                ngender.setText("");
		                nsalary.setText("");
		            } else {
		                JOptionPane.showMessageDialog(UpdateEmp.this, 
		                        "No employee found with ID: " + inid);
		            }

		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(UpdateEmp.this, ex.getMessage());
		            ex.printStackTrace();
		        } finally {
		            try {
		                if (ps != null) ps.close();
		                if (con != null) con.close();
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		btnNewButton.setBounds(130, 227, 90, 26);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(new Color(59, 130, 246));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		
		JButton btnNewButton_3 = new JButton("BACK");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
		        main.setVisible(true);
		        dispose();
			}
		});
		btnNewButton_3.setBounds(253, 227, 90, 26);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.setBackground(new Color(59, 130, 246));
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnNewButton_3.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnNewButton_3.setBackground(new Color(37, 99, 235));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnNewButton_3.setBackground(new Color(59, 130, 246));
		    }
		});


		
		

	}

}
