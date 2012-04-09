/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author Syndarin
 */
public class XMLHelper {

    public String generateXML(String root, String[] nodes, String[] values) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buffer.append(createOpenTag(root));
        for (int i = 0; i < nodes.length; i++) {
            buffer.append(createNode(nodes[i], values[i]));
        }
        buffer.append(createCloseTag(root));
        return buffer.toString();
    }

    private String createNode(String nodeName, String value) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(createOpenTag(nodeName));
        buffer.append(value);
        buffer.append(createCloseTag(nodeName));
        return buffer.toString();
    }

    private String createOpenTag(String tagName) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<").append(tagName).append(">");
        return buffer.toString();
    }

    private String createCloseTag(String tagName) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("</").append(tagName).append(">");
        return buffer.toString();
    }
}
