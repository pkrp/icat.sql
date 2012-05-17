package org.icatproject.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.icatproject.core.IcatException;


@Comment("A parameter associated with a data set")
@SuppressWarnings("serial")
@Entity
@TableGenerator(name = "datasetParameterGenerator", pkColumnValue = "DatasetParameter")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "DATASET_ID", "PARAMETER_TYPE_ID" }) })
@XmlRootElement
public class DatasetParameter extends Parameter implements Serializable {

	private static Logger logger = Logger.getLogger(DatasetParameter.class);

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "datasetParameterGenerator")
	private Long id;

	@Comment("The associated data set")
	@JoinColumn(name = "DATASET_ID", nullable = false)
	@ManyToOne
	private Dataset dataset;

	/* Needed for JPA */
	public DatasetParameter() {
	}

	@Override
	public void beforeMarshal(Marshaller source) {
		logger.trace("Marshalling DatasetParameter for " + this.includes);
		if (!this.includes.contains(Dataset.class)) {
			this.dataset = null;
		}
	}

	public Dataset getDataset() {
		return this.dataset;
	}

	public Long getId() {
		return this.id;
	}

	@Override
	public Object getPK() {
		return this.id;
	}

	@Override
	public void preparePersist(String modId, EntityManager manager) throws IcatException {
		super.preparePersist(modId, manager);
		this.id = null;
		if (type == null) {
			throw new IcatException(IcatException.IcatExceptionType.VALIDATION, "Type of parameter is not set");
		}
		if (!type.isApplicableToDataset()) {
			throw new IcatException(IcatException.IcatExceptionType.VALIDATION, "Parameter of type "
					+ type.getName() + " is not applicable to a Dataset");
		}
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DatasetParameter[id=" + this.id + "]";
	}
}