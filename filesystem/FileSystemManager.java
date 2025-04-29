package filesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSystemManager {
    private FileSystemObject root;
    private FileSystemObject currDirectory;

    public FileSystemManager() {
        this.root = new Directory("/");
    }

    public FileSystemObject getCurrDir() {
        return currDirectory;
    }

    public boolean isValidPath(String path) {
        return path != null && !path.isEmpty() && path.startsWith("/");
    }

    public boolean createPath(String path) {
        if (!isValidPath(path)) {
            return false;
        }
        // pathComponents = ["", "a", "b", "c"]
        String[] pathComponents = path.split("/");
        System.out.println(pathComponents);
        String lastComponent = pathComponents[pathComponents.length - 1];
        if (lastComponent.isEmpty()) {
            return false;
        }
        FileSystemObject curr = root;

        for (int i = 1; i < pathComponents.length; i++) {
            String component = pathComponents[i];
            if (component.isEmpty()) {
                continue;
            }
            if (!curr.hasChild(component)) {
                FileSystemObject newDir = new Directory(component);
                curr.addChild(component, newDir);
            }
            FileSystemObject child = curr.getChild(component);
            if (child.isFile()) {
                return false;
            }
            curr = child;
        }
        return true;
    }

    // getObject /a/b/c -> c
    public FileSystemObject getObject(String path) {
        if (!isValidPath(path)) {
            return null;
        }

        if (path.equals("/")) {
            return root;
        }

        // pathComponents = ["", "a", "b", "c"]
        String[] pathComponents = path.split("/");
        FileSystemObject curr = root;

        for (int i = 1; i < pathComponents.length; i++) {
            String component = pathComponents[i];
            if (component.isEmpty()) {
                continue;
            }
            if (!curr.hasChild(component)) {
                return null;
            }
            curr = curr.getChild(component);
        }
        return curr;
    }

    public boolean getDirectory(String path) {
        if (!isValidPath(path)) {
            return false;
        }

        if (path.equals("/")) {
            currDirectory = root;
            return true;
        }

        FileSystemObject object = getObject(path);
        if (object instanceof File) {
            return false;
        }
        currDirectory = object;
        return true;
    }

    public boolean setFileContent(String path, String content) {
        FileSystemObject object = getObject(path);
        if (object == null || !object.isFile()) {
            return false;
        }
        File file = (File) object;
        file.setContent(content);
        return true;
    }

    public String getFileContent(String path) {
        FileSystemObject object = getObject(path);
        if (object == null || !object.isFile()) {
            return null;
        }
        File file = (File) object;
        return file.getContent();
    }

    public void display(String path) {
        FileSystemObject object = getObject(path);
        if (object == null) {
            System.out.println("Invalid path!");
        }
        List<String> allChildren = object.display();
        System.out.print(Arrays.toString(allChildren.toArray()));
    }
}
