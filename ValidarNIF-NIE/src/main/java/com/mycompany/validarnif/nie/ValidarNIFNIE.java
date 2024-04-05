/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.validarnif.nie;


/**
 *
 * @author DanielPM
 */
public class ValidarNIFNIE {
    
    private static final String  LETRAS_VALIDACION = "TRWAGMYFPDXBNJZSQVHLCKE";

    public static boolean validar(String documento) throws Exception {
        if (documento == null || documento.length() != 9) {
            throw new Exception("El documento debe tener una longitud de 9 caracteres.");
        }

        documento = documento.toUpperCase();

        char tipo = documento.charAt(0);
        char letra = documento.charAt(8);
        String digitos = documento.substring(0, 8);

        if (Character.isDigit(letra)) {
            throw new Exception("El último carácter debe ser una letra.");
        }

        if (tipo == 'X' || tipo == 'Y' || tipo == 'Z') {
            digitos = (tipo == 'X' ? "0" : tipo == 'Y' ? "1" : "2") + digitos.substring(1);
        } else if (!Character.isDigit(tipo)) {
            throw new Exception("El primer carácter debe ser un dígito o una de las letras X, Y, Z.");
        }

        int numero;
        try {
            numero = Integer.parseInt(digitos);
        } catch (NumberFormatException e) {
            throw new Exception("Los caracteres entre el primero y el último deben ser dígitos.");
        }

        char letraCalculada = LETRAS_VALIDACION.charAt(numero % 23);

        if (letra != letraCalculada) {
            throw new Exception("La letra no corresponde con los números.");
        }

        return true;
    }
    
    public static void imprimirValidacion(String documento) {
    try {
        if (validar(documento)) {
            System.out.println("El documento "+documento+" es correcto.");
        }
    } catch (Exception e) {
        System.out.println("Error al validar el documento: " + e.getMessage());
    }
}

    
    public static void main(String[] args) {
        
        imprimirValidacion("Z0000000M");
        imprimirValidacion("Z0000011Q");
        
        /*
        //DNIs        
        imprimirValidacion("12345678Z"); // Correcto
        imprimirValidacion("23456789D"); // Correcto
        imprimirValidacion("34567890V"); // Correcto
        
        
        //NIEs
        imprimirValidacion("X1234567L"); // Correcto
        imprimirValidacion("Y2345678Z"); // Correcto
        imprimirValidacion("Z3456789D"); // Correcto
        */
        
    }
}


/*
        //DNIs
        imprimirValidacion("12345678Z"); //Correcto (La letra Z corresponde al número 12345678)
        imprimirValidacion("12345678A"); //Incorrecto (La letra A no corresponde al número 12345678)
        imprimirValidacion("1234567"); // Incorrecto (Faltan dígitos y la letra)
        imprimirValidacion("06299582C"); //Correcto
        
        //NIEs
        imprimirValidacion("X1234567L"); //Correcto (La letra L corresponde al número 01234567)
        imprimirValidacion("Y1234567M"); //Incorrecto (La letra M no corresponde al número 11234567)
        imprimirValidacion("Z123456"); //Incorrecto  (Faltan dígitos y la letra final)
        imprimirValidacion("Y1234567X"); //Correcto
        imprimirValidacion("Y1234567Z"); //Incorrecto

        //DNIs
        imprimirValidacion("12345678Z");
        imprimirValidacion("87654321A");
        imprimirValidacion("23456789B");
        imprimirValidacion("34567890C");
        imprimirValidacion("45678901D");
        imprimirValidacion("56789012E");
        imprimirValidacion("67890123F");
        imprimirValidacion("78901234G");
        imprimirValidacion("89012345H");
        imprimirValidacion("90123456I");

        //NIEs
        imprimirValidacion("X1234567L");
        imprimirValidacion("Y2345678M");
        imprimirValidacion("Z3456789N");
        imprimirValidacion("X4567890O");
        imprimirValidacion("Y5678901P");
        imprimirValidacion("Z6789012Q");
        imprimirValidacion("X7890123R");
        imprimirValidacion("Y8901234S");
        imprimirValidacion("Z9012345T");
        imprimirValidacion("X0123456U");
*/