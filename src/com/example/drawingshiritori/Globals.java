package com.example.drawingshiritori;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;

public class Globals extends Application
{
	private final String TAG = "Globals";
	//グローバル変数に使用する変数

	//今何番目のプレイヤーか
	int now;
	//プレイヤー人数
	int player;
	//制限時間
	int limit;
	//何が描かれているのか
	String word;
	//絵へのパス
	String imgPath;
	// 描いた人
	int drawer;
	// パスした人
	ArrayList<Integer> pather;

	//全て初期化するメソッド
	public void GlobalAllInit(){
		now = 1;
		player = 3;
		limit = 60;
		word = "";
		imgPath = "";
		drawer = 1;
		imgPath = null;
		pather = new ArrayList<Integer>();

	}

	public void saveBitmap(String fileName, Bitmap bitmap)
	{
		try
		{
			if(!getFilesDir().exists()) getFilesDir().mkdir();
			FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
			bitmap.compress(CompressFormat.JPEG, 80, fos);
			fos.flush();
			fos.close();
		}
		catch (Exception e)
		{
			Log.i(TAG, e.getMessage());
			System.exit(-1);
		}
		Log.i(TAG, "Complete Bitmap File Output");
	}

	/**
	 * ローカルからの画像の読込関数
	 * @param fileName 読み込みたいファイル名
	 * @return bitmap Bitmap型
	 */
	private Bitmap loadBitmap(String fileName)
	{
		Bitmap bitmap = null;
		try
		{
			FileInputStream in = openFileInput(fileName);
			BufferedInputStream binput = new BufferedInputStream(in);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] w = new byte[1024];
			while(binput.read(w) >= 0)
			{
				out.write(w, 0, 1024);
			}
			byte[] byteData = out.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(byteData, 0, byteData.length);
			in.close();
			binput.close();
			out.close();
		}
		catch (Exception e)
		{
			Log.i(TAG, e.getMessage());
			System.exit(-1);
		}
		Log.i(TAG, "Complete Bitmap File Input");
		return bitmap;
	}
}
