import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        SistemaSalud hospital = new SistemaSalud();

        try {
            hospital.agregarPersona(new Persona("Andrea", "Barcala", 33, "Las Lilas", "34851240", "Estudiante"));
        }catch (FaltaDeRactivosExeption e){
            System.out.printf(e.getMessage());
            hospital.agregarTest();
        }

        try {
            hospital.agregarPersona(new Persona("Mariana", "Fernandez", 33, "Las Lilas", "123456789", "Estudiante"));
        }catch (FaltaDeRactivosExeption e) {
            System.out.printf(e.getMessage());
            hospital.agregarTest();
        }

        try {
            hospital.agregarPersona(new Persona("Maxi", "Castillo", 33, "Las Lilas", "123", "Estudiante"));
        }catch (FaltaDeRactivosExeption e) {
            System.out.printf(e.getMessage());
            hospital.agregarTest();
        }

        try {
            hospital.agregarPersona(new Persona("Leo", "Chiesa", 33, "Las Lilas", "12345", "Estudiante"));
        }catch (FaltaDeRactivosExeption e) {
            System.out.printf(e.getMessage());
            hospital.agregarTest();
        }
        System.out.println();


        hospital.testear();

        hospital.aislar2();

        System.out.println("Personas Enfermas= "+ hospital.getEnfermos());
        System.out.println("Personas Sanas= "+hospital.getSanos());


        System.out.println();
        System.out.println(hospital);


        /** pasaje a Json */
        File archivo_enfermos = new File("ArchivoEnfermos.json");
        File archivo_sanos = new File("ArchivoSanos.json");

        ObjectMapper mapper = new ObjectMapper();

        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo_enfermos, hospital.getEnfermos());
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo_sanos, hospital.getSanos());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        /** pasaje a archivo*/


        try{
            BufferedWriter escribir = new BufferedWriter(new FileWriter("urgente.dat"));
            for(String e: hospital.getEnfermos()){
                escribir.write(e);
                escribir.newLine();

            }
            escribir.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}