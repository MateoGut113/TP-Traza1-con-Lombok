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
    @Builder.Default
    private Set<Domicilio> domicilios = new HashSet<>();

    public void agregarDomicilio (Domicilio domicilio){
        domicilios.add(domicilio);
    }
}
