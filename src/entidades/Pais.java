package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class Pais {
    private String nombre;
    private static final Set<Provincia> provincias = new HashSet<>();

    public void agregarProvincia(Provincia provincia) {
        provincias.add(provincia);
    }
}
