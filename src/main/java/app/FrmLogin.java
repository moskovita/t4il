package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(10, 64, 102, 14);
		contentPane.add(lblClave);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(122, 61, 161, 20);
		contentPane.add(txtClave);
		
	}

	
	private JTextField txtClave;
	
	//Aqui afuera el leer Usuario
	String leerUsuario() {
		if (!txtUsuario.getText().matches("[A-Za-z0-9]+[@][a-z0-9]+[.][a-z]{2,3}")) {
			JOptionPane.showMessageDialog(null, "Ingrese un correo");
			return null;
		}
		return txtUsuario.getText();
	}
	
	void registrar() {
		/*Cambios
		String usuario = JOptionPane.showInputDialog("Ingrese usuario");
		String clave = JOptionPane.showInputDialog("Ingrese clave");  */
		
		// Cambios 2 para examen 
		//String usuario = txtUsuario.getText();
		String usuario = leerUsuario();
		String clave = txtClave.getText();
		
		//Validacion
		if (usuario == null || clave == null) {
			return;
		}
		//LISTADO DE USUARIOS, SEGUN UN CRITERIO (fILTRO)
		//1. Conexion
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion001");
		//2. Crear manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		// select * from tb_.....
		//String sql = "select * from tb_usuarios where "campo" condicion"; como esta declarado en la clase usuario
		//String sql = "select * from tb_usuarios where idtipo = ? --> Lista
		//------------int xtipo = 1; //Buscara usuarios tipo 1 - administrativo
		int xtipo = 2; //Buscara los usuarios tipo 2 - cliente
		String jpql = "select u from Usuario u where u.usr_usua = :xusr and u.cla_usua = :xcla";
		/*List<Usuario> lstUsuarios = em.createQuery(jpql, Usuario.class).
				setParameter("xusr", usuario).setParameter("xcla", clave).getResultList();*/
		
		try {
			Usuario u = em.createQuery(jpql, Usuario.class).
					setParameter("xusr", usuario).setParameter("xcla", clave).getSingleResult();
			
			//Mostrar contenido de listado
			//for (Usuario u : lstUsuarios) {
				
			//Mostrar contenido del usuario
			JOptionPane.showMessageDialog(null, "Bienvenido" + u.getNom_usua());
			//Abrir la ventana principal
			FrmManteProd v = new FrmManteProd();
			v.setVisible(true);
			// DISPOSEEEEEEEEEEEEEEEEE PARA GUI
			dispose();
			//dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error Usuario o clave incorrecta");
		}
			
		
		em.close();
	}
}
