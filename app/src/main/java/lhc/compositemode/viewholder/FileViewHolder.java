package lhc.compositemode.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import lhc.compositemode.R;
import lhc.compositemode.model.Dir;

/**
 * 作者：LHC on 2017/5/2 10:43
 * 描述：
 */
public class FileViewHolder extends BaseViewHolder {
    private RelativeLayout rl_dir;
    private ImageView img_arrow;
    private TextView tv_name;
    private int leftMargin;

    public FileViewHolder(View itemView) {
        super(itemView);
        rl_dir = (RelativeLayout) itemView.findViewById(R.id.rl_dir);
        img_arrow = (ImageView) itemView.findViewById(R.id.img_arrow);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        leftMargin = itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.item_margin);
    }

    public void bindView(final Dir dir, int pos) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) img_arrow.getLayoutParams();
        params.leftMargin = leftMargin * (dir.getTreeDepth() + 1);
        rl_dir.setLayoutParams(params);
        tv_name.setText(dir.getName());
        img_arrow.setVisibility(View.INVISIBLE);

        rl_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rl_dir.getContext(), "打开文件" + dir.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
