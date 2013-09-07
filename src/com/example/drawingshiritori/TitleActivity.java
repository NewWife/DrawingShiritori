package com.example.drawingshiritori;

import com.example.pictureshiritori.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.Gravity;

public class TitleActivity extends Activity
implements OnClickListener
{
	//制限時間の配列
	private String[] timeLimit = {"60", "30", "10"};
	//プレイ人数の配列
	//private int[] player = {3,4,5,6,7,8,9,10};
	//グローバル変数
	Globals globals;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title);

		//グローバル変数を取得
				globals = (Globals) this.getApplication();
				//初期化
				globals.GlobalAllInit();

		//プルダウンメニューの設定
		//制限時間
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		                android.R.layout.simple_spinner_item,timeLimit);
        spinner.setAdapter(adapter);

        //プルダウンメニューで選択された時
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	@Override
        	            public void onItemSelected(AdapterView<?> parent,
        	                    View view, int position, long id) {
        	                Spinner spinner = (Spinner) parent;
        	                String item = (String) spinner.getSelectedItem();

        	                //グローバル変数に選択した制限時間を設定
        	                int stringToValue = Integer.parseInt(item);
        	                globals.limit = stringToValue;

        	                //トーストで表示させる。
        	                Toast toast =
        	                Toast.makeText(getApplicationContext(),
        	                        String.format("制限時間は%s秒です。", item),
        	                        Toast.LENGTH_SHORT);
        	                toast.setGravity(Gravity.CENTER, 0, 0);
        	                toast.show();

        	            }
        	            @Override
        	            public void onNothingSelected(AdapterView<?> parent) {
        	                Toast.makeText(getApplicationContext(),
        	                        "onNothingSelected", Toast.LENGTH_SHORT).show();
        	            }

		});


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
