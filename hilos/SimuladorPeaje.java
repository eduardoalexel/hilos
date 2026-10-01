package hilos;

public class SimuladorPeaje {

    public static void main(String[] args) {
        final int TOTAL_VEHICULOS = 30;
        final int NUM_CABINAS = 3;
        int porCabina = TOTAL_VEHICULOS / NUM_CABINAS; // 10 vehículos cada una

        CabinaPeaje[] cabinas = {
            new CabinaPeaje("Cabina Bernabe", porCabina),
            new CabinaPeaje("Cabina 2", porCabina),
            new CabinaPeaje("Cabina 3", porCabina)
        };

        Thread[] hilos = new Thread[NUM_CABINAS];
        for (int i = 0; i < NUM_CABINAS; i++) {
            hilos[i] = new Thread(cabinas[i]);
            hilos[i].start();
        }

        // Esperar a que todas las cabinas terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n===== REPORTE FINAL =====");
        for (CabinaPeaje c : cabinas) {
            System.out.println(c.getNombre() + ": " + c.getVehiculosAtendidos() + " vehiculos");
        }
        System.out.println("Total recaudado global: $" + CabinaPeaje.totalRecaudadoGlobal);
    }
}