package com.example.drawingshiritori;

import com.example.pictureshiritori.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class DrawingActivity extends Activity
implements OnClickListener
{
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
	private boolean isFirst;
	private boolean isInputedWord;
	private String themeWord;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawing);

		// get layout content
		drawSurfaceView = (DrawSurfaceView)findViewById(R.id.draw_surface_view);
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

		// 一番始めかどうかを調べる
		Intent intent = getIntent();
		isFirst = intent.getBooleanExtra("isFirst", false);

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
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// 単語の入力欄に対してOKを押した場合
		case R.id.drawing_word_ok_button:
		{

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
			Log.d(LOG, "push erase button");
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

	}

	/**
	 * 自動的にお題を生成する関数
	 */
	private String generateThemeWord()
	{
		int r = (int)(Math.random() * TEMPLATE_THEME_WORD.length);
		return TEMPLATE_THEME_WORD[r];
	}
}
