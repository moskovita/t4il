package app;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

//GUI

public class Demo08 {
	public static void main(String[] args) {
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
		String jpql = "select u from Usuario u where u.idtipo = :xtipo";
		List<Usuario> lstUsuarios = em.createQuery(jpql, Usuario.class).setParameter("xtipo", xtipo).getResultList();
		
		//Mostrar contenido de listado
		for (Usuario u : lstUsuarios) {
			System.out.println("Codigo...:" + u.getCod_usua());
			System.out.println("Nombre...:" + u.getNom_usua()+ " " + u.getApe_usua());
			System.out.println("Tipo.....:" + u.getIdtipo()+ " - " + u.getObjTipo().getDescripcion());
			System.out.println("------------------------------------");
			
		}
		em.close();
	}
}
