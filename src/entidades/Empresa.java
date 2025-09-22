package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString (exclude = "sucursales") //No mostrará las sucursales asociadas a la empresa en el Main. En este caso no hace falta
// debido a que no hay bidireccionalidad entre las clases.
@ToString
@Setter
@Getter

public class Empresa {
    private Long id; //Atributo necesario para la implementacion de la clase InMemoryRepository
    private String nombre;
    private String razonSocial;
    private int cuit;
    private String logo;

    //Relaciones con otras clases.
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

    //Metodo no necesario, debido a que el patron builder se encarga de la agregacion a la lista.
    public void agregarSucursal (Sucursal sucursal){
        sucursales.add(sucursal);
    }

}
