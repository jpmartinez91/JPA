/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.utpl.dcce.sic.arqapp.demojpa.controllers;

import ec.edu.utpl.dcce.sic.arqapp.demojpa.model.Componente;
import ec.edu.utpl.dcce.sic.arqapp.demojpa.model.Entregable;
import ec.edu.utpl.dcce.sic.arqapp.demojpa.model.Estudiante;
import ec.edu.utpl.dcce.sic.arqapp.demojpa.model.Etiqueta;
import ec.edu.utpl.dcce.sic.arqapp.demojpa.model.Proyecto;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.jinq.jpa.JinqJPAStreamProvider;
/**
 *
 * @author jorgaf
 */
public class Main {

    public static void main(String[] args) {
        Estudiante est1 = new Estudiante();
        est1.setCorreo("est1@utpl.edu.ec");
        est1.setApellidos("Udiante 1");
        est1.setNombres("Est");

        Estudiante est2 = new Estudiante();
        est2.setCorreo("est2@utpl.edu.ec");
        est1.setApellidos("Udiante 2");
        est1.setNombres("Est");

        Etiqueta etq1 = new Etiqueta();
        etq1.setTexto("mvn");

        Etiqueta etq2 = new Etiqueta();
        etq2.setTexto("JPA");

        Componente componente = new Componente();
        componente.setNombre("Arquitectura de aplicaciones");
        componente.setTitulacion("Sistema informáticos y computación");

        Proyecto p = new Proyecto();
        p.setTitulo("Uso de JPA a través de Maven");
        p.setPeriodoAcademico("Octubre 2016 - Febrero 2017");
        p.setObjetivos("Crear aplicaciones que usen JPA para persistir datos");
        p.setEstado('A');
        p.setFechaInicio(Date.valueOf(LocalDate.now()));
        p.setFechaFin(Date.valueOf(LocalDate.parse("2017-02-01")));

        Entregable e1 = new Entregable();
        e1.setNombre("Primera entrega");
        e1.setFecha(Date.valueOf(LocalDate.parse("2016-11-01")));

        Entregable e2 = new Entregable();
        e2.setNombre("Segunda entrega");
        e2.setFecha(Date.valueOf(LocalDate.parse("2016-12-01")));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DemoJPA");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(componente);
        p.setComponte(componente);
        em.persist(e1);
        em.persist(e2);
        p.addEntregable(e1);
        p.addEntregable(e2);
        p.addTags(etq1);
        p.addTags(etq2);
        p.addEstudiante(est1);
        p.addEstudiante(est2);
        em.persist(p);
        em.getTransaction().commit();
        /*TypedQuery<Proyecto> qry = em.createQuery("SELECT p FROM Proyecto p", Proyecto.class);
        List<Proyecto> listProyectos = qry.getResultList();
        for (Proyecto cp : listProyectos) {
            System.out.println(cp.getId());
            System.out.println(cp.getFechaInicio());
            System.out.println(cp.getFechaFin());
        }*/
        
        JinqJPAStreamProvider streams = new JinqJPAStreamProvider(emf);
        
        List<String> pry = streams.streamAll(em, Proyecto.class).select(py -> py.getTitulo()).toList();        
        pry.stream().forEach(System.out::println);

        em.close();
    }

}
