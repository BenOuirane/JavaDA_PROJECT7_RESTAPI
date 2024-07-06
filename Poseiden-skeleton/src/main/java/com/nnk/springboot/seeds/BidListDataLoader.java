package com.nnk.springboot.seeds;

import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Component
public class BidListDataLoader implements CommandLineRunner {

    @Autowired
    private BidListRepository bidListRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBidListData();
    }

    private void loadBidListData() {
        if (bidListRepository.count() == 0) {
            BidList bid1 = new BidList(null, "Account1", "Type1", 100.0, 110.0, 105.0, 115.0, "Benchmark1", Timestamp.from(Instant.now()), "Commentary1", "Security1", "Status1", "Trader1", "Book1", "CreationName1", Timestamp.from(Instant.now()), "RevisionName1", Timestamp.from(Instant.now()), "DealName1", "DealType1", "SourceListId1", "Side1");
            BidList bid2 = new BidList(null, "Account2", "Type2", 200.0, 210.0, 205.0, 215.0, "Benchmark2", Timestamp.from(Instant.now()), "Commentary2", "Security2", "Status2", "Trader2", "Book2", "CreationName2", Timestamp.from(Instant.now()), "RevisionName2", Timestamp.from(Instant.now()), "DealName2", "DealType2", "SourceListId2", "Side2");
            BidList bid3 = new BidList(null, "Account3", "Type3", 300.0, 310.0, 305.0, 315.0, "Benchmark3", Timestamp.from(Instant.now()), "Commentary3", "Security3", "Status3", "Trader3", "Book3", "CreationName3", Timestamp.from(Instant.now()), "RevisionName3", Timestamp.from(Instant.now()), "DealName3", "DealType3", "SourceListId3", "Side3");

            bidListRepository.save(bid1);
            bidListRepository.save(bid2);
            bidListRepository.save(bid3);

            System.out.println("Sample bid list data loaded.");
        } else {
            System.out.println("Bid list data already exists.");
        }
    }
}