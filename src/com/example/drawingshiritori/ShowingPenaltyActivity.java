package com.example.drawingshiritori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.pictureshiritori.R;

import android.R.anim;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ShowingPenaltyActivity extends Activity
implements OnClickListener
{
	Globals globals;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showing_penalty);

		globals = (Globals)this.getApplication();

		TextView textView = (TextView)findViewById(R.id.showwing_penalty_text_view);
		int r = (int)(Math.random() * globals.penaltys.length);
		String penaltyText = globals.penaltys[r];
		textView.setText(penaltyText);

		Button button = (Button)findViewById(R.id.showwing_penalty_button);
		button.setOnClickListener(this);
	}

	public void onClick(View v)
	{
		Intent intent = new Intent(ShowingPenaltyActivity.this, TitleActivity.class);
		startActivity(intent);
	}
}