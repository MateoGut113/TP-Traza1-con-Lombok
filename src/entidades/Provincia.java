package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class Provincia {
    private String nombre;

    //Relaciones con otras clases.
    //@Builder.Default <-- No va por direccion de la relacion.
    //private Set<Localidad> localidades = new HashSet<>();
    private Pais pais;

    //public void agregarLocalidad (Localidad localidad){
    //    localidades.add(localidad);
    //}

}
