package com.demo.securedoc.event;

import com.demo.securedoc.entity.UserEntity;
import com.demo.securedoc.enumarations.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {
    private UserEntity user;
    private EventType eventType;
    private Map<?, ?> data;
}
