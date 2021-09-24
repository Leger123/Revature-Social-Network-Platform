package com.revature.models;

import lombok.*;
/**
 * @author Zimi Li
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class  JSONResponse {
     private Boolean status;
     private String message;
     private Object data;
}
