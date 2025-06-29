package br.com.api.neomind.apirestneomind.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "fornecedores")
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f ORDER BY f.name"),
    @NamedQuery(name = "Fornecedor.findByEmail", query = "SELECT f FROM Fornecedor f WHERE f.email = :email"),
    @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj")
})
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(
          min = 2,
          max = 100,
          message = "Nome deve ter entre 2 e 100 caracteres"
    )
    @Column(
            name = "name",
            nullable = false,
            length = 100
    )
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter formato válido")
    @Size(
          max = 150,
          message = "Email deve ter no máximo 150 caracteres"
    )
    @Column(
            name = "email",
            nullable = false,
            length = 150,
            unique = true
    )
    private String email;

    @Size(
          max = 500,
          message = "Comentário deve ter no máximo 500 caracteres"
    )
    @Column(
            name = "comment",
            length = 500
    )
    private String comment;

    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(
            regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}",
            message = "CNPJ deve estar no formato XX.XXX.XXX/XXXX-XX"
    )
    @Column(
            name = "cnpj",
            nullable = false,
            length = 18,
            unique = true
    )
    private String cnpj;

    public Fornecedor() {
    }

    public Fornecedor(String name, String email, String comment, String cnpj) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fornecedor that = (Fornecedor) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
