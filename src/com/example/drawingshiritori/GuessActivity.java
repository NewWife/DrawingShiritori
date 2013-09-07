package com.example.drawingshiritori;

import java.io.File;

import com.example.pictureshiritori.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class GuessActivity extends Activity
implements View.OnClickListener
{
	//グローバル変数
	Globals globals;
	MediaPlayer mp = null;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guess);		

		ImageView imgview = (ImageView)findViewById(R.id.guess_imageView);
		Button okButton = (Button)findViewById(R.id.guess_word_ok_button);
		okButton.setOnClickListener(this);

		//グローバル変数を取得
		globals = (Globals) this.getApplication();
				
		Bitmap imgBitmap = globals.loadBitmap(globals.imgPath);
		imgview.setImageBitmap(imgBitmap);


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
				mp = MediaPlayer.create(this, R.raw.seikai);
				mp.start();
				if(globals.failnum  == 0){//前に間違えた人がいない場合
				 //トーストで表示させる。
                Toast toast =
                Toast.makeText(getApplicationContext(),
                        String.format("正解！続きを描いて下さい！"),
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                globals.failnum = 0;
				//次の人へ
				Intent i = new Intent(this, DrawingActivity.class);
				startActivity(i);
				}else{//前に間違えた人がいる場合
					 //トーストで表示させる。
	                Toast toast =
	                Toast.makeText(getApplicationContext(),
	                        String.format("正解！"+"\n"+"これまで間違えた人全員罰ゲーム！"),
	                        Toast.LENGTH_LONG);
	                toast.setGravity(Gravity.CENTER, 0, 0);
	                toast.show();
					Intent i = new Intent(this, ShowingPenaltyActivity.class);
					startActivity(i);        
				}
					
			}else{
				//外した場合の処理
				mp = MediaPlayer.create(this, R.raw.fuseikai);
				mp.start();
		
				// 順番を次に飛ばして
				int next = (++globals.now) % globals.player;
				// 次のプレイヤーが描いた人と異なれば
				if(globals.drawer != globals.now)
				{
					 //トーストで表示させる。
	                Toast toast =
	                Toast.makeText(getApplicationContext(),
	                        String.format("不正解！次の人へパス"),
	                        Toast.LENGTH_SHORT);
	                toast.setGravity(Gravity.CENTER, 0, 0);
	                toast.show();
	                globals.failnum++;
					// パスした人を登録し
					globals.pather.add(next);
					globals.now = next;
					// 画面を切り替えて次の人へ
					Intent intent = new Intent(this, GuessActivity.class);
					startActivity(intent);
				}
				// 描いた人ならば
				else
				{
					// パスした人を全て除外し、
					globals.pather.clear();
					// 罰ゲーム通知画面に飛ばす
					Intent intent = new Intent(this, ShowingPenaltyActivity.class);
					 //トーストで表示させる。
	                Toast toast1 =
	                Toast.makeText(getApplicationContext(),
	                        String.format("不正解！絵を書いた人罰ゲーム！"),
	                        Toast.LENGTH_LONG);
	                toast1.setGravity(Gravity.CENTER, 0, 0);
	                toast1.show();
					startActivity(intent);
				}
			}	 

		}
	}


	@Override
	public void onClick(View v)
	{
		onClickOkButton();
	}
}


