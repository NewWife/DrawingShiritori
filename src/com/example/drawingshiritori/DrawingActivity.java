package com.example.drawingshiritori;

import com.example.pictureshiritori.R;

import android.app.Activity;
import android.content.Intent;
<<<<<<< Updated upstream
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
=======
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
>>>>>>> Stashed changes
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
<<<<<<< Updated upstream
import android.widget.Toast;

=======
>>>>>>> Stashed changes

public class DrawingActivity extends Activity
implements OnClickListener
{
<<<<<<< Updated upstream

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

	/**
	 * カウントダウン機能を提供するためのクラス
	 *
	 */
	public class MyCountDownTimer extends CountDownTimer
	{
		private TextView timerView;

		public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView timerView)
		{
			super(millisInFuture, countDownInterval);
			this.timerView = timerView;
		}

		@Override
		public void onFinish()
		{
			// カウントダウン完了後に呼ばれる
			timerView.setText("0.00");
			// 現在描いている人を罰ゲームの対象にして罰ゲーム表示画面へ
			globals.pather.add(globals.now);
			Intent intent = new Intent(DrawingActivity.this, ShowingPenaltyActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			myCountDownTimer.cancel();
			startActivity(intent);
		}

		@Override
		public void onTick(long millisUntilFinished) {

			// インターバル(countDownInterval)毎に呼ばれる
			timerView.setText(Long.toString(millisUntilFinished/1000%60) + ":" + String.format("%02d", (millisUntilFinished / 10)%100));
		}

	}

	//
	private final String IMAGE_FILE_NAME = "tmp.jpg";

	// 【DEBUG】
	private final String LOG = "DrawingActivity";
	// 【DEBUG】テンプレートのお題をこちらで決める
	private final String[] TEMPLATE_THEME_WORD = {"とけい", "ごりら", "らっぱ", "こっぷ", "はんが"};

=======
	// 【DEBUG】
	private final String LOG = "DrawingActivity";
	// 【DEBUG】テンプレートのお題をこちらで決める
	private final String[] TEMPLATE_THEME_WORD = {"とけい", "ゴリラ", "ラッパ", "コップ", "ハンガー"};
	// 【DEBUG】制限時間をこちらで決める
	private final int LIMIT_TIME = 60;
>>>>>>> Stashed changes

	// ペンの太さ、消しゴムの太さをこちらで設定
	private final int PEN_WIDTH = 5, ERASER_WIDTH = 20;

	// お絵かきビュー
	private DrawSurfaceView drawSurfaceView;
<<<<<<< Updated upstream
	// カウントダウンをするカスタムクラス
	private EditText wordEditText;
	private MyCountDownTimer myCountDownTimer;
	private boolean isFirst;
	private boolean isInputedWord;

	// お題の格納する変数
	private String preWord;

	private Globals globals;

=======
	private boolean isFirst;
	private boolean isInputedWord;
>>>>>>> Stashed changes
	private String themeWord;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawing);

<<<<<<< Updated upstream

		globals = (Globals)this.getApplication();

		// get layout content
		wordEditText = (EditText)findViewById(R.id.drawing_word_edit_text);
		drawSurfaceView = (DrawSurfaceView)findViewById(R.id.draw_surface_view);
		drawSurfaceView.disableDrawing();


=======
		// get layout content
		drawSurfaceView = (DrawSurfaceView)findViewById(R.id.draw_surface_view);
>>>>>>> Stashed changes
		Button okButton = (Button)findViewById(R.id.drawing_word_ok_button);
		okButton.setOnClickListener(this);
		Button nextButton = (Button)findViewById(R.id.drawing_next_button);
		nextButton.setOnClickListener(this);
		ImageButton clearButton = (ImageButton)findViewById(R.id.drawing_clear_button);
		clearButton.setOnClickListener(this);
		ImageButton eraserButton = (ImageButton)findViewById(R.id.drawing_eraser_button);
		eraserButton.setOnClickListener(this);
		ImageButton penButton = (ImageButton)findViewById(R.id.drawing_pen_button);
		penButton.setOnClickListener(this);

<<<<<<< Updated upstream
		TextView remainTextView = (TextView)findViewById(R.id.drawing_remain_time_text_view);

		// カウントダウンタイマーを指定の時間で初期化する
		myCountDownTimer = new MyCountDownTimer(globals.limit * 1000, 10, remainTextView);





=======
>>>>>>> Stashed changes
		// 一番始めかどうかを調べる
		Intent intent = getIntent();
		isFirst = intent.getBooleanExtra("isFirst", false);

<<<<<<< Updated upstream

		// 始めてならば
		if(isFirst)
		{
			// テーマを自動的に生成する
			preWord = generateThemeWord();
			// テーマをセットする
			wordEditText.setText(preWord);
			// 単語入力の無効化を行う
			disableWordInput();
		}else{
			String tailstr = globals.word.substring(globals.word.length()-1);
				wordEditText.setText(tailstr);
			}
			
		

