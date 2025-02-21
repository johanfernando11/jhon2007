public class creacionarreglos {
    public static void main(String[] args) {
        
        //creacion del arreglo
        int[] a = { 3, 6, 5, 10, 20, 1, 4, 9 };

        //recorrer y mostrar los elementos del arreglo
        //a.length -> obtiene la longitud o tamaño del arreglo 
        for (int i = 0; i < a.length; i++){
            System.out.println("a[" + i + "]=" + a[i]);
        }
    }
}