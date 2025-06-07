-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lab7db_20220378
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lab7db_20220378
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lab7db_20220378` DEFAULT CHARACTER SET utf8 ;
USE `lab7db_20220378` ;

-- -----------------------------------------------------
-- Table `lab7db_20220378`.`proveedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab7db_20220378`.`proveedores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `razon_social` VARCHAR(100) NOT NULL,
  `nombre_comercial` VARCHAR(100) NOT NULL,
  `ruc` INT NOT NULL,
  `telefono` INT NOT NULL,
  `correo_electronico` VARCHAR(45) NOT NULL,
  `sitio_web` VARCHAR(100) NOT NULL,
  `direccion_fisica` VARCHAR(150) NOT NULL,
  `pais` VARCHAR(30) NOT NULL,
  `representante_legal` VARCHAR(60) NOT NULL,
  `dni_representante_legal` INT NOT NULL,
  `tipo_de_proveedor` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `facturacion_anual_dolar` FLOAT NOT NULL,
  `fecha_de_registro` DATETIME NOT NULL,
  `ultima_actualizacion` DATETIME NOT NULL,
  `estado` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `ruc_UNIQUE` (`ruc` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
