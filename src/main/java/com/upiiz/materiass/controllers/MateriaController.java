package com.upiiz.materiass.controllers;

import com.upiiz.materiass.dto.MateriaDTO;
import com.upiiz.materiass.models.Materia;
import com.upiiz.materiass.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    //LISTAR MATERIAS
    //http://localhost:8080/materias
    @GetMapping
    public String ListarMaterias(Model model){

        model.addAttribute("materias",materiaService.getListadomaterias());
        return "listado.html";

    }

    //MOSTRAR EL FORMULARIO - ACTUALIZAR
    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Long id, Model model){

        Optional<Materia> materia = materiaService.getMateriaPorId(id);
        if (materia.isPresent()){
            model.addAttribute("materia",materia.get());
            return  "formulario-actualizar";
        }
        return "listado";
    }

    //ACTUALIZAR MATERIA
    @PostMapping("/actualizar")
    public String actualizarMateria(@ModelAttribute Materia materia){
        MateriaDTO materiaDTO = new MateriaDTO(materia.getNombre(), materia.getCreditos());
        materiaService.modificarMateria(materia.getId(),materiaDTO);
        return "redirect:/materias";

    }

    //MOSTRAR EL FORMULARIO - NUEVA
    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model){
        model.addAttribute("materia",new MateriaDTO());
        return "formulario-crear";
    }

    //GUARDAR MATERIA POR EL METODO POST
    @PostMapping
    public String guardarMateria(@ModelAttribute MateriaDTO materiaDTO){
        materiaService.crearMateria(materiaDTO);
        return "redirect:/materias";
    }

    //MOSTRAR FORMULARIO - ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String formularioEliminarMateria(@PathVariable Long id,Model model){
        Optional<Materia> materia = materiaService.getMateriaPorId(id);
        if (materia.isPresent()) {
            model.addAttribute("materia", materia.get());

            return "formulario-eliminar";
        }
        return "listado";
    }

    //ELIMINAR MATERIA
    @PostMapping("/eliminar")
    public String eliminarMateria(@ModelAttribute MateriaDTO materiaDTO,Long id) {

        materiaService.eliminarMateria(id);

        return "redirect:/materias";
    }


    //ELIMINAR UNA MATERIA
    //http://localhost:8080/materias/1
    //@GetMapping("/eliminar/{id}")
    //public String eliminarMateria(@PathVariable Long id){z
        //materiaService.eliminarMateria(id);
        //return "redirect:/materias";
    //}

}
