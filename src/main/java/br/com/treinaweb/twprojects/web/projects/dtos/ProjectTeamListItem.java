package br.com.treinaweb.twprojects.web.projects.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTeamListItem {

    private String name;

    private String position;
    
}
