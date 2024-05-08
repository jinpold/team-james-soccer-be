package com.james.api.common.serviceImpl;


import com.james.api.common.model.PageDto;
import com.james.api.common.service.PageService;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl implements PageService {
    public PageDto getPageDtO() {

//        totalCount = playerRouter.countAllplayers();
//        //pageCount => totalCount  / pageSize
//        pageCount = (totalCount % pageSize==0) ? totalCount/pageSize : (totalCount/pageSize)+1;
//        //blockCount =>  totalCount 34  / (pageSize * BLOCK_SIZE)
//        blockCount = (totalCount % (pageSize*BLOCK_SIZE)==0) ? totalCount/(pageSize*BLOCK_SIZE) : (totalCount/(pageSize*BLOCK_SIZE))+1;
//
//        // 0~9 페이지 number 1부터 시작이니까 -1 처리 후  10페이지 단위로 넘어감
//        // 페이지 row 0 / 페이지는 10단위
//        // 페이지는 1부터 시작이지만 row 시작은 0이기 때문에 -1처리하면 0부터 시작
//
//        startRow = pageSize * (pageNumber-1);
//        endRow = startRow + (pageSize-1);
//
//        startPage = BLOCK_SIZE + (blockNumber-1);
//        endPage = startPage+BLOCK_SIZE-1;
//
//        blockNumber = pageNumber/BLOCK_SIZE;
//        pageNumber = 1;
//
//        nextBlock = blockNumber+1;  // < | > BLOCK 단위로 움직인다.
//        prevBlock = blockNumber-1;
//
//        existPrev = blockNumber > 1;
//        existNext = blockNumber < blockCount;
//
//
//
//        log.info("MY-INFO : Controller searchPlayer totalCount is {}", totalCount);
//        log.info("MY-INFO : Controller searchPlayer pageCount is {}", pageCount);
//        log.info("MY-INFO : Controller searchPlayer blockCount is {}", blockCount);
//        log.info("MY-INFO : Controller searchPlayer startRow is {}", startRow);
//        log.info("MY-INFO : Controller searchPlayer endRow is {}", endRow);
//        log.info("MY-INFO : Controller searchPlayer blockNumber is {}", blockNumber);
//        log.info("MY-INFO : Controller searchPlayer startPage is {}", startPage);
//        log.info("MY-INFO : Controller searchPlayer endPage is {}", endPage);
//        log.info("MY-INFO : Controller searchPlayer existPrev is {}", existPrev);
//        log.info("MY-INFO : Controller searchPlayer existNext is {}", existNext);
//        log.info("MY-INFO : Controller searchPlayer nextBlock is {}", nextBlock);
//        log.info("MY-INFO : Controller searchPlayer prevBlock is {}", prevBlock);
//        log.info("Controller searchPlayer q is {}", q);

        return  PageDto.builder().build();




    }
}
