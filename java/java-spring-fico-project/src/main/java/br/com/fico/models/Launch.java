package br.com.fico.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.fico.helpers.BigDecimalToStringSerializerHelper;
import br.com.fico.helpers.CalendarToStringSerializerHelpser;
import br.com.fico.helpers.DateTimeHelper;
import br.com.fico.helpers.StringToBigDecimalDeserializerHelper;
import br.com.fico.helpers.StringToCalendarDeserializerHelper;

@Entity
public class Launch implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String description;

	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = CalendarToStringSerializerHelpser.class)
	@JsonDeserialize(using = StringToCalendarDeserializerHelper.class)
	private Calendar createdDate = Calendar.getInstance();

	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = CalendarToStringSerializerHelpser.class)
	@JsonDeserialize(using = StringToCalendarDeserializerHelper.class)
	private Calendar doneDate;

	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = CalendarToStringSerializerHelpser.class)
	@JsonDeserialize(using = StringToCalendarDeserializerHelper.class)
	@Column(nullable = false)
	private Calendar maturityDate;

	@JsonSerialize(using = BigDecimalToStringSerializerHelper.class)
	@JsonDeserialize(using = StringToBigDecimalDeserializerHelper.class)
	@Column(nullable = false)
	private BigDecimal amount = new BigDecimal(0.00);

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private LaunchType type;

	/**
	 * org.hibernate.type.NumericBooleanType 1=true, 0=false
	 */
	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean done = false;

	@ManyToOne(optional = false)
	private Category category;
	
	@ManyToOne
	private Bank bank;

	@Transient
	private Boolean late; // atrasado

	public Launch() {
		super();
	}

	public Launch(String description, BigDecimal amount, Calendar maturityDate, LaunchType type){
		this.description = description;
		this.amount = amount;
		this.createdDate = Calendar.getInstance();
		this.maturityDate = maturityDate;
		this.type = type;
	}
	
	public Launch(Long id, String description, Calendar createdDate, Calendar doneDate, Calendar maturityDate,
			BigDecimal amount, Boolean done, LaunchType type, Category category) {
		super();
		this.id = id;
		this.description = description;
		this.createdDate = createdDate;
		this.doneDate = doneDate;
		this.maturityDate = maturityDate;
		this.amount = amount;
		this.done = done;
		this.type = type;
		this.category = category;
	}

	public Launch(Long id, String description, BigDecimal amount, Bank bank, LaunchType type, Category category) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.type = type;
		this.bank = bank;
		this.category = category;
	}

	public Launch(Long id, String description, Calendar createdDate, Calendar doneDate, Calendar maturityDate,
			BigDecimal amount, Boolean done, Bank bank, LaunchType type, Category category) {
		this(id, description, createdDate, doneDate, maturityDate, amount, done, type, category);
		this.id = id;
		this.description = description;
		this.createdDate = createdDate;
		this.doneDate = doneDate;
		this.maturityDate = maturityDate;
		this.amount = amount;
		this.done = done;
		this.type = type;
		this.bank = bank;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public Calendar getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(Calendar doneDate) {
		this.doneDate = doneDate;
	}

	public Calendar getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Calendar maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Boolean getLate() {
		late = Calendar.getInstance().getTime().after(this.getMaturityDate().getTime());
		return late;
	}

	public LaunchType getType() {
		return type;
	}

	public void setType(LaunchType type) {
		this.type = type;
	}

	public void setLate(Boolean late) {
		this.late = late;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Launch other = (Launch) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Launch [id=" + id + ", description=" + description + ", type=" + type + ", createdDate="
				+ DateTimeHelper.toDate(createdDate) + ", doneDate=" + DateTimeHelper.toDate(doneDate)
				+ ", maturityDate=" + DateTimeHelper.toDate(maturityDate) + ", amount=" + amount + ", done=" + done
				+ ", bank=" + bank + ", category=" + category + "]";
	}

}
