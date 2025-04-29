package filesystem;

public class Directory extends FileSystemObject {
    public Directory(String name) {
        super(name);
    }

    @Override
    public boolean isFile() {
        return false;
    }
}
