package lhc.compositemode;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lhc.compositemode.adapter.DirRecyclerAdapter;
import lhc.compositemode.model.Dir;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyler_view;
    private DirRecyclerAdapter mAdapter;
    private List<Dir> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyler_view = (RecyclerView) findViewById(R.id.recyler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyler_view.setLayoutManager(linearLayoutManager);

        recyler_view.getItemAnimator().setAddDuration(100);
        recyler_view.getItemAnimator().setRemoveDuration(100);
        recyler_view.getItemAnimator().setMoveDuration(100);
        recyler_view.getItemAnimator().setChangeDuration(100);

        mAdapter = new DirRecyclerAdapter(this, list);
        recyler_view.setAdapter(mAdapter);
        list.addAll(mAdapter.getChildrenByPath(Environment.getExternalStorageDirectory().getAbsolutePath(), 0));
        mAdapter.notifyDataSetChanged();

    }
}
