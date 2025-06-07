package com.example.lab7_20220378.Controller;

import com.example.lab7_20220378.Entity.Proveedores;
import com.example.lab7_20220378.Repository.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {

    @Autowired
    ProveedoresRepository proveedoresRepository;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public ResponseEntity obtenerProveedores() {

        return new ResponseEntity(proveedoresRepository.findAll(), HttpStatus.OK) ;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public ResponseEntity<HashMap<String, Object>> obtenerProveedorPorId(@PathVariable("id") String idStr) {
        HashMap<String, Object> responseJson = new HashMap<>();
        try{
            Optional<Proveedores> proveedorOpt = proveedoresRepository.findById(Integer.parseInt(idStr));
            if(proveedorOpt.isPresent()){
                responseJson.put("result", "sucess" );
                responseJson.put("proveedor", proveedorOpt.get());
                return ResponseEntity.ok(responseJson);
            } else {
                responseJson.put("msg", "Proveedor no encontrado");
            }
        } catch (NumberFormatException e) {
            responseJson.put("msg", "El ID del proveedor debe ser un número entero positivo");
        }
        responseJson.put("result", "failure");
        return ResponseEntity.badRequest().body(responseJson);
    }

}
