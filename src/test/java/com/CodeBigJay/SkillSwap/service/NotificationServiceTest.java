package com.CodeBigJay.SkillSwap.service;

import com.CodeBigJay.SkillSwap.model.Notification;
import com.CodeBigJay.SkillSwap.model.NotificationType;
import com.CodeBigJay.SkillSwap.model.User;
import com.CodeBigJay.SkillSwap.repository.NotificationRepository;
import com.CodeBigJay.SkillSwap.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
    @Mock NotificationRepository notifications;
    @Mock UserRepository users;
    @InjectMocks NotificationService service;

    private User member;

    @BeforeEach
    void setUp() {
        member = new User();
        member.setId(7L);
        member.setUsername("learner");
        member.setEmail("learner@example.test");
    }

    @Test
    void sendsNotificationToTheIntendedMember() {
        when(users.findById(7L)).thenReturn(Optional.of(member));
        when(notifications.save(any(Notification.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Notification saved = service.sendNotification(7L, "A member requested an exchange.", NotificationType.EXCHANGE_SCHEDULED);

        assertEquals(member, saved.getUser());
        assertEquals("A member requested an exchange.", saved.getMessage());
        assertEquals(NotificationType.EXCHANGE_SCHEDULED, saved.getType());
        assertFalse(saved.isRead());
        verify(notifications).save(saved);
    }

    @Test
    void marksOnlyTheCurrentMembersNotificationAsRead() {
        Notification notification = new Notification();
        notification.setId(12L);
        notification.setUser(member);
        notification.setMessage("Your exchange was accepted.");
        notification.setType(NotificationType.SYSTEM);
        when(notifications.findByIdAndUser_Id(12L, 7L)).thenReturn(Optional.of(notification));
        when(notifications.save(notification)).thenReturn(notification);

        Notification updated = service.markAsRead(12L, 7L);

        assertTrue(updated.isRead());
        verify(notifications).findByIdAndUser_Id(12L, 7L);
        verify(notifications).save(notification);
    }

    @Test
    void cannotMarkAnotherMembersNotificationAsRead() {
        when(notifications.findByIdAndUser_Id(12L, 7L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.markAsRead(12L, 7L));

        verify(notifications, never()).save(any());
    }
}
