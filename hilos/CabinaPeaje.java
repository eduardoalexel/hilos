package hilos;

import java.util.Random;

public class CabinaPeaje implements Runnable {

    // Variable global compartida, SIN synchronized, volatile ni atomic (según el requisito)
    public static int totalRecaudadoGlobal = 0;

    private final String nombre;          // Nombre/ID de la cabina
    private int vehiculosAtendidos = 0;   // Contador individual
    private final int vehiculosAsignados; // Vehículos que le tocan a esta cabina
    private final Random random = new Random();

    public CabinaPeaje(String nombre, int vehiculosAsignados) {
        this.nombre = nombre;
        this.vehiculosAsignados = vehiculosAsignados;
    }

    @Override
    public void run() {
        for (int i = 1; i <= vehiculosAsignados; i++) {
            try {
                // Simula el cobro: entre 500 y 1500 ms
                Thread.sleep(500 + random.nextInt(1001));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            vehiculosAtendidos++;
            totalRecaudadoGlobal += 50;
            System.out.println(nombre + " cobro al vehiculo #" + i);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getVehiculosAtendidos() {
        return vehiculosAtendidos;
    }
}