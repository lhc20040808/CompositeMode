package lhc.compositemode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lhc.compositemode.OnItemExpandListener;
import lhc.compositemode.R;
import lhc.compositemode.model.Dir;
import lhc.compositemode.model.Document;
import lhc.compositemode.model.Folder;
import lhc.compositemode.viewholder.BaseViewHolder;
import lhc.compositemode.viewholder.FileViewHolder;
import lhc.compositemode.viewholder.FolderViewHolder;

/**
 * 作者：LHC on 2017/5/2 11:19
 * 描述：
 */
public class DirRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<Dir> mData;

    public DirRecyclerAdapter(Context mContext, List<Dir> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    private OnItemExpandListener itemExpandListener = new OnItemExpandListener() {
        @Override
        public void onExpand(Dir dir, int pos) {
            List<Dir> list = getChildrenByPath(dir.getPath(), dir.getTreeDepth());
            if (list != null && list.size() > 0) {
                mData.addAll(pos + 1, list);
                notifyItemRangeChanged(pos + 1, list.size());
                dir.setDirs(list);
            }
        }

        @Override
        public void onHide(Dir dir, int pos) {
            List<Dir> list = dir.getDirs();
            if (list != null && list.size() > 0) {
                removeAll(pos + 1, getChildCount(dir) - 1);
            }
            dir.clear();
        }
    };

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case Dir.DOCUMENT:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_file, parent, false);
                return new FileViewHolder(view);
            default:
            case Dir.FOLDER:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_folder, parent, false);
                return new FolderViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Dir.FOLDER:
                FolderViewHolder folderViewHolder = (FolderViewHolder) holder;
                folderViewHolder.bindView(mData.get(position), position, itemExpandListener);
                break;
            case Dir.DOCUMENT:
                FileViewHolder fileViewHolder = (FileViewHolder) holder;
                fileViewHolder.bindView(mData.get(position), position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public List<Dir> getChildrenByPath(String path, int treeDepth) {
        treeDepth++;
        try {
            List<Dir> list = new ArrayList<>();
            List<Dir> fileList = new ArrayList<>();
            File file = new File(path);
            File[] files = file.listFiles();
            for (File child : files) {
                if (child.isDirectory()) {
                    Folder folder = new Folder();
                    folder.setName(child.getName());
                    folder.setPath(child.getAbsolutePath());
                    folder.setTreeDepth(treeDepth);
                    list.add(folder);
                } else {
                    Document document = new Document();
                    document.setName(child.getName());
                    document.setPath(child.getAbsolutePath());
                    document.setTreeDepth(treeDepth);
                    fileList.add(document);
                }
            }
            Collections.sort(list);
            Collections.sort(fileList);
            list.addAll(fileList);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void removeAll(int pos, int count) {
        for (int i = 0; i < count; i++) {
            mData.remove(pos);
        }
        notifyItemRangeRemoved(pos, count);
    }

    private int getChildCount(Dir dir) {
        List<Dir> list = new ArrayList<>();
        countChild(dir, list);
        return list.size();
    }

    private void countChild(Dir dir, List<Dir> list) {
        list.add(dir);
        if (dir.getType() == Dir.FOLDER && dir.getDirs() != null) {
            for (int i = 0; i < dir.getDirs().size(); i++) {
                countChild(dir.getDirs().get(i), list);
            }
        }
    }

}
