package com.example.drawingshiritori;
import java.util.ArrayList;

import android.app.Application;

public class Globals extends Application {
	//グローバル変数に使用する変数

	//今何番目のプレイヤーか
	int now;
	//プレイヤー人数
	int player;
	//制限時間
	int limit;
	// 描いた人
	int drawer;
	// パスした人
	ArrayList<Integer> pather;

	//全て初期化するメソッド
	public void GlobalAllInit(){
		now = 1;
		player = 3;
		limit = 60;
		drawer = 1;
		pather = new ArrayList<Integer>();
	}
}
