package com.example.ggyy.phoneguard.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.ggyy.phoneguard.R;
import com.example.ggyy.phoneguard.domain.BlackNumberInfo;
import com.example.ggyy.phoneguard.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;

public class CallMsmSafeActivity extends Activity {
    @Bind(R.id.ll_add_number_tips)
    LinearLayout llAddNumberTips;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.lv_callsms_safe)
    ListView lvCallsmsSafe;
    private Activity act = this;
    private List<BlackNumberInfo> infos; // 代表就是当前界面的集合。
    /**
     * 开始获取数据的位置
     */
    private int startIndex = 0;

    /**
     * 一次最多获取几条数据
     */
    private int maxCount = 20;

    private int totalCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniUI();
        fillData();

    }

    private void fillData() {

    }

    /**
     * 初始化UI
     */

    private void iniUI() {
        setContentView(R.layout.activity_callsms_safe);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            lvCallsmsSafe.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {
                    switch (i) {
                        case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                            int lastVisiblePosition = lvCallsmsSafe.getLastVisiblePosition();
                            System.out.println("最后一个条目" + lastVisiblePosition);
                            if (lastVisiblePosition == infos.size() - 1) {
                                startIndex += maxCount;
                                if (startIndex >= totalCount) {
                                    ToastUtils.showToast(act, "no more data");
                                    return;
                                }
                            } else {

                            }
                    }
                }

                @Override
                public void onScroll(AbsListView absListView, int i, int i1, int i2) {

                }
            });
        } else {
            ToastUtils.showToast(act, "api版本问题 导致ListView即在错误");
        }*/

    }
}
