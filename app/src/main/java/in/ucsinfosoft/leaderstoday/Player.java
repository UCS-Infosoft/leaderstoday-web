package in.ucsinfosoft.leaderstoday;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
/**
 * Created by Harjas on 04-01-2017.
 */

public class Player  extends AppCompatActivity {

    String value;
    VideoView v;
    MediaController mediaController;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        v = (VideoView)findViewById(R.id.myVideo);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
            //The key argument here must match that used in the other activity
            playvideo(value);
        }
        else
        {
            Toast.makeText(this,"invalid url",Toast.LENGTH_SHORT).show();
        }

    }

    public void playvideo(String videopath) {
        Log.e("entered", "playvideo");
        Log.e("path is", "" + videopath);
        try {
            progressDialog = ProgressDialog.show(Player.this, "",
                    "Buffering video...", false);
            progressDialog.setCancelable(true);
            getWindow().setFormat(PixelFormat.TRANSLUCENT);

            mediaController = new MediaController(Player.this);

            Uri video = Uri.parse(videopath);
            v.setMediaController(mediaController);
            v.setVideoURI(video);

            v.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    v.start();
                }
            });

        } catch (Exception e) {
            progressDialog.dismiss();
            System.out.println("Video Play Error :" + e.getMessage());
        }

    }
}
