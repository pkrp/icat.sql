/*
 * Parameter.java
 *
 * Created on 08 February 2007, 10:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package uk.icat3.entity;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import uk.icat3.exceptions.ValidationException;

/**
 * Entity class Parameter
 * 
 * @author gjd37
 */
@Entity
@Table(name = "PARAMETER")
@NamedQueries( {
        @NamedQuery(name = "Parameter.findByName", query = "SELECT p FROM Parameter p WHERE p.parameterPK.name = :name"),
        @NamedQuery(name = "Parameter.findByUnits", query = "SELECT p FROM Parameter p WHERE p.parameterPK.units = :units"),
        @NamedQuery(name = "Parameter.findByUnitsLongVersion", query = "SELECT p FROM Parameter p WHERE p.unitsLongVersion = :unitsLongVersion"),
        @NamedQuery(name = "Parameter.findBySearchable", query = "SELECT p FROM Parameter p WHERE p.searchable = :searchable"),
        @NamedQuery(name = "Parameter.findByNumericValue", query = "SELECT p FROM Parameter p WHERE p.numericValue = :numericValue"),
        @NamedQuery(name = "Parameter.findByNonNumericValueFormat", query = "SELECT p FROM Parameter p WHERE p.nonNumericValueFormat = :nonNumericValueFormat"),
        @NamedQuery(name = "Parameter.findByIsSampleParameter", query = "SELECT p FROM Parameter p WHERE p.isSampleParameter = :isSampleParameter"),
        @NamedQuery(name = "Parameter.findByIsDatasetParameter", query = "SELECT p FROM Parameter p WHERE p.isDatasetParameter = :isDatasetParameter"),
        @NamedQuery(name = "Parameter.findByIsDatafileParameter", query = "SELECT p FROM Parameter p WHERE p.isDatafileParameter = :isDatafileParameter"),
        @NamedQuery(name = "Parameter.findByDescription", query = "SELECT p FROM Parameter p WHERE p.description = :description"),
        @NamedQuery(name = "Parameter.findByModId", query = "SELECT p FROM Parameter p WHERE p.modId = :modId"),
        @NamedQuery(name = "Parameter.findByModTime", query = "SELECT p FROM Parameter p WHERE p.modTime = :modTime")
    })
public class Parameter extends EntityBaseBean implements Serializable {

    /**
     * EmbeddedId primary key field
     */
    @EmbeddedId
    protected ParameterPK parameterPK;

    @Column(name = "UNITS_LONG_VERSION")
    private String unitsLongVersion;

    @Column(name = "SEARCHABLE", nullable = false)
    private String searchable;

    @Column(name = "NUMERIC_VALUE", nullable = false)
    private String numericValue;

    @Column(name = "NON_NUMERIC_VALUE_FORMAT")
    private String nonNumericValueFormat;

    @Column(name = "IS_SAMPLE_PARAMETER", nullable = false)
    private String isSampleParameter;

    @Column(name = "IS_DATASET_PARAMETER", nullable = false)
    private String isDatasetParameter;

    @Column(name = "IS_DATAFILE_PARAMETER", nullable = false)
    private String isDatafileParameter;

    @Column(name = "DESCRIPTION")
    private String description;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameter")
    @XmlTransient
    private Collection<DatasetParameter> datasetParameterCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameter")
    @XmlTransient
    private Collection<SampleParameter> sampleParameterCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameter")
    @XmlTransient
    private Collection<DatafileParameter> datafileParameterCollection;
    
    /** Creates a new instance of Parameter */
    public Parameter() {
    }

    /**
     * Creates a new instance of Parameter with the specified values.
     * @param parameterPK the parameterPK of the Parameter
     */
    public Parameter(ParameterPK parameterPK) {
        this.parameterPK = parameterPK;
    }

    /**
     * Creates a new instance of Parameter with the specified values.
     * @param parameterPK the parameterPK of the Parameter
     * @param searchable the searchable of the Parameter
     * @param numericValue the numericValue of the Parameter
     * @param isSampleParameter the isSampleParameter of the Parameter
     * @param isDatasetParameter the isDatasetParameter of the Parameter
     * @param isDatafileParameter the isDatafileParameter of the Parameter
     * @param modId the modId of the Parameter
     * @param modTime the modTime of the Parameter
     */
    public Parameter(ParameterPK parameterPK, String searchable, String numericValue, String isSampleParameter, String isDatasetParameter, String isDatafileParameter, String modId, Date modTime) {
        this.parameterPK = parameterPK;
        this.searchable = searchable;
        this.numericValue = numericValue;
        this.isSampleParameter = isSampleParameter;
        this.isDatasetParameter = isDatasetParameter;
        this.isDatafileParameter = isDatafileParameter;
        this.modId = modId;
        this.modTime = modTime;
    }

