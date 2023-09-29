package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

//GUI

public class Demo01 {
	public static void main(String[] args) {
		//1. Conexion
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion001");
		//2. Crear manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		//proceso
		//-------------Esto por que hay un controlador en la clase tipo 
		//Usuario u = new Usuario(223, "Jun", "Perez", "jperez", "7854", 
								//"2000/01/15", 1, 1);
		
		//Valores fijos. Sirve para registrar
		Usuario u = new Usuario();
		u.setCod_usua(123);
		u.setNom_usua("Juan");
		u.setApe_usua("Perez");
		u.setUsr_usua("jperez");
		u.setCla_usua("7854");
		u.setFna_usua("2000/01/15");
		u.setIdtipo(1);
		u.setEst_usua(1);
		//Usuario u = new Usuario(123, "Maria", "Fidigna", "mkorl", "3333", 
			//	"2008/01/15", 1, 1);
		em.getTransaction().begin();
		em.persist(u);
		
		
		// update 
		em.merge(u);
		// delete
		em.remove(u);
		// select * form ... where ...coming soon...
		Usuario x = em.find(Usuario.class, 1);
		
		
		em.getTransaction().commit();
		System.out.println("Registro OK");
		
		em.close();
	}
}
