package geekgrains.java.section4.level1.mynet.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User authorUser;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipientUser;
    @Column(name = "body")
    private String body;
    @Column(name = "created_at")
    private Timestamp datetime;
}
