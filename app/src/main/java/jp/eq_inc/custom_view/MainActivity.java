package jp.eq_inc.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jp.eq_inc.test_custom_view.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(jp.eq_inc.test_custom_view.R.layout.activity_main);

        AdapterLayout adapterLayout = findViewById(jp.eq_inc.test_custom_view.R.id.alList);
        adapterLayout.setDataArray(new ListItem[]{
                new ListItem(R.mipmap.ic_launcher, "title1", "description1"),
                new ListItem(R.mipmap.ic_launcher, "title2", "description2"),
                new ListItem(R.mipmap.ic_launcher, "title3", "description3"),
                new ListItem(R.mipmap.ic_launcher, "title4", "description4"),
                new ListItem(R.mipmap.ic_launcher, "title5", "description5"),
                new ListItem(R.mipmap.ic_launcher, "title6", "description6"),
                new ListItem(R.mipmap.ic_launcher, "title7", "description7"),
                new ListItem(R.mipmap.ic_launcher, "title8", "description8"),
                new ListItem(R.mipmap.ic_launcher, "title9", "description9"),
                new ListItem(R.mipmap.ic_launcher, "title10", "description10"),
                new ListItem(R.mipmap.ic_launcher, "title11", "description11"),
                new ListItem(R.mipmap.ic_launcher, "title12", "description12"),
                new ListItem(R.mipmap.ic_launcher, "title13", "description13"),
        });
    }

    private static class ListItem implements AdapterLayout.AdapterItemData {
        public int iconResId;
        private String title;
        public String description;

        public ListItem(int iconResId, String title, String description) {
            this.iconResId = iconResId;
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public int getLayoutResId() {
            return R.layout.item_layout;
        }
    }
}
