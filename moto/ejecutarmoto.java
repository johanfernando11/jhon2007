public class ejecutarmoto {
    public static void main(String[] args) {
        moto [] m = new moto[8];
        m[0] = new moto(2020, "Yamaha", "Rojo", 150.0, 5000.0);
        m[1] = new moto(2021, "Honda", "Negro", 125.0, 4500.0);
        m[2] = new moto(2019, "Suzuki", "Blanco", 175.0, 6000.0);
        m[3] = new moto(2022, "Kawasaki", "Azul", 300.0, 8500.0);
        m[4] = new moto(2018, "Harley-Davidson", "Gris", 180.0, 9500.0);
        m[5] = new moto(2023, "Ducati", "Verde", 999.9, 15555.99);
        m[6] = new moto(2017, "Triumph", "Morado", 675.4, 7888.77);
        m[7] = new moto(2024, "KTM", "Amarillo", 399.8, 6666.66);

        int suma = 0;
        for (int i = 0; i < m.length; i++) {
            suma += m[i].getmodelo();
        }
        System.out.println("el promedio de los modelos de las motos del arreglo es: " + (suma / m.length));

        String cad = "";
        for (int i = 0; i < m.length; i++) {
            if(m[i].getprecio() <= 20000.0){
                cad += "-" + m[i].getmarca() + " " + m[i].getprecio() + "\n";
            }
        }
        System.out.println("las motos que puedo comprar con mi presupuesto son:" + cad);
    }
}