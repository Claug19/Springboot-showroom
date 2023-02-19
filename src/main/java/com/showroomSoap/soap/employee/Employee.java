/**
 * <p>Java class for employee complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="employee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jobtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */

package com.showroomSoap.soap.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
        "id",
        "user_name",
        "user_password",
        "user_email"
})
public class Employee {

    protected int id;
    @XmlElement(required = true)
    protected String user_name;
    @XmlElement(required = true)
    protected String user_password;
    @XmlElement(required = true)
    protected String user_email;

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String value) {
        this.user_name = value;
    }

    public String getUserPassword() {
        return user_password;
    }

    public void setUserPassword(String value) {
        this.user_password = value;
    }

    public String getUserEmail() {
        return user_email;
    }

    public void setUserEmail(String value) {
        this.user_email = value;
    }
}