package project.messenger.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import project.messenger.model.Status;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private Long senderId;
    private Long receiverId;
    private String message;
    @Enumerated(EnumType.STRING)
    private Status status;
}
