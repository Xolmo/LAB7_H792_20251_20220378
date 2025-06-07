package com.example.lab7_20220378.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "proveedores")
public class Proveedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "razon_social")
    @NotBlank
    @Size(max = 100, message = "La razón social solo puede tener 100 caracteres")
    private String razonSocial;
    @Column(name = "nombre_comercial")
    @NotBlank
    @Size(max = 100, message = "El nombre comercial solo puede tener 100 caracteres")
    private String nombreComercial;
    @Digits(integer = 11, fraction = 0)
    private Integer ruc;
    @Digits(integer = 9, fraction = 0)
    private Integer telefono;
    @Column(name = "correo_electronico")
    @NotBlank
    @Email(message = "Debe ingresar un correo válido")
    private String corrroElectronico;
    @Column(name = "sitio_web")
    @NotBlank
    @URL(message = "Debe ingresar una URL válida")
    private String sitioWeb;
    @Column(name = "direccion_fisica")
    @NotBlank
    @Size(max = 150, message = "La direccion solo puede tener 150 caracteres")
    private String direccionFisica;
    @NotBlank
    private String pais;
    @Column(name = "representante_legal")
    @NotBlank
    private String representanteLegal;
    @Column(name = "dni_representante_legal")
    @Digits(integer = 8, fraction = 0)
    private Integer dniRepresentanteLegal;
    @Column(name = "tipo_de_proveedor")
    @NotBlank
    private String tipoDeProveedor;
    @NotBlank
    private String categoria;
    @Column(name = "facturacion_anual_dolar")
    private float facturacionAnualDolar;
    @Column(name = "fecha_de_registro")
    private LocalDateTime fechaDeRegistro;
    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
    private boolean estado;


}
