package lhc.compositemode.model;

import java.util.List;

/**
 * 作者：LHC on 2017/5/2 10:10
 * 描述：文件
 */
public class Document extends Dir {

    public Document() {
        this.type = Dir.DOCUMENT;
    }

    @Override
    public void addFile(Dir dir) {
        throw new UnsupportedOperationException("文件对象不支持该类型操作");
    }

    @Override
    public void rmFile(Dir dir) {
        throw new UnsupportedOperationException("文件对象不支持该类型操作");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("文件对象不支持该类型操作");
    }

    @Override
    public void print() {
        System.out.print(getName());
    }

    @Override
    public List<Dir> getDirs() {
        throw new UnsupportedOperationException("文件对象不支持该类型操作");
    }

    @Override
    public void setDirs(List<Dir> dirs) {
        throw new UnsupportedOperationException("文件对象不支持该类型操作");
    }
}
