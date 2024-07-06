package com.nnk.springboot.seeds;

import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;


@Component
public class TradeDataLoader implements CommandLineRunner {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public void run(String... args) throws Exception {
        loadTradeData();
    }

    private void loadTradeData() {
        if (tradeRepository.count() == 0) {
            Trade trade1 = new Trade(1, "Account1", "Type1", 100.0, 200.0, 50.0, 60.0, "Benchmark1", Timestamp.from(Instant.now()), "Security1", "Status1", "Trader1", "Book1", "CreationName1", null, "RevisionName1", null, "DealName1", "DealType1", "SourceListId1", "Side1");
            Trade trade2 = new Trade(2, "Account2", "Type2", 150.0, 250.0, 55.0, 65.0, "Benchmark2", Timestamp.from(Instant.now()), "Security2", "Status2", "Trader2", "Book2", "CreationName2", null, "RevisionName2", null, "DealName2", "DealType2", "SourceListId2", "Side2");
            Trade trade3 = new Trade(3, "Account3", "Type3", 200.0, 300.0, 60.0, 70.0, "Benchmark3", Timestamp.from(Instant.now()), "Security3", "Status3", "Trader3", "Book3", "CreationName3", null, "RevisionName3", null, "DealName3", "DealType3", "SourceListId3", "Side3");

            tradeRepository.save(trade1);
            tradeRepository.save(trade2);
            tradeRepository.save(trade3);

            System.out.println("Sample trades data loaded.");
        } else {
            System.out.println("Trades data already exists.");
        }
    }
}