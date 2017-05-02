package lhc.compositemode.viewholder;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lhc.compositemode.OnItemExpandListener;
import lhc.compositemode.R;
import lhc.compositemode.model.Dir;

/**
 * 作者：LHC on 2017/5/2 10:43
 * 描述：
 */
public class FolderViewHolder extends BaseViewHolder {
    private RelativeLayout rl_dir;
    private ImageView img_arrow;
    private TextView tv_name;
    private int leftMargin;

    public FolderViewHolder(View itemView) {
        super(itemView);
        rl_dir = (RelativeLayout) itemView.findViewById(R.id.rl_dir);
        img_arrow = (ImageView) itemView.findViewById(R.id.img_arrow);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        leftMargin = itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.item_margin);
    }

    public void bindView(final Dir dir, final int pos, final OnItemExpandListener listener) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) img_arrow.getLayoutParams();
        params.leftMargin = leftMargin * dir.getTreeDepth();
        rl_dir.setLayoutParams(params);
        tv_name.setText(dir.getName());
        if (dir.isExpand()) {
            img_arrow.setRotation(45);
        } else {
            img_arrow.setRotation(0);
        }

        rl_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    if (dir.isExpand()) {
                        listener.onHide(dir, pos);
                        dir.setExpand(false);
                        rotationExpandIcon(45, 0);
                    } else {
                        listener.onExpand(dir, pos);
                        dir.setExpand(true);
                        rotationExpandIcon(0, 45);
                    }
                }
            }
        });
    }

    private void rotationExpandIcon(int from, int to) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(150);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                img_arrow.setRotation((Float) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

}
