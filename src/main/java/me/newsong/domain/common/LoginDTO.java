package me.newsong.domain.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
   
}
