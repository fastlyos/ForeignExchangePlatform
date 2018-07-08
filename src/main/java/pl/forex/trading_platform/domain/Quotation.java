package pl.forex.trading_platform.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    @OneToOne(cascade = CascadeType.ALL)
    private BidPriceBucket bidPriceBucket;

    @OneToOne(cascade = CascadeType.ALL)
    private AskPriceBucket askPriceBucket;

    public Quotation(LocalDateTime dateTime, Instrument instrument, BidPriceBucket bidPriceBucket, AskPriceBucket askPriceBucket) {
        this.dateTime = dateTime;
        this.instrument = instrument;
        this.bidPriceBucket = bidPriceBucket;
        this.askPriceBucket = askPriceBucket;
    }
}
