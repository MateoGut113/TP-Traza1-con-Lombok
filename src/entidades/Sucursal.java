package entidades;

import lombok.*;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true) //Solo apareceran los campos indicados

public class Sucursal {
    @ToString.Include
    private String nombre; //Asi solo aparecera el nombre de la sucursal por pantalla.
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_Casa_Motriz;

    //Relaciones con otras clases.
    //private Empresa empresa; <-- No va por direccion de la relacion.
    private Domicilio domicilio;

}
