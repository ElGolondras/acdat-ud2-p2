import java.io.File;
import java.util.Scanner;

public class Main {

    /**
     * Programa base para la gestión de ficheros y directorios con java.io.File.
     *
     * TODO:
     * - Implementar menú principal en consola.
     * - Usar únicamente la clase File para las operaciones.
     * - Reutilizar la comprobación de existencia/tipo de la práctica anterior.
     * - Manejar errores y mensajes.
     */
    public static void main(String[] args) {
        // TODO: Inicializar lectura de entrada y, si procede, ruta base desde args.
        // TODO: Bucle del menú hasta seleccionar Salir.
        // TODO: Enrutar cada opción a su método correspondiente.
        // TODO: Mensaje final al salir.

        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        System.out.println("Programa de gestión de ficheros y directorios.");
        System.out.println("Funcionalidades a implementar:");
        System.out.println("1. Crear directorio");
        System.out.println("2. Eliminar directorio");
        System.out.println("3. Crear fichero");
        System.out.println("4. Eliminar fichero");
        System.out.println("5. Salir");

        while(opcion != 5){

            System.out.print("Seleccione una opción (1-5): ");
            opcion = sc.nextInt();

            switch(opcion){
                case 1:
                    crearDirectorio();
                    break;
                case 2:
                    eliminarDirectorio();
                    break;
                case 3:
                    crearFichero();
                    break;
                case 4:
                    eliminarFichero();
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        }


    }

    /**
     * Crear directorio.
     *
     * TODO:
     * - Pedir ruta del directorio.
     * - Validar entrada.
     * - Elegir mkdir o mkdirs.
     * - Mostrar éxito o motivo de fallo.
     */
    public static void crearDirectorio() {
        // TODO: Implementar según los puntos anteriores usando únicamente File.
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la ruta del directorio a crear: ");
        String ruta = sc.nextLine();
        File dir = new File(ruta);
        boolean err = true;
        if (dir.exists()) {
            System.out.println("El directorio ya existe");
        } else {
            if (dir.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                err = false;
            }
        }

        if (err == false) {
            System.out.println("No se pudo crear el directorio.");
        }
    }

    /**
     * Eliminar directorio.
     *
     * TODO:
     * - Pedir ruta y verificar exists e isDirectory.
     * - Intentar delete (solo si está vacío).
     * - Informar si no se puede eliminar.
     */
    public static void eliminarDirectorio() {
        // TODO: Implementar según los puntos anteriores usando únicamente File.
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la ruta del directorio a eliminar: ");
        String ruta = sc.nextLine();
        File dir = new File(ruta);
        if (!dir.exists()) {
            System.out.println("El directorio no existe");
        } else if (!dir.isDirectory()) {
            System.out.println("La ruta no es un directorio");
        } else {
            if (dir.delete()) {
                System.out.println("Directorio eliminado");
            } else {
                System.out.println("No se pudo eliminar el directorio, asegúrese de que esté vacío y tenga los permisos necesarios");
            }
        }
    }

    /**
     * Crear fichero.
     *
     * TODO:
     * - Pedir ruta completa del fichero.
     * - Verificar si existe.
     * - Crear con createNewFile controlando posibles excepciones.
     * - Si el directorio padre no existe, decidir si crearlo.
     */
    public static void crearFichero() {
        // TODO: Implementar según los puntos anteriores usando únicamente File.
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la ruta completa del fichero a crear: ");
        String ruta = sc.nextLine();
        File file = new File(ruta);
        if (file.exists()) {
            System.out.println("El fichero ya existe");
        } else {
            try {
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    System.out.print("El directorio padre no existe. ¿Desea crearlo? (s/n): ");
                    String confirmacion = sc.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        if (!parentDir.mkdirs()) {
                            System.out.println("No se pudo crear el directorio padre, verifique la ruta y los permisos");
                            return;
                        }
                    } else {
                        System.out.println("Creación de fichero cancelada");
                        return;
                    }
                }
                if (file.createNewFile()) {
                    System.out.println("Fichero creado");
                } else {
                    System.out.println("No se pudo crear el fichero, verifique los permisos");
                }
            } catch (Exception e) {
                System.out.println("Error al crear el fichero: " + e.getMessage());
            }
        }
    }

    /**
     * Eliminar fichero.
     *
     * TODO:
     * - Pedir ruta y verificar exists() e isFile().
     * - Confirmar con el usuario antes de borrar.
     * - Eliminar con delete y mostrar resultado.
     */
    public static void eliminarFichero() {
        // TODO: Implementar según los puntos anteriores usando únicamente File.
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la ruta del fichero a eliminar: ");
        String ruta = sc.nextLine();
        File file = new File(ruta);
        if (!file.exists()) {
            System.out.println("El fichero no existe");
        } else if (!file.isFile()) {
            System.out.println("La ruta no es un fichero");
        } else {
            System.out.print("¿Está seguro de que desea eliminar el fichero? (s/n): ");
            String confirmacion = sc.nextLine();
            if (confirmacion.equalsIgnoreCase("s")) {
                if (file.delete()) {
                    System.out.println("Fichero eliminado");
                } else {
                    System.out.println("No se pudo eliminar el fichero, verifique los permisos");
                }
            } else {
                System.out.println("Eliminación cancelada");
            }
        }
    }
}