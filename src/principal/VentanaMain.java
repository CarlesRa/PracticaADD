package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class VentanaMain extends JFrame {

	private JPanel contentPane;
	private static JTextField tfIdCliente;
	private static JTextField tfIdProducto;
	private static JTextField tfCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain frame = new VentanaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void insertarMysql() {
		String urlConexion = "jdbc:mysql://localhost:3306/empresa?serverTimeZone=UTC";
		String usser = "root";
		String passwd = "";
		Connection conexion;
		try {
			conexion = DriverManager.getConnection(urlConexion,usser,passwd);
			PreparedStatement statement = conexion.prepareStatement("INSERT INTO empresa.ventas values(" 
					+ "null, null,"
					+ Integer.parseInt(tfIdCliente.getText()) +","
					+ Integer.parseInt(tfIdProducto.getText()) +","
					+ Integer.parseInt(tfCantidad.getText()) +")");
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro insertado satisfactoriamente!!");
			conexion.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "HERROR AL INSERTAR");
			e.printStackTrace();
		}
	}
	
	public static void insertarSqlite() {

		String urlSqlite = "./empresa.db";
		try {
			Connection conexionToSqlite = DriverManager.getConnection("jdbc:sqlite:" + urlSqlite);
			PreparedStatement statement = conexionToSqlite.prepareStatement("INSERT INTO ventas values(" 
					+ "null, null,"
					+ Integer.parseInt(tfIdCliente.getText()) +","
					+ Integer.parseInt(tfIdProducto.getText()) +","
					+ Integer.parseInt(tfCantidad.getText()) +")");
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro insertado satisfactoriamente!!");
			conexionToSqlite.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "HERROR AL INSERTAR");
			e.printStackTrace();
		}
	}
	
	public static void insertarDerby() {
		String urlDerby = "jdbc:derby:/home/carles/Descargas/db-derby-10.15.1.3-bin/lib/empresa";
		try {
			Connection conexionToSqlite = DriverManager.getConnection(urlDerby);
			PreparedStatement statement = conexionToSqlite.prepareStatement("INSERT INTO ventas (id_cli, id_producto, cantidad) values(" 
					+ Integer.parseInt(tfIdCliente.getText()) +","
					+ Integer.parseInt(tfIdProducto.getText()) +","
					+ Integer.parseInt(tfCantidad.getText()) +")");
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro insertado satisfactoriamente!!");
			conexionToSqlite.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "HERROR AL INSERTAR");
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public VentanaMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JLabel lblBasesDeDatos = new JLabel("Base de Datos");
		
		JLabel lblDBSeleccionada = new JLabel("");
		
		JLabel lblIdcliente = new JLabel("ID_CLIENTE:");
		
		JLabel lblIdproducto = new JLabel("ID_PRODUCTO:");
		
		JLabel lblCantidad = new JLabel("CANTIDAD:");
		
		tfIdCliente = new JTextField();
		tfIdCliente.setColumns(10);
		
		tfIdProducto = new JTextField();
		tfIdProducto.setColumns(10);
		
		tfCantidad = new JTextField();
		tfCantidad.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				lblDBSeleccionada.setText((String)comboBox.getSelectedItem());
			}
		});
		
		comboBox.addItem("MySql");
		comboBox.addItem("Derby");
		comboBox.addItem("Sqlite");
		
		
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
		
		
		
		JLabel lblMensaje = new JLabel("");
		lblMensaje.setSize(20,20);
		
		JButton btnConsultar = new JButton("CONSULTAR");
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(250, Short.MAX_VALUE)
					.addComponent(lblDBSeleccionada)
					.addGap(190))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMensaje)
							.addGap(278)
							.addComponent(btnConsultar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBasesDeDatos)
							.addGap(43)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCantidad)
								.addComponent(lblIdproducto)
								.addComponent(lblIdcliente))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(tfCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnInsertar))
								.addComponent(tfIdProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfIdCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBasesDeDatos, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblDBSeleccionada)
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfIdCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdcliente))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdproducto)
						.addComponent(tfIdProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCantidad)
								.addComponent(tfCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInsertar))
							.addGap(45)
							.addComponent(lblMensaje)
							.addContainerGap(51, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnConsultar)
							.addGap(20))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
