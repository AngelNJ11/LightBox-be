package pe.lightbox.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String correo;
    private String clave;
}