=======
		// 始めならば
		if(isFirst)
		{
			// テーマを自動的に生成する
			themeWord = generateThemeWord();
			// ボタンを見えなくする
			okButton = (Button)findViewById(R.id.drawing_word_ok_button);
			// お題を入力し、エディット出来なくする
			EditText editText = (EditText)findViewById(R.id.drawing_word_edit_text);
			editText.setText(themeWord);
			editText.setEnabled(false);
			editText.setFocusable(false);
		}
>>>>>>> Stashed changes
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// 単語の入力欄に対してOKを押した場合
		case R.id.drawing_word_ok_button:
		{
<<<<<<< Updated upstream
			onClickOkButton();
=======

>>>>>>> Stashed changes
			break;
		}
		// 次の画面へ移動する
		case R.id.drawing_next_button:
		{

<<<<<<< Updated upstream
			onClickNextButton();

=======
>>>>>>> Stashed changes
			break;
		}
		// クリアボタンが押された場合
		case R.id.drawing_clear_button:
		{
			drawSurfaceView.clearCanvas();
			break;
		}
		// 消しゴムボタンが押された場合
		case R.id.drawing_eraser_button:
		{
			drawSurfaceView.setColor(Color.WHITE, ERASER_WIDTH);
<<<<<<< Updated upstream

			Log.d(LOG, "push erase button");

=======
			Log.d(LOG, "push erase button");
>>>>>>> Stashed changes
			break;
		}
		// 鉛筆ボタンが押された場合
		case R.id.drawing_pen_button:
		{
			drawSurfaceView.setColor(Color.BLACK, PEN_WIDTH);
			break;
		}
		default:
			break;
		}
	}

<<<<<<< Updated upstream
	/**
	 * 書くお題に対してOKボタンを押した時の
	 */
	private void onClickOkButton()
	{
		String inputWord = wordEditText.getText().toString();


		// 既に単語を入力していた場合
		// バリデーションに引っかかった場合
		// 無効にする
		if(isInputedWord || !validation(inputWord)) return;

		// タイマーを起動し、各種UIを触れないようにする
		myCountDownTimer.start();
		disableWordInput();
		disableOkButton();
		drawSurfaceView.enableDrawing();
		Toast.makeText(this, "start drawing", Toast.LENGTH_SHORT).show();
		isInputedWord = true;
	}

	/**
	 * 入力された文字のバリデーションを行う
	 */
	private boolean validation(String str)
	{
		final String MATCH_HIRAGANA = "^[\\u3040-\\u309F]+$";
		final String MATCH_SOUND_MARK = "\u30FC";
		// 文字が入力されているか
		if(str.length() == 0)
		{
			Toast.makeText(this, "書く文字を入力して下さい", Toast.LENGTH_SHORT).show();
			return false;
		}
		// ひらがなんみで入力されているか
		String tailstr = str.substring(str.length()-1);
		if(!str.matches(MATCH_HIRAGANA))
		{
			//一番後ろに横棒が付いている場合
			if(tailstr.equals(MATCH_SOUND_MARK))
				return true;
			
			Toast.makeText(this, "ひらがなのみで入力して下さい", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	/**
	 * 次へボタンを押した時の動作定義
	 */
	private void onClickNextButton()
	{
		// 単語を入力していなかった場合
		if(!isInputedWord) return;

		// 画像の保存
		Bitmap bitmap = drawSurfaceView.getBitmap();
		globals.saveBitmap(IMAGE_FILE_NAME, bitmap);
		globals.imgPath = IMAGE_FILE_NAME;

		// 現在の人を絵を描いた人に設定
		globals.drawer = globals.now;
		// 順番を次に飛ばして
		int next = (++globals.now) % globals.player;
		globals.now = next;
		globals.word = wordEditText.getText().toString();
		// 画面を切り替えて次の人移り、答え予測画面へ
		Intent intent = new Intent(DrawingActivity.this, GuessActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		myCountDownTimer.cancel();
		startActivity(intent);
		}


	/**
=======
	private void onClickOkButton()
	{

	}

	/**
>>>>>>> Stashed changes
	 * 自動的にお題を生成する関数
	 */
	private String generateThemeWord()
	{
		int r = (int)(Math.random() * TEMPLATE_THEME_WORD.length);
		return TEMPLATE_THEME_WORD[r];
<<<<<<< Updated upstream
	}

	/**
	 *  お題を入力した後、エディット出来なくする
	 */
	private void disableWordInput()
	{
		wordEditText.setFocusable(false);

	}

	/**
	 *  OKボタンの無効化
	 */
	private void disableOkButton()
	{
		// ボタンを見えなくする
		Button okButton = (Button)findViewById(R.id.drawing_word_ok_button);
		okButton.setVisibility(View.GONE);

=======
>>>>>>> Stashed changes
	}
}
