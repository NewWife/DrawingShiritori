package com.example.drawingshiritori;

import com.example.pictureshiritori.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TitleActivity extends Activity
implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title);

		// Buttonを取得
		Button startButton = (Button)findViewById(R.id.start_title_button);
		startButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view)
	{
		switch(view.getId())
		{
		case R.id.start_title_button:
		{
			Intent intent = new Intent(TitleActivity.this, DrawingActivity.class);
			intent.putExtra("isFirst", true);
			startActivity(intent);
		}
		break;
		case R.id.setting_penalty_title_button:
		{
			Intent intent = new Intent(TitleActivity.this, SettingPenaltyActivity.class);
			startActivity(intent);
		}
		break;
		}
	}
}
