package geekgrains.java.section4.level1.mynet.dictionary;

public enum MessageTypeCode {
    JAVA("queue-java", "java"),
    PHP("queue-php", "php"),
    SQL("queue-sql", "sql");

    private String name;
    private String key;

    MessageTypeCode(String name, String key) {this.name = name; this.key = key;}

    public String getName() {return name;}
    public String getKey() {return key;}
}