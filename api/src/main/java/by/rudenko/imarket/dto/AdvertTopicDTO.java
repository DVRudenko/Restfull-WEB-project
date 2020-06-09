package by.rudenko.imarket.model;

import javax.persistence.*;


/**
 * класс описывает модель Раздела объявления  AdvertTopic
 */

@javax.persistence.Entity
@Table(name = "advert_topics")
public class AdvertTopic implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "topic_sub_name")
    private String topicSubName;


    public AdvertTopic() {
    }

    public AdvertTopic(long id, String topicName, String topicSubName) {
        this.id = id;
        this.topicName = topicName;
        this.topicSubName = topicSubName;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicSubName() {
        return topicSubName;
    }

    public void setTopicSubName(String topicSubName) {
        this.topicSubName = topicSubName;
    }

    @Override
    public String toString() {
        return "AdvertTopic{" +
                "id=" + id +
                ", topicName='" + topicName + '\'' +
                ", topicSubName='" + topicSubName + '\'' +
                '}';
    }
}
