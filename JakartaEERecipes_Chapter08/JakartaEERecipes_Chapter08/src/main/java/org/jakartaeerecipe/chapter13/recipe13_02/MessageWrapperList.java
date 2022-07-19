package org.jakartaeerecipe.chapter13.recipe13_02;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name="messageWrapperList")
@XmlSeeAlso(String.class)
public class MessageWrapperList extends ArrayList<String> {
    private static final long serialVersionUID = 1L;

    public MessageWrapperList(){
        super();
    }

    public MessageWrapperList(Collection<? extends String> message){
        super(message);
    }

    @XmlElement(name="messageList")
    public List<String> getMessageList() {
        return this;
    }

    public void setMessageList(List<String> messages) {
        this.addAll(messages);
    }
}
