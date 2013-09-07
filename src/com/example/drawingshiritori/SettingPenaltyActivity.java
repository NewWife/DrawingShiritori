package com.example.drawingshiritori;

import com.example.pictureshiritori.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class SettingPenaltyActivity extends Activity
implements OnClickListener
{
	private final int[] spinnerIds = {
			R.id.setting_penalty_pulldown_spinner_1,
			R.id.setting_penalty_pulldown_spinner_2,
			R.id.setting_penalty_pulldown_spinner_3,
			R.id.setting_penalty_pulldown_spinner_4,
			R.id.setting_penalty_pulldown_spinner_5
	};
	private String[] selectedSpinnerItem = new String[5];
	private ListView listView;
	private Globals globals;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_penalty);

		// Globalの取得
		globals = (Globals)this.getApplication();

		// ボタンの設定
		Button button = (Button)findViewById(R.id.setting_penalty_return_button);
		button.setOnClickListener(this);

		// スピナーの設定
		Spinner spinner0 = (Spinner)findViewById(spinnerIds[0]);
		ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, globals.PENALTY_CONTENTS);
		selectedSpinnerItem[0] = globals.PENALTY_CONTENTS[0];
		spinner0.setAdapter(adapter0);
		spinner0.setSelection(0);
		spinner0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				Spinner spinner = (Spinner) parent;
				selectedSpinnerItem[0] = (String) spinner.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});

		Spinner spinner1 = (Spinner)findViewById(spinnerIds[1]);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, globals.PENALTY_CONTENTS);
		selectedSpinnerItem[1] = globals.PENALTY_CONTENTS[1];
		spinner1.setAdapter(adapter1);
		spinner1.setSelection(1);
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				Spinner spinner = (Spinner) parent;
				selectedSpinnerItem[1] = (String) spinner.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});

		Spinner spinner2 = (Spinner)findViewById(spinnerIds[2]);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, globals.PENALTY_CONTENTS);
		selectedSpinnerItem[2] = globals.PENALTY_CONTENTS[2];
		spinner2.setAdapter(adapter2);
		spinner2.setSelection(2);
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				Spinner spinner = (Spinner) parent;
				selectedSpinnerItem[2] = (String) spinner.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});

		Spinner spinner3 = (Spinner)findViewById(spinnerIds[3]);
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, globals.PENALTY_CONTENTS);
		selectedSpinnerItem[3] = globals.PENALTY_CONTENTS[3];
		spinner3.setAdapter(adapter3);
		spinner3.setSelection(3);
		spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				Spinner spinner = (Spinner) parent;
				selectedSpinnerItem[3] = (String) spinner.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}

		});

		Spinner spinner4 = (Spinner)findViewById(spinnerIds[4]);
		ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, globals.PENALTY_CONTENTS);
		selectedSpinnerItem[4] = globals.PENALTY_CONTENTS[4];
		spinner4.setAdapter(adapter4);
		spinner4.setSelection(4);
		spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				Spinner spinner = (Spinner) parent;
				selectedSpinnerItem[4] = (String) spinner.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}

		});

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.setting_penalty_return_button:
		{
			onClickReturnButton();
			break;
		}
		default:
			break;
		}
	}

	private void onClickReturnButton()
	{
		for(int i = 0, n = globals.penaltys.length; i < n; ++i)
		{
			globals.penaltys[i] = selectedSpinnerItem[i];
		}
		finish();
	}
}
