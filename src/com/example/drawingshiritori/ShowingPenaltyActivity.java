package com.example.drawingshiritori;

//import android.R;
//import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ShowingPenaltyActivity extends Activity
{
	//private static final int NUMBER_OF_PENALTY = 20;
	//private static String[] penaluty;
	List<String> array = new ArrayList<String>();

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//罰ゲームの内容
		array.add("腹筋10回！");
		array.add("腕立て伏せ！");
		array.add("女装");
		array.add("一発芸");
		/*
		array.add("");//5
		array.add("");
		array.add("");
		array.add("");
		array.add("");
		array.add("");//10
		array.add("");
		array.add("");
		array.add("");
		array.add("");
		array.add("");//15
		array.add("");
		array.add("");
		array.add("");
		array.add("");
		array.add("");/20

		*/
		//罰ゲームの配列の要素をランダムに格納する
		Collections.shuffle(array);
		String penalty = array.get(0);

		//setContentView(R.layout.main);
		//TextView tv= (TextView)findViewById(R.id.penalty);
		TextView tv= new TextView(this);
		tv.setText(penalty);
		setContentView(tv);

	}

}