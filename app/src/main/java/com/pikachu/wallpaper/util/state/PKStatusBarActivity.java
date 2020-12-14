package com.pikachu.wallpaper.util.state;
import androidx.appcompat.app.AppCompatActivity;


//继承这个防止用户其他操作又变回来了
//系统清楚 flag 重新
public class PKStatusBarActivity extends AppCompatActivity {
    private PKStatusBarTools pkStatusBarTools;


    @Override
    protected void onRestart() {
        super.onRestart();
        if (pkStatusBarTools != null)
            pkStatusBarTools.init();
    }



    public void setPkStatusBarTools(PKStatusBarTools pkStatusBarTools) {
        if (pkStatusBarTools != null)
            this.pkStatusBarTools = pkStatusBarTools;
    }




}
