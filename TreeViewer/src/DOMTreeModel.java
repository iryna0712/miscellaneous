import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class DOMTreeModel implements TreeModel {

    private Document doc;

    public DOMTreeModel(Document doc) {
        this.doc = doc;
    }
    @Override
    public Object getRoot() {
        return doc.getDocumentElement();
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((Node) parent).getChildNodes().item(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((Node) parent).getChildNodes().getLength();
    }

    @Override
    public boolean isLeaf(Object node) {
        return (getChildCount(node) == 0);
    }

    /**
     * @return -1 if such child node was not found.
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        int index = -1;

        NodeList list = ((Node) parent).getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i) == child) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {}

    @Override
    public void addTreeModelListener(TreeModelListener l) {}

    @Override
    public void removeTreeModelListener(TreeModelListener l) {}
}
