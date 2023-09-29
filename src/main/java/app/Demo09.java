package app;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import model.Usuario;

//GUI

public class Demo09 {
	public static void main(String[] args) {
		String usuario = JOptionPane.showInputDialog("Ingrese usuario");
		String clave = JOptionPane.showInputDialog("Ingrese clave");
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
			//dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error Usuario o clave incorrecta");
		}
			
		
		em.close();
	}
}
