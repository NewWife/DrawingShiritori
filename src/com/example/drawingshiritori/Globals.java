package com.example.drawingshiritori;
import android.app.Application;

public class Globals extends Application {
	//グローバル変数に使用する変数
	
	//今何番目のプレイヤーか
	int now;
	//プレイヤー人数
	int player;
	//制限時間
	int limit;
	
	//全て初期化するメソッド
	public void GlobalAllInit(){
		now = 1;
		player = 3;
		limit = 60;
	}
}
