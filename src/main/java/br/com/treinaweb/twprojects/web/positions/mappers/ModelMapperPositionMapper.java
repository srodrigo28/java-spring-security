package br.com.treinaweb.twprojects.web.positions.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojects.core.models.Position;
import br.com.treinaweb.twprojects.web.positions.dtos.PositionForm;
import br.com.treinaweb.twprojects.web.positions.dtos.PositionListItem;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "br.com.treinaweb.twprojects.mappers.provider", havingValue = "modelmapper")
public class ModelMapperPositionMapper implements PositionMapper {

    private final ModelMapper modelMapper;

    @Override
    public PositionForm toPositionForm(Position position) {
        return modelMapper.map(position, PositionForm.class);
    }

    @Override
    public Position toPosition(PositionForm positionForm) {
        return modelMapper.map(positionForm, Position.class);
    }

    @Override
    public PositionListItem toPositionListItem(Position position) {
        return modelMapper.map(position, PositionListItem.class);
    }
    
}
