package com.james.api.common.serviceImpl;


import com.james.api.common.model.PageDto;
import com.james.api.common.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PageServiceImpl implements PageService {
    private Long calBlockCount;
    private Long blockSize=PageDto.BLOCK_SIZE;

    @Override
    public PageDto getPageDto(Long total,Long paramPazeSize,Long paramPageNum) {
        calBlockCount = total % (paramPazeSize * blockSize) == 0 ? total / (paramPazeSize * blockSize)
                : total / (paramPazeSize * blockSize) + 1;

        return PageDto.builder()
                .totalCount(total)
                .pageSize(paramPazeSize)
                .pageCount(total % paramPazeSize == 0 ? total / paramPazeSize : total / paramPazeSize + 1)
                .blockCount(calBlockCount)
                .startRow(paramPazeSize * (paramPageNum - 1))
                .endRow(paramPazeSize * (paramPageNum - 1) + paramPazeSize - 1)
                .startPage(blockSize + (paramPageNum / blockSize - 1))
                .endPage(blockSize + (paramPageNum / blockSize - 1) + blockSize - 1)
                .blockNumber(paramPageNum / blockSize)
                .pageNumber(paramPageNum)
                .existPrev(paramPageNum / blockSize != 0)
                .existNext(paramPageNum / blockSize + 1 != calBlockCount)
                .build();
        
    }
}
