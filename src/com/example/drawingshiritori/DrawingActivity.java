package com.example.drawingshiritori;

import com.example.pictureshiritori.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DrawingActivity extends Activity
implements OnClickListener
{
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
		}

		@Override
		public void onTick(long millisUntilFinished) {

			// インターバル(countDownInterval)毎に呼ばれる
			timerView.setText(Long.toString(millisUntilFinished/1000%60) + ":" + String.format("%02d", (millisUntilFinished / 10)%100));
		}

	}

	// 【DEBUG】
	private final String LOG = "DrawingActivity";
	// 【DEBUG】テンプレートのお題をこちらで決める
	private final String[] TEMPLATE_THEME_WORD = {"とけい", "ゴリラ", "ラッパ", "コップ", "ハンガー"};
	// 【DEBUG】制限時間をこちらで決める
	private final int LIMIT_TIME = 60;

	// ペンの太さ、消しゴムの太さをこちらで設定
	private final int PEN_WIDTH = 5, ERASER_WIDTH = 20;

	// お絵かきビュー
	private DrawSurfaceView drawSurfaceView;
	// カウントダウンをするカスタムクラス
	private EditText wordEditText;
	private MyCountDownTimer myCountDownTimer;
	private boolean isFirst;
	private boolean isInputedWord;

	// お題の格納する変数
	private String preWord;

	private Globals globals = (Globals)this.getApplication();

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawing);

		// get layout content
		wordEditText = (EditText)findViewById(R.id.drawing_word_edit_text);
		drawSurfaceView = (DrawSurfaceView)findViewById(R.id.draw_surface_view);
		drawSurfaceView.disableDrawing();

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
		TextView remainTextView = (TextView)findViewById(R.id.drawing_remain_time_text_view);

		// カウントダウンタイマーを指定の時間で初期化する
		myCountDownTimer = new MyCountDownTimer(LIMIT_TIME * 1000, 10, remainTextView);

		// 一番始めかどうかを調べる
		Intent intent = getIntent();
		isFirst = intent.getBooleanExtra("isFirst", false);

		// 始めてならば
		if(isFirst)
		{
			// テーマを自動的に生成する
			preWord = generateThemeWord();
			// 単語入力の無効化を行う
			disableWordInput();
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// 単語の入力欄に対してOKを押した場合
		case R.id.drawing_word_ok_button:
		{
			onClickOkButton();
			break;
		}
		// 次の画面へ移動する
		case R.id.drawing_next_button:
		{

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

	private void onClickOkButton()
	{
		// 前回の絵を当てられていれば
		String inputWord = wordEditText.getText().toString();
		if(inputWord.equals(preWord) && !isInputedWord)
		{
			myCountDownTimer.start();
			disableWordInput();
			disableOkButton();
			drawSurfaceView.enableDrawing();
			Toast.makeText(this, "start drawing", Toast.LENGTH_SHORT).show();
		}
		// 外した場合
		else
		{
			Intent intent = new Intent();
		}
		isInputedWord = true;
	}

	/**
	 * 自動的にお題を生成する関数
	 */
	private String generateThemeWord()
	{
		int r = (int)(Math.random() * TEMPLATE_THEME_WORD.length);
		return TEMPLATE_THEME_WORD[r];
	}

	// お題を入力し、エディット出来なくする
	private void disableWordInput()
	{
		wordEditText.setText(preWord);
		wordEditText.setEnabled(false);
		wordEditText.setFocusable(false);

	}

	// OKボタンの無効化
	private void disableOkButton()
	{
		// ボタンを見えなくする
		Button okButton = (Button)findViewById(R.id.drawing_word_ok_button);
		okButton.setVisibility(View.GONE);
	}
}
