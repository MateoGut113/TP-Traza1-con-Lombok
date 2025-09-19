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
    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>();

    public void agregarLocalidad (Localidad localidad){
        localidades.add(localidad);
    }

}
