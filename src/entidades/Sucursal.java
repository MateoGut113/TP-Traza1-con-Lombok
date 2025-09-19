package entidades;

import lombok.*;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Sucursal {
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_Casa_Motriz;
    private Domicilio domicilio;

}
