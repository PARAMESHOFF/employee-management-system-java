package Employees;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class InsertEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField gender;
	private JTextField salary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertEmployee frame = new InsertEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void styleTextField(JTextField field) {
	    field.setBackground(new Color(55, 65, 81));
	    field.setForeground(Color.WHITE);
	    field.setCaretColor(Color.WHITE);
	    field.setBorder(BorderFactory.createLineBorder(new Color(75, 85, 99)));
	}


	/**
	 * Create the frame.
	 */
	public InsertEmployee() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setBackground(new Color(17, 24, 39));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ===== Title =====
		JLabel title = new JLabel("ADD Employee");
		title.setBounds(150, 20, 200, 30);
		title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		title.setForeground(new Color(229, 231, 235));
		contentPane.add(title);

		// ===== Labels =====
		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(80, 80, 80, 25);
		lblId.setForeground(new Color(209, 213, 219));
		contentPane.add(lblId);

		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(80, 120, 80, 25);
		lblName.setForeground(new Color(209, 213, 219));
		contentPane.add(lblName);

		JLabel lblGender = new JLabel("Gender :");
		lblGender.setBounds(80, 160, 80, 25);
		lblGender.setForeground(new Color(209, 213, 219));
		contentPane.add(lblGender);

		JLabel lblSalary = new JLabel("Salary :");
		lblSalary.setBounds(80, 200, 80, 25);
		lblSalary.setForeground(new Color(209, 213, 219));
		contentPane.add(lblSalary);

		// ===== Text Fields Styling =====
		id = new JTextField();
		id.setBounds(160, 80, 180, 25);
		styleTextField(id);
		contentPane.add(id);

		name = new JTextField();
		name.setBounds(160, 120, 180, 25);
		styleTextField(name);
		contentPane.add(name);

		gender = new JTextField();
		gender.setBounds(160, 160, 180, 25);
		styleTextField(gender);
		contentPane.add(gender);

		salary = new JTextField();
		salary.setBounds(160, 200, 180, 25);
		styleTextField(salary);
		contentPane.add(salary);

		// ===== ADD Button =====
		JButton addButton = new JButton("ADD");
		addButton.setBounds(107, 250, 120, 35);
		addButton.setBackground(new Color(59, 130, 246));
		addButton.setForeground(Color.WHITE);
		addButton.setFocusPainted(false);
		addButton.setBorderPainted(false);
		addButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		contentPane.add(addButton);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(277, 250, 111, 35);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(new Color(59, 130, 246));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));

		// Action Listener
		addButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		    	if (id.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(InsertEmployee.this, "Please enter ID");
		            id.requestFocus();
		            return;
		            
		        }

		        int inid;
		        try {
		            inid = Integer.parseInt(id.getText().trim());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(InsertEmployee.this, "ID must be a number");
		            id.setText("");
		            return;
		        }
//		        String inname = name.getText();
		        if (name.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(InsertEmployee.this, "Please enter Name");
		            name.requestFocus();
		            return;
		            
		        }
		        String inname = name.getText().trim();

		        if (!inname.isEmpty() && !inname.matches("[a-zA-Z ]+")) {
		            JOptionPane.showMessageDialog(InsertEmployee.this, 
		                    "Name should contain letters only");
		            name.setText("");
		            name.requestFocus();
		            return;
		        }

		        
		        if (gender.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(InsertEmployee.this, "Please enter Gender");
		            gender.requestFocus();
		            return;
		            
		        }
		        String ingender = gender.getText().trim();
		        
		        if(!ingender.isEmpty() && !ingender.matches("[a-zA-Z]+")) {
		        	JOptionPane.showMessageDialog(InsertEmployee.this, "Gender Should contain Letters only ");
		        	gender.setText("");
		        	gender.requestFocus();
		        	return;
		        }
		        
		        if (salary.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(InsertEmployee.this, "Please enter Salary");
		            salary.requestFocus();
		            return;
		            
		        }
		        String salaryText = salary.getText().trim();
		        if (!salaryText.isEmpty()) {
		            try {
		                Integer.parseInt(salaryText);
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(InsertEmployee.this,
		                        "Salary must be a number");
		                salary.setText("");
		                salary.requestFocus();
		                return;
		            }
		        }
		        int insalary = Integer.parseInt(salaryText);

		        Connection con = null;
		        PreparedStatement ps = null;

		        String userHome = System.getProperty("user.home");
		        String url = "jdbc:sqlite:" + userHome + "/employees.db";

		       
		        String sql = "INSERT INTO employee (id, name, gender, salary) VALUES(?,?,?,?)";

		        try {
		        	Class.forName("org.sqlite.JDBC");
		        	con = DriverManager.getConnection(url);
		        	
		        	// create table if not exists
		        	
		        	String createtable = "create table if not exists employee ("
		        			
		        			+ "id INTEGER PRIMARY KEY ,"
		        			+ "name TEXT, "
		        			+ "gender TEXT,"
		        			+ "salary INTEGER)";
		        	
		        	PreparedStatement createStmt = con.prepareStatement(createtable);
		        	createStmt.execute();
		        	createStmt.close();
		        	
		        	
		            ps = con.prepareStatement(sql);

		            ps.setInt(1, inid);
		            ps.setString(2, inname);
		            ps.setString(3, ingender);
		            ps.setInt(4, insalary);

		            int nora = ps.executeUpdate();

		            if (nora > 0) {
		                JOptionPane.showMessageDialog(InsertEmployee.this, "Employee Added Successfully!");
		                
		                id.setText("");
		                name.setText("");
		                gender.setText("");
		                salary.setText("");
		                
		                id.requestFocus();
		            }

		        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
		        	JOptionPane.showMessageDialog(InsertEmployee.this, "EMployee ID already exists. Please use a different ID.");
		        	id.setText("");
	  
	                
	                id.requestFocus();
		        }
		        
		        
		        catch (Exception ex) {
		        	JOptionPane.showMessageDialog(InsertEmployee.this, ex.getMessage());
		            ex.printStackTrace();
	  
	                
	                id.requestFocus();
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

		// Hover Effect
		addButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        addButton.setBackground(new Color(37, 99, 235));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        addButton.setBackground(new Color(59, 130, 246));
		    }
		});
}
}