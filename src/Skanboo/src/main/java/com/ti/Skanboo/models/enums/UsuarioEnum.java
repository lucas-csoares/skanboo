package com.ti.Skanboo.models.enums;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UsuarioEnum {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private Integer code;
    private String description;

    public static UsuarioEnum toEnum(Integer code) {

        if (Objects.isNull(code))
            return null;

        for (UsuarioEnum x : UsuarioEnum.values()) {
            if (code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Codigo invalido: " + code);
    }

}
