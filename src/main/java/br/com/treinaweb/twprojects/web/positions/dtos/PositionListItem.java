package br.com.treinaweb.twprojects.web.positions.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionListItem {

    private Long id;

    private String name;
    
}
