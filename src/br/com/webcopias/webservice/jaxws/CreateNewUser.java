
package br.com.webcopias.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createNewUser", namespace = "http://webservice.webcopias.com.br/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createNewUser", namespace = "http://webservice.webcopias.com.br/", propOrder = {
    "matricula",
    "nome",
    "email",
    "senha",
    "limiteCopias",
    "papeis"
})
public class CreateNewUser {

    @XmlElement(name = "matricula", namespace = "")
    private String matricula;
    @XmlElement(name = "nome", namespace = "")
    private String nome;
    @XmlElement(name = "email", namespace = "")
    private String email;
    @XmlElement(name = "senha", namespace = "")
    private String senha;
    @XmlElement(name = "limiteCopias", namespace = "")
    private String limiteCopias;
    @XmlElement(name = "papeis", namespace = "")
    private String papeis;

    /**
     * 
     * @return
     *     returns String
     */
    public String getMatricula() {
        return this.matricula;
    }

    /**
     * 
     * @param matricula
     *     the value for the matricula property
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * 
     * @param nome
     *     the value for the nome property
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 
     * @param email
     *     the value for the email property
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * 
     * @param senha
     *     the value for the senha property
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getLimiteCopias() {
        return this.limiteCopias;
    }

    /**
     * 
     * @param limiteCopias
     *     the value for the limiteCopias property
     */
    public void setLimiteCopias(String limiteCopias) {
        this.limiteCopias = limiteCopias;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getPapeis() {
        return this.papeis;
    }

    /**
     * 
     * @param papeis
     *     the value for the papeis property
     */
    public void setPapeis(String papeis) {
        this.papeis = papeis;
    }

}
