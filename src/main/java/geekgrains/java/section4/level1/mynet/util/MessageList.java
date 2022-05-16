package geekgrains.java.section4.level1.mynet.util;

import geekgrains.java.section4.level1.mynet.entity.Message;

import java.util.Iterator;
import java.util.List;

public class MessageList implements Iterable<Message>{
    private List<Message> list;
    private final MessageIteratorCode CODE;

    public MessageList(List<Message> list, MessageIteratorCode code) {
        this.list = list;
        this.CODE = code;
    }

    @Override
    public Iterator<Message> iterator() {
        switch (CODE) {
            case MAIN: return new MessageIterator(this.list);
            case DIALOG: return new MessageDialogIterator(this.list);
            default: return null;
        }
    }
}
