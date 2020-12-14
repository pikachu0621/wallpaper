package com.pikachu.book.tools.state;

import com.pikachu.book.tools.base.BaseActivity;

//继承这个防止用户其他操作又变回来了
//系统清楚 flag 重新
public class PKStatusBarActivity extends BaseActivity {
    private PKStatusBarTools pkStatusBarTools;

    @Override
    protected void onRestart() {
        super.onRestart();
        if (pkStatusBarTools != null)
            pkStatusBarTools.init();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (pkStatusBarTools != null)
            pkStatusBarTools.init();
    }


    public void setPkStatusBarTools(PKStatusBarTools pkStatusBarTools) {
        if (pkStatusBarTools != null)
            this.pkStatusBarTools = pkStatusBarTools;
    }
}
