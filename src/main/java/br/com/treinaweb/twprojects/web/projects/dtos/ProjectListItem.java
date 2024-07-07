package br.com.treinaweb.twprojects.web.projects.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectListItem {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String client;

    private String manager;

    private BigDecimal budget;
    
}
