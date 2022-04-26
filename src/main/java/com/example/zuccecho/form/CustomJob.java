package com.example.zuccecho.form;

import com.example.zuccecho.service.MessageService;
import com.example.zuccecho.util.CacheUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomJob implements Job {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MessageService messageService;

    public Integer Key;

    public void setKey(Integer key) {
        Key = key;
    }

    public Integer getKey() {
        return Key;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Set<StudentDto> studentDtos = redisTemplate.opsForSet().members(CacheUtil.cacheKey(CacheUtil.UNFINISHED_FORM,Key));
        messageService.send(studentDtos.stream().collect(Collectors.toList()));
    }
}
