package br.com.treinaweb.twprojects.web.positions.mappers;

import br.com.treinaweb.twprojects.core.models.Position;
import br.com.treinaweb.twprojects.web.positions.dtos.PositionForm;
import br.com.treinaweb.twprojects.web.positions.dtos.PositionListItem;

public interface PositionMapper {

    PositionForm toPositionForm(Position position);

    Position toPosition(PositionForm positionForm);

    PositionListItem toPositionListItem(Position position);
    
}
