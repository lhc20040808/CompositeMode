package lhc.compositemode;

import lhc.compositemode.model.Dir;

/**
 * 作者：LHC on 2017/5/2 11:32
 * 描述：
 */
public interface OnItemExpandListener {
    void onExpand(Dir dir,int pos);

    void onHide(Dir dir,int pos);
}
