package com.goormplay.indexingservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goormplay.indexingservice.avro.ClickEventLog;
import com.goormplay.indexingservice.avro.LikeToggleEvent;
import com.goormplay.indexingservice.converter.DtoToAvroConverter;
import com.goormplay.indexingservice.dto.ContentPlayEventDto;
import com.goormplay.indexingservice.dto.LikeToggleEventDto;
import com.goormplay.indexingservice.dto.raw.RawContentPlayEventDto;
import com.goormplay.indexingservice.dto.raw.RawLikeToggleEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeToggleEventConsumer {
    private final KafkaTemplate<String, Object> avroKafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "raw-like-click-events", groupId = "indexing-group2")
    public void consume(String message) {
        try {
            RawLikeToggleEventDto eventDto = objectMapper.readValue(message, RawLikeToggleEventDto.class);
            log.info("Received event: {}", eventDto);
            LikeToggleEventDto dto = LikeToggleEventDto.builder()
                    .userId(eventDto.getUserId())
                    .videoId(eventDto.getVideoId())
                    .liked(eventDto.isLiked())
                    .timestamp(eventDto.getTimestamp())
                    .eventType("like_click")
                    .page("content_detail")
                    .build();
            publish(dto);
        }catch (Exception e) {
            log.error("Failed to process message while consuming video event: {}", message, e);
        }
    }

    private void publish(LikeToggleEventDto dto){
        try {
            LikeToggleEvent avro = DtoToAvroConverter.toAvro(dto);
            avroKafkaTemplate.send("content-user-events", avro);
            log.info("✅ Published processed like event: {}", avro);
        } catch (Exception e) {
            log.error("❌ Failed to serialize processed like event", e);
        }
    }
}
