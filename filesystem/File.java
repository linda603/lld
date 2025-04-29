package filesystem;

public class File extends FileSystemObject {
    private String content;

    public File(String name) {
        super(name);
    }

    @Override
    public boolean isFile() {
        return true;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
