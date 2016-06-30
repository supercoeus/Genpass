package com.feix.genpass;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText; 
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

import android.text.ClipboardManager;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText edtUsername = (EditText)findViewById(R.id.username);
		final EditText edtKeyinfo = (EditText)findViewById(R.id.keyinfo);
		final EditText edtPassword = (EditText)findViewById(R.id.password);
		final Button submit = (Button)findViewById(R.id.submit);
		final Boolean simple = new Boolean("false");
		
		submit.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				String concat = "strongpassword";
				String username = edtUsername.getText().toString();
				String keyinfo = edtKeyinfo.getText().toString();
				String password = edtPassword.getText().toString();
				String joinstr = username + concat + keyinfo + concat + password;
				try {
					MessageDigest md = MessageDigest.getInstance("SHA-256");
					md.update(joinstr.getBytes("UTF-8"));
					byte[] digest = md.digest();
					String result = "";
					for (int i = 0; i < digest.length; i++) {
						int tmp = (int)digest[i];
						if (tmp > 32 && tmp < 127 && tmp != 92 && tmp != 96 && tmp != 34 && tmp != 39) {
							if (simple != true) {
								result = (char)tmp + result;
							} else if ((tmp > 47 && tmp < 58) || (tmp > 64 && tmp < 91) || (tmp > 96 && tmp < 123)) {
								result = (char)tmp + result;
							}
						}
					}
					if (result.length() < 7) {
						result += result;
					} else if (result.length() > 14) {
						result = result.substring(result.length()-14, result.length());
					}
					ClipboardManager clipboard = (ClipboardManager)getSystemService(MainActivity.this.CLIPBOARD_SERVICE);
					clipboard.setText(result);
//					try {
//						Thread.sleep(3000);
//					} catch (interruptedException e) {
//						e.printStackTrac();
//					}
					Toast.makeText(MainActivity.this, "Password is in clipboard.", Toast.LENGTH_LONG).show();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void openSettings() {	}

	private void openAbout() { }

	private void openClear() {
		ClipboardManager clipboard = (ClipboardManager)getSystemService(MainActivity.this.CLIPBOARD_SERVICE);
		clipboard.setText("");
		Toast.makeText(MainActivity.this, "Clear clipboard.", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
			case R.id.action_settings:
				openSettings();
				return true;
			case R.id.action_about:
				openAbout();
				return true;
			case R.id.action_clear:
				openClear();
				return true;
			default:
				return super.onOptionsItemSelected(item);
        }
    }
}

