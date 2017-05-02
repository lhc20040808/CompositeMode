package lhc.compositemode.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：LHC on 2017/5/2 10:03
 * 描述：文件和文件夹抽象类
 */
public abstract class Dir implements Comparable<Dir> {
    public static final int DOCUMENT = 0;
    public static final int FOLDER = 1;

    protected int type;

    private String uuid;

    private String name;

    private boolean expand;

    private String path;

    private int treeDepth;

    protected List<Dir> dirs = new ArrayList<>();

    public abstract void addFile(Dir dir);

    public abstract void rmFile(Dir dir);

    public abstract void clear();

    public abstract void print();

    public abstract List<Dir> getDirs();

    public abstract void setDirs(List<Dir> d);

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTreeDepth() {
        return treeDepth;
    }

    public void setTreeDepth(int treeDepth) {
        this.treeDepth = treeDepth;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(Dir dir) {
        return this.getName().compareTo(dir.getName());
    }
}
