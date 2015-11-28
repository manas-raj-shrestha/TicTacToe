package com.manasshrestha.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Manas on 11/9/2015.
 */
public class BoxGroup extends RelativeLayout implements BoxView.SendTouchEvents {

    public static ArrayList<String[]> winConditions = new ArrayList<>();
    ArrayList<String> playerOneMoves = new ArrayList<>();
    ArrayList<String> playerTwoMoves = new ArrayList<>();
    public static Boolean playerOneTurn = true;
    public static Boolean winStatus = false;
    private String winMode = "";
    private int noOfMoves = 0;
    private String winnerPlayer;
    private ArrayList<TextView> playerTvs = new ArrayList<>();

    public BoxGroup(Context context) {
        super(context);
    }

    public BoxGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoxGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void inflate() {
        setWinConditions();

        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof BoxView) {
                BoxView boxView = (BoxView) getChildAt(i);
                boxView.setUpInterface(this);
            }else if(getChildAt(i) instanceof TextView){
                playerTvs.add((TextView) getChildAt(i));
            }
        }

        setPlayers();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate();
    }

    @Override
    public void boxInfo(String tag) {

        if (playerOneTurn) {
            playerOneMoves.add(tag);

            for (int i = 0; i < winConditions.size(); i++) {
                int count = 0;
                for (int j = 0; j < winConditions.get(i).length; j++) {

                    if (playerOneMoves.contains(winConditions.get(i)[j])) {
                        count++;

                        if (count == 3) {
                            Toast.makeText(getContext(), "Player One Wins", Toast.LENGTH_SHORT).show();
                            winMode = winConditions.get(i)[0];
                            winStatus = true;
                            invalidate();
                            showDialog("Player One Winner");
                            break;
                        }
                    }

                }

            }
        } else {
            playerTwoMoves.add(tag);

            for (int i = 0; i < winConditions.size(); i++) {
                int count = 0;
                for (int j = 0; j < winConditions.get(i).length; j++) {

                    if (playerTwoMoves.contains(winConditions.get(i)[j])) {
                        count++;

                        if (count == 3) {
                            Toast.makeText(getContext(), "Player Two Wins", Toast.LENGTH_SHORT).show();
                            winStatus = true;
                            winMode = winConditions.get(i)[0];
                            invalidate();
                            showDialog("Player Two Winner");
                            break;
                        }
                    }

                }

            }
        }

        noOfMoves++;

        if (!winStatus && noOfMoves == Constants.TOTAL_MOVES) {
            showDialog(Constants.TXT_DRAW);
        }

        playerOneTurn = !playerOneTurn;
        invalidate();
    }

    /**
     * sets up win conditions
     */
    public void setWinConditions() {
        winConditions.add(new String[]{"H1", "1", "2", "3"});
        winConditions.add(new String[]{"H2", "4", "5", "6"});
        winConditions.add(new String[]{"H3", "9", "8", "7"});
        winConditions.add(new String[]{"D2", "7", "5", "3"});
        winConditions.add(new String[]{"V1", "1", "4", "7"});
        winConditions.add(new String[]{"V2", "2", "5", "8"});
        winConditions.add(new String[]{"V3", "3", "6", "9"});
        winConditions.add(new String[]{"D1", "1", "5", "9"});
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (winStatus) {
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(20);

            switch (winMode) {
                case "H1":
                    canvas.drawLine(px(20), px(175), getWidth() - px(20), px(175), paint);
                    break;
                case "H2":
                    canvas.drawLine(px(20), px(280), getWidth() - px(20), px(280), paint);
                    break;
                case "H3":
                    canvas.drawLine(px(20), px(385), getWidth() - px(20), px(385), paint);
                    break;
                case "V1":
                    canvas.drawLine(px(80), px(140), px(80), px(420), paint);
                    break;
                case "V2":
                    canvas.drawLine(px(182), px(140), px(182), px(420), paint);
                    break;
                case "V3":
                    canvas.drawLine(px(285), px(140), px(285), px(420), paint);
                    break;
                case "D1":
                    canvas.drawLine(px(80), px(180), px(285), px(380), paint);
                    break;
                case "D2":
                    canvas.drawLine(px(285), px(180), px(80), px(380), paint);
                    break;
            }

        }
    }

    /**
     * Show win/draw dialog
     */
    private void showDialog(String message) {
        Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);

        TextView textView = (TextView) dialog.findViewById(R.id.tv_status);
        textView.setText(message);

        dialog.show();
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     */
    public float px(float dp) {
        Resources resources = getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);

        return px;
    }

    /**
     * get player name text views from arraylist containing player textviews and populate them
     */
    public void setPlayers(){
        playerTvs.get(0).setText(Constants.NAME_PLAYER_1);
        playerTvs.get(1).setText(Constants.NAME_PLAYER_2);
    }

}
