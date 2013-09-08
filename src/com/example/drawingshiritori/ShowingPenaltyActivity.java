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
import android.view.KeyEvent;
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

	@Override
	/**
	 * 戻るボタンを無効化する
	 * (非 Javadoc)
	 * @see android.app.Activity#dispatchKeyEvent(android.view.KeyEvent)
	 */
	public boolean dispatchKeyEvent(KeyEvent event){
	    //画面から離れた場合
	    if(event.getAction()==KeyEvent.ACTION_UP){
	        //戻るボタンの場合
	        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
	            //trueを返して戻るのを無効化する
	            return true;
	        }
	    }
	    //通常通りの値を返す
	    return super.dispatchKeyEvent(event);
	}

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
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}