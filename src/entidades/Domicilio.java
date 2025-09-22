package entidades;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class Domicilio {
    private String calle;
    private int numero;
    private int cp;

    //Relaciones con otras clases.
    //private Sucursal sucursal; <-- No va por direccion de la relacion.
    private Localidad localidad;
}
