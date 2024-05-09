package com.james.api.common.service.impl;


import com.james.api.common.model.PageDto;
import com.james.api.common.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {

    Long BLOCK_SIZE = PageDto.BLOCK_SIZE;
    @Override
    public PageDto getPageDto(Long totalCount, Long pageNumber, Long pageSize) {

        //pageCount => totalCount  / pageSize
        Long pageCount = (totalCount % pageSize==0) ? totalCount/pageSize : (totalCount/pageSize)+1;
        //blockCount =>  totalCount 34  / (pageSize * BLOCK_SIZE)
        Long blockCount = (totalCount % (pageSize*BLOCK_SIZE)==0) ? totalCount/(pageSize*BLOCK_SIZE) : (totalCount/(pageSize*BLOCK_SIZE))+1;

        Long blockNumber = pageNumber/BLOCK_SIZE;

        // 0~9 페이지 number 1부터 시작이니까 -1 처리 후  10페이지 단위로 넘어감
        // 페이지 row 0 / 페이지는 10단위
        // 페이지는 1부터 시작이지만 row 시작은 0이기 때문에 -1처리하면 0부터 시작

        Long startRow = pageSize * (pageNumber-1);
        Long endRow = startRow + (pageSize-1);

        Long startPage = BLOCK_SIZE + (blockNumber-1);
        Long endPage = startPage+BLOCK_SIZE-1;



        Long nextBlock = blockNumber+1;  // < | > BLOCK 단위로 움직인다.
        Long prevBlock = blockNumber-1;

        boolean existPrev = blockNumber > 1;
        boolean existNext = blockNumber < blockCount;



        log.info("MY-INFO : Controller searchPlayer totalCount is {}", totalCount);
        log.info("MY-INFO : Controller searchPlayer pageCount is {}", pageCount);
        log.info("MY-INFO : Controller searchPlayer blockCount is {}", blockCount);
        log.info("MY-INFO : Controller searchPlayer startRow is {}", startRow);
        log.info("MY-INFO : Controller searchPlayer endRow is {}", endRow);
        log.info("MY-INFO : Controller searchPlayer blockNumber is {}", blockNumber);
        log.info("MY-INFO : Controller searchPlayer startPage is {}", startPage);
        log.info("MY-INFO : Controller searchPlayer endPage is {}", endPage);
        log.info("MY-INFO : Controller searchPlayer existPrev is {}", existPrev);
        log.info("MY-INFO : Controller searchPlayer existNext is {}", existNext);
        log.info("MY-INFO : Controller searchPlayer nextBlock is {}", nextBlock);
        log.info("MY-INFO : Controller searchPlayer prevBlock is {}", prevBlock);

        return  PageDto.builder()
                .pageSize(pageSize)
                .pageCount(pageCount)
                .totalCount(totalCount)
                .blockCount(blockCount)
                .pageNumber(pageNumber)
                .blockNumber(blockNumber)
                .startRow(startRow)
                .endRow(endRow)
                .startPage(startPage)
                .endPage(endPage)
                .nextBlock(nextBlock)
                .prevBlock(prevBlock)
                .existNext(existNext)
                .existPrev(existPrev)
                .build();

    }
}
