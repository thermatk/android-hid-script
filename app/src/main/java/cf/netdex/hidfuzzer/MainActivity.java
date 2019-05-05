package cf.netdex.hidfuzzer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ToggleButton;

import java.io.File;

import cf.netdex.hidfuzzer.ltask.HIDTask;
import cf.netdex.hidfuzzer.ltask.LuaTaskLoader;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "hidfuzzer";
    public static final String SCRIPT_PATH = "scripts";

    private HIDTask mRunningTask;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sort out the UI
        final ToggleButton btnPoll = findViewById(R.id.btnPoll);

        btnPoll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                String selectedTask = "wallpaper2.lua";
                String taskSrcFilePath = SCRIPT_PATH + File.separator + selectedTask;
                mRunningTask = LuaTaskLoader.createTaskFromLuaFile(this, selectedTask, taskSrcFilePath);
                mRunningTask.execute();
            } else {
                if (mRunningTask != null) {
                    btnPoll.setEnabled(false);
                    mRunningTask.cancel(false);
                }
            }
        });
    }
}
