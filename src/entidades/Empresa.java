package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString (exclude = "sucursales")
@Setter
@Getter

public class Empresa {
    private Long id; //Atributo necesario para la implementacion de la clase InMemoryRepository
    private String nombre;
    private String razonSocial;
    private int cuit;
    private String logo;
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

    public void agregarSucursal (Sucursal sucursal){
        sucursales.add(sucursal);
    }

}
