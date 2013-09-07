package com.example.drawingshiritori;

import java.io.File;

import com.example.pictureshiritori.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class GuessActivity extends Activity
implements View.OnClickListener
{
	//グローバル変数
	Globals globals;
	
	public void onCreate(Bundle savedInstanceState)
	{
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guess);
		
		ImageView imgview = (ImageView)findViewById(R.id.guess_imageView);
		Button okButton = (Button)findViewById(R.id.guess_word_ok_button);
		okButton.setOnClickListener(this);
		
		//グローバル変数を取得
		globals = (Globals) this.getApplication();
		/*		
		//ファイルのパスから絵を取得し、画像を表示させる。
		File imgfile = new File(globals.imgPath);
		if(imgfile.exists()){
		Bitmap imgBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());
		imgview.setImageBitmap(imgBitmap);
		}
						*/
		
				
	}

	
	private void onClickOkButton()
	{
		//グローバル変数を取得
		globals = (Globals) this.getApplication();
		//何が描かれているのか取得
		String trueWords = globals.word; 
		
		EditText edittext = (EditText)findViewById(R.id.guess_word_edit_text);
		if(!edittext.getText().toString().equals("")){
		 SpannableStringBuilder sb = (SpannableStringBuilder)edittext.getText();
	     String str = sb.toString();
	     if(str.equals(trueWords)){
	    	 //正解した場合の処理
	    	 //次の人へ
	    	 Intent i = new Intent(this, DrawingActivity.class);
	    	 startActivity(i);
	     }else{
	    	 //失敗した場合の処理
	    	 //罰ゲーム表示画面へ飛ぶ
	    	 Intent i = new Intent(this, ShowingPenaltyActivity.class);
	    	 startActivity(i);
	     }
	     
	    }
	    	 
	     
	}

	@Override
	public void onClick(View v)
	{
		onClickOkButton();
	}
}
	

