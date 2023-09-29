package app;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
import model.Usuario;

//GUI

public class Demo07 {
	public static void main(String[] args) {
		//LISTADO DE USUARIOS, MOSTRANDO EL TIPO DE USUARIO ---------------------
		//1. Conexion
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion001");
		//2. Crear manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		// select * from tb_.....
		//String sql = "select * from tb_usuarios"; como esta declarado en la clase usuario
		String jpql = "select p from Producto p";
		List<Producto> lstProductos = em.createQuery(jpql, Producto.class).getResultList();
		
		//Mostrar contenido de listado
		for (Producto p : lstProductos) {
			System.out.println("Codigo......:" + p.getId_prod());
			System.out.println("Nombre......:" + p.getDes_prod());
			System.out.println("Categoria...:" + p.getObjCategoria().getDescripcion());
			System.out.println("Proveedor...:" + p.getObjProveedor().getNombre_rs());
			System.out.println("------------------------------------");
			
		}
		em.close();
	}
}
