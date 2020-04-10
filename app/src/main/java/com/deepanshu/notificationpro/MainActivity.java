package com.deepanshu.notificationpro;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

public class MainActivity extends AppCompatActivity {
    private final String Channel_id = "personal_notification";
    public static int NOTIFICATION_id = 001;
    public static final String TXT_reply = "text_reply";
    String replyLabel = "Enter your reply here";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void dispalayNotificaton(View View) {
        RemoteInput remoteInput;
        createNOtificationChannerl();
       /* Intent LandingIntent=new Intent(this,LandingActivity.class);
        LandingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent landingPendingIntent=PendingIntent.getActivity(this,0,LandingIntent,PendingIntent.FLAG_ONE_SHOT);

       /* Intent yesIntent=new Intent(this,YesActivity.class);
        yesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent yesLandingInten=PendingIntent.getActivity(this,0,yesIntent,PendingIntent.FLAG_ONE_SHOT);

        Intent NoIntent=new Intent(this,NoActivtity.class);
        NoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent NoLandingInten=PendingIntent.getActivity(this,0,NoIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,Channel_id);
        builder.setSmallIcon(R.drawable.ic_sms_black_24dp);
        builder.setContentTitle("Simple Notification");
        builder.setContentText("this is a simple notification..");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);//to cancep the message form above
        builder.setAutoCancel(true);
       // builder.setContentIntent(landingPendingIntent);//to call otehr activity
       /* builder.addAction(R.drawable.ic_sms_black_24dp,"Yes",yesLandingInten);
        builder.addAction(R.drawable.ic_sms_black_24dp,"No",NoLandingInten);
       */

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel_id);
        builder.setSmallIcon(R.drawable.ic_arrow_downward_black_24dp);
        builder.setContentTitle("Image download");
        builder.setContentText("download in progress ..");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);//to cancep the message form above
       // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            remoteInput = new RemoteInput.Builder(TXT_reply).setLabel(replyLabel).build();
            Intent replyInten = new Intent(this, ReviewActivity.class);
            replyInten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent replyPendeingIntent = PendingIntent.getActivity(this, 0, replyInten, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_sms_black_24dp,
                    "Reply", replyPendeingIntent).addRemoteInput(remoteInput)
                    .setAllowGeneratedReplies(true)
            .build();
            builder.addAction(action);
       // }
        /*final int max_progress = 100;
        int current_progress = 0;
        //builder.setProgress(max_progress,current_progress,false);//f for determeinatne
        builder.setProgress(0, current_progress, true);//f for determeinatne
        final NotificationManagerCompat compat = NotificationManagerCompat.from(this);
        compat.notify(NOTIFICATION_id, builder.build());
        Thread thread = new Thread() {
            @Override
            public void run() {
                int count = 0;
                try {
                    while (count <= 100) {
                        count = count + 10;
                        sleep(1000);
                        //builder.setProgress(max_progress, count, false);//for update
                        //compat.notify(NOTIFICATION_id, builder.build());
                    }
                    builder.setContentText("download complete");
                    builder.setProgress(0, 0, false);//
                    //remove the progress from
                    //to update teh otificaiton
                    compat.notify(NOTIFICATION_id, builder.build());

                } catch (Exception e) {

                }

            }
        };
        thread.start();*/
       /* Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.balll);
        //to add the imagae at the thumbnail
        builder.setLargeIcon(bitmap);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));*///for the image show in notification

        //builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Text)));
        //Create Notification.



       NotificationManager compat=    (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);//NotificationManagerCompat.from(this);
        compat.notify(NOTIFICATION_id,builder.build());
        builder.setAutoCancel(true);
    }

    private void createNOtificationChannerl() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Personal NOtification";
            String description = "include allthe personal notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            //create notification nchannel
            NotificationChannel notificationChannel = new NotificationChannel(Channel_id, name, importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
