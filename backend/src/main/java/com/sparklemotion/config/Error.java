package com.sparklemotion.config;

public enum Error {
    INTERNAL_SERVER_ERROR(500, "Error interno del servidor"),
    USER_DISABLED(102, "El usuario esta desactivado"),
    USER_NOT_FOUND(406, "El usuario no fue encontrado"),

    USER_EXISTS(101, "El usuario ya existe"),

    ACTION_NOT_FOUND(404, "La Action no existe"),


    INVALID_CREDENTIALS(404,"credenciales inválidas");
    private final int value;
    private final String message;

    Error(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
