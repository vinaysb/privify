package se.joscarsson.privify;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

class NotificationHelper {
    private Context context;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;

    NotificationHelper(Context context) {
        this.context = context;
        this.manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.builder = new NotificationCompat.Builder(context, "privify_progress");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("privify_progress", "Progress updates", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setSound(null, null);
            this.manager.createNotificationChannel(channel);
        }
    }

    void showEstimating() {
        this.builder
                .setProgress(100, 0, true)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_lock_white)
                .setContentTitle("Estimating");
        this.manager.notify(1, this.builder.build());
    }

    void showProcessing(int progress, boolean isEncrypted, String name) {
        this.builder
                .setProgress(100, progress, false)
                .setContentTitle(name)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_lock_white)
                .setContentText(isEncrypted ? "Decrypting" : "Encrypting");
        this.manager.notify(1, this.builder.build());
    }

    void showError() {
        this.builder
                .setProgress(0, 0, false)
                .setOngoing(false)
                .setSmallIcon(R.drawable.ic_error_outline_white)
                .setContentText("Failed to process file.");
        this.manager.notify(1, this.builder.build());
    }

    void hide() {
        this.manager.cancel(1);
    }

    void toast(String text) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show();
    }
}
