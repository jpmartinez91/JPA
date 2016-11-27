/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.utpl.dcce.sic.arqapp.demojpa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jorgaf
 */
@Entity
public class Proyecto implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String titulo;
    private String periodoAcademico;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    private String objetivos;
    private Character estado;
    @OneToOne
    private Componente componte;
    @OneToMany
    @JoinColumn(name = "proyecto_id")
    private List<Entregable> entregables;
    
    @ManyToMany (mappedBy = "proyectos", 
            cascade = CascadeType.ALL)
    private List<Estudiante> desarrolladoPor;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "proyecto_etiqueta" ,
            joinColumns = @JoinColumn(name = "proyecto_id") ,
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Etiqueta> tags;
    

    public Proyecto() {
        entregables = new ArrayList<>();
        desarrolladoPor = new ArrayList<>();
        tags = new ArrayList<>();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the periodoAcademico
     */
    public String getPeriodoAcademico() {
        return periodoAcademico;
    }

    /**
     * @param periodoAcademico the periodoAcademico to set
     */
    public void setPeriodoAcademico(String periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the objetivos
     */
    public String getObjetivos() {
        return objetivos;
    }

    /**
     * @param objetivos the objetivos to set
     */
    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    /**
     * @return the estado
     */
    public Character getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Componente getComponte() {
        return componte;
    }

    public void setComponte(Componente componte) {
        this.componte = componte;
    }

    public List<Entregable> getEntregables() {
        return entregables;
    }

    public void setEntregables(List<Entregable> entregables) {
        this.entregables = entregables;
    }
    
    public void addEntregable(Entregable e) {
        this.entregables.add(e);
    }

    public List<Estudiante> getDesarrolladoPor() {
        return desarrolladoPor;
    }

    public void setDesarrolladoPor(List<Estudiante> desarrolladoPor) {
        this.desarrolladoPor = desarrolladoPor;
    }
    
    public void addEstudiante(Estudiante e) {
        this.desarrolladoPor.add(e);
    }

    public List<Etiqueta> getTags() {
        return tags;
    }

    public void setTags(List<Etiqueta> tags) {
        this.tags = tags;
    }
    
    public void addTags(Etiqueta e) {
        this.tags.add(e);
    }
    
}
