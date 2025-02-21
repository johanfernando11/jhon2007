public class ejercicio13 {
    public static void main(String[] args) {
        /*
         * dado un arreglo A de N elementos, almacemarlos elementos mayores y meneores
         * que la media, almacenarlo en arreglos diferentes.
         * 
         * analisi: necesitamos crear el arreglo de tamaño aleatorio y generar numeros 
         * aleatorios para que sean almacenados en el arreglo, posterirmente obtenemos 
         * la medida del arreglo y compramos cada posicion del arreglo para determinar 
         * si es mayor o menor a la media.
         */

        //n sera el numero aleatorio para el tamaño del arreglo 
        int n = (int) (Math.random() * (15 - 5 + 1)) + 15;
        //declara el arreglo 
        int[] a = new int[n];

        //llenara el arreglo con numeros aleatorios etre 0 a 50
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * (50 - 0 )) + 0;
            System.out.println(a[i] + " ");
        }
        //calcular la media del arreglo a
        // declarar una variable que almace la suma del arreglo 
        int sumarArreglo = 0;
        for (int i = 0; i < a.length; i++) {
            sumarArreglo += a [i];
        }
        double media = sumarArreglo / a.length;
        System.out.println("media del arreglo..." + media);

        // definir la cantidad de elemento que tiene el arreglo por encima de la media
        // y por debajo de la media
        int contadorMayorMedia = 0, contadorMenorMedia = 0; 
        for (int i = 0; i < a.length; i++) {
            if(a[i] >= media){
                contadorMayorMedia++;
            }else{
                contadorMenorMedia++;
            }
        }
        int[] mayores = new int[contadorMayorMedia];
        int[] menores = new int[contadorMenorMedia];
        int posMayores = 0, posMenores = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i] >= media){
                mayores[posMayores] = a[i];
                posMayores++;
            }else{
                menores[posMenores] = a[i];
                posMenores++;
            }
        }
        for (int i = 0; i < mayores.length; i++) {
            System.out.println(mayores[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < menores.length; i++) {
            System.out.println(menores[i] + " ");
        }
    }
}
