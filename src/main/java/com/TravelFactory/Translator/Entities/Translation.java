package com.TravelFactory.Translator.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "translations", uniqueConstraints = @UniqueConstraint(columnNames = {"translation_key_id", "language_code"}))
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "translation_key_id")
    private TranslationKey translationKey;

    @Column(name = "language_code")
    private String languageCode;

    private String text;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Override
    public boolean equals(Object object) {
        if(object instanceof Translation) {
            return ((Translation) object).getId().equals(this.getLanguageCode());
        }
        return false;
    }
}
