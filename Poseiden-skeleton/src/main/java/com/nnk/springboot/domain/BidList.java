package com.nnk.springboot.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "bidlist")
public class BidList {
	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Integer id;
	    @Column(nullable = false)
	    @NotBlank(message = "Account cannot be null")
	    private String account;
	    @Column(nullable = false)
	    @NotBlank(message = "Type cannot be null")
	    private String type;
	    @Column(nullable = false)
	    @NotNull(message = "bid Quantity cannot be null")
	    private Double bidQuantity;
	    private Double askQuantity;
	    private Double bid;
	    private Double ask;
	    private String benchmark;
	    private Timestamp bidListDate;
	    private String commentary;
	    private String security;
	    private String status;
	    private String trader;
	    private String book;
	    private String creationName;
	    @CreationTimestamp
	    @Column(updatable=false)
	    private Timestamp creationDate;
	    private String revisionName;
	    @UpdateTimestamp
	    private Timestamp revisionDate;
	    private String dealName;
	    private String dealType;
	    private String sourceListId;
	    private String side;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Double getBidQuantity() {
			return bidQuantity;
		}
		public void setBidQuantity(Double bidQuantity) {
			this.bidQuantity = bidQuantity;
		}
		public Double getAskQuantity() {
			return askQuantity;
		}
		public void setAskQuantity(Double askQuantity) {
			this.askQuantity = askQuantity;
		}
		public Double getBid() {
			return bid;
		}
		public void setBid(Double bid) {
			this.bid = bid;
		}
		public Double getAsk() {
			return ask;
		}
		public void setAsk(Double ask) {
			this.ask = ask;
		}
		public String getBenchmark() {
			return benchmark;
		}
		public void setBenchmark(String benchmark) {
			this.benchmark = benchmark;
		}
		public Timestamp getBidListDate() {
			return bidListDate;
		}
		public void setBidListDate(Timestamp bidListDate) {
			this.bidListDate = bidListDate;
		}
		public String getCommentary() {
			return commentary;
		}
		public void setCommentary(String commentary) {
			this.commentary = commentary;
		}
		public String getSecurity() {
			return security;
		}
		public void setSecurity(String security) {
			this.security = security;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getTrader() {
			return trader;
		}
		public void setTrader(String trader) {
			this.trader = trader;
		}
		public String getBook() {
			return book;
		}
		public void setBook(String book) {
			this.book = book;
		}
		public String getCreationName() {
			return creationName;
		}
		public void setCreationName(String creationName) {
			this.creationName = creationName;
		}
		public Timestamp getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Timestamp creationDate) {
			this.creationDate = creationDate;
		}
		public String getRevisionName() {
			return revisionName;
		}
		public void setRevisionName(String revisionName) {
			this.revisionName = revisionName;
		}
		public Timestamp getRevisionDate() {
			return revisionDate;
		}
		public void setRevisionDate(Timestamp revisionDate) {
			this.revisionDate = revisionDate;
		}
		public String getDealName() {
			return dealName;
		}
		public void setDealName(String dealName) {
			this.dealName = dealName;
		}
		public String getDealType() {
			return dealType;
		}
		public void setDealType(String dealType) {
			this.dealType = dealType;
		}
		public String getSourceListId() {
			return sourceListId;
		}
		public void setSourceListId(String sourceListId) {
			this.sourceListId = sourceListId;
		}
		public String getSide() {
			return side;
		}
		public void setSide(String side) {
			this.side = side;
		}
		public BidList(Integer id, String account, String type,
				Double bidQuantity, Double askQuantity, Double bid, Double ask,
				String benchmark, Timestamp bidListDate, String commentary,
				String security, String status, String trader, String book,
				String creationName, Timestamp creationDate,
				String revisionName, Timestamp revisionDate, String dealName,
				String dealType, String sourceListId, String side) {
			super();
			this.id = id;
			this.account = account;
			this.type = type;
			this.bidQuantity = bidQuantity;
			this.askQuantity = askQuantity;
			this.bid = bid;
			this.ask = ask;
			this.benchmark = benchmark;
			this.bidListDate = bidListDate;
			this.commentary = commentary;
			this.security = security;
			this.status = status;
			this.trader = trader;
			this.book = book;
			this.creationName = creationName;
			this.creationDate = creationDate;
			this.revisionName = revisionName;
			this.revisionDate = revisionDate;
			this.dealName = dealName;
			this.dealType = dealType;
			this.sourceListId = sourceListId;
			this.side = side;
		}
		public BidList() {
			super();
		}
		public BidList(String account, String type, double bidQuantity) {
			super();
			
			this.account = account;
			this.type = type;
			this.bidQuantity = bidQuantity;

			}
	
		@Override
		public String toString() {
			return "BidList [id=" + id + ", account=" + account + ", type="
					+ type + ", bidQuantity=" + bidQuantity + ", askQuantity="
					+ askQuantity + ", bid=" + bid + ", ask=" + ask
					+ ", benchmark=" + benchmark + ", bidListDate="
					+ bidListDate + ", commentary=" + commentary + ", security="
					+ security + ", status=" + status + ", trader=" + trader
					+ ", book=" + book + ", creationName=" + creationName
					+ ", creationDate=" + creationDate + ", revisionName="
					+ revisionName + ", revisionDate=" + revisionDate
					+ ", dealName=" + dealName + ", dealType=" + dealType
					+ ", sourceListId=" + sourceListId + ", side=" + side + "]";
		}
		

}

