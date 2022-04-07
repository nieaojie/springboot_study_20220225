package com.example.domain;

import java.io.File;

import lombok.Data;

@Data
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String text;
    //是否解析文本中的html内容
    private boolean isHtml = false;
    //是否包含附件
    private boolean containsAttachment = false;
    private File attachment;
}
