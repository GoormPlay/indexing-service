package com.goormplay.indexingservice.converter;

import com.goormplay.indexingservice.avro.*;
import com.goormplay.indexingservice.dto.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DtoToAvroConverter {
    public static ClickEventLog toAvro(ClickEventLogDto dto) {
        return ClickEventLog.newBuilder()
                .setUserId(dto.getUserId())
                .setVideoId(dto.getVideoId())
                .setTimestamp(Instant.parse(dto.getTimestamp()))
                .setEventType(dto.getEventType())
                .setContentCategory(dto.getContentCategory())
                .setPage(dto.getPage())
                .build();
    }

    public static ContentPlayEvent toAvro(ContentPlayEventDto dto) {
        return ContentPlayEvent.newBuilder()
                .setUserId(dto.getUserId())
                .setVideoId(dto.getVideoId())
                .setTimestamp(Instant.parse(dto.getTimestamp()))
                .setEventType(dto.getEventType())
                .setPage(dto.getPage())
                .build();
    }

    public static CreateReviewEvent toAvro(CreateReviewEventDto dto) {
        return CreateReviewEvent.newBuilder()
                .setUserId(dto.getUserId())
                .setVideoId(dto.getVideoId())
                .setTimestamp(Instant.parse(dto.getTimestamp()))
                .setReview(dto.getReview())
                .setEventType(dto.getEventType())
                .setPage(dto.getPage())
                .build();
    }

    public static LikeToggleEvent toAvro(LikeToggleEventDto dto) {
        return LikeToggleEvent.newBuilder()
                .setUserId(dto.getUserId())
                .setVideoId(dto.getVideoId())
                .setTimestamp(Instant.parse(dto.getTimestamp()))
                .setLiked(dto.isLiked())
                .setEventType(dto.getEventType())
                .setPage(dto.getPage())
                .build();
    }

    public static RatingEvent toAvro(RatingEventDto dto) {
        return RatingEvent.newBuilder()
                .setUserId(dto.getUserId())
                .setVideoId(dto.getVideoId())
                .setTimestamp(Instant.parse(dto.getTimestamp()))
                .setRating(dto.getRating())
                .setEventType(dto.getEventType())
                .setPage(dto.getPage())
                .build();
    }

    public static VideoEvent toAvro(VideoEventDto dto) {
        return VideoEvent.newBuilder()
                .setVideoId(dto.getVideoId())
                .setEventType(dto.getEventType())
                .setTimestamp(Instant.parse(dto.getTimestamp()))
                .setCurrentTime(dto.getCurrentTime())
                .build();
    }

    private static long toMillis(String isoTimestamp) {
        return Instant.parse(isoTimestamp).toEpochMilli();
    }
}
