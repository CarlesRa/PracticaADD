package principal;


import java.awt.EventQueue;
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
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class InsertarProductos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField tfNombre;
	private static JTextField tfStockActual;
	private static JTextField tfStocMinimo;
	private static JTextField tfPvp;
	private static JTextField tfId;
	private static JLabel lblDBSeleccionada = new JLabel("");
	
	public static void insertarMysql() {
		String urlConexion = "jdbc:mysql://localhost:3306/empresa?serverTimeZone=UTC";
		String usser = "root";
		String passwd = "";
		Connection conexion;
		try {
			conexion = DriverManager.getConnection(urlConexion,usser,passwd);
			String sqlNuevoProducto = "Insert into productos values(?,?,?,?,?)";
			PreparedStatement ps;
			ps = conexion.prepareStatement(sqlNuevoProducto);
			ps.setInt(1,Integer.parseInt(tfId.getText()));
			ps.setString(2,tfNombre.getText());
			ps.setInt(3, Integer.parseInt(tfStockActual.getText()));
			ps.setInt(4, Integer.parseInt(tfStocMinimo.getText()));
			ps.setDouble(5, Double.parseDouble(tfPvp.getText()));
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
			String sqlNuevoProducto = "Insert into productos values(?,?,?,?,?)";
			PreparedStatement ps;
			ps = conexion.prepareStatement(sqlNuevoProducto);
			ps.setInt(1,Integer.parseInt(tfId.getText()));
			ps.setString(2,tfNombre.getText());
			ps.setInt(3, Integer.parseInt(tfStockActual.getText()));
			ps.setInt(4, Integer.parseInt(tfStocMinimo.getText()));
			ps.setDouble(5, Double.parseDouble(tfPvp.getText()));
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
			String sqlNuevoProducto = "Insert into productos values(?,?,?,?,?)";
			PreparedStatement ps;
			ps = conexion.prepareStatement(sqlNuevoProducto);
			ps.setInt(1,Integer.parseInt(tfId.getText()));
			ps.setString(2,tfNombre.getText());
			ps.setInt(3, Integer.parseInt(tfStockActual.getText()));
			ps.setInt(4, Integer.parseInt(tfStocMinimo.getText()));
			ps.setDouble(5, Double.parseDouble(tfPvp.getText()));
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
	public InsertarProductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDescripcion = new JLabel("Nombre");
		
		JLabel lblStockActual = new JLabel("Stock actual:");
		
		JLabel lblStockMnimo = new JLabel("Stock mínimo: ");
		
		JLabel lblPvp = new JLabel("PVP: ");
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		tfStockActual = new JTextField();
		tfStockActual.setColumns(10);
		
		tfStocMinimo = new JTextField();
		tfStocMinimo.setColumns(10);
		
		tfPvp = new JTextField();
		tfPvp.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
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
		
		JLabel lblId = new JLabel("ID:");
		
		tfId = new JTextField();
		tfId.setColumns(10);
		
		JLabel lblInsertarProductos = new JLabel("INSERTAR PRODUCTOS");
		
		JLabel lblDatabase = new JLabel("Database?");
		
		JButton btnVentanaPrincipal = new JButton("HOME");
		btnVentanaPrincipal.addActionListener(new ActionListener() {
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(152, Short.MAX_VALUE)
					.addComponent(lblInsertarProductos)
					.addGap(134))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDatabase))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDescripcion)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfNombre))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblStockActual)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfStockActual))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblStockMnimo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfStocMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPvp)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfPvp))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblId)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfId)))
							.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInsertar)
								.addComponent(lblDBSeleccionada, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))))
					.addGap(32))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVentanaPrincipal)
					.addContainerGap(354, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblInsertarProductos)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(lblDatabase)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(tfId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescripcion)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblStockActual)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(tfStockActual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDBSeleccionada)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblStockMnimo)
						.addComponent(tfStocMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPvp)
						.addComponent(tfPvp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInsertar))
					.addGap(37)
					.addComponent(btnVentanaPrincipal)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
