package by.rudenko.imarket.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Advert Topic DTO class to use in RestAPI
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertTopicDTO {

    private Long id;
    private String topicName;
    private String topicSubName;

    public Long getId() {
        return id;
    }

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

}
