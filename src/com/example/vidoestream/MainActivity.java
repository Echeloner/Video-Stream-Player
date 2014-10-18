package com.example.vidoestream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {

	private static ProgressDialog progressDialog;
	// String videourl = "http://www.pocketjourney.com/"
	// + "downloads/pj/video/famous.3gp"; // It should be 3gp or mp4
	String videourl = "http://cdn.playwire.com/12917/video-20141017-1439126.mp4";
	VideoView videoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		videoView = (VideoView) findViewById(R.id.video_View);
		progressDialog = ProgressDialog.show(MainActivity.this, "",
				"Buffering video", true);
		progressDialog.setCancelable(false);
		playVideo();
	}

	private void playVideo() {
		try {
			getWindow().setFormat(PixelFormat.TRANSLUCENT);
			MediaController mediaController = new MediaController(
					MainActivity.this);
			mediaController.setAnchorView(videoView);

			Uri video = Uri.parse(videourl);
			videoView.setMediaController(mediaController);
			videoView.setVideoURI(video);
			videoView.requestFocus();
			videoView.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer arg0) {
					progressDialog.dismiss();
					videoView.start();

				}
			});

		} catch (Exception e) {
			progressDialog.dismiss();
			Toast.makeText(getApplicationContext(), "Video Play Error",
					Toast.LENGTH_LONG).show();
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
