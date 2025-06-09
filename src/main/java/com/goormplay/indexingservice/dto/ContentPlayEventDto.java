package com.goormplay.indexingservice.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentPlayEventDto {
    private String userId;
    private String videoId;
    private String timestamp;
    private String eventType;      // e.g., "play", "pause", "end", "exit"
    private String page;
}
