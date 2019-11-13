package principal;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaScrollable extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfRow;
	private JTable table;
	private ResultSet result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaScrollable frame = new VentanaScrollable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static ResultSet getResultSet(String consulta) {
		
		 String userName = "root";
		 String contraseña = "";
		 String urlConexion = "jdbc:mysql://localhost:3306/empresa?serverTimeZone=UTC";
		 Connection cn;
		 ResultSet rs = null;
		try {
			cn = DriverManager.getConnection(urlConexion,userName, contraseña);
			Statement st = cn.createStatement();
			rs = st.executeQuery(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return rs;
	}

	/**
	 * Create the frame.
	 */
	public VentanaScrollable() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblNombre = new JLabel("Descripción");
		
		JLabel lblPvp = new JLabel("PVP");
		
		JButton btFirst = new JButton("<<");
		
		JLabel lblFoto = new JLabel("");

		btFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					result.first();
					lblId.setText(result.getString("id"));
					if (result.getBinaryStream("foto") != null) {
						BufferedImage im = ImageIO.read(result.getBinaryStream("foto"));
						Image imagen = im.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
						lblFoto.setIcon(new ImageIcon(imagen));
					}
					lblNombre.setText(result.getString("descripcion"));
					lblPvp.setText(result.getString("pvp"));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btPrevius = new JButton("<");
		table = new JTable();
		scrollPane.setViewportView(table);
		
		String[] campos = {"ID","Descripción","PVP"};
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(campos);
		table.setModel(modelo);
		
		result = getResultSet("select * from productos");
		try {
			while (result.next()) {
				String[] datos = {result.getString("id"),result.getString("descripcion"),result.getString("pvp")};
				modelo.addRow(datos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btPrevius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!result.isFirst()) {
						result.previous();
						if (result.getBinaryStream("foto") != null) {
							BufferedImage im = ImageIO.read(result.getBinaryStream("foto"));
							Image imagen = im.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
							lblFoto.setIcon(new ImageIcon(imagen));
						}
						lblNombre.setText(result.getString("descripcion"));
						lblPvp.setText(result.getString("pvp"));
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		JButton btNext = new JButton(">");
		btNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if (!result.isLast()) {
						result.next();
						if (result.getBinaryStream("foto") != null) {
							BufferedImage im = ImageIO.read(result.getBinaryStream("foto"));
							Image imagen = im.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
							lblFoto.setIcon(new ImageIcon(imagen));
						}
						lblNombre.setText(result.getString("descripcion"));
						lblPvp.setText(result.getString("pvp"));
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btLast = new JButton(">>");
		btLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					result.last();
					lblId.setText(result.getString("id"));
					BufferedImage im = ImageIO.read(result.getBinaryStream("foto"));
					Image imagen = im.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
					lblFoto.setIcon(new ImageIcon(imagen));
					lblNombre.setText(result.getString("descripcion"));
					lblPvp.setText(result.getString("pvp"));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		tfRow = new JTextField();
		tfRow.setColumns(10);
		Border border = LineBorder.createGrayLineBorder();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblFoto, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(131)
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(106)
							.addComponent(lblPvp, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btFirst)
							.addGap(24)
							.addComponent(btPrevius, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(tfRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btNext, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(btLast)))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFoto, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId)
								.addComponent(lblNombre)
								.addComponent(lblPvp))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btFirst, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(btPrevius, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfRow, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(btNext, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(btLast, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
					.addGap(22))
		);	
		contentPane.setLayout(gl_contentPane);
	}
}
