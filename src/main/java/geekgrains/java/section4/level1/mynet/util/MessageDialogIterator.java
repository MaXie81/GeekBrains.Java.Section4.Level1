package geekgrains.java.section4.level1.mynet.util;

import geekgrains.java.section4.level1.mynet.entity.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageDialogIterator implements Iterator<Message>{
    private List<Message> list;
    private final int MAX_POSITION;
    private int currentPosition;

    public MessageDialogIterator(List<Message> list) {
        this.list = new ArrayList<>(list);
        this.list.sort((o1, o2) -> o1.getDatetime().compareTo(o2.getDatetime()));        
        MAX_POSITION = this.list.size();
        currentPosition = 0;
        this.list = setList(this.list);
    }

    @Override
    public boolean hasNext() {
        return currentPosition < MAX_POSITION;
    }

    @Override
    public Message next() {
        ++currentPosition;
        return list.get(currentPosition - 1);
    }
    
    private List<Message> setList(List<Message> list) {
        List<Message> newList = new ArrayList<>();
        List<String> loginList = new ArrayList<>();

        while (newList.size() < list.size()) {
            loginList.clear();

            for (Message message : list) {
                if (newList.contains(message)) continue;

                if (loginList.size() == 0) {
                    loginList.add(message.getAuthorUser().getLogin());
                    loginList.add(message.getRecipientUser().getLogin());

                    newList.add(message);
                } else {
                    if (loginList.contains(message.getAuthorUser().getLogin()) & loginList.contains(message.getRecipientUser().getLogin())) {
                        newList.add(message);
                    }
                }
            }
        }
        return newList;
    }
}
