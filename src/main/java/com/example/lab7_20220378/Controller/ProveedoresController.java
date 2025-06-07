package com.example.lab7_20220378.Controller;

import com.example.lab7_20220378.Entity.Proveedores;
import com.example.lab7_20220378.Repository.ProveedoresRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
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

    @PostMapping(value = "")
    public ResponseEntity<HashMap<String, Object>> crearProveedor(
            @RequestBody Proveedores proveedor,
            @RequestParam(value = "fectId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();
        if (proveedor.getRazonSocial() != null) {
            if (proveedor.getRazonSocial().length() > 100) {
                responseMap.put("msg", "La razón social no debe exceder de 100 caracteres");
                responseMap.put("result", "failure");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("msg", "Debe indicar una razón social");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getNombreComercial() != null) {
            if (proveedor.getNombreComercial().length() > 100) {
                responseMap.put("msg", "El nombre comercial no debe exceder de 100 caracteres");
                responseMap.put("result", "failure");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("msg", "Debe indicar un nombre comercial");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getRuc() == null) {
            responseMap.put("msg", "Debe indicar un Ruc");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getTelefono() != null) {
            if (proveedor.getTelefono() > 999999999 || proveedor.getTelefono() < 99999999) {
                responseMap.put("msg", "El número de teléfono no es válido");
                responseMap.put("result", "failure");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("msg", "Debe indicar un número de telefono");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getCorreoElectronico() != null) {
            if (!proveedor.getCorreoElectronico().matches("^[\\w-+]+(\\.[\\w-]{1,62}){0,126}@[\\w-]{1,63}(\\.[\\w-]{1,62})+/[\\w-]+$")) {
                responseMap.put("msg", "Debe ingresar un correo electronico valido");
                responseMap.put("result", "failure");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("msg", "Debe ingresar un correo electronico");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getSitioWeb() != null) {
            if (!validarUrl(proveedor.getSitioWeb())) {
                responseMap.put("msg", "Debe ingresar una URL válida");
                responseMap.put("result", "failure");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("msg", "Debe ingresar una URL");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getDireccionFisica() != null) {
            if (proveedor.getDireccionFisica().length() > 150) {
                responseMap.put("msg", "La dirección no debe exceder de 150 caracteres");
                responseMap.put("result", "failure");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("msg", "Debe indicar una dirección");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getPais() == null) {
            responseMap.put("msg", "Debe indicar un pais");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getRepresentanteLegal() == null) {
            responseMap.put("msg", "Debe indicar un representante legal");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getDniRepresentanteLegal() != null) {
            if (proveedor.getDniRepresentanteLegal() > 99999999 || proveedor.getDniRepresentanteLegal() < 9999999) {
                responseMap.put("msg", "El número de DNI no es válido");
                responseMap.put("result", "failure");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("msg", "Debe indicar un DNI para el representante legal");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getTipoDeProveedor() != null) {
            if (!proveedor.getTipoDeProveedor().equals("Nacional") && !proveedor.getTipoDeProveedor().equals("Electronico")) {
                responseMap.put("msg", "Debe ingresar una tipo de proveedor válido");
                responseMap.put("result", "failure");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("msg", "Debe indicar un tipo de proveedor");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getCategoria() != null) {
            if (!proveedor.getCategoria().equals("Servicios") && !proveedor.getCategoria().equals("Productos") && !proveedor.getCategoria().equals("Tecnologia") && !proveedor.getCategoria().equals("Otros")) {
                responseMap.put("msg", "Debe ingresar una categoria valida");
                responseMap.put("result", "failure");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("msg", "Debe indicar una categoria");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        if (proveedor.getFacturacionAnualDolar() < 0) {
            responseMap.put("msg", "Debe indicar la facturacion anual en dolares");
            responseMap.put("result", "failure");
            return ResponseEntity.badRequest().body(responseMap);
        }

        proveedor.setFechaDeRegistro(LocalDateTime.now());
        proveedor.setUltimaActualizacion(LocalDateTime.now());
        proveedor.setEstado(true);

        proveedoresRepository.save(proveedor);

        responseMap.put("msg", "El proveedor ha sido agregado correctamente");
        responseMap.put("result", "success");
        return ResponseEntity.ok().body(responseMap);
    }

    public static boolean validarUrl(String urlString) {
        try {
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }


}
