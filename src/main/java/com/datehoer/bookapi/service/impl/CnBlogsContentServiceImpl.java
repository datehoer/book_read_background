package com.datehoer.bookapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.datehoer.bookapi.mapper.CnBlogsContentMapper;
import com.datehoer.bookapi.model.CnBlogsContent;
import com.datehoer.bookapi.service.CnBlogsContentService;
import org.springframework.stereotype.Service;

@Service
public class CnBlogsContentServiceImpl extends ServiceImpl<CnBlogsContentMapper, CnBlogsContent> implements CnBlogsContentService {
}
