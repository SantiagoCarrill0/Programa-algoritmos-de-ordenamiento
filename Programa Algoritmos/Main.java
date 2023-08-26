import vista.Interfaz;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Interfaz interfaz = new Interfaz();
                interfaz.init();
            }
        });
    }
}
