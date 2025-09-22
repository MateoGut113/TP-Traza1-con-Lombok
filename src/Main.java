import entidades.*;
import repositorios.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        //Iniciamos el Repositorio
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();

        //Creamos el pais donde funcionará en un principio nuestro sistema
        Pais argentina = Pais.builder().nombre("Argentina")
                .build();

        //Creacion de provincias disponibles para nuestro sistema (incluyendo localidades y domicilios)
        //Creacion de la provincia de Buenos Aires
        Provincia buenosAires = Provincia.builder().nombre("Buenos Aires").pais(argentina)
                .build();
        //argentina.agregarProvincia(buenosAires); <-- No va por direccion de la relacion.

        //Creacion de Localidades para la provincia de Buenos Aires
        Localidad caba = Localidad.builder().nombre("CABA").provincia(buenosAires)
                .build();
        //buenosAires.agregarLocalidad(caba); <-- No va por direccion de la relacion.

        //Creacion de Domicilios
        Domicilio domicilioCABA1 = Domicilio.builder().numero(1).cp(1094).calle("Calle Almagro").localidad(caba)
                .build();
        //caba.agregarDomicilio(domicilioCABA1); <-- No va por direccion de la relacion.

        Localidad laPlata = Localidad.builder().nombre("La Plata").provincia(buenosAires)
                .build();
        //buenosAires.agregarLocalidad(laPlata); <-- No va por direccion de la relacion.

        Domicilio domicilioLAPLATA1 = Domicilio.builder().numero(2).cp(1919).calle("Calle Lobo").localidad(laPlata)
                .build();
        //laPlata.agregarDomicilio(domicilioLAPLATA1); <-- No va por direccion de la relacion.

        //Creacion de la provincia de Cordoba
        Provincia cordoba = Provincia.builder().nombre("Córdoba").pais(argentina)
                .build();
        //argentina.agregarProvincia(cordoba); <-- No va por direccion de la relacion.

        //Creacion de Localidades para la provincia de CORDOBA
        Localidad cbaCapital = Localidad.builder().nombre("Córdoba Capital").provincia(cordoba)
                .build();
        //cordoba.agregarLocalidad(cbaCapital); <-- No va por direccion de la relacion.

        //Creacion de Domicilios
        Domicilio domicilioCBACAPITAL1 = Domicilio.builder().numero(1).cp(1516).calle("Belgrano").localidad(cbaCapital)
                .build();
        //cbaCapital.agregarDomicilio(domicilioCBACAPITAL1);

        Localidad villaCarlosPaz = Localidad.builder().nombre("Villa Carlos Paz").provincia(cordoba)
                .build();
        //cordoba.agregarLocalidad(villaCarlosPaz); <-- No va por direccion de la relacion.

        Domicilio domicilioVILLACARLOSPAZ1 = Domicilio.builder().numero(10).cp(5152).calle("La Paz").localidad(villaCarlosPaz)
                .build();
        //villaCarlosPaz.agregarDomicilio(domicilioVILLACARLOSPAZ1);


        //Creacion de sucursales
        Sucursal sucursal1 = Sucursal.builder().nombre("Sucursal 1").horarioApertura(LocalTime.of(9,00))
                .horarioCierre(LocalTime.of(20,00))
                .domicilio(domicilioCABA1)
                .es_Casa_Motriz(false)
                .build();

        Sucursal sucursal2 = Sucursal.builder().nombre("Sucursal 2").horarioApertura(LocalTime.of(8,00))
                .horarioCierre(LocalTime.of(18,00))
                .domicilio(domicilioLAPLATA1)
                .es_Casa_Motriz(true)
                .build();

        Sucursal sucursal3 = Sucursal.builder().nombre("Sucursal 3").horarioApertura(LocalTime.of(10,30))
                .horarioCierre(LocalTime.of(22,00))
                .domicilio(domicilioCBACAPITAL1)
                .es_Casa_Motriz(true)
                .build();

        Sucursal sucursal4 = Sucursal.builder().nombre("Sucursal 4").horarioApertura(LocalTime.of(8,00))
                .horarioCierre(LocalTime.of(20,00))
                .domicilio(domicilioVILLACARLOSPAZ1)
                .es_Casa_Motriz(false)
                .build();

        //Creacion de empresas
        Empresa empresa1 = Empresa.builder().nombre("Empresa 1").cuit(304050).razonSocial("Razon Social 1").logo("Tigre")
                .build();
        //Aprovechamos y utilizamos este metodo creado, no optimizado (se pueden agregar directamente en el patron builder)
        empresa1.agregarSucursal(sucursal1);
        empresa1.agregarSucursal(sucursal2);

        Empresa empresa2 = Empresa.builder().nombre("Empresa 2").cuit(405060).razonSocial("Razon Social 2").logo("Lobo")
                .build();
        //Aprovechamos y utilizamos este metodo creado, no optimizado (se pueden agregar directamente en el patron builder)
        empresa2.agregarSucursal(sucursal3);
        empresa2.agregarSucursal(sucursal4);

        // Guardar empresas en el repositorio
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);

        //-->PROBAMOS EL REPOSITORIO<--
        System.out.println("PRUEBA DEL NUEVO REPOSITORIO DEL SISTEMA:");

        //Mostramos las empresas previamente guardadas
        System.out.println("\nTodas las empresas disponibles en el sistema son:");
        List<Empresa> todasLasEmpresas = empresaRepository.findAll();
        todasLasEmpresas.forEach(System.out::println);

        // Encontrar una empresa por su ID
        System.out.println("\nBuscamos a una empresa por su ID:");
        Optional<Empresa> empresaEncontradaPorID = empresaRepository.findById(1L);
        empresaEncontradaPorID.ifPresent(e -> System.out.println("\nEmpresa encontrada por ID 1: " + e));

        //Encontrar una empresa por su nombre
        System.out.println("\nBuscamos a una empresa por su nombre:");
        List<Empresa> empresasPorNombre = empresaRepository.genericFindByField("nombre", "Empresa 2");
        System.out.println("Empresas con nombre 'Empresa 2':");
        empresasPorNombre.forEach(System.out::println);

        //Actualizar los datos de una empresa buscando por su ID. Por ejemplo modificar su cuit.
        System.out.println("\nActualizamos el cuit de la Empresa 1:");
        Empresa empresaActualizada1 = Empresa.builder().id(1L).nombre("Empresa 1").razonSocial("Razon Social 1").cuit(345678)
                .logo("Tigre")
                //Manera optima de utilizar el metodo builder con Hashset
                .sucursales(new HashSet<>(Set.of(sucursal1, sucursal2)))
                .build();

        //Guardamos los cambios
        empresaRepository.genericUpdate(1L, empresaActualizada1);

        //Aqui mostramos los cambios hechos
        Optional<Empresa> empresaVerificada = empresaRepository.findById(1L);
        empresaVerificada.ifPresent(e -> System.out.println("Empresa después de la actualización: " + e));

        //Eliminar una empresa por su ID, por ejemplo la Empresa 1
        System.out.println("\nEliminamos a la empresa 1 de nuestro sistema.");
        empresaRepository.genericDelete(1L);

        //Mostramos a todas las empresas de nuevo, para verificar que se haya eliminado correctamente.
        System.out.println("\nTodas las empresas, luego de la actualizacion:");
        List<Empresa> todasLasEmpresas2 = empresaRepository.findAll();
        todasLasEmpresas2.forEach(System.out::println);
    }
}