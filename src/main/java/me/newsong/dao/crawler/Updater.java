package me.newsong.dao.crawler;

import me.newsong.dao.UserDOMapper;
import me.newsong.domain.entity.UserDO;
import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by SinjinSong on 2017/5/29.
 */
public class Updater extends BaseSpringTester {
    @Autowired
    private UserDOMapper userDOMapper;
    @Test
    public void update(){
        for (UserDO userDO : userDOMapper.findAllUnDisplay()) {
            userDO.setUserId(userDO.getUserName());
            userDOMapper.updateByPrimaryKeySelective(userDO);
        }
    }
}
