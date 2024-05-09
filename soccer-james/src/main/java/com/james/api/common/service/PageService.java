package com.james.api.common.service;

import com.james.api.common.model.PageDto;

public interface PageService {
    PageDto getPageDto(Long totalCount, Long pageNumber, Long pageSize);
}
