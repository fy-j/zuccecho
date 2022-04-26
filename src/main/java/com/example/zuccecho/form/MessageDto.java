package com.example.zuccecho.form;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MessageDto implements Serializable {

    private static final long serialVersionUID = -2116367492649751914L;
    private List<String> recipient;//邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容

}
