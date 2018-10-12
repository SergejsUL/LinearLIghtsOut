package ie.ul.serge.linearlightsout;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.sql.StatementEvent;

public class MainActivity extends AppCompatActivity {

    private LightsOutGame mLightsOutGame;
    private TextView mGameText;
    private Button[] mButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLightsOutGame = new LightsOutGame(7);
        mGameText = findViewById(R.id.headerMessage);
        mButtons = new Button[7];
        mButtons[0] = findViewById(R.id.button0);
        mButtons[1] = findViewById(R.id.button1);
        mButtons[2] = findViewById(R.id.button2);
        mButtons[3] = findViewById(R.id.button3);
        mButtons[4] = findViewById(R.id.button4);
        mButtons[5] = findViewById(R.id.button5);
        mButtons[6] = findViewById(R.id.button6);


        if (savedInstanceState != null) {
            mLightsOutGame.setNumPresses(savedInstanceState.getInt("numPresses", 0));
            int[] newValues = savedInstanceState.getIntArray("newValues");
            mLightsOutGame.setAllValues(newValues);

        }

        updateView();

    }

    public void pressedButton(View view) {
        String pressedStr = view.getTag().toString();
        int pressedInt = Integer.parseInt(pressedStr);
        mLightsOutGame.pressedButtonAtIndex(pressedInt);
        //Toast.makeText(this, "Pressed button " + pressedInt, Toast.LENGTH_SHORT).show();
        updateView();

    }

    public void startNewGame(View view) {
        mLightsOutGame = new LightsOutGame(7);
        mLightsOutGame.setNumPresses(0);
        //Toast.makeText(this,"pressed new game",Toast.LENGTH_SHORT).show();
        updateView();


    }

    private void updateView() {
        String attmpt;
        if (mLightsOutGame.getNumPresses() > 1) {
            attmpt = " attempts";
        } else attmpt = " attempt";
        //update text on the buttons
        for (int i = 0; i < 7; i++) {
            mButtons[i].setText(mLightsOutGame.getValueAtIndex(i) + "");
        }
        //update status message
        if (mLightsOutGame.getNumPresses() > 0) {
            if (mLightsOutGame.checkForWin()) {
                mGameText.setText("You have WON by making " + mLightsOutGame.getNumPresses() + attmpt + ".");
            } else
                mGameText.setText("You have made " + mLightsOutGame.getNumPresses() + attmpt + ".");
        } else {
            mGameText.setText(R.string.headerMessage);
        }
    }

    //save variables on screen rotation
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("numPresses", mLightsOutGame.getNumPresses());
        int[] newValues = new int[7];
        for (int i = 0; i < mButtons.length; i++) {
            newValues[i] = mLightsOutGame.getValueAtIndex(i);
        }
        outState.putIntArray("newValues", newValues);

    }
}
