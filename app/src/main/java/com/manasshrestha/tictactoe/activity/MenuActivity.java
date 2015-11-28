package com.manasshrestha.tictactoe.activity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.manasshrestha.tictactoe.Constants;
import com.manasshrestha.tictactoe.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Menu for selecting player name.
 */
public class MenuActivity extends AppCompatActivity {

    @Bind(R.id.et_player_one)
    EditText etPlayerOne;

    @Bind(R.id.et_player_two)
    EditText getEtPlayerTwo;

    @Bind(R.id.tv_tic_tac_toe)
    TextView tvTicTacToe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        ButterKnife.bind(this);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_anim);
        tvTicTacToe.startAnimation(animation);
    }

    @OnClick(R.id.btn_play)
    public void OnClick() {
        Constants.NAME_PLAYER_1 = etPlayerOne.getText().toString();
        Constants.NAME_PLAYER_2 = getEtPlayerTwo.getText().toString();

        startActivity(new Intent(this, MainActivity.class));
    }
}
