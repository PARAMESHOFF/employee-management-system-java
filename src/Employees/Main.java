package Employees;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		DatabaseUtil.initializeDatabase();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(17, 24, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE MANAGEMENT");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(229, 231, 235));
		lblNewLabel.setBounds(122, 10, 194, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Search Emp");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RetriveEmployee retrive = new RetriveEmployee();
				retrive.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(243, 67, 126, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Add Emp");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertEmployee insert = new InsertEmployee();
		        insert.setVisible(true);
		        dispose();
			}
		});
		btnNewButton_2.setBounds(52, 140, 126, 32);
		contentPane.add(btnNewButton_2);
		
		
		JButton btnNewButton_3 = new JButton("Delete Emp");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteEmp delete = new DeleteEmp();
		        delete.setVisible(true);
		        dispose();
			}
		});
		btnNewButton_3.setBounds(243, 140, 126, 32);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Update Emp");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateEmp update = new UpdateEmp();
		        update.setVisible(true);
		        dispose();
			}
		});
		btnNewButton_4.setBounds(148, 205, 126, 32);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton = new JButton("View Emp");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FetchEmp fetch = new FetchEmp();
		        fetch.setVisible(true);
		        dispose();
			}
		});
		btnNewButton.setBounds(52, 67, 126, 32);
		contentPane.add(btnNewButton);

	}
}
