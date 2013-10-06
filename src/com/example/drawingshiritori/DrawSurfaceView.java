package com.example.drawingshiritori;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class DrawSurfaceView extends SurfaceView
implements SurfaceHolder.Callback
{
	// 初期で描かれる色の設定
	private int DEFAULT_COLOR = Color.BLACK;

<<<<<<< Updated upstream

	// キャンバスの描画の有効・無効
	private boolean drawingDisable = false;

=======
>>>>>>> Stashed changes
	private SurfaceHolder mHolder;
	private Bitmap bitmap;

	private Canvas bmpCanvas;

	private Paint paint;

	private float px = -1;
	private float py = -1;

	private int WIDTH = 300;
	private int HEIGHT = 300;

	// ソースから生成されるときに呼び出される
	public DrawSurfaceView(Context context)
	{
		super(context);
		//path = new Path();
		getHolder().addCallback(this);

		// 背景色を白に設定
		setBackgroundColor(0xFFFFFFFF);

		// 線と色の設定
		paint = new Paint();
		paint.setColor(DEFAULT_COLOR);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(6);
		paint.setStrokeCap(Paint.Cap.SQUARE);
		paint.setStrokeJoin(Paint.Join.ROUND);
	}

	// XMLからの生成時のコンストラクタ
	public DrawSurfaceView(Context context, AttributeSet attr)
	{
		super(context, attr);
		//path = new Path();
		getHolder().addCallback(this);

		// 線と色の設定
		paint = new Paint();
		paint.setColor(DEFAULT_COLOR);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(6);
		paint.setStrokeCap(Paint.Cap.SQUARE);
		paint.setStrokeJoin(Paint.Join.ROUND);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{
		WIDTH = width;
		HEIGHT = height;
		mHolder = holder;
		paint.setColor(DEFAULT_COLOR);
		clearCanvas();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
	}

	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		if(drawingDisable) return true;
		switch(e.getAction())
		{
		case MotionEvent.ACTION_DOWN:
		{
			float x = e.getX();
			float y = e.getY();
			//drawListX.add();
			px = x;
			py = y;
		}
		case MotionEvent.ACTION_MOVE:
		{
			float x = e.getX();
			float y = e.getY();
			drawLine(x, y);
			px = x;
			py = y;
		}
		break;
		}
		return true;
	}

	public void drawLine(float nx, float ny)
	{
		Canvas canvas = mHolder.lockCanvas();
		if(bmpCanvas != null)
		{
			// current
			//bmpCanvas.drawPath(path, paint);
			bmpCanvas.drawLine(px, py, nx, ny, paint);

			canvas.drawBitmap(bitmap, 0, 0, null);
			mHolder.unlockCanvasAndPost(canvas);
			invalidate();
		}
	}

	public void clearCanvas()
	{
		bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
		// 裏でBitmapを操作するキャンバス
		bmpCanvas = new Canvas(bitmap);
		// 表示用のキャンバス
		Canvas canvas = mHolder.lockCanvas();
		// Bitmapを白で初期化する
		bmpCanvas.drawColor(Color.WHITE);
		// 裏書きを表示のキャンバスへコピーする
		canvas.drawBitmap(bitmap, 0, 0, null);
		mHolder.unlockCanvasAndPost(canvas);
		// 線を描画
		// drawLine();
		// キャンバスを更新する
		invalidate();
	}

	// 渡された画像をキャンバスにセットする関数
	public void setCanvas(Bitmap bitmap)
	{
		SurfaceHolder holder = getHolder();
		Canvas canvas = holder.lockCanvas();
		bmpCanvas = new Canvas(bitmap);
		canvas.drawBitmap(bitmap, 0, 0, null);
		holder.unlockCanvasAndPost(canvas);
	}

	// SurfaceView上で描画されているビットマップのインスタンスを返す
	public Bitmap getBitmap()
	{
		Bitmap copyBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
		return copyBitmap;
	}

	// 色を設定する
	public void setColor(int color, int width)
	{
		paint.setColor(color);
		paint.setStrokeWidth(width);
		Log.i("MemoWrite", Integer.toString(color));
	}

	// SurfaceViewの機能を無効にする
	public void disableDrawing()
	{
		drawingDisable = true;
	}

	// SurfaceViewの機能を有効にする
	public void enableDrawing()
	{
		drawingDisable = false;
	}
}