    /**
     * Creates a new instance of ParameterPK with the specified values.
     * @param units the units of the ParameterPK
     * @param name the name of the ParameterPK
     */
    public Parameter(String units, String name) {
        this.parameterPK = new ParameterPK(units, name);
    }

    /**
     * Gets the parameterPK of this Parameter.
     * @return the parameterPK
     */
    public ParameterPK getParameterPK() {
        return this.parameterPK;
    }

    /**
     * Sets the parameterPK of this Parameter to the specified value.
     * @param parameterPK the new parameterPK
     */
    public void setParameterPK(ParameterPK parameterPK) {
        this.parameterPK = parameterPK;
    }

    /**
     * Gets the unitsLongVersion of this Parameter.
     * @return the unitsLongVersion
     */
    public String getUnitsLongVersion() {
        return this.unitsLongVersion;
    }

    /**
     * Sets the unitsLongVersion of this Parameter to the specified value.
     * @param unitsLongVersion the new unitsLongVersion
     */
    public void setUnitsLongVersion(String unitsLongVersion) {
        this.unitsLongVersion = unitsLongVersion;
    }

    /**
     * Gets the searchable of this Parameter.
     * @return the searchable
     */
    public String getSearchable() {
        return this.searchable;
    }

    /**
     * Sets the searchable of this Parameter to the specified value.
     * @param searchable the new searchable
     */
    public void setSearchable(String searchable) {
        this.searchable = searchable;
    }

    /**
     * Gets the numericValue of this Parameter.
     * @return the numericValue
     */
    public String getNumericValue() {
        return this.numericValue;
    }

    /**
     * Sets the numericValue of this Parameter to the specified value.
     * @param numericValue the new numericValue
     */
    public void setNumericValue(String numericValue) {
        this.numericValue = numericValue;
    }

    /**
     * Gets the nonNumericValueFormat of this Parameter.
     * @return the nonNumericValueFormat
     */
    public String getNonNumericValueFormat() {
        return this.nonNumericValueFormat;
    }

    /**
     * Sets the nonNumericValueFormat of this Parameter to the specified value.
     * @param nonNumericValueFormat the new nonNumericValueFormat
     */
    public void setNonNumericValueFormat(String nonNumericValueFormat) {
        this.nonNumericValueFormat = nonNumericValueFormat;
    }

    /**
     * Gets the isSampleParameter of this Parameter.
     * @return the isSampleParameter
     */
    public String getIsSampleParameter() {
        return this.isSampleParameter;
    }

    /**
     * Sets the isSampleParameter of this Parameter to the specified value.
     * @param isSampleParameter the new isSampleParameter
     */
    public void setIsSampleParameter(String isSampleParameter) {
        this.isSampleParameter = isSampleParameter;
    }

    /**
     * Gets the isDatasetParameter of this Parameter.
     * @return the isDatasetParameter
     */
    public String getIsDatasetParameter() {
        return this.isDatasetParameter;
    }

    /**
     * Sets the isDatasetParameter of this Parameter to the specified value.
     * @param isDatasetParameter the new isDatasetParameter
     */
    public void setIsDatasetParameter(String isDatasetParameter) {
        this.isDatasetParameter = isDatasetParameter;
    }

    /**
     * Gets the isDatafileParameter of this Parameter.
     * @return the isDatafileParameter
     */
    public String getIsDatafileParameter() {
        return this.isDatafileParameter;
    }

    /**
     * Sets the isDatafileParameter of this Parameter to the specified value.
     * @param isDatafileParameter the new isDatafileParameter
     */
    public void setIsDatafileParameter(String isDatafileParameter) {
        this.isDatafileParameter = isDatafileParameter;
    }

    /**
     * Gets the description of this Parameter.
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of this Parameter to the specified value.
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }
       
    /**
     * Gets the datasetParameterCollection of this Parameter.
     * @return the datasetParameterCollection
     */
    @XmlTransient
    public Collection<DatasetParameter> getDatasetParameterCollection() {
        return this.datasetParameterCollection;
    }

