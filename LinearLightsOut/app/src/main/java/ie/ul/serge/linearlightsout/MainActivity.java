package ie.ul.serge.linearlightsout;

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
    private Button [] mButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLightsOutGame = new LightsOutGame (7);
        mGameText = findViewById(R.id.headerMessage);
        mButtons = new Button[7];
        mButtons[0] = findViewById(R.id.button0);
        mButtons[1] = findViewById(R.id.button1);
        mButtons[2] = findViewById(R.id.button2);
        mButtons[3] = findViewById(R.id.button3);
        mButtons[4] = findViewById(R.id.button4);
        mButtons[5] = findViewById(R.id.button5);
        mButtons[6] = findViewById(R.id.button6);
        updateView();

        }

        public void pressedButton (View view){
            String pressedStr = view.getTag().toString();
            int pressedInt = Integer.parseInt(pressedStr);
            mLightsOutGame.pressedButtonAtIndex(pressedInt);
            Toast.makeText(this,"Pressed button "+ pressedInt,Toast.LENGTH_SHORT).show();
            updateView();

        }

        public void startNewGame(View view){
            mLightsOutGame = new LightsOutGame (7);
            Toast.makeText(this,"pressed new game",Toast.LENGTH_SHORT).show();
            updateView();


        }

        private void updateView(){
        mGameText.setText("You have made "+mLightsOutGame.getNumPresses() + " attempts.");
        for (int i=0;i<7;i++){
            mButtons[i].setText(mLightsOutGame.getValueAtIndex(i)+"");
        }

        }
}
