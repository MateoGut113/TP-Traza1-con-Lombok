package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class Localidad {
    private String nombre;

    //Relaciones con otras clases.
    //@Builder.Default  <-- No va por direccion de la relacion.
    //private Set<Domicilio> domicilios = new HashSet<>();
    private Provincia provincia;

    //public void agregarDomicilio (Domicilio domicilio){
    //    domicilios.add(domicilio);
    //}
}
