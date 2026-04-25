package com.upiiz.materiass.services;

import com.upiiz.materiass.dto.MateriaDTO;
import com.upiiz.materiass.models.Materia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    private List<Materia> listadomaterias= new ArrayList<Materia>();
    private Long contadorID=3L;

    //CONSTRUCTOR
    public MateriaService(){
        listadomaterias.add(new Materia(1L,"Matematicas",7.5f));
        listadomaterias.add(new Materia(2L,"Quimica",7.2f));
    }

    //CRUD - CREATE,READ, UPDATE, DELETE
    //R - TODAS LAS MATERIAS
    public List<Materia> getListadomaterias() {
        return listadomaterias;
    }

    //R - UNA MATERIA
    public Optional<Materia> getMateriaPorId(Long id) {
        return listadomaterias.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    //C - CREAR UNA MATERIA
    public Materia crearMateria(MateriaDTO materiaDTO){
        Materia nuevaMateria = new Materia(contadorID, materiaDTO.getNombre(), materiaDTO.getCreditos());
        listadomaterias.add(nuevaMateria);
        contadorID++;
        return nuevaMateria;
    }

    //U - ACTUALIZAR MATERIA
    public Optional<Materia> modificarMateria(Long id, MateriaDTO materiaDTO){
        Optional<Materia> materiaActualizar=getMateriaPorId(id);
        if (materiaActualizar.isPresent()){
            Materia materiaActual=materiaActualizar.get();
            materiaActual.setNombre(materiaDTO.getNombre());
            materiaActual.setCreditos(materiaDTO.getCreditos());
            return Optional.of(materiaActual);
        }
        return Optional.empty();
    }

    //D - ELIMINAR MATERIA
    public boolean eliminarMateria(Long id) {
        return listadomaterias.removeIf(materia -> materia.getId().equals(id));
    }

}
