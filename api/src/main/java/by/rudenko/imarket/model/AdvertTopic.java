package by.rudenko.imarket.model;

import javax.persistence.*;
import java.util.Objects;


/**
 * класс описывает модель Раздела объявления  AdvertTopic
 */

@javax.persistence.Entity
@Table(name = "advert_topics")
public class AdvertTopic implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "topic_sub_name")
    private String topicSubName;

    public AdvertTopic() {
    }

    public AdvertTopic(Long id, String topicName, String topicSubName) {
        this.id = id;
        this.topicName = topicName;
        this.topicSubName = topicSubName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdvertTopic)) return false;
        AdvertTopic that = (AdvertTopic) o;
        return getTopicName().equals(that.getTopicName()) &&
                Objects.equals(getTopicSubName(), that.getTopicSubName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTopicName(), getTopicSubName());
    }
}
