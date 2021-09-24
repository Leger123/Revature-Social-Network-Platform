package com.revature.models;

import lombok.*;

/**
 * @author Zimi Li
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
     private Integer id;
     private String name;
     private String profileImage;
     private Long lastNotification;
}
