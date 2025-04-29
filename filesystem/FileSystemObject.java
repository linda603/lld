package filesystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FileSystemObject {
    private String name;
    private Map<String, FileSystemObject> children;

    public FileSystemObject(String name) {
        this.name = name;
        this.children = new HashMap<>();
    }

    public abstract boolean isFile();

    public String getName() {
        return name;
    }

    public Collection<FileSystemObject> getChildren() {
        return children.values();
    }

    public List<String> display() {
        List<String> allChildren = new ArrayList<>(children.keySet());
        Collections.sort(allChildren);
        return allChildren;
    }

    public void addChild(String name, FileSystemObject child) {
        children.put(name, child);
    }

    public boolean hasChild(String name) {
        return children.containsKey(name);
    }

    public FileSystemObject getChild(String name) {
        return children.get(name);
    }

    public boolean removeChild(String name) {
        if (hasChild(name)) {
            children.remove(name);
            return true;
        }
        return false;
    }
}
