package com.cardsui.pattern;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class PatternCardsActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        CardSampleFragment detailFragment = new CardSampleFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, detailFragment).commit();
	}
}