    /**
     * Sets the datasetParameterCollection of this Parameter to the specified value.
     * @param datasetParameterCollection the new datasetParameterCollection
     */
    public void setDatasetParameterCollection(Collection<DatasetParameter> datasetParameterCollection) {
        this.datasetParameterCollection = datasetParameterCollection;
    }

    /**
     * Gets the sampleParameterCollection of this Parameter.
     * @return the sampleParameterCollection
     */
    @XmlTransient
    public Collection<SampleParameter> getSampleParameterCollection() {
        return this.sampleParameterCollection;
    }

    /**
     * Sets the sampleParameterCollection of this Parameter to the specified value.
     * @param sampleParameterCollection the new sampleParameterCollection
     */
    public void setSampleParameterCollection(Collection<SampleParameter> sampleParameterCollection) {
        this.sampleParameterCollection = sampleParameterCollection;
    }

    /**
     * Gets the datafileParameterCollection of this Parameter.
     * @return the datafileParameterCollection
     */
    @XmlTransient
    public Collection<DatafileParameter> getDatafileParameterCollection() {
        return this.datafileParameterCollection;
    }

    /**
     * Sets the datafileParameterCollection of this Parameter to the specified value.
     * @param datafileParameterCollection the new datafileParameterCollection
     */
    public void setDatafileParameterCollection(Collection<DatafileParameter> datafileParameterCollection) {
        this.datafileParameterCollection = datafileParameterCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.parameterPK != null ? this.parameterPK.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Parameter.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Parameter object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parameter)) {
            return false;
        }
        Parameter other = (Parameter)object;
        if (this.parameterPK != other.parameterPK && (this.parameterPK == null || !this.parameterPK.equals(other.parameterPK))) return false;
        return true;
    }

    /**
     * Method to be overridden if needed to check if the data held in the entity is valid.
     * This method checks whether all the fields which are marked as not null are not null
     *
     * @throws ValidationException if validation error.
     * @return true if validation is correct,
     */
    @Override
    public boolean isValid() throws ValidationException {
        
        //get public the fields in class
        Field[] allFields = this.getClass().getDeclaredFields();
        //all subclasses should use this line below
        //Field[] allFields = getClass().getDeclaredFields();
        outer:
            for (int i = 0; i < allFields.length; i++) {
            //get name of field
            String fieldName = allFields[i].getName();
            
            //check if field is labeled id and generateValue (primary key, then it can be null)
            boolean id = false;
            boolean generateValue = false;
            
            for (Annotation a : allFields[i].getDeclaredAnnotations()) {
                if(a.annotationType().getName().equals(javax.persistence.Id.class.getName())){
                    id = true;     }
                if(a.annotationType().getName().equals(javax.persistence.GeneratedValue.class.getName())){
                    generateValue = true;
                }
                if(generateValue && id) {
                    log.trace(getClass().getSimpleName()+": "+fieldName+" is auto generated id value, no need to check.");
                    continue outer;
                }
            }
            
            //now check all annoatations
            for (Annotation a : allFields[i].getDeclaredAnnotations()) {
                //if this means its a none null column field
                if(a.annotationType().getName().equals(
                        javax.persistence.Column.class.getName()) && a.toString().contains("nullable=false") ){
                    
                    //now check if it is null, if so throw error
                    try {
                        //get value
                        if(allFields[i].get(this) == null){
                            throw new ValidationException(getClass().getSimpleName()+": "+fieldName+" cannot be null.");
                        } else {
                            log.trace(getClass().getSimpleName()+": "+fieldName+" is valid");
                        }
                    } catch (IllegalAccessException ex) {
                        log.warn(getClass().getSimpleName()+": "+fieldName+" cannot be accessed.");
                    }
                }
            }
            }
                     
        //ok here
        return super.isValid();
    }
    
    
    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "uk.icat3.entity.Parameter[parameterPK=" + parameterPK + "]";
    }
    
    public boolean isDataSetParameter(){
        if(getIsDatasetParameter() != null && getIsDatasetParameter().equalsIgnoreCase("Y")) return true;
        else return false;
    }
    
    public boolean isDatafileParameter(){
        if(getIsDatafileParameter() != null && getIsDatafileParameter().equalsIgnoreCase("Y")) return true;
        else return false;
    }
    
    public boolean isSampleParameter(){
        if(getIsSampleParameter()!= null && getIsSampleParameter().equalsIgnoreCase("Y")) return true;
        else return false;
    }
    
    public boolean isNumeric(){
        if(getNumericValue()!= null && getNumericValue().equalsIgnoreCase("Y")) return true;
        else return false;
    }
    
}
