package geekgrains.java.section4.level1.mynet.util;

import geekgrains.java.section4.level1.mynet.entity.Message;

import java.util.Iterator;
import java.util.List;

public class MessageIterator implements Iterator<Message> {
    private List<Message> list;
    private Iterator<Message> iterator;

    public MessageIterator(List<Message> list) {
        this.list = list;
        list.sort((o1, o2) -> o1.getDatetime().compareTo(o2.getDatetime()));
        iterator = this.list.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Message next() {
        return iterator.next();
    }
}
