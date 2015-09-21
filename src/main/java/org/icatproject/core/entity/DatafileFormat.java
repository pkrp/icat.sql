package org.icatproject.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.mappings.querykeys.OneToManyQueryKey;
import org.icatproject.core.entity.Datafile;

@Comment("A data file format")
@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "FACILITY_ID", "NAME", "VERSION" }) })
public class DatafileFormat extends EntityBaseBean implements Serializable, DescriptorCustomizer {
	
	@Override
    public void customize(ClassDescriptor descriptor) throws Exception {
        // Direct (basic) query for the Oracle DB's ROWID value
        descriptor.addDirectQueryKey("rowId", "ROWID");
 
        // 1:M query accessing all projects which have a M:1 teamLeader reference to this employee
        // this can also be used to allow querying of large collections not wanted mapped
        OneToManyQueryKey projectsQueryKey = new OneToManyQueryKey();
        projectsQueryKey.setName("datafiles");
        projectsQueryKey.setReferenceClass(Datafile.class);
        ExpressionBuilder builder = new ExpressionBuilder();
        projectsQueryKey.setJoinCriteria(builder.getField("DATAFILE.DATAFILEFORMAT").equal(builder.getParameter("DATAFILEFORMAT.ID")));
        descriptor.addQueryKey(projectsQueryKey);
    }

	@Comment("The facility which has defined this format")
	@JoinColumn(name = "FACILITY_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Facility facility;

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	/*@Comment("Files with this format")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "datafileFormat")*/
	//private List<Datafile> datafiles = new ArrayList<Datafile>();

	@Comment("An informal description of the format")
	private String description;

	@Comment("Holds the underlying format - such as binary or text")
	private String type;

	@Comment("A short name identifying the format -e.g. \"mp3\" within the facility")
	@Column(name = "NAME", nullable = false)
	private String name;

	@Comment("The version if needed.  The version code may be part of the basic name")
	@Column(name = "VERSION", nullable = false)
	private String version;

	/* Needed for JPA */
	public DatafileFormat() {
	}

	/*public List<Datafile> getDatafiles() {
		return this.datafiles;
	}*/

	public String getDescription() {
		return this.description;
	}

	public String getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public String getVersion() {
		return this.version;
	}

	/*public void setDatafiles(List<Datafile> datafiles) {
		this.datafiles = datafiles;
	}*/

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
