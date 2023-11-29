package data;

import java.io.*;

public class PlayerTime {
    public int seconds = 0;
    private TimerTask timerTask;


    public void startTimer() {
        // Load the previous duration from the file
        loadDurationFromFile();

        timerTask = new TimerTask();
        Thread timerThread = new Thread(timerTask);
        timerThread.start();
    }

    public void resetTimer(){
        seconds = 0;
        try (FileWriter writer = new FileWriter("duration.txt")) {
            writer.write(formatDuration(seconds));
            System.out.println("Duration saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveTimer() {
        saveDurationToFile();
    }

    private void loadDurationFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("duration.txt"))) {
            String line = reader.readLine();
            if (line != null && line.matches("\\d+:\\d+:\\d+")) {
                String[] parts = line.split(":");
                seconds = Integer.parseInt(parts[0]) * 60 * 60 + // hours to seconds
                           Integer.parseInt(parts[1]) * 60 +      // minutes to seconds
                           Integer.parseInt(parts[2]);           // remaining seconds
                System.out.println("Previous duration loaded: " + formatDuration(seconds));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void saveDurationToFile() {
        try (FileWriter writer = new FileWriter("duration.txt")) {
            writer.write(formatDuration(seconds));
            System.out.println("Duration saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String formatDuration(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    public class TimerTask implements Runnable {
        private volatile boolean stop = false;

        @Override
        public void run() {
            while (!stop) {
                // Code to be executed when the timer triggers
                System.out.println("Time elapsed: " + formatDuration(seconds));
                seconds++;

                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopTimer() {
            stop = true;
        }
    }
}