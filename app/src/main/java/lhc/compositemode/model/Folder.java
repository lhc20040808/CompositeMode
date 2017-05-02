package lhc.compositemode.model;

import java.util.Iterator;
import java.util.List;

/**
 * 作者：LHC on 2017/5/2 10:06
 * 描述：文件夹
 */
public class Folder extends Dir {

    public Folder() {
        this.type = Dir.FOLDER;
    }

    @Override
    public void addFile(Dir dir) {
        dirs.add(dir);
    }

    @Override
    public void rmFile(Dir dir) {
        dirs.remove(dir);
    }

    @Override
    public void clear() {
        dirs.clear();
    }

    @Override
    public void print() {
        System.out.print(getName() + "(");
        Iterator<Dir> iterator = dirs.iterator();
        while (iterator.hasNext()) {
            Dir dir = iterator.next();
            dir.print();
            if (iterator.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.print(")");
    }

    @Override
    public List<Dir> getDirs() {
        return dirs;
    }

    @Override
    public void setDirs(List<Dir> dirs) {
        this.dirs = dirs;
    }
}
