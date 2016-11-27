/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.utpl.dcce.sic.arqapp.demojpa.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jorgaf
 */
public class ProyectoTest1 {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    
    public ProyectoTest1() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("DemoJPA");
        em = emf.createEntityManager();
    }
    
    @AfterClass
    public static void tearDownClass() {
        em.close();
        emf.close();
    }
    
    @Before
    public void setUp() {
        tx = em.getTransaction();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void crearProyecto(){
        Proyecto p = new Proyecto();
        p.setTitulo("Uso de JPA a través de Maven");
        p.setPeriodoAcademico("Octubre 2016 - Febrero 2017");
        p.setObjetivos("Crear aplicaciones que usen JPA para persistir datos");
        p.setEstado('A');
        p.setFechaInicio(Date.valueOf(LocalDate.now()));
        p.setFechaFin(Date.valueOf(LocalDate.parse("2017-02-01")));
        
        tx.begin();
        em.persist(p);
        tx.commit();
        
        assertNotNull("ID no debería ser nulo", p.getId());
        
        TypedQuery<Proyecto> qry = em.createQuery("SELECT p FROM Proyecto p", Proyecto.class);
        List<Proyecto> proyectos = qry.getResultList();
        
        assertEquals("Debería existir una fila", 1, proyectos.size());
        
    }
    
}
