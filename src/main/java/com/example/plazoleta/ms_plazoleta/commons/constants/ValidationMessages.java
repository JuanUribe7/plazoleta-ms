package com.example.plazoleta.ms_plazoleta.commons.constants;

public class ValidationMessages {
    public static final String UTILITY_CLASS          = "Clase utilitaria, no debe instanciarse.";
    // PhoneValidator
    public static final String PHONE_EMPTY            = "El número de teléfono no puede estar vacío";
    public static final String PHONE_PLUS_ONLY_AT_START = "El '+' solo puede estar al inicio y una sola vez";
    public static final String PHONE_NO_DOUBLE_ZERO   = "El número no puede comenzar con '00'";
    public static final String PHONE_LENGTH_DIGITS    = "El número debe tener entre 10 y 13 caracteres y contener solo dígitos, con un '+' opcional al inicio";
    // OwnerValidator
    public static final String OWNER_ID_NULL         = "El ID del propietario no puede ser nulo";
    public static final String OWNER_ID_POSITIVE     = "El ID del propietario debe ser un número positivo";
    // NameValidator
    public static final String NAME_ONLY_LETTERS     = "El nombre solo puede contener letras y espacios";
    public static final String NAME_NO_DOUBLE_SPACES = "El nombre no puede tener espacios dobles";
    public static final String NAME_NO_TRIM_SPACES   = "El nombre no puede comenzar ni terminar con espacio";
    public static final String NAME_MIN_LENGTH       = "El nombre debe tener al menos 2 caracteres";
    // NitValidator
    public static final String NIT_EMPTY             = "El NIT no puede estar vacío";
    public static final String NIT_DIGITS_8_15       = "El NIT debe contener solo dígitos entre 8 y 15 caracteres";
    public static final String NIT_NO_LEADING_ZERO   = "El NIT no puede comenzar con cero";
    // LogoValidator
    public static final String LOGO_URL_EMPTY        = "La URL del logo no puede estar vacía";
    public static final String LOGO_URL_LOCAL        = "La URL del logo no puede ser local";
    public static final String LOGO_URL_VALID        = "La URL del logo debe ser válida y comenzar con http o https";
    public static final String LOGO_URL_IMAGE_EXT    = "La URL del logo debe terminar en .png, .jpg, .jpeg o .svg";
    // AddressValidator
    public static final String ADDRESS_EMPTY         = "La dirección no puede estar vacía ni ser solo espacios";
    public static final String ADDRESS_ALLOWED_CHARS = "La dirección solo puede contener letras, números, espacios, guiones, puntos, comas y numeral";
    public static final String ADDRESS_LENGTH        = "La dirección debe tener entre 5 y 100 caracteres";
    public static final String ADDRESS_NO_ONLY_NUM   = "La dirección no puede contener solo números";



    // CategoryValidator
    public static final String CATEGORY_EMPTY             = "La categoría no puede estar vacía";
    public static final String CATEGORY_ONLY_LETTERS      = "La categoría solo puede contener letras y espacios";
    public static final String CATEGORY_LENGTH            = "La categoría debe tener entre 3 y 30 caracteres";

    // DescriptionValidator & DishUpdateValidator
    public static final String DESCRIPTION_EMPTY          = "La descripción no puede estar vacía";
    public static final String DESCRIPTION_ALLOWED_CHARS  = "La descripción solo puede contener letras, números, espacios, puntos y comas";
    public static final String DESCRIPTION_LENGTH         = "La descripción debe tener entre 10 y 200 caracteres";

    // DishValidator – imagen
    public static final String IMAGE_URL_EMPTY            = "La URL del logo no puede estar vacía";
    public static final String IMAGE_URL_LOCAL            = "La URL del logo no puede ser local";
    public static final String IMAGE_URL_VALID            = "La URL del logo debe ser válida y comenzar con http o https";
    public static final String IMAGE_URL_IMAGE_EXT        = "La URL del logo debe terminar en .png, .jpg, .jpeg o .svg";

    // Precio
    public static final String PRICE_POSITIVE             = "Precio debe ser mayor a 0";
    private ValidationMessages() {}
}
