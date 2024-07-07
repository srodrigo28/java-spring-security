package br.com.treinaweb.twprojects.web.projects.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectForm {

    private String name;

    private String description;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate endDate;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate startDate;

    private BigDecimal budget;

    private BigDecimal cost;

    private Long client;

    private Long manager;

    private List<Long> team;
    
}
