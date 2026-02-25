public class LiveUpdater extends Thread {

    private SmartParkGUI gui;
    private boolean running = true;

    public LiveUpdater(SmartParkGUI gui) {
        this.gui = gui;
    }

    public void stopUpdater() {
        running = false;
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(5000);

                javax.swing.SwingUtilities.invokeLater(() -> {
                    gui.refreshUI();
                });

            } catch (InterruptedException e) {
                break;
            }
        }
    }
}