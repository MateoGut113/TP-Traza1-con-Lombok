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

}
