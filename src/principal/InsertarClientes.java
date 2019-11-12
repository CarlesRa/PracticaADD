package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertarClientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField tfId;
	private static JTextField tfNombre;
	private static JTextField tfDireccion;
	private static JTextField tfPoblacion;
	private static JTextField tfTelefono;
	private static JTextField tfNif;
	private static JLabel lblDBSeleccionada = new JLabel("");
	/**
	 * Launch the application.
	 */
	
	public static void insertarMysql() {
		String urlConexion = "jdbc:mysql://localhost:3306/empresa?serverTimeZone=UTC";
		String usser = "root";
		String passwd = "";
		Connection conexion;
		try {
			conexion = DriverManager.getConnection(urlConexion,usser,passwd);
			String sqlNuevoProducto = "Insert into clientes values(?,?,?,?,?,?)";
			PreparedStatement ps;
			ps = conexion.prepareStatement(sqlNuevoProducto);
			ps.setInt(1,Integer.parseInt(tfId.getText()));
			ps.setString(2,tfNombre.getText());
			ps.setString(3,tfDireccion.getText());
			ps.setString(4,tfPoblacion.getText());
			ps.setString(5, tfTelefono.getText());
			ps.setString(6, tfNif.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro insertado satisfactoriamente en "+ lblDBSeleccionada.getText()+"!!");
			conexion.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "HERROR AL INSERTAR");
			e.printStackTrace();
		}
	}
	
	public static void insertarSqlite() {

		String urlSqlite = "./empresa.db";
		try {
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + urlSqlite);
			String sqlNuevoProducto = "Insert into clientes values(?,?,?,?,?,?)";
			PreparedStatement ps;
			ps = conexion.prepareStatement(sqlNuevoProducto);
			ps.setInt(1,Integer.parseInt(tfId.getText()));
			ps.setString(2,tfNombre.getText());
			ps.setString(3,tfDireccion.getText());
			ps.setString(4,tfPoblacion.getText());
			ps.setString(5, tfTelefono.getText());
			ps.setString(6, tfNif.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro insertado satisfactoriamente en "+ lblDBSeleccionada.getText()+"!!");
			conexion.close();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "HERROR AL INSERTAR");
			e.printStackTrace();
		}
	}
	
	public static void insertarDerby() {
		String urlDerby = "jdbc:derby:/home/carles/Descargas/db-derby-10.15.1.3-bin/lib/empresa";
		try {
			Connection conexion = DriverManager.getConnection(urlDerby);
			conexion = DriverManager.getConnection(urlDerby);
			String sqlNuevoProducto = "Insert into clientes values(?,?,?,?,?,?)";
			PreparedStatement ps;
			ps = conexion.prepareStatement(sqlNuevoProducto);
			ps.setInt(1,Integer.parseInt(tfId.getText()));
			ps.setString(2,tfNombre.getText());
			ps.setString(3,tfDireccion.getText());
			ps.setString(4,tfPoblacion.getText());
			ps.setString(5, tfTelefono.getText());
			ps.setString(6, tfNif.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro insertado satisfactoriamente en "+ lblDBSeleccionada.getText()+"!!");
			conexion.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "HERROR AL INSERTAR");
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public InsertarClientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblInsertarClientes = new JLabel("INSERTAR CLIENTES");
		
		JLabel lblId = new JLabel("ID: ");
		
		JLabel lblNombre = new JLabel("Nombre: ");
		
		JLabel lblDireccion = new JLabel("Direccion: ");
		
		JLabel lblPoblacin = new JLabel("Población: ");
		
		JLabel lblTelefono = new JLabel("Telefono:");
		
		JLabel lblNif = new JLabel("NIF:");
		
		tfId = new JTextField();
		tfId.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		
		tfPoblacion = new JTextField();
		tfPoblacion.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		
		tfNif = new JTextField();
		tfNif.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				lblDBSeleccionada.setText((String)comboBox.getSelectedItem());
			}
		});
		
		comboBox.addItem("MySql");
		comboBox.addItem("Derby");
		comboBox.addItem("Sqlite");
		
		JLabel lblDatabase = new JLabel("Database?");
		
		JButton btnInsertar = new JButton("INSERTAR");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (lblDBSeleccionada.getText()) {
				case "MySql":
					insertarMysql();
					break;
				case "Derby":
					insertarDerby();
					break;
				case "Sqlite":
					insertarSqlite();
					break;

				default:
					break;
				}
			}
		});
		
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblInsertarClientes)
											.addGap(67))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDatabase)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(tfId, 349, 349, 349))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfNombre, 287, 287, 287)
							.addGap(88))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDireccion)
								.addComponent(lblPoblacin))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(tfPoblacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(217, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(tfDireccion, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
									.addGap(77))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTelefono)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfTelefono, 299, 299, 299)
							.addContainerGap(54, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDBSeleccionada)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnInsertar))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNif)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(tfNif, 335, 335, 335)))
							.addContainerGap(48, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnHome)
							.addContainerGap(354, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblInsertarClientes)
							.addGap(2)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDatabase))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDireccion))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPoblacin)
						.addComponent(tfPoblacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono)
						.addComponent(tfTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNif)
						.addComponent(tfNif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInsertar)
						.addComponent(lblDBSeleccionada))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnHome)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
