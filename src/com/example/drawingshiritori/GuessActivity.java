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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class GuessActivity extends Activity
implements View.OnClickListener
{
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
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
				}

			}else{
				//外した場合の処理
				mp = MediaPlayer.create(this, R.raw.fuseikai);
				mp.start();

				// 順番を次に飛ばして
				int next = (globals.now + 1) % globals.player;
				// 次のプレイヤーが描いた人と異なれば
				if(globals.drawer != next)
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
					globals.pather.add(globals.now);
					globals.now = next;
					// 画面を切り替えて次の人へ
					Intent intent = new Intent(this, GuessActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
				// 描いた人ならば
				else
				{
					// パスした人を全て除外し、
					globals.pather.clear();
					// 罰ゲーム通知画面に飛ばす
					Intent intent = new Intent(this, ShowingPenaltyActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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


